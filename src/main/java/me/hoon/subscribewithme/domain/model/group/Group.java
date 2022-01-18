package me.hoon.subscribewithme.domain.model.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.ui.ModelMap;
import reactor.core.publisher.Flux;

import java.util.List;

@Table("groups")
@Data
public class Group {

    @Id
    private Long groupId;

    private String groupName;

    private Integer maxSize;

    private GroupType groupType;

    public Group (GroupDto groupDto){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(groupDto,this);
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseGroup{

        private Long groupId;

        private String groupName;

        private GroupType groupType;

        private Integer maxSize;

        private List<Long> memberId;

        public ResponseGroup (Group group){

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(group,this);

        }

        public ResponseGroup (GroupDto group){

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(group,this);

        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestGroup{

        private String groupName;

        private GroupType groupType;


        public RequestGroup (GroupDto group){

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(group,this);

        }
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GroupDto{

        private Long groupId;

        private String groupName;

        private GroupType groupType;

        private Integer maxSize;

        private List<Long> memberId;

        public GroupDto (RequestGroup requestGroup){

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(requestGroup,this);

        }

        public GroupDto (Group group){

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(group,this);

        }

        public GroupDto (GroupService.MappedGroup mappedGroup){

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(mappedGroup,this);

        }
    }



}
