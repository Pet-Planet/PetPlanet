package com.example.pet.domain.board;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.member.Member;
import javax.persistence.*;

import com.example.pet.dto.board.BoardSaveRequestDto;
import com.example.pet.dto.board.BoardUpdateRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

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
    @ManyToOne(fetch = FetchType.LAZY) // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name="member_id") // foreign key (userId) references User (id)
    @JsonBackReference
    private Member member;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column
    private String writer;
    @Column(nullable = false)
    private String category;
    @Column
    private URL imageUrl1;
    @Column
    private URL imageUrl2;
    @Column
    private URL imageUrl3;
    @Column
    private int status; //글 삭제 여부

    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }
    @Builder
    public Board(String title, String content, String category, String writer, Member member) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.writer = writer;
        if(this.member != null)
            member.getBoards().remove(this);
    }

    public void update(BoardUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.category = requestDto.getCategory();
    }
}
