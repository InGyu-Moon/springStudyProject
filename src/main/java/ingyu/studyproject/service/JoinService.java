package ingyu.studyproject.service;

import ingyu.studyproject.dto.JoinDTO;
import ingyu.studyproject.entity.UserEntity;
import ingyu.studyproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(JoinDTO joinDTO){

        if(userRepository.existsByUsername(joinDTO.getUsername())){
            return;
        }

        UserEntity entity = UserEntity.builder()
                .username(joinDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(joinDTO.getPassword()))
                .role("ROLE_USER")
                .build();
        userRepository.save(entity);
    }
}
