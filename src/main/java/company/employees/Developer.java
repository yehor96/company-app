package company.employees;

import company.enums.Gender;

import static company.Randomizer.getBoolean;

public class Developer extends Employee {

    private static final double DEFAULT_BUG_RATE = 7.2;
    private final int fixedBugs;

    public Developer(String name, int age, int salary, Gender gender, int fixedBugs) {
        super(name, age, salary, gender);
        this.fixedBugs = fixedBugs;
    }

    @Override
    public double getFinalSalary() {
        return (salary + fixedBugs * DEFAULT_BUG_RATE) * (double) (getBoolean() ? 2 : 0);
    }

    @Override
    public String toString() {
        String superString = super.toString().replace("}", "");
        return superString +
                ", fixedBugs=" + fixedBugs +
                "}";
    }

}
