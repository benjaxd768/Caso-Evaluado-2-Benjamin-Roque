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
public class Sorteo {

    private Random aleatorio = new Random();

    public void realizarSorteo(Boleto[][] boletos) {
        int[] ganadores = new int[3];

        for (int i = 0; i < ganadores.length; i++) {
            boolean repetido;
            do {
                ganadores[i] = aleatorio.nextInt(100);
                repetido = false;
                for (int j = 0; j < i; j++) {
                    if (ganadores[i] == ganadores[j]) {
                        repetido = true;
                    }
                }
            } while (repetido);
        }

        String[] premios = {"Primer lugar", "Segundo lugar", "Tercer lugar"};
        String texto = "Resultado del sorteo\n\n";

        for (int i = 0; i < ganadores.length; i++) {
            Boleto boleto = boletos[ganadores[i] / 10][ganadores[i] % 10];
            texto += premios[i] + " - Número " + ganadores[i] + "\n";
            if (boleto.isDisponible()) {
                texto += "Premio desierto.\n\n";
            } else {
                texto += "Ganador: " + boleto.getComprador()
                        + "\nTeléfono: " + boleto.getTelefonoComprador() + "\n\n";
            }
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    public Random getAleatorio() {
        return aleatorio;
    }

    public void setAleatorio(Random aleatorio) {
        this.aleatorio = aleatorio;
    }

}
