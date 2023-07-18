package com.example.pet.repository;

import com.example.pet.domain.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("SELECT m FROM Message m WHERE m.receiver.memberId = :memberId and m.deletedByReceiver = false")
    List<Message> findByReceiveMessage(int memberId);

    @Query("SELECT m FROM Message m WHERE m.sender.memberId = :memberId and m.deletedBySender = false")
    List<Message> findBySendMessage(int memberId);
}
