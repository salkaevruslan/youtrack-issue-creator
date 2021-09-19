import com.fasterxml.jackson.databind.ObjectMapper
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class HttpSender {
    companion object {
        private fun String.utf8(): String = URLEncoder.encode(this, "UTF-8")

        private fun formData(data: Map<String, String>): HttpRequest.BodyPublisher? {

            val res = data.map { (k, v) -> "${(k.utf8())}=${v.utf8()}" }
                .joinToString("&")

            return HttpRequest.BodyPublishers.ofString(res)
        }

        @JvmStatic
        fun send(summary: String, description: String) {
            val params = mapOf(
                "fields" to "id,name,shortName",
                "query" to "sp"
            )
            val urlParams = formData(params)

            val objectMapper = ObjectMapper()

            val values = mapOf(
                "project" to mapOf("id" to "0-0"),
                "summary" to summary,
                "description" to description
            )

            val requestBody: String = objectMapper.writeValueAsString(values)

            val client = HttpClient.newBuilder().build()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("https://example.myjetbrains.com/youtrack/api/issues${if (params.isNotEmpty()) "?" else ""}${urlParams}"))
                .header("Accept", "application/json")
                .header("Authorization", "Bearer perm:amFuZS5kb2U=.UkVTVCBBUEk=.wcKuAok8cHmAtzjA6xlc4BrB4hleaX") //sample authorization token for example.myjetbrains.com is used
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            println(response.body())
        }
    }
}