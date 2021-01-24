/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoignacio;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
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
            System.out.println("El usuario se ha creado con exito.\nPresiona cualquier tecla para volver al menu principal...");
            entradaDatos.nextLine();
        } catch (ExcepcionProyecto ex)
        {
            PropertyConfigurator.configure("logs\\log4j.properties");
            org.apache.log4j.Logger loggerERROR = LogManager.getLogger("ERROR");
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL();
            loggerERROR.error(error);

            System.out.println(ex.getMensajeErrorUsuario() + "\nPresiona cualquier tecla para volver al menu principal...");
            entradaDatos.nextLine();
        }
    }

    public void menuEliminacion(Scanner entradaDatos)
    {
        String idUsuario;

        System.out.println("ELIMINACION DE UN USUARIO");
        System.out.print("Para poder eliminar un usuario, introduce su identificador: ");
        idUsuario = entradaDatos.nextLine();

        while (!esEntero(idUsuario) || idUsuario.length() < 5)
        {
            System.out.println("Dato erróneo. Introduce de nuevo el identificador del usuario:");
            idUsuario = entradaDatos.nextLine();
        }

        try
        {
            ProyectoCAD cAD = new ProyectoCAD();
            if (cAD.eliminarUsuario(Integer.parseInt(idUsuario)) > 0)
            {
                System.out.println("El usuario se ha eliminado con exito.\nPresiona cualquier tecla para volver al menu principal...");
                entradaDatos.nextLine();
            } else
            {
                System.out.println("No existe ningun usuario con el identificador proporcionado.\nPresiona cualquier tecla para volver al menu principal...");
                entradaDatos.nextLine();
            }
        } catch (ExcepcionProyecto ex)
        {
            PropertyConfigurator.configure("logs\\log4j.properties");
            org.apache.log4j.Logger loggerERROR = LogManager.getLogger("ERROR");
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL();
            loggerERROR.error(error);

            System.out.println(ex.getMensajeErrorUsuario() + "\nPresiona cualquier tecla para volver al menu principal...");
            entradaDatos.nextLine();
        }

    }

    public void menuModificacion(Scanner entradaDatos)
    {

    }

    public void menuLeer(Scanner entradaDatos)
    {

    }

    public void menuLeerTodos()
    {

    }
}
