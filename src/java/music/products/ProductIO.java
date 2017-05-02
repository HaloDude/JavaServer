package music.products;



import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductIO {

    private static List<Product> products = null;
   
    public static List<Product> selectProducts() {
        products = new ArrayList<Product>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        ResultSet rs = null;
        
        String query = "SELECT * FROM product";
        products = new LinkedList<>();
        try{
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            Product p = null;
            while(rs.next()){
                p = new Product();
                p.setCode(rs.getString("ProductCode"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setPrice(rs.getDouble("ProductPrice"));
                
                products.add(p);
            }
            
            return products;
        } catch (SQLException e){
            Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, e);
            
        } finally {
           DBUtil.closeResultSet(rs);
           DBUtil.closePreparedStatement(ps);
           pool.freeConnection(connection);
        }
  
        return null;
        
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
        ConnectionPool pool = ConnectionPool.getInstance();
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
        }
    }

    public static void updateProduct(Product product) {
        /*products = selectProducts();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (product.getCode() != null
                    && product.getCode().equalsIgnoreCase(p.getCode())) {
                products.set(i, product);
            }
        }
        saveProducts(products);*/
    }

    public static void deleteProduct(Product product) {
        /*products = selectProducts();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (product != null
                    && product.getCode().equalsIgnoreCase(p.getCode())) {
                products.remove(i);
            }
        }
        saveProducts(products);*/
    }    
}