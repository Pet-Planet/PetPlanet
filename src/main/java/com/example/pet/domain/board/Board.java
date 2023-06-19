package com.example.pet.domain.board;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.member.Member;
import javax.persistence.*;

import com.example.pet.dto.board.BoardUpdateRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column
    private String writer;
    @Column
    private String category;
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int countView;

    @ManyToOne(fetch = FetchType.LAZY) // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name="member_id") // foreign key (memberId) references Member (id)
    @JsonBackReference
    private Member member;

    @OneToMany
    @JsonManagedReference
    private List<BoardComment> boardCommentList = new ArrayList<>();

    public void boardUpdate(BoardUpdateRequestDto requestDto) {
        if (requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
        if (requestDto.getContent() != null) {
            this.content = requestDto.getContent();
        }
        if (requestDto.getCategory() != null) {
            this.category = requestDto.getCategory();
        }
    }

    public void updateView (int countView) {
        this.countView = countView;
    }
}
