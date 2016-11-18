package com.mindtree.joins.driver;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.mindtree.joins.mapper.StoreLocationMapper;
import com.mindtree.joins.mapper.StoreSalesMapper;
import com.mindtree.joins.reducer.StoreReducer;
/**
 * @author M1035838
 *
 */
public class StoreDriver extends Configured implements Tool{

	public static void main(String[] args) throws Exception {
		System.exit(ToolRunner.run(new StoreDriver(), args));
	}

	@Override
	public int run(String[] arg0) throws Exception {
		
		if(arg0.length < 2){
			System.out.println("Missing Arguments <inputFile> <outputDir>");
			System.exit(1);
		}
		
		Job job = new Job(getConf(), "Module4_1_3");
		job.setJarByClass(StoreDriver.class);
		job.setMapperClass(StoreLocationMapper.class);
		job.setReducerClass(StoreReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		MultipleInputs.addInputPath(job, new Path(arg0[0]), TextInputFormat.class, StoreLocationMapper.class);
		MultipleInputs.addInputPath(job, new Path(arg0[1]), TextInputFormat.class, StoreSalesMapper.class);
		FileOutputFormat.setOutputPath(job, new Path(arg0[2]));
		
		return job.waitForCompletion(true) ? 0 : 1;
		
	}
}
