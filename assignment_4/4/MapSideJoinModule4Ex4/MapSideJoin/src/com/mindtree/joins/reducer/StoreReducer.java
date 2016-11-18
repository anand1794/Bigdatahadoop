package com.mindtree.joins.reducer;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * @author M1035838
 *
 */
public class StoreReducer extends Reducer<Text, Text, Text, DoubleWritable>{

	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		
		double salesAmount = 0.0;
		for(Text value : values){
			salesAmount += Double.parseDouble(value.toString());
		}
		context.write(key, new DoubleWritable(salesAmount));
		
	}
}
