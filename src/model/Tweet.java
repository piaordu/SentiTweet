package model;

import java.util.List;

public class Tweet {
	public String polarity;
	public String text;
	public List<String> tokenList;
	
	public Tweet(String text,String polarity){
		this.text = text;
		this.polarity = polarity;
	}
	
	public String getPolarity() {
		return polarity;
	}
	public void setPolarity(String polarity) {
		this.polarity = polarity;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getTokenList() {
		return tokenList;
	}
	public void setTokenList(List<String> tokenList) {
		this.tokenList = tokenList;
	}
}
