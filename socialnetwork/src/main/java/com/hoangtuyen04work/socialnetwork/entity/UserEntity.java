package com.hoangtuyen04work.socialnetwork.entity;


import com.hoangtuyen04work.socialnetwork.constant.State;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Builder
@EntityListeners(AuditingEntityListener.class) // Enable JPA Auditing for this entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String email;
    @Column(nullable = false, unique = true)
    String userId;
    @Column(nullable = false)
    String userName;
    @Column(nullable = false)
    String password;
    LocalDate DOB;
    @CreatedDate
    LocalDate createdDate;
    @LastModifiedDate
    LocalDate modifiedDate;
    LocalDate deletedDate;
    @Builder.Default
    @Column(nullable = false)
    private String state = State.active;

    @ManyToMany
    @JoinTable(name = "role_users")
    Set<RoleEntity> roles;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    Set<PostEntity> posts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    Set<CommentEntity> comments;
    @OneToMany(mappedBy = "follower",  cascade = CascadeType.REMOVE)
    Set<FollowEntity> followers;
    @OneToMany(mappedBy = "follower",  cascade = CascadeType.REMOVE)
    Set<FollowEntity> followees;

}
