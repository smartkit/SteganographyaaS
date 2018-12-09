package info.smartkit.saas.steganographyaas.utils;

import info.smartkit.saas.steganographyaas.settings.ServerSetting;

import java.util.regex.Pattern;



/**
 * The Class ImageInfoHelper.
 */
public class StegonagraphyInfoHelper {
	
	public static String getRemoteFaceUrl(String remoteImageUrl) {
		String fileName = remoteImageUrl.split(getUrlPrefix() )[1];
		String faceUrl = getUrlPrefix() + fileName.split(Pattern.quote("."))[0]+"_f."+fileName.split(Pattern.quote("."))[1];
		return faceUrl;
	}

	public static String getRemoteImageUrl(String fileName) {
		return getUrlPrefix() + fileName;
	}
	
	private static String getUrlPrefix()
	{
		return "http://" + ServerUtil.getInetAddress().getHostAddress() + ":" + ServerSetting.getPort()
		+ ServerSetting.getContextPath() + "/uploads/";
	}
}
