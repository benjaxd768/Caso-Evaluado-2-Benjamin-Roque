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
public class Rifa {

    private Boleto[][] boletos = new Boleto[10][10];
    private final int precioBoleto = 2000;
    private int rifaTodos;
    private int rifaMayor;
    private int primeraVariable = 20;
    private Random aleatorio = new Random();

    public void iniciarSistema() {
        inicializarMatriz();
        mostrarMenu();
    }

    //Para crear los boletos del 0 al 99 
    public void inicializarMatriz() {
        int numero = 0;
        for (int fila = 0; fila < boletos.length; fila++) {
            for (int columna = 0; columna < boletos[fila].length; columna++) {
                boletos[fila][columna] = new Boleto(numero);
                numero++;
            }
        }
    }

    public void mostrarMenu() {
        boolean continuar = true;
        String[] opciones = {"Venta manual", "Gallo tapado", "Ver talonario",
            "Consultar comprador", "Estadísticas", "Realizar sorteo", "Salir"};

        while (continuar) {
            int opcion = JOptionPane.showOptionDialog(null,
                    "Sistema para registrar rifas\n"
                    + "Precio por boleto: ₡" + precioBoleto,
                    "Menú principal", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (opcion == 0) {
                ventaManual();
            } else if (opcion == 1) {
                galloTapado();
            } else if (opcion == 2) {
                mostrarTalonario();
            } else if (opcion == 3) {
                consultarComprador();
            } else if (opcion == 4) {
                mostrarEstadisticas();
            } else if (opcion == 5) {
                realizarSorteo();
            } else {
                continuar = false;
            }
        }
    }

    public Boleto buscarBoleto(int numero) {
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

    //Para vender un numero especifico si esta disponible 
    public void ventaManual() {
        int numero = Integer.parseInt(JOptionPane.showInputDialog(
                "Digite el número que desea comprar (0 al 99):"));

        if (numero < 0 || numero > 99) {
            JOptionPane.showMessageDialog(null, "El número debe estar entre 0 y 99.");
        } else {
            Boleto boleto = buscarBoleto(numero);
            if (!boleto.isDisponible()) {
                JOptionPane.showMessageDialog(null, "El boleto ya fue vendido.");
            } else {
                String nombre = JOptionPane.showInputDialog("Nombre del comprador:");
                String telefono = JOptionPane.showInputDialog("Teléfono del comprador:");
                boleto.vender(nombre, telefono);
                JOptionPane.showMessageDialog(null,
                        "Compra realizada. Número asignado: " + numero);
            }
        }
    }

    //Para asignar numeros aleatorios al comprador 
    public void galloTapado() {
        calcularVariablesEspeciales();
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(
                "¿Cuántos boletos desea comprar?"));
        int disponibles = contarDisponibles();

        if (cantidad <= 0 || cantidad > disponibles) {
            JOptionPane.showMessageDialog(null,
                    "Cantidad incorrecta. Boletos disponibles: " + disponibles);
        } else {
            String nombre = JOptionPane.showInputDialog("Nombre del comprador:");
            String telefono = JOptionPane.showInputDialog("Teléfono del comprador:");
            String asignados = "Números asignados: ";
            int vendidos = 0;

            while (vendidos < cantidad) {
                int numero = aleatorio.nextInt(100);
                Boleto boleto = buscarBoleto(numero);
                if (boleto.isDisponible()) {
                    boleto.vender(nombre, telefono);
                    asignados = asignados + numero + " ";
                    vendidos++;
                }
            }

            JOptionPane.showMessageDialog(null, asignados);
        }
    }

    //Para mostrar el talonario en 10x10
    public void mostrarTalonario() {
        String texto = "Talonario de la rifa\n\n";
        for (int fila = 0; fila < boletos.length; fila++) {
            for (int columna = 0; columna < boletos[fila].length; columna++) {
                if (boletos[fila][columna].isDisponible()) {
                    texto = texto + String.format("%02d  ", boletos[fila][columna].getNumero());
                } else {
                    texto = texto + " X   ";
                }
            }
            texto = texto + "\n";
        }
        JOptionPane.showMessageDialog(null, texto,
                "Talonario", JOptionPane.INFORMATION_MESSAGE);
    }

    //Busca las compras mediante el telefono o el nombre del que compra el numero
    public void consultarComprador() {
        String dato = JOptionPane.showInputDialog(
                "Digite el nombre o teléfono del comprador:");
        String texto = "Boletos encontrados\n\n";
        int encontrados = 0;

        for (int fila = 0; fila < boletos.length; fila++) {
            for (int columna = 0; columna < boletos[fila].length; columna++) {
                Boleto boleto = boletos[fila][columna];
                if (!boleto.isDisponible()
                        && (boleto.getComprador().equalsIgnoreCase(dato)
                        || boleto.getTelefonoComprador().equals(dato))) {
                    texto = texto + boleto.getNumero() + " ";
                    encontrados++;
                }
            }
        }

        if (encontrados == 0) {
            texto = "El comprador no existe o no tiene boletos.";
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    public int contarVendidos() {
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

    public int contarDisponibles() {
        return 100 - contarVendidos();
    }

    //calcula tres variables aleatorias
    public void calcularVariablesEspeciales() {
        rifaTodos = 0;
        rifaMayor = 0;

        for (int fila = 0; fila < boletos.length; fila++) {
            for (int columna = 0; columna < boletos[fila].length; columna++) {
                if (boletos[fila][columna].isDisponible()) {
                    rifaTodos = rifaTodos + precioBoleto;
                    rifaMayor = rifaMayor
                            + boletos[fila][columna].getNumero();
                }
            }
        }
    }

    public void mostrarEstadisticas() {
        int vendidos = contarVendidos();
        int disponibles = contarDisponibles();
        double porcentaje = vendidos;
        int recaudado = vendidos * precioBoleto;
        calcularVariablesEspeciales();

        String texto = "Stats de la rifa\n\n"
                + "Boletos vendidos: " + vendidos
                + "\nBoletos disponibles: " + disponibles
                + "\nPorcentaje vendido: " + String.format("%.2f", porcentaje) + "%"
                + "\nDinero recaudado: ₡" + recaudado
                + "\n\nrifaTodos: ₡" + rifaTodos
                + "\nvariableRifaMayor: " + rifaMayor
                + "\nvariableUno: " + primeraVariable;
        JOptionPane.showMessageDialog(null, texto);
    }

    // Para seleccionar tres numeros distintos y saber si fueron vendidos 
    public void realizarSorteo() {
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

        String texto = "RESULTADO DEL SORTEO\n\n";
        String[] premios = {"Primer lugar", "Segundo lugar", "Tercer lugar"};

        for (int i = 0; i < ganadores.length; i++) {
            Boleto boleto = buscarBoleto(ganadores[i]);
            texto = texto + premios[i] + " - Número " + ganadores[i] + "\n";
            if (boleto.isDisponible()) {
                texto = texto + "Premio desierto.\n\n";
            } else {
                texto = texto + "Ganador: " + boleto.getComprador()
                        + "\nTeléfono: " + boleto.getTelefonoComprador() + "\n\n";
            }
        }

        JOptionPane.showMessageDialog(null, texto);
    }

    public Boleto[][] getBoletos() {
        return boletos;
    }

    public void setBoletos(Boleto[][] boletos) {
        this.boletos = boletos;
    }

    public int getRifaTodos() {
        return rifaTodos;
    }

    public void setRifaTodos(int rifaTodos) {
        this.rifaTodos = rifaTodos;
    }

    public int getRifaMayor() {
        return rifaMayor;
    }

    public void setRifaMayor(int rifaMayor) {
        this.rifaMayor = rifaMayor;
    }

    public int getPrimeraVariable() {
        return primeraVariable;
    }

    public void setPrimeraVariable(int primeraVariable) {
        this.primeraVariable = primeraVariable;
    }

    public Random getAleatorio() {
        return aleatorio;
    }

    public void setAleatorio(Random aleatorio) {
        this.aleatorio = aleatorio;
    }

}
