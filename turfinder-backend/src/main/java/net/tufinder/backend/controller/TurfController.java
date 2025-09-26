package net.tufinder.backend.controller;



import net.tufinder.backend.dto.ReviewDto;
import net.tufinder.backend.dto.TurfDto;
import net.tufinder.backend.entity.Turf;
import net.tufinder.backend.repository.TurfRepo;
import net.tufinder.backend.service.TurfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Autowired
    private TurfService turfService;

    @Cacheable("turfs")
    @GetMapping()
    public ResponseEntity<List<TurfDto.RetrieveDto>> allTurfs() {
        return ResponseEntity.ok(turfService.allTurfs());
    }



    @GetMapping("/reviews/{id}")
    public ResponseEntity<List<ReviewDto.RetrieveDto>> getReviews(@PathVariable Long id){
        Optional<Turf> opt = turfRepo.findById(id);
        if(opt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<ReviewDto.RetrieveDto> list = opt.get().getReviewList().stream()
                .map(ReviewDto::map).toList();

        return ResponseEntity.ok(list);
    }

    

}
