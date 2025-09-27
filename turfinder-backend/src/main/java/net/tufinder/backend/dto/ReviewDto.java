package net.tufinder.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tufinder.backend.entity.Description;
import net.tufinder.backend.entity.Review;

import java.time.LocalDateTime;

public class ReviewDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDto{
        private String text;
        private Double rating;
        private Long user_id;
        private Long turf_id;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RetrieveDto{
        private Long id;
        private Double rating;
        private String text;
        private Long user_id;
        private String user_name;
        private LocalDateTime created_at;
    }

    public static RetrieveDto map(Review review){
        return new RetrieveDto(
                   review.getId(),
                   review.getRating(),
                   review.getDescription().getText(),
                   review.getUser().getId(),
                   review.getUser().getName(),
                   review.getCreated_at()
                );
    }
}
