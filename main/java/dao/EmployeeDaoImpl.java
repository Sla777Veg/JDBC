package dao;

import model.Employee;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.HibernateSessionFactoryUtil;

public class EmployeeDaoImpl implements EmployeeDAO{

    private Connection connection;

    public EmployeeDaoImpl(Connection connection) {this.connection = connection;}


    @Override
    public List<Employee> findAll() {
        //List<Employee> employees = new ArrayList<>();
        List<Employee> employees = (List<Employee>)  HibernateSessionFactoryUtil
                .getSessionFactory().openSession().createQuery("From Employee").list();
        return employees;
    }

    @Override
    public void create(Employee employee) {
        if (employee.getCity() !=0 ) {
            employee.setCity(0);
        }
        // В ресурсах блока try создаем объект сессии с помощью нашего конфиг-файла
        // И открываем сессию
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            // Создаем транзакцию и начинаем ее
            Transaction transaction = session.beginTransaction();
            // вызываем на объекте сессии метод save
            // данный метод внутри себя содержит необходимый запрос к базе
            // для создания новой строки
            session.save(employee);
            // Выполняем коммит, то есть сохраняем изменения,
            // которые совершили в рамках транзакции
            transaction.commit();
        }
    }
    @Override

    public Employee readById(int id) {
        // С помощью конфиг-файла получаем сессию, открываем ее
        // и через метод get получаем объект
        // В параметре метода get нужно указать объект какого класса нам нужен
        // и его id
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }



    @Override
    public void updateAmountById(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            // Для обновления данных нужно передать в конструктор
            // объект с актуальными данными
            session.update(employee);
            transaction.commit();
        }
    }

    @Override
    public void deleteById(Employee employee) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Для удаления объекта из таблицы нужно передать его в метод delete
            session.delete(employee);
            transaction.commit();
        }
    }
}
