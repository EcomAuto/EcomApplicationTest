package util;
import io.qameta.allure.Allure;

public class TestStepTracker {
    private static final ThreadLocal<Integer> stepNumber = ThreadLocal.withInitial(() -> 1);

    public static void logStep(String stepDescription) {
        int step = stepNumber.get();
        Allure.step("Step " + step + ": " + stepDescription);
        stepNumber.set(step + 1);
    }

    public static void reset() {
        stepNumber.set(1);
    }
}


