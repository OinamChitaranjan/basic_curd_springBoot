package org.coddingwallah.em.project;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class empController {
@Autowired
    EmployeeService employeeService ;
   


    @GetMapping("employee")
public List<Employee> getAllEmployees() {
   return employeeService.readEmployees();
}
@GetMapping("employee/{id}")
public Employee getEmployeesbyId(@PathVariable Long id) {
   return employeeService.readEmployee(id);
}


@PostMapping("employee")
public String createEmployee(@RequestBody Employee employee){
    
    employeeService.createEmployee(employee);
    return "saved";
}

@DeleteMapping("employee/{id}")
public boolean deleteEmployee(@PathVariable Long id)
{ employeeService.deleteEmployee(id);
    return true;
}

@PostMapping("employees/{id}")
public String postMethodName(@PathVariable Long id, @RequestBody Employee employee)
 { return employeeService.UpdateEmloyee(id, employee);
}


}
