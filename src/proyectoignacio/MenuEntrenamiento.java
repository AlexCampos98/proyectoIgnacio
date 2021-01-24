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
import static proyectoignacio.main.esEntero;
import static proyectoignacio.main.esFecha;

/**
 *
 * @author Alejandro Campos Maestre
 */
public class MenuEntrenamiento
{
    public void menuInsercion(Scanner entradaDatos)
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
//            entrenamiento entrenamiento = new entrenamiento(null, plazas, nombre, fechaEntranamiento, idUsuarioEntrenador, idUsuarioDeportista);
//            
//            ProyectoIgnacioCAD conexion = new ProyectoIgnacioCAD();
//            
//            try
//            {
//                conexion.insertarEntrenamiento(entrenamiento);
//            } catch (excepcionProyecto ex)
//            {
//                System.out.println(ex.getMensajeErrorUsuario());
//                
//                PropertyConfigurator.configure("logs\\log4j.properties");
//                org.apache.log4j.Logger logger = LogManager.getLogger("ERROR");
//                logger.error(ex.getCodigoError() + " - " + ex.getMensajeErrorAdministrador() + " - " + ex.getSentenciaSQL());
//            }
            System.out.println("Los datos han sido guardados.");
    }

    public void menuEliminacion(Scanner entradaDatos)
    {

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
