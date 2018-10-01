//FROM RANDOM PAGES

// Generate Date 
${=String.format(‘%tF’, new Date() + 1)}

// Generate Random GUID 1  
Guid_${(int)(Math.random()*100000000)}

// Generate Random GUID 2
def guidVal = "${java.util.UUID.randomUUID()}"

// Get current date and time in milliseconds
now = Calendar.instance
log.info 'now is a ' + now.class.name
date = now.time
log.info 'date is a ' + date.class.name + ' with value ' + date
millis = date.time
log.info 'millis is a ' + millis.class.name + ' with value ' + millis

//Referencing Properties from within Test Requests
//TestCase level
${#TestCase#MyProp}
//TestSuite level
${#TestSuite#MyProp}
//Project level
${#Project#MyProp}

//Getting Properties - From a Groovy Script test step
def testCaseProperty = testRunner.testCase.getPropertyValue("MyProp")
def testSuiteProperty = testRunner.testCase.testSuite.getPropertyValue("MyProp")
def projectProperty = testRunner.testCase.testSuite.project.getPropertyValue("MyProp")
def globalProperty = com.eviware.soapui.SoapUI.globalProperties.getPropertyValue("MyProp")

//Getting Properties - From a Script Assertion on a test request
def testCaseProperty = messageExchange.modelItem.testStep.testCase.getPropertyValue("MyProp")

//Setting Properties -From a Groovy Script test step
testRunner.testCase.setPropertyValue("MyProp", someValue)
testRunner.testCase.testSuite.setPropertyValue("MyProp", someValue)
testRunner.testCase.testSuite.project.setPropertyValue("MyProp", someValue)
com.eviware.soapui.SoapUI.globalProperties.setPropertyValue("MyProp", someValue)

//Setting Properties-From a Script Assertion on a test request
messageExchange.modelItem.testStep.testCase.setPropertyValue("MyProp", someValue)

//Setting Properties -Set Test Suite property to a Json object from Script Assertion
//add a test suite property for a JSON object from a response of a given test case
//builder is the var of your JSON builder which is most likely 'jsonizing' a map
messageExchange.modelItem.testStep.testCase.testSuite.setPropertyValue("SomeObject", JsonOutput.prettyPrint(builder.toString()))

//Assign Test Case properties to a map from Groovy Script test step
def tcProps = testRunner.testCase.getProperties()
def map = [:]
 
//grab all the properties from the test case and put them in a map as long as they aren't empty
for(p in tcProps){      
            if (p.value.getValue() != "") { 
            map.put(p.key, p.value.getValue())              
    }   
} 