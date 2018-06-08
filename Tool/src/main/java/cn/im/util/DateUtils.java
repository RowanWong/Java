package cn.im.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/** 
 * @Description: 时间类  (需要commons-lang jar包)
 * @author p.x.pang
 * @date 2012-12-16 下午08:03:49 
 * @version V1.0   
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	/**
	 * 字符串转换成时间
	 * @param stringDate
	 * @return
	 */
	public static Date string2Date(String stringDate, String pas) {
		Date reDate;

		try {
			reDate = new SimpleDateFormat(pas).parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
			reDate = null;
		}

		return reDate;
	}
	
	/**
	 * 将Long类型的时间转化为String类型
	 * @param time
	 * @return
	 */
	public static String TimeForamt(String time){
		 Date date = new Date(Long.parseLong(time));
		 DateFormat df2 = DateFormat.getDateTimeInstance();//可以精确到时分秒
	    // System.out.println(df2.format(date));
	    // DateFormat df3 = DateFormat.getTimeInstance();//只显示出时分秒
		return df2.format(date);
	}
       
	
	/**
	 * 字符串转换成时间
	 * @param stringDate
	 * @return
	 */
	public static Date string2Date(String stringDate) {
		return string2Date(stringDate, "yyyyMMdd");
	}
	
	/**
	 * 获取前一周的周一
	 * @return
	 */
	public static Date getPreviousWeekOneDay(){
		Calendar calendar;

		calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		//用当前时间-7天
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
		calendar.get(Calendar.DAY_OF_YEAR);
		
		return getWeekOneDay(calendar.getTime());
	}
	
	/**
	 * 获取传入时间的周一
	 * @return
	 */
	public static Date getWeekOneDay(Date date) {
		Calendar calendar;

		calendar = Calendar.getInstance();
		calendar.setTime(date);

		// 是否是周日
		if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
			calendar.get(Calendar.DAY_OF_YEAR);
		}

		calendar.set(Calendar.DAY_OF_WEEK, 2); // 设置为周一
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取传入时间的周日
	 * @return
	 */
	public static Date getWeekLastDay(Date date) {
		Calendar calendar;

		calendar = Calendar.getInstance();
		calendar.setTime(date);

		// 是否是周日
		if (calendar.get(Calendar.DAY_OF_WEEK) != 1) {
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 6);
			calendar.get(Calendar.DAY_OF_YEAR);
		}

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取传入时间的周日
	 * @return
	 */
	public static Date addDate(Date date, int day) {
		Calendar calendar;

		calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + day);
		calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	
	/**
	 * 获取活动时间
	 * @return
	 */
	public static List<Date> getActTopDate(Date starttime) {
		SimpleDateFormat sdf;
		String endWeekDay;//结束的周一
		Date start;//周一
		Date end;//周日
		int index;
		List<Date> reList = null;
		
		if(null != starttime){
			index = 0;
			sdf = new SimpleDateFormat("yyyyMMdd");
			reList = new ArrayList<Date>();
			endWeekDay = sdf.format(getPreviousWeekOneDay());//获取结束时间的周一
			
			//获取开始时间的周日
			start = getWeekOneDay(starttime);
			end = getWeekLastDay(start);
			reList.add(start);

			//开始时间不等于结束时间
			while(!sdf.format(start).equals(endWeekDay) && index < 100){
				start = addDate(end, 1);//让end+1天跳到下一周的第一天
				end = getWeekLastDay(start);//获取到下一周的最后一天
				reList.add(start);
				index++;
			}
		}
		
		return reList;
	}
	
	//获取指定时间
	public static Date getDesignationDate(int year, int mth, int day , int H, int M, int S){
		Calendar calendar;

		calendar = Calendar.getInstance();
		
        calendar.set(Calendar.DAY_OF_MONTH, day);  //设置日期  
        calendar.set(Calendar.MONTH, mth-1 );  
        calendar.set(Calendar.YEAR, year);      
		calendar.set(Calendar.HOUR_OF_DAY, H);
		calendar.set(Calendar.MINUTE, M);
		calendar.set(Calendar.SECOND, S);

		return calendar.getTime();
	}
	
	/**
	 * 是否在时间范围内
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean inRangeTime(Date start, Date end){
		boolean reflag = false; 
		Date currTime = new Date();
		
		if(currTime.after(start) && currTime.before(end)){
			reflag = true;
		}

		return reflag;
	}
	
	public static void main(String[] args) {
		
		
	}
}