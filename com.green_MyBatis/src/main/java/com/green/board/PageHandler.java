package com.green.board;
// 페이징을 위한 계산식을 가지고 있는 클래스
public class PageHandler {
	
	// 1. 기본 변수
	private int totalCnt; // 전체 게시글 수
	private int pageNum; // 현재 페이지 번호
	private int pageSize; // 한 페이지에 보여줄 레코드(=행) 개수
	private int pageBlock = 5; // 페이지 번호 묶음 (1 ~ 3)
	
	// 2. DB 조회용 변수
	// Limit 1(startRow), 5(pageSize) => 1부터 5개
	private int startRow; // DB의 시작 위치
	private int endRow; // 가져올 게시글 수 = pageSize
	
	// 3. pageBlock 네이게이션용 변수 [1][2][3] / [4][5][6]
	private int totalPage; // 전체 페이지 수
	private int startPage; // 블록페이지 시작 번호 [1]
	private int endPage; // 블록페이지의 마지막 번호 [3]
	
	private boolean prev; // ◀ 이전 
	private boolean next; // ▶ 다음
	
	public PageHandler(int totalCnt, int pageNum, int pageSize) {
		this.totalCnt = totalCnt;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		
		// 페이지 계산 메서드 호출
		calcPaging();
	}
	
	// 페이지 계산 메서드
	private void calcPaging() {
		// ex) totalCnt: 23, pageSize: 5, pageNum: 3
		// 1. 전체 페이지 수
		// 명시적 형변환을 사용 하여 소숫점을 계산하고 다시 정수화
		// double 해야하는 이유 : 23/5 = 4.6 => 올림해서 3페이지로 부여
		// Math.ceil() : 소수점을 올림해서 정수로 출력
		// 게시글의 개수는 증가/감소 한다
		totalPage = (int)Math.ceil(totalCnt / (double)pageSize);
		// 실수 부분 없이 계산하려면
		// totalPage = (totalCnt + pageSize - 1) / pageSize; 
		
		// 2. DB 조회 범위
		// 1페이지 -> 0 ~ 4까지 5개
		// 2페이지 -> 5부터 9까지 5개
		// 3페이지 -> 10부터 14까지 5개
		startRow = (pageNum - 1) * pageSize; // 해당 페이지 시작 행
		endRow = pageSize; // 가져올 게시글 수
		
		// 3. 페이지 블록
		startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
		endPage = startPage + (pageBlock - 1);
		// 페이지 수가 게시글에 들어 맞지 않을 경우, 필요 이상의 페이지 출력을 막는 조건문
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		// 4. 이전/다음 버튼 여부
		prev = startPage > 1;	
//		if(startPage>1) {
//			prev = true;
//		}else prev = false;
		
		next = endPage < totalPage;
	}

	
	// getter  & setter
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

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
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
}
