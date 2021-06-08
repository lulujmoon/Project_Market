package com.mm.market.util;

public class ProductPager {
	
	private Long page;
	private Long perPage;
	
	private Long startRow;
	
	private Long startNum;
	private Long lastNum;
	
	private boolean pre;
	private boolean next;
		
	private Long categoryCode;
	private Long locationCode;
	private String keyword;
	private String order;
	

	public Long getPerPage() {
		return perPage;
	}

	public void setPerPage(Long perPage) {
		this.perPage = perPage;
	}

	public void makeRow(Long perPage) {
		this.startRow = (this.getpage()-1) * perPage;
		
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
		
		Long curBlock = this.getpage()/perBlock;
		if(this.getpage()%perBlock!=0) {
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


	public Long getpage() {
		if(this.page == null || this.page == 0) {
			this.page = 1L;
		}
		return page;
	}


	public void setpage(Long page) {
		if(page == null || page == 0) {
			page = 1L;
		}
		this.page = page;
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

	public Long getCategoryCode() {
		if(categoryCode == null) {
			categoryCode = 0L;
		}
		return categoryCode;
	}

	public void setCategoryCode(Long categoryCode) {
		if(categoryCode == null) {
			categoryCode = 0L;
		}
		this.categoryCode = categoryCode;
	}

	public Long getLocationCode() {
		if(locationCode == null) {
			locationCode = 0L;
		}
		return locationCode;
	}

	public void setLocationCode(Long locationCode) {
		if(locationCode == null) {
			locationCode = 0L;
		}		
		this.locationCode = locationCode;
	}

	public String getkeyword() {
		if(keyword == null) {
			keyword = "";
		}
		return keyword;
	}

	public void setkeyword(String keyword) {
		if(keyword == null) {
			keyword = "";
		}
		this.keyword = keyword;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}


	
	
	

}