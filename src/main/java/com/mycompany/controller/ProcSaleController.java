/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;
import com.mycompany.bean.ProcSaleBean;
import com.mycompany.dao.BasicDAO;
import com.mycompany.entity.FrecuentSaleItem;
import com.mycompany.entity.Item;
import com.mycompany.entity.MenuOptionPermission;
import com.mycompany.entity.SaleDetail;
import com.mycompany.entity.SaleHeader;
import com.mycompany.enums.MenuOptionEnum;
import com.mycompany.extended.FrecSaleItemExt;
import com.mycompany.extended.ItemExt;
import com.mycompany.extended.SaleHeaderExt;
import com.mycompany.lazy.QuickItemLazySearch;
import com.mycompany.util.Utility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author bacajos
 */
public class ProcSaleController implements Serializable {
    static final long serialVersionUID = 1L;
    ProcSaleBean viewBean;
    DecimalFormat df = new DecimalFormat("#.##");
    private Integer consecutive;
    private DateFormat dateFormat;
    private String strToday;
    Calendar today;
    Calendar creationDate;
    private DateFormat creationDateFormat;
    private List <FrecuentSaleItem> liFrecItem;
    private List <FrecuentSaleItem> liPromoItem;
    private List <FrecuentSaleItem> liExtraItem;
    private List <FrecuentSaleItem> liOtherItem;
    private List<FrecuentSaleItem> liFullFreclist;
       

    public ProcSaleController() {
        dateFormat = new SimpleDateFormat("yy/MM/dd");
        today  = Calendar.getInstance();
        strToday = dateFormat.format(today.getTime());
        creationDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
        creationDate  = Calendar.getInstance();
        dateFormat.format(creationDate.getTime());
      
    }
    
    public ProcSaleController(ProcSaleBean viewBean){
        this.viewBean = viewBean;
         dateFormat = new SimpleDateFormat("yy/MM/dd");
        today  = Calendar.getInstance();
        strToday = dateFormat.format(today.getTime());
        creationDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
        creationDate  = Calendar.getInstance();
        dateFormat.format(creationDate.getTime());
    }
    
