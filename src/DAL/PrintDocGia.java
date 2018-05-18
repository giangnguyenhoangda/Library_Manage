/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

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
public class PrintDocGia {
    Connection con;
    public PrintDocGia(){
        con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
    }
    public void printToanBoDocGia() {
        try {

            JasperReport report = JasperCompileManager.compileReport("src/ireport/DocGia/reportToanBoDocGia.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void printThongKeTheoTen() {
        try {

            JasperReport report = JasperCompileManager.compileReport("src/ireport/DocGia/reportThongKeTheoTen.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public  void printThongKeTheoGioiTinh() {
        try {

            JasperReport report = JasperCompileManager.compileReport("src/ireport/DocGia/reportThongKeTheoGioiTinh.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void printThongKeTheoDiaChi() {
        try {

            JasperReport report = JasperCompileManager.compileReport("src/ireport/DocGia/reportThongKeTheoDiaChi.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void printKetQuaTimKiemDocGia(String maDocGia, String tenDocGia, String gioiTinh, String phone, String email, String diaChi) {
        try {

            JasperReport report = JasperCompileManager.compileReport("src/ireport/DocGia/reportKetQuaTimKiem.jrxml");
            Hashtable paramenter = new Hashtable();
            paramenter.put("pMaDocGia", maDocGia);
            paramenter.put("pTenDocGia", tenDocGia);
            paramenter.put("pGioiTinh", gioiTinh);
            paramenter.put("pPhone", phone);
            paramenter.put("pEmail", email);
            paramenter.put("pDiaChi", diaChi);
            JasperPrint print = JasperFillManager.fillReport(report, paramenter, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
