package excel;

import Model.Cliente;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriterExcel {
        //cria nova linha e preenche as cells

        public static void addExcel(Cliente cliente) {
            String caminho = "C:/teste/clientesRB.xlsx";
            File arquivo = new File(caminho);

            //======
            System.out.println("Salvando em: " + arquivo.getAbsolutePath());
            System.out.println("Arquivo já existe? " + arquivo.exists());
            //======
            XSSFWorkbook workbook;
            XSSFSheet sheet;

            try {//confirma que a pasta já existe
                if(arquivo.getParentFile() != null && arquivo.getParentFile().exists()){
                    arquivo.getParentFile().mkdir();
                    System.out.println(arquivo.getAbsolutePath());
                }
                if (arquivo.exists()) {
                    FileInputStream file = new FileInputStream(arquivo);
                    workbook = new XSSFWorkbook(file);
                    sheet = workbook.getSheetAt(0);
                    file.close();
                } else {
                    workbook = new XSSFWorkbook();
                    sheet = workbook.createSheet("Clientes");
                    Row header = sheet.createRow(0);
                    header.createCell(0).setCellValue("ID");
                    header.createCell(1).setCellValue("Nome");
                    header.createCell(2).setCellValue("Id_Login");
                    header.createCell(3).setCellValue("Login_Ativo");
                    header.createCell(4).setCellValue("Login_Online");
                    header.createCell(5).setCellValue("MAC");
                }
                int last = sheet.getLastRowNum();
                Row newRow = sheet.createRow(last + 1);
                newRow.createCell(0).setCellValue(cliente.getId());
                newRow.createCell(1).setCellValue(cliente.getNome());
                newRow.createCell(2).setCellValue(cliente.getId_login());
                newRow.createCell(3).setCellValue(cliente.isLogin_ativo());
                newRow.createCell(4).setCellValue(cliente.isLogin_online());
                newRow.createCell(5).setCellValue(cliente.getMac());

                FileOutputStream saida = new FileOutputStream(arquivo);
                workbook.write(saida);
                saida.close();
                workbook.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}
