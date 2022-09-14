package contactkeeper.demo.service;


import contactkeeper.demo.model.MyUser;
import contactkeeper.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public List<MyUser> getUsers(){
        return userRepository.findAll();
    }

    public void register(MyUser myUser){
        String hashedPassword= new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashedPassword);
        myUser.setRole("User");
        userRepository.save(myUser);
    }
}
