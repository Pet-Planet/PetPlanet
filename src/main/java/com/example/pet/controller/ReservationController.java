package com.example.pet.controller;

import com.example.pet.dto.reservation.ReservationDto;
import com.example.pet.service.ReservationService;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;

    /*
    예약 API
     */
    @PostMapping("/reservation")
    public String saveReservation(HttpServletRequest request, @RequestBody ReservationDto reservationDto){

        int memberId = memberService.getMember(request).getMemberId();

        reservationService.saveReservation(memberId, reservationDto);

        return reservationDto.getRevName() + " success";
    }
}
