package company.service;

import company.employees.Designer;
import company.employees.Developer;
import company.employees.Employee;
import company.employees.Manager;
import company.enums.EmployeeType;
import company.enums.Gender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeServiceDb implements EmployeeService {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/employee";
    static final String USER = "postgres";
    static final String PASS = "root";

    public EmployeeServiceDb() {
        // this constructor should create database + write provided employees into it:
        // TODO add constraint checks into CREATE TABLE query
        // CREATE TABLE employees
        // (id SERIAL PRIMARY KEY NOT NULL,
        // name VARCHAR(50) NOT NULL,
        // age INT NOT NULL,
        // salary INT NOT NULL,
        // gender VARCHAR(50) NOT NULL,
        // fixed_bugs INT,
        // rate DECIMAL,
        // worked_days INT);

        // constraint checks:
        // ALTER TABLE employees ADD CONSTRAINT valid_type_check CHECK (type IN ('Designer', 'Developer', 'Manager'));
        // ALTER TABLE employees ADD CONSTRAINT valid_gender_check CHECK (gender IN ('Male', 'Female'));
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void main(String[] args) {
        EmployeeServiceDb db = new EmployeeServiceDb();
        db.printEmployees();
        System.out.println(db.getById(5));
    }

    @Override
    public void printEmployees() {
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees")) {

            while(resultSet.next()) {
                Employee employee = getEmployeeFromResultSet(resultSet);
                System.out.println(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calculateSalaryAndBonus() {
        return 0;
    }

    @Override
    public Employee getById(long id) {
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE id = " + id)) {

            if (resultSet.next()) {
                return getEmployeeFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee[] getByName(String name) {
        return new Employee[0];
    }

    @Override
    public Employee[] sortByName() {
        return new Employee[0];
    }

    @Override
    public Employee[] sortByNameAndSalary() {
        return new Employee[0];
    }

    @Override
    public Employee edit(Employee newEmployee) {
        return null;
    }

    @Override
    public Employee remove(long id) {
        return null;
    }

    @Override
    public void add(Employee newEmployee) {

    }

    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        int age = resultSet.getInt(3);
        int salary = resultSet.getInt(4);
        Gender gender = Gender.valueOf(resultSet.getString(5).toUpperCase());
        int fixedBugs = resultSet.getInt(6);
        double rate = resultSet.getDouble(7);
        int workedDays = resultSet.getInt(8);
        EmployeeType type = EmployeeType.valueOf(resultSet.getString(9).toUpperCase());

        return switch (type) {
            case MANAGER -> new Manager(id, name, age, salary, gender);
            case DESIGNER -> new Designer(id, name, age, salary, gender, rate, workedDays);
            case DEVELOPER -> new Developer(id, name, age, salary, gender, fixedBugs);
        };
    }
}
