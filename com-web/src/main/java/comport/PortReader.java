package comport;

import domain.Load;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;

/**
 * @author mstetsenko
 */

public class PortReader implements SerialPortEventListener { /*Слушатель срабатывающий по появлению данных на COM-порте*/

    private static final Logger LOGGER = Logger.getLogger(PortListener.class.getName());

    private SerialPort serialPort;
    private BlockingQueue<Load> portDataQueue;
    private int count = 1;

    public PortReader(SerialPort serialPort, BlockingQueue<Load> portDataQueue) {
        this.serialPort = serialPort;
        this.portDataQueue = portDataQueue;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {

        String string = null;


        if (event.isRXCHAR() && event.getEventValue() == 11) { /*Если происходит событие установленной маски и количество байтов в буфере более 0*/
            try {
                byte buffer[] = serialPort.readBytes(11);
                //вытягиваю данные из 11 символов, которые приходят на компорт
                byte buf[] = {buffer[4], buffer[5], buffer[6], buffer[7], buffer[8]};
                string = new String(buf, StandardCharsets.UTF_8);
                System.out.println(count + " : " + string);

                LOGGER.info("Port reported " + string);
                LOGGER.info("PortReader are waiting for putting realData '" + string + "' into the queue(size:" + portDataQueue.size() + ")");
                portDataQueue.put(new Load(count++, Double.valueOf(string)));
                LOGGER.info("PortReader successfully put realData into the queue: " + string);
                //�   0.000!n  -это приходит
                //0.000         -это получаю
            } catch (SerialPortException ex) {
                System.out.println(ex);


                PrintWriter out = null;
                try {
                    FileOutputStream fos = new FileOutputStream("log.txt", true); /*Запись потоков необработанных байтов в текстовый файл. В данном случае создает поток вывода файла, чтобы записать в файл с указанным именем */
                    OutputStreamWriter osr = new OutputStreamWriter(fos, "UTF-8");  /*Создаем объект для вывода данных в указанный поток fos, с учетом заданной кодировки символов*/
                    out = new PrintWriter(osr);

                  //  out.println((key++) + " " + string); /*Запись данных с новой строки*/
//                    dataMap.put(key, string); //добавляю данные в map
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                } finally { /*Обязательное выполнение кода*/
                    if (out != null) {
                        try {
                            out.close();     /*Закрытие файла*/
                        } catch (Exception e) {
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


    }
}