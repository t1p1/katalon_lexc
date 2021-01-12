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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import org.openqa.selenium.Cookie as Cookie
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

WebUI.openBrowser(GlobalVariable.SSO_login, FailureHandling.OPTIONAL)

cookieValue = findTestData('cookieValues').getValue(2, 1)

Cookie ck = new Cookie('ESTSAUTH', cookieValue)

WebDriver driver = DriverFactory.getWebDriver()

driver.manage().addCookie(ck)

domain = GlobalVariable.domain

'this step is added to handle legacy staging authentication'
if (WebUI.verifyMatch(domain, 'staging', false, FailureHandling.OPTIONAL)) {
    WebUI.navigateToUrl(GlobalVariable.TS_Domain + GlobalVariable.legacyURL)
}

WebUI.navigateToUrl(GlobalVariable.SC_Domain + '/offers')

'This is a workaround in case LAM-2532 occurs.'
if (WebUI.verifyElementPresent(findTestObject('OffersPage/ZipGate/expand CTA'), 3, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('OffersPage/ZipBar/change market CTA'), FailureHandling.STOP_ON_FAILURE)

    WebUI.setText(findTestObject('OffersPage/ZipBar/zip entry field'), '08008')

    WebUI.click(findTestObject('OffersPage/ZipBar/search icon'), FailureHandling.STOP_ON_FAILURE)

    WebUI.waitForElementPresent(findTestObject('OffersPage/SharedMarketOverlay/shared zip overlay'), 5, FailureHandling.OPTIONAL)

    WebUI.verifyElementPresent(findTestObject('OffersPage/SharedMarketOverlay/shared zip overlay'), 0, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('OffersPage/SharedMarketOverlay/X button'), FailureHandling.STOP_ON_FAILURE)
}

WebUI.waitForElementPresent(findTestObject('OffersPage/ZipGate/form input'), 5, FailureHandling.OPTIONAL)

WebUI.setText(findTestObject('OffersPage/ZipGate/form input'), '95730')

WebUI.click(findTestObject('OffersPage/ZipGate/submit button'))

WebUI.waitForElementPresent(findTestObject('OffersPage/ZipGate/no dealer message'), 5, FailureHandling.OPTIONAL)

WebUI.verifyElementPresent(findTestObject('OffersPage/ZipGate/no dealer message'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('OffersPage/ZipGate/expand CTA'), 0, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('OffersPage/ZipGate/expand CTA'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('OffersPage/ExpandSearch/expand zip overlay'), 5, FailureHandling.OPTIONAL)

WebUI.verifyElementPresent(findTestObject('OffersPage/ExpandSearch/expand zip overlay'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('OffersPage/ExpandSearch/displayed dealer 1'), 5, FailureHandling.STOP_ON_FAILURE)

noHover = WebUI.getCSSValue(findTestObject('OffersPage/ExpandSearch/displayed dealer 1'), 'background-color', FailureHandling.STOP_ON_FAILURE)

WebUI.mouseOver(findTestObject('OffersPage/ExpandSearch/displayed dealer 1'), FailureHandling.OPTIONAL)

hoverState = WebUI.getCSSValue(findTestObject('OffersPage/ExpandSearch/displayed dealer 1'), 'background-color', FailureHandling.STOP_ON_FAILURE)

WebUI.verifyNotMatch(hoverState, noHover, false, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('OffersPage/ExpandSearch/displayed dealer 2'), 5, FailureHandling.STOP_ON_FAILURE)

noHover = WebUI.getCSSValue(findTestObject('OffersPage/ExpandSearch/displayed dealer 2'), 'background-color', FailureHandling.STOP_ON_FAILURE)

WebUI.mouseOver(findTestObject('OffersPage/ExpandSearch/displayed dealer 2'), FailureHandling.OPTIONAL)

hoverState = WebUI.getCSSValue(findTestObject('OffersPage/ExpandSearch/displayed dealer 2'), 'background-color', FailureHandling.STOP_ON_FAILURE)

WebUI.verifyNotMatch(hoverState, noHover, false, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('OffersPage/ExpandSearch/displayed dealer 3'), 5, FailureHandling.STOP_ON_FAILURE)

noHover = WebUI.getCSSValue(findTestObject('OffersPage/ExpandSearch/displayed dealer 3'), 'background-color', FailureHandling.STOP_ON_FAILURE)

WebUI.mouseOver(findTestObject('OffersPage/ExpandSearch/displayed dealer 3'), FailureHandling.OPTIONAL)

hoverState = WebUI.getCSSValue(findTestObject('OffersPage/ExpandSearch/displayed dealer 3'), 'background-color', FailureHandling.STOP_ON_FAILURE)

WebUI.verifyNotMatch(hoverState, noHover, false, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2, FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('OffersPage/ExpandSearch/displayed dealer 6'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('OffersPage/ExpandSearch/displayed dealer 6'), 5, FailureHandling.STOP_ON_FAILURE)

noHover = WebUI.getCSSValue(findTestObject('OffersPage/ExpandSearch/displayed dealer 6'), 'background-color', FailureHandling.STOP_ON_FAILURE)

WebUI.mouseOver(findTestObject('OffersPage/ExpandSearch/displayed dealer 6'), FailureHandling.STOP_ON_FAILURE)

hoverState = WebUI.getCSSValue(findTestObject('OffersPage/ExpandSearch/displayed dealer 6'), 'background-color', FailureHandling.STOP_ON_FAILURE)

WebUI.verifyNotMatch(hoverState, noHover, false, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2, FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('OffersPage/ExpandSearch/displayed dealer 9'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.mouseOver(findTestObject('OffersPage/ExpandSearch/displayed dealer 9'), FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2, FailureHandling.STOP_ON_FAILURE)

@com.kms.katalon.core.annotation.TearDownIfPassed
def passed() {
    WebUI.executeJavaScript('sauce:job-result=passed', [])
}

@com.kms.katalon.core.annotation.TearDownIfFailed
def failed() {
    WebUI.executeJavaScript('sauce:job-result=failed', [])
}

