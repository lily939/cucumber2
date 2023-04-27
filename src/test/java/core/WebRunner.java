package core;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class WebRunner extends TestRunner {
    @BeforeTest
    public void setUpTest() {
    }

    @AfterTest
    public void tearDownTest()
            throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        DriverManager.quitDriver();
    }
}
