package com.spring_security.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Role {
    @Id
    @SequenceGenerator(name = "role_seq",sequenceName = "role_sequence", initialValue = 401,allocationSize = 5)
    @GeneratedValue(generator = "role_seq", strategy = GenerationType.AUTO)
    private int id;
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
