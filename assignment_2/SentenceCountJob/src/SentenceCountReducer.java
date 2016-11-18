import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
/**
 * @author M1035838
 *
 */
public class SentenceCountReducer  extends MapReduceBase implements Reducer<Text, IntWritable, Text,IntWritable>{

	@Override
	public void reduce(Text key, Iterator<IntWritable> value, OutputCollector<Text, IntWritable> output,
			Reporter reporter)
			throws IOException {
	
			int count = 0;
			while (value.hasNext()) {
				IntWritable i = value.next();
				count += i.get();
			}
			output.collect(new Text("Total Number of lines are:"), new IntWritable(count ));
			
		
		}
	
				
		}
	

