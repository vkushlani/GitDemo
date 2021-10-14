package MavenProjectPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenViaExcel {

	public ArrayList<String> getData(String testcaseName) throws IOException {
	
		ArrayList<String> a = new ArrayList<String>();
		
		//XSSFWorkbook accepts FileInputStream argument and that how it will know which excel(workboook) it has to access
		//Step 1: Create object of XSSFWorkbook class and pass the FileInputStream argument. This will help to access the workbook
		FileInputStream fis = new FileInputStream("E:\\ExcelDemoData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//Step 2 : Get the count of worksheets present in workbook
		int sheets = workbook.getNumberOfSheets();
		
		//Step 3 : Run through all the sheets in workbook and access the required sheet which user wants
		for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("testdata"))
			{
			//Get the sheet at Index i. Return type of getSheetAt is XSSFSheet
			XSSFSheet sheet = workbook.getSheetAt(i);
			
		//Step 4 : Get access and reach to 1st Row of the worksheet identified
			
			//Iterator will scan through all the rows of the worksheet
			//rows.next() will land user to 1st row of the worksheet
			//object 'sheet' holds all the rows
			//object 'firstrow' holds all the cells of the first row where cell Iterator can be run to reach all the cells of that row
			//Return type of each element can be checked and found after clicking dot operator e.g.rows.  has return type as Row, sheet. has return type as Iterator<Row>
			
				Iterator<Row> rows = sheet.iterator();//sheet is collection of rows
				Row firstrow = rows.next();
				
				Iterator<Cell> cells = firstrow.cellIterator();//row is collection of cells
				//Iterate through each cell of the row. has.next() checks whether there is next element present 
				
				int k=0;
				int column=0;
				
				while(cells.hasNext())
				{
					//moves to first cell
				Cell value = cells.next();
				if(value.getStringCellValue().equalsIgnoreCase("Test Cases"))
				{
					column=k;
				}
				k++;
				}
				System.out.println(column);
			//Step 5 iterate through each row
				while(rows.hasNext()) {
					Row r= rows.next();
					//get cell value at the column index using getCell method
					//compare the value to "Purchase"
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))
						System.out.println(r.getCell(column).getStringCellValue());
					{
						//grab all the data for the cells in the "Purchase" row
						Iterator<Cell> cd = r.cellIterator();
						while(cd.hasNext())
						{
							Cell c = cd.next();
							
							if(c.getCellType()==CellType.STRING) 
							{
								a.add(c.getStringCellValue());
							}
							else
							{
								//convert int to string
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
									
								
							}
							
						}
						
						
						
					}
					
					
				}
			}
		}
		return a;
		
	}

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
	}}
