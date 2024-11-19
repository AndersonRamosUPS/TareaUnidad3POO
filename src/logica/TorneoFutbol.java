package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import excepciones.ArchivoTorneoException;
import modelo.Enfrentamiento;
import modelo.EquipoFutbol;

/*La clase TorneoFutbol administra la lógica principal del torneo,
incluyendo las etapas, enfrentamientos y ganadores*/
public class TorneoFutbol {
	
	// Lista de equipos participantes en el torneo.
	private final ArrayList<EquipoFutbol> equipos;
    
	// Logger para registrar eventos importantes en un archivo de log.
	private final TorneoLogger logger;
    
	//Constructor para inicializar un torneo con equipos y un logger.
    public TorneoFutbol(ArrayList<EquipoFutbol> equipos, TorneoLogger logger) {
        this.equipos = equipos;
        this.logger = logger;
    }
    
    //Inicia el torneo y gestiona etapa por etapa hasta que haya un campeón.
    public void iniciarTorneo(Scanner scanner) throws ArchivoTorneoException{
    	// Continua mientras haya más de un equipo en la lista.
    	while(equipos.size()>1) {
    		String etapa = obtenerNombreEtapa(equipos.size());	//Obtenemos el nombre de la etapa del torneo mediante el numero de equipos
    		logger.escribirLog("\nEtapa Actual: " + etapa);	// Registra la etapa en el archivo de log.
    		System.out.println("\nEtapa Actual");
    		
    		// Sortea los enfrentamientos y los muestra.
    		ArrayList<Enfrentamiento> enfrentamientos = sortearEquipos();
            mostrarEnfrentamientos(enfrentamientos);
            
            // Determina los ganadores y los registra para la siguiente etapa.
            determinarGanadores(scanner, enfrentamientos);
    		
    	}
    	// Una vez que queda un solo equipo, lo declara campeón.
    	EquipoFutbol campeon = equipos.get(0);
        logger.escribirLog("\n¡El campeón del torneo es: " + campeon + "!");
        System.out.println("\n¡El campeón del torneo es: " + campeon + "!");
    }
    
    //Organiza los equipos en enfrentamientos aleatorios.
    //Y retorna una lista de enfrentamientos para la etapa actual.
    private ArrayList<Enfrentamiento> sortearEquipos(){
    	Collections.shuffle(equipos);	// Mezcla aleatoriamente los equipos.
    	return asignarPartidos(equipos, new ArrayList<>());	// Utilizamos recursividad para asignar los enfrentamientos.
    }
    
    //Método recursivo para asignar enfrentamientos entre los equipos.
    private ArrayList<Enfrentamiento> asignarPartidos(ArrayList<EquipoFutbol> equipos, ArrayList<Enfrentamiento> enfrentamientos) {
    	// Caso base: Si no hay más equipos, retorna la lista de enfrentamientos.
    	if (equipos.isEmpty()) {
            return enfrentamientos;
        }
    	
    	// Toma dos equipos y los asigna a un enfrentamiento.
        Enfrentamiento enfrentamiento = new Enfrentamiento(equipos.remove(0), equipos.remove(0));
        enfrentamientos.add(enfrentamiento);
        
        // Llamada recursiva para los equipos restantes.
        return asignarPartidos(equipos, enfrentamientos);
    }
    
    //Muestra los enfrentamientos de la etapa actual en la consola.
    //Pasamos por parametro los enfrentamientos Lista de enfrentamientos a mostrar.
    private void mostrarEnfrentamientos(ArrayList<Enfrentamiento> enfrentamientos) {
        System.out.println("\nEnfrentamientos:");
        for (int i = 0; i < enfrentamientos.size(); i++) {
            System.out.println("Partido " + (i + 1) + ": " + enfrentamientos.get(i));
        }
    }
    
    //Determina los ganadores de cada enfrentamiento y los agrega a la lista de equipos para la siguiente etapa.
    //Pasamos una instancia de Scanner para leer entradas del usuario.
    //Pasamos la lista de enfrentamientos de la etapa actual.
    //ArchivoTorneoException por si ocurre un error al registrar los ganadores en el archivo de log.
    private void determinarGanadores(Scanner scanner, ArrayList<Enfrentamiento> enfrentamientos) throws ArchivoTorneoException {
        System.out.println("\nIngresa los ganadores de los partidos:");
        for (Enfrentamiento enfrentamiento : enfrentamientos) {
            System.out.println(enfrentamiento);
            EquipoFutbol ganador;
            do {
                System.out.print("Ganador: ");
                String ganadorNombre = scanner.nextLine();
                ganador = buscarEquipoPorNombre(ganadorNombre, enfrentamiento);
                if (ganador == null) {
                    System.out.println("El ganador debe ser uno de los equipos del enfrentamiento.");
                }
            } while (ganador == null);	// Repite hasta que el ganador sea válido.
            
         // Establece el ganador del enfrentamiento y lo agrega a la lista de equipos.
            enfrentamiento.setGanador(ganador);
            equipos.add(ganador);
            
         // Registra el ganador en el archivo de log.
            logger.escribirLog("Ganador: " + ganador);
        }
    }
    
    //Busca un equipo por su nombre dentro de un enfrentamiento.
    //Pasamos por parametro el nombre del equipo a buscar.
    //Pasamos por parametro el enfrentamiento donde buscar el equipo.
    private EquipoFutbol buscarEquipoPorNombre(String nombre, Enfrentamiento enfrentamiento) {
        if (enfrentamiento.getEquipo1().getNombre().equalsIgnoreCase(nombre)) {
            return enfrentamiento.getEquipo1();
        }
        if (enfrentamiento.getEquipo2().getNombre().equalsIgnoreCase(nombre)) {
            return enfrentamiento.getEquipo2();
        }
        return null;
    }
    
    private String obtenerNombreEtapa(int equipos) {
    	return switch(equipos) {
    		case 16 -> "Ocatavos de Final";
    		case 18 -> "Cuartos de Final";
    		case 4 -> "Semifinales";
    		case 2 -> "Final";
    		default -> "Etapa Desconocida";
    	};
    }
}
