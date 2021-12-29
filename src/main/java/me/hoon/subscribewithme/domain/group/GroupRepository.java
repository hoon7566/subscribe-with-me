package me.hoon.subscribewithme.domain.group;

import me.hoon.subscribewithme.domain.member.Member;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends R2dbcRepository<Group,Long> {
}
