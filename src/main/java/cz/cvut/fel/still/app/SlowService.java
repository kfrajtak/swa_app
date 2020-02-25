package cz.cvut.fel.still.app;

//import co.elastic.apm.api.CaptureSpan;
//import co.elastic.apm.api.Traced;
import brave.Span;
import brave.Tracer;
import co.elastic.apm.api.CaptureSpan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SlowService {

    @CaptureSpan("otherOperations")
    public void doWorkAndCaptureSpan() throws InterruptedException {
        Thread.sleep(1500L);
        log.info("Doing some work ...");

        //log.info("I'm in the original span");

        /*Span newSpan = tracer.nextSpan().name("newSpan").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {*/
        Thread.sleep(1000L);
        //log.info("I'm in the new span doing some cool work that needs its own span");
        /*} finally {
            newSpan.finish();
        }*/

        //log.info("I'm in the original span");
        doWork();
    }

    @CaptureSpan("another")
    public void doWork() throws InterruptedException {
        Thread.sleep(1500L);
        log.info("(1) Doing some work and captured span ...");

        log.info("(1) I'm in the original span");

        Thread.sleep(300L);
        log.info("(1) I'm in the new span doing some cool work that needs its own span");

        log.info("(1) I'm in the original span");
    }

    //@Traced
    public void doTracedWork() throws InterruptedException {
        Thread.sleep(2500L);
        log.info("(2) Doing some work ...");

        log.info("(2) I'm in the original span");

        Thread.sleep(1000L);
        log.info("(2) I'm in the new span doing some cool work that needs its own span");

        log.info("(2) I'm in the original span");
    }
}
