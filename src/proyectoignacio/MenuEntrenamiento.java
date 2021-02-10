/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoignacio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import proyectocad.Entrenamiento;
import proyectocad.ExcepcionProyecto;
import proyectocad.ProyectoCAD;
import proyectocad.Usuario;
import static proyectoignacio.Main.esEntero;
import static proyectoignacio.Main.esFecha;

/**
 *
 * @author Alejandro Campos Maestre
 */
public class MenuEntrenamiento
{

    private final Logs log = new Logs();

    public void menuInsercion(Scanner entradaDatos)
    {
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("CREACION DE UN ENTRENAMIENTO");
        try
        {
            ProyectoCAD cAD = new ProyectoCAD();
            Usuario usuEntre, usuDepor;

            String nombre, plazas, fecha, id_usuario_entrenador, id_usuario_deportista;
            Date fechaEntranamiento;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            System.out.print("Introduce el nombre del entrenamiento: ");
            nombre = entradaDatos.nextLine();
            while (nombre.equals("") || nombre.length() > 30)
            {
                if (nombre.length() > 30)
                {
                    System.out.print("El nombre introducido supera el limite de 30 caracteres. Introduce de nuevo el nombre del entrenamiento: ");
                } else
                {
                    System.out.print("No has introducido ningun nombre. Introduce de nuevo el nombre del entrenamiento: ");
                }
                nombre = entradaDatos.nextLine();
            }

            System.out.print("Introduce el numero de plazas abiertas: ");
            plazas = entradaDatos.nextLine();

            while (!esEntero(plazas) || Integer.parseInt(plazas) > 999 || Integer.parseInt(plazas) < 0)
            {
                System.out.print("Dato erróneo. Introduce de nuevo el numero de plazas (limite de 999): ");
                plazas = entradaDatos.nextLine();
            }

            System.out.print("Introduce la fecha del entrenamiento: ");
            fecha = entradaDatos.nextLine();

            while (!esFecha(fecha))
            {
                System.out.print("Dato erróneo. Introduce de nuevo la fecha del entrenamiento (yyyy-MM-dd): ");
                fecha = entradaDatos.nextLine();
            }

            System.out.print("Introduce el identificador del entrenador: ");
            id_usuario_entrenador = entradaDatos.nextLine();

            while (!esEntero(id_usuario_entrenador) || id_usuario_entrenador.length() > 3 || Integer.parseInt(id_usuario_entrenador) < 0)
            {
                System.out.print("Dato erróneo. Introduce de nuevo el identificador del entrenador: ");
                id_usuario_entrenador = entradaDatos.nextLine();
            }

            do
            {
                usuEntre = cAD.leerUsuario(Integer.parseInt(id_usuario_entrenador));
                if (usuEntre.getIdUsuario() == null)
                {
                    id_usuario_entrenador = null;
                    System.out.println("El entrenador selecionado no existe.");
                    while (!esEntero(id_usuario_entrenador) || id_usuario_entrenador.length() > 3 || Integer.parseInt(id_usuario_entrenador) < 0)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el identificador del entrenador: ");
                        id_usuario_entrenador = entradaDatos.nextLine();
                    }
                }
            } while (usuEntre.getIdUsuario() == null);

            System.out.print("Introduce el identificador del deportista: ");
            id_usuario_deportista = entradaDatos.nextLine();

            while (!esEntero(id_usuario_deportista) || id_usuario_deportista.length() > 3 || Integer.parseInt(id_usuario_deportista) < 0)
            {
                System.out.print("Dato erróneo. Introduce de nuevo el identificador del deportista: ");
                id_usuario_deportista = entradaDatos.nextLine();
            }

            do
            {
                usuDepor = cAD.leerUsuario(Integer.parseInt(id_usuario_deportista));
                if (usuDepor.getIdUsuario() == null)
                {
                    id_usuario_deportista = null;
                    System.out.println("El deportista selecionado no existe.");
                    while (!esEntero(id_usuario_deportista) || id_usuario_deportista.length() > 3 || Integer.parseInt(id_usuario_deportista) < 0)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el identificador del deportista: ");
                        id_usuario_deportista = entradaDatos.nextLine();
                    }
                }
            } while (usuDepor.getIdUsuario() == null);

