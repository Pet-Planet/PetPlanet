package com.example.pet.service;

import com.example.pet.domain.member.Member;
import com.example.pet.domain.place.Place;
import com.example.pet.domain.reservation.Reservation;
import com.example.pet.dto.reservation.ReservationDto;
import com.example.pet.repository.MemberRepository;
import com.example.pet.repository.PlaceRepository;
import com.example.pet.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {


    private final MemberRepository memberRepository;
    private final PlaceRepository placeRepository;
    private final ReservationRepository reservationRepository;



    /*
    결제 금액 구하기
     */
    public int totalPrice(int price, LocalDate checkIn, LocalDate checkOut) {

        int totalPrice = 0;
        LocalDateTime date1 = checkIn.atStartOfDay();
        LocalDateTime date2 = checkOut.atStartOfDay();

        int days = (int) Duration.between(date1, date2).toDays();

        totalPrice = price * days;

        return totalPrice;

    }

    /*
    날짜, 인원 수 입력 폼
     */

    public void checkForm(ReservationDto reservationDto){

        Optional<Place> place = placeRepository.findById(reservationDto.getPlaceId());

        LocalDate checkInDate = reservationDto.getCheckInDate();
        LocalDate checkOutDate = reservationDto.getCheckOutDate();

        int price = place.get().getPrice(); //  총 결제금액 저장 로직
        reservationDto.setAmount(totalPrice(price, checkInDate, checkOutDate));

        String placeName = place.get().getPlaceTitle();
        reservationDto.setPlaceName(placeName);


    }


    /*
      예약하기
    */
    public Reservation saveReservation(int memberId, ReservationDto reservationDto) {

        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Place> place = placeRepository.findById(reservationDto.getPlaceId());

        Reservation reservation = reservationRepository.save(reservationDto.toEntity());

        reservation.setMember(member.get());
        reservation.setPlace(place.get());


        return reservation;
    }



    }



