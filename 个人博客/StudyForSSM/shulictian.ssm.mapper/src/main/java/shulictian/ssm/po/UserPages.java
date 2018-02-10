package shulictian.ssm.po;

import java.util.List;

public class UserPages {

	private int nowPage;// 当前页数
	private int pageTopNum;// 每页的帖子数量
	private int topsNum;// 查询总数量

	private int pageNum;// 总页数
	private int startIndex;// 数据库开始索引

	private String pageTops;//每页显示的帖子

	private int start;// 首页
	private int end;// 尾页
	
	public UserPages() {
	}
	
	public UserPages(int nowPage, int pageTopNum, int topsNum) {
		
		this.nowPage = nowPage;
		this.pageTopNum = pageTopNum;
		this.topsNum = topsNum;
		
		if(topsNum%pageTopNum==0) {
			this.pageNum=topsNum/pageTopNum;
		}else {
			this.pageNum=topsNum/pageTopNum+1;
		}
		
		this.startIndex=(nowPage-1)*pageTopNum;
		
		this.start=1;
		this.end=5;
		if(this.pageNum<=5) {
			this.end=this.pageNum;
		}else {
			this.start=nowPage-2;
			this.end=nowPage+2;
			if(start<=0) {
				this.start=1;
				this.end=5;
			}
			if(end>this.pageNum) {
				this.end=this.pageNum;
				this.start=this.end-4;
			}
		}
		
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPageTopNum() {
		return pageTopNum;
	}

	public void setPageTopNum(int pageTopNum) {
		this.pageTopNum = pageTopNum;
	}

	public int getTopsNum() {
		return topsNum;
	}

	public void setTopsNum(int topsNum) {
		this.topsNum = topsNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getPageTops() {
		return pageTops;
	}

	public void setPageTops(String pageTops) {
		this.pageTops = pageTops;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
