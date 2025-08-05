/*
 * Proyecto Buscaminas
 * Hecho por Brandon Cárdenas
 * correo: bcardenasc5@est.ups.edu.ec
 * Fecha de Modificación: 0/08/2025
 * Clase BuscaminasApp
 */
package buscaminas_crtx;

import clases.ControladorBuscaminas;
import clases.Tablero;
import clases.VistaConsola;
import java.util.Scanner;

/**
 * Clase con el método main para ejecutar el juego de Buscaminas.  Presenta
 * un menú sencillo que permite iniciar una nueva partida o cargar una
 * partida previa.  Una vez iniciada la partida delega el control al
 * controlador.  
 * 
 * Este archivo se encuentra fuera del paquete de clases
 * para diferenciar claramente la capa de aplicación de la lógica del
 * negocio.
 */
public class BuscaminasApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== 	JUEGO BUSCAMINAS	===");
        System.out.println("---        by Cretax        ---");
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú principal:");
            System.out.println("1. Iniciar nueva partida");
            System.out.println("2. Cargar partida guardada");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1":
                    Tablero tablero = new Tablero();
                    VistaConsola vista = new VistaConsola();
                    ControladorBuscaminas controlador = new ControladorBuscaminas(tablero, vista);
                    controlador.iniciarJuego();
                    break;
                case "2":
                    System.out.print("Ingrese el nombre del archivo a cargar (ejemplo juego.txt): ");
                    String ruta = scanner.nextLine().trim();
                    Tablero cargado = Tablero.cargar(ruta);
                    if (cargado != null) {
                        VistaConsola vistaCargada = new VistaConsola();
                        ControladorBuscaminas controladorCargado = new ControladorBuscaminas(cargado, vistaCargada);
                        controladorCargado.iniciarJuego();
                    } else {
                        System.out.println("No se pudo cargar el archivo especificado.");
                    }
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        System.out.println("¡Gracias por jugar Buscaminas! 😊");
        scanner.close();
    }
}