package company.employees;

import company.enums.Gender;

public class Designer extends Employee {

    private final double rate;
    private final int workedDays;

    public Designer(String name, int age, int salary, Gender gender, double rate, int workedDays) {
        super(name, age, salary, gender);
        this.rate = rate;
        this.workedDays = workedDays;
    }

    @Override
    public double getFinalSalary() {
        return (double) salary + rate * workedDays;
    }

    @Override
    public String toString() {
        String superString = super.toString().replace("}", "");
        return superString +
                ", rate=" + rate +
                ", workedDays=" + workedDays +
                "}";
    }
}
