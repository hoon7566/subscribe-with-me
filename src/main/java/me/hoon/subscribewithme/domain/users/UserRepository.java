package me.hoon.subscribewithme.domain.users;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface UserRepository extends R2dbcRepository<Users,Long> {

    public Mono<Users> findByUserEmail(String userEmail);
}
