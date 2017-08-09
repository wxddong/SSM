package com.atwhere.p2p.webservicerestful;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class ClientTest
{
	public static void main(String[] args) throws ClientProtocolException,IOException
	{

		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		
		
		
		HttpGet httpget = new HttpGet("http://localhost:8888/cxf_rest/student/get/6");
				
		
		HttpResponse response = httpclient.execute(httpget);
		
		
		
		HttpEntity entity = response.getEntity();
		
		
		
		
		InputStream inputStream = null;

		try
		{
			if (entity != null)
			{
				int length = 0;// 记录读取的长度
				int tmp = 0;
				byte[] data = new byte[1024];// 开辟一个空间
				inputStream = entity.getContent();
				while ((tmp = inputStream.read()) != -1)
				{
					data[length++] = (byte) tmp;; // 保存在字节数组中
				}
				System.out.println(new String(data, 0, length));
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			if (inputStream != null)	inputStream.close();
			if (httpclient != null)		httpclient.close();
		}
	}
}
