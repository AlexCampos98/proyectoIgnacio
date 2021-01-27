/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.Date;

/**
 *
 * @author Alejandro Campos Maestre
 */
public class NewClass
{

    public static void main(String[] args)
    {
        System.out.printf("%10s%40s%25s%25s%25s%15s%25s%25s\n", "Id_usuario", "Correo", "Nombre", "Primer apellido", 
                "Segundo apellido", "Telefono", "Telefono Emergencia", "NickName");
        
        System.out.printf("%10s%40s%25s%25s%25s%15s%25s%25s\n", "1", "alex_-campos@hotmail.com", "Alejandro", "Campos", 
                "Maestre", "123456789", "987654321", "Alex69");
        
        System.out.printf("%16s%20s%20s%20s%20s%20s%20s%20s\n", "Id Entrenamiento", "Nombre", "Fecha", "Nº plazas", 
                "Id Entrenador", "Entrenador", "Id Deportista", "Deportista");
        
        Date fecha = new Date(2021, 01, 27);
        
        System.out.printf("%16d%20s%20tD%20d%20d%20s%20d%20s\n", 1, "Crossfit", fecha, 5, 
                1, "Nordi789", 2, "Alex69");
    }
}
