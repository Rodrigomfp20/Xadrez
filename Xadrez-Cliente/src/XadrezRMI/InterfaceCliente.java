/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package XadrezRMI;

import java.rmi.Remote;
import java.rmi.*;

/**
 *
 * @author raulm
 */
public interface InterfaceCliente extends Remote {
    
    
    void notificar(String mensagem) throws RemoteException;
    void alteranome(int player,String nome) throws RemoteException;
    void adicionaObservador(String nome) throws RemoteException;
    int getTipo() throws RemoteException;
    void setTipo(int tipo) throws RemoteException;
    InterfaceCliente getReferencia()throws RemoteException;
    String getNomeUtilizador()throws RemoteException;
}
