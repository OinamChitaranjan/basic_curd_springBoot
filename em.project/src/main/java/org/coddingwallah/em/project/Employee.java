package org.coddingwallah.em.project;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
 
    @Column(unique = true)
    private String name;
    
    private String phone;
    private String email;

    private String password;

    private String role;  }