package net.tufinder.backend.controller;


import net.tufinder.backend.dto.BookingDto;
import net.tufinder.backend.entity.Booking;
import net.tufinder.backend.entity.Turf;
import net.tufinder.backend.repository.TurfRepo;
import net.tufinder.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private TurfRepo turfRepo;

    @PostMapping("/create")
    public ResponseEntity<BookingDto.RetrieveDto> createBooking(@RequestBody BookingDto.CreateDto dto){
        Booking booking = bookingService.createBooking(dto);
        if(booking == null){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(BookingDto.map(booking));
    }

    @GetMapping("/{turf_id}")
    public ResponseEntity<List<BookingDto.RetrieveDto>> getBooking(@PathVariable Long turf_id){
        Optional<Turf> opt = turfRepo.findById(turf_id);
        if(opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        Turf turf = opt.get();

        List<BookingDto.RetrieveDto> list = turf.getBookingList().stream().map(BookingDto::map).toList();
        return ResponseEntity.ok(list);
    }

}
