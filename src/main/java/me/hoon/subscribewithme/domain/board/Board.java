package me.hoon.subscribewithme.domain.board;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("board")
@Data
public class Board {

    @Id
    private Long boardId;

    private String boardName;
}
