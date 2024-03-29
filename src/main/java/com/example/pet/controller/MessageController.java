package com.example.pet.controller;

import com.example.pet.dto.message.MessageDetailDto;
import com.example.pet.dto.message.MessageDto;
import com.example.pet.dto.message.MessageListDto;
import com.example.pet.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {


    private final MessageService messageService;



    /*
    메세지 작성 API
     */

    @PostMapping("/message/{memberId}")
    public String messageWrite(@PathVariable int memberId, @ModelAttribute("msg") MessageDto messageDto, Model model){

        model.addAttribute("memberId", memberId);

        messageService.write(memberId, messageDto);

        return "success "+memberId;

    }



    /*
    수신함 조회 API
    */
    @GetMapping("/message/inbox/{memberId}")
    public List<MessageListDto> inbox(@PathVariable int memberId){

        return messageService.getReceiveMessage(memberId);

    }



    /*
    발신함 조회 API
     */
    @GetMapping("/message/outbox/{memberId}")
    public List<MessageListDto> outbox(@PathVariable int memberId){

        return messageService.getSendMessage(memberId);

    }



    /*
      상세 메세지 조회 API
     */
    @GetMapping("/message/{memberId}/{msgId}")
    public MessageDetailDto messageDetail(@PathVariable int memberId, @PathVariable int msgId){

       return messageService.getMessageDetail(memberId, msgId) ;

    }


    /*
    메세지 삭제
     */
    @DeleteMapping("/message/delete/{memberId}/{msgId}")
    public String deleteMessage(@PathVariable int memberId, @PathVariable int msgId){

        messageService.deleteMessage(memberId, msgId);

        return "success "+ msgId;
    }




}
