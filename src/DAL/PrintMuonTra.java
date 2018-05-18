/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DocGia;
import DTO.MuonTra;
import DTO.NhanVien;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class PrintMuonTra {

    static Connection con;

    public static void printDanhSachMuonTra() {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/reportDanhSachMuonTra.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new Hashtable<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printThongKeTheoMaDocGia() {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/reportThongKeTheoMaDocGia.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new Hashtable<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printThongKeTheoMaNhanVien() {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/reportThongKeTheoMaNhanVien.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new Hashtable<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printMuonSach(MuonTra mt, NhanVien nv, DocGia dg, String tongTienPhat) {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/reportMuonSach.jrxml");
            Hashtable hash = new Hashtable();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            hash.put("pMaMuonTra", mt.getMaMuonTra());
            hash.put("pMaNhanVien", nv.getMaNhanVien());
            hash.put("pTenNhanVien", nv.getTenNhanVien());
            hash.put("pMaDocGia", dg.getMaDocGia());
            hash.put("pTenDocGia", dg.getTenDocGia());
            hash.put("pGioiTinh", dg.getGioiTinh());
            hash.put("pSoDienThoai", dg.getPhone());
            hash.put("pEmail", dg.getEmail());
            hash.put("pDiaChi", dg.getDiaChi());
            hash.put("pTongTienPhat", tongTienPhat + " VND");
            Date ngayMuon = mt.getNgayMuon();
            if (ngayMuon == null) {
                hash.put("pNgayMuon", "");
            } else {
                hash.put("pNgayMuon", sdf.format(ngayMuon));
            }
            Date ngayHenTra = mt.getNgayMuon();
            if (ngayHenTra == null) {
                hash.put("pNgayHenTra", "");
            } else {
                hash.put("pNgayHenTra", sdf.format(ngayHenTra));
            }
            Integer tienCoc = mt.getTienCoc();
            if (tienCoc == null) {
                hash.put("pTienCoc", "0 VND");
            } else {
                hash.put("pTienCoc", mt.getTienCoc() + " VND");
            }
            JasperPrint print = JasperFillManager.fillReport(report, hash, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printTraSachSach(MuonTra mt, NhanVien nv, DocGia dg, String tongTienPhat, String tongTien, String tienCanTra) {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/reportTraSach.jrxml");
            Hashtable hash = new Hashtable();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            hash.put("pMaMuonTra", mt.getMaMuonTra());
            hash.put("pMaNhanVien", nv.getMaNhanVien());
            hash.put("pTenNhanVien", nv.getTenNhanVien());
            hash.put("pMaDocGia", dg.getMaDocGia());
            hash.put("pTenDocGia", dg.getTenDocGia());
            hash.put("pGioiTinh", dg.getGioiTinh());
            hash.put("pSoDienThoai", dg.getPhone());
            hash.put("pEmail", dg.getEmail());
            hash.put("pDiaChi", dg.getDiaChi());
            hash.put("pTongTienPhat", tongTienPhat);
            hash.put("pTongTien", tongTien);
            hash.put("pTienCanTra", tienCanTra);
            Date ngayMuon = mt.getNgayMuon();
            if (ngayMuon == null) {
                hash.put("pNgayMuon", "");
            } else {
                hash.put("pNgayMuon", sdf.format(ngayMuon));
            }
            Date ngayHenTra = mt.getNgayMuon();
            if (ngayHenTra == null) {
                hash.put("pNgayHenTra", "");
            } else {
                hash.put("pNgayHenTra", sdf.format(ngayHenTra));
            }
            Integer tienCoc = mt.getTienCoc();
            if (tienCoc == null) {
                hash.put("pTienCoc", "0 VND");
            } else {
                hash.put("pTienCoc", mt.getTienCoc() + " VND");
            }
            JasperPrint print = JasperFillManager.fillReport(report, hash, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printThongKeTheoNgayMuon() {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/reportThongKeTheoNgayMuon.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new Hashtable<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printThongKeTheoTienCoc() {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/reportThongKeTheoTienCoc.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new Hashtable<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printThongKeTheoNgayHenTra() {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/reportThongKeTheoNgayHenTra.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, new Hashtable<>(), con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void printKetQuaTimKiem(String maMuonTra, String maDocGia, String maNhanVien, String ngayMuon, String tienCoc, String ngayHenTra) {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/reportTimKiemMuonTra.jrxml");
            Hashtable hash = new Hashtable();
            hash.put("pMaMuonTra", maMuonTra);
            hash.put("pMaDocGia", maDocGia);
            hash.put("pMaNhanVien", maNhanVien);
            hash.put("pNgayMuon", ngayMuon);
            hash.put("pTienCoc", tienCoc);
            hash.put("pNgayHenTra", ngayHenTra);
            JasperPrint print = JasperFillManager.fillReport(report, hash, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void inSachMuonNgay(Date ngay) {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/inDanhSachSachMuonNgayHomNay.jrxml");
            Hashtable hash = new Hashtable();
            hash.put("pNgay", sdf.format(ngay));
            hash.put("pNgayHomNay", sdfSQL.format(ngay));
            JasperPrint print = JasperFillManager.fillReport(report, hash, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void inDocGiaMuonNgay(Date ngay) {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/inDanhSachDocGiaMuonSachNgay.jrxml");
            Hashtable hash = new Hashtable();
            hash.put("pNgay", sdf.format(ngay));
            hash.put("pNgayMuon", sdfSQL.format(ngay));
            JasperPrint print = JasperFillManager.fillReport(report, hash, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void inDanhSachDocGiaTraNgay(Date ngay) {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/inDanhSachDocGiaTraSachNgay.jrxml");
            Hashtable hash = new Hashtable();
            hash.put("pNgay", sdf.format(ngay));
            hash.put("pNgayTra", sdfSQL.format(ngay));
            JasperPrint print = JasperFillManager.fillReport(report, hash, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void inDanhSachSachTraNgay(Date ngay) {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/inDanhSachSachTraNgay.jrxml");
            Hashtable hash = new Hashtable();
            hash.put("pNgay", sdf.format(ngay));
            hash.put("pNgayTra", sdfSQL.format(ngay));
            JasperPrint print = JasperFillManager.fillReport(report, hash, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void inSoLuongSachMuonNgay(Date ngay) {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/inSoLuongSachMuonNgay.jrxml");
            Hashtable hash = new Hashtable();
            hash.put("pNgay", sdf.format(ngay));
            hash.put("pNgayMuon", sdfSQL.format(ngay));
            JasperPrint print = JasperFillManager.fillReport(report, hash, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void inSoLuongMuonTraNgay(Date ngay) {
        try {
            con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
            JasperReport report = JasperCompileManager.compileReport("src/ireport/MuonTra/inSoLuongMuonTraNgay.jrxml");
            Hashtable hash = new Hashtable();
            hash.put("pNgay", sdf.format(ngay));
            hash.put("pNgayHomNay", sdfSQL.format(ngay));
            JasperPrint print = JasperFillManager.fillReport(report, hash, con);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void main(String[] args) {
//        MuonTra mt = new MuonTra();
//        mt.setMaMuonTra("MT0001");
//        NhanVien nv = new NhanVien();
//        DocGia dg = new DocGia();
//        PrintMuonTra.printTraSachSach(mt, nv, dg, 10 + "", 10 + "", 10 + "");
        PrintMuonTra.printKetQuaTimKiem("%%", "%%", "%%", "%2017-11-10%", "%%", "%2017-11-11%");
    }
}
