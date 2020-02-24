package cz.cvut.fel.still.app;

import brave.Span;
import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SlowService {
    @Autowired
    private Tracer tracer;

    public void doWork() throws InterruptedException {
        Thread.sleep(1500L);
        log.info("Doing some work ...");

        log.info("I'm in the original span");

        Span newSpan = tracer.nextSpan().name("newSpan").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            Thread.sleep(1000L);
            log.info("I'm in the new span doing some cool work that needs its own span");
        } finally {
            newSpan.finish();
        }

        log.info("I'm in the original span");
    }
}
