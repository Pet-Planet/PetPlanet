package com.example.pet.domain.member;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.Role;
import com.example.pet.domain.board.Board;
import com.example.pet.domain.board.BoardComment;
import com.example.pet.domain.board.BookMark;
import com.example.pet.domain.reservation.Reservation;
import com.example.pet.domain.review.Review;
import com.example.pet.dto.member.MemberUpdateRequestDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity @EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberId;

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

    @Column
    @ColumnDefault("1") // 0 이면 탈퇴회원
    private int status;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<BoardComment> boardCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<BookMark> bookMarkDtoList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Friend> friends = new ArrayList<>();

    @Builder
    public Member(Long kakaoId, String kakaoProfileImg, String kakaoNickname,
                String kakaoEmail, Role role) {
        this.kakaoId = kakaoId;
        this.kakaoProfileImg = kakaoProfileImg;
        this.kakaoNickname = kakaoNickname;
        this.kakaoEmail = kakaoEmail;
        this.role = role;
    }

    public void memberUpdate(MemberUpdateRequestDto requestDto) {
        if (requestDto.getNickname() != null) {
            this.nickname = requestDto.getNickname();
        }
        if (requestDto.getAddress() != null) {
            this.address = requestDto.getAddress();
        }
        if (requestDto.getPetName() != null) {
            this.petName = requestDto.getPetName();
        }
        if (requestDto.getPetType() != null) {
            this.petType = requestDto.getPetType();
        }
    }

    // 해당 유저의 권한을 리턴한다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(()->{ return this.getRole().toString();});
        return collection;
    }

    @Override
    public String getPassword() {
        return this.getKakaoEmail();
    }

    @Override
    public String getUsername() {
        return this.getKakaoNickname();
    }

    // 계정 만료됐는지 확인 -> true 면 아니요.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠겼는지 확인 -> true 면 아니요.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 만료됐는지 확인 -> true 면 아니요.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 만료됐는지 확인 -> true 면 아니요.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
