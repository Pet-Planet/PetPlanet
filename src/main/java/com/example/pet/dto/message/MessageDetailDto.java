package com.example.pet.dto.message;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDetailDto {

    private int msgId;
    private int senderId;
    private int receiverId;
    private String sendName;
    private String receiveName;
    private String title;
    private String content;
    private LocalDateTime date;

}
