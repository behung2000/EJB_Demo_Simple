/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import Entity.Nhanvien;
import Session.NhanvienSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author LuckyKendo
 */
@ManagedBean(name="nhanvienServlet")
public class NhanvienServlet extends HttpServlet implements Serializable{
    @EJB
    private NhanvienSessionBeanLocal nhanvienSessionBeanLocal;
    
    //HttpServlet -> hàm đẩy dữ liệu post -> Để làm delete insert và update
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String cccd = req.getParameter("cccd");
        String ten = req.getParameter("ten");
        String sdt = req.getParameter("sdt");
        Nhanvien nhanvien = new Nhanvien(cccd, ten, sdt);
        if ("Delete".equalsIgnoreCase(action)) {
            if(nhanvienSessionBeanLocal.ButtonDeleteNhanVien(cccd)==true){
                //Delete success
                req.getRequestDispatcher("index.xhtml").forward(req, resp);
            } else {
                //Delete fail
                req.getRequestDispatcher("Invalid.xhtml").forward(req, resp);
            }
        }
        else if("Update".equalsIgnoreCase(action)){
            if(nhanvienSessionBeanLocal.ButtonUpdateNhanvien(nhanvien)){
                //Update success
                req.getRequestDispatcher("index.xhtml").forward(req, resp);
            }
            else {
                //Update fail
                req.getRequestDispatcher("Invalid.xhtml").forward(req, resp);
            }
        }
        else if("Insert".equalsIgnoreCase(action)){
            if(nhanvienSessionBeanLocal.ButtonInsertNhanvien(nhanvien)){
                //Insert success
                req.getRequestDispatcher("index.xhtml").forward(req, resp);
            }
            else{
                //Insert fail
                req.getRequestDispatcher("Invalid.xhtml").forward(req, resp);
            }
        }
        else{
            //Fail
            req.getRequestDispatcher("Invalid.xhtml").forward(req, resp);
        }
    }

    //HttpServlet -> Hàm lấy get -> để tìm kiếm (Chưa làm)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    //Serializable -> Lấy danh sách bảng
    public List<Nhanvien> getNhanvienList() {
        return nhanvienSessionBeanLocal.getListAll();
    }
    
}
