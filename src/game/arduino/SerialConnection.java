package game.arduino;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

import static jssc.SerialPort.MASK_RXCHAR;

public class SerialConnection{

    SerialPort arduinoPort = null;
    ObservableList<String> portList;
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(SerialConnection.class);
    private static final Marker Readings = MarkerFactory.getMarker("SYS");
    private static int buttonC;
    private static int buttonZ;
    private static int joyStickX;
    private static int joyStickY;

    private void detectPort(){

        portList = FXCollections.observableArrayList();

        String[] serialPortNames = SerialPortList.getPortNames();
        for(String name: serialPortNames){
            System.out.println(name);
            portList.add(name);
        }
    }

    public void arduinoConnections() {

        detectPort();
        disconnectArduino();
        connectArduino("/dev/ttyUSB0");

    }

    public boolean connectArduino(String port){

        System.out.println("connectArduino");

        boolean success = false;
        SerialPort serialPort = new SerialPort(port);
        try {
            serialPort.openPort();
            serialPort.setParams(
                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setEventsMask(MASK_RXCHAR);
            serialPort.addEventListener((SerialPortEvent serialPortEvent) -> {
                if(serialPortEvent.isRXCHAR()){
                    try {
                        byte[] b = serialPort.readBytes();
                        //System.out.println(b[0]);
                        //System.out.println(b[1]);
                        //System.out.println("X: " + b[2]);
                        //System.out.println("Y: " + b[3]);
                        buttonC = b[0];
                        buttonZ = b[1];
                        joyStickX = b[2];
                        joyStickY = b[3];
                        System.out.println(joyStickX);

                    } catch (SerialPortException ex) {
                        Logger.getLogger(SerialConnection.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }

                }
            });

            arduinoPort = serialPort;
            success = true;
        } catch (SerialPortException ex) {
            Logger.getLogger(SerialConnection.class.getName())
                    .log(Level.SEVERE, null, ex);
            System.out.println("SerialPortException: " + ex.toString());
        }

        return success;
    }

    public void disconnectArduino(){
        System.out.println("disconnectArduino()");
        if(arduinoPort != null){
            try {
                arduinoPort.removeEventListener();

                if(arduinoPort.isOpened()){
                    arduinoPort.closePort();
                }

            } catch (SerialPortException ex) {
                Logger.getLogger(SerialConnection.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getJoyStickLeft(){
        int result = 0;
        if(joyStickX > 30 && joyStickX < 118){
            result = -1;
        }
        logger.info(Readings, "Left: " + result);
        return result;
    }

    public int getJoyStickRight(){
        int result = 0;
        if (joyStickX < -30 && joyStickX > -120){
            result = 1;
        }
        logger.info(Readings, "Right: " + result);
        return result;
    }

    public int getJoyStickUp(){
        int result = 0;
        if (joyStickY > -120 && joyStickY < -30){
            result = 1;
        }

        logger.info(Readings, "Up: " + result);
        return result;
    }

    public int getJoyStickDown(){
        int result = 0;
        if(joyStickY < 118 && joyStickY > 30){
            result = -1;
        }
        logger.info(Readings, "Down: " + result);
        return result;
    }

    public int getButtonC(){
        return buttonC;
    }

    public int getButtonZ(){
        return buttonZ;
    }


}