package com.hoangtuyen04work.socialnetwork.mapper;

import com.hoangtuyen04work.socialnetwork.dto.request.FollowRequest;
import com.hoangtuyen04work.socialnetwork.entity.FollowEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FollowMapper {
    FollowEntity toFollowEntity(FollowRequest followRequest);
}
