package com.mindtree.counters.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.mindtree.counters.enums.CustomCounters;
/**
 * @author M1035838
 *
 */
public class CounterMapper extends Mapper<LongWritable, Text, Text, Text>{
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String[] split = value.toString().split("\t");
		if(Integer.parseInt(split[1]) < 10){
			context.getCounter(CustomCounters.LESS_THAN_TEN).increment(1);
		}else if(Integer.parseInt(split[1]) > 50){
			context.getCounter(CustomCounters.MORE_THAN_FIFTY).increment(1);
		}
	}
}
