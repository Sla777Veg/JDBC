package dao;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    void create(Employee employee);
    Employee readById(int id);
    void updateAmountById(Employee employee);
    void deleteById(Employee employee);
}
