package com.crowd.bean;

import java.util.List;

import com.github.pagehelper.Page;

public class PageInfo {
		// 总条数
		private long total;
		// 当前页
		private int page;
		// 返回的记录数
		private long records;
		//
		private List rows;

		public PageInfo(Page pageList) {
			this.total =pageList.getPages();
			this.rows = pageList;
			this.page = pageList.getPageNum();
			this.records = pageList.getTotal();
		}

		public long getTotal() {
			return total;
		}

		public void setTotal(long total) {
			this.total = total;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public List getRows() {
			return rows;
		}

		public void setRows(List rows) {
			this.rows = rows;
		}

		public long getRecords() {
			return records;
		}

		public void setRecords(long records) {
			this.records = records;
		}
}
