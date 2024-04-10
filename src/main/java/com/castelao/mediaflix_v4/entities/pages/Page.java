package com.castelao.mediaflix_v4.entities.pages;

import java.util.List;

public class Page<T> {

    private List<T> items;
    private int total;
    private int skip;
    private int limit;
    
    public Page() {
        
    }
    
    public Page(List<T> items, int total, int skip, int limit) {
        this.items = items;
        this.total = total;
        this.skip = skip;
        this.limit = limit;
    }

    public List<T> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    public int getSkip() {
        return skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Page [items=" + items + ", total=" + total + ", skip=" + skip + ", limit=" + limit + "]";
    }
}