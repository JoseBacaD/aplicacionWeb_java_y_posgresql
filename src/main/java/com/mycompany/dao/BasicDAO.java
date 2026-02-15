/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;




import com.mycompany.dropdown.AbstractDropdown;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.mycompany.extended.EntityExt;
import com.mycompany.jpa.util.HibernateUtil;
import com.mycompany.pojo.AbstractField;
import java.math.BigInteger;
import java.util.HashMap;

/**
 *
 * @author bacajos
 */
public class BasicDAO {
    
    private EntityExt entity;
    public static SessionFactory sf = null;
    public static Session session = null;
    public static Transaction trans = null;
    
    public BasicDAO(){
        
    }
    
    public BasicDAO(EntityExt entity){
        this.entity = entity;
    }
        
        
        public static void basicInsert(Object obj2Insert) throws Exception{
        
        
        
        try{
         sf = HibernateUtil.getSessionFactory();
         session = sf.openSession();
         trans = session.beginTransaction(); 
         session.save(obj2Insert);
         trans.commit();
         
         
         
        }catch (Exception ex){
            ex.printStackTrace();
            if (trans != null && trans.isActive()){
                    trans.rollback();
                    session.flush();
                }
            throw ex;
        }finally{
             if (session!= null){
                    session.close();
                }
        }
    }
    
    public static void basicMerge(Object newObject) throws Exception{
        try{
         sf = HibernateUtil.getSessionFactory();
         session = sf.openSession();
         trans = session.beginTransaction(); 
         session.merge(newObject);  
         trans.commit();
//         session.close();   
         
                  
        }catch (Exception ex){
            ex.printStackTrace();
            if (trans != null && trans.isActive()){
                    trans.rollback();
//                    session.flush();
                }
            throw ex;
            
        }finally{
             if (session!= null){
                    session.close();
                }
        }
        
    }
    
   
    
    public static void readDropDownList(AbstractDropdown dropdown){
         try{
                sf = HibernateUtil.getSessionFactory();
                session = sf.openSession();
                trans = session.beginTransaction(); 
                
               
         
                
//                empieza construcción del query
               
                dropdown.getLiObject().clear();
                Criteria query = session.createCriteria(dropdown.getEntityClassName()).
                                 add(dropdown.getExample());


               
          
            

                dropdown.setLiObject(query.list());
                trans.commit();
                
         
         
               
            }catch(Exception ex){
                ex.printStackTrace();
                dropdown.getLiObject().clear();
                if (trans != null && trans.isActive()){
                    trans.rollback();
                    session.flush();
                }
            }finally{
             if (session!= null){
                    session.close();
                }
         }
            
        
        
        
        
        
        
        
    }
    
    public static Object basicRead(Object obj2Search, int id2Search){
        Object objFound;
        try{
          
         sf = HibernateUtil.getSessionFactory();
         session = sf.openSession();
         trans = session.beginTransaction(); 
         objFound = session.get(obj2Search.getClass(), id2Search);
         
         trans.commit();
         
         
            return objFound;
         
        }catch (HibernateException ex){
          ex.printStackTrace();
          return null;
        }finally{
            session.close();
        }
    

}
    
    public static List<Object> readList(String strQuery ){
        try{
            List<Object> liObjects;
         sf = HibernateUtil.getSessionFactory();
         session = sf.openSession();
         trans = session.beginTransaction(); 
         liObjects = session.createQuery(strQuery).list(); 
         trans.commit();
         
         return liObjects;
            
         
        }catch (HibernateException ex){
          ex.printStackTrace();
          return null;
        }finally{
        session.close();
        }
    }
    
    public static Object readByColumn( String whereCondition,String searchCriteria){
        Object objFound;
        try{
          
         sf = HibernateUtil.getSessionFactory();
         session = sf.openSession();
         trans = session.beginTransaction(); 
            Query query = session.createQuery(whereCondition + " = :searchCriteria");
            query.setParameter("searchCriteria", searchCriteria);
           
            objFound = query.uniqueResult();
         
         trans.commit();
         
         
            return objFound;
         
        }catch (HibernateException ex){
          ex.printStackTrace();
          return null;
        }finally{
            session.close();
        }
    }
    
