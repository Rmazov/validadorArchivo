package com.validador.validadorArchivo.ServiceProcessor;

import com.validador.validadorArchivo.ControllerFile.Archivo;
import com.validador.validadorArchivo.ControllerFile.RespuestaArchivo;
import com.validador.validadorArchivo.ValidatorFile.ValidatorCsv;
import com.validador.validadorArchivo.ValidatorFile.ValidatorFile;
import com.validador.validadorArchivo.ValidatorFile.ValidatorXlsx;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ServicioProcesador {

    private int contadorLineaValida = 0;
    private int contadorLineaInvalida = 0;


    private static String ruta;

    private List<Archivo> archivo;



    public RespuestaArchivo leer(Archivo archivo) {
 ValidatorFile validatorFile;
        ruta = archivo.getRuta();
        contadorLineaValida = 0;
        contadorLineaInvalida = 0;
        if (ruta.contains(".csv")) {
            validatorFile = new ValidatorCsv();
            return validatorFile.validar(ruta);

        } else if (ruta.contains(".xlsx")) {
            validatorFile = new ValidatorXlsx();
            return validatorFile.validar(ruta);
        }


            return new RespuestaArchivo(contadorLineaValida, contadorLineaInvalida);
        }


    }




