package com.example.pet.controller;

import com.example.pet.domain.member.Member;
import com.example.pet.dto.place.PlaceDto;
import com.example.pet.service.MemberService;
import com.example.pet.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainPageController {

    private final MemberService memberService;
    private final PlaceService placeService;

    @GetMapping("/main/{memberId}")
    public String mainPage(@PathVariable int memberId, Model model) {
        Member member = memberService.findMe(memberId);
        List<PlaceDto> places = placeService.getTopPlacesByAvgRating();
        model.addAttribute("placesTopRating", places);
        return "mainpage";
    }
}
