package comport;

import domain.Load;
import jssc.SerialPort;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author mstetsenko
 */
@RunWith(PowerMockRunner.class)
public class PortListenerTest {

    PortListener portListener;

    private SerialPort serialPort;
    private BlockingQueue<Load> portDataQueue;
    private PortReader portReader;

    @Before
    public void init() {
        portListener = Mockito.spy(PortListener.getInstance());
        serialPort = Mockito.mock(SerialPort.class);
        portReader = Mockito.mock(PortReader.class);
        portDataQueue = new SynchronousQueue<>();
    }

    @Test
    public void testListenPort() throws Exception {

        portListener.listenPort("2425");

    }

    @Test
    public void testStopListenPort() throws Exception {

    }

}