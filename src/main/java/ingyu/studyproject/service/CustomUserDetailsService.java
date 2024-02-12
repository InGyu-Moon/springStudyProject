package ingyu.studyproject.service;

import ingyu.studyproject.dto.CustomUserDetails;
import ingyu.studyproject.entity.UserEntity;
import ingyu.studyproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userData = userRepository.findByUsername(username);
        // 아이디가 존재
        if(userData != null){
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
