/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.extended;

/**
 *
 * @author bacajos
 */
import com.mycompany.entity.Item;
import com.mycompany.entity.SaleDetail;
import com.mycompany.entity.SaleHeader;
import com.mycompany.enums.ProcSaleEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Example;

public class ProcSaleExt extends EntityExt implements Serializable {
    private ItemExt itemSearch;
    private List<SaleDetailExt> liItemDetail;
    private SaleHeader saleHeader;
    private Integer quantity;
    
    public ProcSaleExt(){
        itemSearch = new ItemExt();
        liItemDetail = new ArrayList<>();
        saleHeader = new SaleHeader();
        quantity = ProcSaleEnum.MIN_QUANTITY.getMinQuantity();
    }

    @Override
    public void entity2Example() {
//        SE CREA EL obj Example DE LA ENTIDAD QUE SE VA A BUSCAR
//EN ESTE CASO SE VA A BUSCAR UN ITEM
        example = Example.create(itemSearch);
    }

    @Override
    public int getObjId(Object obj) {
        return 0;
     }

    @Override
    public String getExtClassName() {
        return itemSearch.getClass().getName();
    }

    public ItemExt getItemSearch() {
        return itemSearch;
    }

    public void setItemSearch(ItemExt itemSearch) {
        this.itemSearch = itemSearch;
    }

    public List<SaleDetailExt> getLiItemDetail() {
        return liItemDetail;
    }

    public void setLiItemDetail(List<SaleDetailExt> liItemDetail) {
        this.liItemDetail = liItemDetail;
    }

   

  

    public SaleHeader getSaleHeader() {
        return saleHeader;
    }

    public void setSaleHeader(SaleHeader saleHeader) {
        this.saleHeader = saleHeader;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

  
    
    
    
    
}
