package com.example.pet.domain.note;

import com.example.pet.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Window implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "window_id")
    private int windowId;

    @ManyToOne
    @JoinColumn(name="recev_id")
    private Member receiveUserId;

    @ManyToOne
    @JoinColumn(name="send_id")
    private Member sendUserId;
}