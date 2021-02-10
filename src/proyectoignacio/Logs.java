/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoignacio;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

/**
 * Clase para la gestion de los logs del proyecto.
 * @author Alejandro Campos Maestre
 */
public class Logs
{
    org.apache.log4j.Logger loggerERROR = LogManager.getLogger("ERROR");

    /**
     * Constructor por defecto, con la carga del archivo de propiedades de los logs.
     */
    public Logs()
    {
        PropertyConfigurator.configure("logs\\log4j.properties");
    }

    /**
     * Creacion de un log de tipo ERROR.
     * @param error Error a imprimir en el logs
     */
    public void logCAD(String error)
    {
        loggerERROR = LogManager.getLogger("ERROR");
        loggerERROR.error(error);
    }
    
    /**
     * Creacion de un log de tipo TRACE.
     * @param trace Trazada a imprimir en el logs
     */
    public void logTrace(String trace)
    {
        loggerERROR = LogManager.getLogger("TRACE");
        loggerERROR.trace(trace + "\n");
    }
}
