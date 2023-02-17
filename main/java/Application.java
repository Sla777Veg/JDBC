import dao.EmployeeDAO;
import dao.EmployeeDaoImpl;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {

        // Создаем переменные с данными для подключения к базе
        final String user = "postgres";
        String password = "klerik87&";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        Connection connection = DriverManager.getConnection(url, user, password);
        EmployeeDAO employeeDAO = new EmployeeDaoImpl(connection);
        // Создаем объект
        City city = new City(1, "Moscow");
        Employee employee1 = new Employee("Iren",
                "Ivanova",
                "female",
                22,1);
        // Вызываем метод добавления объекта
        employeeDAO.create(employee1);

        // Создаем список наполняя его объектами, которые получаем
        // путем вызова метода для получения всех элементов таблицы
        List<Employee> list = new ArrayList<>(employeeDAO.findAll());

        // Выведем список в консоль
        for(Employee employee: list) {
            System.out.println(employee);
        }

    }

}