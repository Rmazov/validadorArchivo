package com.validador.validadorArchivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
public class LectorArchivo {


    public List<Persona> leer(String ruta) {
        List<Persona> personas = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String line;
            while((line = br.readLine()) != null){

               String[] datos  =  line.split(",");

                Persona persona = new Persona();
                persona.setIndex((datos[0]));
                persona.setUserId(datos[1]);
                persona.setFirstName(datos[2]);
                persona.setLastName(datos[3]);
                persona.setSex(datos[4]);
                persona.setEmail(datos[5]);
                persona.setPhone(datos[6]);
                persona.setDateOfBirth(datos[7]);
                persona.setJobTitle(datos[8]);

                personas.add(persona);

            }
        } catch (Exception e){
            System.out.println(e);
        }
        return personas;
    }
}
