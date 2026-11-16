package com.aerolinea.app;

import com.aerolinea.excepciones.PasajeroNoEncontradoException;
import com.aerolinea.excepciones.PersonalNoEncontradoException;
import com.aerolinea.excepciones.TiqueteInvalidoException;
import com.aerolinea.excepciones.VueloLlenoException;
import com.aerolinea.modelo.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
                sc.nextLine(); // Limpiar entrada inválida
            }
        }
    }

    private static Pasajero buscarEnRegistrados(ArrayList<Pasajero> lista, String pasaporte) {
        for (Pasajero p : lista) {
            if (p.getPasaporte().equalsIgnoreCase(pasaporte)) {
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Pasajero> pasajerosRegistrados = new ArrayList<>();

        Aerolinea aerolinea = new Aerolinea("Aerolínea Andes Express");

        AgenteVentas a1 = new AgenteVentas("Sofía Herrera", "AG001", "Ventas aeropuerto");
        AgenteVentas a2 = new AgenteVentas("Mateo Vargas", "AG002", "Ventas en línea");
        Piloto p1 = new Piloto("Capitán Ricardo León", "P001", "Piloto comandante", 5200);

        aerolinea.agregarPersonal(a1);
        aerolinea.agregarPersonal(a2);
        aerolinea.agregarPersonal(p1);

        int opcion;
        do {
            System.out.println("\n========= MENU AEROLINEA ANDES EXPRESS =========");
            System.out.println("1. Registrar pasajero");
            System.out.println("2. Listar pasajeros registrados");
            System.out.println("3. Abordar pasajero a un vuelo");
            System.out.println("4. Vender tiquete");
            System.out.println("5. Ver pasajeros de un vuelo");
            System.out.println("6. Ver tiquetes de un agente");
            System.out.println("7. Desembarcar un pasajero");
            System.out.println("8. Buscar pasajero por pasaporte");
            System.out.println("9. Reporte general de la aerolínea");
            System.out.println("10. Crear nuevo vuelo");
            System.out.println("0. Salir");

            opcion = leerEntero(sc, "Seleccione una opción: ");

            switch (opcion) {
                case 1: {
                    int tipo = leerEntero(sc, "Tipo de pasajero (1=Económico, 2=Ejecutivo): ");
                    if (tipo != 1 && tipo != 2) {
                        System.out.println("Tipo inválido.");
                        break;
                    }
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Pasaporte (6-9 caracteres alfanuméricos): ");
                    String pasaporte = sc.nextLine();
                    int edad = leerEntero(sc, "Edad: ");

                    try {
                        if (tipo == 1) {
                            int equipaje = leerEntero(sc, "Equipaje (0-23 kg): ");
                            PasajeroEconomico pe = new PasajeroEconomico(nombre, pasaporte, edad, equipaje);
                            pasajerosRegistrados.add(pe);
                            System.out.println("Pasajero económico registrado exitosamente.");
                        } else {
                            int millas = leerEntero(sc, "Millas acumuladas: ");
                            System.out.print("¿Tiene acceso a Sala VIP? (s/n): ");
                            String respVip = sc.nextLine().trim();
                            boolean salaVip = respVip.equalsIgnoreCase("s");
                            PasajeroEjecutivo pj = new PasajeroEjecutivo(nombre, pasaporte, edad, millas, salaVip);
                            pasajerosRegistrados.add(pj);
                            System.out.println("Pasajero ejecutivo registrado exitosamente.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error de validación: " + e.getMessage());
                    }
                    break;
                }

                case 2: {
                    if (pasajerosRegistrados.isEmpty()) {
                        System.out.println("No hay pasajeros registrados en el sistema.");
                    } else {
                        System.out.println("--- Pasajeros Registrados ---");
                        for (Pasajero p : pasajerosRegistrados) {
                            System.out.println(p.obtenerInfo());
                        }
                    }
                    break;
                }

                case 3: {
                    System.out.print("Código del vuelo: ");
                    String codVuelo = sc.nextLine();
                    Vuelo vuelo = aerolinea.buscarVuelo(codVuelo);
                    if (vuelo == null) {
                        System.out.println("Error: El vuelo '" + codVuelo + "' no existe.");
                        break;
                    }
                    System.out.print("Pasaporte del pasajero: ");
                    String pasaporte = sc.nextLine();
                    Pasajero pasajero = buscarEnRegistrados(pasajerosRegistrados, pasaporte);
                    if (pasajero == null) {
                        System.out.println("Error: Pasajero no registrado. Regístrelo primero en la opción 1.");
                        break;
                    }
                    try {
                        vuelo.abordar(pasajero);
                    } catch (VueloLlenoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 4: {
                    aerolinea.listarAgentes();
                    System.out.print("ID del agente de ventas: ");
                    String idAgente = sc.nextLine();
                    try {
                        AgenteVentas agente = aerolinea.buscarAgente(idAgente);
                        System.out.print("Pasaporte del pasajero (debe haber abordado un vuelo): ");
                        String pasaporte = sc.nextLine();
                        Pasajero pasajero = aerolinea.buscarPasajero(pasaporte);

                        System.out.print("Fecha del tiquete (ej. 2026-11-20): ");
                        String fecha = sc.nextLine();
                        System.out.print("¿Desea especificar una clase? (s/n): ");
                        String respClase = sc.nextLine().trim();

                        if (respClase.equalsIgnoreCase("s")) {
                            System.out.print("Clase (ej. Ejecutiva, Primera, Económica): ");
                            String clase = sc.nextLine();
                            agente.venderTiquete(fecha, clase, pasajero);
                        } else {
                            agente.venderTiquete(fecha, pasajero);
                        }
                    } catch (PersonalNoEncontradoException | PasajeroNoEncontradoException | TiqueteInvalidoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 5: {
                    System.out.print("Código del vuelo: ");
                    String codVuelo = sc.nextLine();
                    Vuelo vuelo = aerolinea.buscarVuelo(codVuelo);
                    if (vuelo == null) {
                        System.out.println("Error: El vuelo '" + codVuelo + "' no existe.");
                    } else {
                        System.out.println("--- Pasajeros del vuelo " + vuelo.getCodigo() + " (" + vuelo.getDestino() + ") ---");
                        vuelo.listarPasajeros();
                    }
                    break;
                }

                case 6: {
                    aerolinea.listarAgentes();
                    System.out.print("ID del agente: ");
                    String idAgente = sc.nextLine();
                    try {
                        AgenteVentas agente = aerolinea.buscarAgente(idAgente);
                        ArrayList<Tiquete> tiquetes = agente.getTiquetes();
                        if (tiquetes.isEmpty()) {
                            System.out.println("El agente " + agente.getNombre() + " aún no ha vendido tiquetes.");
                        } else {
                            System.out.println("--- Tiquetes vendidos por " + agente.getNombre() + " ---");
                            for (Tiquete t : tiquetes) {
                                System.out.println(t.getInfo());
                            }
                        }
                    } catch (PersonalNoEncontradoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 7: {
                    System.out.print("Código del vuelo: ");
                    String codVuelo = sc.nextLine();
                    Vuelo vuelo = aerolinea.buscarVuelo(codVuelo);
                    if (vuelo == null) {
                        System.out.println("Error: El vuelo '" + codVuelo + "' no existe.");
                        break;
                    }
                    System.out.print("Pasaporte del pasajero a desembarcar: ");
                    String pasaporte = sc.nextLine();
                    try {
                        vuelo.desembarcar(pasaporte);
                    } catch (PasajeroNoEncontradoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 8: {
                    System.out.print("Pasaporte del pasajero a buscar en vuelos: ");
                    String pasaporte = sc.nextLine();
                    try {
                        Pasajero encontrado = aerolinea.buscarPasajero(pasaporte);
                        System.out.println("Pasajero encontrado a bordo: " + encontrado.obtenerInfo());
                    } catch (PasajeroNoEncontradoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 9: {
                    aerolinea.generarReporteGeneral();
                    break;
                }

                case 10: {
                    System.out.print("Código del nuevo vuelo: ");
                    String codigo = sc.nextLine();
                    System.out.print("Destino: ");
                    String destino = sc.nextLine();
                    int capacidad = leerEntero(sc, "Capacidad: ");
                    try {
                        aerolinea.agregarVuelo(codigo, destino, capacidad);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
