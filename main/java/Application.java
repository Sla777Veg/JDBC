import dao.EmployeeDAO;
import dao.EmployeeDaoImpl;
import jdbc.ConnectionManager;

import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Application {
    public static void main(String[] args) throws SQLException {

        EmployeeDAO employeeDAO = new EmployeeDaoImpl();

        // Создаем объект
        //City city = new City(1, "Moscow");
        Employee employee1 = new Employee("Iren",
                "Adler",
                "female",
                23,1);
        // Вызываем метод добавления объекта
        employeeDAO.create(employee1);

        // Получаем объект по id
        System.out.println(employeeDAO.readById(1));

        // Создаем список наполняя его объектами, которые получаем
        // путем вызова метода для получения всех элементов таблицы
        List<Employee> list = new ArrayList<>(employeeDAO.findAll());

        // Выведем список в консоль
        for(Employee employee: list) {
            System.out.println(employee);
        }

        Employee employee2 = new Employee("Vasily",
                "Terkin",
                "male",
                34,3);

        // Изменяем объект
        employeeDAO.updateAmountById(employee2);

        // Удаляем объект
        //employeeDAO.deleteById(employee2);

    }
}