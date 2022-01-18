package me.hoon.subscribewithme.interfaces.api;

import lombok.RequiredArgsConstructor;
import me.hoon.subscribewithme.domain.model.group.Group;
import me.hoon.subscribewithme.domain.model.group.GroupService;
import me.hoon.subscribewithme.domain.model.group.GroupType;
import me.hoon.subscribewithme.domain.model.member.Member;
import me.hoon.subscribewithme.domain.model.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public Flux<ResponseEntity<Member.ResponseMember>> getMembers(){

        return memberService.getMembers()
                .map(memberDto -> ResponseEntity.ok(new Member.ResponseMember(memberDto)));
    }

    @PostMapping("/member/{groupType}")
    public Mono<ResponseEntity<Member.ResponseMember>> createMember(@PathVariable GroupType groupType,
                                                                    @RequestBody Member.RequestMember requestMember){
        //참가요청을 함.
        // Auto일시 group중에 auto그룹을 찾아서 참가하게 됨.
        // auto가 아닐 시 groupId까지 받고 member생성
        Member.MemberDto memberDto = new Member.MemberDto(requestMember);
        memberService.createMember(memberDto, groupType);
        return null;
    }
}
