package com.mindtree.weather.driver;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.mindtree.weather.mapper.WeatherMapper;
import com.mindtree.weather.reducer.WeatherReducer;
/**
 * @author M1035838
 *
 */
public class WeatherDriver extends Configured implements Tool{

	public static void main(String[] args) throws Exception {
		System.exit(ToolRunner.run(new WeatherDriver(), args));
	}

	@Override
	public int run(String[] arg0) throws Exception {
		
		if(arg0.length < 2){
			System.out.println("Missing Arguments <inputFile> <outputDir>");
			System.exit(1);
		}
		
		Job job = new Job(getConf(), "Module4_1_4");
		job.setJarByClass(WeatherDriver.class);
		job.setMapperClass(WeatherMapper.class);
		job.setReducerClass(WeatherReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		
		return job.waitForCompletion(true) ? 0 : 1;
		
	}
}
