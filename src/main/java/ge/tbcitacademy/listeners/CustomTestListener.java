package ge.tbcitacademy.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomTestListener implements ITestListener {
    private Map<String, LocalDateTime> testCaseStartTimes = new HashMap<>();
    private LocalDateTime testStartTime;

    @Override
    public void onStart(ITestContext context) {
        testStartTime = LocalDateTime.now();
        System.out.printf("Test %s Has Started On %s\n", context.getName(), context.getStartDate());
    }

    @Override
    public void onFinish(ITestContext context) {
        LocalDateTime testEndTime = LocalDateTime.now();
        Duration duration = Duration.between(testStartTime, testEndTime);
        System.out.printf("Test %s Has Finished On %s\n", context.getName(), context.getEndDate());
        System.out.printf("Test %s Took %s\n", context.getName(), duration);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        LocalDateTime startTime = LocalDateTime.now();
        testCaseStartTimes.put(methodName, startTime);
        System.out.printf("Test Case %s has started on %s\n", methodName, startTime);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        LocalDateTime startTime = testCaseStartTimes.get(methodName);
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        System.out.printf("Test Case %s Successfully executed on %s\n", methodName, new Date(result.getEndMillis()));
        System.out.printf("Test Case %s took %s\n", methodName, duration);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        LocalDateTime startTime = testCaseStartTimes.get(methodName);
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        System.out.printf("Test Case %s Failed on %s\n", methodName, new Date(result.getEndMillis()));
        System.out.printf("Test Case %s took %s\n", methodName, duration);
    }
}
