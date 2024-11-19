package modelo;

import excepciones.EquipoInvalidoException;

public class EquipoFutbol {
	private String nombre;

	public EquipoFutbol(String nombre) throws EquipoInvalidoException {

		// Evalua si "nombre" es nulo, vacía o contiene solo espacios en blanco
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new EquipoInvalidoException("El nombre del equipo no puede estar vacío.");
		} else {
			this.nombre = nombre;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public String toString() {
		return nombre;
	}
}
