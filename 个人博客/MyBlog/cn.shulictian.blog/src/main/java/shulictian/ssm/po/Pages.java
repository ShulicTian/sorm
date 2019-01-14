package shulictian.ssm.po;

public class Pages {

	private int nowPage;// 当前页数
	private int nowPageNum;// 每页的数量
	private int allNum;// 查询总数量
	private int pageNum;// 总页数
	private int startIndex;// 数据库开始索引
	private String pageJSON;//每页显示的帖子
	private int start;// 首页
	private int end;// 尾页
	
	public Pages() {
	}
	
	public Pages(int nowPage, int nowPageNum, int allNum) {
		
		this.nowPage = nowPage;
		this.nowPageNum = nowPageNum;
		this.allNum = allNum;
		
		if(allNum%nowPageNum==0) {
			this.pageNum=allNum/nowPageNum;
		}else {
			this.pageNum=allNum/nowPageNum+1;
		}
		
		this.startIndex=(nowPage-1)*nowPageNum;
		
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

	public int getNowPageNum() {
		return nowPageNum;
	}

	public void setNowPageNum(int nowPageNum) {
		this.nowPageNum = nowPageNum;
	}

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
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

	public String getPageJSON() {
		return pageJSON;
	}

	public void setPageJSON(String pageJSON) {
		this.pageJSON = pageJSON;
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
