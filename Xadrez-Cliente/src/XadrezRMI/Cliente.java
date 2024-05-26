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
        Jogo = new Mesa(refServidor,this);
        Jogo.setTitle("Xadrez -> " + nomeUtilizador);
        
    }
    
    Cliente(InterfaceCliente refCliente,int tipo,String nomeUtilizador) throws RemoteException{
        super();
        this.refCliente = refCliente;
        this.tipo = tipo;
        this.nomeUtilizador = nomeUtilizador;
    }
    public void mostrarJanela(){
        Jogo.setVisible(true);
    }
    
    public void setTipo(int tipo){
        this.tipo = tipo;
        if(Jogo != null){
            if(tipo == 3){
                Jogo.setPlayable(false);
            
            }
            else{
                Jogo.setPlayable(true);
            }
        }
        
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
    
    public String getNomeUtilizador(){
        return nomeUtilizador;
    }

    public int getTipo(){
        return tipo;
    }

    public InterfaceCliente getReferencia(){
        return refCliente;
    }

    @Override
    public void adicionaObservador(String nome) throws RemoteException {
        Jogo.addObservador(nome);
    }
    
    @Override
    public void removerObservador(String nome) throws RemoteException {
        Jogo.removerObservador(nome);
    }

    @Override
    public void atualizarTabuleiro(ArrayList<Peca> pecasTabuleiro,ArrayList<Peca> pecasForaBrancas,ArrayList<Peca> pecasForaPretas) throws RemoteException {
        Jogo.setPiecesPosition(pecasTabuleiro);
        Jogo.setPecasFora(pecasForaBrancas, pecasForaPretas);
        
    }

    @Override
    public void escreverChat(String mensagem) throws RemoteException {
        Jogo.escreveMensagem(mensagem);
    }
    
}
