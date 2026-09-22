package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseClass;
import utils.ScreenshotUtils;

public class TestListener
        implements ITestListener {

    @Override
    public void onTestStart(
            ITestResult result) {

        System.out.println(
                "\n========== Test Started: "
                + result.getName()
                + " =========="
        );
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        System.out.println(
                "========== Test PASSED: "
                + result.getName()
                + " ==========\n"
        );
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        System.out.println(
                "========== Test FAILED: "
                + result.getName()
                + " =========="
        );

        Object testObject =
                result.getInstance();

        if (testObject instanceof BaseClass) {

            BaseClass base =
                    (BaseClass) testObject;

            if (base.driver != null) {

                ScreenshotUtils.takeScreenshot(
                        base.driver,
                        result.getName()
                );
            }
        }
    }

    @Override
    public void onTestSkipped(
            ITestResult result) {

        System.out.println(
                "Test skipped: "
                + result.getName()
        );
    }
}