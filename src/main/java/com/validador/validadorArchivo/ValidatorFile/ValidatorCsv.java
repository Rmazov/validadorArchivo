package com.validador.validadorArchivo.ValidatorFile;

import com.validador.validadorArchivo.ControllerFile.ControlladorArchivo;
import com.validador.validadorArchivo.ControllerFile.RespuestaArchivo;
import com.validador.validadorArchivo.ServiceProcessor.ProcesadorFile;
import com.validador.validadorArchivo.ServiceProcessor.ServicioProcesador;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ValidatorCsv implements ValidatorFile{


    private int contadorLineaValida = 0;
    private int contadorLineaInvalida = 0;
    ProcesadorFile procesadorFile;

    @Autowired
    public void ValidatorCsv(ProcesadorFile procesadorFile){
        this.procesadorFile = procesadorFile;
    }

    @Override
    public RespuestaArchivo validar(String ruta) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String line;

            while ((line = br.readLine()) != null) {


                String[] datos = line.split(",");



                String email = datos[5];
                String dateOfBirth = datos[7];
                String jobTitle = datos[8];



                String lineaValidar = "csv"+","+email+","+dateOfBirth+","+jobTitle;


                boolean esValido = procesadorFile.procesar(lineaValidar);
                if (esValido) {

                    contadorLineaValida++;
                } else {
                    contadorLineaInvalida++;
                }
            }


        } catch (Exception e) {
            System.out.println(e);
        }
        //restar el encabezado

        contadorLineaInvalida--;
        return new RespuestaArchivo(contadorLineaValida, contadorLineaInvalida);
    }
}
