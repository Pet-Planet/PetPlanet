package com.example.pet.dto.reservation;

import com.example.pet.domain.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {


    private int revId;
    private int memberId;
    private int placeId;
    private String placeName;
    private String revName;
    private String phoneNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String time;
    private int guests;
    private int pets;
    private int amount;
    private int status;


    //dto -> entity
    public Reservation toEntity() {

        return Reservation.builder()
                .Id(revId)
                .revName(revName)
                .phoneNum(phoneNum)
                .startDate(startDate)
                .time(time)
                .guests(guests)
                .pets(pets)
                .amount(amount)
                .build();
    }

}

