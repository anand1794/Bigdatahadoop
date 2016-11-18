package com.mindtree.joins.mapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * @author M1035838
 *
 */
public class StoreMapper extends Mapper<LongWritable, Text, Text, Text>{
	
	private Map<String, String> storeDetailsMap = new HashMap<String, String>();
	
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {

		Path[] files = DistributedCache.getLocalCacheFiles(context.getConfiguration());
		
		for(Path path : files){
			BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
			String line = reader.readLine();
			while(line != null){
				String token[] = line.split(",");
				storeDetailsMap.put(token[0], token[1] + "\t" + token[2]);
				line = reader.readLine();
			}
		}
		if(storeDetailsMap.isEmpty()){
			throw new IOException("Unable to get data from Distributed Cache file[s]");
		}
	}
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {

		String[] split = value.toString().split(",");
		String storeLocationName = storeDetailsMap.get(split[0]);
		context.write(new Text(storeLocationName), new Text(split[2]));
		
	}
}
