package cn.tedu.domain;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class HttpAppHost implements Writable{
	//表示时间
	private String reportTime;
	//小区id编号
	private String cellId;
	//应用大类
	private int appType;
	//应用小类
	private int appSubType;
	//用户IP
	private String userIP;
	//用户端口
	private int userPort;
	//服务器端ip地址
	private String appServerIP;
	//服务器端的端口
	private int appServerPort;
	//访问域名
	private String host;
	//尝试次数
	private int attempts;
	//接受次数
	private int accepts;
	//上行流量
	private long trafficUL;
	//下行流量
	private long trafficDL;
	//重传上行流量
	private long retranUL;
	//重传下行流量
	private long retranDL;
	//延迟时间
	private long transDelay;
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public String getCellId() {
		return cellId;
	}
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	public int getAppType() {
		return appType;
	}
	public void setAppType(int appType) {
		this.appType = appType;
	}
	public int getAppSubType() {
		return appSubType;
	}
	public void setAppSubType(int appSubType) {
		this.appSubType = appSubType;
	}
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	public int getUserPort() {
		return userPort;
	}
	public void setUserPort(int userPort) {
		this.userPort = userPort;
	}
	public String getAppServerIP() {
		return appServerIP;
	}
	public void setAppServerIP(String appServerIP) {
		this.appServerIP = appServerIP;
	}
	public int getAppServerPort() {
		return appServerPort;
	}
	public void setAppServerPort(int appServerPort) {
		this.appServerPort = appServerPort;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	public int getAccepts() {
		return accepts;
	}
	public void setAccepts(int accepts) {
		this.accepts = accepts;
	}
	public long getTrafficUL() {
		return trafficUL;
	}
	public void setTrafficUL(long trafficUL) {
		this.trafficUL = trafficUL;
	}
	public long getTrafficDL() {
		return trafficDL;
	}
	public void setTrafficDL(long trafficDL) {
		this.trafficDL = trafficDL;
	}
	public long getRetranUL() {
		return retranUL;
	}
	public void setRetranUL(long retranUL) {
		this.retranUL = retranUL;
	}
	public long getRetranDL() {
		return retranDL;
	}
	public void setRetranDL(long retranDL) {
		this.retranDL = retranDL;
	}
	public long getTransDelay() {
		return transDelay;
	}
	public void setTransDelay(long transDelay) {
		this.transDelay = transDelay;
	}
	//序列化
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(reportTime);
		out.writeUTF(cellId);
		out.writeInt(appType);
		out.writeInt(appSubType);
		out.writeUTF(userIP);
		out.writeInt(userPort);
		out.writeUTF(appServerIP);
		out.writeInt(appServerPort);
		out.writeUTF(host);
		out.writeInt(attempts);
		out.writeInt(accepts);
		out.writeLong(trafficUL);
		out.writeLong(trafficDL);
		out.writeLong(retranUL);
		out.writeLong(retranDL);
		out.writeLong(transDelay);
	}
	//反序列化
	@Override
	public void readFields(DataInput in) throws IOException {
		this.reportTime = in.readUTF();
		this.cellId = in.readUTF();
		this.appType = in.readInt();
		this.appSubType = in.readInt();
		this.userIP = in.readUTF();
		this.userPort = in.readInt();
		this.appServerIP = in.readUTF();
		this.appServerPort = in.readInt();
		this.host = in.readUTF();
		this.attempts = in.readInt();
		this.accepts = in.readInt();
		this.trafficUL = in.readLong();
		this.trafficDL = in.readLong();
		this.retranUL = in.readLong();
		this.retranDL = in.readLong();
		this.transDelay = in.readLong();
	}
	@Override
	public String toString() {
		return reportTime + "|" + cellId + "|" + appType + "|" + appSubType + "|" + userIP + "|" + userPort + "|"
				+ appServerIP + "|" + appServerPort + "|" + host + "|" + attempts + "|" + accepts + "|" + trafficUL
				+ "|" + trafficDL + "|" + retranUL + "|" + retranDL + "|" + transDelay;
	}

	
	
	
}
