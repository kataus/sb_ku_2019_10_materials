package ru.itvitality.sbrf.cu;

/**
 * @author sergey
 * created on 15.07.18.
 */

/*
cd L08-remoteDebug/target/classes/
java -Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n ru.otus.RemoteDebug
*/
public class RemoteDebug {

    private int value = 0;

    public static void main(String[] args) throws InterruptedException {
        new RemoteDebug().loop();
    }


    private void loop() throws InterruptedException {
        while (true) {
            value += 1;
            incVal();
            System.out.println(value);
            Thread.sleep(2_000);
        }
    }

    private void incVal()  {
        value += 10;
    }

}
