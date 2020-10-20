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

WebUI.navigateToUrl(GlobalVariable.TS_Domain + GlobalVariable.Overview_GX)

WebUI.waitForPageLoad(0)

pageWidth = WebUI.getPageWidth()

if (WebUI.verifyGreaterThan(pageWidth, '960', FailureHandling.OPTIONAL)) {
    WebUI.scrollToElement(findTestObject('ModelPages/Styles/styles module'), 0)

    WebUI.waitForElementPresent(findTestObject('ModelPages/Styles/Compare CTA - desktop'), 0)

    WebUI.click(findTestObject('ModelPages/Styles/Compare CTA - desktop'), FailureHandling.STOP_ON_FAILURE)

    WebUI.waitForElementPresent(findTestObject('ModelPages/InformationLayer/column 1'), 0)

    column1width = WebUI.getElementWidth(findTestObject('ModelPages/InformationLayer/column 1'))

    not_run: column3width = WebUI.getElementWidth(findTestObject('ModelPages/InformationLayer/column 3'))

    not_run: WebUI.verifyEqual(column1width, column3width)

    WebUI.verifyElementPresent(findTestObject('ModelPages/InformationLayer/trim heading - GX 460'), 0)

    WebUI.waitForElementPresent(findTestObject('ModelPages/InformationLayer/remove CTA - first column'), 0)

    WebUI.click(findTestObject('ModelPages/InformationLayer/remove CTA - first column'), FailureHandling.STOP_ON_FAILURE)

    WebUI.waitForElementNotPresent(findTestObject('ModelPages/InformationLayer/trim heading - GX 460'), 0, FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyElementNotPresent(findTestObject('ModelPages/InformationLayer/trim heading - GX 460'), 0)

    WebUI.verifyElementPresent(findTestObject('ModelPages/InformationLayer/add feature option'), 0)

    not_run: column1width = WebUI.getElementWidth(findTestObject('ModelPages/InformationLayer/column 1'))

    not_run: column2widthPlus = (WebUI.getElementWidth(findTestObject('ModelPages/InformationLayer/column 2')) + 1)

    not_run: column2widthMinus = (WebUI.getElementWidth(findTestObject('ModelPages/InformationLayer/column 2')) - 1)

    not_run: WebUI.verifyLessThanOrEqual(column1width, column2widthPlus)

    not_run: WebUI.verifyGreaterThanOrEqual(column1width, column2widthMinus)

    not_run: column3widthPlus = (WebUI.getElementWidth(findTestObject('ModelPages/InformationLayer/column 3')) + 1)

    not_run: column3widthMinus = (WebUI.getElementWidth(findTestObject('ModelPages/InformationLayer/column 3')) - 1)

    not_run: WebUI.verifyLessThanOrEqual(column1width, column3widthPlus)

    not_run: WebUI.verifyGreaterThanOrEqual(column1width, column3widthMinus)

    not_run: WebUI.click(findTestObject('ModelPages/InformationLayer/remove CTA - second column'), FailureHandling.STOP_ON_FAILURE)

    not_run: column1width = WebUI.getElementWidth(findTestObject('ModelPages/InformationLayer/column 1'))

    not_run: column2widthPlus = (WebUI.getElementWidth(findTestObject('ModelPages/InformationLayer/column 2')) + 1)

    not_run: column2widthMinus = (WebUI.getElementWidth(findTestObject('ModelPages/InformationLayer/column 2')) - 1)

    not_run: WebUI.verifyLessThanOrEqual(column1width, column2widthPlus)

    not_run: WebUI.verifyGreaterThanOrEqual(column1width, column2widthMinus)

    not_run: column3widthPlus = (WebUI.getElementWidth(findTestObject('ModelPages/InformationLayer/column 3')) + 1)

    not_run: column3widthMinus = (WebUI.getElementWidth(findTestObject('ModelPages/InformationLayer/column 3')) - 1)

    not_run: WebUI.verifyLessThanOrEqual(column1width, column3widthPlus)

    not_run: WebUI.verifyGreaterThanOrEqual(column1width, column3widthMinus)

    not_run: WebUI.verifyElementPresent(findTestObject('ModelPages/InformationLayer/add feature option'), 0)
}

@com.kms.katalon.core.annotation.TearDownIfPassed
def passed() {
    WebUI.executeJavaScript('sauce:job-result=passed', [])
}

@com.kms.katalon.core.annotation.TearDownIfFailed
def failed() {
    WebUI.executeJavaScript('sauce:job-result=failed', [])
}
