package com.example.pet.domain.member;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.Role;
import com.example.pet.domain.board.Board;
import com.example.pet.domain.board.BoardComment;
import com.example.pet.domain.board.BookMark;
import com.example.pet.domain.message.Message;
import com.example.pet.domain.reservation.Reservation;
import com.example.pet.domain.review.Review;
import com.example.pet.dto.member.MemberUpdateRequestDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity @EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

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

//    일반 로그인을 위한 컬럼
    @Column(length = 45, unique = true)
    private String email; // 로그인 하는 아이디
    @Column(length = 100)
    private String password;
    @Column
    private String refreshToken;
    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
//
// 회원탈퇴 시 작성한 예약, 리뷰, 게시글, 댓글 모두 삭제
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BoardComment> boardCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BookMark> bookMarkDtoList = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    @JsonManagedReference
    private List<Message> receive = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    @JsonManagedReference
    private List<Message> send = new ArrayList<>();

    @OneToMany(mappedBy = "fromMember", cascade = CascadeType.ALL)
    private List<Friend> sentFriendRequests;

    @OneToMany(mappedBy = "toMember", cascade = CascadeType.ALL)
    private List<Friend> receivedFriendRequests;

//    @Builder
//    public Member(Long kakaoId, String kakaoProfileImg, String kakaoNickname,
//                String kakaoEmail, Role role) {
//        this.kakaoId = kakaoId;
//        this.kakaoProfileImg = kakaoProfileImg;
//        this.kakaoNickname = kakaoNickname;
//        this.kakaoEmail = kakaoEmail;
//        this.role = role;
//    }
//    @Builder
//    public Member(String email, String nickname, String password, Role role) {
//        this.email = email;
//        this.nickname = nickname;
//        this.password = password;
//        this.role = role;
//    }

    // 정보 수정
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

    public void updatePassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }
    // 비밀번호 병경, 회원 탈퇴 시, 비밀번호를 확인하며, 이때 비밀번호의 일치여부를 판단
    public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword) {
        return passwordEncoder.matches(checkPassword, getPassword());
    }
    // 회원가입시 user 권한을 부여하는 메소드
    public void addUserAuthority() {
        this.role = Role.USER;
    }
    private boolean friendRequested;
}
