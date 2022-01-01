package me.hoon.subscribewithme.domain.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Flux<Users.UserDto> getUsers(){

        return userRepository.findAll()
                .doFirst(() -> log.info("=======find users"))
                .doOnError(throwable -> throwable.printStackTrace())
                .map(users -> new Users.UserDto(users));
    }

    public Mono<Users.UserDto> createUser(Users.UserDto userDto){
        Users user = new Users(userDto);
        log.info("userDTO ====="+userDto.toString());
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        return userRepository.save(user)
                .doFirst(() -> log.info("=======create users"))
                .doOnError(throwable -> throwable.printStackTrace())
                .map(users -> new Users.UserDto(users));
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        return  userRepository.findByUserEmail(userEmail)
                .blockOptional()
                .map(users -> new User(users.getUserEmail()
                        ,users.getUserPassword()
                        ,new ArrayList<>()))
                .orElseThrow(() -> new UsernameNotFoundException("userEmail ->" +userEmail +"Not Found"));

    }
}
