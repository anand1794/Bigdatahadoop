package com.mindtree.weather.mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * @author M1035838
 *
 */
public class WeatherMapper extends Mapper<LongWritable, Text, Text, Text>{
	
	static Date TEMP_DATE;
	static String DATE;
	static float MAX_TEMP;
	static float MIN_TEMP;
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String[] split = value.toString().split("\\s+");
		SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
		SimpleDateFormat format2 = new SimpleDateFormat("dd/mm/yyyy");
		try {
			TEMP_DATE = format.parse(split[1]);
			DATE = format2.format(TEMP_DATE).toString();
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		MAX_TEMP = Float.parseFloat(split[5]);
		MIN_TEMP = Float.parseFloat(split[6]);
		context.write(new Text(DATE), new Text(MAX_TEMP + "\t" + MIN_TEMP));
		
	}
}
