import dao.EmployeeDAO;
import impl.EmployeeDaoImpl;

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

        Employee employee2 = new Employee("Vasily",
                "Terkin",
                "male",
                34,3);
        employeeDAO.create(employee2);

        // Получаем объект по id
        System.out.println(employeeDAO.readById(1));

        // Создаем список наполняя его объектами, которые получаем
        // путем вызова метода для получения всех элементов таблицы
        List<Employee> list = new ArrayList<>(employeeDAO.findAll());

        // Выведем список в консоль
        for(Employee employee: list) {
            System.out.println(employee);
        }



        // Изменяем объект
        //employeeDAO.updateAmountById(employee2);

        employee1.setAge(15);
        employee2.setName("Андрей");
        System.out.println(employee1);
        System.out.println(employee2);


        // Удаляем объект
        //employeeDAO.deleteById(employee1);

    }
}