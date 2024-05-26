package XadrezRMI;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */


/**
 *
 * @author raulm
 */
public interface InterfaceJogo extends Remote {

    public boolean registrarCliente(InterfaceCliente cliente) throws RemoteException;
    
    public void moverPecaServidor(ArrayList<Peca> pecasTabuleiro,ArrayList<Peca> pecasForaBrancas,ArrayList<Peca> pecasForaPretas) throws RemoteException;
    
    public void organizaPecas() throws RemoteException;


}