    // retorna el totalde registros encontrados, es igual que la otra búsqueda
    public static int countTotal(String strQuery, Map<String, Object> params ){
        try{          
            int totalRows = 0;
            List<Object> liObjects;
         sf = HibernateUtil.getSessionFactory();
         session = sf.openSession();
         trans = session.beginTransaction(); 
          
          Query query = session.createQuery(strQuery);
          
            for (Map.Entry<String, Object> pair : params.entrySet()) {
                 query.setParameter(pair.getKey(), pair.getValue());
                    }
           totalRows = query.list().size();
         trans.commit();
         
         return totalRows;
            
         
        }catch (HibernateException ex){
          ex.printStackTrace();
          if (trans != null && trans.isActive()){
                    trans.rollback();
                    session.flush();
                }
          return 0;
        }finally{
            if (session!= null){
            session.close();
            }
        }
    }
    
     public static List<Object> readWithParams(String strQuery, Map<String, Object> params){
        try{          

            List<Object> liObjects;
         sf = HibernateUtil.getSessionFactory();
         session = sf.openSession();
         trans = session.beginTransaction(); 
          
          Query query = session.createQuery(strQuery);
          
          
            for (Map.Entry<String, Object> pair : params.entrySet()) {
                 query.setParameter(pair.getKey(), pair.getValue());
                    }
            liObjects = query.list();
         trans.commit();
         
         return liObjects;
            
         
        }catch (HibernateException ex){
          ex.printStackTrace();
          return null;
        }finally{
            session.close();
        }
    }
     
     
     public List<Object> readLazyList(int firstRow, int maxRowPageSize){
       
            try{
                    
                sf = HibernateUtil.getSessionFactory();
                session = sf.openSession();
                trans = session.beginTransaction(); 
                
                List<Object> liObjects;
         
                
              //empieza construcción del query
                entity.getExample().enableLike(MatchMode.ANYWHERE);
         
                Criteria query = session.createCriteria(entity.getExtClassName()).
                                 add(entity.getExample()).
                                 addOrder(Order.asc(entity.getOrderColumnName()))   ;
                                 
                                 
                
                
                if (entity.getMapExample()!= null){
                    for(Map.Entry<String, Example> entry : entity.getMapExample().entrySet()) {
                         String key = entry.getKey();
                         Example value = entry.getValue();
                    
                         query.createCriteria(key).add(value);
                        
                    }
                }
               
                query = buildQuery(query);
                
                
                query.setFirstResult(firstRow);
                query.setMaxResults(maxRowPageSize);
                query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                liObjects = query.list();
                trans.commit();
               
         
         
                return liObjects;
                
            }catch(Exception ex){
                ex.printStackTrace();
                if (trans != null && trans.isActive()){
                    trans.rollback();
                    session.flush();
                }
                return null;
            }finally{
                if (session!= null){
                    session.close();
                }
            }
            
            
            
        } 
     
     
     public static List<Object> readByExample(EntityExt entity, int order, int matchMode, int maxRowSize) throws Exception{
       
            try{
                    
                sf = HibernateUtil.getSessionFactory();
                session = sf.openSession();
                trans = session.beginTransaction(); 
                
                List<Object> liObjects;
         
                switch(matchMode){
                  case EntityExt.MATCH_START: entity.getExample().enableLike(MatchMode.START);break;
                  case EntityExt.MATCH_END: entity.getExample().enableLike(MatchMode.END);break;
                  case EntityExt.MATCH_EXACT: entity.getExample().enableLike(MatchMode.EXACT);break;
                  case EntityExt.MATCH_ANYWHERE: entity.getExample().enableLike(MatchMode.ANYWHERE);break;
                  
              }

                Criteria query = session.createCriteria(entity.getExtClassName()).
                                 add(entity.getExample());
                
                
                switch(order){
                    case EntityExt.ORDER_ASC: query.addOrder(Order.asc(entity.getOrderColumnName())); break;
                    case EntityExt.ORDER_DESC: query.addOrder(Order.desc(entity.getOrderColumnName())); break;
            }
                
//                CICLO FOR PARA BUSCAR EN ENTIDADES CON RELACIONES
             
               for(Map.Entry<String, Example> entry : entity.getMapExample().entrySet()) {
                    String key = entry.getKey();
                    Example value = entry.getValue();
                    
                    query.createCriteria(key).add(value);
                        
                }
             
                
                query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                
                if(maxRowSize > 0){
                    query.setMaxResults(maxRowSize);
                }
                  liObjects = query.list();
                trans.commit();
               
         
         
                return liObjects;
            }catch(Exception ex){
                ex.printStackTrace();
                if (trans != null && trans.isActive()){
//                    trans.rollback();
                    session.flush();
                }
               
                return null;
            }finally{
                if (session!= null){
                    session.close();
                }
               
            }
            
        }
        
        
      
//        MÉTODO PARA COMPLEMENTAR QUERY CON CAMPOS PARES DE UNA
//        BÚSQUEDA LAZY, HACE UN BETWEEN DE CADA CAMPO PAR
        private Criteria buildQuery(Criteria query){
            
            try{
                Field [] fields = entity.getClass().getFields();
         
                for (Field field : fields) {
                    if (field != null){
                        if(field.getType().isAssignableFrom((AbstractField.class))){
                      
                       
                            AbstractField tempField = ((AbstractField)field.get(entity));
                                if (tempField != null){
                                    query.add(Restrictions.between(tempField.getId(),
                                                           tempField.getObjIniValue(),
                                                           tempField.getObjEndValue()));
                                    
                                  
                                }
                            }
                        
                    }
                       
                }
                return query;
                   
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return null;
            
        }
        
        public int resultSize(int firstRow, int maxRowPageSize ){
            try{
                long totalRows = 0;
      
                sf = HibernateUtil.getSessionFactory();
                session = sf.openSession();
                trans = session.beginTransaction();
         
               
                entity.getExample().enableLike(MatchMode.ANYWHERE);
                
//             en esta parte de setProjection(entity.getPrimaryColumn) va la columna por la cual va a hacer el count(*) 
//             lo voy a dejar por la llave primaria de la tabla, pero se puede ajustar según se desee, solo hay que agregar la columna en la clase EntityExt
                Criteria query = session.createCriteria(entity.getExtClassName())
                                 .setProjection(Projections.count(entity.getCountColumnName()))
                                 .add(entity.getExample());
                
                if(entity.getMapExample()!= null){
                    for(Map.Entry<String, Example> entry : entity.getMapExample().entrySet()) {
                        String key = entry.getKey();
                        Example value = entry.getValue();
                    
                        query.createCriteria(key).add(value);
                        
                    }
                }
                
                query = buildQuery(query);
                query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                totalRows = (long)query.uniqueResult();
                trans.commit();
                
                
                return Math.toIntExact(totalRows);
            }catch (Exception ex){
                ex.printStackTrace();
                if (trans != null && trans.isActive()){
                    trans.rollback();
                    session.flush();
                }
                return 0;
            }finally{
                 if (session!= null){
                    session.close();
                }
                
            }
            
        }
     
//      version antigua de readLazyList que pedía el query ya hecho a la medida, se puede utilizar luego como ejemplo de busquedas mas especificas.
//     public static List<Object> readLazyList(String strQuery, Map<String, Object> params, int firstRow,int maxRowPageSize ){
//        try{          
//
//            List<Object> liObjects;
//         sf = HibernateUtil.getSessionFactory();
//         session = sf.openSession();
//         trans = session.beginTransaction(); 
//          
//          Query query = session.createQuery(strQuery);
//          query.setFirstResult(firstRow);
//          query.setMaxResults(maxRowPageSize);
//          
//            for (Map.Entry<String, Object> pair : params.entrySet()) {
//                 query.setParameter(pair.getKey(), pair.getValue());
//                    }
//            liObjects = query.list();
//         trans.commit();
//         
//         return liObjects;
//            
//         
//        }catch (HibernateException ex){
//          ex.printStackTrace();
//          return null;
//        }finally{
//            session.close();
//        }
//    }
    
    public static void basicDelete(Object obj2Delete) throws Exception{
        
        
        try{
         sf = HibernateUtil.getSessionFactory();
         session = sf.openSession();
         trans = session.beginTransaction(); 
         session.delete(obj2Delete);
         trans.commit();
         
        }catch (Exception ex){
            ex.printStackTrace();
            if (trans != null && trans.isActive()){
                    trans.rollback();
//                    session.flush();
                }
            throw ex;
            
        }finally{
             if (session!= null){
                    session.close();
                }
        }
        
    }
    
    public static Object readSingleReturnUnique(EntityExt entityExt)throws Exception{
        try{
            Object objFound;
                sf = HibernateUtil.getSessionFactory();
                session = sf.openSession();
                trans = session.beginTransaction(); 
               
//                empieza construcción del query
                 Criteria query = session.createCriteria(entityExt.getExtClassName()).
                                 add(entityExt.getExample());
                
                for(Map.Entry<String, Example> entry : entityExt.getMapExample().entrySet()) {
                    String key = entry.getKey();
                    Example value = entry.getValue();
                    
                    query.createCriteria(key).add(value);
                        
                }
                
                query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

               objFound = query.uniqueResult();
                trans.commit();
                
              return objFound;
         
               
            }catch(Exception ex){
                ex.printStackTrace();               
                return null;
               
            }finally{
             session.close();
         }
    }
        
    public List<Object> readSPLazyList(String namedQuery,int firstRow, int maxRowPageSize, Object... params){
       
            try{
                    
                sf = HibernateUtil.getSessionFactory();
                session = sf.openSession();
                trans = session.beginTransaction(); 
                
                List<Object> liObjects;
		final Query query = session.getNamedQuery(namedQuery);
 
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}

			query.setFirstResult(firstRow);
 			query.setMaxResults(maxRowPageSize);
		 
                liObjects = query.list();
                trans.commit();
               
                return liObjects;
                
            }catch(Exception ex){
                ex.printStackTrace();
                if (trans != null && trans.isActive()){
//                    trans.rollback();
                    session.flush();
                }
                return null;
            }finally{
                if (session!= null){
                    session.close();
                }
            }
            
            
            
        } 
     
    
     public static List<Object> readSPReturnList(String namedQuery, Object... params){
       
            try{
                    
                sf = HibernateUtil.getSessionFactory();
                session = sf.openSession();
                trans = session.beginTransaction(); 
                
                List<Object> liObjects;
		final Query query = session.getNamedQuery(namedQuery);
 
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}

			
                liObjects = query.list();
                trans.commit();
               
                return liObjects;
                
            }catch(Exception ex){
                ex.printStackTrace();
                if (trans != null && trans.isActive()){
//                    trans.rollback();
                    session.flush();
                }
                return null;
            }finally{
                if (session!= null){
                    session.close();
                }
            }
            
            
            
        } 
     
    
    public boolean runSPNoReturn(String namedQuery, Object... params){
       
            try{
                    
                sf = HibernateUtil.getSessionFactory();
                session = sf.openSession();
                trans = session.beginTransaction(); 
                
             
		final Query query = session.getNamedQuery(namedQuery);
 
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}

			
                query.uniqueResult();
                trans.commit();
               
                return true;
                
            }catch(Exception ex){
                ex.printStackTrace();
                if (trans != null && trans.isActive()){
                    trans.rollback();
                    session.flush();
                }
                return false;
            }finally{
                if (session!= null){
                    session.close();
                }
            }
            
            
            
        } 
     
    
    
     public int resultSizeSP(String namedQuery,int firstRow, int maxRowPageSize, Object... params){
         BigInteger countRows;
            try{
                    
                sf = HibernateUtil.getSessionFactory();
                session = sf.openSession();
                trans = session.beginTransaction(); 
                
                
		final Query query = session.getNamedQuery(namedQuery);
 
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
 
		if (firstRow> 0) {
			query.setFirstResult(firstRow);
		}
 
		if (maxRowPageSize > 0) {
			query.setMaxResults(maxRowPageSize);
		
                }
                
                query.setFirstResult(firstRow);
                query.setMaxResults(maxRowPageSize);
                
               countRows = (BigInteger)query.uniqueResult();
                trans.commit();
                
              
                return countRows.intValue();
         
         
                
            }catch(Exception ex){
                ex.printStackTrace();
                if (trans != null && trans.isActive()){
//                    trans.rollback();
                    session.flush();
                }
                return 0;
            }finally{
                if (session!= null){
                    session.close();
                }
            }
            
            
                  } 
    
     
     public Object readSPReturnObject(String namedQuery, Object... params){
      
            try{
                 Object objFound;    
                sf = HibernateUtil.getSessionFactory();
                session = sf.openSession();
                trans = session.beginTransaction(); 
                
              
		final Query query = session.getNamedQuery(namedQuery);
 
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}

			
                objFound = query.uniqueResult();
                trans.commit();
               
                return objFound;
                
            }catch(Exception ex){
                ex.printStackTrace();
                if (trans != null && trans.isActive()){
//                    trans.rollback();
                    session.flush();
                }
                return null;
            }finally{
                if (session!= null){
                    session.close();
                }
            }
            
            
            
        } 
     
}
