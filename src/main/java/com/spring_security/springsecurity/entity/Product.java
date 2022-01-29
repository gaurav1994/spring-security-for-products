package com.spring_security.springsecurity.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(
        name = "product_tbl"
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int price;
    private String description;
    private String addedby;
    private String addedbyLevel;

//    @ManyToOne
//    @JoinColumn(name = "likedProductByUserId")
//    private User userLiked;

    @Transient
    private boolean isLiked;
}
