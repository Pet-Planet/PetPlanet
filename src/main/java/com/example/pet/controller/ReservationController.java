package com.example.pet.controller;

import com.example.pet.dto.reservation.ReservationDto;
import com.example.pet.dto.reservation.ReservationResortDto;
import com.example.pet.service.ReservationService;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;


       /*
      예약 작성 폼 (카페, 운동장, 식당)
        */
    @GetMapping("/reservation/a")
    public String reservationFormA(){

//        model.addAttribute("placeId", placeId);

        return "reservation-FormA";
    }



        /*
    예약 정보 입력 폼 (카페, 운동장, 식당 전용)
    (type=Unsupported Media Type, status=415) 에러 -> @ModelAttribute 사용
     */

    @PostMapping("/reservation/confirm/a")
    public String checkReservationFormA(@ModelAttribute("rev") ReservationDto reservationDto){

        reservationService.checkFormA(reservationDto);

        return "reservation-ConfirmA";

    }


    /*
        카페, 운동장, 식당 예약 API
     */
    @PostMapping("/reservation/a")
    public String saveReservationA(HttpServletRequest request, @ModelAttribute("rev") ReservationDto reservationDto){

        int memberId = memberService.getMember(request).getMemberId();

        reservationService.saveReservationA(memberId, reservationDto);

        //추후 수정: 예약 성공시 나의 예약 페이지로 리다이렉트
        return"reservation-success";


    }



    /*
   예약 작성 폼(숙소)
    */
    @GetMapping("/reservation/b")
    public String reservationFormB(){

//        model.addAttribute("placeId", placeId);

        return "reservation-FormB";
    }



    /*
    예약 정보 입력 폼 (숙소 전용)
    (type=Unsupported Media Type, status=415) 에러 -> @ModelAttribute 사용
     */

    @PostMapping("/reservation/confirm/b")
    public String checkReservationFormB(@ModelAttribute("rev") ReservationResortDto reservationResortDto){

        reservationService.checkFormB(reservationResortDto);

        return "reservation-ConfirmB";

    }



    /*
    숙소예약 API
     */
    @PostMapping("/reservation/b")
    public String saveReservationB(HttpServletRequest request, @ModelAttribute("rev") ReservationResortDto reservationResortDto){

        int memberId = memberService.getMember(request).getMemberId();

        reservationService.saveReservationB(memberId, reservationResortDto);

        //추후 수정: 예약 성공시 나의 예약 페이지로 리다이렉트
        return"reservation-success";


    }


}
