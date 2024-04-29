/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlrs232;

/**
 *
 * @author Miguel Alejandro
 * @author Fabrizio Guzmán
 * @author Eduardo Naal
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SocketCliente {

    Scanner leer = new Scanner(System.in);
    String cadena = "";
    boolean bandera = true;

    public static void main(String[] args) {
        new SocketCliente();
    }

    /**
     * Crea el socket cliente y lee los datos
     */
    public SocketCliente() {
        try {

            //Socket socket = new Socket ("127.0.0.1", 5000);
            System.out.println("conectado");

            while (bandera) {
                Socket socket = new Socket("127.0.0.1", 6002);

                DataOutputStream buffer = new DataOutputStream(socket.getOutputStream());
                System.out.println("Dame valor digital");
                cadena = leer.nextLine();
                buffer.writeUTF(cadena);
                System.out.println("Enviado:" + cadena);
                if (cadena.equals("F")) {
                    bandera = false;
                    System.out.println("Good Byee");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}