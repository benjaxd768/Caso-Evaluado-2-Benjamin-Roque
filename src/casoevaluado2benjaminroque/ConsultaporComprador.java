/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoevaluado2benjaminroque;

import javax.swing.JOptionPane;

/**
 *
 * @author benja
 */
public class ConsultaporComprador {

    //Loop para buscar los boletos que ha comprado el comprador 
    public void consultarComprador(Boleto[][] boletos) {
        String dato = JOptionPane.showInputDialog(
                "Digite el nombre o teléfono del comprador:");
        String texto = "Boletos:\n\n";
        int encontrados = 0;

        for (int fila = 0; fila < boletos.length; fila++) {
            for (int columna = 0; columna < boletos[fila].length; columna++) {
                Boleto boleto = boletos[fila][columna];
                if (!boleto.isDisponible()
                        && (boleto.getComprador().equalsIgnoreCase(dato)
                        || boleto.getTelefonoComprador().equals(dato))) {
                    texto += boleto.getNumero() + " ";
                    encontrados++;
                }
            }
        }

        if (encontrados == 0) {
            texto = "El comprador no tiene boletos.";
        }
        JOptionPane.showMessageDialog(null, texto);
    }
}
