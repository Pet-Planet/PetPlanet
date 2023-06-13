package com.example.pet.domain.board;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.member.Member;
import javax.persistence.*;

import com.example.pet.dto.board.BoardUpdateRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.net.URL;

@Entity @EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;
    @ManyToOne(fetch = FetchType.LAZY) // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name="member_id") // foreign key (memberId) references Member (id)
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

    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

    public void update(BoardUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.category = requestDto.getCategory();
    }
}
