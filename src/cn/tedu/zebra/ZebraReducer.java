package cn.tedu.zebra;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import cn.tedu.domain.HttpAppHost;

public class ZebraReducer extends Reducer< Text, HttpAppHost, HttpAppHost, NullWritable> {

	public void reduce(Text _key, Iterable<HttpAppHost> values, Context context) throws IOException, InterruptedException {
		Iterator<HttpAppHost> it = values.iterator();
//		hasNext返回布尔类型true:代表迭代器中至少含有一个元素 false：迭代器为空
		if(it.hasNext()){
//			将迭代器中的第一个元素赋值给hah对象 该对象中包含了key部分不会发生变化的值
			HttpAppHost hah = it.next();
			while(it.hasNext()){
				HttpAppHost hah1 = it.next();
//				尝试次数累加
				hah.setAttempts(hah.getAttempts()+hah1.getAttempts());
//				接受次数累加
				hah.setAccepts(hah.getAccepts()+hah1.getAccepts());
//				上行流量
				hah.setTrafficUL(hah.getTrafficUL()+hah1.getTrafficUL());
//				下行流量
				hah.setTrafficDL(hah.getTrafficDL()+hah1.getTrafficDL());
//				重传上行流量
				hah.setRetranUL(hah.getRetranUL()+hah1.getRetranUL());
//				重传下行流量
				hah.setRetranDL(hah.getRetranDL()+hah1.getRetranDL());
//				延迟时间
				hah.setTransDelay(hah.getTransDelay()+hah1.getTransDelay());
			}
//			reduce处理后写出hah对象即可，value值为空
			context.write(hah, NullWritable.get());
		}
		
	}

}
