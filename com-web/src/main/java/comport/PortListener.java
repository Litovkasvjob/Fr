package comport;

import domain.Load;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Serg on 12.08.2017.
 */
public class PortListener {

    private static final Logger LOGGER = Logger.getLogger(PortListener.class.getName());

    private static PortListener instance = null;
    private PortListener() {
        // Exists only to defeat instantiation.
    }

    public static PortListener getInstance() {
        if (instance == null) {
            instance = new PortListener();
        }
        return instance;
    }

    private Map<String, SerialPort> portMap = new HashMap<>();
    private Map<String, BlockingQueue<Load>> portDataQueuesMap = new HashMap<>();
    private Map<String, PortThread> portThreadMap = new HashMap<>();

    public BlockingQueue<Load> listenPort(String numberCom) throws SerialPortException {
        if(!portMap.containsKey(numberCom)){

            BlockingQueue<Load> dataQueue = new LinkedBlockingQueue<>();
            PortThread portThread = new PortThread(numberCom, dataQueue);

            portThread.start();

            portDataQueuesMap.put(numberCom, dataQueue);
            portThreadMap.put(numberCom, portThread);
            portMap.put(numberCom, portThread.getSerialPort());
        }
        return portDataQueuesMap.get(numberCom);
    }

    public void stopListenPort(String numberCom) throws SerialPortException {

        LOGGER.info("PortListener stop port: " + numberCom);
        PortThread portThread = portThreadMap.get(numberCom);
        portThread.getSerialPort().closePort();
        portThread.stop();


        portThreadMap.remove(numberCom);
        portMap.remove(numberCom);
        portDataQueuesMap.remove(numberCom);
    }

    public void sendToPort(String numberCom, String dataToPort) throws SerialPortException {
        if(!portMap.containsKey(numberCom) || portMap.get(numberCom) == null){
            throw new RuntimeException("This port is not listening");
        }

        SerialPort serialPort = portMap.get(numberCom);
        serialPort.writeString("Get data");
    }

    public BlockingQueue<Load> getPortDataQueue(String portNumber) {
        return portDataQueuesMap.get(portNumber);
    }

    public SerialPort getSerialPort(String portNumber) {
        return portMap.get(portNumber);
    }

    public PortThread getPortThread(String portNumber) {
        return portThreadMap.get(portNumber);
    }

}
