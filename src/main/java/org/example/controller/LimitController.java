package org.example.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.LimitDto;
import org.example.dto.ChangeLimitRequest;
import org.example.dto.ChangeLimitResponse;
import org.example.service.LimitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
@RequiredArgsConstructor
@Slf4j
public class LimitController {

  private final LimitService limitService;

  @GetMapping
  public List<LimitDto> getAllLimits() {
    log.info("Получен запрос всех лимитов");

    return limitService.getAllLimits();
  }

  @PostMapping("/change")
  public ChangeLimitResponse changeLimit(@RequestBody ChangeLimitRequest request) {
    log.info("Получен запрос изменения остатка {}", request);

    return limitService.changeLimit(request);
  }
}
