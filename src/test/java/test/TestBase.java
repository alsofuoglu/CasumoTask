package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.io.IOException;
import java.util.Properties;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {
  protected static ExtentReports report;
  protected static ExtentHtmlReporter htmlReporter;
  protected static ExtentTest extentLogger;

  Properties props = new Properties();

  public TestBase() {
    try {
      props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @BeforeTest
  public void setUpTest() {
    report = new ExtentReports();
    String projectPath = System.getProperty("user.dir");
    String path = projectPath + "/test-output/report.html";
    htmlReporter = new ExtentHtmlReporter(path);
    report.attachReporter(htmlReporter);
    htmlReporter.config().setReportName("Casumo Vowel Test");
    report.setSystemInfo("Environment", "QA");
    report.setSystemInfo("OS", System.getProperty("os.name"));
  }

  @AfterMethod
  public void tearDown(ITestResult result){
    if(result.getStatus()==ITestResult.FAILURE){
      extentLogger.fail(result.getName());
      extentLogger.fail(result.getThrowable());

    }
    report.flush();
  }

  @AfterTest
  public void tearDownTest() {}
}
