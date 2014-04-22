package jesg;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumGridTest {
	private WebDriver driver;
	private Capabilities capabilities;
	private Map<String, Capabilities> browserConfig = new HashMap<String, Capabilities>();
	
	@BeforeTest
	public void init(){
		browserConfig.put("firefox", DesiredCapabilities.firefox());
		browserConfig.put("chrome", DesiredCapabilities.chrome());
		browserConfig.put("phantomjs", DesiredCapabilities.phantomjs());
	}
	
	@Parameters({ "browser" })
	@BeforeMethod
	public void setUp(@Optional("firefox") String browser) throws Exception {
		RemoteWebDriver remoteDriver = new RemoteWebDriver(browserConfig.get(browser));
		driver = remoteDriver;
		capabilities = ((HasCapabilities) remoteDriver).getCapabilities();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test()
	public void verifyLinks() {
		driver.get("https://www.google.com/");
		System.out.println("Browser: " + capabilities.getBrowserName()
				+ " version: " + capabilities.getVersion() + " platform: "
				+ capabilities.getPlatform() + " title: " + driver.getTitle());
	}

}
