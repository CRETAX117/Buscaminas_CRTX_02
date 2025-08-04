/*
 * Proyecto Buscaminas
 * Hecho por Brandon Cárdenas
 * correo: bcardenasc5@est.ups.edu.ec
 * Fecha de Modificación: 04/08/2025
 * Clase Casilla
 */

package clases;

import java.io.Serializable;

/**
 * Representa una casilla individual dentro del tablero de Buscaminas.  Cada
 * casilla conoce su estado interno (mina, descubierta, marcada) y el
 * número de minas adyacentes.  Esta clase implementa Serializable para
 * permitir que el estado del juego se pueda persistir en disco.  Todos
 * los atributos están encapsulados para evitar accesos directos desde
 * otras clases.
 */
public class Casilla implements Serializable {
    private boolean tieneMina;
    private boolean descubierta;
    private boolean marcada;
    private int minasAdyacentes;

    /**
     * Constructor por defecto.  Inicializa una casilla sin mina y sin
     * descubrir.
     */
    public Casilla() {
        this.tieneMina = false;
        this.descubierta = false;
        this.marcada = false;
        this.minasAdyacentes = 0;
    }

    public boolean tieneMina() {
        return tieneMina;
    }

    public void setTieneMina(boolean tieneMina) {
        this.tieneMina = tieneMina;
    }

    public boolean isDescubierta() {
        return descubierta;
    }

    public void descubrir() {
        this.descubierta = true;
        // Una casilla descubierta no puede estar marcada
        this.marcada = false;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }

    public boolean isMarcada() {
        return marcada;
    }

    public void alternarMarca() {
        if (!descubierta) {
            this.marcada = !this.marcada;
        }
    }
}