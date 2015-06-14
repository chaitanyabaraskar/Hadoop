package com.maven.mapreduce.join;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JoinReducer extends Reducer<Text, Text, Text, Text>{
	
	@Override
	protected void reduce(Text arg0, Iterable<Text> arg1,
			Reducer<Text, Text, Text, Text>.Context arg2) throws IOException,
			InterruptedException {
		

		Set<String> visitorSet = new HashSet<String>();
		for (Text visitor : arg1) {
			visitorSet.add(visitor.toString());
		}
		
		String dept = null;
		for (String str : visitorSet) {
			if(str.contains("'"))
			{
				dept = str;
				visitorSet.remove(str);
				break;
			}
		}
		
		for (String string : visitorSet) {
			
			String[] emp = string.split(",");
			emp[1] = dept;
			arg2.write(arg0, new Text(emp[0]+emp[1]+emp[2]));
		}
		
		
		
	}

}