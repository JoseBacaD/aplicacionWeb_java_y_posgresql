/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import com.mycompany.entity.SaleDetail;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jerry
 */
public class RecentSale implements Serializable {
    static final long serialVersionUID = 1L;
    
    private long folio;
    private List<RecentItem> itemRecentList ;
    private String recentSaleComment;

    public long getFolio() {
        return folio;
    }

    public void setFolio(long folio) {
        this.folio = folio;
    }

    public List<RecentItem> getItemRecentList() {
        return itemRecentList;
    }

    public void setItemRecentList(List<RecentItem> itemRecentList) {
        this.itemRecentList = itemRecentList;
    }

    public String getRecentSaleComment() {
        return recentSaleComment;
    }

    public void setRecentSaleComment(String recentSaleComment) {
        this.recentSaleComment = recentSaleComment;
    }

    
}
