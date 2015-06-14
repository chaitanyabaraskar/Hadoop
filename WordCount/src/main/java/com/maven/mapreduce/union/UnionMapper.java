package com.maven.mapreduce.union;

import java.io.IOException;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UnionMapper extends Mapper<LongWritable, Text, Text, Text> {

	

	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {

		String str = value.toString();
		String[] info = str.split(",");	
		
		if(info[1].equalsIgnoreCase("CA"))
		{
			context.write(new Text(), value);
		}
	}
}
