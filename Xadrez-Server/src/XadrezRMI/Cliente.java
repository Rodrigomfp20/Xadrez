/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XadrezRMI;

/**
 *
 * @author raulm
 */
public class Cliente {
    private InterfaceCliente  refCliente;
    
    Cliente(InterfaceCliente refCliente){
        this.refCliente = refCliente;
    }
    
    public InterfaceCliente getRefCliente() {
        return refCliente;
    }
}
