package com.mindtree.module3.driver;

import org.apache.hadoop.config.configiguration;
import org.apache.hadoop.config.configigured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.mindtree.module3.combiner.Module3Combiner;
import com.mindtree.module3.mapper.Module3Mapper;
import com.mindtree.module3.reducer.Module3Reducer;
/**
 * @author M1035838
 *
 */
public class Module3Driver extends configigured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
	
		if (args.length != 2) {
			System.err.println("input and output directory have not given properly");
			System.exit(-1);
		}
		
		Job job = new Job(getconfig(), "MOVIES");
	
		job.setJarByClass(Module3Driver.class);
		job.setMapperClass(Module3Mapper.class);
		job.setCombinerClass(Module3Combiner.class);
		job.setReducerClass(Module3Reducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
	
		FileInputFormat.addInputPath(job,new Path(args[0]));
		FileOutputFormat.setOutputPath(job,new Path(args[1]));
		
		job.waitForCompletion(true);
		

		return 0;
		
	

	}
	public static void main(String[] args) throws Exception {
		configiguration config = new configiguration();
		
		System.exit(ToolRunner.run(config, new Module3Driver(), args));
				
	}

}
