package com.example.pet.domain.note;

import com.example.pet.domain.BaseEntity;
import com.example.pet.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Note extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private int noteId;
    
    @ManyToOne // Many = Note, One = Member 한명의 유저는 여러개의 쪽지를 받을 수 있다.
    @JoinColumn(name="recev_id") // foreign key (receiveUserId) references Member (id)
    private Member receiveUserId;

    @ManyToOne
    @JoinColumn(name="send_id")
    private Member sendUserId;
    
    @Column
    private String content;
    
    @ManyToOne // Many = Note, One = Window 하나의 창은 여러개의 쪽지를 가질 수 있다.
    @JoinColumn(name="window_id") // foreign key (windowId) references Window (id)
    private Window windowId;
}