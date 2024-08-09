
package com.hoangtuyen04work.socialnetwork.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post_like")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PostLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "postid", nullable = false)
    PostEntity post;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userid", nullable = false)
    UserEntity user;
}