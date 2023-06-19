package com.example.pet.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetailDto{

        private int revId;
        private int placeId;
        private int memberId;

        private String placeName;
        private String revName;
        private String phoneNum;

        private LocalDateTime revDate;
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
