package me.hoon.subscribewithme.domain.member;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("members")
@Data
public class Member {

    @Id
    private Long memberId;

    @Column
    private Long groupId;

    @Column
    private String userId;



}
