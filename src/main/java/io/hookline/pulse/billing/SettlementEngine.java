package io.hookline.pulse.billing;

import io.hookline.pulse.billing.model.EvaluationRequest;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ledger")
@Validated
public class SettlementEngine {

    private static final Logger logger = LoggerFactory.getLogger(SettlementEngine.class);

    @PostMapping("/evaluate")
    public ResponseEntity<Map<String, Object>> evaluate(@RequestBody @Valid EvaluationRequest request) {
        logger.info("######### Settlement evaluation started ledger={} scenario={}",
                request.ledgerId(), request.scenario());

        BigDecimal variance = request.amount().remainder(BigDecimal.valueOf(5)).abs()
                .setScale(2, RoundingMode.HALF_UP);
        boolean anomaly = variance.doubleValue() > 0.01;

        logger.info("######### Settlement evaluation completed anomalies={} variance={}",
                anomaly ? 1 : 0, variance);

        Map<String, Object> body = Map.of(
                "ledgerId", request.ledgerId(),
                "evaluatedAt", Instant.now().toString(),
                "amount", request.amount(),
                "variance", variance,
                "anomaly", anomaly);

        return ResponseEntity.ok(body);
    }
}
