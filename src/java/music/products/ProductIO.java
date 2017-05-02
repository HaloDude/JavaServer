package music.products;



import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
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
        /*ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO product(ProductCode, ProductDescription, ProductPrice)" 
                + " VALUES (?, ?, ?)";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }*/
    }

    public static void updateProduct(Product product) {
       /*ConnectionPool pool = ConnectionPool.getInstance();
       Connection connection = pool.getConnection();
       PreparedStatement ps = null;
       
       String query = "UPDATE product " 
                + " SET ProductDescription = ?, ProductPrice = ? "
                + " WHERE ProductCode = ?";
   
       try {
           ps = connection.prepareStatement(query);
           ps.setString(1, product.getDescription());
           ps.setDouble(2, product.getPrice());
           ps.setString(3, product.getCode());
                          
           ps.executeUpdate();
       } catch (SQLException e) {
           System.out.println(e);
       } finally {
           DBUtil.closePreparedStatement(ps);
           pool.freeConnection(connection);
       }*/
      
    }

    public static void deleteProduct(Product product) {
        /*ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE FROM product" 
                + " WHERE ProductCode = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getCode());
            
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }*/
    }    
}