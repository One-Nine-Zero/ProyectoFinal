# Sistema de Gestión de Aerolínea (POO)

Proyecto final desarrollado en Java aplicando los pilares de la Programación Orientada a Objetos: abstracción, encapsulamiento, herencia, polimorfismo (estático y dinámico), composición, agregación, asociación y manejo de excepciones personalizadas.

## Estructura del Proyecto

```
SistemaAerolinea/
├── .vscode/
│   ├── launch.json
│   └── settings.json
└── src/
    └── com/aerolinea/
        ├── app/
        │   └── App.java
        ├── excepciones/
        │   ├── PasajeroNoEncontradoException.java
        │   ├── PersonalNoEncontradoException.java
        │   ├── TiqueteInvalidoException.java
        │   └── VueloLlenoException.java
        └── modelo/
            ├── Aerolinea.java
            ├── AgenteVentas.java
            ├── Pasajero.java
            ├── PasajeroEconomico.java
            ├── PasajeroEjecutivo.java
            ├── Personal.java
            ├── Piloto.java
            ├── Tiquete.java
            ├── Validador.java
            └── Vuelo.java
```

## Cómo ejecutar en Visual Studio Code

1. Descomprime el archivo `SistemaAerolinea.zip`.
2. Abre Visual Studio Code y selecciona **Archivo > Abrir carpeta...** (`File > Open Folder...`), eligiendo la carpeta `SistemaAerolinea`.
3. Asegúrate de tener instalado el **Extension Pack for Java** de Microsoft.
4. Abre `src/com/aerolinea/app/App.java` y haz clic en **Run** (o presiona `F5`). El menú interactivo se abrirá directamente en la terminal integrada.

## Decisión de diseño (Venta de tiquetes)

De acuerdo con la especificación, el método `Aerolinea.buscarPasajero(pasaporte)` busca pasajeros que ya se encuentran asignados (abordados) en algún vuelo activo. Por ello, el flujo operativo para vender un tiquete consiste en:
1. Registrar al pasajero en el sistema (**Opción 1**).
2. Abordarlo a un vuelo disponible (**Opción 3**).
3. Emitir/vender el tiquete asociándolo al agente de ventas y al pasajero a bordo (**Opción 4**).

Juan David Vargas
