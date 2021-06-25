package com.mm.market.util;

public class SocialPager {
	
	private Long curPage;
	private Long perPage;
	
	private Long startRow;
	
	//page
	private Long startNum;
	private Long lastNum;
	
	private boolean pre;
	private boolean next;
	
	//search
	private String keyword;
	private String search;
	private Long categoryCode;
	
	public void makeNum(Long totalCount) {
		int perBlock=5;

		Long totalPage = totalCount/this.getPerPage();
		if(totalCount%this.getPerPage() != 0) {
			totalPage++;
		}

		Long totalBlock = totalPage / perBlock;
		if(totalPage%perBlock !=0) {
			totalBlock++;
		}

		Long curBlock = this.getCurPage() / perBlock;
		if(this.curPage%perBlock !=0) {
			curBlock++;
		}
		

		this.startNum=(curBlock-1)*perBlock+1;
		this.lastNum=curBlock*perBlock;

		this.pre=true;
		this.next=true;
		if(curBlock==totalBlock) {
			lastNum = totalPage;
			this.next=false;
		}
		
		if(curBlock==1) {
			this.pre=false;
		}
		
		
		
	}
	
	public void makeRow() {

		this.startRow = (this.getCurPage()-1)*this.getPerPage(); 
	}

	public Long getCurPage() {
		if(this.curPage == null || this.curPage==0) {
			this.curPage=1L;
		}
		return curPage;
	}

	public void setCurPage(Long curPage) {
		if(curPage == null || curPage==0) {
			this.curPage=1L;
		}else {
			this.curPage = curPage;
		}
	}

	public Long getPerPage() {
		if(this.perPage == null || this.perPage==0) {
			this.perPage=10L;
		}
		return perPage;
	}

	public void setPerPage(Long perPage) {
		if(perPage==null || perPage==0) {
			this.perPage=10L;
		}else {
			this.perPage = perPage;
		}
	}

	public Long getStartRow() {
		return startRow;
	}

	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}

	public Long getStartNum() {
		return startNum;
	}

	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}

	public Long getLastNum() {
		return lastNum;
	}

	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
	}

	public boolean isPre() {
		return pre;
	}

	public void setPre(boolean pre) {
		this.pre = pre;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public String getKeyword() {
		if(this.keyword==null) {
			this.keyword="";
		}
		
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearch() {
		if(this.search==null) {
			this.search="";
		}
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Long getCategoryCode() {
		if(this.categoryCode == null) {
			this.categoryCode = 0L;
		}
		return categoryCode;
	}

	public void setCategoryCode(Long categoryCode) {
		if(categoryCode == null) {
			categoryCode = 0L;
		}
		this.categoryCode = categoryCode;
	}
	
	
	

}