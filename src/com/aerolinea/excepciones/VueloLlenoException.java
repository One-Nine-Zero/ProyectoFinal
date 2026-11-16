package com.aerolinea.excepciones;

public class VueloLlenoException extends Exception {
    public VueloLlenoException(String mensaje) {
        super(mensaje);
    }
}
