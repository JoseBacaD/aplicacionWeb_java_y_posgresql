/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.bean.CatSaleDetailBean;
import com.mycompany.bean.ProcRefundBean;
import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.Item;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.entity.SaleDetail;
import com.mycompany.entity.SaleHeader;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.extended.EntityExt;
import com.mycompany.extended.SaleHeaderExt;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jerry
 */
public class ProcRefundController implements Serializable {
     static final long serialVersionUID = 1L;
     
     private Integer consecutive;
     private ProcRefundBean viewBean;
     private DateFormat dateFormat;
     private String strMercOrCash;
    public ProcRefundController() {
    
    }
    
     public ProcRefundController(ProcRefundBean viewBean) {
        this.viewBean = viewBean;
        dateFormat = new SimpleDateFormat("yy/MM/dd");
    }

   
    
    public Long processRefund()throws Exception{
       strMercOrCash = "";
       Long folio = generateFolio(viewBean.getRefundSaleHeader(), false);
       BigDecimal tempTax = viewBean.getCalculatedTax();
       BigDecimal tempRefund = viewBean.getCalculatedPriceDetail();
//       BigDecimal tempQuantity = viewBean.getQuantity();
       tempTax = tempTax.multiply(new BigDecimal("-1"));
       tempRefund = tempRefund.multiply(new BigDecimal("-1"));
//       tempQuantity = tempQuantity.multiply(new BigDecimal("-1"));

       viewBean.getSaleDetToSave().setStandardCodeItem(viewBean.getRowSelected().getStandardCodeItem());
//       viewBean.getSaleDetToSave().setQuantity(tempQuantity);
       viewBean.getSaleDetToSave().setQuantity(viewBean.getQuantity());
       viewBean.getSaleDetToSave().setPriceDetail(tempRefund);
       viewBean.getSaleDetToSave().setCreationDate(Utility.getDate());
       viewBean.getSaleDetToSave().setSaleDate(Utility.getDate());
       viewBean.getSaleDetToSave().setItemTax(tempTax);
        
        
       viewBean.getRefundSaleHeader().getFolioCollection().add(viewBean.getSaleDetToSave());
       viewBean.getRefundSaleHeader().setCashRefund(viewBean.getSaleDetToSave().getPriceDetail());
       viewBean.getRefundSaleHeader().setTaxTotal(viewBean.getSaleDetToSave().getItemTax());
 
       viewBean.getRefundSaleHeader().setFolio(folio);
       viewBean.getRefundSaleHeader().setConsecutive(consecutive);
       viewBean.getRefundSaleHeader().setRateSale(viewBean.getAppConfig().getRateSale());
       viewBean.getRefundSaleHeader().setSaleDate(Utility.getDate());
       viewBean.getRefundSaleHeader().setCreationDate(Utility.getDate());
       viewBean.getRefundSaleHeader().setIdDivition(viewBean.getAppConfig().getIdDivition());
       viewBean.getRefundSaleHeader().setIdSubdivition(viewBean.getAppConfig().getIdSubdivition());
       viewBean.getRefundSaleHeader().setModifiedBy(viewBean.getUserApp().getUserAlias());
       viewBean.getRefundSaleHeader().setIsWithdraw(false);
       viewBean.getRefundSaleHeader().setIsrefund(true);
       viewBean.getRefundSaleHeader().setCashWithdraw(BigDecimal.ZERO);
       viewBean.getRefundSaleHeader().setTotalSale(viewBean.getSaleDetToSave().getPriceDetail());
       viewBean.getRefundSaleHeader().setCashPayment(BigDecimal.ZERO);
       viewBean.getRefundSaleHeader().setCardPayment(BigDecimal.ZERO);
       viewBean.getRefundSaleHeader().setOtherPayment(BigDecimal.ZERO);
       viewBean.getRefundSaleHeader().setHasISRInvoice(false);
       viewBean.getRefundSaleHeader().setDebitPayment(BigDecimal.ZERO);
       if (viewBean.isIsMerchandiseChecked()){
           strMercOrCash ="-Mercancía-";
       }else{
           strMercOrCash = "-Efectivo-";
       }
       
       viewBean.getRefundSaleHeader().
               setComment("Devolución de venta No. "+ viewBean.getRowSelected().getFolio().getFolio()+ " "+ strMercOrCash);
      
       viewBean.getRefundSaleHeader().setTaxTotal(tempTax);
       viewBean.getRefundSaleHeader().setCashRefund(tempRefund);
       viewBean.getSaleDetToSave().setFolio(viewBean.getRefundSaleHeader());
   
       
       try{
            BasicDAO.basicInsert(viewBean.getRefundSaleHeader());
            System.out.println("folio "+folio);
            Item updateItem = viewBean.getRefundSaleHeader().getFolioCollection().get(0).getStandardCodeItem();
            //actualiza stock en base de datos
            BasicDAO.basicMerge(addStock(updateItem,viewBean.getQuantity())); 
                            
               
        return folio;
       }catch(Exception ex){
           ex.printStackTrace();             
           throw ex;
           
       }
    }
    
