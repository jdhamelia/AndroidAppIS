/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

/**
 *
 * @author jasmin
 */
public class AndroidAppServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         String paramNames = request.getParameter("Answers");
         String Longitude = request.getParameter("Longitude");
         String Latitude = request.getParameter("Latitude");
         String Location="Longitude: "+Longitude+" ," +" Latitude: "+Latitude;
        
        Connection conn;
        Statement st;
        PrintWriter out = response.getWriter();
        
        
        
       if(!paramNames.equals("")) {

            try{
                       
                JSONObject paramName = new JSONObject(paramNames);
                    
                   String answer1 = paramName.getString("answer1");
                   String answer2 = paramName.getString("answer2");
                   String answer3 = paramName.getString("answer3");
                   String answer4 = paramName.getString("answer4");
                   
                   

                     Class.forName("org.apache.derby.jdbc.ClientDriver");
                    
                     String connectionURL = "jdbc:derby://localhost:1527/AndroidApp";
                     
                    conn = DriverManager.getConnection(connectionURL,"upmc","upmc");
                    
                    st = conn.createStatement();
                   
                    //FRIEND_ID is changed manually as we do not have login page for authentication of user
                    st.execute("INSERT INTO UPMC.ANDROIDAPPDATA(FRIEND_ID,Q1_ANSWER,Q2_ANSWER,Q3_ANSWER,Q4_ANSWER,GPS_LOCATION,CURRENT_DATE_TIME) VALUES("+10+",'"+answer1+"','"+answer2+"','"+answer3+"','"+answer4+"','"+Location+"',CURRENT TIMESTAMP)");
                   
                    out.print("Thank you for your time, Have a good day");
                  
            }
            catch(JSONException ex){
                
               ex.printStackTrace();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
             
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
