package com.hc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.DateFormatConverter.DateFormatTokenizer;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	//判断excel文件后缀名，生成不同的workbook
	public static Workbook createWorkbook(InputStream is,String excelFileName) throws IOException{
		
        if (excelFileName.endsWith(".xls")) {
        	
            return new HSSFWorkbook(is);
            
        }else if (excelFileName.endsWith(".xlsx")) {
        	
            return new XSSFWorkbook(is);
        }
        return null;
    }
	
	//根据sheet索引号获取对应的sheet
	public static Sheet getSheet(Workbook workbook,int sheetIndex){
        return workbook.getSheetAt(sheetIndex);        
    }
	
	//将sheet中的数据保存到list中，
    //调用此方法时，object的属性个数必须和excel文件每行数据的列数相同且一一对应，
	//object的所有属性都为String
	@SuppressWarnings("deprecation")
	public static List<Object> importListFromExcel(Object object,InputStream is,String excelFileName){
		
        List<Object> list = new ArrayList<Object>();
        
        try {
        	//创建工作簿
            Workbook workbook = createWorkbook(is, excelFileName);
            
            //创建工作表sheet
            Sheet sheet = getSheet(workbook, 0);
            
            //获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
          //System.out.println("有"+rows+"行");
            
            //获取表头单元格个数
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
            
            
            //利用反射，给JavaBean的属性进行赋值
            Field[] fields = object.getClass().getDeclaredFields();
            
            /*System.out.println("Field[] :");
            for (Field field : fields) {
            	System.out.println(field);
			}
            Method[] methods = object.getClass().getMethods();
            for (Method method : methods) {
				System.out.println(method.getName());
			}*/
            
            //第一行为标题栏，从第二行开始取数据
             for (int i = 1; i < rows; i++) {
                 Row row = sheet.getRow(i);
                 
                 System.out.println("第"+i+"行");
                 //System.out.println(row);
                 
                 if (row != null) {
					
                	 int index = 0;
                     while (index < cells) {
                         Cell cell = row.getCell(index);
                         if (null == cell) {
                             cell = row.createCell(index);
                             //System.out.println("createCell------>"+cell.getCellTypeEnum());
                         }
                         
                         Field field = fields[index];
                         String fieldName = field.getName();
                         String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                         //System.out.println(methodName);
                         //System.out.println(field.getType().getName());
                         Method setMethod = object.getClass().getMethod(methodName, new Class[]{field.getType()});
                         //Method setMethod = null;
                         
                        // System.out.println(cell);
                         String cellType = cell.getCellTypeEnum().toString();//STRING NUMERIC  BLANK BOOLEAN _NONE  FORMULA ERROR
                         
                         System.out.println("getCellTypeEnum :"+ cellType);
                         if ( cellType.equals("NUMERIC") ) {
                        	 
                        	 boolean isDate = DateUtil.isCellDateFormatted(cell);
                        	 //System.out.println("----是否日期："+ isDate);
                        	 
                        	 if (field.getType().getName().toString().equals("java.lang.String")) {
                        		 
                        		 Double num = cell.getNumericCellValue();
                        		 Integer intNum = num.intValue();
                        		 setMethod.invoke(object, new Object[]{intNum.toString()});
                        		 
							}else if (isDate) {
                        		//是日期
								//System.out.print("是日期   -----> ");
                        		Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                        		System.out.println("日期："+date);
                        		if (date != null && date.toString().trim().length() > 0) {
                        			
                        			setMethod.invoke(object, new Object[]{date});
								}
                        		//setMethod = object.getClass().getMethod(methodName, new Class[]{Date.class});
                        		
                        	}else{
                        		//是数字
                        		System.out.print("是数字   ------> ");
                        		Double num = cell.getNumericCellValue();
                        		System.out.println(num);
                        		
                        		if (num != null && num.toString().trim().length() > 0) {
									
                        			//属性类是整形
                            		if (field.getType().getName().toString().equals("java.lang.Integer")) {
                            			
    									int intNum = num.intValue();
    									setMethod.invoke(object, new Object[]{intNum});
    									
    								}else{
    									
    									setMethod.invoke(object, new Object[]{num});
    								}
								}
                        		
                        	}
                        	 
    					}else if( cellType.equals("STRING") ){
    						//是字符串
    						//System.out.print("是字符串   ------> ");
    						String str = cell.getStringCellValue();
                    		//System.out.println(str);
                    		
                    		if (str != null && str.trim().length() > 0) {
                    			setMethod.invoke(object, new Object[]{str});
							}
                    		
    					}else if ( cellType.equals("BLANK") ) {
							//没有值
    						//System.out.println("没有值");
						}
                         
                         index++;
                         
				 }
                     
                 //System.out.println("object---->"+object);
                 
                 }
                 if (isHasValues(object)) {//判断对象属性是否有值
                     list.add(object);
                     object = object.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});//���´���һ��object����
                 }
             }
         } catch (Exception e) {
        	 e.printStackTrace();
         }finally{
             try {
                 is.close();//关闭流
             } catch (Exception e2) {
            	 e2.printStackTrace();
             }
         }
         return list;
         
     }
	
	
	//判断一个对象所有属性是否有值，如果一个属性有值(分空)，则返回true
	public static boolean isHasValues(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        boolean flag = false;
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
            Method getMethod;
            try {
                getMethod = object.getClass().getMethod(methodName);
                Object obj = getMethod.invoke(object);
                if (null != obj && !"".equals(obj)) {
                    flag = true;
                    break;
                }
                
            } catch (Exception e) {
            	e.printStackTrace();
            }
            
        }
        return flag;
    }
	
	public <T> void exportListToExcel(List<T> list,String[] headers,String title,OutputStream os){
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        //设置表格默认列宽15个字节
        sheet.setDefaultColumnWidth(15);
        //生成一个样式
        HSSFCellStyle style = this.getCellStyle(workbook);
        //生成一个字体
        HSSFFont font = this.getFont(workbook);
        //把字体应用到当前样式
        style.setFont(font);
        
        //生成表格标题
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short)300);
        HSSFCell cell = null;
        
        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell(i);
            //cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        
        //将数据放入sheet中
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i+1);
            T t = list.get(i);
            //System.out.println(t);
            
            //利用反射，根据JavaBean属性的先后顺序，动态调用get方法得到属性的值
            Field[] fields = t.getClass().getDeclaredFields();
            
            try {
                for (int j = 0; j < fields.length; j++) {
                    cell = row.createCell(j);
                    Field field = fields[j];
                    String fieldName = field.getName();
                    String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
                    Method getMethod = t.getClass().getMethod(methodName,new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    
                    //System.out.println("methodName--->"+methodName);
                    //System.out.println("value--->"+value);
                    
                    //STRING NUMERIC  BLANK BOOLEAN _NONE  FORMULA ERROR
                    String fieldType = field.getType().getName().toString();
                    //System.out.println("fieldType---> "+fieldType);//java.lang.Integer java.lang.String java.util.Date java.lang.Double
                    
                    if (fieldType.equals("java.lang.Integer") || fieldType.equals("java.util.Date") || fieldType.equals("java.lang.Double") ) {
						
                    	if (value != null) {
							
                    		cell.setCellType(CellType.NUMERIC);
                    		
                    		if (fieldType.equals("java.util.Date")) {
                    			
                        		Date date = (Date)value;
                        		
                        		cell.setCellValue(date);  
                        	    CreationHelper createHelper = workbook.getCreationHelper();  
                        	    CellStyle cellStyle = workbook.createCellStyle();  
                        	    cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));  
                        	    cell.setCellStyle(cellStyle);
                                
                    		}
                    		else if (fieldType.equals("java.lang.Integer")) {
								
                    			cell.setCellValue((Integer)value);
                    			
							}else if (fieldType.equals("java.lang.Double")) {
								
                    			cell.setCellValue((Double)value);
							}
                    		
						}
                    	
					}else if ( fieldType.equals("java.lang.String") ) {
						//System.out.println("是字符串");
						if (value != null && value.toString().trim().length() > 0) {
							//System.out.println("不为空");
							cell.setCellType(CellType.STRING);
							cell.setCellValue(value.toString());
						}
					}
                    
                }
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
        
        try {
            workbook.write(os);
        } catch (Exception e) {
        	e.printStackTrace();
        }finally{
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
        
    }
	
	//获取单元格格式
	public HSSFCellStyle getCellStyle(HSSFWorkbook workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor((short)110);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setLeftBorderColor((short)3);
        style.setRightBorderColor((short)3);
        style.setAlignment(HorizontalAlignment.CENTER);
        
        return style;
    }
	
	//生成字体样式
	public HSSFFont getFont(HSSFWorkbook workbook){
        HSSFFont font = workbook.createFont();
        font.setColor((short)120);
        font.setFontHeightInPoints((short)12);
        font.setBold(true);
        return font;
    }
	
	/*public boolean isIE(HttpServletRequest request){
    	return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie")>0?true:false;    
	}*/
	
}
