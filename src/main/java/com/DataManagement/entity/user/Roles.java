package com.DataManagement.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
//@ToString
@Table(name = "role")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    private RoleName roleName;
    private String roleDescription;

    @OneToMany(mappedBy = "roles")
    private List<User> users;
}
