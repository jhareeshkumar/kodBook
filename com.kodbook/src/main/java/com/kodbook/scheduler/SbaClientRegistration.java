package com.kodbook.scheduler;

import de.codecentric.boot.admin.client.registration.ApplicationRegistrator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class SbaClientRegistration {

    private final ApplicationRegistrator applicationRegistrator;

    @Scheduled(initialDelay = 2, fixedDelay = 30, timeUnit = TimeUnit.MINUTES)
    public void scheduleClientRegistration() {
        boolean registerStatus = applicationRegistrator.register();
        if (registerStatus) {
            log.info("SBA client registered successfully");
        } else {
            log.error("SBA client registration failed");
        }
    }
}
