package com.mindtree.module3.reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * @author M1035838
 *
 */
public class Module3Reducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text keys, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		Double min = Double.MAX_VALUE;
		Double max = Double.MIN_VALUE;
		double sum = 0;
		int count=0;
		
		String combinedata;
		String[] combinedataArray;
		double tempValue;
		
		for (Text value : values) {
			combinedata = value.toString();
			combinedataArray=combinedata.split("_");
			tempValue= new Double(combinedataArray[1]).doubleValue();
			count++;
			sum += tempValue;
			if(tempValue < min ){
				min = tempValue;
			}
			if(tempValue > max ){
				max = tempValue;	
			}
		}
		context.write(new Text("output:"), new Text("(count:"+count+" ) "+"(sum:"+sum+" ) "+"(max:"+max+" ) "+"(min:"+min+" )"));
		
	}
}