/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.GestionCartaModel;

/**
 *
 * @author Adone
 */
public class GestionCartaController {
    private GestionCartaModel model;
    
    public GestionCartaController () {
        this.model = new GestionCartaModel();
    }
    
    public static void main (String[] args) {
        GestionCartaController controller = new GestionCartaController();
    }
}
