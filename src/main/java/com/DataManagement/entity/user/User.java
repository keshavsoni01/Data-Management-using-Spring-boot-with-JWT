package com.DataManagement.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private byte[] profilePic;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String contactNo;
    private String password;
    private String resetPassword;
    private boolean termsAndConditions;
    private String verificationCode;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    private Date accCreatedAt;
    private Date lastUpdatedBy;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Roles roles;


    // getter setter for is_deleted
    public boolean isDeleted() {
        return this.isDeleted;
    }
    public void setDeleted()     {
        this.isDeleted = true;
    }
}
