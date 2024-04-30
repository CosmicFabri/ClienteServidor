/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_servidor;

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

    private Socket socket;
    private final int PUERTO = 6002;
    private final String HOST = "127.0.0.1";

    /**
     * Inicializar el socket con la dirección del host y puerto establecidos
     */
    public SocketCliente() throws IOException {
        try {
            socket = new Socket(HOST, PUERTO);
        } catch (ConnectException e) {
            System.out.println("Probablemente no has iniciado el servidor");
            e.printStackTrace();
        }
    }

    /**
     * 
     * La conexión se cierra si el cliente manda un caracter "F"
     */
    public void iniciarConexion() {
        Scanner leer = new Scanner(System.in);
        String cadena = "";
        boolean bandera = true;

        try {
            while (bandera) {
                // Flujo de datos para enviar el mensaje al cliente
                DataOutputStream buffer = new DataOutputStream(socket.getOutputStream());
                
                System.out.println("Dame el valor para enviar al servidor: ");
                cadena = leer.nextLine();
                
                // Enviar la cadena al servidor
                buffer.writeUTF(cadena);
                System.out.println("Mensaje enviado: " + cadena);

                if (cadena.equalsIgnoreCase("f")) {
                    bandera = false;
                    System.out.println("Conexión finalizada");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
