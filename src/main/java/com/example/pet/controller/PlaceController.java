package com.example.pet.controller;

import com.example.pet.domain.place.Place;
import com.example.pet.dto.place.PlaceDetailDto;
import com.example.pet.dto.place.PlaceDto;
import com.example.pet.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/places/{memberId}/placeDetail/{placeId}")
    public String getPlaceDetail(@PathVariable int placeId, @PathVariable int memberId, Model model) {
        PlaceDetailDto placeDetailDto = placeService.getPlaceDetail(placeId);
        if (placeDetailDto != null) {
            model.addAttribute("placeDetail", placeDetailDto);
            return "placeDetail";
        } else {
            return "error";
        }
    }

    @GetMapping("/places/{memberId}")
    public String getAllPlacesPaging(@PathVariable int memberId, Model model, @RequestParam(defaultValue = "1") int page) {
        int pageSize = 8; // 한 페이지에 출력할 개수

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Place> placePage = placeService.getAllPlacesPaging(pageable);
        List<Place> places = placePage.getContent();

        model.addAttribute("places", places);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", placePage.getTotalPages());

        return "places";
    }
    @PostMapping("/places/filter/{memberId}")
    public String getPlacesByTypeAndRegion(HttpServletRequest request, @PathVariable int memberId,
                                           @RequestParam(value = "placeType", required = false, defaultValue = "all") String placeType,
                                           @RequestParam(value = "regionId", required = false, defaultValue = "-1") Integer regionId,
                                           @RequestParam(value = "sortOption", required = false) String sortOption,
                                           @RequestParam(defaultValue = "1") int page,
                                           Model model) {

        String keyword = request.getParameter("keyword");
        int pageSize = 8;

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Place> placePage;

        if (keyword == "") {
            if (placeType.equals("all") && regionId == -1) {
                placePage = placeService.getAllPlacesPaging(pageable);
            } else if (!placeType.equals("all") && regionId == -1) {
                placePage = placeService.getPlacesByPlaceType(placeType, pageable);
            } else if (placeType.equals("all") && regionId != -1) {
                placePage = placeService.getPlacesByRegionId(regionId, pageable);
            } else {
                placePage = placeService.getPlacesByTypeAndRegion(placeType, regionId, pageable);
            }
        } else {
            if(placeType.equals("all") && regionId == -1) {
                //지역과 타입을 모두 선택 안 한 경우
                placePage = placeService.getPlacesByKeyword(keyword, pageable);
            } else if(placeType.equals("all")) {
                //타입만 선택 안 한 경우
                placePage = placeService.getPlacesByRegionIdAndKeyword(regionId, keyword, pageable);
            } else if(regionId == -1) {
                //지역만 선택 안 한 경우
                placePage = placeService.getPlacesByTypeAndKeyword(placeType, keyword, pageable);
            } else {
                //지역, 타입, 키워드 모두 입력한 경우
                placePage = placeService.getPlacesByTypeAndRegionIdAndKeyword(placeType, regionId, keyword, pageable);
            }
        }

        //변환
        List<Place> placeList = placePage.getContent();
        List<PlaceDto> places = convertToPlaceDtoList(placeList);

        //정렬
        if (sortOption != null) {
            if (sortOption.equals("reviewCountDesc")) {
                places.sort((p1, p2) -> p2.getReviewCnt() - p1.getReviewCnt());
            } else if (sortOption.equals("reviewCountAsc")) {
                places.sort(Comparator.comparingInt(PlaceDto::getReviewCnt));
            } else if (sortOption.equals("avgRatingDesc")) {
                places.sort((p1, p2) -> Double.compare(p2.getAvgRating(), p1.getAvgRating()));
            } else if (sortOption.equals("avgRatingAsc")) {
                places.sort(Comparator.comparingDouble(PlaceDto::getAvgRating));
            }
        }

        model.addAttribute("places", places);
        return "places";
    }

    //placeDto로 변환
    private List<PlaceDto> convertToPlaceDtoList(List<Place> content) {
        List<PlaceDto> placeDtoList = new ArrayList<>();
        for (Place place : content) {
            PlaceDto placeDto = new PlaceDto();
            placeDto.setPlaceId(place.getPlaceId());
            placeDto.setAvgRating(place.getAvgRating());
            placeDto.setReviewCnt(place.getReviewCnt());
            placeDto.setPlaceTitle(place.getPlaceTitle());
            placeDto.setPlaceType(place.getPlaceType());
            placeDto.setImageUrl(place.getImageUrl());
            placeDto.setAddress(place.getAddress());

            placeDtoList.add(placeDto);
        }
        return placeDtoList;
    }
}