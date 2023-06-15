package com.example.pet.controller;

import com.example.pet.dto.place.PlaceDetailDto;
import com.example.pet.dto.place.PlaceDto;
import com.example.pet.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;


    //장소 id로 장소 상세보기
    @GetMapping("/place/placeDetail/{placeId}")
    public String getPlaceDetail(@PathVariable int placeId, Model model) {
        PlaceDetailDto placeDetailDto = placeService.getPlaceDetail(placeId);
        if (placeDetailDto != null) {
            model.addAttribute("placeDetail", placeDetailDto);
            return "placeDetail";
        } else {
            return "error";
        }
    }

    //전체 장소 조회
    @GetMapping("/place/all")
    public List<PlaceDto> getAllPlaces() {
        return placeService.getAllPlaces();
    }

}