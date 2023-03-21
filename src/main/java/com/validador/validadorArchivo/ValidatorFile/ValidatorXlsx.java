package com.validador.validadorArchivo.ValidatorFile;

import com.validador.validadorArchivo.ControllerFile.RespuestaArchivo;
import com.validador.validadorArchivo.ServiceProcessor.ProcesadorFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ValidatorXlsx implements ValidatorFile{

    private ProcesadorFile procesadorFile;

    private int contadorLineaValida = 0;
    private int contadorLineaInvalida = 0;

    @Autowired
    public void ValidatorXlsx(ProcesadorFile procesadorFile){
        this.procesadorFile = procesadorFile;
    }



    @Override
    public RespuestaArchivo validar(String ruta) {
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
                boolean esValido = procesadorFile.procesar(lineaValidar);
                if (esValido) {

                    contadorLineaValida++;
                } else {
                    contadorLineaInvalida++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new RespuestaArchivo(contadorLineaValida, contadorLineaInvalida);
    }
}
