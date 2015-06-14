package com.maven.mapreduce.union;


import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



public class UnionDriver extends Configured implements Tool {

	public int run(String[] args) throws Exception {

		if (args.length != 3) {
			System.out.printf(
					"Usage: %s [generic options] <input dir> <output dir>\n",
					getClass().getSimpleName());
			ToolRunner.printGenericCommandUsage(System.out);
			return -1;
		}

		
		Job job = new Job(getConf());
		
		
		job.setJarByClass(UnionDriver.class);
		job.setJobName(this.getClass().getName());

		job.setMapperClass(UnionMapper.class);
		
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileOutputFormat.setOutputPath(job, new Path(args[2]));
	    MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, UnionMapper.class);
	    MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, UnionMapper.class);
		
		if (job.waitForCompletion(true)) {
			return 0;
		}
		return 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new UnionDriver(), args);
		System.exit(exitCode);
	}

}
