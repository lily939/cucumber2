package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.enums.Browser;
import java.time.Duration;
import java.util.ArrayList;

public class BasePage {
  private WebDriver driver;
  private String currentWindow = null;
  private WebDriverWait wait;
  private String mainWindowHandle;

  public BasePage() {
    if (DriverManager.getDriver() == null) {
      driver = DriverManager.startBrowser();
    }
    else {
      driver = DriverManager.getDriver();
    }
    this.wait = new WebDriverWait(driver,10);
  }
  public void openPage(String url) {
    driver.get(url);
  }

  // Get current url
  public String getUrl() {
    return driver.getCurrentUrl();
  }

  // Refresh page
  public void refreshPage() {
    driver.navigate().refresh();
  }

  // Maximize browser
  public void maximizeBrowser() {
    driver.manage().window().maximize();
  }

  // Move browser to -2000, 0 to minimize it
  public void minimizeBrowser() {
    driver.manage().window().setPosition(new Point(-2000, 0));
  }

  // Bring back browser
  public void maximizeBrowser(boolean isMinimized) {
    driver.manage().window().setPosition(new Point(0, 0));
  }

  public void switchToNewTab() {
    currentWindow = driver.getWindowHandle();
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    // Switch to nearest tab
    for (String tab : tabs) {
      if (!tab.equals(currentWindow)) {
        driver.switchTo().window(tab);
        break;
      }
    }
  }

  public void sendKeysActiveElement(CharSequence value) {
    if (Browser.getBrowser() == Browser.FIREFOX) {
      driver.findElement(By.tagName("body")).sendKeys(value);
    } else {
      driver.switchTo().activeElement().sendKeys(value);
    }
  }

  public void sendActionsActiveElement(CharSequence value) {
    Actions action = new Actions(driver);
    action.moveToElement(driver.switchTo().activeElement()).sendKeys(value).perform();
  }

  public BasePage waitUntilNewWindows(int timeout) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.until(CustomWait.waitUntilNewWindowIsOpened(driver));
    return this;
  }

  public BasePage waitUntilNewWindows() {
    wait.until(CustomWait.waitUntilNewWindowIsOpened(driver));
    return this;
  }

  public BasePage switchToNewWindow() {
    // Set the current one so that we can switch back
    this.mainWindowHandle = driver.getWindowHandle();

    // Switch to different window handles
    for (String windowHandle : driver.getWindowHandles()) {
      if (!windowHandle.equals(this.mainWindowHandle)) {
        driver.switchTo().window(windowHandle);
      }
    }

    return this;
  }

  public BasePage switchToMainWindow() {
    driver.switchTo().window(this.mainWindowHandle);
    return this;
  }

  public BasePage waitUntilNewWindowIsClosed(int timeout) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.until(CustomWait.waitUntilNewWindowIsCLosed(driver));
    return this;
  }

  public BasePage waitUntilNewWindowIsClosed() {
    wait.until(CustomWait.waitUntilNewWindowIsCLosed(driver));
    return this;
  }
  public void scrollToEndPage() {
    JavascriptSupport.scrollToEndPage(driver);
  }

  public WebDriver getDriver() {
    return driver;
  }
}
