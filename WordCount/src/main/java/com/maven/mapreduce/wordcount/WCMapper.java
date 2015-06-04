package com.maven.mapreduce.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class WCMapper extends Mapper<LongWritable, Text, Text, Text> {

	
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
	
		String line = value.toString();
		String[] word = line.split(" ");
		context.write(new Text(word[0]),new Text(word[1]));
	}
	
	/*protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		for (String word : line.split(" ")) {
			context.write(new Text(word), new IntWritable(1));
		}
	}*/
}
