package company;

import company.employees.Designer;
import company.employees.Developer;
import company.employees.Employee;
import company.employees.Manager;
import company.enums.EmployeeType;
import company.enums.Gender;

import static company.Randomizer.getBoolean;
import static company.Randomizer.getIntWithin;

public class EmployeeFactory {

    private final String[] maleNames = {"John", "Martin", "Michael", "Adam", "Colin", "Steve", "Marcus", "Ivan"};
    private final String[] femaleNames = {"Angelina", "Veronica", "Jessica", "Sarah", "Nicole", "Kylie", "Anna", "Evy"};

    public Employee[] generateEmployees(int size) {
        Employee[] employees = new Employee[size];

        for (int i = 0; i < size; i++) {
            employees[i] = generateEmployee();
        }
        return employees;
    }

    public Employee generateEmployee() {
        Gender gender = getGender(getBoolean());
        String name = getName(gender);
        int age = getIntWithin(18, 75);

        int salary;
        EmployeeType type = getRandomEmployeeType();
        switch (type) {
            case DEVELOPER:
                salary = getIntWithin(10, 100) * 100;
                int fixedBugs = getIntWithin(1, 10);
                return new Developer(name, age, salary, gender, fixedBugs);
            case MANAGER:
                salary = getIntWithin(50, 200) * 100;
                return new Manager(name, age, salary, gender);
            case DESIGNER:
                salary = getIntWithin(5, 60) * 100;
                double rate = getIntWithin(50, 150) * 1.5;
                int workedDays = getIntWithin(1, 20);
                return new Designer(name, age, salary, gender, rate, workedDays);
            default:
                throw new IllegalStateException("Unexpected value: " + type.name());
        }
    }

    private EmployeeType getRandomEmployeeType() {
        EmployeeType[] types = EmployeeType.values();
        return types[getIntWithin(0, types.length - 1)];
    }

    private String getName(Gender gender) {
        if (gender == Gender.MALE) {
            return maleNames[getIntWithin(0, maleNames.length - 1)];
        } else {
            return femaleNames[getIntWithin(0, femaleNames.length - 1)];
        }
    }

    private Gender getGender(boolean isFemale) {
        if (isFemale) {
            return Gender.FEMALE;
        } else {
            return Gender.MALE;
        }
    }
}
