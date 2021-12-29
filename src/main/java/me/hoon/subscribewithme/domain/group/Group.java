package me.hoon.subscribewithme.domain.group;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("groups")
@Data
public class Group {

    @Id
    private Long groupId;

    private Long memberId;

    private String groupName;

    private GroupType type;

}
