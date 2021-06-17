
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author S
 */
public class FileUpload { 
    private static final String ABS_PATH="C:\\Users\\RS\\Documents\\shopping\\web\\images\\";
    private static String REAL_PATH=null;
    boolean isMultipart;
String filepath;
int maxFileSize=50*1024;
int maxMemSize=4*1024;
File file;

    public boolean fileUpload(HttpServletRequest request,String code){
        REAL_PATH=request.getSession().getServletContext().getRealPath("\\images\\");
        
        if(!new File(REAL_PATH).exists()){
            new File(REAL_PATH).mkdir();
        }
        if(!new File(ABS_PATH).exists()){
            new File(ABS_PATH).mkdir();
        }
         boolean  isMultipart=ServletFileUpload.isMultipartContent(request);
        try{
            
            DiskFileItemFactory factory=new DiskFileItemFactory();
            
            factory.setSizeThreshold(maxMemSize);
            factory.setRepository(new File("c:\\temp"));
            
            ServletFileUpload upload=new ServletFileUpload(factory);
            
            upload.setFileSizeMax(maxFileSize);
            
            List fileitems=upload.parseRequest(request);
            
            Iterator i=fileitems.iterator();
            
            while(i.hasNext()){
                FileItem fi=(FileItem)i.next();
                
                if(!fi.isFormField()){
                String fileName=request.getParameter("p_image");    
                    
                    
                    
                    
                    
                    if(fileName.lastIndexOf("\\")>=0){
                        file=new File(REAL_PATH+code.substring(code.lastIndexOf("\\")));
                    }
                    else{
                        file=new File(filepath+fileName.substring(fileName.lastIndexOf("\\")+1));
                    }
                    fi.write(file);

                    
                }
            }
        }
        catch(Exception ex){
        }
        
        return true;
    }
}
