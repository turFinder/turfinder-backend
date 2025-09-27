package net.tufinder.backend.controller;

import net.tufinder.backend.dto.ReviewDto;
import net.tufinder.backend.entity.Description;
import net.tufinder.backend.entity.Review;
import net.tufinder.backend.entity.Turf;
import net.tufinder.backend.entity.Users;
import net.tufinder.backend.repository.DescriptionRepo;
import net.tufinder.backend.repository.ReviewRepo;
import net.tufinder.backend.repository.TurfRepo;
import net.tufinder.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TurfRepo turfRepo;
    @Autowired
    private DescriptionRepo descriptionRepo;
    @Autowired
    private ReviewRepo reviewRepo;

    @PostMapping("/create")
    public ResponseEntity<Boolean> createReview(@RequestBody ReviewDto.CreateDto dto){
        Optional<Users> optUser = userRepo.findById(dto.getUser_id());
        if(optUser.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        Optional<Turf> optTurf = turfRepo.findById(dto.getTurf_id());
        if(optTurf.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);

        Description description = new Description();
        description.setText(dto.getText());
        descriptionRepo.save(description);

        Review review = new Review();
        review.setUser(optUser.get());
        review.setTurf(optTurf.get());
        review.setDescription(description);
        review.setRating(dto.getRating());

        reviewRepo.save(review);

        Turf turf = optTurf.get();
        Integer prevSize = turf.getReviewList().size();
        Double newAvgRating = (turf.getAverageRating()*prevSize + dto.getRating())/(prevSize+1);
        turf.setAverageRating(newAvgRating);
        turfRepo.save(turf);

        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }


}
