package company;

public class Employee {

    private static final double DEFAULT_BUG_RATE = 7.5;
    private static long counter = 1;
    private final long id;
    private final String name;
    private final int age;
    private final int salary;
    private final Gender gender;
    private final int fixedBugs;

    public Employee(String name, int age, int salary, Gender gender, int fixedBugs) {
        this.id = counter++;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
        this.fixedBugs = fixedBugs;
    }

    public Employee(long id, String name, int age, int salary, Gender gender, int fixedBugs) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
        this.fixedBugs = fixedBugs;
    }

    public long getId() {
        return id;
    }

    public double getDefaultBugRate() {
        return DEFAULT_BUG_RATE;
    }

    public int getSalary() {
        return salary;
    }

    public int getFixedBugs() {
        return fixedBugs;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", gender=" + gender +
                ", fixedBugs=" + fixedBugs +
                '}';
    }
}
