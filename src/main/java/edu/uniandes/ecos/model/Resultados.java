/*
* Class Name: Resultados                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       14/02/2016     
* Version:    1.0
*/
package edu.uniandes.ecos.model;

import java.util.List;

/**
 * Se encarga de guardar los resultados del conteo
 */
public class Resultados {
    private Long items;
    private Long lineasDeCodigo;
    private Long lineasDeCodigoPorItem;
    private Long numeroArchivos;
    private Long itemsPorClase;
    private Long lineasDeCodigoPorClase;
    private List<Object[]> datosTablaResumen;

    /**
     * Devuelve la cantidad de items
     * @return Long con la cantidad de items
     */
    public Long getItems() {
        return items;
    }

    /**
     * Establece la cantidad de items
     * @param items numero de item
     */
    public void setItems(Long items) {
        this.items = items;
    }

    /**
     * Devuelve el numero de lineas de codigo totales
     * @return Long con el numero de lineas
     */
    public Long getLineasDeCodigo() {
        return lineasDeCodigo;
    }

    /**
     * Establece el numero de lines de codigo total
     * @param lineasDeCodigo Long con el numero de lineas
     */
    public void setLineasDeCodigo(Long lineasDeCodigo) {
        this.lineasDeCodigo = lineasDeCodigo;
    }

    /**
     * Devuelve el promedio de numero de lineas por item
     * @return Long con la division entre el numero de lineas total y el numero
     * de items
     */
    public Long getLineasDeCodigoPorItem() {
        lineasDeCodigoPorItem = new Long(0);
        
        if(items > 0)
            lineasDeCodigoPorItem = lineasDeCodigo / items;
        
        return lineasDeCodigoPorItem;
    }

    /**
     * Establece las lineas de codigo por Item
     * @param lineasDeCodigoPorItem 
     */
    public void setLineasDeCodigoPorItem(Long lineasDeCodigoPorItem) {
        this.lineasDeCodigoPorItem = lineasDeCodigoPorItem;
    }

    /**
     * Devuelve el numero de archivos procesado
     * @return Long con el numero de archivos
     */
    public Long getNumeroArchivos() {
        return numeroArchivos;
    }

    /**
     * Establece el numero de archivos
     * @param numeroArchivos Long con el numero de archivos
     */
    public void setNumeroArchivos(Long numeroArchivos) {
        this.numeroArchivos = numeroArchivos;
    }

    /**
     * Agrega a los items la cantidad pasada como parametro
     * @param numero numero de items por agregar
     */
    public void sumarItems(int numero){
        if(items == null)
            items = new Long(0);
        
        if(itemsPorClase == null)
            itemsPorClase = new Long(0);
        
        this.items = this.items + new Long(numero);
        this.itemsPorClase = this.itemsPorClase + new Long(numero);
    }
 
    /**
     * Agrega un numero de lineas de codigo a la variable lineasDeCodigo
     * @param numero de lineas por agregar
     */
    public void sumarLineas(int numero){
        if(lineasDeCodigo == null)
            lineasDeCodigo = new Long(0);
        
        if(lineasDeCodigoPorClase == null)
            lineasDeCodigoPorClase = new Long(0);
        
        this.lineasDeCodigoPorClase = lineasDeCodigoPorClase + new Long(numero);
        this.lineasDeCodigo = this.lineasDeCodigo + new Long(numero);
    }

    /**
     * Establece el numero de items por clase
     * @param itemsPorClase Long con el numero de items
     */
    public void setItemsPorClase(Long itemsPorClase) {
        this.itemsPorClase = itemsPorClase;
    }

    /**
     * Devuelve el numero de items por clase
     * @return Long con el numero de items por clase
     */
    public Long getItemsPorClase() {
        return itemsPorClase;
    }

    /**
     * Devuelve el numero de lineas de codigo por clase
     * @return Long con el numero de lineas de codigo por clase
     */
    public Long getLineasDeCodigoPorClase() {
        return lineasDeCodigoPorClase;
    }

    /**
     * Establece el numero de lineas de codigo por clase
     * @param lineasDeCodigoPorClase Long con el numero de lineas de codigo por clase
     */
    public void setLineasDeCodigoPorClase(Long lineasDeCodigoPorClase) {
        this.lineasDeCodigoPorClase = lineasDeCodigoPorClase;
    }

    /**
     * Devuelve un objeto con el resumen del conteo de lineas de codigo
     * @return Arreglo con los datos del conteo
     */
    public List<Object[]> getDatosTablaResumen() {
        return datosTablaResumen;
    }

    /**
     * Establece los datos de resumen del conteo de lineas de codigo
     * @param datosTablaResumen datos con el resumen del conteo
     */
    public void setDatosTablaResumen(List<Object[]> datosTablaResumen) {
        this.datosTablaResumen = datosTablaResumen;
    }
  
    /**
     * Agrega un objeto con los datos del conteo de un archivo a la lista de resumen
     * @param objeto arreglo con los datos obtenidos
     */
    public void addObjectToList(Object[] objeto){
        this.datosTablaResumen.add(objeto);
    }
}
