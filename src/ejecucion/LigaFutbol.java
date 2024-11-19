package ejecucion;

import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.Scanner;

import excepciones.EquipoInvalidoException;
import logica.TorneoFutbol;
import logica.TorneoLogger;
import modelo.EquipoFutbol;

public class LigaFutbol {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //Logger para registrar eventos importantes del torneo en un archivo.
        TorneoLogger logger = new TorneoLogger("torneo_log.txt");

        try {
        	// Selección de la etapa inicial del torneo por parte del usuario.
            System.out.println("Seleccione la etapa:"
            		+ "\n1) Octavos"
            		+ "\n2) Cuartos"
            		+ "\n3) Semifinales"
            		+ "\n4) Final:");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            int equiposRequeridos;
            switch (opcion) {
                case 1 -> equiposRequeridos = 16;
                case 2 -> equiposRequeridos = 8;
                case 3 -> equiposRequeridos = 4;
                case 4 -> equiposRequeridos = 2;
                default -> {
                    System.out.println("Opción inválida. El programa se cerrará.");
                    return; // Salir del programa si la opción es inválida
                }
            }

            // Ingreso de los nombres de los equipos participantes.
            ArrayList<EquipoFutbol> equipos = new ArrayList<>();
            System.out.println("Ingresa los nombres de los equipos:");
            for (int i = 0; i < equiposRequeridos; i++) {
                System.out.print("Equipo " + (i + 1) + ": ");
                String nombre = scanner.nextLine();
                equipos.add(new EquipoFutbol(nombre));  // Crea un objeto EquipoFutbol y lo agrega a la lista.
            }

            //Crea una instancia de TorneoFutbol con los equipos ingresados y el logger.
            TorneoFutbol torneo = new TorneoFutbol(equipos, logger);
            
         // Inicia el torneo y gestiona su flujo.
            torneo.iniciarTorneo(scanner);

        } catch (EquipoInvalidoException e) {
        	// Maneja excepciones relacionadas con nombres de equipos inválidos.
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
        	// Maneja cualquier otro tipo de excepción inesperada.
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}
