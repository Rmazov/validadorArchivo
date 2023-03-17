package com.validador.validadorArchivo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1")

public class ControlladorArchivo {
    private ServicioProcesador servicioProcesador;

    public ControlladorArchivo(){
        this.servicioProcesador = new ServicioProcesador();
        //@services
    }

    @GetMapping("/archivo")
    public List<Archivo> obtenerArchivo(){
        return this.servicioProcesador.obtenerArchivo();
    }
//    @PostMapping("/archivo/validar")
//   public List<Persona>  procesar(@RequestBody Archivo archivo){
//       return this.servicioProcesador.procesar(archivo);
//   }

   @PostMapping("/archivo/validar")
  public RespuestaArchivo procesar(@RequestBody Archivo archivo){
       return this.servicioProcesador.leer(archivo);
  }
}
