package com.aerolinea.excepciones;

public class TiqueteInvalidoException extends Exception {
    public TiqueteInvalidoException(String mensaje) {
        super(mensaje);
    }
}
