package com.learndatastructure.utils;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.util.List;

/**
 * @author Sanjay Singh Rawat
 * @since 2020.08.21
 */
public final class JUnitUtils {

    private JUnitUtils() {}

    public static void launch(Class<?> clazz) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request().selectors(DiscoverySelectors.selectClass(clazz)).build();
        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
        TestExecutionSummary summary = listener.getSummary();
        List<TestExecutionSummary.Failure> failures = summary.getFailures();
        System.out.println("getTestsFoundCount() - " + summary.getTestsFoundCount());
        System.out.println("getTestsSucceededCount() - " + summary.getTestsSucceededCount());
        failures.forEach(failure -> System.out.println("failure - " + failure.getException()));
    }
}
