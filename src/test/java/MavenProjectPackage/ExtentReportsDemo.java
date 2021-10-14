package MavenProjectPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsDemo {

	//Declare ExtentReports variable at global level
	ExtentReports extentReport;
	
	@BeforeTest
	public void reportsconfig()
	{
		//Path where the results html file will be stored in project
		//user.dir gives path till the project i.e.E:\eclipse-workspace\MavenProject and after that new it will add under reports folder as : \\reports\\index.html
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		//Set the Report name as per user. This is custom made
		reporter.config().setReportName("Vikas Web Automation Results Report");
		
		//Set the Document Title as per user. This is custom made
		reporter.config().setDocumentTitle("Vikas Automation");
		
		//Attach the Extent Reporter to Extent class
		extentReport = new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("Tester", "Vikas Kushlani");
		
	}
	
	@Test
	public void initialDemo()
	{
		//Test object of extent report below will be used to tweak the extent report
		//extentReport createtest below will create test in Extent report and will talk to ExtentReport clasee above. It will understand that new test is created and run to it
		ExtentTest test= extentReport.createTest("Test Case 1");
		System.setProperty("webdriver.chrome.driver","E:\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		
		//Fail the results and report to ExtentReport
		//test.fail("Results do not macth");
		
		//add screenshot for this test
		//test.addScreenCaptureFromBase64String(null);
		
		//extentReport flush below will conclude the test and will create report, update the status with pass/fail
		extentReport.flush();
	}
}
