/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente_servidor;

import java.io.IOException;

/**
 *
 * @author miguel
 */
public class TestCliente {

    public static void main(String[] args) throws IOException {
        SocketCliente cliente = new SocketCliente();
        cliente.iniciarConexion();
    }
}
