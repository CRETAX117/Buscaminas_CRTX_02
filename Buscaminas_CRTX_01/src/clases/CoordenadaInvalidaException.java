/*
 * Proyecto Buscaminas
 * Hecho por Brandon Cárdenas
 * correo: bcardenasc5@est.ups.edu.ec
 * Fecha de Modificación: 04/08/2025
 * Clase CoordenadaInvalidaException
 */

package clases;

/**
 * Excepción personalizada que se lanza cuando el usuario ingresa una
 * coordenada inválida (por ejemplo fuera de los límites del tablero).  
 * 
 * La validación de coordenadas asegura que las operaciones no provoquen
 * errores como ArrayIndexOutOfBoundsException.
 */
public class CoordenadaInvalidaException extends Exception {
    public CoordenadaInvalidaException(String mensaje) {
        super(mensaje);
    }
}