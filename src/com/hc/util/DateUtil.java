package com.hc.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public boolean isWeekDay(Calendar cal){
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
        	w = 0;
        }
        //System.out.println(weekDays[w]);
        
        switch (weekDays[w]) {
		case "星期一":
			return true;
		case "星期二":
			return true;
		case "星期三":
			return true;
		case "星期四":
			return true;
		case "星期五":
			return true;
		case "星期六":
			return false;
		case "星期日":
			return false;
		}
        
		return false;
	}
	
	public Date getEndDate(Date startDate){
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(startDate);
		
		int total = 0;
		
		if (this.isWeekDay(cal)) {
			
			total = 4;
		}else{
			
			total = 5;
		}
		
		for (int i = 1; i <= total ; i++) {
			
			cal.add(Calendar.DATE, 1);
			
			if ( !this.isWeekDay(cal)) {
				
				i--;
			}
		}
		
		return cal.getTime();
	}
	
	public Date getNextStartDate(Date endDate){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		
		do{
			cal.add(Calendar.DATE, 1);
			
		}while( !this.isWeekDay(cal));
		
		return cal.getTime();
	}
}
