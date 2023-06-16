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
public class ReservationResortDto {

    private int revId;
    private int memberId;
    private int placeId;
    private String placeName;
    private String revName;
    private String phoneNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;
    private int guests;
    private int amount;
    private int status;



    //dto -> entity
    public Reservation toEntity(){

        return Reservation.builder()
                .Id(revId)
                .revName(revName)
                .phoneNum(phoneNum)
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .amount(amount)
                .guests(guests)
                .build();

    }

}
