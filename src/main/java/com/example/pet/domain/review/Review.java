package com.example.pet.domain.review;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.member.Member;
import com.example.pet.domain.place.Place;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int Id;

    @Column
    private int rating; //평점 1~5

    @Column(nullable = false)
    private String Content; //리뷰 내용

    @ManyToOne(fetch = FetchType.LAZY)  //한명의 멤버는 여러개의 리뷰 가능
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)  //하나의 숙소는 여러개의 리뷰를 가짐
    @JoinColumn(name = "place_id")
    private Place place;
}
