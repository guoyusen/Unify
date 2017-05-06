package com.bili.unify.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

public class HttpUtil {
	
	private HttpUtil() {}

	/**
	 * Get HTML from the Internet
	 * @param urlAddress
	 * @return The content
	 */
	public static String getHtmlContentByURL(String urlAddress) {
		String result = null;
		try {
			URL url = new URL(urlAddress);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Content-Type", "text/html; charset=utf-8");
	        urlConnection.setRequestProperty("User-Agent", "Charles/3.11.4");
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8")); // 获取输入流  
	        String line = null;  
	        StringBuilder sb = new StringBuilder();  
	        while ((line = br.readLine()) != null) {  
	            sb.append(line + "\n");  
	        }  
	        br.close();  
	        
	        result = sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}
		
		return result;
	}
	
	/**
	 * Download file from the Internet
	 * @param urlAddress
	 * @param savePath must contain file's name and format
	 */
	public static void downloadFileByUrlAndSavePath(String urlAddress, String savePath) {
		int byteread = 0;
        try {
        	URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();
            InputStream inStream = urlConnection.getInputStream();
            FileOutputStream fs = new FileOutputStream(savePath);

            byte[] buffer = new byte[1024];
            while ((byteread = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
            }
            fs.close();
        } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Mock the request of POST
	 * @param urlAddress
	 * @param requestBody
	 * @param headerMap
	 */
	public static String mockPostRequestBody(String urlAddress, String requestBody, Map<String, String> headerMap) {
		OutputStreamWriter outputStreamWriter = null;
		BufferedReader bufferedReader = null;
		String response = "";
		
		try {
			URL url = new URL(urlAddress);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.setRequestProperty("connection", "keep-alive");
			if(headerMap != null && !headerMap.isEmpty()) {
				Set<String> keys = headerMap.keySet();
				for(String key : keys) {
					httpURLConnection.setRequestProperty(key, headerMap.get(key));
				}
			}
			
			httpURLConnection.setUseCaches(false);// 设置不要缓存
			httpURLConnection.setInstanceFollowRedirects(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.connect();
			
			outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
			outputStreamWriter.write(requestBody);
			outputStreamWriter.flush();
			// 读取响应
			bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String lines = null;
			while ((lines = bufferedReader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				response += lines;
			}
			bufferedReader.close();
			httpURLConnection.disconnect();
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		finally {
			try {
				if (outputStreamWriter != null) {
					outputStreamWriter.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return response;
	}
	
}
