import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser(GlobalVariable.TS_Domain + '/compare/report/80112722021/80112712021,80112702021,80112692021')

WebUI.scrollToElement(findTestObject('MSRP/section objects/compare/report pages/pricing header'), 0, FailureHandling.STOP_ON_FAILURE)

textWithMSRP = WebUI.getText(findTestObject('MSRP/section objects/compare/report pages/wrapper'))

'ES 300h Luxury'
expectedMSRP = findTestData(GlobalVariable.DS_version + 'MSRPs').getValue(4, 14)

textWithoutExpectedMSRP = (textWithMSRP - expectedMSRP)

WebUI.verifyNotMatch(textWithoutExpectedMSRP, textWithMSRP, false, FailureHandling.STOP_ON_FAILURE)

'ES 300h Ultra Luxury'
expectedMSRP = findTestData(GlobalVariable.DS_version + 'MSRPs').getValue(4, 15)

textWithoutExpectedMSRP = (textWithMSRP - expectedMSRP)

WebUI.verifyNotMatch(textWithoutExpectedMSRP, textWithMSRP, false, FailureHandling.STOP_ON_FAILURE)

'ES 350 Luxury'
expectedMSRP = findTestData(GlobalVariable.DS_version + 'MSRPs').getValue(4, 11)

textWithoutExpectedMSRP = (textWithMSRP - expectedMSRP)

WebUI.verifyNotMatch(textWithoutExpectedMSRP, textWithMSRP, false, FailureHandling.STOP_ON_FAILURE)

'ES 350 Ultra Luxury'
expectedMSRP = findTestData(GlobalVariable.DS_version + 'MSRPs').getValue(4, 12)

textWithoutExpectedMSRP = (textWithMSRP - expectedMSRP)

WebUI.verifyNotMatch(textWithoutExpectedMSRP, textWithMSRP, false, FailureHandling.STOP_ON_FAILURE)

@com.kms.katalon.core.annotation.TearDownIfPassed
def passed() {
	WebUI.executeJavaScript('sauce:job-result=passed', [])
}

@com.kms.katalon.core.annotation.TearDownIfFailed
def failed() {
	WebUI.executeJavaScript('sauce:job-result=failed', [])
}

