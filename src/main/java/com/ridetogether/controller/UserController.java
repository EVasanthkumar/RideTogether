
package com.ridetogether.controller;

import com.ridetogether.entity.User;
import com.ridetogether.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ridetogether.dto.RegisterRequest;
import com.ridetogether.dto.RegisterResponse;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(
            @Valid @RequestBody RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());

        User registeredUser = userService.registerUser(user);

        RegisterResponse response = new RegisterResponse(
                registeredUser.getId(),
                registeredUser.getName(),
                registeredUser.getEmail(),
                registeredUser.getPhone(),
                registeredUser.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
