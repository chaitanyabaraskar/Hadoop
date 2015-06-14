package com.maven.mapreduce.join;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class JoinPartitioner extends Partitioner<Text, Text> {

	@Override
	public int getPartition(Text arg0, Text arg1, int arg2) {
		// TODO Auto-generated method stub

		String str = arg0.toString();
		String[] info = str.split(",");

		if (Integer.parseInt(info[1]) == 0)
			return 0;
		else {
			return 1;
		}
	}

}
