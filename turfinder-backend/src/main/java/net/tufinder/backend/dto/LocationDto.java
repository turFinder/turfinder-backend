package net.tufinder.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tufinder.backend.entity.Location;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto implements Serializable {
        private String address;
        private Double longitude;
        private Double latitude;
        private String googlePlaceId;

        public static LocationDto map(Location location){
            return new LocationDto(
                    location.getAddress(),
                    location.getLongitude(),
                    location.getLatitude(),
                    location.getGooglePlaceId()
            );
        }
}
