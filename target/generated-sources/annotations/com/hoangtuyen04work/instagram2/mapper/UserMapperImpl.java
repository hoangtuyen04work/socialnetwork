package com.hoangtuyen04work.instagram2.mapper;

import com.hoangtuyen04work.instagram2.dto.request.UserRequest;
import com.hoangtuyen04work.instagram2.dto.response.RoleResponse;
import com.hoangtuyen04work.instagram2.dto.response.UserResponse;
import com.hoangtuyen04work.instagram2.entity.RoleEntity;
import com.hoangtuyen04work.instagram2.entity.UserEntity;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-23T22:05:05+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserResponse toUserResponse(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.roles( roleEntitySetToRoleResponseSet( userEntity.getRoles() ) );
        userResponse.userName( userEntity.getUserName() );
        userResponse.userId( userEntity.getUserId() );
        if ( userEntity.getDOB() != null ) {
            userResponse.DOB( DateTimeFormatter.ISO_LOCAL_DATE.format( userEntity.getDOB() ) );
        }
        userResponse.email( userEntity.getEmail() );

        return userResponse.build();
    }

    @Override
    public UserEntity toUserEntity(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.userId( userRequest.getUserId() );
        userEntity.password( userRequest.getPassword() );

        return userEntity.build();
    }

    protected Set<RoleResponse> roleEntitySetToRoleResponseSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleResponse> set1 = new LinkedHashSet<RoleResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleMapper.toRoleResponse( roleEntity ) );
        }

        return set1;
    }
}
