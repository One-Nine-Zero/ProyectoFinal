package com.aerolinea.modelo;

public class Validador {

    private Validador() {
        // Constructor privado para evitar instanciación
    }

    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        for (char c : nombre.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("El nombre no puede contener números: " + nombre);
            }
        }
    }

    public static void validarPasaporte(String pasaporte) {
        if (pasaporte == null || pasaporte.length() < 6 || pasaporte.length() > 9) {
            throw new IllegalArgumentException("El pasaporte debe tener entre 6 y 9 caracteres.");
        }
        for (char c : pasaporte.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                throw new IllegalArgumentException("El pasaporte solo puede tener letras y números.");
            }
        }
    }
}
