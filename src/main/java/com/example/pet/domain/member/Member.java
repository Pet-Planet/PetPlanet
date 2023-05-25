package com.example.pet.domain.member;

import com.example.pet.domain.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String password;

    private String petType;

    private String petName;

    private URL imgUrl;

    private String status;  //탈퇴여부


    @OneToMany(mappedBy = "reservation")
    private List<Reservation> goals = new ArrayList<>();

}
