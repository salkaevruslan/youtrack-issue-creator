import com.fasterxml.jackson.databind.ObjectMapper

class InspectionResult(result: String) {
    private val dataMap: HashMap<*, *>

    init {
        val objectMapper = ObjectMapper()
        dataMap = objectMapper.readValue(result, HashMap::class.java)
    }

    fun buildSummary(): String {
        val ruleId = getRuleID()
        val level = getLevel()
        val position = getPosition()
        return "$level: $ruleId at $position"
    }

    fun buildDescription(): String {
        val ruleId = getRuleID()
        val level = getLevel()
        val position = getPosition()
        val lineText = getLineText()
        val problemInfo = getProblemInfo()
        return "Problem found:\n\n$level: $ruleId\n\nat: $position\n\ncode: $lineText\n\n problem info: $problemInfo"
    }

    private fun getLevel(): String {
        return dataMap["level"] as String
    }

    private fun getRuleID(): String {
        return dataMap["ruleId"] as String
    }

    private fun getPosition(): String {
        val locations = dataMap["locations"] as ArrayList<*>
        val physicalLocation = (locations[0] as Map<*, *>)["physicalLocation"] as Map<*, *>
        val region = physicalLocation["region"] as Map <*, *>
        val artifactLocation = physicalLocation["artifactLocation"] as Map <*, *>
        val uri = artifactLocation["uri"] as String
        val line = region["startLine"] as Int
        val column = region["startColumn"] as Int
        return "$uri at $line:$column"
    }

    private fun getLineText(): String {
        val locations = dataMap["locations"] as ArrayList<*>
        val physicalLocation = (locations[0] as Map<*, *>)["physicalLocation"] as Map<*, *>
        val contextRegion = physicalLocation["contextRegion"] as Map <*, *>
        val snippet = contextRegion["snippet"] as Map <*, *>
        return snippet["text"] as String
    }

    private fun getProblemInfo(): String {
        val message = dataMap["message"] as Map<*, *>
        return message["text"] as String
    }
}