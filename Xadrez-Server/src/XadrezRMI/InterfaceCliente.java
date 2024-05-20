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
    
    void atualizarTabuleiro(ArrayList<Peca> pecas) throws RemoteException;
    void alteranome(int player,String nome) throws RemoteException;
    void adicionaObservador(String nome) throws RemoteException;
    int getTipo() throws RemoteException;
    void setTipo(int tipo) throws RemoteException;
    InterfaceCliente getReferencia() throws RemoteException;
    String getNomeUtilizador() throws RemoteException;
    void movePecas(int inicialX,int inicialY,int finalX,int finalY,int tipo,int cor) throws RemoteException;

}
