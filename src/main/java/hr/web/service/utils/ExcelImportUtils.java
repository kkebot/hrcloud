package hr.web.service.utils;

/**
 * https://www.jianshu.com/p/e12c69eea764
 */
public class ExcelImportUtils
{
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
