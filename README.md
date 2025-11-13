# Pulse Settlement Engine

Pulse Settlement Engine emulates the billing ledger microservice from the Hookline Pulse reference flow. It provides a simple REST API that evaluates ingested events and emits the same log lines captured in the demo datasets.

## Highlights
- `/ledger/evaluate` endpoint with deterministic scoring logic
- SLF4J logs for evaluation start/completion to align with LogTracer mappings
- Spring Boot Actuator for health/liveness
- Maven build that targets Java 17

## Run Instructions
```bash
sdk use java 17-tem
mvn spring-boot:run
```
The service binds to `http://localhost:8083`.

```bash
curl -X POST http://localhost:8083/ledger/evaluate \
     -H 'Content-Type: application/json' \
     -d '{"ledgerId":"VX-9921","amount":42.50}'
```

## Key Files
```
src/main/java/io/hookline/pulse/billing/SettlementEngineApplication.java
src/main/java/io/hookline/pulse/billing/SettlementEngine.java
src/main/java/io/hookline/pulse/billing/model/EvaluationRequest.java
```

Use this scaffold as the standalone repo for the billing component when you publish the Hookline demo microservices.
