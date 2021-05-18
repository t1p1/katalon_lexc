import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser(GlobalVariable.TS_Domain + GlobalVariable.Header)

WebUI.navigateToUrl(GlobalVariable.TS_Domain + GlobalVariable.Overview_NX)

WebUI.waitForPageLoad(0)

WebUI.scrollToElement(findTestObject('ModelPages/Gallery/gallery module'), 0, FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('null'), 0, FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('null'), 0, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('null'), 0)

WebUI.verifyElementNotPresent(findTestObject('null'), 0, FailureHandling.STOP_ON_FAILURE)

pageWidth = WebUI.getPageWidth()

if (WebUI.verifyGreaterThan(pageWidth, '960', FailureHandling.OPTIONAL)) {
    WebUI.verifyElementVisible(findTestObject('null'))

    WebUI.verifyElementVisible(findTestObject('null'))
}

WebUI.verifyElementPresent(findTestObject('null'), 0, FailureHandling.STOP_ON_FAILURE)

'if there is no image visible, then video should be present'
if (WebUI.verifyElementNotVisible(findTestObject('null'), FailureHandling.OPTIONAL)) {
    WebUI.verifyElementPresent(findTestObject('null'), 0)
}

WebUI.verifyElementVisible(findTestObject('null'), FailureHandling.STOP_ON_FAILURE)

@com.kms.katalon.core.annotation.TearDownIfPassed
def passed() {
    WebUI.executeJavaScript('sauce:job-result=passed', [])
}

@com.kms.katalon.core.annotation.TearDownIfFailed
def failed() {
    WebUI.executeJavaScript('sauce:job-result=failed', [])
}
