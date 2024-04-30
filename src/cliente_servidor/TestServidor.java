/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente_servidor;

/**
 *
 * @author miguel
 */
public class TestServidor {

    public static void main(String[] args) {
        SocketServidor servidor = new SocketServidor();
        servidor.iniciarConexion();
    }
}
