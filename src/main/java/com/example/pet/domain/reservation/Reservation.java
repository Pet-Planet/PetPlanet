package com.example.pet.domain.reservation;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.member.Member;
import com.example.pet.domain.place.Place;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rev_id")
    private int Id;

    @Column(nullable = false)
    private String revName;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;

    @ColumnDefault("0")
    private int totalPrice;

    @ColumnDefault("0")
    private int status; //예약 취소 여부, cancle: 1

    @ManyToOne(fetch = FetchType.LAZY)  //한명의 멤버는 여러개의 예약이 가능
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)  //하나의 숙소는 여러개의 예약을 가짐
    @JoinColumn(name = "place_id")
    private Place place;




}
