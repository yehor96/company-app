package company;

public class EmployeeFactory {

    public Employee[] generateEmployees(int size) {
        Employee[] employees = new Employee[size];

        for (int i = 0; i < size; i++) {
            employees[i] = generateEmployee();
        }
        return employees;
    }

    public Employee generateEmployee() {
        int randomizer = NumberHelper.getRandomBounded(0, 10);

        String name = getRandomName(randomizer);
        int age = NumberHelper.getRandomBounded(18, 75);
        int salary = NumberHelper.getRandomBounded(10, 100) * 100;
        Gender gender = getGender(randomizer);
        int fixedBugs = NumberHelper.getRandomBounded(1, 10);

        return new Employee(name, age, salary, gender, fixedBugs);
    }

    private String getRandomName(int randomizer) {
        switch (randomizer) {
            case 0:
                return "John";
            case 1:
                return "Martin";
            case 2:
                return "Michael";
            case 3:
                return "Adam";
            case 4:
                return "Colin";
            case 5:
                return "Angelina";
            case 6:
                return "Veronica";
            case 7:
                return "Jessica";
            case 8:
                return "Sarah";
            case 9:
                return "Nicole";
            default:
                throw new IllegalStateException("Unexpected value: " + randomizer);
        }
    }

    private Gender getGender(int randomizer) {
        if (randomizer > 4) {
            return Gender.FEMALE;
        } else {
            return Gender.MALE;
        }
    }
}

