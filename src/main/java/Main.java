import com.fazecast.jSerialComm.SerialPort;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard_scanner = new Scanner(System.in);

        int port_number = 0;

        System.out.println("All ports available ::");

//        I'm using the fazecast jSerialComm library for Serial Connections
//        rs322 is a serial communications protocol, so this is a good fit.

        SerialPort serialPort[] = SerialPort.getCommPorts();

        System.out.println("Choose a serial port .. ");

//        This will display all detected ports outside the windows system
        for (SerialPort port: serialPort){
            System.out.println(port_number + 1 + ". " + port.getSystemPortName());
        }

        int working_port = keyboard_scanner.nextInt() - 1;

        System.out.println("You have chosen port:: " + serialPort[working_port].getSystemPortName());

        System.out.println("opening port ...");

//        Check if the selected prt is open
        if (serialPort[working_port].openPort()){

//            Open a stable comm channel with a timeout of 0 secs (open virtually forever)
            serialPort[working_port].setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

//            Display the chosen port if it is open for communication
            System.out.println(serialPort[working_port].getSystemPortName() + " is now open for communication");
        } else
            System.out.println("The port is busy ...");

        Scanner port_scanner = new Scanner(serialPort[working_port].getInputStream());

//        Print to console every piece of data received from serial port
        while (port_scanner.hasNext()){
            System.out.println(port_scanner.nextLine());
        }
        
    }

}
