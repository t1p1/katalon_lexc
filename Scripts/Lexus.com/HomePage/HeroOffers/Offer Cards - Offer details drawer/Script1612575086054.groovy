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

'these steps are added to handle lower environment authentication'
if (WebUI.verifyMatch(GlobalVariable.lowerEnvironment, 'yes', false, FailureHandling.OPTIONAL)) {
    cookieValue = findTestData('cookieValues').getValue(2, 1)

    Cookie ck = new Cookie('ESTSAUTH', cookieValue)

    WebDriver driver = DriverFactory.getWebDriver()

    driver.manage().addCookie(ck)

    WebUI.navigateToUrl(GlobalVariable.TS_Domain + GlobalVariable.legacyURL)
}

WebUI.navigateToUrl(GlobalVariable.AEM_Domain)

'if the page renders slowly, it will be refreshed so the test can continue'
if (WebUI.verifyElementNotPresent(findTestObject('GlobalNav/header/header - Lexus logo'), 3, FailureHandling.OPTIONAL)) {
    WebUI.refresh()

    WebUI.delay(2)
}

WebUI.verifyElementVisibleInViewport(findTestObject('Homepage/HeroModule/hero module'), 0, FailureHandling.STOP_ON_FAILURE)

'runs these tests on sales event version of page'
if (WebUI.verifyElementPresent(findTestObject('Homepage/HeroOffers/zip code field'), 5, FailureHandling.OPTIONAL)) {
    WebUI.verifyElementVisibleInViewport(findTestObject('Homepage/HeroOffers/zip code field'), 0, FailureHandling.STOP_ON_FAILURE)

    WebUI.scrollToPosition(0, 350)

    WebUI.click(findTestObject('Homepage/HeroOffers/zip code field'))

    WebUI.setText(findTestObject('Homepage/HeroOffers/zip code field'), '75218')

    WebUI.click(findTestObject('Homepage/HeroOffers/submit button'))

    WebUI.waitForElementPresent(findTestObject('Homepage/HeroOffers/offer cards row'), 5, FailureHandling.OPTIONAL)

    WebUI.verifyElementPresent(findTestObject('Homepage/HeroOffers/OfferRow/offer card - details button'), 0, FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyElementNotPresent(findTestObject('Homepage/HeroOffers/OfferRow/offer card 1 - details'), 0, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Homepage/HeroOffers/OfferRow/offer card - details button'), FailureHandling.STOP_ON_FAILURE)

    offerType = WebUI.getText(findTestObject('Homepage/HeroOffers/OfferRow/offer card 1 - offer type'), FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(2, FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyElementVisibleInViewport(findTestObject('Homepage/HeroOffers/OfferRow/offer card 1 - details'), 0, FailureHandling.STOP_ON_FAILURE)

    offerCardText = WebUI.getText(findTestObject('Homepage/HeroOffers/OfferRow/offer card 1 - details'), FailureHandling.STOP_ON_FAILURE)

    'offer type in offer details should match offer card'
    textWithoutOffer = (offerCardText - offerType)

    WebUI.verifyNotMatch(offerCardText, textWithoutOffer, false, FailureHandling.STOP_ON_FAILURE)

    legalCopy = WebUI.getText(findTestObject('Homepage/HeroOffers/OfferRow/offer card 1 - legal'), FailureHandling.STOP_ON_FAILURE)

    'legal copy should display copy, including year'
    modifiedString = (legalCopy - '20')

    WebUI.verifyNotMatch(modifiedString, legalCopy, false, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Homepage/HeroOffers/OfferRow/offer card - details button'), FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(2, FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyElementNotPresent(findTestObject('Homepage/HeroOffers/OfferRow/offer card 1 - details'), 0, FailureHandling.STOP_ON_FAILURE)
}

'runs these tests on non-sales event version of page'
if (WebUI.verifyElementNotPresent(findTestObject('Homepage/HeroOffers/zip code field'), 5, FailureHandling.OPTIONAL)) {
    WebUI.verifyElementPresent(findTestObject('Homepage/HeroModule/hero module'), 0, FailureHandling.STOP_ON_FAILURE)
}

@com.kms.katalon.core.annotation.TearDownIfPassed
def passed() {
    WebUI.executeJavaScript('sauce:job-result=passed', [])
}

@com.kms.katalon.core.annotation.TearDownIfFailed
def failed() {
    WebUI.executeJavaScript('sauce:job-result=failed', [])
}

