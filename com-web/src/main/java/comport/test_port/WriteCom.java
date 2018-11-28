package comport.test_port;

import jssc.SerialPort;
import jssc.SerialPortException;

public class WriteCom {

    public static void main(String[] args) {

        while (true) {
            sendData("test" );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void sendData(String x) {
        SerialPort serialPort = new SerialPort("COM1");
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            System.out.println("===== send: " + x);
            serialPort.writeString(x);
            serialPort.closePort();
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }

    }
}
