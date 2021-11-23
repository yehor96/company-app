package company.service;

import company.employees.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeServiceDb implements EmployeeService {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/employees";
    static final String USER = "postgres";
    static final String PASS = "root";

    public EmployeeServiceDb() {
        //initializeDb
        //CREATE TABLE employees
        // (id SERIAL PRIMARY KEY NOT NULL,
        // name VARCHAR(50) NOT NULL,
        // age INT NOT NULL,
        // salary INT NOT NULL,
        // gender VARCHAR(50) NOT NULL,
        // fixed_bugs INT,
        // rate DECIMAL,
        // worked_days INT);
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    @Override
    public void printEmployees() {
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees")) {

            while(resultSet.next()) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calculateSalaryAndBonus() {
        return 0;
    }

    @Override
    public Employee getById(long id) {
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
}
