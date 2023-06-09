package com.example.pet.domain.review;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.member.Member;
import com.example.pet.domain.place.Place;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity @EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int Id;

    @Column
    private int rating; //평점 1~5

    @Column(nullable = false)
    private String content; //리뷰 내용

    @ManyToOne(fetch = FetchType.LAZY)  //한명의 멤버는 여러개의 리뷰 가능
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)  //하나의 숙소는 여러개의 리뷰를 가짐
    @JoinColumn(name = "place_id")
    @JsonBackReference
    private Place place;


    public void changeRating(int rating){
        this.rating = rating;
    }

    public void changeText(String content){
        this.content = content;
    }
}
