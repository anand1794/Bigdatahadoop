package com.mindtree.module3.mapper;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * @author M1035838
 *
 */
public class Module3Mapper extends Mapper<LongWritable, Text, Text, DoubleWritable>{
	
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String[] split = value.toString().split("\\s+");
		
		double tempvalues= new Double(split[2]).doubleValue();

		context.write(new Text(split[0]), new DoubleWritable(tempvalues));
		
	}

}
