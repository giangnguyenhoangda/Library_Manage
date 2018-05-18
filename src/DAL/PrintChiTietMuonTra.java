/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
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
public class PrintChiTietMuonTra {
    static Connection con;

    public static void printDanhSachChiTietMuonTra() {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/ChiTietMuonTra/inDanhSachChiTietMuonTra.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new Hashtable<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printThongKeTheoMaMuonTra() {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/ChiTietMuonTra/reportThongKeTheoMaMuonTra.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new Hashtable<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printThongKeTheoMaSach() {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/ChiTietMuonTra/reportThongKeTheoMaSach.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new Hashtable<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printThongKeTheoNgayTra() {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/ChiTietMuonTra/reportThongKeTheoNgayTra.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new Hashtable<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printThongKeTheoTienPhat() {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/ChiTietMuonTra/reportThongKeTheoTienPhat.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new Hashtable<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printTimKiemChiTietMuonTra(String maMuonTra, String maSach, String ngayTra, String tienPhat) {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/ChiTietMuonTra/reportTimKiemChiTietMuonTra.jrxml");
            Hashtable hash = new Hashtable();
            hash.put("pMaMuonTra", maMuonTra);
            hash.put("pMaSach", maSach);
            hash.put("pNgayTra", ngayTra);
            hash.put("pTienPhat", tienPhat);
            JasperPrint print = JasperFillManager.fillReport(report, hash, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        printDanhSachChiTietMuonTra();
    }
}
