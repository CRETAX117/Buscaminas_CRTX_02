/*
 * Proyecto Buscaminas
 * Hecho por Brandon Cárdenas
 * correo: bcardenasc5@est.ups.edu.ec
 * Fecha de Modificación: 04/08/2025
 * Clase CasillaYaDescubiertaException
 */

package clases;

/**
 * Excepción personalizada que se lanza cuando el usuario intenta revelar
 * una casilla que ya ha sido descubierta previamente.  Al utilizar
 * excepciones personalizadas podemos dar mensajes de error más claros y
 * específicos durante el juego.
 */
public class CasillaYaDescubiertaException extends Exception {
    public CasillaYaDescubiertaException(String mensaje) {
        super(mensaje);
    }
}