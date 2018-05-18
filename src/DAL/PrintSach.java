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
public class PrintSach {

    Connection con;

    public PrintSach() {
        con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
    }

    public void printToanBoSach() {
        try {
            JasperReport report = JasperCompileManager.compileReport("src/ireport/Sach/reportToanBoSach.jrxml");

            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void printKetQuaTimKiemSach(String maSach, String tenSach, String tacGia, String nhaXuatBan, String theLoai, String tinhTrang) {
        try {
            File file = new File("src/ireport/Sach/reportKetQuaTimKiemSach.jrxml");

            JasperReport report = JasperCompileManager.compileReport("src/ireport/Sach/reportKetQuaTimKiemSach.jrxml");
            Hashtable paramenter = new Hashtable();
            paramenter.put("pMaSach", maSach);
            paramenter.put("pTenSach", tenSach);
            paramenter.put("pTacGia", tacGia);
            paramenter.put("pNhaXuatBan", nhaXuatBan);
            paramenter.put("pTheLoai", theLoai);
            paramenter.put("pTinhTrang", tinhTrang);
            JasperPrint print = JasperFillManager.fillReport(report, paramenter, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void printThongKeSachTheoTenSach() {
        try {
            JasperReport report = JasperCompileManager.compileReport("src/ireport/Sach/reportThongKeTheoTenSach.jrxml");

            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void printThongKeSachTheoTacGia() {
        try {
            JasperReport report = JasperCompileManager.compileReport("src/ireport/Sach/reportThongKeTheoTacGia.jrxml");

            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void printThongKeSachTheoNhaXuatBan() {
        try {
            JasperReport report = JasperCompileManager.compileReport("src/ireport/Sach/reportThongKeTheoNhaXuatBan.jrxml");

            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void printThongKeSachTheoTheLoai() {
        try {
            JasperReport report = JasperCompileManager.compileReport("src/ireport/Sach/reportThongKeTheoTheLoai.jrxml");

            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void printThongKeSachTheoTinhTrang() {
        try {
            JasperReport report = JasperCompileManager.compileReport("src/ireport/Sach/reportThongKeTheoTinhTrang.jrxml");

            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
