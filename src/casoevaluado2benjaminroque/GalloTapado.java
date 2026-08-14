/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoevaluado2benjaminroque;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author benja
 */
public class GalloTapado {

    private int rifaTodos;
    private int rifaMayor;
    private int primeraVariable = 20;
    private Random aleatorio = new Random();

    public void comprarAleatorios(Boleto[][] boletos) {
        calcularVariables(boletos);
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(
                "¿Cuántos boletos desea comprar?"));
        int disponibles = contarDisponibles(boletos);

        if (cantidad <= 0 || cantidad > disponibles) {
            JOptionPane.showMessageDialog(null,
                    "Cantidad incorrecta. Disponibles: " + disponibles);
        } else {
            String nombre = JOptionPane.showInputDialog("Nombre del comprador:");
            String telefono = JOptionPane.showInputDialog("Teléfono del comprador:");
            String asignados = "Números asignados: ";
            int vendidos = 0;

            while (vendidos < cantidad) {
                int numero = aleatorio.nextInt(100);
                Boleto boleto = buscarBoleto(boletos, numero);
                if (boleto.isDisponible()) {
                    boleto.vender(nombre, telefono);
                    asignados += numero + " ";
                    vendidos++;
                }
            }
            JOptionPane.showMessageDialog(null, asignados);
        }
    }

    public void calcularVariables(Boleto[][] boletos) {
        rifaTodos = 0;
        rifaMayor = 0;
        for (int fila = 0; fila < boletos.length; fila++) {
            for (int columna = 0; columna < boletos[fila].length; columna++) {
                if (boletos[fila][columna].isDisponible()) {
                    rifaTodos += 2000;
                    rifaMayor += boletos[fila][columna].getNumero();
                }
            }
        }
    }

    public int contarDisponibles(Boleto[][] boletos) {
        int cantidad = 0;
        for (int fila = 0; fila < boletos.length; fila++) {
            for (int columna = 0; columna < boletos[fila].length; columna++) {
                if (boletos[fila][columna].isDisponible()) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    public Boleto buscarBoleto(Boleto[][] boletos, int numero) {
        return boletos[numero / 10][numero % 10];
    }
}
