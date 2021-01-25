/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoignacio;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Alejandro Campos Maestre
 */
public class Logs
{
    org.apache.log4j.Logger loggerERROR = LogManager.getLogger("ERROR");

    public Logs()
    {
        PropertyConfigurator.configure("logs\\log4j.properties");
    }

    public void logCAD(String error)
    {
        loggerERROR.error(error);
    }
    
    public void logTrace()
    {
        
    }
}
