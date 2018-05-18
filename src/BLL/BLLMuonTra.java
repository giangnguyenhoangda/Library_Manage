/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.QueryMuonTra;
import DTO.DocGia;
import DTO.MuonTra;
import DTO.Sach;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLMuonTra {

    QueryMuonTra qMuonTra;

    public BLLMuonTra() {
        qMuonTra = new QueryMuonTra();
    }

    public static void main(String[] args) {
        BLLMuonTra bll = new BLLMuonTra();
        ArrayList<MuonTra> ds = bll.timKiemMuonTra("", "", "", "13/11/2017", "", "");
        for (MuonTra mt : ds) {
            System.out.println("" + mt.getMaMuonTra() + " " + mt.getMaDocGia());
        }
//        String s = "13/11/2017";
//        if (s.matches("\\s*")) {
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
    }

    public ArrayList<MuonTra> timKiemMuonTra(String maMuonTra, String maDocGia, String maNhanVien, String NgayMuon, String ngayHenTra, String tienCoc) {
        MuonTra mt = new MuonTra();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyy");
        mt.setMaMuonTra(maMuonTra);
        mt.setMaDocGia(maDocGia);
        mt.setMaNhanVien(maNhanVien);
        try {
            if (NgayMuon.matches("\\s*")) {
                mt.setNgayMuon(null);
            } else {
                mt.setNgayMuon(sdf.parse(NgayMuon));
            }
            if (ngayHenTra.matches("\\s*")) {
                mt.setNgayHenTra(null);
            } else {
                mt.setNgayHenTra(sdf.parse(ngayHenTra));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tienCoc.matches("\\s*")) {
            mt.setTienCoc(null);
        } else {
            mt.setTienCoc(Integer.parseInt(tienCoc));
        }
        System.out.println("Mã mượn trả:" + maMuonTra);
        System.out.println("Mã độc giả:" + maDocGia);
        System.out.println("Mã nhân viên:" + maNhanVien);
        if (mt.getNgayMuon() == null) {
            System.out.println("Ngày mượn:");
        } else {
            System.out.println("Ngày mượn:" + sdf.format(mt.getNgayMuon()));
        }
        if (mt.getNgayHenTra() == null) {
            System.out.println("Ngày hẹn trả:");
        } else {
            System.out.println("Ngày hẹn trả:" + sdf.format(mt.getNgayHenTra()));
        }
        if (mt.getTienCoc() == null) {
            System.out.println("Tiền cọc:");
        } else {
            System.out.println("Tiền cọc:" + mt.getTienCoc());
        }

        return qMuonTra.timKiemMuonTra(mt);
    }

    public ArrayList<MuonTra> layToanBoMuonTra() {
        return qMuonTra.SelectFromMuonTra();
    }

    public int themMuonTra(String maMuonTra, String maDocGia, String maNhanVien, Date NgayMuon, Date ngayHenTra, int tienCoc) {
        MuonTra mt = new MuonTra();
        mt.setMaMuonTra(maMuonTra);
        mt.setMaDocGia(maDocGia);
        mt.setMaNhanVien(maNhanVien);
        mt.setNgayMuon(NgayMuon);
        mt.setNgayHenTra(ngayHenTra);
        mt.setTienCoc(tienCoc);
        return qMuonTra.insertIntoMuonTra(mt);
    }

    public int suaMuonTra(String maMuonTra, String maDocGia, String maNhanVien, Date NgayMuon, Date ngayHenTra, int tienCoc) {
        MuonTra mt = new MuonTra();
        mt.setMaMuonTra(maMuonTra);
        mt.setMaDocGia(maDocGia);
        mt.setMaNhanVien(maNhanVien);
        mt.setNgayMuon(NgayMuon);
        mt.setNgayHenTra(ngayHenTra);
        mt.setTienCoc(tienCoc);
        return qMuonTra.suaMuonTra(mt);
    }

    public int xoaMuonTra(String maMuonTra, String maDocGia, String maNhanVien, Date NgayMuon, Date ngayHenTra, int tienCoc) {
        MuonTra mt = new MuonTra();
        mt.setMaMuonTra(maMuonTra);
        mt.setMaDocGia(maDocGia);
        mt.setMaNhanVien(maNhanVien);
        mt.setNgayMuon(NgayMuon);
        mt.setNgayHenTra(ngayHenTra);
        mt.setTienCoc(tienCoc);
        return qMuonTra.deleteMuonTra(mt);
    }

    public HashMap<String, Integer> thongKeTheoMaDocGia() {
        return qMuonTra.thongKeMuonTraTheoMaDocGia();
    }

    public HashMap<String, Integer> thongKeTheoMaNhanVien() {
        return qMuonTra.thongKeMuonTraTheoMaNhanVien();
    }

    public HashMap<Date, Integer> thongKeTheoNgayMuon() {
        return qMuonTra.thongKeMuonTraTheoNgayMuon();
    }

    public HashMap<Date, Integer> thongKeTheoNgayHenTra() {
        return qMuonTra.thongKeMuonTraTheoNgayHenTra();
    }

    public HashMap<Integer, Integer> thongKeTheoTienCoc() {
        return qMuonTra.thongKeMuonTraTheoTienCoc();
    }

    public boolean kiemTraTonTaiMaMuonTra(String maMuonTra) {
        MuonTra mt = new MuonTra();
        mt.setMaMuonTra(maMuonTra);
        return qMuonTra.kiemTraTonTaiMaMuonTra(mt);
    }

    public int demMuonTraTrongNgay(Date ngay) {
        return qMuonTra.thongKeMuonTraTrongNgay(ngay);
    }

    public ArrayList<Sach> sachMuonNgay(Date ngay) {
        return qMuonTra.sachMuonNgay(ngay);
    }

    public ArrayList<Sach> sachTraNgay(Date ngay) {
        return qMuonTra.sachTraNgay(ngay);
    }

    public ArrayList<DocGia> docGiaMuonNgay(Date ngay) {
        return qMuonTra.docGiaMuonNgay(ngay);
    }

    public ArrayList<DocGia> docGiaTraNgay(Date ngay) {
        return qMuonTra.docGiaTraNgay(ngay);
    }

    public int soSachMuonNgay(Date ngay) {
        return qMuonTra.soLuongSachMuonNgay(ngay);
    }
}
