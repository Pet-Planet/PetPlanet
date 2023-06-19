package com.example.pet.service;

import com.example.pet.domain.member.Member;
import com.example.pet.domain.place.Place;
import com.example.pet.domain.reservation.Reservation;
import com.example.pet.dto.reservation.ReservationDetailDto;
import com.example.pet.dto.reservation.ReservationDto;
import com.example.pet.dto.reservation.ReservationListDto;
import com.example.pet.dto.review.GetReviewDto;
import com.example.pet.repository.MemberRepository;
import com.example.pet.repository.PlaceRepository;
import com.example.pet.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {


    private final MemberRepository memberRepository;
    private final PlaceRepository placeRepository;
    private final ReservationRepository reservationRepository;


    /*
    예약정보 입력 (카페, 운동장, 식당 전용)
     */

    public void checkFormA(ReservationDto reservationDto){

        Optional<Place> place = placeRepository.findById(reservationDto.getPlaceId());

        //총 입장료 계산
        int guests = reservationDto.getGuests();
        int price = place.get().getPrice();

        int amount = guests * price;

        reservationDto.setAmount(amount);

        String placeName = place.get().getPlaceTitle();
        reservationDto.setPlaceName(placeName);


    }




    /*
    예약정보 입력 (숙소 전용)
     */

    public void checkFormB(ReservationDto reservationDto){

        Optional<Place> place = placeRepository.findById(reservationDto.getPlaceId());

        LocalDate checkInDate = reservationDto.getStartDate();
        LocalDate checkOutDate = reservationDto.getEndDate();

        int price = place.get().getPrice();     // 숙박요금 계산
        reservationDto.setAmount(hotelAmount(price, checkInDate, checkOutDate));

        String placeName = place.get().getPlaceTitle();
        reservationDto.setPlaceName(placeName);


    }

    /*
    숙소 결제 금액 구하기
     */
    public int hotelAmount(int price, LocalDate checkIn, LocalDate checkOut) {

        int totalPrice = 0;
        LocalDateTime date1 = checkIn.atStartOfDay();
        LocalDateTime date2 = checkOut.atStartOfDay();

        int days = (int) Duration.between(date1, date2).toDays();

        totalPrice = price * days;

        return totalPrice;

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

    /*
    예약 상세보기
     */
    @Transactional(readOnly = true)
    public ReservationDetailDto getReservationDetail(int revId){

        Reservation reservation = reservationRepository.findById(revId).get();

        ReservationDetailDto reservationDto = new ReservationDetailDto(
                reservation.getId(), reservation.getPlace().getPlaceTitle(),
                reservation.getRevName(), reservation.getPhoneNum(),
                reservation.getStartDate(), reservation.getEndDate(), reservation.getTime(),
                reservation.getGuests(), reservation.getPets(),
                reservation.getAmount(), reservation.getPlace().getAddress());

        return reservationDto;
    }



    /*
    나의 예약리스트 조회
     */

    @Transactional(readOnly = true)
    public List<ReservationListDto> getMyReservation(int memberId) {

        List<Reservation> reservationList =
                reservationRepository.findByMember_MemberIdOrderByCreatedDateDesc(memberId);

        List<ReservationListDto> reservationListDtoList = new ArrayList<>();

        for(Reservation reservation : reservationList){

            ReservationListDto reservationListDto = new ReservationListDto(
                    reservation.getId(), reservation.getMember().getMemberId(),
                    reservation.getPlace().getPlaceTitle(),
                    reservation.getStartDate(),
                    reservation.getGuests(), reservation.getPets(),
                    reservation.getPlace().getImageUrl()
            );

            reservationListDtoList.add(reservationListDto);

    }

        return reservationListDtoList;



    }



    /*
    예약 취소
     */

    public int cancelReservation(int revId){

        reservationRepository.deleteById(revId);

        return revId;
    }

    }



