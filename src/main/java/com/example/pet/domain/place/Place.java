package com.example.pet.domain.place;

import com.example.pet.domain.reservation.Reservation;
import com.example.pet.domain.review.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Place {

    @Id
    @Column(name = "place_id")
    private int placeId;

    @Column
    private String placeContent;

    @Column(precision = 2, scale = 1)
    private BigDecimal avgRating;

    @Column
    private String placeTitle;

    @Column
    private String placeType;

    @Column
    private String reviewCnt;

    @Column
    private int price;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name="region_id")
    private Region region;

    @OneToMany(mappedBy = "place")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    private List<Review> reviews = new ArrayList<>();

}
