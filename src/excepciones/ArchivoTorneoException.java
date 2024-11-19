package excepciones;

/*La clase ArchivoTorneoException extiende de Exception y se utiliza para manejar errores
 *específicos o personalizados y esta relacionado con el manejo de archivos en el torneo.*/

public class ArchivoTorneoException extends Exception {
	
	//Constructor que permite crear una excepción con un mensaje personalizado.
	public ArchivoTorneoException(String mensaje) {
		super(mensaje);
	}
	
	/*Constructor que permite crear una excepcion con un mensaje personalizado
    y la causa original del error*/
	public ArchivoTorneoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
