package com.mindtree.joins.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * @author M1035838
 *
 */
public class StoreLocationMapper extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {

		String[] split = value.toString().split(",");
		context.write(new Text(split[0]), new Text("location_"+split[1]));
		
	}
}
