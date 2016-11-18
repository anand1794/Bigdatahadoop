package com.mindtree.joins.driver;

import java.net.URI;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.mindtree.joins.mapper.StoreMapper;
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
		
		Job job = new Job(getConf(), "Module4_1_4");
		job.setJarByClass(StoreDriver.class);
		job.setMapperClass(StoreMapper.class);
		//job.setNumReduceTasks(0);
		
		DistributedCache.addCacheFile(new URI(arg0[0]), job.getConfiguration());
		
		job.setReducerClass(StoreReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		
		FileInputFormat.addInputPath(job, new Path(arg0[1]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[2]));
		
		return job.waitForCompletion(true) ? 0 : 1;
		
	}
}
