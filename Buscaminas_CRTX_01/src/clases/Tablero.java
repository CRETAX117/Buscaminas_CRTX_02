/*
 * Proyecto Buscaminas
 * Hecho por Brandon Cárdenas
 * correo: bcardenasc5@est.ups.edu.ec
 * Fecha de Modificación: 04/08/2025
 * Clase Tablero
 */

package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * La clase Tablero administra un conjunto bidimensional de casillas.  
 * 
 * Se encarga de inicializar la partida distribuyendo minas aleatoriamente y
 * calculando el número de minas adyacentes para cada casilla.  Provee
 * operaciones para revelar y marcar casillas, así como para determinar
 * cuándo el jugador ha ganado.  
 * 
 * También ofrece funciones para guardar y
 * cargar el estado del tablero en un archivo de texto.
 */
public class Tablero implements Serializable {
    private static final int TAMANIO_DEFECTO = 10;
    private static final int MINAS_DEFECTO = 10;

    private int filas;
    private int columnas;
    private int numMinas;
    private Casilla[][] casillas;
    private int casillasDescubiertas;

    public Tablero() {
        this(TAMANIO_DEFECTO, TAMANIO_DEFECTO, MINAS_DEFECTO);
    }

    public Tablero(int filas, int columnas, int numMinas) {
        this.filas = filas;
        this.columnas = columnas;
        this.numMinas = numMinas;
        this.casillas = new Casilla[filas][columnas];
        this.casillasDescubiertas = 0;
        inicializarCasillas();
        colocarMinas();
        calcularMinasAdyacentes();
    }

    private void inicializarCasillas() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    private void colocarMinas() {
        Random random = new Random();
        int minasColocadas = 0;
        while (minasColocadas < numMinas) {
            int fila = random.nextInt(filas);
            int columna = random.nextInt(columnas);
            if (!casillas[fila][columna].tieneMina()) {
                casillas[fila][columna].setTieneMina(true);
                minasColocadas++;
            }
        }
    }

    private void calcularMinasAdyacentes() {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (casillas[i][j].tieneMina()) {
                    casillas[i][j].setMinasAdyacentes(-1);
                    continue;
                }
                int contador = 0;
                for (int k = 0; k < dx.length; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (enRango(nx, ny) && casillas[nx][ny].tieneMina()) {
                        contador++;
                    }
                }
                casillas[i][j].setMinasAdyacentes(contador);
            }
        }
    }

    private boolean enRango(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    public void revelar(int fila, int columna)
            throws CasillaYaDescubiertaException, MinaEncontradaException, CoordenadaInvalidaException {
        if (!enRango(fila, columna)) {
            throw new CoordenadaInvalidaException("Coordenadas fuera de rango");
        }
        Casilla casilla = casillas[fila][columna];
        if (casilla.isDescubierta()) {
            throw new CasillaYaDescubiertaException("La casilla ya se encuentra descubierta");
        }
        if (casilla.tieneMina()) {
            casilla.descubrir();
            throw new MinaEncontradaException("¡Boom! Has descubierto una mina.");
        }
        revelarRecursivo(fila, columna);
    }

    private void revelarRecursivo(int fila, int columna) {
        if (!enRango(fila, columna)) {
            return;
        }
        Casilla casilla = casillas[fila][columna];
        if (casilla.isDescubierta() || casilla.isMarcada()) {
            return;
        }
        casilla.descubrir();
        casillasDescubiertas++;
        if (casilla.getMinasAdyacentes() == 0) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx != 0 || dy != 0) {
                        int nx = fila + dx;
                        int ny = columna + dy;
                        if (enRango(nx, ny)) {
                            revelarRecursivo(nx, ny);
                        }
                    }
                }
            }
        }
    }

    public void marcar(int fila, int columna) throws CoordenadaInvalidaException {
        if (!enRango(fila, columna)) {
            throw new CoordenadaInvalidaException("Coordenadas fuera de rango");
        }
        Casilla casilla = casillas[fila][columna];
        casilla.alternarMarca();
    }

    public boolean haGanado() {
        int totalSeguras = filas * columnas - numMinas;
        return casillasDescubiertas >= totalSeguras;
    }

    public Casilla getCasilla(int fila, int columna) {
        if (!enRango(fila, columna)) {
            return null;
        }
        return casillas[fila][columna];
    }

    public void guardar(String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write(filas + "," + columnas + "," + numMinas + "," + casillasDescubiertas);
            bw.newLine();
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    Casilla c = casillas[i][j];
                    bw.write(i + "," + j + "," + c.tieneMina() + "," + c.isDescubierta()
                            + "," + c.isMarcada() + "," + c.getMinasAdyacentes());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el juego: " + e.getMessage());
        }
    }

    public static Tablero cargar(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String cabecera = br.readLine();
            if (cabecera == null) return null;
            String[] meta = cabecera.split(",");
            int filas = Integer.parseInt(meta[0]);
            int columnas = Integer.parseInt(meta[1]);
            int numMinas = Integer.parseInt(meta[2]);
            int descubiertas = Integer.parseInt(meta[3]);
            Tablero tablero = new Tablero(filas, columnas, numMinas);
            tablero.casillasDescubiertas = descubiertas;
            tablero.inicializarCasillas();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int i = Integer.parseInt(datos[0]);
                int j = Integer.parseInt(datos[1]);
                boolean mina = Boolean.parseBoolean(datos[2]);
                boolean descubierta = Boolean.parseBoolean(datos[3]);
                boolean marcada = Boolean.parseBoolean(datos[4]);
                int adyacentes = Integer.parseInt(datos[5]);
                Casilla casilla = new Casilla();
                casilla.setTieneMina(mina);
                if (descubierta) casilla.descubrir();
                if (marcada) casilla.alternarMarca();
                casilla.setMinasAdyacentes(adyacentes);
                tablero.casillas[i][j] = casilla;
            }
            return tablero;
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar el juego: " + e.getMessage());
            return null;
        }
    }
}