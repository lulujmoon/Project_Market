package com.mm.market.util;

public class ReviewPager {
	
	private Long page;
	private Long perPage;
	
	private Long startRow;
	
	private Long startNum;
	private Long lastNum;
	
	private boolean pre;
	private boolean next;
	
	private String reviewee;
	private String reviewer;
	private Long type;
	
	
	public void makeRow(Long perPage) {
		this.startRow = (this.getPage()-1) * perPage;
		
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
		
		Long curBlock = this.getPage()/perBlock;
		if(this.getPage()%perBlock!=0) {
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


	public Long getPage() {
		if(this.page == null || this.page == 0) {
			this.page = 1L;
		}
		return page;
	}


	public void setPage(Long page) {
		if(page == null || page == 0) {
			page = 1L;
		}
		this.page = page;
	}

	public Long getPerPage() {
		return perPage;
	}

	public void setPerPage(Long perPage) {
		this.perPage = perPage;
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
	
	public String getReviewee() {
		return reviewee;
	}

	public void setReviewee(String reviewee) {
		this.reviewee = reviewee;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}


}