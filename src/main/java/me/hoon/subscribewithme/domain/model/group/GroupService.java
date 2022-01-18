package me.hoon.subscribewithme.domain.model.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hoon.subscribewithme.domain.model.member.MemberRepository;
import me.hoon.subscribewithme.domain.model.users.Users;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;

    public Flux<Group.GroupDto> groupList (GroupType groupType){

        Flux<MappedGroup> mappedGroupFlux = groupRepository.findGroupsByGroupType(groupType,MappedGroup.class)
                .doFirst(() -> log.info("=======find groupList"))
                .doOnError(throwable -> throwable.printStackTrace());


        return mappedGroupFlux.map(Group.GroupDto::new);
    }

    public Mono<Group.GroupDto> createGroup (Group.GroupDto  groupDto){

        Mono<Group> group = groupRepository.save(new Group(groupDto))
                .doFirst(() -> log.info("=======createGroup"))
                .doOnError(throwable -> throwable.printStackTrace());

        return group.map(Group.GroupDto::new);
    }

    public Flux<Group.GroupDto> findAutoGroup(){

        Flux<MappedGroup> groupsByGroupType = groupRepository.findAutoGroup(GroupType.AUTO, MappedGroup.class);

        return groupsByGroupType.map(mappedGroup -> new Group.GroupDto(mappedGroup));
    }

    @NoArgsConstructor
    @Data
    @AllArgsConstructor
    public static class MappedGroup{
         Long groupId;
         String groupName;
         GroupType groupType;
         List<Long> memberId;
    }

}
