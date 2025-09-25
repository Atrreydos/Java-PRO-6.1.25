package org.example.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.LimitDto;
import org.example.dto.ReduceLimitRequest;
import org.example.dto.ReduceLimitResponse;
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

  @PostMapping("/reduce")
  public ReduceLimitResponse reduceLimit(@RequestBody ReduceLimitRequest request) {
    log.info("Получен запрос уменьшения остатка {}", request);

    return limitService.reduceLimit(request);
  }
}
