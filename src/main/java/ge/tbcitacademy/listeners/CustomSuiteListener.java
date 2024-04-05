package ge.tbcitacademy.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.time.Duration;
import java.time.LocalDateTime;

public class CustomSuiteListener implements ISuiteListener {
    private LocalDateTime startTime;
    @Override
    public void onStart(ISuite suite) {
        startTime = LocalDateTime.now();
        System.out.printf("Suite %s Has Started On %s\n", suite.getName(), startTime);
    }

    @Override
    public void onFinish(ISuite suite) {
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        System.out.printf("Suite %s Has Finished On %s\n", suite.getName(), LocalDateTime.now());
        System.out.printf("Suite %s Has Taken %s\n", suite.getName(), duration);
    }
}
