/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package XadrezRMI;

import java.rmi.Remote;
import java.rmi.*;
import java.util.ArrayList;

/**
 *
 * @author raulm
 */
public interface InterfaceCliente extends Remote {

    public void atualizarTabuleiro(ArrayList<Peca> pecasTabuleiro, ArrayList<Peca> pecasForaBrancas, ArrayList<Peca> pecasForaPretas) throws RemoteException;

    public void alteranome(int player, String nome) throws RemoteException;

    public void adicionaObservador(String nome) throws RemoteException;

    public void removerObservador(String nome) throws RemoteException;
    
    public void escreverChat(String mensagem) throws RemoteException;
    
    

}
