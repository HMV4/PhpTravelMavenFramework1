package Excel;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {
	static ExcelApi eat = null;
	static String xlPath="C:\\Users\\Hituj Velukar\\Desktop\\Data\\Book5.xls";
	static String sheetNames="Login";

	
	//-----------------------QuickBook data provider for travel booking-----------------------------
	@DataProvider(name="QuickBook")
	public static Object[][] userQuickBookData() throws Exception
	{
		Object[][] bookingData=testData(xlPath, sheetNames);
		return bookingData;
	}
	
	
	
	
	//-------------------Method to get data from ExcelAPI Class-------------------------------------------------------------
	public static Object[][] testData(String excelPath, String sheetName)
			throws Exception {

	//ExcelAPI object initailization-
		eat = new ExcelApi(excelPath);

		// total Row and Col count 
		int retRowNum = eat.getRowCount(sheetName);
		int retColNum = eat.getColCount(sheetName);
		System.out.println("Total Row::::" + retRowNum);
		System.out.println("Total Column::::" + retColNum);

	/*
	 * ===========2D variable of type Object initialization to store the data return by excelAPIs getCellData() method==========
	 */
		Object getExcelData[][] = new Object[retRowNum][retColNum];
		for (int i = 1; i <= retRowNum; i++) {
			for (int j = 0; j <retColNum; j++) {

				getExcelData[i-1][j] = eat.getCellData(sheetName,i,j);

			}

		}

		return getExcelData;

	}

}
