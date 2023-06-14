package com.example.pet.controller;

import com.example.pet.dto.reservation.ReservationDto;
import com.example.pet.service.ReservationService;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;


    /*
   예약 작성 폼
    */
    @GetMapping("/reservation")
    public String reservationForm(){

        return "reservationForm";
    }


       /*
    날짜, 인원 입력 폼
    (type=Unsupported Media Type, status=415) 에러 -> @ModelAttribute 사용
     */

    @PostMapping("/reservation/check")
    public String checkReservationForm(@ModelAttribute("rev") ReservationDto reservationDto){

        reservationService.checkForm(reservationDto);

        return "reservationConfirm";
    }


    /*
    예약 API
     */
    @PostMapping("/reservation")
    public String saveReservation(HttpServletRequest request, @ModelAttribute("rev") ReservationDto reservationDto){

        int memberId = memberService.getMember(request).getMemberId();

        reservationService.saveReservation(memberId, reservationDto);

        return"success";
    }


}
