/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Hashtable;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class PrintNhanVien {
    Connection con;
    public PrintNhanVien(){
        con=MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
    }
    
    public void printToanBoNhanVien() {
        try {
            
            JasperReport report = JasperCompileManager.compileReport("src/ireport/NhanVien/reportToanBoNhanVien.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void printThongKeTheoTen() {
        try {
            
            JasperReport report = JasperCompileManager.compileReport("src/ireport/NhanVien/reportThongKeTheoTen.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void printThongKeTheoGioiTinh() {
        try {
            
            JasperReport report = JasperCompileManager.compileReport("src/ireport/NhanVien/reportThongKeTheoGioiTinh.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void printThongKeTheoLuong() {
        try {
            
            JasperReport report = JasperCompileManager.compileReport("src/ireport/NhanVien/reportThongKeTheoLuong.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public  void printThongKeTheoDiaChi() {
        try {
            
            JasperReport report = JasperCompileManager.compileReport("src/ireport/NhanVien/reportThongKeTheoDiaChi.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public  void printKetQuaTimKiemNhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, String phone, String luong, String diaChi) {
        try {
            File file = new File("src/ireport/Sach/reportKetQuaTimKiemSach.jrxml");
            
            JasperReport report = JasperCompileManager.compileReport("src/ireport/NhanVien/reportKetQuaTimKiem.jrxml");
            Hashtable paramenter = new Hashtable();
            paramenter.put("pMaNhanVien", maNhanVien);
            paramenter.put("pTenNhanVien", tenNhanVien);
            paramenter.put("pGioiTinh", gioiTinh);
            paramenter.put("pPhone", phone);
            paramenter.put("pLuong", luong);
            paramenter.put("pDiaChi", diaChi);
            JasperPrint print = JasperFillManager.fillReport(report, paramenter, con);
            JasperViewer view = new JasperViewer(print,false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
