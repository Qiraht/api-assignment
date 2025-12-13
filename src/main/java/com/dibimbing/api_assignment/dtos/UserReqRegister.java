package com.dibimbing.api_assignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReqRegister {
    private String username;
    private String password;
    private String email;
    private String address;
}
