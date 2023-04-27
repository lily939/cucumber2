package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BaseLocator extends By {

    private WebDriver driver = DriverManager.getDriver();
    private By locator;
    private WebDriverWait wait = new WebDriverWait(driver, 10);

    public BaseLocator(By locator) {
        this.locator = locator;
    }

    public BaseLocator waitUntilVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this;
    }

    public BaseLocator waitUntilVisible(int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this;
    }

    public BaseLocator waitUntilElementTextContains(String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        return this;
    }

    public BaseLocator waitUntilElementTextContains(int timeout, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        return this;
    }

    public BaseLocator waitUntilElementAttributeContainsValue(String attribute, String value) {
        wait.until(
                CustomWait.waitUntilElementAttributeValueContains(driver, locator, attribute, value));
        return this;
    }

    public BaseLocator waitUntilElementAttributeContainsValue(int timeout, String attribute,
                                                              String value) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(
                CustomWait.waitUntilElementAttributeValueContains(driver, locator, attribute, value));
        return this;
    }

    public BaseLocator waitUntilElementAttributeValue(String attribute, String value) {
        wait.until(CustomWait.waitUntilElementAttributeValue(driver, locator, attribute, value));
        return this;
    }

    public BaseLocator waitUntilElementAttributeValue(int timeout, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(CustomWait.waitUntilElementAttributeValue(driver, locator, attribute, value));
        return this;
    }

    public BaseLocator waitUntilClickable() {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return this;
    }

    public BaseLocator waitUntilClickable(int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return this;
    }

    public BaseLocator waitUntilInvisible() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return this;
    }

    public BaseLocator waitUntilInvisible(int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return this;
    }

    public BaseLocator waitUntilPresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this;
    }

    public BaseLocator waitUntilPresent(int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this;
    }

    public BaseLocator waitUntilElementText(String text) {
        wait.until(ExpectedConditions.textToBe(locator, text));
        return this;
    }

    public BaseLocator waitUntilElementText(int timeout, String text) {
        wait.until(ExpectedConditions.textToBe(locator, text));
        return this;
    }

    public BaseLocator delay(int timeout) throws InterruptedException {
        Thread.sleep(timeout * 1000);
        return this;
    }

    public BaseLocator moveToElement() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(locator)).perform();
        return this;
    }


    public BaseLocator moveToElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        return this;
    }


    public BaseLocator sendKeys(CharSequence value) {
        driver.findElement(locator).sendKeys(value);
        return this;
    }

    public BaseLocator clear() {
        driver.findElement(locator).clear();
        return this;
    }


    public BaseLocator click() {
        // Try to find element and click on it up to 3 times to prevent case page
        // refresh element
        for (int i = 0; i < 3; i++) {
            try {
                driver.findElement(locator).click();
                break;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                if (i < 2) {
                    continue;
                } else {
                    throw e;
                }
            }
        }
        return this;
    }

    public static BaseLocator click(BaseLocator locator) {
        return locator.waitUntilClickable(10).click();
    }

    public BaseLocator clickByJs() {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        return this;
    }

    public BaseLocator tab() {
        WebElement element = driver.findElement(locator);
        element.sendKeys(Keys.TAB);
        return this;
    }

    public BaseLocator checkEnable() {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.isEnabled(), "OK button is enabled.");
        return this;
    }

    public boolean verifyElementAbsent() throws Exception {
        try {
            WebElement element = driver.findElement(locator);
            System.out.println("Element Present");
            return false;

        } catch (NoSuchElementException e) {
            System.out.println("Element absent");
            return true;
        }
    }

    public boolean isContainAttribute(String attribute) throws Exception {
        boolean isContain = false;
        WebElement element = driver.findElement(locator);
        if (element.getAttribute(attribute) != null) {
            isContain = true;
        }
        return isContain;
    }

    public String getText() {
        return driver.findElement(locator).getText();
    }

    public String getTextNode(WebElement element) {
        String text = element.getText().trim();
        List<WebElement> children = element.findElements(By.xpath("./*"));
        for (WebElement child : children) {
            text = text.replaceFirst(child.getText(), "").trim();
        }
        return text;

    }

    public List<String> getTexts() {
        List<WebElement> elems = driver.findElements(locator);
        List<String> textValues = new ArrayList<String>();
        for (WebElement elem : elems) {
            textValues.add(elem.getText());
        }
        return textValues;
    }

    public String getAttributeValue(String attributeName) {
        return driver.findElement(locator).getAttribute(attributeName);
    }

    public WebElement findElement() {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements() {
        return driver.findElements(locator);
    }

    public String getCssValue(String propName) {
        return driver.findElement(locator).getCssValue(propName);
    }

    public BaseLocator clearTextFieldAction() throws InterruptedException {
        WebElement element = driver.findElement(locator);
        element.clear();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.SPACE).sendKeys(Keys.BACK_SPACE).perform();
        Thread.sleep(1000);
        return this;
    }

    public BaseLocator setBlankValue() {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.click();
        element.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a", Keys.DELETE, ""));
        return this;
    }

    public BaseLocator sendKeysAction(CharSequence value) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(locator)).click().sendKeys(value).perform();
        return this;
    }

    public BaseLocator waitUntilNumberOfElements(int num) {
        wait.until(CustomWait.waitUntilNumberOfElement(driver, locator, num));
        return this;
    }

    public BaseLocator waitUntilNumberOfElements(int num, int timeout) {
        new WebDriverWait(driver, timeout)
                .until(CustomWait.waitUntilNumberOfElement(driver, locator, num));
        return this;
    }

    public WebElement findChildElement(WebElement element) {
        return element.findElement(this.locator);
    }

    public BaseLocator waitUntilChildElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, this.locator));
        return this;
    }

    public BaseLocator moveToRight(int dimension) {
        WebElement startElement = driver.findElement(this.locator);
        Actions builder = new Actions(driver);

        builder.clickAndHold(startElement).moveByOffset(dimension, 0).release().build().perform();
        return this;
    }

    public BaseLocator moveToLeft(int dimension) {
        WebElement startElement = driver.findElement(this.locator);
        Actions builder = new Actions(driver);

        builder.clickAndHold(startElement).moveByOffset(dimension, 0).release().build().perform();
        return this;
    }

    public Dimension getDimensions() {
        return driver.findElement(locator).getSize();
    }

    public Boolean isVisible(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        if (elements.size() != 0) {
            // If threre's element
            try {
                // return is displayed or not
                return elements.get(0).isDisplayed();
            } catch (StaleElementReferenceException e) {
                // Try again if hit StaleElementReferenceException
                if (driver.findElements(locator).size() != 0) {
                    return driver.findElements(locator).get(0).isDisplayed();
                }
            }
        }
        return false;
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
        return null;
    }
}


