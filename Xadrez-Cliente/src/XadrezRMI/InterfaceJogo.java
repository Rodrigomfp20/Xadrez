package XadrezRMI;


import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */


/**
 *
 * @author raulm
 */
public interface InterfaceJogo extends Remote {

    public void registrarCliente(InterfaceCliente cliente) throws RemoteException;
    
    public void moverPecaServidor(int inicialX,int inicialY,int finalX,int finalY,int tipo,int cor) throws RemoteException;
    
    public void organizaPecas() throws RemoteException;


}
