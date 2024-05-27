package com.x250.plants.owners.watering_timer;

import com.x250.plants.owners.model.UsersPlant;
import com.x250.plants.owners.repository.UsersPlantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WateringChecker {

    private final UsersPlantRepository usersPlantRepository;

    List<UsersPlant> findPlantsToWater(LocalDateTime currentTime) {
        log.debug("findPlantsToWater({})", currentTime.toString());
        return usersPlantRepository.findByNotificationDateIsBefore(currentTime);
    }

    void moveNextWateringOneDayAhead(UsersPlant usersPlant) {
        log.debug("moveNextWateringOneDayAhead({})", usersPlant.getClass());
        usersPlant.setNotificationDate(LocalDateTime.now().plusDays(1L));
        usersPlantRepository.save(usersPlant);
        log.info("Notification date in user's plant: {} is updated", usersPlant);
    }

}
