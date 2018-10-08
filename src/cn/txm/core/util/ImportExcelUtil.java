package cn.txm.core.util;

import cn.txm.nsfw.user.dao.impl.UserDaoImpl;
import cn.txm.nsfw.user.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ImportExcelUtil {
    public static List<User> importExcel(File userExcel, String userExcelFileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(userExcel);
            boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
            //1、读取工作簿
            Workbook workbook = is03Excel ? new HSSFWorkbook(fileInputStream) : new XSSFWorkbook(fileInputStream);
            //2、读取工作表
            Sheet sheet = workbook.getSheetAt(0);
            //3、读取行
            List<User> userList=new ArrayList<User>();
            User user;
            if (sheet.getPhysicalNumberOfRows() > 2) {
                for (int k = 2; k < sheet.getPhysicalNumberOfRows(); k++) {
                    user=new User();
                    //4、读取单元格
                    Row row = sheet.getRow(k);
                    user = new User();
                    //用户名
                    Cell cell0 = row.getCell(0);
                    user.setName(cell0.getStringCellValue());
                    //帐号
                    Cell cell1 = row.getCell(1);
                    user.setAccount(cell1.getStringCellValue());
                    //所属部门
                    Cell cell2 = row.getCell(2);
                    user.setDept(cell2.getStringCellValue());
                    //性别
                    Cell cell3 = row.getCell(3);
                    user.setGender(cell3.getStringCellValue().equals("男"));
                    //手机号
                    String mobile = "";
                    Cell cell4 = row.getCell(4);
                    try {
                        mobile = cell4.getStringCellValue();
                    } catch (Exception e) {
                        double dMobile = cell4.getNumericCellValue();
                        mobile = BigDecimal.valueOf(dMobile).toString();
                    }
                    user.setMobile(mobile);

                    //电子邮箱
                    Cell cell5 = row.getCell(5);
                    user.setEmail(cell5.getStringCellValue());
                    //生日
                    Cell cell6 = row.getCell(6);
                    if (cell6.getDateCellValue() != null) {
                        user.setBirthday(cell6.getDateCellValue());
                    }
                    //默认用户密码为 123456
                    user.setPassword("123456");
                    //默认用户状态为 有效
                    user.setState(User.USER_STATE_VALID);

                    userList.add(user);
                }
            }
            workbook.close();
            fileInputStream.close();

            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
}