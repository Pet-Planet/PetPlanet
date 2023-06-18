package com.example.pet.controller;

import com.example.pet.dto.place.PlaceAddDto;
import com.example.pet.dto.place.PlaceDetailDto;
import com.example.pet.dto.place.PlaceDto;
import com.example.pet.dto.reservation.ReservationDto;
import com.example.pet.service.PlaceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

@Controller
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
    public String getAllPlaces(Model model) {
        List<PlaceDto> places = placeService.getAllPlaces();
        model.addAttribute("places", places);
        return "placeAll";
    }

    @GetMapping("/place/search")
    public String getPlacesByTypeAndRegion(@RequestParam(value = "placeType", required = false) String placeType,
                                           @RequestParam(value = "regionId", required = false) Integer regionId,
                                           @RequestParam(value = "sort", required = false) String sortOption,
                                           Model model) {

        // 장소 타입별로 조회
        List<PlaceDto> places;
        if (placeType != null && regionId != null) {
            // placeType과 regionId가 모두 지정된 경우
            places = placeService.getPlacesByTypeAndRegion(placeType, regionId);
        } else if (placeType != null) {
            // placeType만 지정된 경우
            places = placeService.getPlacesByPlaceType(placeType);
        } else if (regionId != null) {
            // regionId만 지정된 경우
            places = placeService.getPlacesByRegionId(regionId);
        } else {
            // placeType과 regionId가 모두 지정되지 않은 경우, 전체 장소 조회
            places = placeService.getAllPlaces();
        }

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
        return "placeSearchForm";
    }
}
