package cn.tedu.zebra;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import cn.tedu.domain.HttpAppHost;

public class ZebraDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "zebra");
		job.setJarByClass(cn.tedu.zebra.ZebraDriver.class);
		job.setMapperClass(cn.tedu.zebra.ZebraMapper.class);
		job.setReducerClass(cn.tedu.zebra.ZebraReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(HttpAppHost.class);
		

		job.setOutputKeyClass(HttpAppHost.class);
		job.setOutputValueClass(NullWritable.class);

		FileInputFormat.setInputPaths(job, new Path("hdfs://tedu:9000/zebraFile/103_20150615143630_00_00_000_2.csv"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://tedu:9000/zebraResult"));

		if (!job.waitForCompletion(true))
			return;
	}

}
