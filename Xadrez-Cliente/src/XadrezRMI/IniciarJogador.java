package XadrezRMI;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author rodrigomfp20011
 */
public class IniciarJogador extends javax.swing.JFrame {
    private Cliente cliente;
    private InterfaceJogo objRemoto;
    private Mesa mesa;

    /**
     * Creates new form IniciarJogador
     */
    public IniciarJogador() {
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

        jLabel1 = new javax.swing.JLabel();
        ip = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        porto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        entrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(310, 330));
        setResizable(false);

        jLabel1.setText("IP do Servidor");

        jLabel2.setText("Porto");

        jLabel3.setText("Nome do Utilizador");

        entrar.setText("Entrar");
        entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(ip)
                    .addComponent(porto)
                    .addComponent(nome, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addComponent(entrar)
                .addGap(114, 114, 114))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(porto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(entrar)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entrarActionPerformed
    // TODO add your handling code here:
    String ip = this.ip.getText();
    int porto = Integer.parseInt(this.porto.getText());
    String nome = this.nome.getText();
        try {
            InetAddress ia = InetAddress.getByName(ip);
            Registry reg = LocateRegistry.getRegistry(ia.getHostAddress(), porto);
            objRemoto = (InterfaceJogo) reg.lookup("Xadrez");
            this.setVisible(false);
            cliente = new Cliente(nome,objRemoto);
            if(!objRemoto.registrarCliente(cliente)){
                JOptionPane.showMessageDialog(this, "Ja existe um utilizador com o seu nome, altere o nome e tente novamente","Erro - Nome Igual",JOptionPane.WARNING_MESSAGE);
                this.setVisible(true);
            }
            else{
                System.out.println(cliente.getTipo());
                JOptionPane.showMessageDialog(this, "Conectado com sucesso","Sucesso",JOptionPane.INFORMATION_MESSAGE);
                cliente.mostrarJanela();
            }
           
        } catch (UnknownHostException ex) {
            Logger.getLogger(IniciarJogador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(IniciarJogador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(IniciarJogador.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }//GEN-LAST:event_entrarActionPerformed

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
            java.util.logging.Logger.getLogger(IniciarJogador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IniciarJogador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IniciarJogador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IniciarJogador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IniciarJogador().setVisible(true);
            }
        });
    }
    public Cliente getCliente(){
        return cliente;
    }
    public InterfaceJogo getObj(){
        return objRemoto;
    }
    public void alteraNome(int player,String nome){
        if(player == 1){
            mesa.setNome1(nome);
        }
        else{
            mesa.setNome2(nome);
        }
        
    }
    public void adicionaEspetador(String nome){
        mesa.addObservador(nome);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton entrar;
    private javax.swing.JTextField ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField porto;
    // End of variables declaration//GEN-END:variables
}
