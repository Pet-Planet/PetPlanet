package com.example.pet.controller;

import com.example.pet.domain.place.Place;
import com.example.pet.domain.reservation.Reservation;
import com.example.pet.dto.reservation.ReservationDetailDto;
import com.example.pet.dto.reservation.ReservationDto;
import com.example.pet.dto.reservation.ReservationListDto;
import com.example.pet.repository.PlaceRepository;
import com.example.pet.repository.ReservationRepository;
import com.example.pet.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;
    private final PlaceRepository placeRepository;
    private final ReservationRepository reservationRepository;


       /*
      예약 작성 폼 placeType==hetel -> reservation-FormB.jsp로 이동
        */
    @GetMapping("/reservation/{memberId}")
    public String reservationForm(@PathVariable int memberId, @RequestParam int placeId, Model model){

        model.addAttribute("memberId", memberId);
        model.addAttribute("placeId", placeId);

        Optional<Place> place = placeRepository.findById(placeId);
        String placeType = place.get().getPlaceType();

        if("hotel".equals(placeType))
            return "reservation-FormB";

        return "reservation-FormA";
    }



        /*
    예약 정보 입력 (카페, 운동장, 식당 전용)
    (type=Unsupported Media Type, status=415) 에러 -> @ModelAttribute 사용
     */

    @PostMapping("/reservation/{memberId}/confirm/a")
    public String checkReservationFormA(@PathVariable int memberId, @ModelAttribute("rev") ReservationDto reservationDto, Model model){

        model.addAttribute("memberId", memberId);
        reservationService.checkFormA(reservationDto);

        return "reservation-ConfirmA";

    }



    /*
    예약 정보 입력 (숙소 전용)
    (type=Unsupported Media Type, status=415) 에러 -> @ModelAttribute 사용
     */

    @PostMapping("/reservation/{memberId}/confirm/b")
    public String checkReservationFormB(@PathVariable int memberId, @ModelAttribute("rev") ReservationDto reservationDto){

        reservationService.checkFormB(reservationDto);

        return "reservation-ConfirmB";

    }



    /*
     예약하기 API
  */
    @PostMapping("/reservation/{memberId}")
    public String saveReservation(@PathVariable int memberId, @ModelAttribute("rev") ReservationDto reservationDto){

        int revId = reservationService.saveReservation(memberId, reservationDto).getId();

        return "redirect:/reservation/" + memberId + "/" + revId;


    }


    /*
    예약 상세조회
     */
    @GetMapping("/reservation/{memberId}/{revId}")
    public String getReview(@PathVariable int memberId, @PathVariable int revId, Model model){

        ReservationDetailDto reservationDetail = reservationService.getReservationDetail(revId);

        model.addAttribute("memberId", memberId);
        model.addAttribute("revDetail", reservationDetail);

        Optional<Reservation> reservation = reservationRepository.findById(revId);
        String placeType = reservation.get().getPlace().getPlaceType();

        if("hotel".equals(placeType))
            return "reservation-detailB";

        return "reservation-detailA";

    }




    /*
    나의 예약 조회 API
     */

    @GetMapping("/mypage/{memberId}/reservations")
    public String getReview(@PathVariable int memberId, Model model){

        List<ReservationListDto> reservationList = reservationService.getMyReservation(memberId);

        model.addAttribute("reservationList", reservationList);

        return "mypageReservations";

    }


    /*
    예약 취소
     */

    @DeleteMapping("/reservation/{revId}")
    public String cancelReservation(@PathVariable int revId){

        reservationService.cancelReservation(revId);

        int memberId = reservationRepository.findById(revId).get().getMember().getMemberId();

        return "redirect:/main/" + memberId;
    }
}
