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
            
            System.out.print(">_ ");
            option = inputInt();
            
            System.out.println();
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
                    opcion3();
                    break;
                case 4:
                    opcion4();
                    break;
                case 5:
                    opcion5();
                    break;
                case 6:
                    opcion6();
                    break;
                case 7:
                    running = false;
                    showMessage("Finalizado");
                    break;
                    
                default :
                    showError("Esa opcion no esta disponible");
                    break;
            }
            
            System.out.println("");
            System.out.println("_______________");
            System.out.println();
            
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
    
    private static void opcion3() {
        showHead("Obtener datos de una cuenta");
        
        System.out.print("IBAN >_ES ");
        String IBAN = inputNotBlankString();
        System.out.println();
        
        if (validarIBAN(IBAN)) {
            CuentaBancaria cuentaOriginal = buscarCuenta(IBAN);
            
            if (cuentaOriginal != null) {
                System.out.print(cuentaOriginal.devolverInfoString());
                
            } else {
                showError("La cuenta introducida no existe actualmente");
            }
            
        } else {
            showError("El IBAN proporcionado no es valido, se cancela el proceso");
        }
    }
    
    private static void opcion5() {
        showHead("Retiro de cuenta");
        
        String IBAN;
        CuentaBancaria cuentaOriginal;
        double cantidad;
        
        System.out.println("");
        System.out.print("IBAN >_ES ");
        IBAN = inputNotBlankString();
        System.out.println();
        
        if (!validarIBAN(IBAN)) {
            showError("Numero de cuenta invalido, se cancela el proceso");
            
        } else {
            if (banco.existeIBAN(IBAN)) {
                cuentaOriginal = buscarCuenta(IBAN);
                
                System.out.print("Cantidad a retirar >_");
                cantidad = inputDouble();
                System.out.println();
                
                if (cuentaOriginal.getSaldo() >= cantidad) {
                    boolean retirado = banco.retiradaCuenta(IBAN, cantidad);
                    
                    if (retirado) {
                        showMessage("Dinero retirado");
                        
                    } else {
                        showMessage("No se ha podido retirar el dinero");
                    }
                    
                } else {
                    showError("Saldo insuficiente, no es posible retirar ese dinero");
                }
                
            } else {
                showError("No existe ninguna cuenta asociado al numero introducido");
            }
        }
    }
    
    private static void opcion4 () {
        showHead("Ingreso de cuenta");
        
        String IBAN;
        double cantidad;
        
        System.out.println("");
        System.out.print("IBAN >_ES ");
        IBAN = inputNotBlankString();
        System.out.println();
        
        if (!validarIBAN(IBAN)) {
            showError("Numero de cuenta invalido, se cancela el proceso");
            
        } else {
            if (banco.existeIBAN(IBAN)) {
                
                System.out.print("Cantidad a ingresar >_");
                cantidad = inputDouble();
                System.out.println();
                
                boolean ingresado = banco.ingresoCuenta(IBAN, cantidad);

                if (ingresado) {
                    showMessage("Dinero ingresado");

                } else {
                    showMessage("No se ha podido ingresar el dinero");
                }
                
            } else {
                showError("No existe ninguna cuenta asociado al numero introducido");
            }
        }
    }
    
    private static void opcion6 () {
        showHead("Consultar saldo");
        
        String IBAN = "";
        
        System.out.println("");
        System.out.print("IBAN >_ES ");
        IBAN = inputNotBlankString();
        System.out.println();
        
        if (validarIBAN(IBAN)) {
            CuentaBancaria cuentaOriginal = buscarCuenta(IBAN);
            
            System.out.println("Saldo actual: $ " + String.valueOf(cuentaOriginal.getSaldo()));
            
        } else {
            showError("Numero IBAN invalido");
        }
    }
    
    //-------------------------
    
    private static void creacionCuentaAhorro(Persona titular) {
        showHead("Introduccion");
        
        CuentaAhorro cuentaAhorro;
        String IBAN = banco.generarIBAN();
        double intereses = -1;
        double saldo = -1;
        
        System.out.println();
        System.out.print("Saldo inicial >_");
        saldo = inputDouble();
        System.out.println();
        
        System.out.println("");
        System.out.print("Intereses >_");
        intereses = inputDouble();
        
        //Validando que el porcentaje de intereses sea valido
        
        if (intereses >= 0 && intereses <= 100) {
            cuentaAhorro = new CuentaAhorro(titular, saldo, IBAN, intereses);

            boolean insertado = banco.abrirCuenta(cuentaAhorro);

            if (insertado) {
                showMessage("Se ha creado la cuenta");

            } else {
                showMessage("No se ha podido insertar la cuenta");
            }            
        
        } else {
            showError("Debe ingresar un interes entre los valores 0%-100%");
        }
    }
    
    private static void creacionCuentaCorrientePersonal(Persona titular) {
        showHead("Introduccion");
        
        CuentaCorrientePersonal cuentaCorrientePersonal;
        double comisionMantenimiento;
        double saldo;
        String IBAN = banco.generarIBAN();
        List<String> titulares = solicitarListaTitulares(titular);
        
        System.out.println();
        System.out.print("Saldo inicial >_");
        saldo = inputDouble();
        System.out.println();
        
        System.out.println("");
        System.out.print("Comision de mantenimiento >_");
        comisionMantenimiento = inputDouble();
        
        if (comisionMantenimiento >= 0 && comisionMantenimiento <= 100) {
            cuentaCorrientePersonal = new CuentaCorrientePersonal(titular, saldo, IBAN, titulares, comisionMantenimiento);

            banco.abrirCuenta(cuentaCorrientePersonal);            
        
        } else {
            showError("Debe tener unos intereses entre los valores 0-100, se cancela el proceso");
            
        }
        
    }
    
    private static void creacionCuentaCorrienteEmpresa(Persona titular) {
        showHead("Introduccion");
        
        CuentaCorrienteEmpresa cuentaCorrienteEmpresa;
        double interesPorDescubierto;
        int maximoDescubierto;
        double saldo;
        String IBAN = banco.generarIBAN();
        List<String> titulares = solicitarListaTitulares(titular);
        
        System.out.println();
        System.out.print("Saldo inicial >_");
        saldo = inputDouble();
        System.out.println();
        
        System.out.println("");
        System.out.print("Intereses por descubierto >_");
        interesPorDescubierto = inputDouble();
        System.out.println();
        
        //No puede haber un interes superior al 100%
        if (interesPorDescubierto >= 0 && interesPorDescubierto <= 100) {
            System.out.println("");
            System.out.print("Maximo numero de descubiertos >_");
            maximoDescubierto = inputInt();
            System.out.println();

            cuentaCorrienteEmpresa = new CuentaCorrienteEmpresa(titular, saldo, IBAN, titulares, interesPorDescubierto, maximoDescubierto);

            banco.abrirCuenta(cuentaCorrienteEmpresa);            
        
        } else {
            showError("Debe ingresar un interes entre los valores 0%-100%");
        }
        
    }
    
    //Funcion para solicitar los datos necesarios para CuentaCorriente
    private static List<String> solicitarListaTitulares(Persona titular) {
        List<String> titulares = new ArrayList();
        
        boolean creandoTitulares = true;
        
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
            
            System.out.println();
            System.out.print("DNI del titular >_ ");
            String nuevoTitularDNI = inputNotBlankString().toUpperCase();
            
            if (validarDNI(nuevoTitularDNI)) {
                //Se valida si el ID ya esta agregado en la lista
                
                boolean existeDNI = false;
                
                for (String titularActual : titulares) {
                    if (titularActual.equals(nuevoTitularDNI)) {
                        existeDNI = true;
                    }
                }
                
                if (!existeDNI && !titular.getDNI().equals(nuevoTitularDNI)) {
                    titulares.add(nuevoTitularDNI);
                    
                } else {
                    showError("Este DNI ya esta asociado a esta cuenta");
                }
            }
        }
        
        return titulares;
        
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
    
    private static CuentaBancaria buscarCuenta(String IBAN) {
        for (CuentaBancaria cuentaActual : banco.getCuentas()) {
            if (cuentaActual.getIBAN().equals(IBAN)) {
                return cuentaActual;
            }
        }
        
        return null;
    }
    
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
        System.out.println("4. Ingresar efectivo de una cuenta.");
        System.out.println("5. Retirar efectivo de una cuenta.");
        System.out.println("6. Consultar el saldo actual de una cuenta.");
        System.out.println("7. Salir de la aplicacion.");
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
    
    private static boolean validarIBAN (String IBAN) {
        String regex = "\\d{20}";
        
        return IBAN.matches(regex);
    }
}
