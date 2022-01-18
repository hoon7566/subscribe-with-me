package me.hoon.subscribewithme.domain.model.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hoon.subscribewithme.domain.model.common.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import org.modelmapper.ModelMapper;

import org.modelmapper.convention.MatchingStrategies;

@Table("users")
@Data
@AllArgsConstructor
@Builder
@Slf4j
public class Users extends BaseEntity {

    @Id
    private Long userId;

    @Column("user_password")
    private String userPassword;

    @Column("user_email")
    private String userEmail;

    public Users(){
        super();
    }


    public Users(UserDto userDto){
        super();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.map(userDto , this);

    }


    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class UserDto {

        private Long userId;
        private String userPassword;
        private String userEmail;

        public UserDto(Users users){
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map( users, this);

        }

        public UserDto(RequestUser requestUser){
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map( requestUser, this);

        }
    }

    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class ResponseUser {

        private Long userId;
        private String userEmail;

        public ResponseUser(UserDto userDto){
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            modelMapper.map( userDto, this);

        }
    }

    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class RequestUser {

        private String userPassword;
        private String userEmail;

        public RequestUser(UserDto userDto){
            log.info("userDto: "+ userDto.toString());

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            modelMapper.map(userDto, this);

        }
    }
}
