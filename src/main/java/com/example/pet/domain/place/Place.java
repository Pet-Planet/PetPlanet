package com.example.pet.domain.place;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Place {

    @Id
    @Column(name = "place_id")
    private int id;

    @Column
    private String placeContent;

    @Column
    private double avgGrade;

    @Column
    private String placeTitle;

    @Column
    private String placeType;

    @Column
    private String reviewCnt;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name="region_id")
    private Region region;

}
