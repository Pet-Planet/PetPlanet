package com.example.pet.controller;

import com.example.pet.domain.place.Place;
import com.example.pet.dto.reservation.ReservationDto;
import com.example.pet.dto.reservation.ReservationResortDto;
import com.example.pet.repository.PlaceRepository;
import com.example.pet.service.ReservationService;
import com.example.pet.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;
    private final PlaceRepository placeRepository;


       /*
      예약 작성 폼 placeType==hetel -> reservation-FormB.jsp로 이동
        */
    @GetMapping("/reservation")
    public String reservationFormA(@RequestParam int placeId, Model model){

        model.addAttribute("placeId", placeId);
        Optional<Place> place = placeRepository.findById(placeId);
        String placeType = place.get().getPlaceType();

        if("hotel".equals(placeType))
            return "reservation-FormB";

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
