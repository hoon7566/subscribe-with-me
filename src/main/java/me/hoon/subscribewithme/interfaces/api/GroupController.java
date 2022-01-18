package me.hoon.subscribewithme.interfaces.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.hoon.subscribewithme.domain.model.group.Group;
import me.hoon.subscribewithme.domain.model.group.GroupService;
import me.hoon.subscribewithme.domain.model.group.GroupType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/group/{groupType}")
    public Flux<ResponseEntity<Group.ResponseGroup>> groupList(@PathVariable GroupType groupType){
        return groupService.groupList(groupType)
                .map(groupDto -> ResponseEntity.status(HttpStatus.OK)
                    .body(new Group.ResponseGroup(groupDto)));

    }

    @PostMapping("/group")
    public Mono<ResponseEntity<Group.ResponseGroup>> createGroup(@RequestBody Group.RequestGroup requestGroup){
        Group.GroupDto groupDto = new Group.GroupDto(requestGroup);
        return groupService.createGroup(groupDto)
                .map(createdGroup -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new Group.ResponseGroup(createdGroup)));

    }
}
