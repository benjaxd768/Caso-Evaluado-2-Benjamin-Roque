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
public class Estadistica {

    //defini el precio de los boletos para posteriormemte hacer la matriz con 100 boletos en total, definimos variables para sacar las estadisticas de la rifa 
    private final int precioBoleto = 2000;

    public void mostrarEstadisticas(Boleto[][] boletos) {
        int vendidos = contarVendidos(boletos);
        int disponibles = 100 - vendidos;
        double porcentaje = vendidos;
        int recaudado = vendidos * precioBoleto;

        String texto = "Stats de la rifa\n\n"
                + "Boletos vendidos: " + vendidos
                + "\nBoletos disponibles: " + disponibles
                + "\nPorcentaje vendido: " + String.format("%.2f", porcentaje) + "%"
                + "\nDinero recaudado: ₡" + recaudado;
        JOptionPane.showMessageDialog(null, texto);
    }

    public int contarVendidos(Boleto[][] boletos) {
        int vendidos = 0;
        for (int fila = 0; fila < boletos.length; fila++) {
            for (int columna = 0; columna < boletos[fila].length; columna++) {
                if (!boletos[fila][columna].isDisponible()) {
                    vendidos++;
                }
            }
        }
        return vendidos;
    }

}
