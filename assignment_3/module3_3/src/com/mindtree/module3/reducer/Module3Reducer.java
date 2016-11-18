package com.mindtree.module3.reducer;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * @author M1035838
 *
 */
public class Module3Reducer extends Reducer<Text, DoubleWritable, Text, Text> {

	@Override
	protected void reduce(Text keys, Iterable<DoubleWritable> values, Context context)
			throws IOException, InterruptedException {
		
		
		Double min = Double.MAX_VALUE;
		Double max = Double.MIN_VALUE;
		double sum = 0;
		int count=0;
		
		for (DoubleWritable doubleWritable : values) {
			double d = doubleWritable.get();
			sum += d;
			
			count++;
			
			if(d < min){
				min=d;
			}
			if(d > max){
				max=d;
			}
			
		}
		
		context.write(new Text("count:"+count+""+","+"sum:"+sum+""+","+"max:"+max+""+","+"min:"+min+""), new Text());
		
	}
}