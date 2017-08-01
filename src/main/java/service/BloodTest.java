package service;

/**
 * Created by Abidur.Rahman on 01/08/2017.
 */
public class BloodTest {

    final public static double BLOOD_TEST_SERVICE = 78;
    private static boolean practioner;

    public static boolean isPractioner() {
        return practioner;
    }

    public static void setPractioner(boolean practioner) {
        BloodTest.practioner = practioner;
    }
}
