package com.poscodx.mysite.repository;

public class BoardPage {
	private Long pageSize;
	private Long itemSize;
	private Long itemLength;
	private Long startNo;
	private Long currentPage;
	private Long startPage;
	private Long lastPage;
	private Long prevStartPage;
	private Long nextStartPage;
	private Long prevPage;
	private Long nextPage;

	public BoardPage(Long currentPage, Long pageSize, Long itemSize, Long itemLength) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.itemSize = itemSize;
		this.itemLength = itemLength;
		this.pageSettings();
	}

	public void pageSettings() {
		Long firstPage = 1L;

		setCurrentPage(currentPage);
		setStartNo(itemLength - itemSize * (currentPage - firstPage));

		setStartPage(currentPage - (currentPage - firstPage) % pageSize);
		setLastPage((long) (Math.floor((itemLength - firstPage) / itemSize) + firstPage));

		setPrevStartPage((startPage == firstPage) ? firstPage : (startPage - pageSize));
		setNextStartPage((startPage + pageSize) > lastPage ? lastPage : (startPage + pageSize));

		setPrevPage((currentPage == firstPage) ? firstPage : currentPage - firstPage);
		setNextPage((currentPage + firstPage > lastPage) ? currentPage : currentPage + firstPage);

	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public Long getStartPage() {
		return startPage;
	}

	public void setStartPage(Long startPage) {
		this.startPage = startPage;
	}

	public Long getLastPage() {
		return lastPage;
	}

	public void setLastPage(Long lastPage) {
		this.lastPage = lastPage;
	}

	public Long getPrevStartPage() {
		return prevStartPage;
	}

	public void setPrevStartPage(Long prevStartPage) {
		this.prevStartPage = prevStartPage;
	}

	public Long getNextStartPage() {
		return nextStartPage;
	}

	public void setNextStartPage(Long nextStartPage) {
		this.nextStartPage = nextStartPage;
	}

	public Long getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(Long prevPage) {
		this.prevPage = prevPage;
	}

	public Long getNextPage() {
		return nextPage;
	}

	public void setNextPage(Long nextPage) {
		this.nextPage = nextPage;
	}

	public Long getStartNo() {
		return startNo;
	}

	public void setStartNo(Long startNo) {
		this.startNo = startNo;
	}

}
