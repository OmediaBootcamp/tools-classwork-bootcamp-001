package dev.omedia.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        System.out.println("doget");
        res.setContentType("text/html");//setting the content type
        try (PrintWriter pw = res.getWriter()) {//get the stream to write the data

//        pw.println("<html><body>");
//        pw.println("<h1>");
//        pw.print("Hello ");
//        pw.print(req.getParameter("fname"));
            try {
                Thread.sleep(60100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//        pw.print(" ");
//        pw.print(req.getParameter("lname"));
//        pw.println("</h1>");
//        pw.println("<a href=\"/tools_classwork_bootcamp_001_war\">&laquo; Previous</a>");
//        pw.println("</body></html>");
            pw.println("my name is Aarnoldii");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
