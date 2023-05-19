package com.example.pet.domain.board;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import java.net.URL;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;
    @ManyToOne // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name="user_id") // foreign key (userId) references User (id)
    private Member member;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String category;
    @Column
    private URL imageUrl1;
    @Column
    private URL imageUrl2;
    @Column
    private URL imageUrl3;
    @Column
    private int status; //글 삭제 여부

}
