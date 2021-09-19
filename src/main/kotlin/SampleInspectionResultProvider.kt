class SampleInspectionResultProvider {
    companion object {
        @JvmStatic
        fun getInspectionResult(): String {
            return """{
            "ruleId": "InfiniteRecursion",
            "kind": "fail",
            "level": "warning",
            "message": {
                "text": "Method <code>visitTypeVariable()</code> recurses infinitely, and can only end by throwing an exception"
             },
            "locations": [
            {
                "physicalLocation": {
                "artifactLocation": {
                "uri": "asm-util/src/main/java/org/objectweb/asm/util/CheckSignatureAdapter.java",
                "uriBaseId": "SRCROOT"
            },
                "region": {
                "startLine": 259,
                "startColumn": 14,
                "charLength": 17,
                "snippet": {
                "text": "visitTypeVariable"
            },
                "sourceLanguage": "JAVA"
            },
                "contextRegion": {
                "startLine": 257,
                "startColumn": 1,
                "charOffset": 9764,
                "charLength": 152,
                "snippet": {
                "text": "\n  @Override\n  public void visitTypeVariable(final String name) {\n    visitTypeVariable(name);\n    if (type != TYPE_SIGNATURE || state != State.EMPTY) {"
            }
            }
            },
                "logicalLocations": [
                {
                    "fullyQualifiedName": "root.asm-util.main",
                    "kind": "module"
                }
                ]
            }
            ],
            "partialFingerprints": {
            "equalIndicator/v1": "ac5714b0b15b7e8c4311899afd1c2b44069865039f2a9d309dcab04eddd4681d"
        },
            "baselineState": "unchanged",
            "properties": {
            "ideaSeverity": "WARNING",
            "tags": [
            "ideaSeverity"
            ]
        }
        }"""
        }
    }
}