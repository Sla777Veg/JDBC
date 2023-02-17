package dao;

import model.City;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO{

    private Connection connection;

    public EmployeeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT " +
                            "first_name,last_name,gender,age,city_name" +
                            " FROM employee " +
                            "FULL JOIN city ON employee.city_id = city.city_id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                City city_name = new City(resultSet.getString( "city_name"));
                employees.add(new Employee(first_name,
                        last_name,
                        gender,
                        age,
                        city_name));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public void create(Employee employee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO employee (first_name,last_name,gender,age,city_id)" +
                            "VALUES ((?),(?),(?),(?),(?))");
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setInt(4, employee.getAge());
            preparedStatement.setInt(5, employee.getCityID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee readById(int id) {
        Employee employee = new Employee("Iren",
                "Ivanova",
                "female",
                22,1);
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT *" +
                            " FROM employee " +
                            "INNER JOIN city ON employee.city_id = city.city_id WHERE id =(?)");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee.setName(resultSet.getString("first_name"));
                employee.setSurname(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
                employee.setId(Integer.parseInt(resultSet.getString("id")));
                employee.setCityID(Integer.parseInt(resultSet.getString("city_id")));
                employee.setCity(new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public void updateAmountById(int id, int cityId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE employee SET city_id = (?) WHERE id = (?)");
            preparedStatement.setInt(1, cityId);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM employee WHERE id = (?)");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
