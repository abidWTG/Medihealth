import service.*;
import domain.Patient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.ServicesCalculator;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by Abidur.Rahman on 01/08/2017.
 */
public class ServicesCalculatorTest {

    private ServicesCalculator servicesCalculator;

    @Before
    public void setUp () {
        //servicesCalculator = mock(ServicesCalculator.class);
        servicesCalculator = new ServicesCalculator();
    }

    @After
    public void tearDown() {
        servicesCalculator = null;
    }

    // DiagnosisService calculations

    @Test
    public void testDiagnosisReturnValueWithNullPatient() {
        assertEquals(60.0, servicesCalculator.calculateDiagnosisService(null));
    }

    @Test
    public void testDiagnosisWithPatientAge10() {
        Patient patient = new Patient(10, false);
        assertEquals(60.0, servicesCalculator.calculateDiagnosisService(patient));
    }

    @Test
    public void testDiagnosisDiscountCalculationWithPatientAgeOver71() {
        Patient patient = new Patient(71, false);
        double expectedResult = calculateDiscount(0.90, ServiceTypes.DIAGNOSIS);
        assertEquals(expectedResult, servicesCalculator.calculateDiagnosisService(patient));
    }

    @Test
    public void testDiagnosisDiscountCalculationWithPatientAgesBetween65And70() {
        Patient patient65 = new Patient(65, false);
        Patient patient70 = new Patient(70, false);

        double expectedResult = calculateDiscount(0.60, ServiceTypes.DIAGNOSIS);

        assertEquals(expectedResult, servicesCalculator.calculateDiagnosisService(patient65));
        assertEquals(expectedResult, servicesCalculator.calculateDiagnosisService(patient70));
    }


    @Test
    public void testDiagnosisDiscountCalculationWithChildUnder5() {
        Patient patient = new Patient(4, false);
        double expectedResult = calculateDiscount(0.40, ServiceTypes.DIAGNOSIS);
        assertEquals(expectedResult, servicesCalculator.calculateDiagnosisService(patient));
    }

    ////////////////////////////////////////////////////////////////////////////////////
    // XRAYService calculations

    @Test
    public void testXRayReturnValueWithNullPatient() {
        assertEquals(150.0, servicesCalculator.calculateXRayService(null));
    }

    @Test
    public void testXRayDiscountCalculationWithPatientAgesBetween65And70() {
        Patient patient65 = new Patient(65, false);
        Patient patient70 = new Patient(70, false);

        double expectedResult = calculateDiscount(0.60, ServiceTypes.XRAYS);

        assertEquals(expectedResult, servicesCalculator.calculateXRayService(patient65));
        assertEquals(expectedResult, servicesCalculator.calculateXRayService(patient70));
    }

    @Test
    public void testXRayDiscountCalculationWithPatientAgesOver70() {
        Patient patient = new Patient(85, false);
        double expectedResult = calculateDiscount(0.90, ServiceTypes.XRAYS);
        assertEquals(expectedResult, servicesCalculator.calculateXRayService(patient));
    }

    @Test
    public void testXRayDiscountCalculationWithChildUnder5() {
        Patient patient = new Patient(3, false);
        double expectedResult = calculateDiscount(0.40, ServiceTypes.XRAYS);
        assertEquals(expectedResult, servicesCalculator.calculateXRayService(patient));
    }

    ////////////////////////////////////////////////////////////////////////////////////
    // BloodTestService calculations

    @Test
    public void testBloodTestReturnValueWithNullPatient() {
        assertEquals(78.0, servicesCalculator.calculateBloodTestService(null));
    }

    @Test
    public void testBloodTestDiscountCalculationWithPatientAgesBetween65And70WithNoInsurance() {
        Patient patient65 = new Patient(65, false);
        Patient patient70 = new Patient(70, false);

        double expectedResult = calculateDiscount(0.60, ServiceTypes.BLOOD_TEST);
        BigDecimal roundUp = new BigDecimal(expectedResult).setScale(2,BigDecimal.ROUND_CEILING);
        assertEquals(roundUp, servicesCalculator.calculateBloodTestService(patient65));
        assertEquals(roundUp, servicesCalculator.calculateBloodTestService(patient70));
    }

    @Test
    public void testBloodTestDiscountCalculationWithPatientAgesBetween65And70WithInsuranceAndPractioner() {
        Patient patient65 = new Patient(65, true);
        Patient patient70 = new Patient(70, true);

        BigDecimal bigDecimal = new BigDecimal(31.049999999999997).setScale(2,BigDecimal.ROUND_CEILING);
        BloodTest.setPractioner(true);
        assertEquals(bigDecimal, servicesCalculator.calculateBloodTestService(patient65));
        assertEquals(bigDecimal, servicesCalculator.calculateBloodTestService(patient70));
    }

