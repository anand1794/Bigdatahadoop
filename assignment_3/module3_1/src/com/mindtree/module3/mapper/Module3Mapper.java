package com.mindtree.module3.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * @author M1035838
 *
 */
public class Module3Mapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String[] split = value.toString().split("\\s+");
		double inputvalue = new Double(split[2]).doubleValue();
		
		if(inputvalue<=20.0){
			context.write(value, new Text());;
		}
		
	}

}
