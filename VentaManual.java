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
public class VentaManual {

    public void venderBoleto(Boleto[][] boletos) {
        int numero = Integer.parseInt(JOptionPane.showInputDialog(
                "Digite el número que desea comprar (0 al 99):"));

        if (numero < 0 || numero > 99) {
            JOptionPane.showMessageDialog(null, "El número debe estar entre 0 y 99.");
        } else {
            Boleto boleto = buscarBoleto(boletos, numero);
            if (!boleto.isDisponible()) {
                JOptionPane.showMessageDialog(null, "El boleto ya fue vendido.");
            } else {
                String nombre = JOptionPane.showInputDialog("Nombre del comprador:");
                String telefono = JOptionPane.showInputDialog("Teléfono del comprador:");
                boleto.vender(nombre, telefono);
                JOptionPane.showMessageDialog(null, "Compra realizada. Número: " + numero);
            }
        }
    }

    public Boleto buscarBoleto(Boleto[][] boletos, int numero) {
        Boleto encontrado = null;
        for (int fila = 0; fila < boletos.length; fila++) {
            for (int columna = 0; columna < boletos[fila].length; columna++) {
                if (boletos[fila][columna].getNumero() == numero) {
                    encontrado = boletos[fila][columna];
                }
            }
        }
        return encontrado;
    }
}
