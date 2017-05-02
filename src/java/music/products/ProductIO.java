package music.products;



import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ProductIO {

    private static List<Product> products = null;
   
    public static List<Product> selectProducts() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p from Product p";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        List<Product> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
        
        return results;
    }

    public static Product selectProduct(String productCode) {
        products = selectProducts();
        for (Product p : products) {
            if (productCode != null
                    && productCode.equalsIgnoreCase(p.getCode())) {
                return p;
            }
        }
        return null;
    }

    public static boolean exists(String productCode) {
        Product p = selectProduct(productCode);
        if (p != null) return true;
        else return false;
    }    

    public static void insertProduct(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try{
            em.persist(product);
            trans.commit();
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void updateProduct(Product product) {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try{
            em.merge(product);
            trans.commit();
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }      
    }

    public static void deleteProduct(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try{
            em.remove(product);
            trans.commit();
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }
    }    
}