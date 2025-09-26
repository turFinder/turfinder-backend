package net.tufinder.backend.service;


import net.tufinder.backend.dto.TurfDto;
import net.tufinder.backend.repository.TurfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurfService {
    @Autowired
    private TurfRepo turfRepo;


    public List<TurfDto.RetrieveDto> allTurfs(){
        return turfRepo.findAll().stream()
                .map(TurfDto::map).toList();
    }
}
