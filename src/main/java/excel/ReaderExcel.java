package excel;

import Model.Cliente;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import api.MacVendorsConsumer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.Iterator;

public class ReaderExcel {
    private static final String path = "H:/Downloads/macsBD.xlsx";

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream file = new FileInputStream(ReaderExcel.path);

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            Iterator<Row> rowIterator = sheet.iterator();

            // pula cabeçalho
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Cliente cliente = new Cliente();

                Cell cell0 = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell cell1 = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell cell2 = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell cell3 = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell cell4 = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell cell5 = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                // ID
                String idStr = formatter.formatCellValue(cell0).trim();
                if (!idStr.isEmpty() && idStr.matches("\\d+")) {
                    cliente.setId(Long.parseLong(idStr));
                }

                // Nome
                cliente.setNome(formatter.formatCellValue(cell1));

                // ID_LOGIN
                String idLoginStr = formatter.formatCellValue(cell2).trim();
                if (!idLoginStr.isEmpty() && idLoginStr.matches("\\d+")) {
                    cliente.setId_login(Long.parseLong(idLoginStr));
                }

                // Booleanos
                cliente.setLogin_ativo(Boolean.parseBoolean(formatter.formatCellValue(cell3)));
                cliente.setLogin_online(Boolean.parseBoolean(formatter.formatCellValue(cell4)));

                // MAC
                String mac = formatter.formatCellValue(cell5).trim();

                if (mac.isEmpty() || mac.equalsIgnoreCase("null")) {
                    System.out.println("MAC vazio - pulando linha> "+ cliente.getId());
                    continue;
                }

                cliente.setMac(mac);

                System.out.println("MAC sendo validado: " + mac);
                if (MacVendorsConsumer.isValid(mac)) {
                    System.out.println("MAC válido, salvando...");
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