    public Item addStock(Item itemRefund, BigDecimal pieces){
        BigDecimal currentStock = itemRefund.getItemStock();
        currentStock = currentStock.add(pieces);
        itemRefund.setItemStock(currentStock);
        itemRefund.setLastModDate(Utility.getDate());
        return itemRefund;
    }
    
    public Long generateFolio(SaleHeader saleHeader, boolean isWithdraw){
       
       String strFolio = dateFormat.format(Utility.getDate());
       strFolio = strFolio.replace("/", "");
        try{
        int countSaleHeader;
        BasicDAO dao = new BasicDAO();
        consecutive = 0;
       
                       countSaleHeader = dao.resultSizeSP("SaleHeader.countSaleHeader",
                                                0,
                                                1,
                                                viewBean.getAppConfig().getIdDivition(),
                                                viewBean.getAppConfig().getIdSubdivition(),
                                                Utility.getDate(),
                                                Utility.getDate(),
                                                isWithdraw
            );
            
            consecutive = countSaleHeader + 1; 
            strFolio = strFolio + consecutive.toString();
//            System.out.println(strFolio);
                return Long.valueOf(strFolio);
            
            
        }catch(Exception ex){
            ex.printStackTrace();
            return  null;
        }
        
       
    }

     public List<SaleDetail> readSaleList(){
        List<SaleDetail> liSaleDetail;
       
       liSaleDetail = (List<SaleDetail>)(Object)BasicDAO.readSPReturnList("SaleDetail.findSaleDetailByFolio",
                                     viewBean.getAppConfig().getIdDivition(),
                                     viewBean.getAppConfig().getIdSubdivition(),
                                     viewBean.getSearchFolio());
       
       return liSaleDetail;
       
    }
     
     public boolean accessPermission(){
          for (MenuOptionPermission permission : viewBean.getUserApp().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.PROC_REFUND.getCodeMenuOption())){
                return true;
            }
            
        } //cambiar a false la linea de abajo una vez agregada la validacion de permisos
        return false;
        
     }
     
         
     public BigDecimal CalcRefund(BigDecimal pieces, BigDecimal unitPrice){
        BigDecimal total;
        BigDecimal discount;
        total = unitPrice.multiply(pieces);
        total = total.setScale(2,RoundingMode.HALF_UP);
        BigDecimal refundPercentage = viewBean.getAppConfig().getRefundPercentage();
        
        if(viewBean.isIsWithDiscountChecked()){
            discount = total.multiply(refundPercentage);
            discount = discount.setScale(2, RoundingMode.HALF_UP);
            total = total.subtract(discount);
        }
         
             return total;
        
    }
     
       public BigDecimal calcItemTax(BigDecimal itemPrice){
       BigDecimal priceWithoutTax = new BigDecimal(BigInteger.ZERO);
       BigDecimal itemTax = new BigDecimal(BigInteger.ZERO);
//       priceWithoutTax = priceWithoutTax.setScale(2,RoundingMode.HALF_UP);
//       itemTax = itemTax.setScale(2,RoundingMode.HALF_UP);
       
       BigDecimal tax = new BigDecimal(BigInteger.ONE);
//         tax = tax.setScale(2,RoundingMode.HALF_UP);
       tax = tax.add(viewBean.getAppConfig().getTax());
       
       priceWithoutTax = itemPrice.divide(tax,2,RoundingMode.HALF_UP);
       itemTax = itemPrice.subtract(priceWithoutTax);
       
        return itemTax;
       
    }
}
