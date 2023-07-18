package com.example.pet.dto.message;

import com.example.pet.domain.member.Member;
import com.example.pet.domain.message.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private int msgId;
    private int senderId;
    private int receiverId;
    private String title;
    private String content;

    public Message toEntity(){

        return Message.builder()
                .id(msgId)
                .title(title)
                .content(content)
                .build();
    }

}
