/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package XadrezRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author raulm
 */
public interface InterfaceJogo extends Remote {
    
    public void ola() throws RemoteException;
    public void registrarCliente(InterfaceCliente cliente) throws RemoteException;
    public void moverPecaServidor(InterfaceCliente cliente,ChessGUI tabuleiro, TabelaGUI[] pecasForaBranco, TabelaGUI pecasForaPreto) throws RemoteException;
}
