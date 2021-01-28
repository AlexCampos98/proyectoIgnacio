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
import proyectocad.ExcepcionProyecto;
import proyectocad.ProyectoCAD;

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
        
        try
        {
            /*
            *   IMPORTANTE Desde este constructor podras cambiar la ip, el nombre de la BD y la contraseña.
            *   En caso de quitarlo o usar el constructor por defecto, los parametros por defecto son:
            *   IP: 192.168.1.125
            *   BD: proyecto
            *   Contraseña: kk
            *
            *   Es posible eliminar este try/catch, esto hara que se usen los parametros por defecto.
            */
            ProyectoCAD cAD = new ProyectoCAD("192.168.1.125", "proyecto", "kk");
        } catch (ExcepcionProyecto ex)
        {
            Logs log = new Logs();
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL() + "\n";
            log.logCAD(error);

            System.err.println(ex.getMensajeErrorUsuario());
        }

        System.out.println("Bienvenido al programa de gestion de la base de datos.");

        do
        {
            System.out.print("MENU PRINCIPAL\nOpcion 1: Crear un nuevo usuario o un entrenamiento.\nOpcion 2: Modificar un usuario o un entrenamiento.\nOpcion 3: Eliminar un usuario o un entrenamiento."
                    + "\nOpcion 4: Obtener la informacion de usuario/s o de entrenamiento/s.\nOpcion 5: Salir de la aplicacion.\nIntroduce el numero de la opcion a elegir: ");
            opcionMenu = entradaDatos.nextLine();

            while (!opcionMenu.matches("-?\\d+") || !esEntero(opcionMenu) || Integer.parseInt(opcionMenu) > 5)
            {
                System.out.print("Dato erróneo. Introduce de nuevo el numero de la opcion a elegir: ");
                opcionMenu = entradaDatos.nextLine();
            }

            System.out.println("\n\n\n\n\n\n\n");
            
            switch (opcionMenu)
            {
                case "1":
                    System.out.println("MENU INSERCION DE DATOS.\nOpcion 1: Introducir un usuario\nOpcion 2: "
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

                        default:
                            opcionMenu = "0";
                    }
                    break;

                case "2":
                    System.out.println("MENU MODIFICACION DE INFORMACION.\nOpcion 1: Modificar un usuario\nOpcion 2: "
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

                        default:
                            opcionMenu = "0";
                    }
                    break;

                case "3":
                    System.out.println("MENU ELIMINACION DE INFORMACION.\nOpcion 1: Eliminar un usuario\nOpcion 2: "
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

                        default:
                            opcionMenu = "0";
                    }
                    break;

                case "4":
                    System.out.println("MENU BUSQUEDA Y OBTENCION DE INFORMACION.\nOpcion 1: Buscar informacion de un usuario\nOpcion 2: "
                            + "Obtener la informacion de todos los usuarios\nOpcion 3: Buscar informacion de un entrenamiento\n"
                            + "Opcion 4: Obtener la informacion de todos los entrenamientos\nOpcion 5: Atras\nIntroduce el numero de la opcion a elegir: ");
                    opcionMenu = entradaDatos.nextLine();
                    while (!opcionMenu.matches("-?\\d+") || !esEntero(opcionMenu) || Integer.parseInt(opcionMenu) > 5 || Integer.parseInt(opcionMenu) < 0)
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

                        case "3":
                            MenuEntrenamiento menuEntrenamiento = new MenuEntrenamiento();
                            menuEntrenamiento.menuLeer(entradaDatos);
                            break;

                        case "4":
                            MenuEntrenamiento menuEntrenamiento1 = new MenuEntrenamiento();
                            menuEntrenamiento1.menuLeerTodos();
                            break;

                        default:
                            opcionMenu = "0";
                    }
                    break;
            }
            System.out.println("\n\n\n\n\n\n\n");
        } while (!opcionMenu.equals("5"));

        System.out.println("Has elegido la opcion de salida de la aplicacion. Hasta la vista.");

        entradaDatos.close();
    }

    /**
     * Mét.odo que valida si un string contiene un dato de tipo int válido
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
     * Mét.odo que valida si un string contiene un dato de clase
     * SimpleDateFormat válido
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
