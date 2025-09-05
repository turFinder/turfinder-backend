package net.tufinder.backend.controller;


import net.tufinder.backend.dto.BookingDto;
import net.tufinder.backend.entity.Booking;
import net.tufinder.backend.repository.BookingRepo;
import net.tufinder.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<BookingDto.RetrieveDto> createBooking(@RequestBody BookingDto.CreateDto dto){
        Booking booking = bookingService.createBooking(dto);
        if(booking == null){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(BookingDto.map(booking));
    }

}
