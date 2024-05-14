/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tejerasantana_adoney_actividades_ut6;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Adone
 */
public class Principal {
    private static Scanner input = new Scanner(System.in);
    
    public static void main (String[] args) {
        boolean running = true;
        
        int option = -1;
        
        while (running) {
            option = -1;
            
            showMain();
            
            //Se obtiene la opcion, siempre y cuando sea valido
            while(option < 1 || option > 6) {
                System.out.print(">_");
                
                option = inputInt();
                
                if (option < 1 || option > 6) {
                    showError("Esa opcion no esta disponible");
                }
                
            }
            
            System.out.println("_______________");
            System.out.println("");
            
            switch (option) {
                case 1: 
                    opcion1();
                    break;
            }
            
            System.out.println("");
            System.out.println("_______________");
            
        }
            
    }
    
    //OPCIONES
    
    private static void opcion1() {
        Persona titular;
        
        //Datos del titular
        
        System.out.print("  Nombre del titular >_");
        String nombreTitular = inputNotBlankString();
        System.out.println();
        
        System.out.print("  Apellidos >_");
        String apellidosTitular = inputNotBlankString();
        System.out.println();
        
        //Validar DNI
        String DNI = "";
        while (!validarDNI(DNI)) {
            System.out.print("  DNI >_");  
            DNI = inputNotBlankString();
        }
        System.out.println();          
        
        titular = new Persona(DNI, nombreTitular, apellidosTitular);
        
    }
    
    //--------------------------------
    
    private static boolean validarDNI (String DNI) {
        String regex = "^(\\d{9})(\\w{1})$";
        
        return DNI.matches(regex);
    }
    
    private static String inputNotBlankString () {
        boolean valid = false;
        
        String txt = "";
        
        while (!valid) {
            valid = true;
            
            txt = input.next();
            input.nextLine();
            
            if (txt.isBlank() || txt.isEmpty()) {
                showError("Este campo debe ser rellenado");
                valid = false;
                
            }
        }
        
        return txt;
    }
    
    private static void showMain() {
        System.out.println("-_-_-_-_-_-Gestor de cuentas bancarias-_-_-_-_-_- ");
        System.out.println("1. Abrir una nueva cuenta.");
        System.out.println("2. Ver un listado de las cuentas disponibles.");
        System.out.println("3. Obtener los datos de una cuenta concreta.");
        System.out.println("4. Retirar efectivo de una cuenta.");
        System.out.println("5. Consultar el saldo actual de una cuenta.");
        System.out.println("6. Salir de la aplicacion.");
    }
    
    private static int inputInt() {
        boolean valid = false;
        
        int n = -1;
        
        while (!valid) {
            valid = true;
            
            try {
                n = input.nextInt();

                if (n < 0) {
                    valid = false;
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                valid = false;
                showError("Debe ser un numero entero valido");
                System.out.print(">_ ");
            } 
            
            input.nextLine();
            
        }
        
        return n;

    }
    
    private static void showError(String error) {
        System.out.println("<!!@@" + error + "@@!!>");
    }
}
