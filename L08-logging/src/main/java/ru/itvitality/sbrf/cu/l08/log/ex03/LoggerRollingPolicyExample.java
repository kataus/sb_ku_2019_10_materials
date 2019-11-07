package ru.itvitality.sbrf.cu.l08.log.ex03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerRollingPolicyExample {
    private static Logger logger = LoggerFactory.getLogger(LoggerRollingPolicyExample.class);
    private long counter = 0;

    public static void main(String[] args) {
        new LoggerRollingPolicyExample().loop();
    }

    public void loop() {
        while(true) {
            logger.error("message for file:{}", counter);
            counter++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
