package com.mm.market.util;

public class Pager {
	
	private Long curPage;
	
	private Long startRow;
	
	private Long startNum;
	private Long lastNum;
	
	private boolean pre;
	private boolean next;
	
	private String kind;
	private String search;
	
	
	public void makeRow(Long perPage) {
		
		this.startRow = (this.getCurPage()-1) * perPage;
		
	}
	
	public void makeNum(Long totalCount, Long perPage, Long perBlock) {
		
		Long totalPage = totalCount / perPage;
		if(totalCount%perPage!=0) {
			totalPage++;
		}
		
		Long totalBlock = totalPage / perBlock;
		if(totalPage%perBlock!=0) {
			totalBlock++;
		}
		
		Long curBlock = this.getCurPage()/perBlock;
		if(this.getCurPage()%perBlock!=0) {
			curBlock++;
		}
		
		this.startNum = (curBlock-1)*perBlock+1;
		this.lastNum = curBlock*perBlock;
		
		this.pre = this.next = true;
		if(curBlock == totalBlock) {
			lastNum = totalPage;
			this.next = false;
		}
		if(curBlock == 1) {
			this.pre = false;
		}
		
	}


	public Long getCurPage() {
		if(this.curPage == null || this.curPage == 0) {
			this.curPage = 1L;
		}
		return curPage;
	}


	public void setCurPage(Long curPage) {
		if(curPage == null || curPage == 0) {
			curPage = 1L;
		}
		this.curPage = curPage;
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


	public String getKind() {
		return kind;
	}


	public void setKind(String kind) {
		this.kind = kind;
	}


	public String getSearch() {
		if(this.search == null) {
			this.search = "";
		}
		return search;
	}


	public void setSearch(String search) {
		if(search == null) {
			search = "";
		}		
		this.search = search;
	}
	
	

}
