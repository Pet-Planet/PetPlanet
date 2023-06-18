package com.example.pet.repository;

import com.example.pet.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByMember_MemberIdOrderByCreatedDateDesc(int id);
    @Query("SELECT count(r) FROM Review r WHERE r.place.placeId=:placeId")
    int countByPlaceId(int placeId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.place.placeId=:placeId")
    Double calculateAverageRatingByPlaceId(int placeId);

}
