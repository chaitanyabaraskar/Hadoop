package com.maven.mapreduce.join;

import java.io.IOException;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class JoinMapper1 extends Mapper<LongWritable, Text, Text, Text> {

	

	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {

		String str = value.toString();
		String[] info = str.split(",");	
		context.write(new Text(info[0]), new Text( "'"+info[1]));
	}
}
