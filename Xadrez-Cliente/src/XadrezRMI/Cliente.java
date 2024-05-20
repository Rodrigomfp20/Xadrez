/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XadrezRMI;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.util.ArrayList;


/**
 *
 * @author raulm
 */
public class Cliente extends UnicastRemoteObject implements InterfaceCliente {
    private InterfaceCliente refCliente;
    private String nomeUtilizador;
    private int tipo; // 1 - Jogador 1 | 2 - Jogador 2 | 3 - Observador 
    private static Mesa Jogo;


    
    Cliente(String nomeUtilizador,InterfaceJogo refServidor) throws RemoteException{
        super();
        this.nomeUtilizador = nomeUtilizador;
        Jogo = new Mesa(refServidor);
        Jogo.setVisible(true);
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
    public void alteranome(int player,String nome) throws RemoteException {
        if(player == 1){
            Jogo.setNome1(nome);
        }
        else{
            Jogo.setNome2(nome);

        }
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

    @Override
    public void adicionaObservador(String nome) throws RemoteException {
        Jogo.addObservador(nome);
    }
    @Override
    public void movePecas(int inicialX,int inicialY,int finalX,int finalY,int tipo,int cor) throws RemoteException{
        Jogo.setPecas(inicialX,inicialY,finalX,finalY,tipo,cor);
    }

    @Override
    public void atualizarTabuleiro(ArrayList<Peca> pecas) throws RemoteException {
        Jogo.setPiecesPosition(pecas);
    }
    
}
