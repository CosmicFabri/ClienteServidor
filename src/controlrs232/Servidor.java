/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlrs232;

/**
 *
 * @author hecmquej
 */
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

class Servidor {

    static final int PUERTO = 6002;

    public Servidor() {
        String cadena = "";
        boolean bandera = true;
        //short dir=(short)0x378;
        try {
            //pPort LPT=new pPort();
            ServerSocket skServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO);

            while (bandera) {
                //ServerSocket skServidor = new ServerSocket(PUERTO);
                Socket skCliente = skServidor.accept();
                InputStream aux = skCliente.getInputStream();
                DataInputStream flujo = new DataInputStream(aux);
                cadena = flujo.readUTF();

                if (cadena.equals("0")) {
                    System.out.println("Cadena RECIBIDA del cliente:" + cadena + " STATUS: APAGADO");
                    JOptionPane.showMessageDialog(null, "SERVIDOR:" + cadena + " STATUS: APAGADO");
                    //LPT.output(dir,(short)Integer.parseInt(cadena));
                    //bandera=false;
                    //skCliente.close();
                } else {
                    System.out.println("Cadena RECIBIDA del cliente:" + cadena + " STATUS: ENCENDIDO");
                    JOptionPane.showMessageDialog(null, "SERVIDOR:" + cadena + " STATUS: ENCENDIDO");
                    //LPT.output(dir,(short)Integer.parseInt(cadena));
                }
                if (cadena.equals("F")) {
                    bandera = false;
                    System.out.println("Servidor Desconectado");
                    skCliente.close();

                }

            }

            System.out.println("Demasiado por hoy");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] arg) {
        new Servidor();
    }
}