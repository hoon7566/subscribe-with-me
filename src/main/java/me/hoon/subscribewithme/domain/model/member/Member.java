package me.hoon.subscribewithme.domain.model.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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


    public Member (MemberDto memberDto){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(memberDto,this);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestMember{

        private Long memberId;
        private Long groupId;
        private String userId;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberDto{

        private Long memberId;
        private Long groupId;
        private String userId;

        public MemberDto(RequestMember requestMember){
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(requestMember,this);

        }


        public MemberDto(Member member){
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(member,this);

        }

    }



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseMember{

        private Long memberId;
        private Long groupId;
        private String userId;

        public ResponseMember(MemberDto memberDto){
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(memberDto,this);

        }
    }


}
