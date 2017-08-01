package service;

/**
 * Created by Abidur.Rahman on 01/08/2017.
 */
public class Vaccine {

    final public static double VACCINE_SERVICE = 27.50;
    private int vaccineCount;

    public Vaccine() {}

    public Vaccine(int vaccineCount) {
        this.vaccineCount = vaccineCount;
    }

    public int getVaccineCount() {
        return vaccineCount;
    }

    public void setVaccineCount(int vaccineCount) {
        this.vaccineCount = vaccineCount;
    }
}
