package com.aerolinea.modelo;

public class Piloto extends Personal {
    private int horasVuelo;

    public Piloto(String nombre, String id, String cargo, int horasVuelo) {
        super(nombre, id, cargo);
        if (horasVuelo < 0) {
            throw new IllegalArgumentException("Las horas de vuelo no pueden ser negativas.");
        }
        this.horasVuelo = horasVuelo;
    }

    @Override
    public String generarReporte() {
        return "=== PILOTO ===\n"
                + "Nombre: " + getNombre() + "\n"
                + "ID: " + getId() + "\n"
                + "Cargo: " + getCargo() + "\n"
                + "Horas de vuelo: " + horasVuelo;
    }
}
