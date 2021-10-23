package company.employees;

import company.enums.Gender;

public abstract class Employee {

    protected static long counter = 1;
    protected long id;
    protected String name;
    protected int age;
    protected int salary;
    protected Gender gender;

    protected Employee(String name, int age, int salary, Gender gender) {
        this.id = counter++;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public abstract double getFinalSalary();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", gender=" + gender +
                '}';
    }
}
