package com.maven.mapreduce.State;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StateReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1,
			Reducer<Text, Text, Text, Text>.Context context) throws IOException,
			InterruptedException {
		String str = arg1.toString();
		context.write(arg0, new Text( str));
	}
}
