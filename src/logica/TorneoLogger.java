package logica;

import java.io.FileWriter;
import java.io.IOException;

import excepciones.ArchivoTorneoException;

//La clase TorneoLogger se encarga de registrar mensajes en un archivo de log.
//Su propósito es llevar un registro de eventos importantes durante el torneo.
//El archivo se guardará automáticamente en el directorio raíz donde se ejecuta el programa.
public class TorneoLogger {
	private final String archivo;

    public TorneoLogger(String archivo) {
        this.archivo = archivo;
    }

    public void escribirLog(String mensaje) throws ArchivoTorneoException {
    	/*Bloque try-catch que garantiza que el archivo se cierre automáticamente
    	al finalizar la escritura.*/
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(mensaje + "\n");
        } catch (IOException e) {
            throw new ArchivoTorneoException("Error al escribir en el archivo de log.", e);
        }
    }
}