    public Long processSale(SaleHeaderExt saleHeadExt)throws Exception{
     
       Long folio = generateFolio(saleHeadExt.getSaleHeadEntity(), false);
       saleHeadExt.getSaleHeadEntity().setFolio(folio);
       saleHeadExt.getSaleHeadEntity().setConsecutive(consecutive);
       saleHeadExt.getSaleHeadEntity().setRateSale(viewBean.getAppConfig().getRateSale());
       saleHeadExt.getSaleHeadEntity().setSaleDate(today.getTime());
       saleHeadExt.getSaleHeadEntity().setCreationDate(today.getTime());
       saleHeadExt.getSaleHeadEntity().setIdDivition(viewBean.getAppConfig().getIdDivition());
       saleHeadExt.getSaleHeadEntity().setIdSubdivition(viewBean.getAppConfig().getIdSubdivition());
       saleHeadExt.getSaleHeadEntity().setModifiedBy(viewBean.getUser().getUserAlias());
       saleHeadExt.getSaleHeadEntity().setIsWithdraw(false);
       saleHeadExt.getSaleHeadEntity().setIsrefund(false);
       saleHeadExt.getSaleHeadEntity().setCashWithdraw(BigDecimal.ZERO);
       saleHeadExt.getSaleHeadEntity().setCashRefund(BigDecimal.ZERO);
       BigDecimal tempTotal = saleHeadExt.getSaleHeadEntity().getTotalSale();
       BigDecimal tempCash = saleHeadExt.getSaleHeadEntity().getCashPayment();
       BigDecimal tempCard = saleHeadExt.getSaleHeadEntity().getCardPayment();
       BigDecimal tempDebit = saleHeadExt.getSaleHeadEntity().getDebitPayment();
       BigDecimal tempOther = saleHeadExt.getSaleHeadEntity().getOtherPayment();
       BigDecimal temptotalPayment = tempCash.add(tempCard).add(tempOther).add(tempDebit);
       saleHeadExt.getSaleHeadEntity().setCashPayment(tempTotal.subtract(tempOther).subtract(tempCard).subtract(tempDebit));     
       saleHeadExt.entity2Example();
       
       try{
             BasicDAO.basicInsert(saleHeadExt.getSaleHeadEntity());
             List<SaleDetail> liDetail = viewBean.getWrapper().getSaleHeader().getSaleHeadEntity().getFolioCollection();
             
             for (SaleDetail saleDetail : liDetail) {// hay que mejorar la forma de hacer los descuentos al inventario
             new BasicDAO().runSPNoReturn("Item.discountItemStockOneByOne",saleDetail.getStandardCodeItem().getIdItem(), saleDetail.getQuantity());
                 
           }

             
               
        return folio;
       }catch(Exception ex){
           ex.printStackTrace();             
           throw ex;
           
       }
    }
    
//    seguir aqui para mejorar el descuento de articulos cuando hay duplicados
//    public List<SaleDetail> groupDetail(List<SaleDetail> details){
//        Set<SaleDetail> dupItems = new HashSet<>();
//        Set<SaleDetail> notDupItems = new HashSet<>();
//        Set<SaleDetail> allDetails = new HashSet<>();
//        
//        dupItems.add(details.get(0));
//        for (int i = 2; i< details.size(); i++) {
//            if (notDupItems.contains(detail)) {
//                dupItems.add(detail);
//            }else{
//                notDupItems.add(detail);
//            }
//        }
        
   
//         for (int i = 0; i< details.size(); i++) {
////             BigDecimal totalQty = new BigDecimal("0");
//             SaleDetail detailI = details.get(i);
//             for (int j = i+1; j < details.size(); j++) {
//                 SaleDetail detailJ = details.get(j);
//                 if(detailI.getStandardCodeItem().getIdItem().equals(detailJ.getStandardCodeItem().getIdItem())){
////                    totalQty = totalQty.add(detailI.getQuantity().add(detailJ.getQuantity()));
////                    allDetails.remove(detailJ);
//                      dupItems.add(detailI);
//               }
//                else{
////                     notDupItems.add(detailI);
////                    totalQty = detailI.getQuantity();
//                }
//                 
//             }
////                allDetails.get(i).setQuantity(totalQty);
////                detailI.setQuantity(totalQty);  
//             
//                 
//             
//        }
//         return details;
//    }
//    seguir aqui para mejorar el descuento de articulos cuando hay duplicados
//    public Item discountStock(Item itemRefund, BigDecimal pieces){
//        BigDecimal currentStock = itemRefund.getItemStock();
//        currentStock = currentStock.subtract(pieces);
//        itemRefund.setItemStock(currentStock);
//        itemRefund.setLastModDate(Utility.getDate());
//        return itemRefund;
//    }
   
    public Long generateFolio(SaleHeader saleHeader, boolean isWithdraw){
       int consecutiveWithdraw;
       String strFolio = dateFormat.format(today.getTime());
       strFolio = strFolio.replace("/", "");
        try{
        int countSaleHeader;
        BasicDAO dao = new BasicDAO();
        consecutive = 0;
        consecutiveWithdraw = 900;
//        List<Object> liFolio = new ArrayList<>();
        
            SaleHeaderExt tempHeader = new SaleHeaderExt();
        tempHeader.getSaleHeadEntity().setSaleDate(today.getTime());
//        agregar después idDivition y idSubDivition para la búsqueda
            tempHeader.entity2Example();
        
//            liFolio = BasicDAO.readByExample(tempHeader, EntityExt.ORDER_DESC, EntityExt.MATCH_ANYWHERE, 1);
            countSaleHeader = dao.resultSizeSP("SaleHeader.countSaleHeader",
                                                0,
                                                1,
                                                viewBean.getAppConfig().getIdDivition(),
                                                viewBean.getAppConfig().getIdSubdivition(),
                                                today.getTime(),
                                                today.getTime(),
                                                isWithdraw
            );
            if (!isWithdraw){
                if (countSaleHeader <= 0){
                    consecutive = 1;
                }else{ 
                consecutive = countSaleHeader + 1; 
                }
            }else{
                consecutiveWithdraw = 900 + countSaleHeader + 1;
                consecutive = consecutiveWithdraw;
            }
            
            
            
            strFolio = strFolio + consecutive.toString();
//            System.out.println(strFolio);
                return Long.valueOf(strFolio);
            
            
        }catch(Exception ex){
            ex.printStackTrace();
            return  null;
        }
        
       
    }
    
