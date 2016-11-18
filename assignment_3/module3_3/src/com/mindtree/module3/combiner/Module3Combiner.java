package com.mindtree.module3.combiner;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * @author M1035838
 *
 */
public class Module3Combiner extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

	@Override
	protected void reduce(Text keys, Iterable<DoubleWritable> values, Context context)
			throws IOException, InterruptedException {
	
		
		double sum=0;
		for (DoubleWritable dw : values) {
			double d = dw.get();
			sum += d;
		}
		context.write( new Text("data"), new DoubleWritable(sum));
	}
}