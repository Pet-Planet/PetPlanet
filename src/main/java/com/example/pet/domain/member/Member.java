package com.example.pet.domain.member;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.Role;
import com.example.pet.domain.reservation.Reservation;
import com.example.pet.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "kakao_id")
    private Long kakaoId;

    @Column(name = "kakao_profile_img")
    private String kakaoProfileImg;

    @Column(name = "kakao_nickname")
    private String kakaoNickname;

    @Column(name = "kakao_email")
    private String kakaoEmail;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "create_time")
    // current_timestamp를 설정했다면 어노테이션 설정할 것
    @CreationTimestamp
    private Timestamp createTime;
    @Column
    private String nickname;

    @Column
    private String address;

    @Column
    private String petType;

    @Column
    private String petName;

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Member(Long kakaoId, String kakaoProfileImg, String kakaoNickname,
                String kakaoEmail, Role role) {
        this.kakaoId = kakaoId;
        this.kakaoProfileImg = kakaoProfileImg;
        this.kakaoNickname = kakaoNickname;
        this.kakaoEmail = kakaoEmail;
        this.role = role;
    }
}
