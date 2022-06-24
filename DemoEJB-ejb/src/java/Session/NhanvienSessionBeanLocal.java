/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Session;

import Entity.Nhanvien;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author LuckyKendo
 */
@Local
public interface NhanvienSessionBeanLocal {
    List<Nhanvien> getListAll();
    Nhanvien findNhanvien(String cccd);
    boolean ButtonDeleteNhanVien(String cccd);
    boolean ButtonUpdateNhanvien(Nhanvien nhanvien);
    boolean ButtonInsertNhanvien(Nhanvien nhanvien);
}
