package com.hoangtuyen04work.socialnetwork.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String userName;
    String userId;
    String DOB;
    String email;
    Set<RoleResponse> roles;
    LocalDate createdDate;
    public UserResponse hideSensitiveInfo() {
        this.email = null;
        this.createdDate = null;
        return this;
    }
}
