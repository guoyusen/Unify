package com.bili.unify.main.hjlistening;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.bili.unify.model.DownloadHJListening;
import com.bili.unify.model.DownloadHJListeningExample;
import com.bili.unify.model.mapper.DownloadHJListeningMapper;
import com.bili.unify.utils.HttpUtil;

public class HJListeningProcessor {

	private static DownloadHJListeningMapper downloadHJListeningMapper;
	private static final String url = "http://ting.hujiang.com/ajax.do";
	private static final String requestBody_1 = "{\"classMethod\": \"RecommandArticle.SelectTopicList\",\"param\": [7,422,\"1\"],\"pageName\": \"null\"}";
	private static final String requestBody_2 = "{\"classMethod\": \"RecommandArticle.SelectTopicList\",\"param\": [7,403,\"2\"],\"pageName\": \"null\"}";

	public static void downloadMP3(ApplicationContext applicationContext) throws MalformedURLException {
		downloadHJListeningMapper = applicationContext.getBean(DownloadHJListeningMapper.class);
		List<DownloadHJListening> downloadHJListenings = downloadHJListeningMapper.selectByExample(new DownloadHJListeningExample());
		for(DownloadHJListening downloadHJListening : downloadHJListenings) {
			HttpUtil.downloadFileByUrlAndSavePath(downloadHJListening.getDownload_url(), "F:/HJListening/" + downloadHJListening.getFile_name() + ".mp3");
		}
	}
	
	public static void updateDownloadUrl(ApplicationContext applicationContext) throws Exception {
		downloadHJListeningMapper = applicationContext.getBean(DownloadHJListeningMapper.class);
		List<DownloadHJListening> downloadHJListenings = downloadHJListeningMapper.selectByExample(new DownloadHJListeningExample());
		
		for(DownloadHJListening downloadHJListening : downloadHJListenings) {
			downloadHJListening.setDownload_url(getDownLoadUrl(downloadHJListening.getDownload_url()));
			downloadHJListeningMapper.updateByPrimaryKey(downloadHJListening);
		}
	}
	
	public static String getDownLoadUrl(String urlAddress) throws Exception {  
        String result = HttpUtil.getHtmlContentByURL(urlAddress);
        if (result == null) {
        	return "";
        }
        result = result.substring(result.indexOf("HiddenField_mp3file") + 28).substring(0, result.indexOf("\""));
        return result;
    }  
	
	public static void saveHJListeningList(ApplicationContext applicationContext) {
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
			
			if (subIndex == 8) {
				subIndex = 1;
				pageIndex++;
			}
		}
	}

	private static List<HJListeningBO> getHJListeningBOList() {
		List<HJListeningBO> listeningBOs = new ArrayList<HJListeningBO>();
		listeningBOs.addAll(JSON.parseArray(postRequestBody(url, requestBody_1), HJListeningBO.class));
		listeningBOs.addAll(JSON.parseArray(postRequestBody(url, requestBody_2), HJListeningBO.class));
		return listeningBOs;
	}

	private static String postRequestBody(String url, String requestBody) {
		String result = HttpUtil.mockPostRequestBody(url, requestBody, null);
		return result.substring(result.indexOf(":") + 1, result.lastIndexOf("}"));
	}
}
