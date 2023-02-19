import dao.EmployeeDAO;
import dao.EmployeeDaoImpl;
import jdbc.ConnectionManager;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Application {
    public static void main(String[] args) throws SQLException {

        Connection connection = ConnectionManager.getConnection();

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