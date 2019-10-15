/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets.chat;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Servidor extends javax.swing.JFrame {

  private ServerSocket server;
  private final int PUERTOH=1000;
  
    public Servidor() {
        initComponents();
        
        try{
            server=new ServerSocket (PUERTOH);//recibe el puerto para la conexión
            mensajeria("*** SERVIDOR CON CONEXION *** \n");
            
            super.setVisible(true);
            
            while (true){
                Socket cliente = server.accept();
                mensajeria ("Clente conectado desde la dirección: "+cliente.getInetAddress().getHostAddress());
                DataInputStream entrada = new DataInputStream (cliente.getInputStream());
                HiloServidor hilo = new HiloServidor (cliente, entrada.readUTF(),this);
                hilo.start();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog (this, e.toString());
        }
    }
        
        public void mensajeria (String msg){
            this.jTextArea1.append(" "+msg+ "\n");
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new Servidor();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
