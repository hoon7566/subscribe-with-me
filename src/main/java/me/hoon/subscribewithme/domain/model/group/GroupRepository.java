package me.hoon.subscribewithme.domain.model.group;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface GroupRepository extends R2dbcRepository<Group,Long> {

    @Query("SELECT * " +
            "FROM groups g " +
            "JOIN members m " +
            "on g.group_id = m.group_id " +
            "WHERE g.group_type = ?1")
    public <T> Flux<T> findGroupsByGroupType (GroupType groupType, Class<T> type);

    @Query("SELECT * " +
            "FROM groups g " +
            "JOIN members m " +
            "on g.group_id = m.group_id " +
            "WHERE g.group_type = 'AUTO'" +
            "AND g.max_size > (SELECT COUNT(member_id)" +
            "                  FROM MEMBER x " +
            "                  WHERE x.member_id = m.member_id)      ")
    public <T> Flux<T> findAutoGroup (GroupType groupType, Class<T> type);

}
