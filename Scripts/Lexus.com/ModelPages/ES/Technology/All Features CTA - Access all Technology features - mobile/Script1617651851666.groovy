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
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import org.openqa.selenium.Cookie as Cookie
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

def modelSeries = GlobalVariable.currentTestCaseID //get current testcase name

String[] parts = modelSeries.split('/' //split it to using delimeter /
    )

String three = parts[(parts.length - 3)]

modelSeries = three

int seriesKey = findTestData('modelData' + modelSeries).getValue(1, 2).toInteger()

WebUI.openBrowser(GlobalVariable.SSO_login, FailureHandling.OPTIONAL)

'these steps are added to handle lower environment authentication'
if (WebUI.verifyMatch(GlobalVariable.lowerEnvironment, 'yes', false, FailureHandling.OPTIONAL)) {
    cookieValue = findTestData('cookieValues').getValue(2, 1)

    Cookie ck = new Cookie('ESTSAUTH', cookieValue)

    WebDriver driver = DriverFactory.getWebDriver()

    driver.manage().addCookie(ck)

    WebUI.navigateToUrl(GlobalVariable.TS_Domain + GlobalVariable.legacyURL)

    WebUI.waitForPageLoad(0)
}

WebUI.navigateToUrl(GlobalVariable.AEM_Domain + findTestData('modelData').getValue(GlobalVariable.dataColumn, seriesKey))

'these steps are added to handle author environment URLs'
if (WebUI.verifyMatch(GlobalVariable.author, 'yes', false, FailureHandling.OPTIONAL)) {
    WebUI.navigateToUrl((GlobalVariable.AEM_Domain + findTestData('modelData').getValue(GlobalVariable.dataColumn, seriesKey)) + 
        GlobalVariable.authorQuery)
}

'this step is added to handle a slow or partial page load'
if (WebUI.verifyElementNotPresent(findTestObject('ModelPages/Hero/hero module'), 3, FailureHandling.OPTIONAL)) {
    WebUI.refresh()
}

WebUI.waitForElementPresent(findTestObject('ModelPages/Hero/hero module'), 3, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('ModelPages/Subnav/mobile nav button'), FailureHandling.OPTIONAL)

WebUI.delay(2)

WebUI.click(findTestObject('ModelPages/Subnav/technology link'), FailureHandling.OPTIONAL)

WebUI.delay(3)

'continues testing if there is a More Features drawer'
if (WebUI.verifyElementPresent(findTestObject('ModelPages/Technology/drawer/more features link'), 3, FailureHandling.OPTIONAL)) {
    WebUI.scrollToElement(findTestObject('ModelPages/Technology/copy heading'), 0)

    WebUI.delay(1, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('ModelPages/Technology/drawer/more features link'))

    WebUI.delay(1, FailureHandling.STOP_ON_FAILURE)

    WebUI.scrollToElement(findTestObject('ModelPages/Technology/drawer/feature 1 heading'), 0)

    WebUI.delay(1, FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('ModelPages/Technology/drawer/all features CTA'), FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(1, FailureHandling.STOP_ON_FAILURE)

    actualValue = WebUI.getText(findTestObject('ModelPages/InformationLayer/selected tab'), FailureHandling.STOP_ON_FAILURE)

    'Clicking on All Design CTA leads to IL features tab'
    WebUI.verifyMatch(actualValue, 'FEATURES', false, FailureHandling.STOP_ON_FAILURE)

    actualValue = WebUI.getText(findTestObject('ModelPages/InformationLayer/expanded accordion button'), FailureHandling.STOP_ON_FAILURE)

    expectedValue = 'TECHNOLOGY'

    valueWithoutExpected = (actualValue - expectedValue)

    'Clicking on All Design CTA leads to open Technology accordion.'
    WebUI.verifyNotMatch(valueWithoutExpected, actualValue, false, FailureHandling.STOP_ON_FAILURE)
}

@com.kms.katalon.core.annotation.TearDownIfPassed
def passed() {
    WebUI.executeJavaScript('sauce:job-result=passed', [])
}

@com.kms.katalon.core.annotation.TearDownIfFailed
def failed() {
    WebUI.executeJavaScript('sauce:job-result=failed', [])
}

