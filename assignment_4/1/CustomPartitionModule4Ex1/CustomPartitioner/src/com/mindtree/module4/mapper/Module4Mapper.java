package com.mindtree.module4.mapper;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * @author M1035838
 *
 */
public class Module4Mapper extends Mapper<LongWritable, Text, Text, Text>{

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String[] split = value.toString().trim().split("\\s+");
		String nameAgeScore = split[0]+"_"+split[1]+"_"+split[3];
		context.write(new Text(split[2]), new Text(nameAgeScore));
	}
}
