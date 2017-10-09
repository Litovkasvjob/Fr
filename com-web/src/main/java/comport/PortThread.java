package comport;

import domain.Load;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;

/**
 * @author mstetsenko
 */
public class PortThread extends Thread {

    final Logger LOGGER = Logger.getLogger(PortThread.class.getName());

    private SerialPort serialPort;
    private String numberCom;
    private BlockingQueue<Load> dataQueue;
    private PortReader portReader;

    public PortThread(String numberCom, BlockingQueue<Load> dataQueue) {
        this.numberCom = numberCom;
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        LOGGER.info("Thread " + this + " starts construction SerialPort for portNumber : " + numberCom);
        serialPort = new SerialPort("COM" + numberCom);

        //Open port
        try {
            serialPort.openPort();

            //Set parameters
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Turn on hardware flow control
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);

            //Set listener and mask
            LOGGER.info("Thread " + this + " adds PortReader to SerialPort(" + numberCom + ")");
            portReader = new PortReader(getSerialPort(), dataQueue);

            serialPort.addEventListener(portReader, SerialPort.MASK_RXCHAR);
            LOGGER.info("Thread " + this + " listening SerialPort(" + numberCom + ")");

        } catch (SerialPortException e) {
            LOGGER.error(e);
        }
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

    public BlockingQueue<Load> getDataQueue() {
        return dataQueue;
    }
}
