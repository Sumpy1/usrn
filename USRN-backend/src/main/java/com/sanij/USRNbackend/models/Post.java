package com.sanij.USRNbackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    private boolean isVerified;

    private LocalDateTime createdAt;

    public void setDescription(String desc) {
        this.body = desc;
    }

    public void setIsVerified(boolean verification) {this.isVerified = verification;}

    @NotNull
    @ManyToOne
    @JoinColumn(name =  "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

}
