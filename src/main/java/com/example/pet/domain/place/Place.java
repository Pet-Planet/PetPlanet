package com.example.pet.domain.place;

import com.example.pet.domain.reservation.Reservation;
import com.example.pet.domain.review.Review;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private int placeId;

    @Column
    private String placeContent;

    @Column(precision = 2, scale = 1)
    private Double avgRating;

    @Column
    private String placeTitle;

    @Column
    private String placeType;

    @Column
    private Integer reviewCnt;

    @Column
    private int price;

    @Column
    private String imageUrl;

    @Column
    private String address;

    @OneToOne(cascade = CascadeType.ALL)  //한 장소는 하나의 지역만을 가진다.
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "place")
    @JsonManagedReference
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

}