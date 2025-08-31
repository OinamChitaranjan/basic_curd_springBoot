package org.coddingwallah.em.project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity //19732010
@Data
@Table(name ="emp")
public class EmployeeEntity {
    @Id
    
    private String name;
   
    private String phone;
    private String email;
    private String password;

    private String role; 
}
