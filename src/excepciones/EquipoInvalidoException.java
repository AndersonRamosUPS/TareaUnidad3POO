package excepciones;

/*La clase EquipoInvalidoException extiende de Exception y se utiliza para manejar errores específicos
relacionados con la validación de los nombres de los equipos en el torneo.*/
public class EquipoInvalidoException extends Exception {
	
	//Constructor que permite crear una excepción con un mensaje personalizado
	public EquipoInvalidoException(String mensaje) {
		super(mensaje);
	}
}
