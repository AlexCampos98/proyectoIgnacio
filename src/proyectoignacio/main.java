/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoignacio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alejandro Campos Maestre
 */
public class main
{

    public static void main(String[] args)
    {
        Scanner entradaDatos = new Scanner(System.in);
        String opcionMenu;

        System.out.println("Bienvenido al programa de gestion de la base de datos.");

        do
        {
            System.out.print("MENU\nOpcion 1: Insercion de datos.\nOpcion 2: Modificacion de datos.\nOpcion 3: Eliminacion de datos."
                    + "\nOpcion 4: Salir de la aplicacion.\nIntroduce el numero de la opcion a elegir: ");
            opcionMenu = entradaDatos.nextLine();

            while (!opcionMenu.matches("-?\\d+") || !esEntero(opcionMenu) || Integer.parseInt(opcionMenu) > 4)
            {
                System.out.print("Dato erróneo. Introduce de nuevo el numero de la opcion a elegir: ");
                opcionMenu = entradaDatos.nextLine();
            }

            switch (opcionMenu)
            {
                case "1":
                    System.out.println("La opcion elegida es 'Insercion de datos'.\nOpcion 1: Tabla de usuario\nOpcion 2: "
                            + "Tabla de entrenamiento\nOpcion 3: Atras\nIntroduce el numero de la opcion a elegir: ");
                    opcionMenu = entradaDatos.nextLine();
                    while (!opcionMenu.matches("-?\\d+") || !esEntero(opcionMenu) || Integer.parseInt(opcionMenu) > 3)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el numero de la opcion a elegir: ");
                        opcionMenu = entradaDatos.nextLine();
                    }
                    insercion(opcionMenu, entradaDatos);
                    break;
                case "2":
                    System.out.println("La opcion elegida es 'Modificacion de datos'.\nOpcion 1: Tabla de usuario\nOpcion 2: "
                            + "Tabla de entrenamiento\nOpcion 3: Atras\nIntroduce el numero de la opcion a elegir: ");
                    opcionMenu = entradaDatos.nextLine();
                    while (!opcionMenu.matches("-?\\d+") || !esEntero(opcionMenu) || Integer.parseInt(opcionMenu) > 3)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el numero de la opcion a elegir: ");
                        opcionMenu = entradaDatos.nextLine();
                    }
                    modificacion(opcionMenu, entradaDatos);
                    break;
                case "3":
                    System.out.println("La opcion elegida es 'Eliminacion de datos'.\nOpcion 1: Tabla de usuario\nOpcion 2: "
                            + "Tabla de entrenamiento\nOpcion 3: Atras\nIntroduce el numero de la opcion a elegir: ");
                    opcionMenu = entradaDatos.nextLine();
                    while (!opcionMenu.matches("-?\\d+") || !esEntero(opcionMenu) || Integer.parseInt(opcionMenu) > 3)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el numero de la opcion a elegir: ");
                        opcionMenu = entradaDatos.nextLine();
                    }
                    eliminacion(opcionMenu, entradaDatos);
                    break;
            }
        } while (!opcionMenu.equals("4"));

        System.out.println("Has elegido la opcion de salida de la aplicacion. Hasta la vista.");

        entradaDatos.close();
    }

    /**
     * Menu de insercion de datos de las tablas
     *
     * @param entradaDatos
     * @param opcion
     */
    public static void insercion(String opcion, Scanner entradaDatos)
    {

        //Tabla usuario
        if (opcion.equals("1"))
        {
            String correo, nombre, apellido1, apellido2, telefono, telefonoEmergencia, nombreUsuario;

            System.out.print("Vamos a introducir los siguientes datos para la creacion del usuario:\nIntroduce "
                    + "el correo: ");
            correo = entradaDatos.nextLine();
            while (!esCorreo(correo))
            {
                System.out.print("Dato erróneo. Introduce de nuevo el correo (____@____.___): ");
                correo = entradaDatos.nextLine();
            }

            System.out.print("Introduce el nombre: ");
            nombre = entradaDatos.nextLine();
            while (nombre.equals(""))
            {
                System.out.print("No has introducido ningun nombre. Introduce de nuevo el nombre: ");
                nombre = entradaDatos.nextLine();
            }

            System.out.print("Introduce el primer apellido: ");
            apellido1 = entradaDatos.nextLine();
            while (apellido1.equals(""))
            {
                System.out.print("No has introducido ningun apellido. Introduce de nuevo el primer apellido: ");
                apellido1 = entradaDatos.nextLine();
            }

            System.out.print("Introduce el segundo apellido: ");
            apellido2 = entradaDatos.nextLine();
            while (apellido2.equals(""))
            {
                System.out.print("No has introducido ningun apellido. Introduce de nuevo el segundo apellido: ");
                apellido2 = entradaDatos.nextLine();
            }

            System.out.print("Introduce el numero de telefono: ");
            telefono = entradaDatos.nextLine();

            while (!esEntero(telefono) || telefono.length() != 9)
            {
                System.out.print("Dato erróneo. Introduce de nuevo el numero de telefono: ");
                telefono = entradaDatos.nextLine();
            }

            System.out.print("Introduce el numero de telefono de emergencia: ");
            telefonoEmergencia = entradaDatos.nextLine();

            while (!esEntero(telefonoEmergencia) || telefonoEmergencia.length() != 9)
            {
                System.out.print("Dato erróneo. Introduce de nuevo el numero de telefono de emergencia: ");
                telefonoEmergencia = entradaDatos.nextLine();
            }

            System.out.print("Introduce el nombre que se mostrará en la aplicacion: ");
            nombreUsuario = entradaDatos.nextLine();
            while (nombreUsuario.equals(""))
            {
                System.out.print("No has introducido ningun nombre. Introduce de nuevo el nombre que se mostrará en la aplicacion: ");
                nombreUsuario = entradaDatos.nextLine();
            }

            /**
             * Zona donde se enviarian los datos, para insertarlos en la BD
             */
            System.out.println("Los datos han sido guardados. Todavia no, esta en proceso de creacion.");

        } //Tabla entrenamiento
        else if (opcion.equals("2"))
        {
            String nombre, plazas, fecha, id_usuario_entrenador, id_usuario_deportista;
            Date fechaEntranamiento;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            System.out.print("Introduce el nombre del entrenamiento: ");
            nombre = entradaDatos.nextLine();
            while (nombre.equals(""))
            {
                System.out.print("No has introducido ningun nombre. Introduce de nuevo el nombre del entrenamiento: ");
                nombre = entradaDatos.nextLine();
            }

            System.out.print("Introduce el numero de plazas abiertas: ");
            plazas = entradaDatos.nextLine();

            while (!esEntero(plazas) || plazas.length() > 999)
            {
                System.out.print("Dato erróneo. Introduce de nuevo el numero de plazas: ");
                plazas = entradaDatos.nextLine();
            }

            System.out.print("Introduce la fecha del entrenamiento: ");
            fecha = entradaDatos.nextLine();

            while (!esFecha(fecha))
            {
                System.out.print("Dato erróneo. Introduce de nuevo la fecha del entrenamiento (yyyy-MM-dd): ");
                fecha = entradaDatos.nextLine();
            }
            try
            {
                fechaEntranamiento = sdf.parse(fecha);
            } catch (ParseException e)
            {
            }
            
            System.out.print("Introduce el identificador del entrenador: ");
            id_usuario_entrenador = entradaDatos.nextLine();

            //Falta comprobar si este usuario existe en la tabla usuario
            while (!esEntero(id_usuario_entrenador) || id_usuario_entrenador.length() > 99999)
            {
                System.out.print("Dato erróneo. Introduce de nuevo el identificador del entrenador: ");
                id_usuario_entrenador = entradaDatos.nextLine();
            }
            
            System.out.print("Introduce el identificador del deportista: ");
            id_usuario_deportista = entradaDatos.nextLine();

            //Falta comprobar si este usuario existe en la tabla usuario
            while (!esEntero(id_usuario_deportista) || id_usuario_deportista.length() > 99999)
            {
                System.out.print("Dato erróneo. Introduce de nuevo el identificador del entrenador: ");
                id_usuario_deportista = entradaDatos.nextLine();
            }
            
            /**
             * Zona donde se enviarian los datos, para insertarlos en la BD
             */
            System.out.println("Los datos han sido guardados. Todavia no, esta en proceso de creacion.");
        }
    }

    /**
     * Menu de modificacion de datos de las tablas
     *
     * @param opcion
     * @param entradaDatos
     */
    public static void modificacion(String opcion, Scanner entradaDatos)
    {
        //Tabla usuario
        if (opcion.equals("1"))
        {
            String idUsuario, correo, nombre, apellido1, apellido2, telefono, telefonoEmergencia, nombreUsuario;
            System.out.println("Vamos a introducir los siguientes datos para la creacion del usuario:\nIntroduce "
                    + "el correo: ");
        } //Tabla entrenamiento
        else if (opcion.equals("2"))
        {
            String idEntrenamiento, nombre, fecha, plazas, id_usuario_entrenador, id_usuario_deportista;

        }
    }

    /**
     * Menu de eliminacion de datos de las tablas
     *
     * @param opcion
     * @param entradaDatos
     */
    public static void eliminacion(String opcion, Scanner entradaDatos)
    {
        //Tabla usuario
        if (opcion.equals("1"))
        {
            String idUsuario;
            System.out.println("Vamos a introducir los siguientes datos para la creacion del usuario:\nIntroduce "
                    + "el correo: ");
        } //Tabla entrenamiento
        else if (opcion.equals("2"))
        {
            String idEntrenamiento;

        }
    }

    /**
     * Método que valida si un string contiene un dato de tipo int válido
     *
     * @param s String a validar si contiene un dato de tipo int válido
     * @return true si el string contiene un dato de tipo int válido o false en
     * caso contrario
     */
    public static boolean esEntero(String s)
    {
        try
        {
            Integer.parseInt(s);
        } catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    /**
     * Método que valida si un string contiene un dato de clase SimpleDateFormat
     * válido
     *
     * @param s String a validar si contiene un dato de clase SimpleDateFormat
     * válido
     * @return true si el string contiene un dato de clase SimpleDateFormat
     * válido o false en caso contrario
     */
    public static boolean esFecha(String s)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            sdf.parse(s);
        } catch (ParseException e)
        {
            return false;
        }
        return true;
    }

    public static boolean esCorreo(String correo)
    {
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);
        if (mather.find() == true)
        {
            return true;
        } else
        {
            return false;
        }
    }
}
