/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoignacio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            System.out.print("MENU PRINCIPAL\nOpcion 1: Crear un nuevo usuario o un entrenamiento.\nOpcion 2: Modificar un usuario o un entrenamiento.\nOpcion 3: Eliminar un usuario o un entrenamiento."
                    + "\nOpcion 4: Obtener la informacion de usuario/s o de entrenamiento/s.\nOpcion 5: Salir de la aplicacion.\nIntroduce el numero de la opcion a elegir: ");
            opcionMenu = entradaDatos.nextLine();

            while (!opcionMenu.matches("-?\\d+") || !esEntero(opcionMenu) || Integer.parseInt(opcionMenu) > 4)
            {
                System.out.print("Dato erróneo. Introduce de nuevo el numero de la opcion a elegir: ");
                opcionMenu = entradaDatos.nextLine();
            }

            switch (opcionMenu)
            {
                case "1":
                    System.out.println("La opcion elegida es 'Introducir nueva informacion'.\nOpcion 1: Introducir un usuario\nOpcion 2: "
                            + "Introducir un entrenamiento\nOpcion 3: Atras\nIntroduce el numero de la opcion a elegir: ");
                    opcionMenu = entradaDatos.nextLine();
                    while (!opcionMenu.matches("-?\\d+") || !esEntero(opcionMenu) || Integer.parseInt(opcionMenu) > 3 || Integer.parseInt(opcionMenu) < 0)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el numero de la opcion a elegir: ");
                        opcionMenu = entradaDatos.nextLine();
                    }

                    switch (opcionMenu)
                    {
                        case "1":
                            MenuUsuario menuUsuario = new MenuUsuario();
                            menuUsuario.menuInsercion(entradaDatos);
                            break;

                        case "2":
                            MenuEntrenamiento menuEntrenamiento = new MenuEntrenamiento();
                            menuEntrenamiento.menuInsercion(entradaDatos);
                            break;
                    }
                    break;
                    
                case "2":
                    System.out.println("La opcion elegida es 'Modificar informacion'.\nOpcion 1: Modificar un usuario\nOpcion 2: "
                            + "Modificar un entrenamiento\nOpcion 3: Atras\nIntroduce el numero de la opcion a elegir: ");
                    opcionMenu = entradaDatos.nextLine();
                    while (!opcionMenu.matches("-?\\d+") || !esEntero(opcionMenu) || Integer.parseInt(opcionMenu) > 3 || Integer.parseInt(opcionMenu) < 0)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el numero de la opcion a elegir: ");
                        opcionMenu = entradaDatos.nextLine();
                    }

                    switch (opcionMenu)
                    {
                        case "1":
                            MenuUsuario menuUsuario = new MenuUsuario();
                            menuUsuario.menuModificacion(entradaDatos);
                            break;

                        case "2":
                            MenuEntrenamiento menuEntrenamiento = new MenuEntrenamiento();
                            menuEntrenamiento.menuModificacion(entradaDatos);
                            break;
                    }
                    break;
                    
                case "3":
                    System.out.println("La opcion elegida es 'Eliminar informacion'.\nOpcion 1: Eliminar un usuario\nOpcion 2: "
                            + "Eliminar un entrenamiento\nOpcion 3: Atras\nIntroduce el numero de la opcion a elegir: ");
                    opcionMenu = entradaDatos.nextLine();
                    while (!opcionMenu.matches("-?\\d+") || !esEntero(opcionMenu) || Integer.parseInt(opcionMenu) > 3 || Integer.parseInt(opcionMenu) < 0)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el numero de la opcion a elegir: ");
                        opcionMenu = entradaDatos.nextLine();
                    }

                    switch (opcionMenu)
                    {
                        case "1":
                            MenuUsuario menuUsuario = new MenuUsuario();
                            menuUsuario.menuEliminacion(entradaDatos);
                            break;

                        case "2":
                            MenuEntrenamiento menuEntrenamiento = new MenuEntrenamiento();
                            menuEntrenamiento.menuEliminacion(entradaDatos);
                            break;
                    }
                    break;
                    
                    case "4":
                    System.out.println("La opcion elegida es 'Buscar y obtener informacion'.\nOpcion 1: Buscar informacion de un usuario\nOpcion 2: "
                            + "Obtener la informacion de todos los usuarios\nOpcion 3: Buscar informacion de un entrenamiento\n"
                            + "Opcion 4: Obtener la informacion de todos los entrenamientos\nOpcion 5: Atras\nIntroduce el numero de la opcion a elegir: ");
                    opcionMenu = entradaDatos.nextLine();
                    while (!opcionMenu.matches("-?\\d+") || !esEntero(opcionMenu) || Integer.parseInt(opcionMenu) > 3 || Integer.parseInt(opcionMenu) < 0)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el numero de la opcion a elegir: ");
                        opcionMenu = entradaDatos.nextLine();
                    }

                    switch (opcionMenu)
                    {
                        case "1":
                            MenuUsuario menuUsuario = new MenuUsuario();
                            menuUsuario.menuLeer(entradaDatos);
                            break;

                        case "2":
                            MenuUsuario menuUsuario1 = new MenuUsuario();
                            menuUsuario1.menuLeerTodos();
                            break;
                            
                        case "4":
                            MenuEntrenamiento menuEntrenamiento = new MenuEntrenamiento();
                            menuEntrenamiento.menuLeer(entradaDatos);
                            break;

                        case "5":
                            MenuEntrenamiento menuEntrenamiento1 = new MenuEntrenamiento();
                            menuEntrenamiento1.menuLeerTodos();
                            break;
                    }
                    break;
            }
        } while (!opcionMenu.equals("5"));

        System.out.println("Has elegido la opcion de salida de la aplicacion. Hasta la vista.");

        entradaDatos.close();
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
