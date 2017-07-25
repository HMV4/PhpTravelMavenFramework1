package Excel;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.xssf.usermodel.HSSFCell;

public class ExcelApi {
	
	public static HSSFWorkbook wb;
	public static HSSFSheet sh;
	public static  HSSFRow row = null;
	public static HSSFCell cell = null;

	// Excel Path constructor
	public ExcelApi(String xlFilePath) throws Exception {
		try {
			FileInputStream fis = new FileInputStream(xlFilePath);
			wb = new HSSFWorkbook(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());;
		}

	}

	//---------------------------------Excel getdata() to get data from excelsheet--------------------------------------------------------------
	public  String getCellData(String Sheetname, int rownum, int colnum) {

		try {

			sh = wb.getSheet(Sheetname);
			row = sh.getRow(rownum);
			cell = row.getCell(colnum);

			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();

			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
					|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				String cellValue = String.valueOf(cell.getNumericCellValue());
				// return cell.getNumericCellValue();
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
					Date date = cell.getDateCellValue();
					cellValue = df.format(date);

				}
				return cellValue;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) 
			{
				return String.valueOf(cell.getBooleanCellValue());
			}else if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
				return "";
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	//-----------------------ROW COUNT-------------------------------------------------------------------------------------
	public int getRowCount(String sheetName)
	{
		try {
			int index=wb.getSheetIndex(sheetName)+1;
			if(index == -1)
			{
				return 0;
			}
			else
			{
				
				sh=wb.getSheet(sheetName);
				int rownumber=sh.getLastRowNum();
				return rownumber;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("getRowCount"+ e.getMessage());
		}
		return 0;
		
	}
	
	//--------------------------------------COLUMN COUNT----------------------------------------------
	public int getColCount(String sheetName)
	{
		try {
			int index =wb.getSheetIndex(sheetName);
			if(index ==-1)
			{
				return 0;
			}
			else{
				sh=wb.getSheet(sheetName);
				row=sh.getRow(0);
				int colNumber=row.getLastCellNum();
				
				return colNumber;	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("getColumnCount"+ e.getMessage());
		}
		return 0;
	}
	
	//--------Testing Purpose----------------------------------------------------
/*public static void main(String[] args) throws Exception
{
	String path ="C:\\Users\\Hituj Velukar\\Desktop\\Data\\Book5.xls";
	System.out.println(path);
	ExcelApi eat=new ExcelApi(path);
	//System.out.println(eat.getCellData("Login", 0, 0));
	
	int retrow=eat.getRowCount("Login");
	int retcol=eat.getColCount("Login");
	
	System.out.println("Row:"+retrow);
	System.out.println("Col:"+retcol);
	
	for(int i=1;i<=retrow;i++)
	{
		for(int j=0;j<retcol;j++)
		{
			
			System.out.println(eat.getCellData("Login", i, j));
		}
		
	}
	
	
}
*/
}
