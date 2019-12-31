/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xwpf.usermodel.*;

/**
 *
 * @author Sistemas
 */
public class archivo {

    String info = "";
    MonitorActivity ma;
    
    public void setMa(MonitorActivity ma){
        this.ma = ma;
    }

    public String leerTxtLine(String dir) {                                     //lee lo que haya en un archivo txt, recibe como parametros la direccion tipo String y devuelve el String del contenido en una sola linea
        try {
            BufferedReader bf = new BufferedReader(new FileReader(dir));
            String temp = "";
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                temp += bfRead;
            }
            info = temp;
        } catch (Exception e) {
            System.err.println("SOY READ LINE: No se encontro el archivo en " + dir + "\n" + e);
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
            System.err.println("SOY READ LINE con lim: No se encontro el archivo en " + dir + "\n" + e);
        }
        return info;
    }

    public String leerTxt(String dir) {                                         //lee lo que haya en un archivo txt, recibe como parametros la direccion tipo String y devuelve el String del contenido
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(dir), "utf-8"));
            String temp = "";
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                temp += bfRead;
                temp += "\n";
            }
            info = temp;
        } catch (Exception e) {
            System.err.println("SOY READ: No se encontro el archivo en " + dir + "\n" + e);
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
            System.err.println("SOY WRITE LINE: hay este error: " + e);
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
            System.err.println("SOY WRITE hay este error: " + e);
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

    public void writeExcelData(String dir, String nameSheet, String[][] data) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet worksheet = workbook.createSheet(nameSheet);

        //poner negrita a la cabecera
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        //generar los datos para el documento
        for (int i = 0; i < data.length; i++) {
            XSSFRow row = worksheet.createRow(i);//se crea las filas
            for (int j = 0; j < data[0].length; j++) {
                if (i == 0) {//para la cabecera
                    XSSFCell cell = row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
                    cell.setCellStyle(style); // se añade el style crea anteriormente 
                    cell.setCellValue(data[i][j]);//se añade el contenido
                } else {//para el contenido
                    XSSFCell cell = row.createCell(j);//se crea las celdas para la contenido, junto con la posición
                    cell.setCellValue(data[i][j]); //se añade el contenido
                }
                if (j == 0) {
                    System.out.println("Excel :EM#" + data[i][0] + " " + data[i][1] + " " + data[i][3]);
                    ma.messenger.setText("Excel :EM#" + data[i][0] + " " + data[i][1] + " " + data[i][3]);
                    ma.repaint();
                }
                if ((i + 1) == data.length) {
                    worksheet.autoSizeColumn(j);
                }
            }
        }

        File file;
        file = new File(dir);
        try (FileOutputStream fileOuS = new FileOutputStream(file)) {
            if (file.exists()) {// si el archivo existe se elimina
                file.delete();
            }
            workbook.write(fileOuS);
            fileOuS.flush();
            fileOuS.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void replaceWordData(String dir, String name, String[][] data) {
        String mdoc = System.getProperty("user.home");
//        System.out.println("replaceWordData");
        XWPFDocument doc;
        try {
            try {
                doc = new XWPFDocument(OPCPackage.open(dir));
                for (int i = 0; i < data.length; i++) {
//                    System.out.println("data: " + data[i][0] + "\t" + data[i][1]);
                    for (XWPFParagraph p : doc.getParagraphs()) {
                        List<XWPFRun> runs = p.getRuns();
                        if (runs != null) {
                            for (XWPFRun r : runs) {
                                String text = r.getText(0);
                                if (text != null && text.contains(data[i][0])) {
                                    text = text.replace(data[i][0], data[i][1]);
                                    r.setText(text, 0);
//                                    System.out.println("change: " + text);
                                }
                            }
                        }
                    }
                    for (XWPFTable tbl : doc.getTables()) {
                        for (XWPFTableRow row : tbl.getRows()) {
                            for (XWPFTableCell cell : row.getTableCells()) {
                                for (XWPFParagraph p : cell.getParagraphs()) {
                                    for (XWPFRun r : p.getRuns()) {
                                        String text = r.getText(0);
                                        if (text.contains(data[i][0])) {
                                            text = text.replace(data[i][0], data[i][1]);
                                            r.setText(text, 0);
//                                            System.out.println("change: " + text);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
//                    doc.write(new FileOutputStream(name));
                File file;
                file = new File(name);
                try (FileOutputStream fileOuS = new FileOutputStream(file)) {
                    if (file.exists()) {// si el archivo existe se elimina
                        file.delete();
                        System.out.println("Archivo eliminado");
                    }
                    doc.write(fileOuS);
                    fileOuS.flush();
                    fileOuS.close();
                    System.out.println("Archivo Creado");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("done");
            } catch (InvalidFormatException ex) {
                Logger.getLogger(archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(archivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
