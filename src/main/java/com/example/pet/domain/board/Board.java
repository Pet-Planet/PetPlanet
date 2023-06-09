package com.example.pet.domain.board;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.member.Member;
import javax.persistence.*;

import com.example.pet.dto.board.BoardUpdateRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.net.URL;
import java.sql.Timestamp;

@Entity @EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;
    @ManyToOne // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name="member_id") // foreign key (userId) references User (id)
    @JsonBackReference
    private Member member;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String writer;
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

    @Builder
    public Board(String title, String content, String category, String writer, Member member) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.writer = writer;
        this.member = member;
    }

    public void update(BoardUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.category = requestDto.getCategory();
    }
}
