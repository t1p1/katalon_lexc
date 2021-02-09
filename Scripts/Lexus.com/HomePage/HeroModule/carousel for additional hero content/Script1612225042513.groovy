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
}

not_run: WebUI.waitForElementPresent(findTestObject('Homepage/HeroModule/slide 1'), 5, FailureHandling.OPTIONAL)

'runs these tests if more than one slide is present'
if (WebUI.verifyElementPresent(findTestObject('Homepage/HeroModule/slide 2'), 3, FailureHandling.OPTIONAL)) {
    'the active slide should cycle once through the available slides'
    activeSlide1 = WebUI.getAttribute(findTestObject('Homepage/HeroModule/active slide'), 'data-index')

    WebUI.delay(4)

    'slides should change every 5 seconds or so'
    activeSlide2 = WebUI.getAttribute(findTestObject('Homepage/HeroModule/active slide'), 'data-index')

    'waits a bit longer if the slide has not changed yet'
    if (WebUI.verifyMatch(activeSlide1, activeSlide2, false, FailureHandling.OPTIONAL)) {
        WebUI.delay(4)

        activeSlide2 = WebUI.getAttribute(findTestObject('Homepage/HeroModule/active slide'), 'data-index')

        'reloads page to try cached carousel load, since slide animation is timing-specific'
        if (WebUI.verifyMatch(activeSlide1, activeSlide2, false, FailureHandling.OPTIONAL)) {
            WebUI.refresh(FailureHandling.STOP_ON_FAILURE)

            activeSlide1 = WebUI.getAttribute(findTestObject('Homepage/HeroModule/active slide'), 'data-index')

            WebUI.delay(4)

            activeSlide2 = WebUI.getAttribute(findTestObject('Homepage/HeroModule/active slide'), 'data-index')

            'waits a bit longer if the slide has not changed yet'
            if (WebUI.verifyMatch(activeSlide1, activeSlide2, false, FailureHandling.OPTIONAL)) {
                WebUI.delay(4)

                activeSlide2 = WebUI.getAttribute(findTestObject('Homepage/HeroModule/active slide'), 'data-index')

                WebUI.verifyNotMatch(activeSlide1, activeSlide2, false, FailureHandling.STOP_ON_FAILURE)
            }
        }
    }
    
    WebUI.verifyElementPresent(findTestObject('Homepage/HeroModule/carousel slide 1 button'), 0)

    WebUI.verifyElementPresent(findTestObject('Homepage/HeroModule/carousel slide 2 button'), 0)

    WebUI.verifyElementPresent(findTestObject('Homepage/HeroModule/carousel next slide button'), 0)

    WebUI.verifyElementPresent(findTestObject('Homepage/HeroModule/carousel previous slide button'), 0)

    WebUI.delay(9)

    stoppedAtSlide = WebUI.getAttribute(findTestObject('Homepage/HeroModule/active slide'), 'data-index')

    'the final slide after animation is complete should always be the first slide'
    WebUI.verifyEqual(0, stoppedAtSlide, FailureHandling.STOP_ON_FAILURE)
}

WebUI.verifyElementPresent(findTestObject('Homepage/HeroModule/hero module'), 5, FailureHandling.STOP_ON_FAILURE)

@com.kms.katalon.core.annotation.TearDownIfPassed
def passed() {
    WebUI.executeJavaScript('sauce:job-result=passed', [])
}

@com.kms.katalon.core.annotation.TearDownIfFailed
def failed() {
    WebUI.executeJavaScript('sauce:job-result=failed', [])
}
