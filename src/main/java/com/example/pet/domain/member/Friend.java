package com.example.pet.domain.member;

import com.example.pet.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private int friendId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_id")
    @JsonBackReference
    private Member fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_id")
    @JsonBackReference
    private Member toMember;

    @Column(name = "we_id")
    @ColumnDefault("0")
    private int weId;

    @Column(name = "kakao_profile_img")
    private String kakaoProfileImg;

    @Column
    private String nickname;

    @Column(name = "kakao_email")
    private String kakaoEmail;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;
}