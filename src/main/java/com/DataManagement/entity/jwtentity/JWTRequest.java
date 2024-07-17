package com.DataManagement.entity.jwtentity;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JWTRequest {
    private String username;
    private String password;
}
