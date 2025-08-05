# BuscaminasApp - POO_U4_Buscaminas

**Programación Orientada a Objetos • Universidad Politécnica Salesiana**

## Objetivos y propósito

Este proyecto es una aplicación de consola que recrea el clásico juego **Buscaminas** para demostrar los principios de **Programación Orientada a Objetos (POO)**.  El objetivo es descubrir todas las casillas seguras de un tablero de 10×10 sin detonar ninguna mina.  La aplicación integra conceptos como encapsulamiento, herencia, polimorfismo, manejo de excepciones, persistencia de datos y el patrón Modelo‑Vista‑Controlador (MVC).

## Clases y funcionalidades

### Modelo de dominio (paquete `clases`)

| Clase                        | Descripción                                                     |
|-----------------------------|-----------------------------------------------------------------|
| `Casilla`                   | Representa una casilla del tablero (mina, descubierta, marcada) |
| `Tablero`                   | Gestiona la generación del tablero, minas y revelado recursivo  |
| `CasillaYaDescubiertaException` | Excepción personalizada cuando se descubre una casilla repetida |
| `MinaEncontradaException`   | Excepción lanzada al descubrir una mina                        |
| `CoordenadaInvalidaException` | Excepción para coordenadas fuera de rango                      |
| `VistaConsola`              | Vista que imprime el tablero y solicita entradas al usuario    |
| `ControladorBuscaminas`     | Controlador que coordina la interacción entre el modelo y la vista |

### Aplicación

`BuscaminasApp` es la clase con el método `main`.  Presenta un menú simple para iniciar una nueva partida o cargar una partida guardada.  Al iniciar el juego crea el `Tablero`, la `VistaConsola` y el `ControladorBuscaminas`, delegando la ejecución a este último.

### Funcionalidades destacadas

* Sorteo aleatorio de **10 minas** distribuidas en un tablero de 10×10.
* Algoritmo **recursivo** para revelar casillas vacías sin minas adyacentes.
* Sistema de **marcado de casillas** para colocar banderas en posiciones sospechosas.
* **Persistencia de datos**: posibilidad de guardar y cargar partidas mediante archivos de texto.
* Manejo robusto de errores con excepciones personalizadas para entradas inválidas, minas y casillas repetidas.
* Separación de responsabilidades siguiendo el patrón **MVC**.

## Instrucciones para compilar y ejecutar

### Clonar el proyecto (si se aloja en Git)

```bash
git clone https://github.com/CRETAX117/Buscaminas_CRTX_02.git
cd POO_U4_Buscaminas/Buscaminas_CRTX_02
```

### Compilar y ejecutar desde consola

1. Compila las clases en un directorio de salida (`bin`):

   ```bash
   mkdir -p bin
   javac -d bin Buscaminas_CRTX_02/clases/*.java Buscaminas_CRTX_02/BuscaminasApp.java
   ```

2. Ejecuta la aplicación indicando el classpath y la clase principal:

   ```bash
   java -cp bin BuscaminasApp
   ```

### Importar en un IDE (NetBeans/Eclipse/IntelliJ)

1. Selecciona **File → Open Project** y elige la carpeta raíz `Buscaminas_CRTX_02`.
2. Asegúrate de que el IDE reconoce el paquete `clases` y el archivo `BuscaminasApp.java` como clase principal.
3. Ejecuta `BuscaminasApp` desde el IDE.

## Mejoras adicionales

* **Validaciones de entrada**: Se comprueba que las coordenadas estén dentro del rango permitido y que el formato sea correcto.
* **Persistencia**: El método `Tablero.guardar()` utiliza `try-with-resources` para escribir el estado del juego y `Tablero.cargar()` lo reconstruye.
* **Diseño (MVC)**: Separamos claramente el modelo (`Tablero`, `Casilla`), la vista (`VistaConsola`) y el controlador (`ControladorBuscaminas`).
* **Uso de excepciones personalizadas** para proporcionar mensajes de error más claros y específicos.
* Preparado para futuras extensiones como pruebas unitarias con JUnit o una interfaz gráfica.

## Refactorización y SOLID

* Se aplicó el **Principio de Responsabilidad Única (SRP)** separando la lógica de negocio, la vista y el controlador en archivos independientes.
* Se favoreció el **Principio de Abierto/Cerrado (OCP)**: agregar nuevas funcionalidades (como otros tamaños de tablero) no requiere modificar las clases existentes.
* Se empleó `Collections` y encapsulamiento para proteger el estado interno del modelo.

## ¡Gracias por probar el sistema!

Desarrollado en la **Universidad Politécnica Salesiana**

Asignatura: **Programación Orientada a Objetos**

Autor: **Brandon Cárdenas (CRETAX)**
