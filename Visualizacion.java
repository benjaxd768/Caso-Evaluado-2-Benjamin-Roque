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
public class Visualizacion {

    public void mostrarTalonario(Boleto[][] boletos) {
        String texto = "Talonario de la rifa\n";
        for (int fila = 0; fila < boletos.length; fila++) {
            for (int columna = 0; columna < boletos[fila].length; columna++) {
                if (boletos[fila][columna].isDisponible()) {
                    texto += String.format("%02d  ", boletos[fila][columna].getNumero());
                } else {
                    texto += " X   ";
                }
            }
            texto += "\n";
        }
        JOptionPane.showMessageDialog(null, texto, "Talonario",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
