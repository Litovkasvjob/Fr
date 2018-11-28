package comport.test_port;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class ReadCom {


    private static SerialPort serialPort;

    public static void main(String[] args) {

        serialPort = new SerialPort("COM1");
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            serialPort.writeString("Get data");
        }
        catch (SerialPortException ex) {
            ex.printStackTrace();
        }

    }

    private static class PortReader
            implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0){
                try {
                    String data = serialPort.readString(event.getEventValue());
                    System.out.println("==== read: " + data);
                    serialPort.writeString("Get data");
                }
                catch (SerialPortException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
