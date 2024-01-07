package com.carservice.thesis.controller;

import com.carservice.thesis.dto.ChangePasswordRequest;
import com.carservice.thesis.dto.RegisterRequest;
import com.carservice.thesis.dto.UserResponse;
import com.carservice.thesis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.createUser(request));
    }

    @GetMapping("/managers")
    public ResponseEntity<List<UserResponse>> getAllUnAssignedManagers() {
        List<UserResponse> employees = service.getAllUnAssignedManagers();
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        service.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer userId, @RequestBody RegisterRequest request) {
        UserResponse updatedUser = service.updateUser(userId, request);
        return ResponseEntity.ok(updatedUser);
    }


}
