/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author Sistemas
 */

public class archivo {

    String info = "";

    public String leerTxtLine(String dir) {                                     //lee lo que haya en un archivo txt, recibe como parametros la direccion tipo String y devuelve el String del contenido en una sola linea
        try {
            int lim = 0;
            BufferedReader bf = new BufferedReader(new FileReader(dir));
            String temp = "";
            String bfRead;
            while ((bfRead = bf.readLine()) != null && lim < 20) {
                temp += bfRead;
                lim++;
            }
            info = temp;
        } catch (Exception e) {
            System.err.println("SOY READ LINE: No se encontro el archivo en " + dir);
        }
        return info;
    }

    public String leerTxtLine(String dir, int lim) {                 //lee lo que haya en un archivo txt, recibe como parametros la direccion tipo String y devuelve el String del contenido en una sola linea
        try {
            BufferedReader bf = new BufferedReader(new FileReader(dir));
            String temp = "";
            String bfRead;
            while ((bfRead = bf.readLine()) != null && lim > 0) {
                temp += bfRead;
                lim--;
            }
            info = temp;
        } catch (Exception e) {
            System.err.println("SOY READ LINE con lim: No se encontro el archivo en " + dir);
        }
        return info;
    }

    public String leerTxt(String dir) {                                         //lee lo que haya en un archivo txt, recibe como parametros la direccion tipo String y devuelve el String del contenido
        try {
            BufferedReader bf = new BufferedReader(new FileReader(dir));
            String temp = "";
            String bfRead;
            int lim = 200;
            while (((bfRead = bf.readLine()) != null) && (lim > 0)) {
                temp += bfRead;
                temp += "\n";
                lim--;
            }
            info = temp;
        } catch (Exception e) {
            System.err.println("SOY READ: No se encontro el archivo en " + dir);
        }
        return info;
    }

    public void escribirTxtLine(String dir, String texto) throws IOException {      //escribe un texto en una archivo existente o lo crea, recibe como parametro la direccion del texto y el texto ambos tipo String
        BufferedWriter bw;
        try {
            File archivo = new File(dir);
            if (archivo.exists()) {
                info = leerTxt(dir);
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(texto + "\n" + info);
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(texto + "\n");
            }
            bw.close();
        } catch (Exception e) {
            System.err.println("SOY WRITE LINE: hay un error ");
        }
    }

    public void escribirTxt(String dir, String texto) throws IOException {      //escribe un texto en una archivo existente o lo crea, recibe como parametro la direccion del texto y el texto ambos tipo String
        BufferedWriter bw;
        try {
            File archivo = new File(dir);
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(texto);
            bw.close();
        } catch (Exception e) {
            System.err.println("SOY WRITE hay un error ");
        }
    }

    public void escribirTxt(String dir, int texto) throws IOException {      //escribe un texto en una archivo existente o lo crea, recibe como parametro la direccion del texto y el texto ambos tipo String
        BufferedWriter bw;
        try {
            File archivo = new File(dir);
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(Integer.toString(texto));
            bw.close();
        } catch (Exception e) {
            System.err.println("SOY WRITE hay un error ");
        }
    }

    public void save(String dir) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String date = sdf.format(cal.getTime());
        BufferedWriter bw;
        try {
            File archivo = new File("resource/btrData_" + date + ".txt");
            info = leerTxt(dir);
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(info + "\n");
            bw.close();
        } catch (Exception e) {
            System.err.println("SOY SAVE: No se encontro el archivo " + dir);
        }
    }

    public void save(String dir, String save) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String date = sdf.format(cal.getTime());
        BufferedWriter bw;
        try {
            File archivo = new File(dir + "_" + date + ".txt");
            //info = leerTxt(dir);
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(save);
            bw.close();
        } catch (Exception e) {
            System.err.println("SOY SAVE: No se encontro el archivo " + dir);
        }
    }

//    public void writeExcelData(String dir, String nameSheet, String[] data) {
//        //create blank workbook
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet spreadsheet = workbook.createSheet(nameSheet);
//        try {
//            HSSFRow row = spreadsheet.createRow(0);
//            Cell cell = row.createCell(0);
//            String result = data[0] + " " + data[1] + " " + data[2];
//            cell.setCellValue(result);
//            FileOutputStream out = new FileOutputStream(new File(dir));
//            workbook.write(out);
//            out.close();
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//        System.out.println("createworkbook.xls created!");
//    }

    public void writeExcelData(String dir, String nameSheet, String[][] data) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(nameSheet);

        //poner negrita a la cabecera
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        //generar los datos para el documento
        for (int i = 0; i <= data.length; i++) {
            XSSFRow row = spreadsheet.createRow(i);//se crea las filas
            for (int j = 0; j < data[0].length; j++) {
                if (i == 0) {//para la cabecera
                    XSSFCell cell = row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                    cell.setCellStyle(style); // se añade el style crea anteriormente 
                    cell.setCellValue(data[0][j]);//se añade el contenido				
                } else {//para el contenido
                    XSSFCell cell = row.createCell(j);//se crea las celdas para la contenido, junto con la posición
                    cell.setCellValue(data[i - 1][j]); //se añade el contenido
                }
            }
        }

        File file;
        file = new File(dir);
        try (FileOutputStream fileOuS = new FileOutputStream(file)) {
            if (file.exists()) {// si el archivo existe se elimina
                file.delete();
                System.out.println("Archivo eliminado");
            }
            workbook.write(fileOuS);
            fileOuS.flush();
            fileOuS.close();
            System.out.println("Archivo Creado");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
