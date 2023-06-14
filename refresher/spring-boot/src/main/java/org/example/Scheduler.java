package org.example;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class Scheduler {

    @Scheduled(fixedRate = 2000)
    public void printScheduler(){
        System.out.println("Hai from scheduler: " + Instant.now());
    }
}
