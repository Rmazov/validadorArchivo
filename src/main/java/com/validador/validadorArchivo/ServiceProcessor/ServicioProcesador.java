package com.validador.validadorArchivo.ServiceProcessor;

import com.validador.validadorArchivo.ControllerFile.Archivo;
import com.validador.validadorArchivo.ControllerFile.RespuestaArchivo;
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
import org.springframework.web.client.RestTemplate;

public class ServicioProcesador {

    private int contadorLineaValida = 0;
    private int contadorLineaInvalida = 0;


    private static String ruta;

    private List<Archivo> archivo;



    public RespuestaArchivo leer(Archivo archivo) {

        ruta = archivo.getRuta();
        contadorLineaValida = 0;
        contadorLineaInvalida = 0;
        if (ruta.contains(".csv")) {

            try {
                BufferedReader br = new BufferedReader(new FileReader(archivo.getRuta()));
                String line;

                while ((line = br.readLine()) != null) {
                    List<String> campos = new ArrayList<>();

                    String[] datos = line.split(",");

//                    Persona persona = new Persona();
//                    persona.setIndex((datos[0]));
//                    persona.setUserId(datos[1]);
//                    persona.setFirstName(datos[2]);
//                    persona.setLastName(datos[3]);
//                    persona.setSex(datos[4]);
//                    persona.setEmail(datos[5]);
//                    persona.setPhone(datos[6]);
//                    persona.setDateOfBirth(datos[7]);
//                    persona.setJobTitle(datos[8]);

                    String email = datos[5];
                    String dateOfBirth = datos[7];
                    String jobTitle = datos[8];

                    //  personas.add(persona);


                    String lineaValidar = "csv"+","+email+","+dateOfBirth+","+jobTitle;
                  
                    boolean esValido = procesar(lineaValidar);
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
        } else if (ruta.contains(".xlsx")) {

            try {
                int indexInjury = 0, indexReport = 0;
                InputStream input = new FileInputStream(ruta);
                Workbook wb = new XSSFWorkbook(input);
                Sheet sheet = wb.getSheetAt(0);
                Iterator<Row> iterator = sheet.iterator();
                DataFormatter formatter = new DataFormatter();
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String contenidoCelda = formatter.formatCellValue(cell);
                    if (contenidoCelda.equals("Injury Location")) {

                        indexInjury = cell.getColumnIndex();

                    } else if (contenidoCelda.equals("Report Type")) {
                        indexReport = cell.getColumnIndex();

                    }
                }

                while (iterator.hasNext()) {
                    nextRow = iterator.next();
                    String lineaValidar = "xlsx," + formatter.formatCellValue(nextRow.getCell(indexInjury)) + "," + formatter.formatCellValue(nextRow.getCell(indexReport));


                     //boolean esValido = procesadorArchivo.procesar(lineaValidar);
                    boolean esValido = procesar(lineaValidar);
                    if (esValido) {

                        contadorLineaValida++;
                    } else {
                        contadorLineaInvalida++;
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }


            return new RespuestaArchivo(contadorLineaValida, contadorLineaInvalida);
        }

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




