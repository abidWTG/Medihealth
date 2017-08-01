package main;

import domain.Patient;
import util.DiscountCalculator;

/**
 * Created by Abidur.Rahman on 01/08/2017.
 */
public class Application {

    public static void main(String[] args) {
        tryDiscountCalculator();
    }

    private static void tryDiscountCalculator() {
        DiscountCalculator serviceCalculator = new DiscountCalculator();

        Patient seniorCitizenAbove70 = new Patient(71, false);
        Patient child = new Patient(4, false);

        double above70 = serviceCalculator.calculateDiscount(seniorCitizenAbove70);
        double under5 = serviceCalculator.calculateDiscount(child);

        System.out.println(above70);
        System.out.println(under5);
    }
}
