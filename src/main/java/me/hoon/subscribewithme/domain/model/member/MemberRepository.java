package me.hoon.subscribewithme.domain.model.member;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends R2dbcRepository<Member,Long> {
}
