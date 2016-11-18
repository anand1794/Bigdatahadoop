package com.mindtree.module4.driver;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.mindtree.module4.mapper.Module4Mapper;
import com.mindtree.module4.partitioner.Module4Partitioner;
import com.mindtree.module4.reducer.Module4Reducer;
/**
 * @author M1035838
 *
 */
public class Module4Driver extends Configured implements Tool{

	public static void main(String[] args) throws Exception {
		System.exit(ToolRunner.run(new Module4Driver(), args));
	}

	@Override
	public int run(String[] arg0) throws Exception {
		
		if(arg0.length != 2){
			System.out.println("Missing Arguments <inputFile> <outputDir>");
			System.exit(1);
		}
		
		Job job = new Job(getConf(), "Module 4.1.1");
		job.setJarByClass(Module4Driver.class);
		job.setMapperClass(Module4Mapper.class);
		job.setPartitionerClass(Module4Partitioner.class);
		job.setReducerClass(Module4Reducer.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		
		return job.waitForCompletion(true) ? 0 : 1;
		
	}
}