    @Test
    public void testBloodTestDiscountCalculationWithPatientAgesBetween65And70WithInsuranceNoPractioner() {
        Patient patient65 = new Patient(65, true);
        Patient patient70 = new Patient(70, true);

        double expectedResult = calculateDiscount(0.60, ServiceTypes.BLOOD_TEST);
        BigDecimal bigDecimal = new BigDecimal(expectedResult).setScale(2,BigDecimal.ROUND_CEILING);
        BloodTest.setPractioner(false);
        assertEquals(bigDecimal, servicesCalculator.calculateBloodTestService(patient65));
        assertEquals(bigDecimal, servicesCalculator.calculateBloodTestService(patient70));
    }

    ////////////////////////////////////////////////////////////////////////////////////
    // ECGService calculations

    @Test
    public void testECGTestReturnValueWithNullPatient() {
        assertEquals(200.40, servicesCalculator.calculateECGService(null));
    }

    @Test
    public void testECGTestDiscountCalculationWithPatientAgesBetween65And70() {
        Patient patient65 = new Patient(65, false);
        Patient patient70 = new Patient(70, false);

        double expectedResult = calculateDiscount(0.60, ServiceTypes.ECG);

        assertEquals(expectedResult, servicesCalculator.calculateECGService(patient65));
        assertEquals(expectedResult, servicesCalculator.calculateECGService(patient70));
    }

    @Test
    public void testECGDiscountCalculationWithPatientAgesOver70() {
        Patient patient = new Patient(85, false);
        double expectedResult = calculateDiscount(0.90, ServiceTypes.ECG);
        assertEquals(expectedResult, servicesCalculator.calculateECGService(patient));
    }

    @Test
    public void testECGDiscountCalculationWithChildUnder5() {
        Patient patient = new Patient(3, false);
        double expectedResult = calculateDiscount(0.40, ServiceTypes.ECG);
        assertEquals(expectedResult, servicesCalculator.calculateECGService(patient));
    }

    ////////////////////////////////////////////////////////////////////////////////////
    // VaccineService calculations

    @Test
    public void testVaccineTestReturnValueWithNullPatient() {
        assertEquals(27.50, servicesCalculator.calculateVaccineServices(null, null));
    }

    @Test
    public void testVaccineTestDiscountCalculationWithPatientAgesBetween65And70() {
        Patient patient65 = new Patient(65, false);
        Patient patient70 = new Patient(70, false);

        double expectedResult = calculateDiscount(0.60, ServiceTypes.VACCINE);

        assertEquals(expectedResult, servicesCalculator.calculateVaccineServices(patient65, null));
        assertEquals(expectedResult, servicesCalculator.calculateVaccineServices(patient70, null));
    }

    @Test
    public void testVaccineTestDiscountCalculationWithPatientAgesBetween65And70WithVaccine() {
        Patient patient65 = new Patient(65, false);
        Patient patient70 = new Patient(70, false);
        Vaccine vaccineOne =  new Vaccine(2);
        Vaccine vaccineTwo =  new Vaccine(3);
        double expectedResultFirstTest = calculateDiscount(0.60, ServiceTypes.VACCINE) + 30;
        double expectedResultSecondTest = calculateDiscount(0.60, ServiceTypes.VACCINE) + 45;

        assertEquals(expectedResultFirstTest, servicesCalculator.calculateVaccineServices(patient65, vaccineOne));
        assertEquals(expectedResultSecondTest, servicesCalculator.calculateVaccineServices(patient70, vaccineTwo));
    }


    /**
     * Stub to calculate discount value.
     *
     * @param discountValue discount value.
     * @return return calculated discount;
     */
    private double calculateDiscount(double discountValue, ServiceTypes serviceType) {
        double serviceCost = findServiceType(serviceType);
        double costAfterDiscount = serviceCost * discountValue;
        double expectedResult = serviceCost - costAfterDiscount;
        return expectedResult;
    }

    private double findServiceType (ServiceTypes serviceType) {
        double serviceCost = 0;
        switch (serviceType) {
            case DIAGNOSIS:
                serviceCost = Diagnosis.DIAGNOSIS_SERVICE;
                break;
            case XRAYS:
                serviceCost = XRay.XRAY_SERVICE;
                break;
            case BLOOD_TEST:
                serviceCost = BloodTest.BLOOD_TEST_SERVICE;
                break;
            case ECG:
                serviceCost = ECG.ECG_SERVICE;
                break;
            case VACCINE:
                serviceCost = Vaccine.VACCINE_SERVICE;
                break;
            default:
                break;
        }
        return serviceCost;
    }
}

enum ServiceTypes {
    DIAGNOSIS, XRAYS, BLOOD_TEST, ECG, VACCINE;
}