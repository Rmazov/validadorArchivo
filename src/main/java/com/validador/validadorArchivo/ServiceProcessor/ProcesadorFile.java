package com.validador.validadorArchivo.ServiceProcessor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ProcesadorFile {
    public boolean procesar(String persona) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/api/v1/archivo/validar";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(persona, headers);
        Boolean respuesta = restTemplate.postForObject(url, requestEntity, Boolean.class);

        //1 enviar una peticion POST al micrservicio de validacion.
        // post request (persona body)
        //restTemplate

        // hacerele el return a ;la respuesta.

        return respuesta;
    }
}
