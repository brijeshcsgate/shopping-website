import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class address extends HttpServlet {

    String add_bill,add_ship,city,country,state,isbill,isship,postal_code,user_id;
    Connection con;
    PreparedStatement ps;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        user_id = request.getParameter("user_id");
        add_bill = request.getParameter("b_address");
        add_ship = request.getParameter("ship_address");
        city = request.getParameter("city");
        country = request.getParameter("country");
        state = request.getParameter("state");
        isbill = request.getParameter("bill-active");
        isship = request.getParameter("ship_active");
        postal_code = request.getParameter("post_code");
       
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_e_com", "root", "");
            //  ps=con.prepareStatement("insert into person values('"+ username+"','"+password+"','"+phone+"','"+email+"')");
            ps = con.prepareStatement("insert into address(address_line_one,address_line_two,city,country,is_billing,is_shipping,postal_code,state,user_id) values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1,user_id);
            ps.setString(2,add_bill);
            ps.setString(3, add_ship);
            ps.setString(4, city);
            ps.setString(5, country);
            ps.setString(6, isbill);
            ps.setString(7,isship);
            ps.setString(8,postal_code);
            ps.setString(9,state);
            
            ps.executeUpdate();
            out.println("Category Addded successfully");

        } catch (Exception e) {
 out.println("Error while inserting data->" + e.getMessage());

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
