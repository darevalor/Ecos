/*
* Class Name: Principal                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       14/02/2016     
* Version:    1.0
*/
package edu.uniandes.ecos.app;

import edu.uniandes.ecos.vista.InterfazContador;
import javax.swing.JFrame;

/**
 * Clase principal para iniciar el programa de conteo de lineas de codigo
 *
 */
public class Principal 
{
    /**
     * Metodo principal que inicia la aplicacion
     * @param args 
     */
    public static void main( String[] args )
    {
        InterfazContador interfaz = new InterfazContador();
        interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        interfaz.setVisible(true);
    }
}
