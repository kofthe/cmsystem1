package kofthe.cmsystem.service.concretes;

import kofthe.cmsystem.constents.CafeConstents;
import kofthe.cmsystem.model.User;
import kofthe.cmsystem.repository.UserRepository;
import kofthe.cmsystem.service.abstracts.UserService;
import kofthe.cmsystem.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("inside signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userRepository.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userRepository.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity("Success!", HttpStatus.OK);
                }else {
                    return CafeUtils.getResponseEntity("email already exists", HttpStatus.BAD_REQUEST);
                }

            } else {
                return CafeUtils.getResponseEntity(CafeConstents.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    return CafeUtils.getResponseEntity(CafeConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
       if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email")
                && requestMap.containsKey("password")) {
           return true;
       }
       return false;
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus(requestMap.get("status"));
        user.setRole("user");
        return user;
    }

}
