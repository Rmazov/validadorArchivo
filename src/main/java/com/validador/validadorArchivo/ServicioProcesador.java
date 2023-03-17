package com.validador.validadorArchivo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ServicioProcesador {

    private int contadorLineaValida = 0;
    private int contadorLineaInvalida = 0;
    private LectorArchivo lectorArchivo;
    private ProcesadorArchivo procesadorArchivo;

    private List<Archivo> archivo;

    public ServicioProcesador() {
        this.archivo = new ArrayList<>();
       this.lectorArchivo = new LectorArchivo();
       this.procesadorArchivo = new ProcesadorArchivo();
    }

    public List<Archivo> obtenerArchivo(){
        return this.archivo;
    }




    public List<Persona>  procesar(Archivo archivo){
        List<Persona> listaPersona =  lectorArchivo.leer(archivo.getRuta());
      String ruta = archivo.getRuta();

        return listaPersona;
   }

    public RespuestaArchivo leer(Archivo archivo) {
        List<Persona> listaPersona =  lectorArchivo.leer(archivo.getRuta());

//
//        for (Persona persona: listaPersona) {
//            boolean esValido = procesadorArchivo.procesar(persona); // comunicacion con el otro servicio.
//            if(esValido){
//                contadorLineaInvalida++;
//            }
//            else{
//                contadorLineaValida++;
//            }
//        }
        contadorLineaValida=10;
        return new RespuestaArchivo(contadorLineaValida, contadorLineaInvalida);
    }
}
