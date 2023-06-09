package com.example.pet.controller;

import com.example.pet.dto.reservation.ReservationDto;
import com.example.pet.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;


    /*
    예약 API
     */
    @PostMapping("/reservation")
    public String saveReservation(@RequestBody ReservationDto reservationDto){

        reservationService.saveReservation(reservationDto);

        return reservationDto.getRevName() + " success";
    }
}
