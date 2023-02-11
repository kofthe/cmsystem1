package kofthe.cmsystem.JWT;

import kofthe.cmsystem.model.User;
import kofthe.cmsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User userDetail;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("inside loadbyusername", email);
        userDetail = userRepository.findByEmailId(email);
        if (!Objects.isNull(userDetail)){
            return new org.springframework.security.core.userdetails.User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());

        }else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }

    public User getUserDetail() {
        return userDetail;
    }
}
