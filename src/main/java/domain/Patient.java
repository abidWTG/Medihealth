package domain;

/**
 * Created by Abidur.Rahman on 01/08/2017.
 */
public class Patient {

    private int age;
    private boolean medihealthInsurance;

    public Patient() {}

    public Patient(int age, boolean medihealthInsurance) {
        this.age = age;
        this.medihealthInsurance = medihealthInsurance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMedihealthInsured() {
        return medihealthInsurance;
    }

    public void setMedihealthInsurance(boolean medihealthInsurance) {
        this.medihealthInsurance = medihealthInsurance;
    }
}
