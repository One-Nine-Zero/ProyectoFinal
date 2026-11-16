package com.aerolinea.modelo;

import com.aerolinea.excepciones.TiqueteInvalidoException;
import java.util.ArrayList;

public class AgenteVentas extends Personal {
    private ArrayList<Tiquete> tiquetes;

    public AgenteVentas(String nombre, String id, String cargo) {
        super(nombre, id, cargo);
        this.tiquetes = new ArrayList<>();
    }

    public void venderTiquete(String fecha, Pasajero pasajero) throws TiqueteInvalidoException {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new TiqueteInvalidoException("La fecha no puede estar vacía.");
        }
        if (pasajero == null) {
            throw new TiqueteInvalidoException("Debe indicarse un pasajero válido.");
        }
        Tiquete nuevoTiquete = new Tiquete(fecha, "Económica", pasajero, this);
        tiquetes.add(nuevoTiquete);
        System.out.println("Tiquete vendido para " + fecha + " a " + pasajero.getNombre());
    }

    public void venderTiquete(String fecha, String clase, Pasajero pasajero) throws TiqueteInvalidoException {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new TiqueteInvalidoException("La fecha no puede estar vacía.");
        }
        if (pasajero == null) {
            throw new TiqueteInvalidoException("Debe indicarse un pasajero válido.");
        }
        if (clase == null || clase.trim().isEmpty()) {
            throw new TiqueteInvalidoException("La clase no puede estar vacía.");
        }
        Tiquete nuevoTiquete = new Tiquete(fecha, clase, pasajero, this);
        tiquetes.add(nuevoTiquete);
        System.out.println("Tiquete vendido para " + fecha + " en clase " + clase + " a " + pasajero.getNombre());
    }

    @Override
    public String generarReporte() {
        return "=== AGENTE DE VENTAS ===\n"
                + "Nombre: " + getNombre() + "\n"
                + "ID: " + getId() + "\n"
                + "Cargo: " + getCargo() + "\n"
                + "Tiquetes vendidos: " + tiquetes.size();
    }

    public ArrayList<Tiquete> getTiquetes() {
        return tiquetes;
    }
}
