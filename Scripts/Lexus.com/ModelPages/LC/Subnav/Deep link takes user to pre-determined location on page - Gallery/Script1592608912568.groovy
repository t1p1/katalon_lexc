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

WebUI.openBrowser((GlobalVariable.TS_Domain + GlobalVariable.Overview_NX) + '/gallery')

WebUI.waitForPageLoad(0)

WebUI.verifyElementVisibleInViewport(findTestObject('ModelPages/Gallery/gallery module'), 0)

WebUI.verifyElementVisible(findTestObject('ModelPages/Gallery/gallery heading'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('ModelPages/Gallery/primary image'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('ModelPages/Gallery/secondary image 1'), 0)

WebUI.verifyElementPresent(findTestObject('ModelPages/Gallery/secondary image 2'), 0)

WebUI.scrollToElement(findTestObject('ModelPages/Gallery/pagination 1'), 0)

WebUI.verifyElementVisibleInViewport(findTestObject('ModelPages/Gallery/pagination 1'), 0)

WebUI.verifyElementVisibleInViewport(findTestObject('ModelPages/Gallery/pagination OF'), 0)

not_run: WebUI.verifyElementPresent(findTestObject('ModelPages/Gallery/image description 1'), 0, FailureHandling.OPTIONAL)

pageWidth = WebUI.getPageWidth()

if (WebUI.verifyGreaterThan(pageWidth, '960', FailureHandling.OPTIONAL)) {
    WebUI.verifyElementPresent(findTestObject('ModelPages/Gallery/carousel tab - left'), 0)

    WebUI.verifyElementPresent(findTestObject('ModelPages/Gallery/carousel tab - right'), 0)
}

WebUI.verifyElementPresent(findTestObject('ModelPages/Gallery/expand image button'), 0)

@com.kms.katalon.core.annotation.TearDownIfPassed
def passed() {
    WebUI.executeJavaScript('sauce:job-result=passed', [])
}

@com.kms.katalon.core.annotation.TearDownIfFailed
def failed() {
    WebUI.executeJavaScript('sauce:job-result=failed', [])
}

