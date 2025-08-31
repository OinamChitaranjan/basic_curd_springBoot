package org.coddingwallah.em.project;
import java.util.List;




public interface EmployeeService {
    
String createEmployee(Employee employee);
 List<Employee> readEmployees();
boolean deleteEmployee(String name);
String UpdateEmloyee(String name, Employee employee);

Employee readEmployee(String name);

}
