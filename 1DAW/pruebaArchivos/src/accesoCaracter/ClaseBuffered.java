package accesoCaracter;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;

public class ClaseBuffered {
    
    static String rutaArchivo = "C://users//AdoneyDAW//Desktop//archivo.dat";
    static File miArchivo;
    
    public static void main(String[] args) {
        miArchivo = new File(rutaArchivo);
        
        añadirLinea("Mensaje");
    
    }
    
    private static void añadirLinea(String texto) {
        BufferedWriter bufferWrite = null;
        try {
            bufferWrite = new BufferedWriter(new FileWriter(miArchivo, true));
            
            bufferWrite.newLine();
            bufferWrite.write(texto);
            
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
                    
        } finally {
            try {
                bufferWrite.close();
                
            } catch (IOException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
            
        }
    }
}
