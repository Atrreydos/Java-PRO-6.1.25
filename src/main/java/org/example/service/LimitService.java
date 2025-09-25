package org.example.service;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.LimitDto;
import org.example.dto.ReduceLimitRequest;
import org.example.dto.ReduceLimitResponse;
import org.example.entity.Limit;
import org.example.repository.LimitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitService {

  private static final BigDecimal LIMIT_DEFAULT_AMOUNT = new BigDecimal("10000");

  private final LimitRepository limitRepository;

  public List<LimitDto> getAllLimits() {
    log.info("Получение всех лимитов");
    var limits = limitRepository.findAll();

    return limits.stream()
        .map(limit -> new LimitDto(limit.getId(), limit.getClientId(), limit.getAmount()))
        .toList();
  }

  public ReduceLimitResponse reduceLimit(ReduceLimitRequest request) {
    log.info("Начата операция уменьшения лимита на сумму: {} для клиента {}.", request.amount(), request.clientId());

    var limit = limitRepository.findByClientId(request.clientId())
        .orElseGet(() -> limitRepository.save(Limit.builder().clientId(request.clientId()).amount(LIMIT_DEFAULT_AMOUNT).build()));

    var finalAmount = limit.getAmount().subtract(request.amount());
    if (finalAmount.compareTo(BigDecimal.ZERO) < 0) {
      log.info("Операция невозможна, превышен лимит.");

      return new ReduceLimitResponse(false);
    } else {
      log.info("Операция возможна, итоговый лимит: {}.", finalAmount);
      limit.setAmount(finalAmount);
      limitRepository.save(limit);

      return new ReduceLimitResponse(true);
    }
  }

  @Transactional
  public void resetLimits() {
    limitRepository.resetLimits(LIMIT_DEFAULT_AMOUNT);
  }
}
