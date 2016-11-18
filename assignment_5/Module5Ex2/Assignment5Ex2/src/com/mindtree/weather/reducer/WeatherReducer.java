package com.mindtree.weather.reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * @author M1035838
 *
 */
public class WeatherReducer extends Reducer<Text, Text, Text, Text>{

	static String HOT_DAY;
	static String COLD_DAY;
	
	@Override
	protected void setup(Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		HOT_DAY = "HOT DAY";
		COLD_DAY = "COLD DAY";
	}
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		for(Text value : values){
			String[] split = value.toString().split("\t");
			float maxTemp = Float.parseFloat(split[0]);
			float minTemp = Float.parseFloat(split[1]);
			if(maxTemp > 42){
				context.write(key, new Text(HOT_DAY));
			}else if(minTemp < 8){
				context.write(key, new Text(COLD_DAY));
			}
		}
		
	}
}
