package me.seaOf.utils;

import me.seaOf.anno.Column;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

public class PoiUtils {

    private PoiUtils(){
    }
    public static Workbook getWorkbook(List list,Class clazz){
        //创建工作簿对象
        Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        int line = 0;
        Row row = null;
        Sheet sheet = wb.createSheet(clazz.getSimpleName());
        Field[] fields = clazz.getDeclaredFields();
        row = sheet.createRow(0);
        int col = 0;
        for (Field field : fields) {
            row.createCell(col).setCellValue(field.getAnnotation(Column.class).value());
            col++;
        }
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        cellStyle.setFillPattern(CellStyle.BIG_SPOTS);
        for (Object o : list) {
            Class cla = o.getClass();
            Field[] fds = cla.getDeclaredFields();
            row = sheet.createRow(++line);
            int coll = 0;
            for (Field fd : fds) {
                String getMethod = "get"+fd.getName().substring(0,1).toUpperCase()+fd.getName().substring(1);
                try {
                    Method method = cla.getMethod(getMethod);
                    Object objstr = method.invoke(o);
                    row.createCell(coll).setCellValue(objstr.toString());
                    coll++;
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return wb;
    }

}
