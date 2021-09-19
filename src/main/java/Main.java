public class Main {
    public static void main(String[] args) {
        InspectionResult inspectionResult = new InspectionResult(SampleInspectionResultProvider.getInspectionResult());
        HttpSender.send(inspectionResult.buildSummary(), inspectionResult.buildDescription());
    }
}
