package comport;


import jssc.SerialPort;
import jssc.SerialPortException;
import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;


public class PortEmulator extends Thread{

    final Logger LOGGER = Logger.getLogger(PortThread.class.getName());

    private SerialPort serialPort;
    private String numberCom;


    public PortEmulator(String numberCom) {
        this.numberCom = numberCom;
    }

    @Override
    public void run() {
        LOGGER.info("Thread " + this + " starts construction PortEmulator for portNumber : " + numberCom);
        serialPort = new SerialPort("COM" + numberCom);

        //Open port
        try {
            while (true) {
            serialPort.openPort();

            //Set parameters
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Turn on hardware flow control
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);

            //Set random data to port
            LOGGER.info("Thread " + this + " adds data to SerialPort(" + numberCom + ")");

            //�   0.000!n  -это приходит на устройстве


                serialPort.writeBytes(createBytesNumber());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                serialPort.closePort();
            }

        } catch (SerialPortException e) {
            LOGGER.error(e);
        }
    }

    public static byte[] createBytesNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append("++++");

        Random random = new Random();

        Double d = Math.random() * 100;
        sb.append(Double.toString(d).substring(0, 5));
        sb.append("++");

        String s = String.valueOf(sb);
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        System.out.println(s);
        System.out.println(Arrays.toString(bytes));

        return bytes;

    }

    public static void main(String[] args) {

        PortEmulator portEmulator = new PortEmulator("1");
        portEmulator.start();

    }


    @Override
    public void destroy() {
        LOGGER.warn(this + " thread was destroyed");
        super.destroy();
    }

    @Override
    public void interrupt() {
        LOGGER.warn(this + " thread was interrupted");
        super.interrupt();
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public String getNumberCom() {
        return numberCom;
    }

}
