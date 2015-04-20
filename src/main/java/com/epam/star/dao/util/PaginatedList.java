package com.epam.star.dao.util;

import java.util.ArrayList;

public class PaginatedList<E> extends ArrayList<E> {
    private int pageNumber;
    private int rowsPerPage;
    private int totalRowsCount;


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public int getTotalRowsCount() {
        return totalRowsCount;
    }

    public void setTotalRowsCount(int totalRowsCount) {
        this.totalRowsCount = totalRowsCount;
    }

    public boolean isFirst() {
        return pageNumber == 1;
    }

    public boolean isLast() {
        return pageNumber == getPageCount();
    }

    public int getPageCount() {

        int first = totalRowsCount % rowsPerPage;
        int second = rowsPerPage - first;
        int third;
        if (second < rowsPerPage) third = totalRowsCount + second;
        else third = totalRowsCount;

        return (third / rowsPerPage);
    }
}
