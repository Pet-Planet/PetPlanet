package com.example.pet.domain.board;

import com.example.pet.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class BookMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private int bookmarkId;

    @ManyToOne // Many = BookMark, User = One 한명의 유저는 여러개의 게시글을 북마크 할 수있다.
    @JoinColumn(name="member_id")
    @JsonBackReference
    private Member member;

    @ManyToOne // Many = BookMark, Board = One 한 개의 게시글은 여러개의 북마크를 가질 수 있다.
    @JoinColumn(name="post_id")
    @JsonBackReference
    private Board board;
}
