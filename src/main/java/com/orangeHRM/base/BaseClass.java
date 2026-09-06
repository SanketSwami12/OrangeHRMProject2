package com.orangeHRM.base;

import java.util.Properties;
import org.apache.logging.log4j.core.Logger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangeHRM.actiondriver.ActionDriver;
import com.orangeHRM.utilities.ExtentManager;
import com.orangeHRM.utilities.LoggerManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class BaseClass {

	protected static Properties prop;
	// protected static WebDriver driver;
	// private static ActionDriver actionDriver;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>(); // for cross browser & parallel testing
	private static ThreadLocal<ActionDriver> actionDriver = new ThreadLocal<>(); // for cross browser & parallel testing

	public static final Logger Logger = LoggerManager.getLogger(BaseClass.class);

	@BeforeSuite
	public void loadConfig() throws IOException {
		// Load the configuration file
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		// now load the file
		prop.load(fis);
		Logger.info("config.properties file loaded");

		// Start the Extent Report
	//	ExtentManager.getReprter(); --> This has been implemented in TestListner

	}

	@BeforeMethod
	public synchronized void setup() throws IOException {

		System.out.println("Setting up WebDriver for:" + this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
		staticWait(5);

		Logger.info("WebDriver Initialized and Browser Maximized");
		Logger.trace("This is a trace message");
		Logger.error("This is a error message");
		Logger.debug("This is a debug message");
		Logger.fatal("This is a fatal message");
		Logger.warn("This is a warn message");

		/*
		 * //Initialize the actionDriver if(actionDriver == null) { actionDriver = new
		 * ActionDriver(driver); Logger.info("ActionDriver Instance Is Created"); }
		 */
		// Initialize ActionDriver for the current thread
		actionDriver.set(new ActionDriver(getDriver()));
		Logger.info("ActionDriver initialized for thread: " + Thread.currentThread().getId());
	}

	// Initialize the WebDriver based on browser defined in config.properties file
	private void launchBrowser() {

	//	String browser = prop.getProperty("browser");
		String browser = System.getProperty("browser", prop.getProperty("browser")); ////“First check whether Maven/Jenkins supplied a browser value. If it didn't, use the browser value from config.properties.”

		if (browser.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver();
			driver.set(new ChromeDriver()); // New changes as per thread
			ExtentManager.registerDriver(getDriver());
			Logger.info("ChromeDriver Instance Is Created");
		} else if (browser.equalsIgnoreCase("firefox")) {
			// driver = new FirefoxDriver();
			driver.set(new FirefoxDriver());// New changes as per thread
			ExtentManager.registerDriver(getDriver());

			Logger.info("FireFoxDriver Instance Is Created");

		} else if (browser.equalsIgnoreCase("edge")) {
			// driver = new EdgeDriver();
			driver.set(new EdgeDriver());// New changes as per thread
			ExtentManager.registerDriver(getDriver());

			Logger.info("EdgeDriver Instance Is Created");

		} else {
			throw new IllegalArgumentException("Browser Not Supported:" + browser);
		}

	}

	// Configure browser setting such as implicit wait, maximize the browser and
	// navigate to the URL
	private void configureBrowser() {

		// Implicit wait
		int implicitwait = Integer.parseInt(prop.getProperty("implicitwait"));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitwait));

		// Maximize the driver
		getDriver().manage().window().maximize();

		// Navigate to URL
		try {
			getDriver().get(prop.getProperty("url"));
		} catch (Exception e) {
			Logger.info("Failed to Navigate to the URL:" + e.getMessage());
		}
	}

	@AfterMethod
	public void tearDown() {
		if (getDriver() != null) {
			try {
				getDriver().quit();
			} catch (Exception e) {
				Logger.info("Unable to quit the driver:" + e.getMessage());
			}
		}
		Logger.info("WebDriver Instance Is Closed");
		driver.remove();
		actionDriver.remove();
		// driver=null;
		// actionDriver=null;
	//	ExtentManager.endTest(); --> This has been implemented in TestListner
	}

	// Driver getter and setter method why we use we need to use driver in another
	// package

//	// Driver getter method
//	public WebDriver getDriver() {
//		return driver;
//	}
//
	// Driver setter method
	public void setDriver(ThreadLocal<WebDriver> driver) {
		this.driver = driver;

	}

	// lets write new getter & setter method
	// Getter Method for WebDriver
	public static WebDriver getDriver() {
		if (driver.get() == null) {
			System.out.println("WebDriver Is Not Initialized");
			throw new IllegalStateException("WebDriver Is Not Initialized");

		}
		return driver.get();
	}

	// Getter Method for ActionDriver
	public static ActionDriver getActionDriver() {
		if (actionDriver.get() == null) {
			System.out.println("ActionDriver Is Not Initialized");
			throw new IllegalStateException("ActionDriver Is Not Initialized");

		}
		return actionDriver.get();
	}

	// Getter method for config prop file
	public static Properties getProp() {
		return prop;
	}

	// static wait for pause instead of Thread.sleep
	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
}
