package org.coddingwallah.em.project;


import java.rmi.server.ExportException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.HttpStatus;



@RestController
@Slf4j
public class empController {
@Autowired
AuthenticationManager authenticationManager;
@Autowired
UserDetailsServiceImp userDetailsService;
@Autowired
    EmployeeService employeeService ;
@Autowired
JwtUtil jwtUtil;   

@Autowired
private  EmployeeRepository employeeRepository;
@Autowired
private  PasswordEncoder passwordEncoder;
EmployeeEntity entity = new EmployeeEntity();
    @PostMapping("/signup")
    public String signup(@RequestBody Employee employee) {
        // Hash the password before saving
       try{ entity.setName(employee.getName());
        entity.setPassword(passwordEncoder.encode(employee.getPassword()));
        entity.setRole("USER"); // default role

        
       }
        catch(Exception e){
            return "p" ;
        }
        employeeRepository.save(entity);
         return "User registered successfully!";
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Employee user) {
        UserDetails userDetails;
        String jwt;
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
            
           userDetails = userDetailsService.loadUserByUsername(user.getName());
           
        }catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        } 
        
        jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @GetMapping("auth/employee")
public List<Employee> getAllEmployees() {
   
    return employeeService.readEmployees();}
    
@GetMapping("auth/employee/{name}")
public Employee getEmployeesbyId(@PathVariable String name) {
   return employeeService.readEmployee(name);
}


@PostMapping("auth/employee")
public String createEmployee(@RequestBody Employee employee){
    
    employeeService.createEmployee(employee);
    return "saved";
}

@DeleteMapping("auth/employee/{name}")
public boolean deleteEmployee(@PathVariable String name)
{ employeeService.deleteEmployee(name);
    return true;
}

@PostMapping("auth/employees/{name}")
public String postMethodName(@PathVariable String name, @RequestBody Employee employee)
 { return employeeService.UpdateEmloyee(name, employee);
}


}
