package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerService {

  private final LimitService limitService;

  @Scheduled(cron = "0 0 0 * * *")
  public void resetLimits() {
    log.info("Запущена задача сброса лимитов по таймеру");
    limitService.resetLimits();
  }
}
