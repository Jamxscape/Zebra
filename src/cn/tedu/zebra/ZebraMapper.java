package cn.tedu.zebra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import cn.tedu.domain.HttpAppHost;

public class ZebraMapper extends Mapper<LongWritable, Text, Text, HttpAppHost> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		//代表一行日志信息
		String line = ivalue.toString();
		//转义才能进行分隔
		String[] data = line.split("\\|");
		
		FileSplit fileSplit = (FileSplit) context.getInputSplit();
		//获取正在处理的数据文件的路径信息
		Path path = fileSplit.getPath();
		//获取正在处理的数据文件的文件名称
		String fileName = path.getName();
		
		String[] infos = fileName.split("_");
		String timeStr = infos[1];//20150615143630
		
		String reportTime = timeStr.substring(0,4)+"-"+timeStr.substring(4,6)+"-"+timeStr.substring(6,8)+" "+timeStr.substring(8,10)+":"+timeStr.substring(10,12)+":"+timeStr.substring(12,14);//20150615
		
		HttpAppHost hah = new HttpAppHost();
		
		//设置日期
		hah.setReportTime(reportTime);
		//设置小区id 如果小区id为null或者为"" 则默认设置为9个0
		if(data[16]==null || data[16].equals("")){
			hah.setCellId("000000000");
		}else{
			hah.setCellId(data[16]);
		}
		
		//设置应用大类
		int appType = Integer.parseInt(data[22]);
		hah.setAppType(appType);
		//设置应用小类
		int appSubType = Integer.parseInt(data[23]);
		hah.setAppSubType(appSubType);
		//设置用户IP
		hah.setUserIP(data[26]);
		//设置用户端口
		int userPort = Integer.parseInt(data[28]);
		hah.setUserPort(userPort);
		//设置服务器IP
		hah.setAppServerIP(data[30]);
		//设置服务器端口
		int appServerPort = Integer.parseInt(data[32]);
		hah.setAppServerPort(appServerPort);
		//设置域名
		hah.setHost(data[58]);
		
		int appTypeCode = Integer.parseInt(data[18]);
		
		//尝试次数 只有APPTypeCode==103为true时，才认为这是一次尝试连接
		if(appTypeCode == 103){
			hah.setAttempts(1);
		}else{
			hah.setAttempts(0);
		}
		//接受次数
		int statu = Integer.parseInt(data[54]);
		List<Integer> list = new ArrayList<Integer>();
		int[] status = {10,11,12,13,14,15,32,33,34,35,36,37,38,48,49,50,51,52,53,54,55,199,200,201,202, 203,204,205,206,302,304,306};
		for(int num : status){
			list.add(num);
		}
		if(appTypeCode==103 && list.contains(statu)){
			hah.setAccepts(1);
		}else{
			hah.setAccepts(0);
		}
		
		if(appTypeCode==103){
			//设置上行流量
			long trafficUL = Long.parseLong(data[33]);
			hah.setTrafficUL(trafficUL);
			
			//设置下行流量
			long trafficDL = Long.parseLong(data[34]);
			hah.setTrafficDL(trafficDL);
			
			//设置重传上行流量
			long retranUL = Long.parseLong(data[39]);
			hah.setRetranUL(retranUL);
			
			//设置重传下行流量
			long retranDL = Long.parseLong(data[40]);
			hah.setRetranDL(retranDL);
			//设置延迟时间
			long start = Long.parseLong(data[19]);
			long end = Long.parseLong(data[20]);
			hah.setTransDelay(end-start);
		}
//		拼接Key，reduce根据Key进行聚合
		String key = hah.getReportTime()+"|"+hah.getCellId()+"|"+hah.getAppType()+"|"+hah.getAppSubType()+"|"+hah.getUserIP()+"|"+hah.getUserPort()+"|"+hah.getAppServerIP()+"|"+hah.getAppServerPort()+"|"+hah.getHost();
		context.write(new Text(key), hah);
	}

}
