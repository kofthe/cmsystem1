package kofthe.cmsystem.service.concretes;

import kofthe.cmsystem.repository.UserRepository;
import kofthe.cmsystem.service.abstracts.UserService;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class UserManager implements UserService {

    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        return null;
    }
}
