package com.example.pet.domain.message;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.member.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity @EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id")
    private int id;

    private  String title;

    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;  //발신자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiver;    //수신자

    //읽음 유무
    @ColumnDefault("false")
    private boolean readCheck;

    //보낸 쪽지함에서 삭제
    @ColumnDefault("false")
    private boolean deletedBySender;

    //받은 쪽지함에서 삭제
    @ColumnDefault("false")
    private boolean deletedByReceiver;

    //발신함에서 삭제
    public void deletedBySender(){

        this.deletedBySender = true;
    }

    //수신함에서 삭제
    public void deletedByReceiver(){

        this.deletedByReceiver = true;
    }

    //메세지 읽음
    public void readMessage(){

        this.readCheck = true;
    }



}
