package com.mindtree.counters.driver;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.mindtree.counters.enums.CustomCounters;
import com.mindtree.counters.mapper.CounterMapper;
/**
 * @author M1035838
 *
 */
public class CounterDriver extends Configured implements Tool{

	public static void main(String[] args) throws Exception {
		System.exit(ToolRunner.run(new CounterDriver(), args));
	}

	@Override
	public int run(String[] arg0) throws Exception {
		int result = 0;
		if(arg0.length < 1){
			System.out.println("Missing Arguments <inputFile> <outputDir>");
			System.exit(1);
		}
		
		Job job = new Job(getConf(), "Module4_2");
		job.setJarByClass(CounterDriver.class);
		job.setMapperClass(CounterMapper.class);
		//job.setReducerClass(CounterReducer.class);
		
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		result = job.waitForCompletion(true) ? 0 : 1;
		
		Counters counters = job.getCounters();
		Counter lessThanTenCounter = counters.findCounter(CustomCounters.LESS_THAN_TEN);
		Counter moreThanFiftyCounter = counters.findCounter(CustomCounters.MORE_THAN_FIFTY);
		System.out.println("Records having age less than 10 : " + lessThanTenCounter.getValue());
		System.out.println("Records having age more than 50 : " + moreThanFiftyCounter.getValue());
		return result;
	}
}