            fechaEntranamiento = sdf.parse(fecha);
            java.sql.Date fechaSql = new java.sql.Date(fechaEntranamiento.getTime());
            Entrenamiento entrenamiento = new Entrenamiento(null, Integer.parseInt(plazas), nombre, fechaSql, usuEntre, usuDepor);
            cAD.insertarEntrenamiento(entrenamiento);

            System.out.println("Se ha creado correctamente el entrenamiento.");
        } catch (ExcepcionProyecto ex)
        {
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL() + "\n";
            log.logCAD(error);

            System.out.println(ex.getMensajeErrorUsuario());
        } catch (ParseException ex)
        {
            String error = "Error ParseException(Date): " + ex.getMessage();
            log.logCAD(error);
        }

        System.out.println("Presiona cualquier tecla para volver al menu principal...");
        entradaDatos.nextLine();
    }

    public void menuEliminacion(Scanner entradaDatos)
    {
        System.out.println("\n\n\n\n\n\n\n");
        String idEntrenamiento;

        System.out.println("ELIMINACION DE UN ENTRENAMIENTO");
        System.out.print("Para poder eliminar un entrenamiento, introduce su identificador: ");
        idEntrenamiento = entradaDatos.nextLine();

        while (!esEntero(idEntrenamiento) || idEntrenamiento.length() > 5 || Integer.parseInt(idEntrenamiento) < 0)
        {
            System.out.println("Dato erróneo. Introduce de nuevo el identificador del entrenamiento:");
            idEntrenamiento = entradaDatos.nextLine();
        }

        try
        {
            ProyectoCAD cAD = new ProyectoCAD();
            if (cAD.eliminarEntrenamiento(Integer.parseInt(idEntrenamiento)) > 0)
            {
                System.out.println("El entrenamiento se ha eliminado con exito.");
            } else
            {
                System.out.println("No existe ningun entrenamiento con el identificador proporcionado.");
            }
        } catch (ExcepcionProyecto ex)
        {
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL() + "\n";
            log.logCAD(error);

            System.err.println(ex.getMensajeErrorUsuario());
        }

        System.out.println("Presiona cualquier tecla para volver al menu principal...");
        entradaDatos.nextLine();
    }

    public void menuModificacion(Scanner entradaDatos)
    {
        System.out.println("\n\n\n\n\n\n\n");
        Usuario usuEntre, usuDepor;
        Entrenamiento entrenamiento;

        String idEntrenamiento, nombre, plazas, fecha, id_usuario_entrenador, id_usuario_deportista;
        Date fechaEntranamiento;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("MODIFICACION DE UN ENTRENAMIENTO");
        System.out.print("Para poder modificar un entrenamiento, es necesario la siguiente informacion.\n\nIMPORTANTE se mostrara la informacion del entrenamiento"
                + " entre parentesis, si desea no cambiarla presione la tecla ENTER.\nIntroduce el identificador del entrenamiento a modificar: ");
        idEntrenamiento = entradaDatos.nextLine();

        while (!esEntero(idEntrenamiento) || idEntrenamiento.length() > 5 || Integer.parseInt(idEntrenamiento) < 0)
        {
            System.out.println("Dato erróneo. Introduce de nuevo el identificador del entrenamiento:");
            idEntrenamiento = entradaDatos.nextLine();
        }

        try
        {
            ProyectoCAD cAD = new ProyectoCAD();

            entrenamiento = cAD.leerEntrenamiento(Integer.parseInt(idEntrenamiento));
            if (entrenamiento.getIdEntrenamiento() != null)
            {
                System.out.print("Introduce el nombre del entrenamiento (" + entrenamiento.getNombre() + "): ");
                nombre = entradaDatos.nextLine();

                if (nombre.equals(""))
                {
                    nombre = entrenamiento.getNombre();
                } else
                {
                    while (nombre.equals("") || nombre.length() > 30)
                    {
                        System.out.print("No has introducido ningun nombre. Introduce de nuevo el nombre del entrenamiento: ");
                        nombre = entradaDatos.nextLine();
                    }
                }

                System.out.print("Introduce el numero de plazas abiertas (" + entrenamiento.getPlazas() + "): ");
                plazas = entradaDatos.nextLine();

                if (plazas.equals(""))
                {
                    plazas = Integer.toString(entrenamiento.getPlazas());
                } else
                {
                    while (!esEntero(plazas) || Integer.parseInt(plazas) > 999 || Integer.parseInt(plazas) < 0)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el numero de plazas: ");
                        plazas = entradaDatos.nextLine();
                    }
                }

                System.out.print("Introduce la fecha del entrenamiento (" + entrenamiento.getFecha() + "): ");
                fecha = entradaDatos.nextLine();

                if (fecha.equals(""))
                {
                    fecha = entrenamiento.getFecha() + "";
                } else
                {
                    while (!esFecha(fecha))
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo la fecha del entrenamiento (yyyy-MM-dd): ");
                        fecha = entradaDatos.nextLine();
                    }
                }

                System.out.print("Introduce el identificador del entrenador (" + entrenamiento.getIdUsuarioEntrenador().getIdUsuario() + "): ");
                id_usuario_entrenador = entradaDatos.nextLine();

                if (id_usuario_entrenador.equals(""))
                {
                    id_usuario_entrenador = Integer.toString(entrenamiento.getIdUsuarioEntrenador().getIdUsuario());
                } else
                {
                    while (!esEntero(id_usuario_entrenador) || id_usuario_entrenador.length() > 5 || Integer.parseInt(id_usuario_entrenador) < 0)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el identificador del entrenador: ");
                        id_usuario_entrenador = entradaDatos.nextLine();
                    }
                }

                do
                {
                    usuEntre = cAD.leerUsuario(Integer.parseInt(id_usuario_entrenador));
                    if (usuEntre.getIdUsuario() == null)
                    {
                        id_usuario_entrenador = null;
                        System.out.println("El usuario selecionado no existe.");
                        while (!esEntero(id_usuario_entrenador) || id_usuario_entrenador.length() > 5 || Integer.parseInt(id_usuario_entrenador) < 0)
                        {
                            System.out.print("Dato erróneo. Introduce de nuevo el identificador del entrenador: ");
                            id_usuario_entrenador = entradaDatos.nextLine();
                        }
                    }
                } while (usuEntre.getIdUsuario() == null);

                System.out.print("Introduce el identificador del deportista (" + entrenamiento.getIdUsuarioDeportista().getIdUsuario() + "): ");
                id_usuario_deportista = entradaDatos.nextLine();

                if (id_usuario_deportista.equals(""))
                {
                    id_usuario_deportista = Integer.toString(entrenamiento.getIdUsuarioDeportista().getIdUsuario());
                } else
                {
                    while (!esEntero(id_usuario_deportista) || id_usuario_deportista.length() > 5 || Integer.parseInt(id_usuario_deportista) < 0)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el identificador del entrenador: ");
                        id_usuario_deportista = entradaDatos.nextLine();
                    }
                }

                do
                {
                    usuDepor = cAD.leerUsuario(Integer.parseInt(id_usuario_deportista));
                    if (usuDepor.getIdUsuario() == null)
                    {
                        id_usuario_deportista = null;
                        System.out.println("El usuario selecionado no existe.");
                        while (!esEntero(id_usuario_deportista) || id_usuario_deportista.length() > 5 || Integer.parseInt(id_usuario_deportista) < 0)
                        {
                            System.out.print("Dato erróneo. Introduce de nuevo el identificador del entrenador: ");
                            id_usuario_deportista = entradaDatos.nextLine();
                        }
                    }
                } while (usuDepor.getIdUsuario() == null);

                fechaEntranamiento = sdf.parse(fecha);
                java.sql.Date fechaSql = new java.sql.Date(fechaEntranamiento.getTime());
                entrenamiento = new Entrenamiento(Integer.parseInt(idEntrenamiento), Integer.parseInt(plazas), nombre, fechaSql, usuEntre, usuDepor);
                cAD.modificarEntrenamiento(entrenamiento);

                System.out.println("Se ha modificado correctamente el entrenamiento.");
            } else
            {
                System.out.println("El entrenamiento no existe.");
            }
        } catch (ExcepcionProyecto ex)
        {
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL() + "\n";
            log.logCAD(error);

            System.err.println(ex.getMensajeErrorUsuario());
        } catch (ParseException ex)
        {
            String error = "Error ParseException(Date): " + ex.getMessage();
            log.logCAD(error);
        }

        System.out.println("Presiona cualquier tecla para volver al menu principal...");
        entradaDatos.nextLine();
    }

    public void menuLeer(Scanner entradaDatos)
    {
        System.out.println("\n\n\n\n\n\n\n");
        String idEntrenamiento;
        Entrenamiento entrenamiento;
        System.out.println("MENU BUSQUEDA DE INFORMACION DE UN ENTRENAMIENTO");
        System.out.println("Para poder buscar y obtener la informacion del entrenamiento sera necesario su identificador.\nIntroduce el identificador del entrenamiento: ");

        idEntrenamiento = entradaDatos.nextLine();
        while (!esEntero(idEntrenamiento) || idEntrenamiento.length() > 5 || Integer.parseInt(idEntrenamiento) < 0)
        {
            System.out.println("Dato erróneo. Introduce de nuevo el identificador del entrenamiento:");
            idEntrenamiento = entradaDatos.nextLine();
        }

        try
        {
            ProyectoCAD cAD = new ProyectoCAD();
            entrenamiento = cAD.leerEntrenamiento(Integer.parseInt(idEntrenamiento));

            if (entrenamiento.getIdEntrenamiento() != null)
            {
                System.out.printf("%16s%20s%20s%20s%20s%20s%20s%20s\n", "Id Entrenamiento", "Nombre", "Fecha", "Nº plazas",
                        "Id Entrenador", "Entrenador", "Id Deportista", "Deportista");

                System.out.printf("%16d%20s%20tD%20d%20d%20s%20d%20s\n", entrenamiento.getIdEntrenamiento(), entrenamiento.getNombre(), entrenamiento.getFecha(), entrenamiento.getPlazas(),
                        entrenamiento.getIdUsuarioEntrenador().getIdUsuario(), entrenamiento.getIdUsuarioEntrenador().getNombreUsuario(), entrenamiento.getIdUsuarioDeportista().getIdUsuario(),
                        entrenamiento.getIdUsuarioDeportista().getNombreUsuario());
            } else
            {
                System.out.println("El entrenamiento no existe.");
            }

        } catch (ExcepcionProyecto ex)
        {
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL() + "\n";
            log.logCAD(error);

            System.err.println(ex.getMensajeErrorUsuario());
        }
        System.out.println("Presiona cualquier tecla para volver al menu principal...");
        entradaDatos.nextLine();
    }

    public void menuLeerTodos()
    {
        System.out.println("\n\n\n\n\n\n\n");
        Scanner espera = new Scanner(System.in);
        System.out.println("MENU DE INFORMACION DE TODOS LOS ENTRENAMIENTOS");

        try
        {
            ProyectoCAD cAD = new ProyectoCAD();
            System.out.printf("%16s%20s%20s%20s%20s%20s%20s%20s\n", "Id Entrenamiento", "Nombre", "Fecha", "Nº plazas",
                    "Id Entrenador", "Entrenador", "Id Deportista", "Deportista");

            ArrayList<Entrenamiento> entrenamientos = cAD.leerEntrenamientos();
            Iterator<Entrenamiento> iteraEntre = entrenamientos.iterator();

            while (iteraEntre.hasNext())
            {
                Entrenamiento entrenamiento = iteraEntre.next();
                System.out.printf("%16d%20s%20tD%20d%20d%20s%20d%20s\n", entrenamiento.getIdEntrenamiento(), entrenamiento.getNombre(), entrenamiento.getFecha(), entrenamiento.getPlazas(),
                        entrenamiento.getIdUsuarioEntrenador().getIdUsuario(), entrenamiento.getIdUsuarioEntrenador().getNombreUsuario(), entrenamiento.getIdUsuarioDeportista().getIdUsuario(),
                        entrenamiento.getIdUsuarioDeportista().getNombreUsuario());
            }

        } catch (ExcepcionProyecto ex)
        {
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL() + "\n";
            log.logCAD(error);

            System.err.println(ex.getMensajeErrorUsuario());
        }

        System.out.println("Presiona cualquier tecla para volver al menu principal...");
        espera.nextLine();
    }
}
