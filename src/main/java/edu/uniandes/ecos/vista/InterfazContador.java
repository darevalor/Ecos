/*
* Class Name: InterfazContador                                                         
* Name:       Daniel Arevalo                                                                      
* Date:       14/02/2016     
* Version:    1.0
*/
package edu.uniandes.ecos.vista;

import edu.uniandes.ecos.controller.BuscadorArchivos;
import edu.uniandes.ecos.controller.ProcesadorArchivos;
import edu.uniandes.ecos.model.Resultados;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Interfaz de la aplicacion
 */
public class InterfazContador extends JFrame{
    private JButton botonSeleccionRuta;
    private JButton botonSalir;
    private JLabel etiqSeleccionRuta;
    private JLabel etiqArchivosProcesados;
    private JLabel etiqItemsEncontrados;
    private JLabel etiqLOCPorIem;
    private JLabel etiqLOCTotales;
    private JLabel etiqResumen;
    private JTextField campoSeleccionRuta;
    private JTextField campoArchivosProcesados;
    private JTextField campoItemsEncontrados;
    private JTextField campoLOCporItem;
    private JTextField campoLOCTotales;
    private JTable tablaResumen;
    private DefaultTableModel tableModel;
   
    public InterfazContador() {
        iniciarComponentes();
    }

    /**
     * Inicia todos los componentes de la interfaz
     */
    private void iniciarComponentes(){
        botonSeleccionRuta = new JButton();
        botonSalir = new JButton();
        etiqSeleccionRuta = new JLabel();
        etiqArchivosProcesados = new JLabel();
        etiqItemsEncontrados = new JLabel();
        etiqLOCPorIem = new JLabel();
        etiqLOCTotales = new JLabel();
        etiqResumen = new JLabel();
        campoSeleccionRuta = new JTextField(50);
        campoArchivosProcesados = new JTextField(20);
        campoItemsEncontrados = new JTextField(20);
        campoLOCporItem = new JTextField(20);
        campoLOCTotales = new JTextField(20);
        tablaResumen = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"Part Name","Number of Items","Part Size"},0);
        
        tablaResumen.setModel(tableModel);
        
        JPanel panelSeleccionRuta = new JPanel(new GridLayout(2,1));
        JPanel panelEtiquetaSeleccionRuta = new JPanel();
        JPanel panelCampoYBotonSeleccionRuta = new JPanel();
        
        JPanel panelCentro = new JPanel(new BorderLayout());
        JPanel panelResultados = new JPanel(new GridLayout(4,2));
        JPanel panelResumen = new JPanel(new BorderLayout());
        
        JPanel panelBotones = new JPanel();
        
        JScrollPane scrollPane = new JScrollPane(tablaResumen,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        botonSeleccionRuta.setText("Seleccione");
        
        botonSeleccionRuta.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarRuta(evt);
            }
        });
        
        botonSalir.setText("Salir");
        
        botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirSistemaAccion(evt);
            }
        });
        etiqSeleccionRuta.setText("Seleccione la ruta del proyecto");
        etiqArchivosProcesados.setText("Archivos procesados: ");
        etiqItemsEncontrados.setText("Items encontrados: ");
        etiqLOCPorIem.setText("LOC por Item: ");
        etiqLOCTotales.setText("Total LOC: ");
        etiqResumen.setText("Resumen ");
        
        setLayout(new BorderLayout());
        
        panelEtiquetaSeleccionRuta.add(etiqSeleccionRuta);
        panelCampoYBotonSeleccionRuta.add(campoSeleccionRuta);
        panelCampoYBotonSeleccionRuta.add(botonSeleccionRuta);
        
        panelSeleccionRuta.add(panelEtiquetaSeleccionRuta);
        panelSeleccionRuta.add(panelCampoYBotonSeleccionRuta);
        
        panelResultados.add(etiqArchivosProcesados);
        panelResultados.add(campoArchivosProcesados);
        panelResultados.add(etiqItemsEncontrados);
        panelResultados.add(campoItemsEncontrados);
        panelResultados.add(etiqLOCPorIem);
        panelResultados.add(campoLOCporItem);
        panelResultados.add(etiqLOCTotales);
        panelResultados.add(campoLOCTotales);
        
        panelResumen.add(etiqResumen,BorderLayout.NORTH);
        panelResumen.add(scrollPane,BorderLayout.CENTER);
        
        panelCentro.add(panelResultados,BorderLayout.NORTH);
        panelCentro.add(panelResumen,BorderLayout.CENTER);
        panelResultados.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        panelBotones.add(botonSalir);
        
        add(panelSeleccionRuta,BorderLayout.NORTH);
        add(new JPanel(),BorderLayout.EAST);
        add(panelCentro,BorderLayout.CENTER);
        add(new JPanel(),BorderLayout.WEST);
        add(panelBotones,BorderLayout.SOUTH);
        
        setTitle("Contador de Lineas de Codigo");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        
        pack();
    }
    
    /**
     * Manejador del evento disparado por el boton de seleccion de directorio
     * @param evento 
     */
    private void seleccionarRuta(ActionEvent evento){
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setDialogTitle("Seleccionar carpeta de proyecto ");
        selectorArchivos.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        if(selectorArchivos.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            
            BuscadorArchivos.setListaArchivos(new ArrayList<String>());
            try {
                Resultados resultados = ProcesadorArchivos.contarItemsYLineas(BuscadorArchivos.obtenerListaArchivos(selectorArchivos.getSelectedFile().toString()));
                campoSeleccionRuta.setText(selectorArchivos.getSelectedFile().toString());
                campoArchivosProcesados.setText(resultados.getNumeroArchivos().toString());
                campoItemsEncontrados.setText(resultados.getItems().toString());
                campoLOCTotales.setText(resultados.getLineasDeCodigo().toString());
                campoLOCporItem.setText(resultados.getLineasDeCodigoPorItem().toString());
                
                for(Object[] objeto: resultados.getDatosTablaResumen()){
                    tableModel.addRow(objeto);
                }
            } catch (IOException ex) {
                Logger.getLogger(InterfazContador.class.getName()).log(Level.SEVERE, "Error al leer los archivos", ex);
            }
        }
    }
    
    /**
     * Manejador del evento que dispara el boton salir. Hace que se cierre la 
     * aplicacion
     * @param evento 
     */
    private void salirSistemaAccion(ActionEvent evento){
        System.exit(0);
    }
}
