package com.example.pet.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageListDto {

    private int msgId;
    private int senderId;
    private int receiverId;
    private String nickName;
    private String title;
    private LocalDateTime date;
    private boolean read;

}
