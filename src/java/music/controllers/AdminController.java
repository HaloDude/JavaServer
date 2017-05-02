/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import music.products.*;

/**
 *
 * @author DennisFedorchuk
 */
public class AdminController extends HttpServlet {
    

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        //ProductIO.init(this.getServletContext().getRealPath(this.getServletContext().getInitParameter("productDatabase")));
       
    }
    
        
    public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {  
        //get the action that is apppended to the url to know what to do 
        String action = req.getParameter("action"); 
        //if the action is null just show the table of songs
        System.out.println(action);
        if(action == null){
            action = "viewProducts";
            System.out.println("tis null");
        }
        
        String url = "index.jsp"; //defauld url points at home page
        if(action.equals("viewProducts")){  //if actition is to view the product table
            url = viewProducts(req, res); //call the url building method
        } else if (action.equals("editProduct")){
            url = editProduct(req, res);
        } else if (action.equals("addProduct")){
            url = "/admin/addProduct.jsp";
        } else if (action.equals("deleteProduct")){
            Product p = ProductIO.selectProduct(req.getParameter("productCode"));
            req.setAttribute("product", p);
            url = "/admin/deleteProduct.jsp";
        }

        //forward the built url to a next page with currect requests and responces
        getServletContext().getRequestDispatcher(url).forward(req, res); 
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(); // get session to stor e the message. Dont forget to clear
        s.removeAttribute("message");
        if(req.getParameter("editProduct") != null){ //if the change product button was trigered
            System.out.println("editing");
            Product p = new Product(); 
            //Check the input
            if(!req.getParameter("codeInput").equals("") && !req.getParameter("descriptionInput").equals("") && !req.getParameter("priceInput").equals("")){
                //check if price is a number
                try{
                    double price = Double.parseDouble(req.getParameter("priceInput"));
                    System.out.println("Price is: "+price);
                } catch (NumberFormatException e2){
                    System.out.println("Price not correct");
                    s.setAttribute("message", "The price entered is invalid. Values have been reset");
                    res.sendRedirect("controllers?action=editProduct&productCode="+((Product)s.getAttribute("product")).getCode());
                    return; //retrun statement is used to remove the "action was commited error"
                }
                //set the attributes because previous steps indicate that they are correct
                p.setCode(req.getParameter("codeInput"));
                p.setDescription(req.getParameter("descriptionInput"));
                p.setPrice(Double.parseDouble(req.getParameter("priceInput")));
                
                ProductIO.updateProduct(p); //update a product
                System.out.println("Updating product in list");
                s.removeAttribute("message"); //remove the session message
                res.sendRedirect("controllers?action=viewProducts"); //send a redirect
            } else {
                s.setAttribute("message", "Must fill out all the fields to edit. Values have been reset");
                res.sendRedirect("controllers?action=editProduct&productCode="+((Product)s.getAttribute("product")).getCode());
            }
            
        } else if(req.getParameter("viewProducts") != null) { //if the view products button was trigered
            System.out.println("return to product page");
            res.sendRedirect("controllers?action=viewProducts"); //send a redirect
        } else if (req.getParameter("addProduct") != null){ //if the add button is pressed
            System.out.println("Addind product");
            Product p = new Product(); 
            s.removeAttribute("message");
            //Check the input
            if(!req.getParameter("codeInput").equals("") && !req.getParameter("descriptionInput").equals("") && !req.getParameter("priceInput").equals("")){
                                
                //check if price is a number
                try{
                    double price = Double.parseDouble(req.getParameter("priceInput")); 
                } catch (NumberFormatException e2){
                    System.out.println("Price not correct");
                    s.setAttribute("message", "The price entered is invalid. Values have been reset");
                    req.setAttribute("tempCode", req.getParameter("codeInput"));
                    req.setAttribute("tempDescription", req.getParameter("descriptionInput"));
                    req.setAttribute("tempPrice", req.getParameter("priceInput"));
                    //res.sendRedirect("controllers?action=addProduct&productCode="+((Product)s.getAttribute("product")).getCode());
                    getServletContext().getRequestDispatcher("/admin/addProduct.jsp").forward(req, res);
                    return; //retrun statement is used to remove the "action was commited error"
                }
                //set the attributes because previous steps indicate that they are correct
                p.setCode(req.getParameter("codeInput"));
                p.setDescription(req.getParameter("descriptionInput"));
                p.setPrice(Double.parseDouble(req.getParameter("priceInput")));
                
                ProductIO.insertProduct(p); //add a product
                System.out.println("Adding product in list");
                s.removeAttribute("message"); //remove the session message
                res.sendRedirect("controllers?action=viewProducts"); //send a redirect
            } else {
                s.setAttribute("message", "Must fill out all the fields to add. Values have been reset");
                req.setAttribute("tempCode", req.getParameter("codeInput"));
                req.setAttribute("tempDescription", req.getParameter("descriptionInput"));
                req.setAttribute("tempPrice", req.getParameter("priceInput"));
                //res.sendRedirect("controllers?action=addProduct");
                getServletContext().getRequestDispatcher("/admin/addProduct.jsp").forward(req, res); 
            }
        } else if (req.getParameter("yesButton") != null){
            String pCode = req.getParameter("productCode");
            Product p = ProductIO.selectProduct(pCode);
            System.out.println("Deleting Product: "+ p.getCode());
            ProductIO.deleteProduct(p);
            res.sendRedirect("controllers?action=viewProducts"); //send a redirect
        }
        
        
    }
        
    public String viewProducts(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        HttpSession session = req.getSession();
        session.setAttribute("productList", ProductIO.selectProducts());
        return "/admin/displayProducts.jsp";
    }
    
    public String editProduct(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        HttpSession session = req.getSession();
        //List l =(List) session.getAttribute("productList");
        
        String code = req.getParameter("productCode");
        if(ProductIO.exists(code)){
            session.setAttribute("product", ProductIO.selectProduct(code));
            return "/admin/editProduct.jsp";
            
        }
        return null;
    }
    
}
