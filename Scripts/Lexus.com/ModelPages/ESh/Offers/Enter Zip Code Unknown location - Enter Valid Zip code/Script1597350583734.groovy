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

WebUI.openBrowser(GlobalVariable.SC_Domain + GlobalVariable.Offers)

WebUI.navigateToUrl(GlobalVariable.TS_Domain + GlobalVariable.Overview_ESh)

WebUI.navigateToUrl(GlobalVariable.TS_Domain_Unauthenticated + GlobalVariable.Overview_ESh)

WebUI.waitForPageLoad(0)

WebUI.scrollToElement(findTestObject('ModelPages/Offers/offers module'), 0)

WebUI.setText(findTestObject('ModelPages/Offers/zip entry field'), '58102')

WebUI.click(findTestObject('ModelPages/Offers/search button'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('ModelPages/Offers/no offers message'), 0)

WebUI.verifyElementVisible(findTestObject('ModelPages/Offers/no offers message'), FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('ModelPages/Offers/offers module'), 0)

WebUI.setText(findTestObject('ModelPages/Offers/zip entry field'), '75218')

WebUI.click(findTestObject('ModelPages/Offers/search button'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('ModelPages/Offers/offer tile'), 0)

WebUI.scrollToElement(findTestObject('ModelPages/Offers/offer tile'), 0)

WebUI.verifyElementVisible(findTestObject('ModelPages/Offers/offer tile'), FailureHandling.STOP_ON_FAILURE)

@com.kms.katalon.core.annotation.TearDownIfPassed
def passed() {
    WebUI.executeJavaScript('sauce:job-result=passed', [])
}

@com.kms.katalon.core.annotation.TearDownIfFailed
def failed() {
    WebUI.executeJavaScript('sauce:job-result=failed', [])
}
