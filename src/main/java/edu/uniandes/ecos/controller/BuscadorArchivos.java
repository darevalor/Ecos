/*
* Class Name: BuscadorArchivos                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       14/02/2016     
* Version:    1.0
*/
package edu.uniandes.ecos.controller;

import java.io.File;
import java.util.List;

/**
 * Clase encargada de buscar archivos
 */
public class BuscadorArchivos {
    
    private static List<String> listaArchivos;
    
    /**
     * Metodo encargado de obtener una lista de archivos (.java).
     * Toma como raiz la carpeta dada como parametro y busca en la misma y en 
     * las subcarpetas los archivos
     * @param carpeta carpeta raiz desde donde comienza a buscar
     * @return Una lista String con las rutas de los archivos encontrados
     */
    public static List<String> obtenerListaArchivos(String carpeta){
        File raiz = new File(carpeta);
        File[] listaArchivosVector = raiz.listFiles();
        
        for(File archivo : listaArchivosVector){
            if(archivo.isDirectory()){
                obtenerListaArchivos(archivo.getAbsolutePath());
            }
            else if(archivo.getName().endsWith(".java")){
                listaArchivos.add(archivo.getAbsolutePath());
            }
        }
        return listaArchivos;
    }

    /**
     * Devuelve la lista de Archivos
     * @param listaArchivos Una lista String con las rutas de los archivos encontrados
     */
    public static void setListaArchivos(List<String> listaArchivos) {
        BuscadorArchivos.listaArchivos = listaArchivos;
    }

    
}
