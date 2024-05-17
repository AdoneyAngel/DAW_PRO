/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.Carta;
import model.GestionCartaModel;
import view.GestionCartaView;

/**
 *
 * @author Adone
 */
public class GestionCartaController {
    private static GestionCartaModel model;
    private static GestionCartaView view;
    private static Carta cartaSeleccionada;
    
    public static void main (String[] args) {
        model = new GestionCartaModel();
        view = new GestionCartaView();
        
        List<Carta> cartasTierra = model.getCartasTierra();
        List<Carta> cartasCriatura = model.getCartasCriatura();
        List<Carta> cartasConjuro = model.getCartasConjuro();
        List<Carta> cartasEncantamiento = model.getCartasEncantamiento();
        List<Carta> cartasInstantaneo = model.getCartasInstantaneo();
        List<Carta> cartasArtefacto = model.getCartasArtefacto();
        
        //Configuración de la vista
        
        view.setVisible(true);
        view.setTitle("Magic The Gathering");
        
        //---------------------------
        
        //Configuración combos
        
            //Se crean los modelos
        DefaultComboBoxModel comboTierra = new DefaultComboBoxModel();
        DefaultComboBoxModel comboCriaturas = new DefaultComboBoxModel();
        DefaultComboBoxModel comboConjuro = new DefaultComboBoxModel();
        DefaultComboBoxModel comboEncantamiento = new DefaultComboBoxModel();
        DefaultComboBoxModel comboInstantaneo = new DefaultComboBoxModel();
        DefaultComboBoxModel comboArtefacto = new DefaultComboBoxModel();
        
            //Se rellenan los modelos
            
        comboTierra.addElement("-");
        comboCriaturas.addElement("-");
        comboEncantamiento.addElement("-");
        comboInstantaneo.addElement("-");
        comboConjuro.addElement("-");
        comboArtefacto.addElement("-");
        
        for (Carta cartaActual : cartasTierra) {
            comboTierra.addElement(cartaActual.getNombre());
        }
        for (Carta cartaActual : cartasConjuro) {
            comboConjuro.addElement(cartaActual.getNombre());
        }
        for (Carta cartaActual : cartasCriatura) {
            comboCriaturas.addElement(cartaActual.getNombre());
        }
        for (Carta cartaActual : cartasEncantamiento) {
            comboEncantamiento.addElement(cartaActual.getNombre());
        }
        for (Carta cartaActual : cartasInstantaneo) {
            comboInstantaneo.addElement(cartaActual.getNombre());
        }
        for (Carta cartaActual : cartasArtefacto) {
            comboArtefacto.addElement(cartaActual.getNombre());
        }
        
            //Se establece los modelos a la vista
        
        view.getComboTierras().setModel(comboTierra);
        view.getComboCriaturas().setModel(comboCriaturas);
        view.getComboConjuros().setModel(comboConjuro);
        view.getComboEncantamientos().setModel(comboEncantamiento);
        view.getComboInstantaneos().setModel(comboInstantaneo);
        view.getComboArtefactos().setModel(comboArtefacto);
        
        //----------------------------
        
        view.getBtnCargar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
    }
    
    private void establecerCarta() {
        
    }
    
    private String getCartaSeleccionada () {
        
    }
}
