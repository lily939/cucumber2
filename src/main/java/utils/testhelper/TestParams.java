package utils.testhelper;

public class TestParams {
    public static final boolean DEBUGGING = isLogger();
    public static boolean isLogger() {
        if (System.getProperty("logging") != null) {
            return System.getProperty("logging").equalsIgnoreCase("true");
        }
        return false;
    }
}
