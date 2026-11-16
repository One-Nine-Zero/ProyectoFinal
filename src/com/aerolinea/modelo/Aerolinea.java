package com.aerolinea.modelo;

import com.aerolinea.excepciones.PasajeroNoEncontradoException;
import com.aerolinea.excepciones.PersonalNoEncontradoException;
import java.util.ArrayList;

public class Aerolinea {
    private String nombre;
    private ArrayList<Vuelo> vuelos;
    private ArrayList<Personal> personal;

    public Aerolinea(String nombre) {
        this.nombre = nombre;
        this.vuelos = new ArrayList<>();
        this.personal = new ArrayList<>();
        // Composición: la aerolínea crea sus vuelos iniciales internamente
        this.vuelos.add(new Vuelo("AV101", "Medellín", 10));
        this.vuelos.add(new Vuelo("AV202", "Cartagena", 8));
        this.vuelos.add(new Vuelo("AV303", "Cali", 6));
    }

    public void agregarVuelo(String codigo, String destino, int capacidad) {
        Vuelo nuevoVuelo = new Vuelo(codigo, destino, capacidad);
        vuelos.add(nuevoVuelo);
        System.out.println("Vuelo '" + codigo + "' a " + destino + " creado con capacidad " + capacidad);
    }

    public void agregarPersonal(Personal p) {
        personal.add(p);
        System.out.println(p.getNombre() + " registrado en la aerolínea.");
    }

    public Vuelo buscarVuelo(String codigo) {
        for (Vuelo v : vuelos) {
            if (v.getCodigo().equalsIgnoreCase(codigo)) {
                return v;
            }
        }
        return null;
    }

    public Pasajero buscarPasajero(String pasaporte) throws PasajeroNoEncontradoException {
        for (Vuelo v : vuelos) {
            try {
                return v.buscarPasajero(pasaporte);
            } catch (PasajeroNoEncontradoException e) {
                // Continuar buscando en el siguiente vuelo
            }
        }
        throw new PasajeroNoEncontradoException(pasaporte);
    }

    public AgenteVentas buscarAgente(String id) throws PersonalNoEncontradoException {
        for (Personal p : personal) {
            if (p instanceof AgenteVentas && p.getId().equalsIgnoreCase(id)) {
                return (AgenteVentas) p;
            }
        }
        throw new PersonalNoEncontradoException(id);
    }

    public void listarAgentes() {
        System.out.println("--- Agentes de Ventas Disponibles ---");
        for (Personal p : personal) {
            if (p instanceof AgenteVentas) {
                System.out.println(p.getId() + " - " + p.getNombre() + " (" + p.getCargo() + ")");
            }
        }
    }

    public void generarReporteGeneral() {
        System.out.println("\n==========================================");
        System.out.println("REPORTE GENERAL: " + nombre.toUpperCase());
        System.out.println("==========================================");
        System.out.println("\n--- VUELOS ---");
        for (Vuelo v : vuelos) {
            System.out.println("Vuelo: " + v.getCodigo()
                    + " | Destino: " + v.getDestino()
                    + " | Ocupación: " + v.getPasajeros().size() + "/" + v.getCapacidad());
        }
        System.out.println("\n--- PERSONAL (" + personal.size() + " en total) ---");
        for (Personal p : personal) {
            System.out.println(p.generarReporte());
            System.out.println("------------------------------------------");
        }
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public ArrayList<Personal> getPersonal() {
        return personal;
    }
}
