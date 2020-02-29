package cz.cvut.fel.still.app.tasks;

import co.elastic.apm.api.CaptureSpan;
import co.elastic.apm.api.CaptureTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class PrintUsersTask {

    @Scheduled(fixedDelayString = "5000")
    public void execute() {
        log.info("run scheduled test");
        doExecute();
    }

    @CaptureTransaction(type = "Task", value = "PrintUsers")
    private void doExecute() {
        sleep();
    }

    @CaptureSpan("someCustomOperation")
    private void sleep() {
        try {
            Random random = new Random();
            int milis = random.nextInt(120 - 20 + 1) + 20;
            Thread.sleep(milis);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}