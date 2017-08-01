import domain.Patient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DiscountCalculator;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Abidur.Rahman on 01/08/2017.
 */
public class DiscountCalculatorTest {

    private DiscountCalculator discountCalculator;

    @Before
    public void setUp() {
        discountCalculator = mock(DiscountCalculator.class);
    }

    @After
    public void tearDown() {
        discountCalculator = null;
    }


    @Test
    public void testcCalculateDiscountWithNullPatient() {
        assertEquals(0.0, discountCalculator.calculateDiscount(null));
    }

    @Test
    public void testCalculateDiscountWithPatientWithNoAge() {
        Patient patient = new Patient();
        patient.setAge(0);
        assertEquals(0.0, discountCalculator.calculateDiscount(patient));
    }


    @Test
    public void testCalculateDiscountWithSeniorCitizenBetweenAge65And70() {
        Patient patientOne = new Patient();
        patientOne.setAge(65);
        Patient patientTwo = new Patient();
        patientOne.setAge(69);

        when(discountCalculator.calculateDiscount(patientOne)).thenReturn(0.60);
        when(discountCalculator.calculateDiscount(patientTwo)).thenReturn(0.60);

        assertEquals(discountCalculator.calculateDiscount(patientOne), 0.60);
        assertEquals(discountCalculator.calculateDiscount(patientTwo), 0.60);

    }

    @Test
    public void testCalculateDiscountWithSeniorCitizenOverAge70() {
        Patient patient = new Patient();
        patient.setAge(71);

        when(discountCalculator.calculateDiscount(patient)).thenReturn(0.90);
        assertEquals(discountCalculator.calculateDiscount(patient), 0.90);

    }

    @Test
    public void testCalculateDiscountWithChildrenUnder5() {
        Patient patient = new Patient();
        patient.setAge(4);

        when(discountCalculator.calculateDiscount(patient)).thenReturn(0.40);
        assertEquals(discountCalculator.calculateDiscount(patient), 0.40);

    }
}
