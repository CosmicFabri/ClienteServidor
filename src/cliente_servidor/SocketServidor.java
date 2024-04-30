package cliente_servidor;

/**
 *
 * @author Miguel Alejandro
 * @author Fabrizio Guzmán
 * @author Eduardo Naal
 */
import java.io.*;
import java.net.*;

class SocketServidor {

    static private final int PUERTO = 6002;
    private ServerSocket socketServidor;

    /**
     * Inicializa socketServidor, que escucha en el puerto 6002
     */
    public SocketServidor() {
        try {
            socketServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho en el puerto " + PUERTO);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Acepta las conexiones con el cliente y recibe los datos La conexión
     * termina cuando el cliente manda un caracter "F" o "f"
     */
    public void iniciarConexion() {
        String cadena = "";
        boolean bandera = true;

        try {
            // Crear el socket para comunciarse con el servidor
            Socket socketCliente = socketServidor.accept();

            while (bandera) {
                // Flujo de datos que se recibirá del cliente
                InputStream aux = socketCliente.getInputStream();
                // Lectura del flujo de datos
                DataInputStream flujo = new DataInputStream(aux);
                cadena = flujo.readUTF();

                System.out.println("Cadena RECIBIDA del cliente: " + cadena);

                if (cadena.equalsIgnoreCase("f")) {
                    System.out.println("Servidor Desconectado");
                    socketCliente.close();
                    bandera = false;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
