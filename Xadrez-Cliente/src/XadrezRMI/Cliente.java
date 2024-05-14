/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XadrezRMI;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;


/**
 *
 * @author raulm
 */
public class Cliente extends UnicastRemoteObject implements InterfaceCliente {
    private InterfaceCliente refCliente;
    private String nomeUtilizador;
    private int tipo; // 1 - Jogador 1 | 2 - Jogador 2 | 3 - Observador 
    private static IniciarJogador Jogo;

    
    Cliente(String nomeUtilizador) throws RemoteException{
        super();
        this.nomeUtilizador = nomeUtilizador;
    }
    
    Cliente(InterfaceCliente refCliente,int tipo,String nomeUtilizador) throws RemoteException{
        super();
        this.refCliente = refCliente;
        this.tipo = tipo;
        this.nomeUtilizador = nomeUtilizador;
    }
    @Override
    public void setTipo(int tipo)throws RemoteException {
        this.tipo = tipo;
    }
    

    @Override
    public void notificar(String mensagem) throws RemoteException {
        System.out.println("Notificação recebida: " + mensagem);
    }
    @Override
    public void alteranome(int player,String nome) throws RemoteException {
        Jogo.alteraNome(player,nome);
    }
    
    @Override
    public String getNomeUtilizador() throws RemoteException{
        return nomeUtilizador;
    }

    @Override
    public int getTipo() throws RemoteException{
        return tipo;
    }
    @Override
    public InterfaceCliente getReferencia() throws RemoteException{
        return refCliente;
    }
    public static void main(String[] args) {
        Jogo = new IniciarJogador();
        Jogo.setVisible(true);
    }

    @Override
    public void adicionaObservador(String nome) throws RemoteException {
        Jogo.adicionaEspetador(nome);
    }
    
}
