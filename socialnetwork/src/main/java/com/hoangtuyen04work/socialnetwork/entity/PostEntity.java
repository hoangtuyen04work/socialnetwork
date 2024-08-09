package com.hoangtuyen04work.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String title;
    @Column(columnDefinition = "TEXT")
    String content;
    @CreatedDate
    @Column(updatable = false)
    LocalDate createddate;
    @LastModifiedDate
    @CreatedDate
    LocalDate modifieddate;
    LocalDate deleteddate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    State state = State.active;
    public enum State {
        active,
        deleted
    }

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    UserEntity user;
    @OneToMany(mappedBy = "post",  cascade = CascadeType.REMOVE)
    Set<CommentEntity> comments;
    @OneToMany(mappedBy = "post",  cascade = CascadeType.REMOVE)
    Set<PostLikeEntity> postLikes;
}
