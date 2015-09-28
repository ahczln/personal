package com.pactera.common.util;

/**
 * 分页类
 * @author Administrator
 *
 */
public class PageMore
{
	private String pageSize;
	
	private String curPage;
	
	private String totalPage;
	
	private String prePage;
	
	private String aftPage;
	
	private String totalCount;
	
	public String getPrePage() {
		return prePage;
	}

	public void setPrePage(String prePage) {
		this.prePage = prePage;
	}

	public String getAftPage() {
		return aftPage;
	}

	public void setAftPage(String aftPage) {
		this.aftPage = aftPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	
	public void initPage()
	{
		
		int c = Integer.valueOf(this.totalCount)%Integer.valueOf(this.pageSize)==0?
				Integer.valueOf(this.totalCount)/Integer.valueOf(this.pageSize):Integer.valueOf(this.totalCount)/Integer.valueOf(this.pageSize)+1;
		this.totalPage = c+"";
		if("1".equals(this.curPage))
		{
			this.prePage = "1";
		}
		else
		{
			this.prePage = (new Integer(this.curPage) - 1)+"";
		}
		if(this.totalPage.equals(this.curPage))
		{
			this.aftPage = this.totalPage;
		}
		else
		{
			this.aftPage =  (new Integer(this.curPage) + 1)+"";
		}
		
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
