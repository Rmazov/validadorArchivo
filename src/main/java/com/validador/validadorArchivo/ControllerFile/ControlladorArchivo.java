package com.validador.validadorArchivo.ControllerFile;
import com.validador.validadorArchivo.ServiceProcessor.ServicioProcesador;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")

public class ControlladorArchivo {
    private ServicioProcesador servicioProcesador;

    public ControlladorArchivo(){
        this.servicioProcesador = new ServicioProcesador();
        //@services
    }

//    @Autowired
//    public void ControlladorArchivo(ServicioProcesador servicioProcesador){
//        this.servicioProcesador = servicioProcesador;
//    }




  @PostMapping("/archivo/validar")
  public RespuestaArchivo procesar(@RequestBody Archivo archivo){
       return this.servicioProcesador.leer(archivo);
 }
}
