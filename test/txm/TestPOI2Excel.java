package txm;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.FileOutputStream;

//03的excel用HSSF
//07的excel用XSSF，大数据使用这个

public class TestPOI2Excel {
    @Test
    public void testWrite() throws Exception {
        //1、创建工作簿
        HSSFWorkbook workbook=new HSSFWorkbook();
        //2、创建工作表
        HSSFSheet sheet=workbook.createSheet("Test");
        //3、创建行 创建第3行
        HSSFRow row=sheet.createRow(2);
        //4、创建单元格 创建第三行第三列
        HSSFCell cell=row.createCell(2);
        cell.setCellValue("Hello word");

        //输出到硬盘
        //一个.xls文件包括多个sheet
        FileOutputStream outputStream=new FileOutputStream("ceshi.xls");
        //把excel输出到具体地址
        workbook.write(outputStream);

        workbook.close();
        outputStream.close();
    }


    @Test
    public void testRead() throws Exception {

        FileInputStream inputStream=new FileInputStream("ceshi.xls");

        //1、读取工作簿
        HSSFWorkbook workbook=new HSSFWorkbook(inputStream);
        //2、读取工作表
        HSSFSheet sheet=workbook.getSheetAt(0);
        //3、读取行 读取第3行
        HSSFRow row=sheet.getRow(2);
        //4、读取单元格 读取第三行第三列
        HSSFCell cell=row.getCell(2);
        System.out.println("第三行第三列单元格的内容为"+cell.getStringCellValue());


        workbook.close();
        inputStream.close();
    }


    @Test
    public void test03Or07() throws Exception {

        FileInputStream inputStream=new FileInputStream("ceshi.xls");
        boolean is03Excel=true;

        //1、读取工作簿
        Workbook workbook=is03Excel?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
        //2、读取工作表
        Sheet sheet=workbook.getSheetAt(0);
        //3、读取行 读取第3行
        Row row=sheet.getRow(2);
        //4、读取单元格 读取第三行第三列
        Cell cell=row.getCell(2);
        System.out.println("第三行第三列单元格的内容为"+cell.getStringCellValue());


        workbook.close();
        inputStream.close();
    }


    @Test
    public void testExcelStyle() throws Exception {
        //1、创建工作簿
        HSSFWorkbook workbook=new HSSFWorkbook();
        //1.1、创建合并单元格对象
        CellRangeAddress cellRangeAddress=new CellRangeAddress(2,2,2,4);
        //1.2、创建单元格样式
        HSSFCellStyle style=workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //垂直居中
        //1.3、创建字体
        HSSFFont font=workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short)16);
        //加载字体
        style.setFont(font);

        //单元格背景
        //设置背景填充模式
        style.setFillPattern(HSSFCellStyle.DIAMONDS);
        //设置填充前景色
        style.setFillForegroundColor(HSSFColor.RED.index);
        //设置背景填充颜色
        style.setFillBackgroundColor(HSSFColor.YELLOW.index);


        //2、创建工作表
        HSSFSheet sheet=workbook.createSheet("Test");
        //2.1、加载合并单元格对象
        sheet.addMergedRegion(cellRangeAddress);

        //3、创建行 创建第3行
        HSSFRow row=sheet.createRow(2);
        //4、创建单元格 创建第三行第三列
        HSSFCell cell=row.createCell(2);
        //加载样式
        cell.setCellStyle(style);
        cell.setCellValue("Hello word");

        //输出到硬盘
        //一个.xls文件包括多个sheet
        FileOutputStream outputStream=new FileOutputStream("ceshi.xls");
        //把excel输出到具体地址
        workbook.write(outputStream);

        workbook.close();
        outputStream.close();
    }


}