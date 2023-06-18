package com.example.pet.repository;

import com.example.pet.domain.reservation.Reservation;
import com.example.pet.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByMember_MemberIdOrderByCreatedDateDesc(int id);
}
