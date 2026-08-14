/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoevaluado2benjaminroque;

/**
 *
 * @author benja
 */
public class Boleto {

    //Defini variables para obtener el numero del comprador y el nombre, un verdadero o falso de si el boleto esta disponible
    private int numero;
    private boolean disponible;
    private String comprador;
    private String telefonoComprador;

    //Constructores setters y getters
    public Boleto(int numero) {
        this.numero = numero;
        disponible = true;
        comprador = "";
        telefonoComprador = "";
    }

    public void vender(String nombre, String telefono) {
        comprador = nombre;
        telefono = telefonoComprador;
        disponible = false;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getTelefonoComprador() {
        return telefonoComprador;
    }

    public void setTelefonoComprador(String telefonoComprador) {
        this.telefonoComprador = telefonoComprador;
    }

}
