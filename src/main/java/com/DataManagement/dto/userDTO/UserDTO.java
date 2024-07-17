package com.DataManagement.dto.userDTO;


import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private int id;
    private String profilePic;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String contactNo;
    private String password;
    private String resetPassword;
    private boolean termsAndConditions;
    private String verificationCode;
    private boolean isDeleted;
    private Date accCreatedAt;
    private Date lastUpdatedBy;
    private RoleDTO roles;
}
