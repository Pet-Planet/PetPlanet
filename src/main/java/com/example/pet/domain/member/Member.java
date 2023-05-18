package com.example.pet.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {


    @Id
    @Column(name = "member_id")
    private String id;

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





}
