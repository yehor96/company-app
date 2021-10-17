package company.employees;

import company.enums.Gender;

public class Manager extends Employee {

    public Manager(String name, int age, int salary, Gender gender) {
        super(name, age, salary, gender);
    }

    @Override
    public double getFinalSalary() {
        return salary;
    }
}
