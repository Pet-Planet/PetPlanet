package com.example.pet.domain.member;

import com.example.pet.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity @EntityListeners(AuditingEntityListener.class)
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

    @ManyToOne(fetch = FetchType.LAZY) // Many = Friend, User = One 한명의 유저는 여러 친구를 둘 수 있다.
    @JoinColumn(name="to_id") // foreign key (toId) references Member (id)
    @JsonBackReference
    private Member member;

    @Column(name = "from_id")
    private int fromId;

    @Column(name = "we_id")
    @ColumnDefault("0")
    private int weId;

    @Column(name = "kakao_profile_img")
    private String kakaoProfileImg;

    @Column
    private String nickname;

    @Column(name = "create_time")
    // current_timestamp를 설정했다면 어노테이션 설정할 것
    @CreationTimestamp
    private Timestamp createTime;
}
