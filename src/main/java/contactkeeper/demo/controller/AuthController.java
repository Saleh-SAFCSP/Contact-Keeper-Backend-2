package contactkeeper.demo.controller;


import contactkeeper.demo.DTO.Api;
import contactkeeper.demo.exceptions.ApiException;
import contactkeeper.demo.model.MyUser;
import contactkeeper.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @GetMapping ("/users")
    public ResponseEntity<?> getUsers(@AuthenticationPrincipal MyUser myUser){
        if (!myUser.getRole().equals("ADMIN")){
            return ResponseEntity.status(401).body(new Api("Only admin can get the users list",401));
        }
        return ResponseEntity.status(HttpStatus.OK).body(authService.getUsers());
    }
    @PostMapping("/login")
    public ResponseEntity<Api> login(){
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Welcome back",200));
    }

    @PostMapping("/register")
    public ResponseEntity<Api> register(@RequestBody @Valid MyUser myUser){
        authService.register(myUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Api("User registered",201));
    }
}