    public void processWithdraw(SaleHeaderExt saleHeadExt) throws Exception{
       Long folio = generateFolio(saleHeadExt.getSaleHeadEntity(), true);
       saleHeadExt.getSaleHeadEntity().setFolio(folio);
       saleHeadExt.getSaleHeadEntity().setConsecutive(consecutive);
       saleHeadExt.getSaleHeadEntity().setRateSale(viewBean.getAppConfig().getRateSale());
       saleHeadExt.getSaleHeadEntity().setSaleDate(today.getTime());
       saleHeadExt.getSaleHeadEntity().setCreationDate(today.getTime());
       saleHeadExt.getSaleHeadEntity().setIdDivition(viewBean.getAppConfig().getIdDivition());
       saleHeadExt.getSaleHeadEntity().setIdSubdivition(viewBean.getAppConfig().getIdSubdivition());
       saleHeadExt.getSaleHeadEntity().setModifiedBy(viewBean.getUser().getUserAlias());
       saleHeadExt.getSaleHeadEntity().setIsWithdraw(true);
       saleHeadExt.getSaleHeadEntity().setIsrefund(false);
       saleHeadExt.getSaleHeadEntity().setCashRefund(BigDecimal.ZERO);
      
       BigDecimal tempCashWithdraw = saleHeadExt.getSaleHeadEntity().getCashWithdraw();
       saleHeadExt.getSaleHeadEntity().setCashWithdraw(tempCashWithdraw.multiply(new BigDecimal(-1)));
       saleHeadExt.getSaleHeadEntity().setTotalSale(BigDecimal.ZERO);
       saleHeadExt.getSaleHeadEntity().setCashPayment(BigDecimal.ZERO);
       saleHeadExt.getSaleHeadEntity().setCardPayment(BigDecimal.ZERO);
       saleHeadExt.getSaleHeadEntity().setOtherPayment(BigDecimal.ZERO);
       saleHeadExt.getSaleHeadEntity().setTaxTotal(BigDecimal.ZERO);
       saleHeadExt.getSaleHeadEntity().setTotalSale(BigDecimal.ZERO);
       saleHeadExt.getSaleHeadEntity().setFolioCollection(null);
       saleHeadExt.entity2Example();
       try{
        BasicDAO.basicInsert(saleHeadExt.getSaleHeadEntity());
           System.out.println("folio "+folio);
           
           
       }catch(Exception ex){
           ex.printStackTrace();             
           throw ex;
           
       }
    }
    
    public void prepFrecItemList(){
        FrecSaleItemExt frecItem = new FrecSaleItemExt();
        frecItem.entity2Example();
        liPromoItem = new ArrayList<>();
        liExtraItem = new ArrayList<>();
        liFrecItem = new ArrayList<>();
        try{
        liFullFreclist = (List<FrecuentSaleItem>)(Object)BasicDAO.readSPReturnList("FrecuentSaleItem.findFrecItem");
       
           
            for (FrecuentSaleItem tempItem : liFullFreclist) {
                
                if(tempItem.getIsFrecuentOther()){
                    liFrecItem.add(tempItem);
                }
                if (tempItem.getIsFrecuentPromo()){
                    liPromoItem.add(tempItem);
                }
                if (tempItem.getIsFrecuentExtra()){
                    liExtraItem.add(tempItem);
                }
                
            }
        
        }catch(Exception ex){
            ex.printStackTrace();
           
        }
        
      
    }
    
