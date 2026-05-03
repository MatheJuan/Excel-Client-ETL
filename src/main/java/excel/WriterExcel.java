package excel;

import Model.Cliente;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriterExcel {
        //criar nova linha e preenche as cells
      public static void addExcel(Cliente cliente){
        try {
            FileInputStream file = new FileInputStream("C:/teste");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet =  workbook.getSheetAt(0);
             
            int last = sheet.getLastRowNum();
            Row newRow = sheet.createRow(last +1);

            newRow.createCell(0).setCellValue(cliente.getId());
            newRow.createCell(1).setCellValue(cliente.getNome());
            newRow.createCell(2).setCellValue(cliente.getMac());

            file.close();
            FileOutputStream saida = new FileOutputStream("C:/teste");
            workbook.write( saida );
            saida.close();
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    // criar planilha com 1o cliente.
}
