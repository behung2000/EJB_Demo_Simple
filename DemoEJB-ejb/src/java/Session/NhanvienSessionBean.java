/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Session;

import Entity.Nhanvien;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author LuckyKendo
 */
@Stateless
public class NhanvienSessionBean implements NhanvienSessionBeanLocal {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public List<Nhanvien> getListAll() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoEJB-ejbPU");
        EntityManager em = factory.createEntityManager();
        
        Query query = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
        
        return query.getResultList();
    }

    @Override
    public Nhanvien findNhanvien(String cccd) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoEJB-ejbPU");
        EntityManager em = factory.createEntityManager();
        
        return em.find(Nhanvien.class, cccd);
    }
    
    public boolean CheckTen(String ten){
        if(ten==null || ten.trim().equalsIgnoreCase("") || ten.trim().equalsIgnoreCase(" ") || ten.length()<=2 || ten.length()>50){
            return false;
        }
        return true;
    }
    
    public boolean CheckSDT(String sdt){
        if(sdt.length()>=10){
            try {
                long i = Long.parseLong(sdt);
                return true;
            } catch (NumberFormatException e) {
                // fail
            }
        }
        return false;
    }
    
    @Override
    public boolean ButtonUpdateNhanvien(Nhanvien nhanvien){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoEJB-ejbPU");
        EntityManager em = factory.createEntityManager();
        
        Nhanvien NhanvienFind = em.find(Nhanvien.class, nhanvien.getCccd());
        
        if(NhanvienFind != null && CheckTen(nhanvien.getTen()) && CheckSDT(nhanvien.getSdt())){
            em.getTransaction().begin();
            NhanvienFind.setTen(nhanvien.getTen());
            NhanvienFind.setSdt(nhanvien.getSdt());
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
    
    @Override
    public boolean ButtonDeleteNhanVien(String cccd) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoEJB-ejbPU");
        EntityManager em = factory.createEntityManager();
        
        Nhanvien nhanvien = em.find(Nhanvien.class, cccd);
        
        if(nhanvien != null){
            em.getTransaction().begin();
            em.remove(nhanvien);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean ButtonInsertNhanvien(Nhanvien nhanvien) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoEJB-ejbPU");
        EntityManager em = factory.createEntityManager();
        
        Nhanvien nhanvienfind = em.find(Nhanvien.class, nhanvien.getCccd());
        if(nhanvienfind==null && nhanvien.getCccd().length()==12 && CheckTen(nhanvien.getTen()) && CheckSDT(nhanvien.getSdt())){
            em.getTransaction().begin();
            em.persist(nhanvien);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
    
}