    public Item searchItem(ItemExt itemSearch){
      
        
        return (Item) new BasicDAO().readSPReturnObject("Item.finditemByCode"
                                                 ,viewBean.getAppConfig().getIdDivition()
                                                 ,viewBean.getAppConfig().getIdSubdivition()
                                                 ,itemSearch.getItemEntity().getStandardCodeItem());
       
       
    }
    
    
    public BigDecimal calcQuantity(BigDecimal pieces, Item foundItem){
        
        
           if(!foundItem.getSaleByFraction() && pieces.compareTo(BigDecimal.ONE) >0){
             
             pieces = pieces.setScale(0,RoundingMode.DOWN);
              
           }
           
           if (!foundItem.getSaleByFraction() && pieces.compareTo(BigDecimal.ONE)<= 0){
               pieces = new BigDecimal("1");
              
           }
           
           if (foundItem.getSaleByFraction() && pieces.compareTo(BigDecimal.ZERO)<= 0){
               if (pieces.compareTo(BigDecimal.ZERO)== 0){
                   pieces = new BigDecimal("1");
               }else{
               pieces = pieces.multiply(new BigDecimal("-1"));
               }
               
           }
           
           return pieces;
           
       }
    
    
    public BigDecimal calcDetail(BigDecimal pieces, BigDecimal unitPrice){
        BigDecimal total;
        total = unitPrice.multiply(pieces);
        total = total.setScale(2,RoundingMode.HALF_UP);
         
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
     
    public BigDecimal calcSubTotal(List<SaleDetail> itemList){
        BigDecimal subtotal = new BigDecimal(0.00).setScale(2,RoundingMode.HALF_UP);
        
        for (SaleDetail sd : itemList) {
           subtotal = subtotal.add(sd.getPriceDetail());
          
        }
        return subtotal;
    }
    
    public BigDecimal calcTax(List<SaleDetail> itemList){
       
       BigDecimal overAllTax = new BigDecimal(BigInteger.ZERO);
       
        for (SaleDetail saleDetail : itemList) {
            overAllTax = overAllTax.add(saleDetail.getItemTax());
        }
   
        return overAllTax;     
    
    }
    public BigDecimal calcTotal(BigDecimal subtotal, BigDecimal tax){
        BigDecimal total;
        total = subtotal;
        total = total.setScale(2,RoundingMode.HALF_UP);
        
        return total;
    }
    
  
    public BigDecimal calcPayment(BigDecimal cash, BigDecimal card, BigDecimal other, BigDecimal debit){
        BigDecimal totPayment;
        totPayment = new BigDecimal(0.00).setScale(2,RoundingMode.HALF_UP);
        totPayment = cash.add(card).add(other).add(debit);
        return totPayment;
    }
    
    public BigDecimal calcChange(BigDecimal payment, BigDecimal total){
        BigDecimal change = new BigDecimal(0.00).setScale(2,RoundingMode.HALF_UP);
             change = payment.subtract(total);
        
        return change;
    }
    
        public LazyDataModel<Object> callReadLazyList() {
           
            if(viewBean.getQuickItemSearch().getDescItem().equals("")){
                viewBean.getQuickItemSearch().setDescItem("nulll");
            }
         return new QuickItemLazySearch("Item.findItemByDesc",
                                     viewBean.getAppConfig().getIdDivition(),
                                     viewBean.getAppConfig().getIdSubdivition(),
                                     viewBean.getQuickItemSearch().getDescItem()
         );
                 
                 
        }
    

     public boolean accessPermission(){
          for (MenuOptionPermission permission : viewBean.getUser().getListMenuOptionPermission()) {
            Integer permCode = permission.getIdMenuOption().getMenuOptionCode();
            
            if(permCode.equals(MenuOptionEnum.PROC_SALE_OPTION.getCodeMenuOption())){
                return true;
            }
            
        }
        return false;
      
     }
     
        public List<FrecuentSaleItem> getLiFrecItem() {
        return liFrecItem;
    }

    public List<FrecuentSaleItem> getLiPromoItem() {
        return liPromoItem;
    }

    public List<FrecuentSaleItem> getLiExtraItem() {
        return liExtraItem;
    }

    public List<FrecuentSaleItem> getLiOtherItem() {
        return liOtherItem;
    }
        
}
 