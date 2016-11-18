package com.mindtree.joins.reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * @author M1035838
 *
 */
public class StoreReducer extends Reducer<Text, Text, Text, Text>{

	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		Long sales = new Long(0);
		StringBuilder storeNameAndSales = new StringBuilder("");
		for(Text value : values){
			String[] split = value.toString().split("_");
			if(split[0].equalsIgnoreCase("locations")){
				storeNameAndSales.append(split[1]);
				storeNameAndSales.append(" ");
			}else if(split[0].equalsIgnoreCase("sales")){
				sales += Long.parseLong(split[1]); 
			}
		}
		storeNameAndSales.append(sales);
		Text storeNameAndTotalSales = new Text(storeNameAndSales.toString());
		context.write(key, storeNameAndTotalSales);
		
	}
}
