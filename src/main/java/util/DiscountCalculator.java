package util;

import domain.Patient;

/**
 * Created by Abidur.Rahman on 01/08/2017.
 */
public class DiscountCalculator {

    // Anyone between 5 and 65 wouldn't get a discount - according to the requirements.
    public static double calculateDiscount(Patient patient) {
        double discount = 0.0;
        int patientAge = (patient != null && patient.getAge() != 0) ?  patient.getAge() : 0;
        if (patient == null || patientAge == 0) return discount;
        if (patientAge >= 65 && patientAge <= 70) {
            discount = 0.60;
        } else if (patientAge > 70) {
            discount = 0.90;
        } else if (patientAge < 5) {
            discount = 0.40;
        }
        return discount;
    }

}
