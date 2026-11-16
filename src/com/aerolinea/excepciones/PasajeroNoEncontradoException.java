package com.aerolinea.excepciones;

public class PasajeroNoEncontradoException extends Exception {
    public PasajeroNoEncontradoException(String pasaporte) {
        super("Pasajero con pasaporte " + pasaporte + " no encontrado.");
    }
}
