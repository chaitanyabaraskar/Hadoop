package com.maven.mapreduce.uniqueusers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WCReducer extends Reducer<Text, Text, Text, IntWritable> {

	
	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1,
			Reducer<Text, Text, Text, IntWritable>.Context arg2)
			throws IOException, InterruptedException {
		
		Set<String> visitorSet = new HashSet<String>();

		for (Text visitor : arg1) {
			visitorSet.add(visitor.toString());
		}
		// unique
		IntWritable uniqueUsers = new IntWritable(visitorSet.size());
		arg2.write(arg0, uniqueUsers);
	}
	
	
	/*@Override
	protected void reduce(Text arg0, Iterable<Text> arg1,
			Reducer<Text, Text, Text, Text>.Context arg2) throws IOException,
			InterruptedException {
		
		java.util.Set result = new java.util.HashSet(java.util.Arrays.asList(arg1.toString()));
		System.out.println(result);
		arg2.write(arg0, new Text(arg1.toString()));
	}*/
}