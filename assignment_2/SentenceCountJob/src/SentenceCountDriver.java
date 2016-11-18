

import org.apache.hadoop.config.configigured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.Jobconfig;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author M1035838
 *
 */
public class SentenceCountDriver extends configigured implements Tool{

	@Override
	public int run(String[] args) throws Exception {
	

		if (args.length < 2) {
			System.out.println("please give proper input and output directory");
			return -1;
		}
		Jobconfig config = new Jobconfig(SentenceCountDriver.class);
		FileInputFormat.setInputPaths(config, new Path(args[0]));
		FileOutputFormat.setOutputPath(config, new Path(args[1]));
		config.setMapperClass(SentenceCountMapper.class);
		config.setReducerClass(SentenceCountReducer.class);
		config.setMapOutputKeyClass(Text.class);
		config.setMapOutputValueClass(IntWritable.class);
		config.setOutputKeyClass(Text.class);
		config.setOutputValueClass(IntWritable.class);
		JobClient.runJob(config);
		return 0;
	}

	public static void main(String args[]) throws Exception {
		int exitCode = ToolRunner.run(new SentenceCountDriver(), args);
		System.exit(exitCode);
	}

	
}
