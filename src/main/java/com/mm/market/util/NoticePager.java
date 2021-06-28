package com.mm.market.util;

public class NoticePager {
		
		private Long page;
		private Long perPage;
		
		private Long startRow;
		
		//page
		private Long startNum;
		private Long lastNum;
		
		private boolean pre;
		private boolean next;
		
		//search
		private String kind;
		private String search;
		
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

			Long curBlock = this.getPage() / perBlock;
			if(this.page%perBlock !=0) {
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

			this.startRow = (this.getPage()-1)*this.getPerPage(); 
		}

		public Long getPage() {
			if(this.page == null || this.page==0) {
				this.page=1L;
			}
			return page;
		}

		public void setPage(Long page) {
			if(page == null || page==0) {
				this.page=1L;
			}else {
				this.page = page;
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

		public String getKind() {
			if(this.kind==null) {
				this.kind="all";
			}
			
			return kind;
		}

		public void setKind(String kind) {
			this.kind = kind;
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

}

