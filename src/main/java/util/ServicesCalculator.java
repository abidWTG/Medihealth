package util;

import service.*;
import domain.Patient;

import java.math.BigDecimal;

/**
 * Created by Abidur.Rahman on 01/08/2017.
 */
public class ServicesCalculator {

    public double calculateDiagnosisService(Patient patient) {
        double discount = Diagnosis.DIAGNOSIS_SERVICE * DiscountCalculator.calculateDiscount(patient);
        return discount != 0 ? Diagnosis.DIAGNOSIS_SERVICE - discount : Diagnosis.DIAGNOSIS_SERVICE;
    }

    public double calculateXRayService(Patient patient) {
        double discount = XRay.XRAY_SERVICE * DiscountCalculator.calculateDiscount(patient);
        return discount != 0 ? XRay.XRAY_SERVICE - discount : XRay.XRAY_SERVICE;
    }

    public BigDecimal calculateBloodTestService(Patient patient) {
        double discount = BloodTest.BLOOD_TEST_SERVICE * DiscountCalculator.calculateDiscount(patient);
        if (BloodTest.isPractioner() && patient.isMedihealthInsured()) {
            discount += 0.15;
        }
        double roundDiscount = Math.round(discount * 100.0) / 100.0;
        double newCost = discount != 0 ? BloodTest.BLOOD_TEST_SERVICE - roundDiscount : BloodTest.BLOOD_TEST_SERVICE;
        BigDecimal roundUp = new BigDecimal(newCost).setScale(2,BigDecimal.ROUND_CEILING);
        return roundUp;
    }

    public double calculateECGService(Patient patient) {
        double discount = ECG.ECG_SERVICE * DiscountCalculator.calculateDiscount(patient);
        return discount != 0 ? ECG.ECG_SERVICE - discount : ECG.ECG_SERVICE;
    }

    public double calculateVaccineServices(Patient patient, Vaccine vaccine) {
        double discount = Vaccine.VACCINE_SERVICE * DiscountCalculator.calculateDiscount(patient);
        double vaccineCost = 0;
        if (vaccine != null && vaccine.getVaccineCount() != 0) {
            vaccineCost = 15 * vaccine.getVaccineCount();
        }
        return discount != 0 ? (Vaccine.VACCINE_SERVICE - discount) + vaccineCost: Vaccine.VACCINE_SERVICE;

    }

}
