package newpackage;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
    public static void ExcelWriter(String Path) {
        String filePath = Path+"\\";
        List<String[]> data = new ArrayList<>();
                data.add((String[]) new String[]{"姓名","学号","姓名","学号","查重率"});
             for (int i = 0; i < GoodText.NameM.size(); i++) {
             data.add((String[]) new String[]{(String)GoodText.NameM.get(i),(String)GoodText.XueHaoM.get(i),(String)GoodText.NameN.get(i),(String)GoodText.XueHaoN.get(i),(String)GoodText.ChaChong.get(i)});
             }         
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");

            int rowNum = 0;
            for (String[] rowData : data) {
                Row row = sheet.createRow(rowNum++);
                int cellNum = 0;
                for (String cellData : rowData) {
                    Cell cell = row.createCell(cellNum++);
                    cell.setCellValue(cellData);
                }
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String filename = "ChaChongExcel_" + dateFormat.format(new Date()) + ".xlsx";
            FileOutputStream outputStream = new FileOutputStream(filePath+filename);
            workbook.write(outputStream);

            outputStream.close();
            workbook.close();
            JOptionPane.showMessageDialog(null, "导出成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}