package com.ismail.springbootlibrary.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="date")
    @CreationTimestamp
    private Date Date;

    @Column(name="book_id")
    private Long book_id;

    @Column(name="review_description")
    private String reviewDescription;

    @Column(name="rating")
    private int rating;


}
