package com.maven.mapreduce.uniqueusers;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class WCMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException,
			InterruptedException {
		
		String[] line = value.toString().split(",");
		
		context.write(new Text(line[0]),new Text(line[1]));
	}
}
