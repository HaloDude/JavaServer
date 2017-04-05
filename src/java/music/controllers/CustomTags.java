package music.controllers;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * Servlet for random checks and edits i guess. So far it will only check the
 * fields for being empty.
 * @author DennisFedorchuk
 */
public class CustomTags extends TagSupport{
    private String field;
    private String color = "red";
    
    @Override
    public int doStartTag() throws JspException{
        try{
            JspWriter out = pageContext.getOut();
            if(field == null || field.length() == 0 || field.equals("")){
                out.print("<font color = "+color+"> *</font>");
            }
        } catch (IOException e){
            System.out.println(e);
        }
        return SKIP_BODY;
    }
    
    public void setField(String field) {
        this.field = field;
    }
    
    public void setColor(String color){
        this.color = color;
    } 
}



