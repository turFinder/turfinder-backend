package net.tufinder.backend.controller;


import net.tufinder.backend.dto.BookingDto;
import net.tufinder.backend.dto.TurfDto;
import net.tufinder.backend.entity.Booking;
import net.tufinder.backend.entity.Turf;
import net.tufinder.backend.repository.LocationRepo;
import net.tufinder.backend.repository.OwnerRepo;
import net.tufinder.backend.repository.TurfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/turfs")
public class TurfController {
    @Autowired
    private TurfRepo turfRepo;


    @GetMapping()
    public ResponseEntity<List<TurfDto.RetrieveDto>> allTurfs() {
        List<TurfDto.RetrieveDto> turfs = turfRepo.findAll().stream()
                .map(TurfDto::map).collect(Collectors.toList());
        return ResponseEntity.ok(turfs);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<List<BookingDto.RetrieveDto>> getTurf(@PathVariable Long id){
        Optional<Turf> opt = turfRepo.findById(id);
        if(opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        Turf turf = opt.get();
        List<BookingDto.RetrieveDto> bookings = turf.getBookingList().stream()
                .map(BookingDto::map).toList();
        return ResponseEntity.ok(bookings);
    }

    

}
