package com.maven.mapreduce.dynamicstate;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StateMapper extends Mapper<LongWritable, Text, Text, Text> {

	

	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {

		Configuration conf = context.getConfiguration();
		String comp = conf.get("states");
		String str = value.toString();
		String[] info = str.split(",");	
		
		if(info[1].equalsIgnoreCase(comp))
		{
			context.write(new Text(), value);
		}
	}
}
