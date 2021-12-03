package com.spring_security.springsecurity.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(
        name = "user_tbl",
              uniqueConstraints = @UniqueConstraint(name = "uk_1212", columnNames = "username")
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private String email;
    private int age;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private boolean isEnabled = true;

}
