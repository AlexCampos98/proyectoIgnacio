/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoignacio;

import java.util.Scanner;
import proyectocad.ExcepcionProyecto;
import proyectocad.ProyectoCAD;
import proyectocad.Usuario;
import static proyectoignacio.main.esCorreo;
import static proyectoignacio.main.esEntero;

/**
 *
 * @author Alejandro Campos Maestre
 */
public class MenuUsuario
{

    private final Logs log = new Logs();

    public void menuInsercion(Scanner entradaDatos)
    {
        String correo, nombre, apellido1, apellido2, telefono, telefonoEmergencia, nombreUsuario;

        System.out.println("CREACION DE UN USUARIO");
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

        Usuario usuario = new Usuario(null, correo, nombre, apellido1, apellido2, telefono, telefonoEmergencia, nombreUsuario);

        try
        {
            ProyectoCAD cAD = new ProyectoCAD();
            cAD.insertarUsuario(usuario);
            System.out.println("El usuario se ha creado con exito.");
        } catch (ExcepcionProyecto ex)
        {
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL();
            log.logCAD(error);

            System.out.println(ex.getMensajeErrorUsuario());
        }
        
        System.out.println("Presiona cualquier tecla para volver al menu principal...");
        entradaDatos.nextLine();
    }

    public void menuEliminacion(Scanner entradaDatos)
    {
        String idUsuario;

        System.out.println("ELIMINACION DE UN USUARIO");
        System.out.print("Para poder eliminar un usuario, introduce su identificador: ");
        idUsuario = entradaDatos.nextLine();

        while (!esEntero(idUsuario) || idUsuario.length() < 5 || Integer.parseInt(idUsuario) < 0)
        {
            System.out.println("Dato erróneo. Introduce de nuevo el identificador del usuario:");
            idUsuario = entradaDatos.nextLine();
        }

        try
        {
            ProyectoCAD cAD = new ProyectoCAD();
            if (cAD.eliminarUsuario(Integer.parseInt(idUsuario)) > 0)
            {
                System.out.println("El usuario se ha eliminado con exito.");
            } else
            {
                System.out.println("No existe ningun usuario con el identificador proporcionado.");
            }
        } catch (ExcepcionProyecto ex)
        {
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL();
            log.logCAD(error);

            System.out.println(ex.getMensajeErrorUsuario());
        }
        
        System.out.println("Presiona cualquier tecla para volver al menu principal...");
        entradaDatos.nextLine();
    }

