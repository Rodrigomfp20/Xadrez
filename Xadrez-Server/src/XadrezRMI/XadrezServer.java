/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package XadrezRMI;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author raulm
 */
public class XadrezServer extends UnicastRemoteObject implements InterfaceJogo {

    private static ArrayList<Cliente> clientes;
    private static Mesa mesa;

    

    XadrezServer() throws RemoteException {
        super();
        clientes = new ArrayList();
        mesa = new Mesa();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int port = lerDadosInt("Introduza o porto no qual deseja abrir o servidor");
            Registry reg = LocateRegistry.createRegistry(port);
            System.out.println("IP do servidor RMI: " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Porto do servidor RMI: " + port);
            XadrezServer serv = new XadrezServer();
            reg.rebind("Xadrez", serv);
            System.out.println("Servidor Iniciado");
        } catch (Exception ex) {
            System.out.println("Ocorreu um erro ao tentar aceder ao RMI Registry.");
        }
        // TODO code application logic here
    }

    public synchronized Mesa joinCliente(InterfaceCliente refCliente) throws RemoteException {
        clientes.add(new Cliente(refCliente));
        return mesa;
    }

    public synchronized Cliente encontraCliente(InterfaceCliente refCliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getRefCliente().equals(refCliente)) {
                return clientes.get(i);
            }
        }
        return null;
    }

    private static String lerDados(String aMensagem) {
        System.out.println(aMensagem);
        Scanner teclado = new Scanner(System.in);
        String linha = teclado.nextLine();
        while (linha.isBlank()) {
            System.out.println("Devera introduzir algo.");
            System.out.println(aMensagem);
            linha = teclado.nextLine();
        }
        return linha;
    }

    private static int lerDadosInt(String aMensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerDados(aMensagem));
            } catch (NumberFormatException nfe) {
                System.out.println("Devera digitar um numero.");
            }
        }
    }

    @Override
    public void ola() throws RemoteException {
        System.out.println("Ola do cliente");
    }

}
