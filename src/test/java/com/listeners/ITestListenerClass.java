package com.listeners;

import com.ui.elements.BaseTest;
import com.utils.Utils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestListenerClass extends BaseTest implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        CaptureScreenshot( result.getName()+".png");
    }

}
