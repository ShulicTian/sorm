package shulictian.ssm.po;

import java.util.ArrayList;
import java.util.List;

public class UserCustom extends User {
	
	private int zeroNum;
	private int oneNum;
	private int towNum;
	private int commentNum;
	private int lookNum;
	private int ranking;
	private int fansNum;
	private int atteNum;
	List<ProMsg> prosAddress = new ArrayList<>();
	private String gitHome;
	
	public String getGitHome() {
		return gitHome;
	}
	public void setGitHome(String gitHome) {
		this.gitHome = gitHome;
	}
	public int getZeroNum() {
		return zeroNum;
	}
	public void setZeroNum(int zeroNum) {
		this.zeroNum = zeroNum;
	}
	public int getOneNum() {
		return oneNum;
	}
	public void setOneNum(int oneNum) {
		this.oneNum = oneNum;
	}
	public int getTowNum() {
		return towNum;
	}
	public void setTowNum(int towNum) {
		this.towNum = towNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public int getLookNum() {
		return lookNum;
	}
	public void setLookNum(int lookNum) {
		this.lookNum = lookNum;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getFansNum() {
		return fansNum;
	}
	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}
	public int getAtteNum() {
		return atteNum;
	}
	public void setAtteNum(int atteNum) {
		this.atteNum = atteNum;
	}
	public List<ProMsg> getProsAddress() {
		return prosAddress;
	}
	public void setProsAddress(List<ProMsg> prosAddress) {
		this.prosAddress = prosAddress;
	}

}
