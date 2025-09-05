package net.tufinder.backend.service;


import net.tufinder.backend.dto.BookingDto;
import net.tufinder.backend.entity.Booking;
import net.tufinder.backend.entity.Turf;
import net.tufinder.backend.entity.Users;
import net.tufinder.backend.repository.BookingRepo;
import net.tufinder.backend.repository.TurfRepo;
import net.tufinder.backend.repository.UserRepo;
import net.tufinder.backend.util.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {
    @Autowired
    BookingRepo bookingRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TurfRepo turfRepo;

    public Booking createBooking(BookingDto.CreateDto dto){
        Booking booking = new Booking();

        Optional<Users> optUser = userRepo.findById(dto.getUserId());
        if(optUser.isEmpty()) return null;
        booking.setUser(optUser.get());

        Optional<Turf> optTurf = turfRepo.findById(dto.getTurfId());
        if(optTurf.isEmpty()) return null;
        booking.setTurf(optTurf.get());

        booking.setDate(dto.getDate());
        booking.setEndTime(dto.getEndTime());
        booking.setStartTime(dto.getStartTime());
        booking.setBookingName(generateBookingName(booking));

        bookingRepo.save(booking);
        return booking;
    }


    public String generateBookingName(Booking booking){
        String userCode = PasswordHasher.hash(booking.getUser().getName()).substring(0,5);
        String dateCode = booking.getDate().toString();
        String bookingCode = UUID.randomUUID().toString().substring(0, 8);
        return "BK"+userCode+"-"+dateCode+"-"+bookingCode;
    }

    // no need for new hash service
}
