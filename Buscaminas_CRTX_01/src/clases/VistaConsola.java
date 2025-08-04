/*
 * Proyecto Buscaminas
 * Hecho por Brandon Cárdenas
 * correo: bcardenasc5@est.ups.edu.ec
 * Fecha de Modificación: 04/08/2025
 * Clase VistaConsola
 */

package clases;

import java.util.Scanner;

/**
 * La clase VistaConsola es responsable de toda la interacción por consola
 * con el usuario.  Imprime el estado actual del tablero y solicita
 * acciones al jugador.  Separar la vista del resto de la lógica nos
 * permite cumplir con el patrón MVC y facilita futuras extensiones como
 * crear una interfaz gráfica.
 */
public class VistaConsola {
    private Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarTablero(Tablero tablero) {
        System.out.print("   ");
        for (int c = 1; c <= 10; c++) {
            System.out.print(c + " ");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            char letraFila = (char) ('A' + i);
            System.out.print(letraFila + " |");
            for (int j = 0; j < 10; j++) {
                Casilla casilla = tablero.getCasilla(i, j);
                char simbolo;
                if (casilla.isDescubierta()) {
                    if (casilla.tieneMina()) {
                        simbolo = 'X';
                    } else if (casilla.getMinasAdyacentes() == 0) {
                        simbolo = 'V';
                    } else {
                        simbolo = Character.forDigit(casilla.getMinasAdyacentes(), 10);
                    }
                } else {
                    simbolo = casilla.isMarcada() ? '!' : '?';
                }
                System.out.print(simbolo + " ");
            }
            System.out.println();
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String pedirCoordenada() {
        System.out.print("Ingrese coordenada (ejemplo A5) o escriba 'marcar A5' para colocar bandera: ");
        return scanner.nextLine().trim();
    }

    /**
     * Método de cierre de la vista.  No cierra la entrada estándar para evitar
     * interferir con otros lectores de System.in (como el menú principal en
     * BuscaminasApp).  Dejar el scanner abierto previene excepciones
     * NoSuchElementException cuando la aplicación solicita más entrada.
     */
    public void cerrar() {
        // No cerrar System.in aquí; el recurso se cerrará en la clase principal.
    }
}