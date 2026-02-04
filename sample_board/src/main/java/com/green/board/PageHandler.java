package com.green.board;

public class PageHandler {
	// 1. 기본 변수
	private int totalCnt;        // 전체 게시글 수
	private int pageNum;            // 현재 페이지 번호
	private int pageSize;        // 한 페이지에 보여줄 레코드(행) 개수
	private int pageBlock;       // 페이지 번호 묶음 크기 (ex. 1~3)

	// 2. DB 조회용 변수
	private int startRow;        // DB 조회 시작 위치
	private int endRow;

	// 3. pageBlock 네비게이션용 변수
	private int totalPage;       // 전체 페이지 수
	private int startPage;       // 블록 시작 페이지 번호
	private int endPage;         // 블록 마지막 페이지 번호

	private boolean prev; // 이전
	private boolean next; // 다음
	
	public PageHandler(int totalCnt, int pageNum, int pageSize, int pageBlock) {
		this.totalCnt = totalCnt;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.pageBlock = pageBlock;
		
		calcPaging();
	}
	
	// 페이지 계산 메서드
	private void calcPaging() {
		// 전체 페이지 수
		totalPage = (int)Math.ceil(totalCnt / (double)pageSize);
		
		// DB 조회 범위 => 페이지 블럭별 조회 범위
		startRow = (pageNum-1)*pageSize;
		endRow = pageSize;
		
		// 페이지 블록 번호
		startPage = ((pageNum-1)/pageBlock)*pageBlock+1;
		endPage = startPage+(pageBlock-1);
		if(endPage>totalPage) endPage=totalPage;
		
		// 이전/다음 버튼 논리값
		prev=startPage>1;
		next=endPage<totalPage;
	}
	
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getPageNum() {
	    return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
	
}
