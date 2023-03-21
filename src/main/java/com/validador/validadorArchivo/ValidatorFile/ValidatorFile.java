package com.validador.validadorArchivo.ValidatorFile;

import com.validador.validadorArchivo.ControllerFile.RespuestaArchivo;
import com.validador.validadorArchivo.ServiceProcessor.ProcesadorFile;
import org.springframework.beans.factory.annotation.Autowired;

public interface ValidatorFile {


    public RespuestaArchivo validar(String ruta);
}
