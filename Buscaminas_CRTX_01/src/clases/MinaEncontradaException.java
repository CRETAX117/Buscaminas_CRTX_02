/*
 * Proyecto Buscaminas
 * Hecho por Brandon Cárdenas
 * correo: bcardenasc5@est.ups.edu.ec
 * Fecha de Modificación: 04/08/2025
 * Clase MinaEncontradaException
 */

package clases;

/**
 * Excepción personalizada que indica que el jugador ha descubierto una mina.
 * Cuando esta excepción es lanzada el juego debe finalizar indicando
 * que el jugador ha perdido.
 */
public class MinaEncontradaException extends Exception {
    public MinaEncontradaException(String mensaje) {
        super(mensaje);
    }
}