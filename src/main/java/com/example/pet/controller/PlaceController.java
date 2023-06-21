package com.example.pet.controller;

import com.example.pet.dto.place.PlaceDetailDto;
import com.example.pet.dto.place.PlaceDto;
import com.example.pet.service.PlaceService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/places/top/{memberId}")
    public String getTopPlacesByAvgRating(@PathVariable int memberId, Model model) {
        List<PlaceDto> places = placeService.getTopPlacesByAvgRating();
        model.addAttribute("places", places);
        return "placesTopRating";
    }

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
    public String getAllPlaces(@PathVariable int memberId, Model model) {
        List<PlaceDto> places = placeService.getAllPlaces();
        model.addAttribute("places", places);
        return "places";
    }

    @PostMapping("/places/filter/{memberId}")
    public String getPlacesByTypeAndRegion(HttpServletRequest request, @PathVariable int memberId,
                                           @RequestParam(value = "placeType", required = false) String placeType,
                                           @RequestParam(value = "regionId", required = false) Integer regionId,
                                           @RequestParam(value = "sortOption", required = false) String sortOption,
                                           Model model) {

        List<PlaceDto> places;
        String keyword = request.getParameter("keyword");
        if (keyword.equals("")) {
            if (placeType.equals("all") && regionId == -1) {
                places = placeService.getAllPlaces();
            } else if (!placeType.equals("all") && regionId == -1) {
                places = placeService.getPlacesByPlaceType(placeType);
            } else if (placeType.equals("all") && regionId != -1) {
                places = placeService.getPlacesByRegionId(regionId);
            } else {
                places = placeService.getPlacesByTypeAndRegion(placeType, regionId);
            }
        } else {
            places = placeService.getPlacesByTypeAndRegionAndKeyword(placeType, regionId, keyword);
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
        return "places";
    }
}
