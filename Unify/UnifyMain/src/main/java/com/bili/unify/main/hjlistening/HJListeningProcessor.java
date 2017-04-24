package com.bili.unify.main.hjlistening;

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
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.bili.unify.model.DownloadHJListening;
import com.bili.unify.model.DownloadHJListeningExample;
import com.bili.unify.model.mapper.DownloadHJListeningMapper;

public class HJListeningProcessor {

	private static DownloadHJListeningMapper downloadHJListeningMapper;
	private static final String url = "http://ting.hujiang.com/ajax.do";
	private static final String requestBody_1 = "{\"classMethod\": \"RecommandArticle.SelectTopicList\",\"param\": [7,422,\"1\"],\"pageName\": \"null\"}";
	private static final String requestBody_2 = "{\"classMethod\": \"RecommandArticle.SelectTopicList\",\"param\": [7,403,\"2\"],\"pageName\": \"null\"}";

	public static void downloadMP3(ApplicationContext applicationContext) throws MalformedURLException {
		downloadHJListeningMapper = applicationContext.getBean(DownloadHJListeningMapper.class);
		List<DownloadHJListening> downloadHJListenings = downloadHJListeningMapper.selectByExample(new DownloadHJListeningExample());
		for(DownloadHJListening downloadHJListening : downloadHJListenings) {
			download(downloadHJListening.getFile_name() + ".mp3", downloadHJListening.getDownload_url());
			System.out.println(downloadHJListening.getFile_name() + ".mp3");
		}
	}
	
	public static void download(String fileName, String downloadURL) throws MalformedURLException {
		int bytesum = 0;
        int byteread = 0;

        URL url = new URL(downloadURL);

        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream("F:/HJListening/" + fileName);

            byte[] buffer = new byte[1204];
            int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void updateDownloadUrl(ApplicationContext applicationContext) throws Exception {
		String result = getDownLoadUrl("http://ting.hujiang.com/n2zhenti/15103062946");
		
		downloadHJListeningMapper = applicationContext.getBean(DownloadHJListeningMapper.class);
		List<DownloadHJListening> downloadHJListenings = downloadHJListeningMapper.selectByExample(new DownloadHJListeningExample());
		
		int i = 0;
		for(DownloadHJListening downloadHJListening : downloadHJListenings) {
			downloadHJListening.setDownload_url(getDownLoadUrl(downloadHJListening.getDownload_url()));
			downloadHJListeningMapper.updateByPrimaryKey(downloadHJListening);
			System.out.println("u" + i++);
		}
	}
	
	public static String getDownLoadUrl(String urlStr) throws Exception  
    {  
  
        URL url = new URL(urlStr);  
        URLConnection urlConnection = url.openConnection(); // 打开连接  text/html; charset=utf-8
        urlConnection.setRequestProperty("Content-Type", "text/html; charset=utf-8");
        urlConnection.setRequestProperty("User-Agent", "Charles/3.11.4");
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8")); // 获取输入流  
        String line = null;  
        StringBuilder sb = new StringBuilder();  
        while ((line = br.readLine()) != null)  
        {  
            sb.append(line + "\n");  
        }  
        br.close();  
        
        String result = sb.toString();
        result = result.substring(result.indexOf("HiddenField_mp3file") + 28);
		result = result.substring(0, result.indexOf("\""));
        return result;
    }  
	
	public static void saveHJListeningFromNet(ApplicationContext applicationContext) {

		downloadHJListeningMapper = applicationContext.getBean(DownloadHJListeningMapper.class);

		List<HJListeningBO> listeningBOs = getHJListeningBOList();
		int pageIndex = 1;
		int subIndex = 1;

		for (HJListeningBO hjListeningBO : listeningBOs) {
			DownloadHJListening downloadHJListening = new DownloadHJListening();
			downloadHJListening.setFile_name(pageIndex + "-" + subIndex);
			downloadHJListening.setDownload_url(
					"http://ting.hujiang.com/" + hjListeningBO.getTopicAlias() + "/" + hjListeningBO.getLongId());

			downloadHJListeningMapper.insert(downloadHJListening);
			
			subIndex++;

			System.out.println(pageIndex + "-" + subIndex);
			
			if (subIndex == 8) {
				subIndex = 1;
				pageIndex++;
			}
		}
	}

	private static List<HJListeningBO> getHJListeningBOList() {
		List<HJListeningBO> listeningBOs = new ArrayList<HJListeningBO>();
		listeningBOs.addAll(JSON.parseArray(getResultByRequest(requestBody_1, url, "POST"), HJListeningBO.class));
		listeningBOs.addAll(JSON.parseArray(getResultByRequest(requestBody_2, url, "POST"), HJListeningBO.class));
		
		return listeningBOs;
	}
	
	public static void main(String[] args) {
		getHJListeningBOList();
	}

	private static String getResultByRequest(String requestBody, String url, String type) {
		OutputStreamWriter out = null;
		BufferedReader reader = null;
		String response = "";
		try {
			URL httpUrl = null; // HTTP URL类 用这个类来创建连接
			// 创建URL
			httpUrl = new URL(url);
			// 建立连接
			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setRequestMethod(type);
			if(type.equals("POST")) {
				conn.setRequestProperty("Content-Type", "application/json");
			}
			conn.setRequestProperty("connection", "keep-alive");
			conn.setUseCaches(false);// 设置不要缓存
			conn.setInstanceFollowRedirects(true);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			if(type.equals("POST")) {
				// POST请求
				out = new OutputStreamWriter(conn.getOutputStream());
				out.write(requestBody);
				out.flush();
			}
			// 读取响应
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String lines;
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				response += lines;
			}
			reader.close();
			// 断开连接
			conn.disconnect();
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		if(type.equals("POST")) {
			return response.substring(response.indexOf(":") + 1, response.lastIndexOf("}"));
		} 
		return response;
	}
}
