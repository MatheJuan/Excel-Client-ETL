package excel;

import Model.Cliente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import api.MacVendorsConsumer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.Iterator;

public class ReaderExcel {
    private static final String path= "C:/test";
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream file = new FileInputStream(new File(ReaderExcel.path));

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator(); //Ele ignora células vazias============
                Cliente cliente = new Cliente();

                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()){
                        case 0:
                            cliente.setId(Long.parseLong(cell.getStringCellValue())); break;
                        case 1:
                            cliente.setNome(cell.getStringCellValue()); break;
                        case 2:
                            cliente.setMac(cell.getStringCellValue()); break;
                    }
                }
                if(MacVendorsConsumer.MacValidator(cliente.getMac())){ // API do MACVENDORS . fazer depois
                    WriterExcel.addExcel(cliente);
                }
            }
            file.close();
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
