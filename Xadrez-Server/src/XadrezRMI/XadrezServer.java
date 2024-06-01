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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raulm
 */
public class XadrezServer extends UnicastRemoteObject implements InterfaceJogo {

    private ArrayList<Cliente> clientes;
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

            mesa.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        // TODO code application logic here
    }

    @Override
    public int registrarCliente(InterfaceCliente cliente, String nome) throws RemoteException {
            boolean player1 = false, player2 = false;
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getNomeUtilizador().equalsIgnoreCase(nome)) {
                    return -1;
                }
            }   int tipo = 0;
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getTipo() == 1) {
                    try {
                        cliente.alteranome(1, clientes.get(i).getNomeUtilizador());
                        player1 = true;
                    } catch (RemoteException ex) {
                        removerCliente(clientes.get(i).getNomeUtilizador());
                        i--;
                    }
                } else if (clientes.get(i).getTipo() == 2) {
                    try {
                        cliente.alteranome(2, clientes.get(i).getNomeUtilizador());
                        player2 = true;
                    } catch (RemoteException ex) {
                        removerCliente(clientes.get(i).getNomeUtilizador());
                        i--;
                    }
                }
                if (clientes.get(i).getTipo() == 3) {
                    try {
                        cliente.adicionaObservador(clientes.get(i).getNomeUtilizador());
                    } catch (RemoteException ex) {
                        removerCliente(clientes.get(i).getNomeUtilizador());
                        i--;
                    }
                }
            }   if (!player1) {
                tipo = 1;
                cliente.alteranome(1, nome);
                for (int o = 0; o < clientes.size(); o++) {
                    try {
                        clientes.get(o).getReferencia().alteranome(1, nome);
                    } catch (RemoteException ex) {
                        removerCliente(clientes.get(o).getNomeUtilizador());
                        o--;
                    }
                }
                
            } else if (!player2) {
                tipo = 2;
                cliente.alteranome(2, nome);
                for (int o = 0; o < clientes.size(); o++) {
                    try {
                        clientes.get(o).getReferencia().alteranome(2, nome);
                    } catch (RemoteException ex) {
                        removerCliente(clientes.get(o).getNomeUtilizador());
                        o--;
                    }
                }
                
            } else {
                tipo = 3;
                for (int o = 0; o < clientes.size(); o++) {
                    try {
                        clientes.get(o).getReferencia().adicionaObservador(nome);
                    } catch (RemoteException ex) {
                        removerCliente(clientes.get(o).getNomeUtilizador());
                        o--;
                    }
                }
                cliente.adicionaObservador(nome);
                
            }
            clientes.add(new Cliente(cliente, tipo, nome));
            for (int i = 0; i < clientes.size(); i++) {
                try {
                    clientes.get(i).getReferencia().escreverChat(" " + nome + " entrou no jogo");
                } catch (RemoteException ex) {
                    removerCliente(clientes.get(i).getNomeUtilizador());
                    i--;
                }
            }   
            System.out.println("Um cliente entrou no jogo com o nome " + nome + ", este cliente e do tipo " + tipo);
            atualizarClientes();
            return tipo;
    }

    @Override
    public void atualizaPosicao(int posInicial, int posFinal, String nome) throws RemoteException  {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNomeUtilizador().equals(nome)) {
                System.out.println("A posicao do cliente \"" + nome + "\" foi alterada de " + posInicial + " para " + posFinal);
                clientes.get(i).setTipo(posFinal);
            }
        }
        switch (posInicial) {
            case 1:
                if (posFinal == 2) {
                    for (int i = 0; i < clientes.size(); i++) {
                        try {
                            clientes.get(i).getReferencia().alteranome(1, "Posicao1");
                            clientes.get(i).getReferencia().alteranome(2, clientes.get(i).getNomeUtilizador());
                        } catch (RemoteException ex) {
                            removerCliente(clientes.get(i).getNomeUtilizador());
                            i--;
                        }
                    }
                } else {
                    for (int i = 0; i < clientes.size(); i++) {
                        try {
                            clientes.get(i).getReferencia().alteranome(1, "Posicao1");
                            clientes.get(i).getReferencia().adicionaObservador(nome);
                        } catch (RemoteException ex) {
                            removerCliente(clientes.get(i).getNomeUtilizador());
                            i--;
                        }
                    }
                }
                break;
            case 2:
                if (posFinal == 1) {
                    for (int i = 0; i < clientes.size(); i++) {
                        try {
                            clientes.get(i).getReferencia().alteranome(2, "Posicao2");
                            clientes.get(i).getReferencia().alteranome(1, nome);
                        } catch (RemoteException ex) {
                            removerCliente(clientes.get(i).getNomeUtilizador());
                            i--;
                        }
                    }
                } else {
                    for (int i = 0; i < clientes.size(); i++) {
                        try {
                            clientes.get(i).getReferencia().alteranome(2, "Posicao2");
                            clientes.get(i).getReferencia().adicionaObservador(nome);
                        } catch (RemoteException ex) {
                            removerCliente(clientes.get(i).getNomeUtilizador());
                            i--;
                        }
                    }
                }
                break;
            case 3:
                if (posFinal == 1) {
                    for (int i = 0; i < clientes.size(); i++) {
                        try {
                            clientes.get(i).getReferencia().removerObservador(nome);
                            clientes.get(i).getReferencia().alteranome(1, nome);
                        } catch (RemoteException ex) {
                            removerCliente(clientes.get(i).getNomeUtilizador());
                            i--;
                        }
                    }
                } else {
                    for (int i = 0; i < clientes.size(); i++) {
                        try {
                            clientes.get(i).getReferencia().removerObservador(nome);
                            clientes.get(i).getReferencia().alteranome(2, nome);
                        } catch (RemoteException ex) {
                            removerCliente(clientes.get(i).getNomeUtilizador());
                            i--;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void moverPecaServidor(ArrayList<Peca> pecasTabuleiro, ArrayList<Peca> pecasForaBrancas, ArrayList<Peca> pecasForaPretas,String posInicial, String posFinal) throws RemoteException {
        
        if(posFinal.equals("Organizado")){
            System.out.println("O tabuleiro foi arrumado.");
        }
        else if(posFinal.equals("Limpo")){
            System.out.println("O tabuleiro foi limpo.");
        }
        else {
            System.out.println(posInicial + "-" + posFinal);
        }
        mesa.setPiecesPosition(pecasTabuleiro);
        mesa.setPecasFora(pecasForaBrancas, pecasForaPretas);
        atualizarClientes();
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
    public void organizaPecas() throws RemoteException {
        mesa.organizaPecas();
        System.out.println("As peças foram arrumadas.");
        atualizarClientes();
    }   

    public void atualizarClientes() throws RemoteException  {
        ArrayList<Peca> pecasTabuleiro = mesa.getPiecesPosition();
        ArrayList<Peca> pecasForaBrancas = mesa.getPecasBrancas();
        ArrayList<Peca> pecasForaPretas = mesa.getPecasPretas();
        for (int i = 0; i < clientes.size(); i++) {
            try {
                clientes.get(i).getReferencia().atualizarTabuleiro(pecasTabuleiro, pecasForaBrancas, pecasForaPretas);
            } catch (RemoteException ex) {
                removerCliente(clientes.get(i).getNomeUtilizador());
                i--;
            }
        }
    }

    @Override
    public void removerCliente(String nome)  {
        int tipoAux = 0;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNomeUtilizador().equals(nome)) {
                System.out.println("O cliente com o nome " + nome + " saiu do jogo.");
                tipoAux = clientes.get(i).getTipo();
                clientes.remove(i);
            }
        }
        for (int i = 0; i < clientes.size(); i++) {
            try{
                enviarMensagem("Saiu do jogo",nome);
                if (tipoAux != 3) {
                    clientes.get(i).getReferencia().alteranome(tipoAux, "Posicao" + tipoAux);
                } else {
                    clientes.get(i).getReferencia().removerObservador(nome);
                } 
            } catch (RemoteException ex) {
                removerCliente(clientes.get(i).getNomeUtilizador());
                i--;
            }

        }
    }

    @Override
    public void enviarMensagem(String mensagem, String nome) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String hora = now.format(formatter);
        for (int i = 0; i < clientes.size(); i++) {
            try {
                clientes.get(i).getReferencia().escreverChat("[" + hora + "] " + nome + ": " + mensagem);
            } catch (RemoteException ex) {
                removerCliente(clientes.get(i).getNomeUtilizador());
                i--;
            }
        }
    }

}
