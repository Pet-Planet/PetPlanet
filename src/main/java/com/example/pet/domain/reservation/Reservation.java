package com.example.pet.domain.reservation;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.member.Member;
import com.example.pet.domain.place.Place;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rev_id")
    private int Id;

    @Column(nullable = false)
    private String revName;

    @Column(nullable = false)
    private String phoneNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String time;

    private int amount;

    private int guests;

    private int pets;


    @ManyToOne(fetch = FetchType.LAZY)  //한명의 멤버는 여러개의 예약이 가능
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)  //하나의 숙소는 여러개의 예약을 가짐
    @JoinColumn(name = "place_id")
    @JsonBackReference
    private Place place;




}
