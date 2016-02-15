/*
* Class Name: ProcesadorArchivos                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       14/02/2016     
* Version:    1.0
*/
package edu.uniandes.ecos.controller;

import edu.uniandes.ecos.model.Resultados;
import edu.uniandes.ecos.util.Constantes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de tomar la lista de archivos y procesarlos. 
 * Contando las lineas de codigo segun el estandar programado
 */
public class ProcesadorArchivos {
    private static Resultados resultados;
    
    /**
     * Se encarga de recibir y procesar la lista de archivos.
     * @param listaArchivos Lista de archivos (.java)
     * @return Resultados del conteo de lineas de codigo
     * @throws IOException Si se presentan errores en la lectura de los archivos
     */
    public static Resultados contarItemsYLineas(List<String> listaArchivos)throws IOException{
        
        resultados = new Resultados();
        resultados.setNumeroArchivos(new Long(listaArchivos.size()));
        resultados.setDatosTablaResumen(new ArrayList<Object[]>());
        
        for(String archivo : listaArchivos){
            resultados.setItemsPorClase(new Long(0));
            resultados.setLineasDeCodigoPorClase(new Long(0));
            System.out.println("Archivo: "+archivo);
            procesarArchivo(archivo);
            
            resultados.addObjectToList(new Object[]{
                archivo.substring(archivo.lastIndexOf("\\")+1),
                resultados.getItemsPorClase().toString(),
                resultados.getLineasDeCodigoPorClase().toString()});
        }
        
        return resultados;
    } 
    
    /**
     * Se encarga de validar cada linea de codigo. Si es valida se contabiliza
     * guardando los resultados en la variable resultados
     * @param archivo ruta del archivo que se va a procesar
     * @throws IOException Si ocurre un error al leer el archivo
     */
    private static void procesarArchivo(String archivo)throws IOException{
        boolean insideAMethod = false;
        int nivel = 0;
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        try{
            String linea = br.readLine();
            
            while(linea != null){
                if(linea.trim().startsWith(Constantes.INICIO_COMENTARIOS)){
                    do{
                        linea = br.readLine();
                    }while(linea != null && !linea.trim().startsWith(Constantes.FIN_COMENTARIOS));
                }else if(linea.trim() != null && !linea.trim().equals("")){
                    
                    if(insideAMethod == false && validarSiEsMetodo(linea)){
                        resultados.sumarItems(1);
                        insideAMethod = true;
                    }
                    if((linea.contains(Constantes.LLAVE_CERRADA) && linea.length() > 1)
                            ||(!linea.contains(Constantes.LLAVE_CERRADA))){
                        resultados.sumarLineas(1);
                        if(linea.contains(Constantes.LLAVE_ABIERTA)){
                            resultados.sumarLineas(1);
                            nivel++;
                        }
                    }
                    
                    if(linea.contains(Constantes.LLAVE_CERRADA) && nivel > 0)
                        nivel--;
                    if(linea.contains(Constantes.LLAVE_CERRADA) && nivel == 1)
                        insideAMethod = false;
                }
                linea = br.readLine();
            }
        }finally{
            br.close();
        }
    }
    
    /**
     * Se encarga de validar si una linea corresponde a la firma de un metodo
     * @param linea linea del archivo a validar
     * @return true si la linea corresponde a la firma de un metodo, false si 
     * no corresponde.
     */
    private static boolean validarSiEsMetodo(String linea){
        boolean valido = false;
        String arrayTextos[] = linea.trim().split(" ");
        System.out.println("Linea:"+linea);
        System.out.println("1:"+arrayTextos[0]);
        System.out.println("Tamanio:"+arrayTextos.length);
        if(!linea.contains(Constantes.CLASE) && !linea.contains(Constantes.IMPORT) && !linea.contains(Constantes.PACKAGE)
                && !linea.contains(Constantes.EXTENDS) && !linea.contains(Constantes.IMPORT) && !linea.contains(Constantes.IGUAL)){
            if(linea.contains(Constantes.MODIFICADOR_ACCESO_PUBLIC) || linea.contains(Constantes.MODIFICADOR_ACCESO_PROTECTED)
            || linea.contains(Constantes.MODIFICADOR_ACCESO_PRIVATE) || linea.contains(Constantes.RETURN_TYPE)){
                valido = true;
            }else if(arrayTextos.length > 1 && Character.isUpperCase(arrayTextos[0].trim().charAt(0)) && Character.isLowerCase(arrayTextos[1].trim().charAt(0))){
                valido = true;
            }
        }
        return valido;
    }
}
