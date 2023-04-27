package pages;

import core.BaseLocator;
import core.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends BasePage{
    public Page page;

    public LoginPage() {
        super();
        page = new Page();
    }
     class Page {
        public BaseLocator txtUserName() {
            return new BaseLocator(By.xpath("//input[@id = 'user-name']"));
        }

        public BaseLocator txtPassword() {
            return new BaseLocator(By.xpath("//input[@id = 'password']"));
        }

        public BaseLocator btnLogin() {
            return new BaseLocator(By.xpath("//input[@id = 'login-button']"));
        }

        public BaseLocator titlePage() {
            return new BaseLocator(By.xpath("//span[@class='title']"));
        }

    }
    @Override
    public void openPage(String url) {
        getDriver().get(url);
    }
    public void enterUserName(String userName) {
        page.txtUserName().sendKeys(userName);
    }

    public void enterPassword(String password) {
        page.txtPassword().sendKeys(password);
    }

    public void clickLoginButton() {
        page.btnLogin().click();
    }

    public void verifyTitlePageAsExpectedResult(String title) {
        Assert.assertEquals(page.titlePage().getText(), title);
    }
}
