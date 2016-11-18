package com.mindtree.module4.reducer;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * @author M1035838
 *
 */
public class Module4Reducer extends Reducer<Text, Text, Text, Text>{

	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String age = "";
		String name = "";
		int score = 0;
		int maxScore = Integer.MIN_VALUE;
		for(Text value : values){
			String[] split = value.toString().split("_");
			score = Integer.parseInt(split[2]);
			if(score > maxScore){
				name = split[0];
				age = split[1];
				maxScore = score;
			}
		}
		context.write(new Text("Name - " + name), new Text("Age - " + age + " Score - " + maxScore));
	
	}
}
