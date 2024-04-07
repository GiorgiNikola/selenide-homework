package ge.tbcitacademy.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int failCounter = 0;
    @Override
    public boolean retry(ITestResult result) {
        RetryCount annotation = result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(RetryCount.class);
        if ((annotation != null) && failCounter < annotation.count()){
            failCounter++;
            return true;
        }
        return false;
    }
}
