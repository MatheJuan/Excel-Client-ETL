package excel;

import Model.Cliente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import api.MacVendorsConsumer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.Iterator;

public class ReaderExcel {
    private static final String path= "C:/test";
     static void main() throws FileNotFoundException {
        FileInputStream file = new FileInputStream(ReaderExcel.path);

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                //
                Cliente cliente = new Cliente();
                Cell cell0 = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell cell1 = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell cell2 = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                if(cell0.getCellType() == CellType.NUMERIC){
                    cliente.setId((long) cell0.getNumericCellValue());

                }else if(cell0.getCellType() == CellType.STRING && !cell0.getStringCellValue().isBlank()){
                    cliente.setId(Long.parseLong(cell0.getStringCellValue()));
                }
                cliente.setNome(cell1.getStringCellValue());
                cliente.setMac(cell2.getStringCellValue());

                if(MacVendorsConsumer.isValid(cliente.getMac())){ // API do MACVENDORS. concluido
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
