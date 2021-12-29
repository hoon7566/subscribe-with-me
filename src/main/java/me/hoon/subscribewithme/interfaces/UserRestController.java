package me.hoon.subscribewithme.interfaces;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hoon.subscribewithme.domain.users.UserService;
import me.hoon.subscribewithme.domain.users.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("/users")
    public Flux<ResponseEntity<Users.ResponseUser>> getUsers(){

       return userService.getUsers()
                .map(userDto -> ResponseEntity.status(HttpStatus.OK)
                        .body(new Users.ResponseUser(userDto)));
    }

    @PostMapping("/users")
    public Mono<ResponseEntity<Users.ResponseUser>> createUsers(@RequestBody Users.RequestUser requestUser){
        Users.UserDto reqUserDto = new Users.UserDto(requestUser);
        return userService.createUser(reqUserDto)
                .map(userDto -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new Users.ResponseUser(userDto)));
    }

}
