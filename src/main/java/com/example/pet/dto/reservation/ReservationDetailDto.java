package com.example.pet.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetailDto{

        private int revId;
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
        private String address;
}
