package com.DataManagement.dto.userDTO;

import com.DataManagement.entity.user.RoleName;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO {
    private RoleName roleName;
    private String roleDescription;
}
