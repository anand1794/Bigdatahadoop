package com.mindtree.module4.partitioner;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
/**
 * @author M1035838
 *
 */
public class Module4Partitioner extends Partitioner<Text, Text>{

	@Override
	public int getPartition(Text key, Text value, int numReduceTask) {
		
		String[] split = value.toString().split("_");
		int age = Integer.parseInt(split[1]);
		if(numReduceTask == 0){
			return 0;
		}else if(age < 20){
			return 0;
		}else if(age >= 20 && age <= 50){
			return 1%numReduceTask;
		}else{
			return 2%numReduceTask;
		}
	}

}
