/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * Esta clase no tiene atributos como tal porque la creación de sockets y flujos 
 * se realizan en el interior del hilo que maneja las conexiones
 * @author Fabrizio
 */
public class InterfazServidor extends javax.swing.JFrame {   
    /**
     * Creates new form InterfazServidor
     */
    public InterfazServidor() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        TxtClient = new javax.swing.JTextField();
        ServerTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TextPuertoRed = new javax.swing.JTextField();
        BtnActivateServer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextDatosRecibidos = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Datos Recibidos");

        jButton1.setText("Enviar a Cliente");

        ServerTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ServerTitle.setText("Aplicación Servidor");

        jLabel1.setText("Puerto de Red");

        TextPuertoRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPuertoRedActionPerformed(evt);
            }
        });

        BtnActivateServer.setText("Activar Server");
        BtnActivateServer.setFocusable(false);
        BtnActivateServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActivateServerActionPerformed(evt);
            }
        });

        TextDatosRecibidos.setEditable(false);
        TextDatosRecibidos.setColumns(20);
        TextDatosRecibidos.setRows(5);
        TextDatosRecibidos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TextDatosRecibidos.setFocusable(false);
        jScrollPane1.setViewportView(TextDatosRecibidos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ServerTitle)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TextPuertoRed, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnActivateServer))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(29, 29, 29)
                        .addComponent(TxtClient, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(ServerTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TextPuertoRed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnActivateServer))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(TxtClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Clase que permite manejar la conexión en un hilo separado al principal
    // TODO: manejar el envío de datos al cliente
    private class ManejarConexion extends Thread {
        private int puertoRed;
        private ServerSocket serverSocket;
        private DataInputStream dataInput;

        public ManejarConexion(int puertoRed) {
            this.puertoRed = puertoRed;
        }

        @Override
        public void run() {
            try {
                // Iniciar el socket servidor en el puerto "puertoRed"
                serverSocket = new ServerSocket(puertoRed);
                System.out.println("Servidor iniciado en el puerto " + Integer.toString(puertoRed));
                String mensajeRecibido = "";
                
                // Aceptamos la conexión del cliente
                Socket socket = serverSocket.accept();
                // Obtenemos el flujo de entrada de datos del cliente
                dataInput = new DataInputStream(socket.getInputStream());

                while (!mensajeRecibido.equalsIgnoreCase("exit")) {
                    mensajeRecibido = dataInput.readUTF();
                    TextDatosRecibidos.setText(TextDatosRecibidos.getText() + mensajeRecibido + "\n");
                    System.out.println(mensajeRecibido);
                }
                
                // Después de que se reciba "exit" para cerrar la conexión
                socket.close();
                dataInput.close();
                System.out.println("Servidor Desconectado");
                JOptionPane.showInternalMessageDialog(null, "Servidor desconectado", "Servidor desconectado", JOptionPane.INFORMATION_MESSAGE);
               
                // Cerrar la ventana y volverla a abrir
                dispose();
                InterfazServidor nuevaVentana = new InterfazServidor();
                nuevaVentana.setVisible(true);
            } catch (Exception e) {
                // Manejar la excepción
            }
        }
    }

    // Recuperar el valor del puerto de red y crear un hilo que maneje la conexión
    private void BtnActivateServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActivateServerActionPerformed
        String puertoUsuario = TextPuertoRed.getText();
        int puertoRed = Integer.parseInt(puertoUsuario);

        try {
            // Iniciar el hilo que maneja la conexión
            ManejarConexion hiloConexion = new ManejarConexion(puertoRed);
            hiloConexion.start();
        } catch (Exception e) {
            // Manejar la excepción
        }
    }//GEN-LAST:event_BtnActivateServerActionPerformed

    private void TextPuertoRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPuertoRedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPuertoRedActionPerformed

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
             */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(InterfazServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(InterfazServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(InterfazServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(InterfazServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new InterfazServidor().setVisible(true);
                }
            });
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActivateServer;
    private javax.swing.JLabel ServerTitle;
    private javax.swing.JTextArea TextDatosRecibidos;
    private javax.swing.JTextField TextPuertoRed;
    private javax.swing.JTextField TxtClient;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
