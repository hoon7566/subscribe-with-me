package me.hoon.subscribewithme.domain.model.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hoon.subscribewithme.domain.model.group.Group;
import me.hoon.subscribewithme.domain.model.group.GroupRepository;
import me.hoon.subscribewithme.domain.model.group.GroupService;
import me.hoon.subscribewithme.domain.model.group.GroupType;
import me.hoon.subscribewithme.domain.model.users.Users;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final GroupService groupService;
    private final MemberRepository memberRepository;

    public Flux<Member.MemberDto> getMembers(){

        return memberRepository.findAll()
                .doFirst(() -> log.info("=======getMembers"))
                .doOnError(throwable -> throwable.printStackTrace())
                .map(member -> new Member.MemberDto(member));
    }

    public void createMember(Member.MemberDto memberDto, GroupType groupType){
        if(groupType==GroupType.AUTO){
            //group서비스에서 찾아서 참가시킨다.

            Flux<Group.GroupDto> groupDto = groupService.findAutoGroup();

            Group.GroupDto autoGroup = groupDto.last()
                    .blockOptional()
                    .orElse(groupService.createGroup(Group.GroupDto.builder().build()).block());

            Long groupId = autoGroup.getGroupId();
            Member member = new Member(memberDto);
            member.setGroupId(groupId);
            memberRepository.save(member);

        }else{
            memberRepository.save(new Member(memberDto));
        }


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
