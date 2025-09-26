package net.tufinder.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tufinder.backend.entity.Pricing;
import net.tufinder.backend.entity.Turf;
import net.tufinder.backend.entity.TurfPictures;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TurfDto {
    public static class CreateDto{

    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RetrieveDto implements Serializable {
        private Long id;
        private String name;
        private LocationDto location;
        private Double averageRating;
        private List<String> images;
        private List<PricingDto> prices;
        private String description;
        private Integer slotDuration;
    }

    public static RetrieveDto map(Turf turf){
        RetrieveDto dto = new RetrieveDto();
        dto.setName(turf.getName());
        dto.setId(turf.getId());

        dto.setImages(turf.getImages().stream()
                .map(TurfPictures::getTurfImageUrl)
                .collect(Collectors.toList()));

        dto.setAverageRating(turf.getAverageRating());
        dto.setLocation(LocationDto.map(turf.getLocation()));
        dto.setDescription(turf.getDescription().getText());
        dto.setSlotDuration(turf.getSlotDuration());

        dto.setPrices(turf.getPricingList().stream()
                .map(PricingDto::map).toList());

        return dto;
    }
}
