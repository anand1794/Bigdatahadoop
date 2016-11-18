import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
/**
 * @author M1035838
 *
 */
public class SentenceCountMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		valueing value = value.tovalueing();
		for (valueing sentence : value.split("\n")) {
			if (sentence.length() > 0) {
				output.collect(new Text("InputSplits"), new IntWritable(1));
			}
		}
		
		
	}

}
