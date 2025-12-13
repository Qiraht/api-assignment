package com.dibimbing.api_assignment.dtos;

import lombok.Data;

@Data
public class UserReqLogin {
    private String email;
    private String password;
}
