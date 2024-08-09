
package com.hoangtuyen04work.socialnetwork.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "follow")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "followerid", nullable = false)
    UserEntity follower;

    @ManyToOne
    @JoinColumn(name = "following", nullable = false)
    UserEntity following;

}