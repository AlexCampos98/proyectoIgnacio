/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoignacio;

import java.util.ArrayList;
import java.util.Iterator;
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
        System.out.println("\n\n\n\n\n\n\n");
        String correo, nombre, apellido1, apellido2, telefono, telefonoEmergencia, nombreUsuario;

        System.out.println("CREACION DE UN USUARIO");
        System.out.print("Vamos a introducir los siguientes datos para la creacion del usuario:\nIntroduce "
                + "el correo: ");
        correo = entradaDatos.nextLine();
        while (!esCorreo(correo) || correo.length() > 320)
        {
            if (correo.length() > 320)
            {
                System.out.println("Has pasado el limite de 320 letras. Introduce de nuevo el correo (____@____.___): ");
            } else
            {
                System.out.print("Dato erróneo. Introduce de nuevo el correo (____@____.___): ");
            }
            correo = entradaDatos.nextLine();
        }

        System.out.print("Introduce el nombre: ");
        nombre = entradaDatos.nextLine();
        while (nombre.equals("") || nombre.length() > 30)
        {
            if (nombre.length() > 30)
            {
                System.out.println("Has pasado el limite de 30 letras. Introduce de nuevo el nombre: ");
            } else
            {
                System.out.print("No has introducido ningun nombre. Introduce de nuevo el nombre: ");
            }
            nombre = entradaDatos.nextLine();
        }

        System.out.print("Introduce el primer apellido: ");
        apellido1 = entradaDatos.nextLine();
        while (apellido1.equals("") || apellido1.length() > 50)
        {
            if (apellido1.length() > 50)
            {
                System.out.println("Has pasado el limite de 50 letras. Introduce de nuevo el primer apellido: ");
            } else
            {
                System.out.print("No has introducido ningun apellido. Introduce de nuevo el primer apellido: ");
            }
            apellido1 = entradaDatos.nextLine();
        }

        System.out.print("Introduce el segundo apellido: ");
        apellido2 = entradaDatos.nextLine();
        while (apellido2.equals("") || apellido2.length() > 50)
        {
            if (apellido2.length() > 50)
            {
                System.out.println("Has pasado el limite de 50 letras. Introduce de nuevo el segundo apellido: ");
            } else
            {
                System.out.print("No has introducido ningun apellido. Introduce de nuevo el segundo apellido: ");
            }
            apellido2 = entradaDatos.nextLine();
        }

        System.out.print("Introduce el numero de telefono: ");
        telefono = entradaDatos.nextLine();

        while (!esEntero(telefono) || telefono.length() != 9)
        {
            System.out.print("Dato erróneo. Introduce de nuevo el numero de telefono (9 numeros): ");
            telefono = entradaDatos.nextLine();
        }

        System.out.print("Introduce el numero de telefono de emergencia: ");
        telefonoEmergencia = entradaDatos.nextLine();

        while (!esEntero(telefonoEmergencia) || telefonoEmergencia.length() != 9)
        {
            System.out.print("Dato erróneo. Introduce de nuevo el numero de telefono de emergencia (9 numeros): ");
            telefonoEmergencia = entradaDatos.nextLine();
        }

        System.out.print("Introduce el nombre que se mostrará en la aplicacion: ");
        nombreUsuario = entradaDatos.nextLine();
        while (nombreUsuario.equals("") || nombreUsuario.length() > 20)
        {
            if (nombreUsuario.length() > 20)
            {
                System.out.println("Has pasado el limite de 20 letras. Introduce de nuevo el nombre que se mostrará en la aplicacion: ");
            } else
            {
                System.out.print("No has introducido ningun nombre. Introduce de nuevo el nombre que se mostrará en la aplicacion: ");
            }
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
            String error = "Codigo de error: " + ex.getCodigoError() + "\nMensaje para el administrador: " + ex.getMensajeErrorAdministrador() + "Sentencia SQL utilizada: " + ex.getSentenciaSQL() + "\n";
            log.logCAD(error);

            System.err.println(ex.getMensajeErrorUsuario());
        }

        System.out.println("Presiona cualquier tecla para volver al menu principal...");
        entradaDatos.nextLine();
    }

    public void menuEliminacion(Scanner entradaDatos)
    {
        System.out.println("\n\n\n\n\n\n\n");
        String idUsuario;

        System.out.println("ELIMINACION DE UN USUARIO");
        System.out.print("Para poder eliminar un usuario, introduce su identificador: ");
        idUsuario = entradaDatos.nextLine();

        while (!esEntero(idUsuario) || idUsuario.length() > 5 || Integer.parseInt(idUsuario) < 0)
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
        String idUsuario, correo, nombre, apellido1, apellido2, telefono, telefonoEmergencia, nombreUsuario;
        Usuario usu;

        System.out.println("MODIFICACION DE UN USUARIO");
        System.out.print("Para poder modificacar un usuario, es necesario la siguiente informacion.\n\nIMPORTANTE se mostrara la informacion del usuario"
                + " entre parentesis, si desea no cambiarla presione la tecla ENTER.\nIntroduce el identificador del usuario a modificar: ");

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
                System.out.print("Introduce el correo (" + usu.getCorreo() + "): ");

                correo = entradaDatos.nextLine();
                if (correo.equals(""))
                {
                    correo = usu.getCorreo();
                } else
                {
                    while (!esCorreo(correo) || correo.length() > 320)
                    {
                        if (correo.length() > 320)
                        {
                            System.out.println("Has pasado el limite de 320 letras. Introduce de nuevo el correo (____@____.___): ");
                        } else
                        {
                            System.out.print("Dato erróneo. Introduce de nuevo el correo (____@____.___): ");
                        }
                        correo = entradaDatos.nextLine();
                    }
                }

                System.out.print("Introduce el nombre (" + usu.getNombre() + "): ");
                nombre = entradaDatos.nextLine();

                if (nombre.equals(""))
                {
                    nombre = usu.getNombre();
                } else
                {
                    while (nombre.equals("") || nombre.length() > 30)
                    {
                        if (nombre.length() > 30)
                        {
                            System.out.println("Has pasado el limite de 30 letras. Introduce de nuevo el nombre: ");
                        } else
                        {
                            System.out.print("No has introducido ningun nombre. Introduce de nuevo el nombre: ");
                        }
                        nombre = entradaDatos.nextLine();
                    }
                }

                System.out.print("Introduce el primer apellido (" + usu.getApellido1() + "): ");
                apellido1 = entradaDatos.nextLine();

                if (apellido1.equals(""))
                {
                    apellido1 = usu.getApellido1();
                } else
                {
                    while (apellido1.equals("") || apellido1.length() > 50)
                    {
                        if (apellido1.length() > 50)
                        {
                            System.out.println("Has pasado el limite de 50 letras. Introduce de nuevo el primer apellido: ");
                        } else
                        {
                            System.out.print("No has introducido ningun apellido. Introduce de nuevo el primer apellido: ");
                        }
                        apellido1 = entradaDatos.nextLine();
                    }
                }

                System.out.print("Introduce el segundo apellido (" + usu.getApellido2() + "): ");
                apellido2 = entradaDatos.nextLine();

                if (apellido2.equals(""))
                {
                    apellido2 = usu.getApellido2();
                } else
                {
                    while (apellido2.equals("") || apellido2.length() > 50)
                    {
                        if (apellido2.length() > 50)
                        {
                            System.out.println("Has pasado el limite de 50 letras. Introduce de nuevo el segundo apellido: ");
                        } else
                        {
                            System.out.print("No has introducido ningun apellido. Introduce de nuevo el segundo apellido: ");
                        }
                        apellido2 = entradaDatos.nextLine();
                    }
                }

                System.out.print("Introduce el numero de telefono (" + usu.getTelefono() + "): ");
                telefono = entradaDatos.nextLine();

                if (telefono.equals(""))
                {
                    telefono = usu.getTelefono();
                } else
                {
                    while (!esEntero(telefono) || telefono.length() != 9)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el numero de telefono: ");
                        telefono = entradaDatos.nextLine();
                    }
                }

                System.out.print("Introduce el numero de telefono de emergencia (" + usu.getTelefonoEmergencia() + "): ");
                telefonoEmergencia = entradaDatos.nextLine();

                if (telefonoEmergencia.equals(""))
                {
                    telefonoEmergencia = usu.getTelefonoEmergencia();
                } else
                {
                    while (!esEntero(telefonoEmergencia) || telefonoEmergencia.length() != 9)
                    {
                        System.out.print("Dato erróneo. Introduce de nuevo el numero de telefono de emergencia: ");
                        telefonoEmergencia = entradaDatos.nextLine();
                    }
                }

                System.out.print("Introduce el nombre que se mostrará en la aplicacion (" + usu.getNombreUsuario() + "): ");
                nombreUsuario = entradaDatos.nextLine();

                if (nombreUsuario.equals(""))
                {
                    nombreUsuario = usu.getNombreUsuario();
                } else
                {
                    while (nombreUsuario.equals("") || nombreUsuario.length() > 20)
                    {
                        if (nombreUsuario.length() > 20)
                        {
                            System.out.println("Has pasado el limite de 20 letras. Introduce de nuevo el nombre que se mostrará en la aplicacion: ");
                        } else
                        {
                            System.out.print("No has introducido ningun nombre. Introduce de nuevo el nombre que se mostrará en la aplicacion: ");
                        }
                        nombreUsuario = entradaDatos.nextLine();
                    }
                }

                usu = new Usuario(Integer.parseInt(idUsuario), correo, nombre, apellido1, apellido2, telefono, telefonoEmergencia, nombreUsuario);

                cAD = new ProyectoCAD();
                cAD.modificarUsuario(usu);
                System.out.println("Se ha modificado el usuario con exito.");

            } else
            {
                System.out.println("El usuario no existe.");
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

    public void menuLeer(Scanner entradaDatos)
    {
        System.out.println("\n\n\n\n\n\n\n");
        String idUsuario;
        Usuario usu;
        System.out.println("MENU BUSQUEDA DE INFORMACION DE UN USUARIO");
        System.out.println("Para poder buscar y obtener la informacion del usuario sera necesario su identificador.\nIntroduce el identificador del usuario: ");

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
                System.out.printf("%10s%40s%25s%25s%25s%15s%25s%25s\n", "Id_usuario", "Correo", "Nombre", "Primer apellido",
                        "Segundo apellido", "Telefono", "Telefono Emergencia", "NickName");

                System.out.printf("%10s%40s%25s%25s%25s%15s%25s%25s\n", usu.getIdUsuario(), usu.getCorreo(), usu.getNombre(), usu.getApellido1(),
                        usu.getApellido2(), usu.getTelefono(), usu.getTelefonoEmergencia(), usu.getNombreUsuario());

            } else
            {
                System.out.println("El usuario no existe.");
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
        System.out.println("MENU DE INFORMACION DE TODOS LOS USUARIOS");

        try
        {
            ProyectoCAD cAD = new ProyectoCAD();
            ArrayList<Usuario> usuarios = cAD.leerUsuarios();
            Iterator<Usuario> iteraUsu = usuarios.iterator();

            System.out.printf("%10s%40s%25s%25s%25s%15s%25s%25s\n", "Id_usuario", "Correo", "Nombre", "Primer apellido",
                    "Segundo apellido", "Telefono", "Telefono Emergencia", "NickName");

            while (iteraUsu.hasNext())
            {
                Usuario usu = iteraUsu.next();
                System.out.printf("%10s%40s%25s%25s%25s%15s%25s%25s\n", usu.getIdUsuario(), usu.getCorreo(), usu.getNombre(), usu.getApellido1(),
                        usu.getApellido2(), usu.getTelefono(), usu.getTelefonoEmergencia(), usu.getNombreUsuario());
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
