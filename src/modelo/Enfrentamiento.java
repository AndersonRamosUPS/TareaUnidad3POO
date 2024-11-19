package modelo;

//La clase Enfrentamiento representa un partido entre dos equipos y almacena información sobre el ganador.
public class Enfrentamiento {
	
	//Atributos que representan los dos equipos que participan en el enfrentamiento
	private final EquipoFutbol equipo1;
	private final EquipoFutbol equipo2;
	
	// Atributo que almacena el equipo ganador del enfrentamiento.
	private EquipoFutbol ganador;

	public Enfrentamiento(EquipoFutbol equipo1, EquipoFutbol equipo2) {
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
	}

	public EquipoFutbol getEquipo1() {
		return equipo1;
	}

	public EquipoFutbol getEquipo2() {
		return equipo2;
	}

	public EquipoFutbol getGanador() {
		return ganador;
	}
	//Establece el ganador del enfrentamiento, asegurando que sea uno de los equipos que participan.
	//Lanza un IllegalArgumentException si el ganador no es el equipo1 ni el equipo2.
	public void setGanador(EquipoFutbol ganador) {
		if (ganador != equipo1 && ganador != equipo2) { // Aseguramos que uno de los dos equipos sea el ganador
			throw new IllegalArgumentException("El ganador debe ser uno de los equipos del enfrentamiento.");
		}
		this.ganador = ganador;
	}

	@Override
	public String toString() {
		return equipo1 + " vs " + equipo2;
	}
}
