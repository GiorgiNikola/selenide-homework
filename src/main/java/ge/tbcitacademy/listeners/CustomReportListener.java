package ge.tbcitacademy.listeners;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomReportListener implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();
                printFailedTests(testContext);
            }
        }
    }

    private void printFailedTests(ITestContext testContext) {
        Set<ITestResult> failedTests = testContext.getFailedTests().getAllResults();
        for (ITestResult failedTest : failedTests) {
            System.out.println("Failed Test: " + failedTest.getName());
            System.out.println("Description: " + failedTest.getMethod().getDescription());
            System.out.println("------------------------------------");
        }
    }
}
