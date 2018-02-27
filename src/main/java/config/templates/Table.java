package config.templates;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


public class Table {

	protected WebElement table;
	protected String rowFilter = "";
	protected String columnFilter = "";
	
	public Table(WebElement e ){
		
		this.table = e;			
	}
	
	/**
	 * For those table would have invisible row or column exist, we can we use xpath filters to exclude them
	 * */
	public void setTableFilter(String rowFilter, String columnFilter)
	{
		this.rowFilter = rowFilter;
		this.columnFilter = columnFilter;
	}


	public int getTotalColumn(){
		return table.findElements(By.xpath(".//th")).size();
	}

	public int getTotalRow(){
		//It did not count the first row as it is the column row
		return table.findElements(By.xpath(".//tr")).size()-1;
	}
	
	/**
	 * Get row index from a row name 
	 * Or several strings in row that can determine a unique column
	 * which separated by ","
	 * 
	 * @param  rowInfo
	 * Example: getRowIndex("rowName"), getRowIndex("namestring1, namestring2")
	 * 
	 * */
	public int getRowIndex(String rowInfo){
		
		String[] rowkey = rowInfo.split(",");
		StringBuilder sRowXpath = new StringBuilder(".//tr");
		for(int i=0;i<rowkey.length;i++)
		{
			sRowXpath.append("[./td[contains(.,'").append(rowkey[i]).append("')]]");
			
		}
		
		sRowXpath.append(rowFilter);

		try {
			// It needs to count itself , hence it needs to + 1
			return table.findElement(By.xpath(sRowXpath.toString())).findElements(By.xpath("./preceding-sibling::tr[./td]" + rowFilter)).size()+1;
		}catch (NoSuchElementException e){
			throw new NoSuchElementException("Cannot find row contains "+rowInfo);
		}
	}

	/**
	 * Get column index from a column name 
	 * Or several strings in a cell in first row that can determine a unique column
	 * which separated by ","
	 * @throws Exception 
	 * 
	 * @param  columnName
	 * Example: getColumnIndex("ColumnName"), getColumnIndex("namestring1, namestring2")
	 * 
	 * */
	public int getColumnIndex(String columnName){
		
		String[] columnkey = columnName.split(",");
		StringBuilder sColumnXpath = new StringBuilder(".//th");  // In our website we use <thead> instead of <th>
		for(int i=0;i<columnkey.length;i++)
		{
			sColumnXpath.append("[contains(.,'").append(columnkey[i]).append("')]");
		}			
		sColumnXpath.append(columnFilter);
		
		if(table.findElements(By.xpath(".//th")).size()==0)
		{
			throw new NoSuchElementException("column name is not under tag ");
		}
		
		return table.findElement(By.xpath(sColumnXpath.toString())).findElements(By.xpath("./preceding-sibling::th" + columnFilter)).size() ;
		
	}

	public WebElement getCell(int rowIndex, int columnIndex)
	{
		String cellXpath = ".//tr[./td][" + String.valueOf(rowIndex) + "]/td[" + String.valueOf(columnIndex) + "]";
		return table.findElement(By.xpath(cellXpath));
	}

	public WebElement getCell(int rowIndex, String columnInfo)
	{
		String cellXpath = ".//tr[./td][" + String.valueOf(rowIndex) + "]/td[" + String.valueOf(getColumnIndex(columnInfo)) + "]";
		return table.findElement(By.xpath(cellXpath));
	}

	public WebElement getCell(String rowInfo, String columnInfo)
	{
		String cellXpath = ".//tr[./td][" + String.valueOf(getRowIndex(rowInfo)) + "]/td[" + String.valueOf(getColumnIndex(columnInfo)) + "]";
		return table.findElement(By.xpath(cellXpath));
	}

	public WebElement getCell(String rowInfo, int columnIndex)
	{
		String cellXpath = ".//tr[./td][" + String.valueOf(getRowIndex(rowInfo)) + "]/td[" + String.valueOf(columnIndex) + "]";
		return table.findElement(By.xpath(cellXpath));
	}


	public WebElement getColumnCell(int columnIndex){
		String cellXpath = ".//th[" + String.valueOf(columnIndex) + "]";
		return table.findElement(By.xpath(cellXpath));
	}
	
	public String getColumnName(int columnIndex){
		String cellXpath = ".//th[" + String.valueOf(columnIndex) + "]";
		return table.findElement(By.xpath(cellXpath)).getText();
	}
	

	public String getCellText(int rowIndex, int columnIndex){

		return getCell(rowIndex, columnIndex).getText();
	}
	
	public String getCellText(String rowInfo, int columnIndex){
		
		return getCell(getRowIndex(rowInfo),columnIndex).getText();
	}
	
	public String getCellText(int rowIndex, String columnName){
		
		return getCell(rowIndex,getColumnIndex(columnName)).getText();
	}
	
	public String getCellText(String rowInfo, String columnName){
		
		return getCell(getRowIndex(rowInfo),getColumnIndex(columnName)).getText();
	}
	

	
}
