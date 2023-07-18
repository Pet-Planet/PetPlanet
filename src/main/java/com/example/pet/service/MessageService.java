package com.example.pet.service;

import com.example.pet.domain.member.Member;
import com.example.pet.domain.message.Message;
import com.example.pet.dto.message.MessageDetailDto;
import com.example.pet.dto.message.MessageDto;
import com.example.pet.dto.message.MessageListDto;
import com.example.pet.repository.MemberRepository;
import com.example.pet.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;


    /*
    메세지 작성
     */
    public Message write(int senderId, MessageDto messageDto){

        Member sender = memberRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("메세지를 보낼 수 없는 유저입니다."));
        Member receiver = memberRepository.findById(messageDto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("메세지를 받을 유저가 존재하지 않습니다."));


        Message message = messageRepository.save(messageDto.toEntity());

        message.setSender(sender);
        message.setReceiver(receiver);


        return message;

    }



    /*
    수신함 조회
     */

    @Transactional(readOnly = true)
    public List<MessageListDto> getReceiveMessage(int memberId){

        List<Message> messageList = messageRepository.findByReceiveMessage(memberId);

        List<MessageListDto> receiveMessage = new ArrayList<>();

        for (Message message:messageList) {

            MessageListDto messageListDto = new MessageListDto(
                    message.getId(), message.getSender().getMemberId(), message.getReceiver().getMemberId(),
                    message.getSender().getNickname(), message.getTitle(), message.getCreatedDate(),
                    message.isReadCheck()
            );

            receiveMessage.add(messageListDto);
        }

        return  receiveMessage;
    }


    /*
    발신함 조회
     */

    @Transactional(readOnly = true)
    public List<MessageListDto> getSendMessage(int memberId){

        List<Message> messageList = messageRepository.findBySendMessage(memberId);

        List<MessageListDto> sendMessages = new ArrayList<>();

        for (Message message:messageList) {

            MessageListDto messageListDto = new MessageListDto(
                    message.getId(), message.getSender().getMemberId(), message.getReceiver().getMemberId(),
                    message.getReceiver().getNickname(), message.getTitle(), message.getCreatedDate(),
                    message.isReadCheck()
            );

            sendMessages.add(messageListDto);
        }

        return  sendMessages;
    }


    /*
    상세 메세지 조회
    읽음 표시 업데이트 -> @Transactional(readOnly = true) 금지
     */

    public MessageDetailDto getMessageDetail(int memberId, int msgId){

        Message message = messageRepository.findById(msgId)
                .orElseThrow(() -> new IllegalArgumentException("메세지가 존재하지 않습니다."));


        //미열람 상태일 때 수신자가 상세 메세지 조회시 메세지 읽음
        if(!message.isReadCheck()) {
            int receiverId = message.getReceiver().getMemberId();
            if (receiverId == memberId)
                message.readMessage();
        }


        MessageDetailDto messageDetail = new MessageDetailDto(

                message.getId(), message.getSender().getMemberId(), message.getReceiver().getMemberId(),
                message.getSender().getNickname(), message.getReceiver().getNickname(),
                message.getTitle(), message.getContent(), message.getCreatedDate()
        );


        return messageDetail;
    }



    /*
    메세지 삭제
     */

    public int deleteMessage(int memberId, int msgId){

        Message message = messageRepository.findById(msgId)
                .orElseThrow(() -> new IllegalArgumentException("메세지가 존재하지 않습니다."));

        int sender = message.getSender().getMemberId();
        int receiver = message.getReceiver().getMemberId();

        if(memberId == sender)
            message.deletedBySender();
        else if(memberId == receiver)
            message.deletedByReceiver();
        else
            new IllegalArgumentException("삭제권한이 없는 유저입니다.");


        //수신자, 발신자가 모두 삭제시 DB에서 삭제
        if(message.isDeletedBySender() && message.isDeletedByReceiver())
            messageRepository.deleteById(msgId);


        return msgId;

    }

}
