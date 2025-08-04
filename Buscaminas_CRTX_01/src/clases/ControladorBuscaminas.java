/*
 * Proyecto Buscaminas
 * Hecho por Brandon Cárdenas
 * correo: bcardenasc5@est.ups.edu.ec
 * Fecha de Modificación: 04/08/2025
 * Clase ControladorBuscaminas
 */

package clases;

/**
 * El ControladorBuscaminas coordina la interacción entre el modelo (Tablero)
 * y la vista (VistaConsola).  Contiene la lógica del flujo del juego,
 * interpretando las entradas del usuario y actualizando el tablero en
 * consecuencia.  Se encarga de manejar las excepciones de forma que el
 * usuario reciba retroalimentación clara.
 */
public class ControladorBuscaminas {
    private Tablero tablero;
    private VistaConsola vista;

    public ControladorBuscaminas(Tablero tablero, VistaConsola vista) {
        this.tablero = tablero;
        this.vista = vista;
    }

    public void iniciarJuego() {
        boolean jugando = true;
        while (jugando) {
            vista.mostrarTablero(tablero);
            String entrada = vista.pedirCoordenada();
            if (entrada == null || entrada.isEmpty()) {
                vista.mostrarMensaje("Entrada vacía. Intente nuevamente.");
                continue;
            }
            entrada = entrada.toUpperCase();
            boolean marcar = false;
            String coordenada;
            if (entrada.startsWith("MARCAR ")) {
                marcar = true;
                coordenada = entrada.substring(7).trim();
            } else {
                coordenada = entrada;
            }
            try {
                int fila = convertirFila(coordenada);
                int columna = convertirColumna(coordenada);
                if (marcar) {
                    tablero.marcar(fila, columna);
                } else {
                    tablero.revelar(fila, columna);
                    if (tablero.haGanado()) {
                        vista.mostrarTablero(tablero);
                        vista.mostrarMensaje("🎉 ¡Felicidades! Has descubierto todas las casillas seguras. ¡Ganaste! 🎉");
                        break;
                    }
                }
            } catch (CasillaYaDescubiertaException e) {
                vista.mostrarMensaje("⚠️ " + e.getMessage());
            } catch (MinaEncontradaException e) {
                vista.mostrarMensaje("💣 " + e.getMessage());
                vista.mostrarTablero(tablero);
                vista.mostrarMensaje("😭 Fin del juego. ¡Mejor suerte la próxima vez!");
                jugando = false;
            } catch (CoordenadaInvalidaException e) {
                vista.mostrarMensaje("❌ " + e.getMessage());
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                vista.mostrarMensaje("⚠️ Formato de coordenada inválido. Utilice letra y número, por ejemplo A5.");
            }
        }
        vista.cerrar();
    }

    private int convertirFila(String coordenada) {
        char letra = coordenada.charAt(0);
        return Character.toUpperCase(letra) - 'A';
    }

    private int convertirColumna(String coordenada) {
        String numeroStr = coordenada.substring(1);
        int numero = Integer.parseInt(numeroStr);
        return numero - 1;
    }
}