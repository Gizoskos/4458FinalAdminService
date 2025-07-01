package com.gizem.adminservice.controller;


import com.gizem.adminservice.dto.AuthRequest;
import com.gizem.adminservice.dto.AuthResponse;
import com.gizem.adminservice.entity.AppUser;
import com.gizem.adminservice.entity.Role;
import com.gizem.adminservice.repository.AppUserRepository;
import com.gizem.adminservice.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final AppUserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, AppUserRepository repo,
                          PasswordEncoder encoder, JwtService jwtService) {
        this.authManager = authManager;
        this.repo = repo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.from(request.getRole().toString()));
        repo.save(user);
        return ResponseEntity.ok("Registered!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        AppUser user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getUsername(), user.getRole().name()); // Role eklenerek

        return ResponseEntity.ok(new AuthResponse(token, user.getId()));
    }
}
