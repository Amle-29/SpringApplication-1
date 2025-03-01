package com.project.spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false, unique = true)
    private String customerEmail;


    public void setName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getName() {
        return customerName;
    }

    public String getEmail() {
        return customerEmail;
    }
}
