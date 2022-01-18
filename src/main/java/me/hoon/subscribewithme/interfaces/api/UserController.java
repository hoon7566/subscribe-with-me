package me.hoon.subscribewithme.interfaces.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hoon.subscribewithme.domain.model.users.UserService;
import me.hoon.subscribewithme.domain.model.users.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class UserController {

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
