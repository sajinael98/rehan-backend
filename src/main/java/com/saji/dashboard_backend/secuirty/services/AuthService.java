package com.saji.dashboard_backend.secuirty.services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saji.dashboard_backend.modules.user_managment.dtos.ChangePasswordRequest;
import com.saji.dashboard_backend.modules.user_managment.entities.Token;
import com.saji.dashboard_backend.modules.user_managment.entities.User;
import com.saji.dashboard_backend.modules.user_managment.repositories.TokenRepo;
import com.saji.dashboard_backend.modules.user_managment.repositories.UserRepo;
import com.saji.dashboard_backend.secuirty.dtos.SignInRequest;
import com.saji.dashboard_backend.secuirty.dtos.SignInResponse;
import com.saji.dashboard_backend.secuirty.utils.PermissionUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;

    private final TokenRepo tokenRepo;

    private final UserRepo userRepo;

    private final JwtService jwtService;

    private final PasswordEncoder encoder;

    public SignInResponse signIn(SignInRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        var user = (User) auth.getPrincipal();

        String token = jwtService.generateToken(user);
        SignInResponse response = new SignInResponse();
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setToken(token);
        response.setId(user.getId());
        response.setRoles(user.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toSet()));
        Set<String> permissions = new HashSet<>();
        user.getRoles().forEach(role -> {
            role.getPermissions().forEach(per -> {
                if (per.isCreateR()) {
                    permissions.add(
                            PermissionUtils.customizePermission(per.getEntity(), "create"));
                }
                if (per.isReadR()) {
                    permissions.add(
                            PermissionUtils.customizePermission(per.getEntity(), "read"));
                }
                if (per.isEditR()) {
                    permissions.add(
                            PermissionUtils.customizePermission(per.getEntity(), "update"));
                }
                if (per.isDeleteR()) {
                    permissions.add(
                            PermissionUtils.customizePermission(per.getEntity(), "delete"));
                }
            });
        });
        response.setPermissions(permissions);
        revokeAllUserTokens(user);
        saveUserToken(user, token);

        return response;
    }

    @Transactional
    public void changePassword(Long id, ChangePasswordRequest req) {
        System.out.println(req.getPassword());
        String encodedPassword = encoder.encode(req.getPassword());
        User user = userRepo.findById(id).get();
        user.setPassword(encodedPassword);
        
        userRepo.save(user);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepo.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token();
        token.setUser(user);
        token.setToken(jwtToken);

        tokenRepo.save(token);
    }
}
