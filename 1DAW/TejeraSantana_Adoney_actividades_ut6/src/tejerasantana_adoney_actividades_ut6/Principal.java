/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tejerasantana_adoney_actividades_ut6;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Adone
 */
public class Principal {
    private static Scanner input = new Scanner(System.in);
    private static Banco banco = new Banco();
    
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
                case 2: 
                    opcion2();
                    break;
                case 3:
                    
                    break;
                case 4:
                    opcion4();
                    break;
                case 6:
                    running = false;
                    showMessage("Finalizado");
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
        
        showHead("Datos del titular");
        
        titular = solicitarPersona();
        
        //Solicitar datos de la nueva cuenta
        
        int optTipoCuenta = -1;
        
        showHead("Tipo de cuenta");
        System.out.println("1. Cuenta ahorro");
        System.out.println("2. Cuenta corriente personal");
        System.out.println("3. Cuenta corriente de empresa");
        System.out.println("4. Cancelar");
        System.out.print(">_");
        
        while (optTipoCuenta < 1 || optTipoCuenta > 4) {
            optTipoCuenta = inputInt();
            
            if (optTipoCuenta < 1 || optTipoCuenta > 4) {
                showError("Esa opcion no esta disponible");
                System.out.print(">_");
            }
        }
        
        switch (optTipoCuenta) {
            case 1 -> creacionCuentaAhorro(titular);
            case 2 -> creacionCuentaCorrientePersonal(titular);
            case 3 -> creacionCuentaCorrienteEmpresa(titular);
            default -> showMessage("Cancelado");
        }
        
    }
    
    private static void opcion2() {
        showHead("Lista de cuentas");
        
        for (CuentaBancaria cuentaActual : banco.getCuentas()) {
            System.out.println("--"+cuentaActual.devolverInfoString());
        }
    }
    
    private static void opcion4() {
        showHead("Retiro de cuenta");
        
        String numeroCuentaRegex = "\\d{20}";
        
        String numeroCuenta;
        CuentaBancaria cuentaOriginal;
        double cantidad;
        
        System.out.println("");
        System.out.print("Numero de cuenta >_ES ");
        numeroCuenta = inputNotBlankString();
        System.out.println();
        
        if (!numeroCuenta.matches(numeroCuentaRegex)) {
            showError("Numero de cuenta invalido, se cancela el proceso");
            
        } else {
            if (banco.existeNumeroCuenta(numeroCuenta)) {
                System.out.print("Dinero a retirar >_");
                cantidad = inputDouble();
                System.out.println();
                
            } else {
                showError("No existe ninguna cuenta asociado al numero introducido");
            }
        }
    }
    
    private static void creacionCuentaCorrientePersonal(Persona titular) {
        showHead("Introduccion");
        
        CuentaCorrientePersonal cuentaCorrientePersonal;
        
        //Se obtiene los datos para una cuenta corriente
        CuentaCorriente cuentaCorriente = creacionCuentaCorriente(titular);
        
        System.out.println("");
        System.out.print("Comision de mantenimiento >_");
        double comisionMantenimiento = inputDouble();
        
        cuentaCorrientePersonal = new CuentaCorrientePersonal(cuentaCorriente, comisionMantenimiento);
        
        banco.insertarCuenta(cuentaCorrientePersonal);
        
    }
    
    private static void creacionCuentaCorrienteEmpresa(Persona titular) {
        showHead("Introduccion");
        
        CuentaCorrienteEmpresa cuentaCorrienteEmpresa;
        double interesPorDescubierto = -1;
        int maximioDescubierto = -1;
        
        //Se obtiene los datos para una cuenta corriente
        CuentaCorriente cuentaCorriente = creacionCuentaCorriente(titular);
        
        System.out.println("");
        System.out.print("Intereses por descubierto >_");
        interesPorDescubierto = inputDouble();
        System.out.println();
        
        System.out.println("");
        System.out.print("Maximo numero de descubiertos >_");
        maximioDescubierto = inputInt();
        System.out.println();
        
        cuentaCorrienteEmpresa = new CuentaCorrienteEmpresa(cuentaCorriente, interesPorDescubierto, maximioDescubierto);
        
        banco.insertarCuenta(cuentaCorrienteEmpresa);
        
    }
    
    private static void creacionCuentaAhorro(Persona titular) {
        showHead("Introduccion");
        
        CuentaAhorro cuentaAhorro;
        CuentaBancaria cuentaBancaria = creacionCuentaBancaria(titular);
        double intereses = -1;
        
        //Se obtiene el resto de datos para este tipo de cuenta
        System.out.println("");
        System.out.print("Intereses >_");
        intereses = inputDouble();
        
        cuentaAhorro = new CuentaAhorro(cuentaBancaria, intereses);
        
        boolean insertado = banco.insertarCuenta(cuentaAhorro);
        
        if (insertado) {
            showMessage("Se ha creado la cuenta");
            
        } else {
            showMessage("No se ha podido insertar la cuenta");
        }
    }
    
    //Funcion para solicitar los datos necesarios para CuentaCorriente
    private static CuentaCorriente creacionCuentaCorriente(Persona titular) {
        CuentaBancaria cuentaBancaria = creacionCuentaBancaria(titular);
        CuentaCorriente cuenta;
        
        List<Persona> titulares = new ArrayList();
        
        boolean creandoTitulares = true;
        
        showHead("Titulares de la cuenta");
        
        while (creandoTitulares) {
            System.out.println("¿Añadir mas titulares?");
            System.out.println("[S]: SI");
            System.out.println("[N]: NO (Por defecto)");
            System.out.print(">_");

            String option = inputNotBlankString();

            if (option.toLowerCase().charAt(0) != 's') {
                creandoTitulares = false;
                break;
            }
            
            Persona nuevoTitular = solicitarPersona();

            boolean existeDNI = false;

            for (Persona titularActual : titulares) {
                if (titularActual.getDNI().equals(nuevoTitular.getDNI())) {
                    existeDNI = true;
                }
            }
            
            if (titular.getDNI().equals(nuevoTitular.getDNI())) {
                existeDNI = true;
            }

            if (!existeDNI) {
                titulares.add(titular);

            } else {
                showError("Ya hay un titular asociado al DNI introducido");
            }
        }
        
        cuenta = new CuentaCorriente(cuentaBancaria, titulares);
        
        return cuenta;
        
    }
    
    private static Persona solicitarPersona() {
        Persona persona;
        
        System.out.print("Nombre del titular >_");
        String nombreTitular = inputNotBlankString();
        System.out.println();
        
        System.out.print("Apellidos >_");
        String apellidosTitular = inputNotBlankString();
        System.out.println();
        
        //Validar DNI
        String DNI = "";
        while (!validarDNI(DNI)) {
            System.out.print("DNI >_");  
            DNI = inputNotBlankString();
        }
        System.out.println();          
        
        persona = new Persona(DNI, nombreTitular, apellidosTitular);
        
        return persona;
    }
    
    //Funcion para solicitar los datos para un objeto CuentaBancaria
    private static CuentaBancaria creacionCuentaBancaria(Persona titular) {
        CuentaBancaria cuenta;
        double saldoInicial = 0;
        String numeroCuenta = banco.generarNumeroCuenta();
        
        System.out.println("");
        System.out.print("Saldo inicial >_");
        saldoInicial = inputDouble();
        
        cuenta = new CuentaBancaria(titular, saldoInicial, numeroCuenta);
        
        return cuenta;
    }
    
    //--------------------------------
    
    private static void showMessage(String txt) {
        System.out.println();
        System.out.println("-"+txt+"-");
    }
    
    private static void showHead(String txt) {
        System.out.println();
        System.out.println("<<<<"+txt+">>>>");
    }
    
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
    
    private static double inputDouble() {
        boolean valid = false;
        
        double n = -1;
        
        while (!valid) {
            valid = true;
            
            try {
                n = input.nextDouble();

                if (n < 0) {
                    valid = false;
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                valid = false;
                showError("Debe ser un numero positivo valido");
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
