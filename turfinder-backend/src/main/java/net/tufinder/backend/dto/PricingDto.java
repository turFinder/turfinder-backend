package net.tufinder.backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tufinder.backend.entity.Pricing;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PricingDto implements Serializable {
    private Double pricePerHour;
    private LocalTime startHour;
    private LocalTime endHour;
    private Short startDay;
    private Short endDay;

    public static PricingDto map(Pricing price){
        return new PricingDto(
                price.getPricePerHour(),
                price.getStartHour(),
                price.getEndHour(),
                price.getStartDay(),
                price.getEndDay()
        );
    }
}
