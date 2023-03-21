package com.validador.validadorArchivo.ControllerFile;

import org.springframework.stereotype.Service;


public class RespuestaArchivo {
    private Integer lineasValidas;
    private Integer lineasInvalidas;

    public RespuestaArchivo(Integer lineasValidas, Integer lineasInvalidas) {
        this.lineasValidas = lineasValidas;
        this.lineasInvalidas = lineasInvalidas;
    }

    public void setLineasValidas(Integer lineasValidas) {
        this.lineasValidas = lineasValidas;
    }

    public void setLineasInvalidas(Integer lineasInvalidas) {
        this.lineasInvalidas = lineasInvalidas;
    }

    public Integer getLineasValidas() {
        return lineasValidas;
    }

    public Integer getLineasInvalidas() {
        return lineasInvalidas;
    }
}