    public void menuModificacion(Scanner entradaDatos)
    {
        String idUsuario, correo, nombre, apellido1, apellido2, telefono, telefonoEmergencia, nombreUsuario;
        Usuario usu;

        System.out.println("MODIFICACION DE UN USUARIO");
        System.out.print("Para poder modificacar un usuario, es necesario la siguiente informacion.\nIntroduce el identificador del usuario a modificar: ");

        idUsuario = entradaDatos.nextLine();
        while (!esEntero(idUsuario) || idUsuario.length() > 5 || Integer.parseInt(idUsuario) < 0)
        {
            System.out.println("Dato erróneo. Introduce de nuevo el identificador del usuario:");
            idUsuario = entradaDatos.nextLine();
        }

        try
        {
            ProyectoCAD cAD = new ProyectoCAD();
            usu = cAD.leerUsuario(Integer.parseInt(idUsuario));
            if (usu.getIdUsuario() != null)
            {
                System.out.println("Introduce el correo (" + usu.getCorreo() + "): ");

                correo = entradaDatos.nextLine();
                while (!esCorreo(correo))
                {
                    System.out.print("Dato erróneo. Introduce de nuevo el correo (____@____.___): ");
                    correo = entradaDatos.nextLine();
                }

                System.out.print("Introduce el nombre (" + usu.getNombre() + "): ");
                nombre = entradaDatos.nextLine();
                while (nombre.equals(""))
                {
                    System.out.print("No has introducido ningun nombre. Introduce de nuevo el nombre: ");
                    nombre = entradaDatos.nextLine();
                }

                System.out.print("Introduce el primer apellido (" + usu.getApellido1() + "): ");
                apellido1 = entradaDatos.nextLine();
                while (apellido1.equals(""))
                {
                    System.out.print("No has introducido ningun apellido. Introduce de nuevo el primer apellido: ");
                    apellido1 = entradaDatos.nextLine();
                }

                System.out.print("Introduce el segundo apellido (" + usu.getApellido2() + "): ");
                apellido2 = entradaDatos.nextLine();
                while (apellido2.equals(""))
                {
                    System.out.print("No has introducido ningun apellido. Introduce de nuevo el segundo apellido: ");
                    apellido2 = entradaDatos.nextLine();
                }

                System.out.print("Introduce el numero de telefono (" + usu.getTelefono() + "): ");
                telefono = entradaDatos.nextLine();

                while (!esEntero(telefono) || telefono.length() != 9)
                {
                    System.out.print("Dato erróneo. Introduce de nuevo el numero de telefono: ");
                    telefono = entradaDatos.nextLine();
                }

                System.out.print("Introduce el numero de telefono de emergencia (" + usu.getTelefonoEmergencia() + "): ");
                telefonoEmergencia = entradaDatos.nextLine();

                while (!esEntero(telefonoEmergencia) || telefonoEmergencia.length() != 9)
                {
                    System.out.print("Dato erróneo. Introduce de nuevo el numero de telefono de emergencia: ");
                    telefonoEmergencia = entradaDatos.nextLine();
                }

                System.out.print("Introduce el nombre que se mostrará en la aplicacion (" + usu.getNombreUsuario() + "): ");
                nombreUsuario = entradaDatos.nextLine();
                while (nombreUsuario.equals(""))
                {
                    System.out.print("No has introducido ningun nombre. Introduce de nuevo el nombre que se mostrará en la aplicacion: ");
                    nombreUsuario = entradaDatos.nextLine();
                }

                usu = new Usuario(Integer.parseInt(idUsuario), correo, nombre, apellido1, apellido2, telefono, telefonoEmergencia, nombreUsuario);

                try
                {
                    cAD = new ProyectoCAD();
                    cAD.modificarUsuario(usu);
                    System.out.println("Se ha modificado el usuario con exito.");
                } catch (ExcepcionProyecto ex)
                {
                    String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL();
                    log.logCAD(error);

                    System.out.println(ex.getMensajeErrorUsuario());
                }
            } else
            {
                System.out.println("El usuario no existe.");
            }
        } catch (ExcepcionProyecto ex)
        {
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL();
            log.logCAD(error);

            System.out.println(ex.getMensajeErrorUsuario());
        }

        System.out.println("Presiona cualquier tecla para volver al menu principal...");
        entradaDatos.nextLine();
    }

    public void menuLeer(Scanner entradaDatos)
    {
        String idUsuario;
        Usuario usu;
        System.out.println("MENU BUSQUEDA DE INFORMACION DE UN USUARIO");
        System.out.println("Para poder buscar y obtener la informacion del usuario sera necesario su identificador.\nIntroduce el identificador del usaurio: ");

        idUsuario = entradaDatos.nextLine();
        while (!esEntero(idUsuario) || idUsuario.length() > 5 || Integer.parseInt(idUsuario) < 0)
        {
            System.out.println("Dato erróneo. Introduce de nuevo el identificador del usuario:");
            idUsuario = entradaDatos.nextLine();
        }

        try
        {
            ProyectoCAD cAD = new ProyectoCAD();
            usu = cAD.leerUsuario(Integer.parseInt(idUsuario));

            //TODO terminar de poner bien la salida de los datos del usuario.
            if (usu.getIdUsuario() != null)
            {
                System.out.println(usu.toString());
            } else
            {
                System.out.println("El usuario no existe.");
            }

        } catch (ExcepcionProyecto ex)
        {
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL();
            log.logCAD(error);

            System.out.println(ex.getMensajeErrorUsuario());
        }
        System.out.println("Presiona cualquier tecla para volver al menu principal...");
        entradaDatos.nextLine();
    }

    public void menuLeerTodos()
    {
        Scanner espera = new Scanner(System.in);
        System.out.println("MENU BUSQUEDA DE INFORMACION DE TODOS LOS USUARIOS");

        //TODO hacer que los datos obtenidos se pongan decentemente.
        try
        {
            ProyectoCAD cAD = new ProyectoCAD();
            System.out.println(cAD.leerUsuarios());
        } catch (ExcepcionProyecto ex)
        {
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL();
            log.logCAD(error);

            System.out.println(ex.getMensajeErrorUsuario());
        }

        System.out.println("Presiona cualquier tecla para volver al menu principal...");
        espera.nextLine();
    }
}
