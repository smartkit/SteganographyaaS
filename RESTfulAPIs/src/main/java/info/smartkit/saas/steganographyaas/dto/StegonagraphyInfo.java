package info.smartkit.saas.steganographyaas.dto;

import lombok.ToString;

/**
 * The Class StegonagraphyInfo.
 */
@ToString
public class StegonagraphyInfo {
	
	private float time;
	private String text;
	private String uri;

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
