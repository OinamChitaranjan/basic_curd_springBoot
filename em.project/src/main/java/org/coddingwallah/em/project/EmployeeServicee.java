package org.coddingwallah.em.project;

import java.util.*;
//import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServicee  implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    List<Employee> Employees = new ArrayList<>();
    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(employee,entity);
        employeeRepository.save( entity);
    Employees.add(employee);
    return "Saved";
    }

    @Override
    public List<Employee> readEmployees() {
     List<EmployeeEntity> EntityList=employeeRepository.findAll();
     List<Employee> employees = new ArrayList<>();
     for(EmployeeEntity employeeEntity: EntityList){
        Employee emp = new Employee();
        emp.setName(employeeEntity.getName());
        emp.setEmail(employeeEntity.getEmail());
        emp.setPhone(employeeEntity.getPhone());
        emp.setId(employeeEntity.getId());
        employees.add(emp);
     }
     return employees;
    }

    
    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity entit = employeeRepository.findById(id).get();
        employeeRepository.delete(entit);
       //Employees= Employees.stream().filter(emp->emp.getId()!=id).collect(Collectors.toList());
       return true;
    }

    @Override
    public String UpdateEmloyee(Long id, Employee employee) {
        
        EmployeeEntity exestingEmp= employeeRepository.findById(id).get();

        exestingEmp.setEmail(employee.getEmail());
        
        exestingEmp.setName(employee.getName());
        exestingEmp.setPhone(employee.getPhone());

        employeeRepository.save(exestingEmp);
return "Updated";
    }

    @Override
    public Employee readEmployee(Long id) {
        
        EmployeeEntity entity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(entity,employee);
        return employee;
    }
    
}
