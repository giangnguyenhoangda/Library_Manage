/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.QuerySach;
import DTO.Sach;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLSach {

    QuerySach qSach;

    public BLLSach() {
        qSach = new QuerySach();
    }

    public void suaTinhTrangSach(String masach, String tinhTrang) {
        Sach sach = new Sach();
        sach.setMaSach_20151094(masach);
        sach.setTinhTrang_20151094(tinhTrang);
        qSach.suaTinhTrangSach(sach);
    }

    public ArrayList<Sach> layToanBoSach() {
        return qSach.SelectFromSach();
    }

    public int laySoThuTuCuoiBangSach() {
        return qSach.laySoThuTuCuoiCuaBangSach();
    }

    public int themSach(String maSach, String tenSach, String tacGia, String nhaXuatBan, String theLoai, String tinhTrang) {
        Sach sach = new Sach();
        sach.setMaSach_20151094(maSach);
        sach.setTacGia_20151094(tacGia);
        sach.setTenSach_20151094(tenSach);
        sach.setNhaXuatBan_20151094(nhaXuatBan);
        sach.setTheLoai_20151094(theLoai);
        sach.setTinhTrang_20151094(tinhTrang);
        return qSach.insertIntoSach(sach);
    }

    public int suaSach(String maSach, String tenSach, String tacGia, String nhaXuatBan, String theLoai, String tinhTrang) {
        Sach sach = new Sach();
        sach.setMaSach_20151094(maSach);
        sach.setTacGia_20151094(tacGia);
        sach.setTenSach_20151094(tenSach);
        sach.setNhaXuatBan_20151094(nhaXuatBan);
        sach.setTheLoai_20151094(theLoai);
        sach.setTinhTrang_20151094(tinhTrang);
        return qSach.suaSach(sach);
    }

    public ArrayList<Sach> timKiemSach(String maSach, String tenSach, String tacGia, String nhaXuatBan, String theLoai, String tinhTrang) {
        Sach sach = new Sach();
        sach.setMaSach_20151094(maSach);
        sach.setTacGia_20151094(tacGia);
        sach.setTenSach_20151094(tenSach);
        sach.setNhaXuatBan_20151094(nhaXuatBan);
        sach.setTheLoai_20151094(theLoai);
        sach.setTinhTrang_20151094(tinhTrang);
        return qSach.timKiemSach(sach);
    }

    public static void main(String[] args) {
        BLLSach bll = new BLLSach();
        ArrayList<Sach> ds = bll.timKiemSach("%a%", "%%", "%%", "%%", "%%", "%%");
        for (Sach s : ds) {
            System.out.println(s.getMaSach_20151094());
        }
    }

    public int xoaSach(Sach sach) {
        return qSach.deleteSach(sach);
    }

    public HashMap<String, Integer> ketQuaThongKeSachTheoTenSach() {
        return qSach.thongKeSachTheoTenSach();
    }

    public HashMap<String, Integer> ketQuaThongKeSachTheoTacGia() {
        return qSach.thongKeSachTheoTacGia();
    }

    public HashMap<String, Integer> ketQuaThongKeSachTheoNhaXuatBan() {
        return qSach.thongKeSachTheoNhaXuatBan();
    }

    public HashMap<String, Integer> ketQuaThongKeSachTheoTheLoai() {
        return qSach.thongKeSachTheoTheLoai();
    }

    public HashMap<String, Integer> ketQuaThongKeSachTheoTinhTrang() {
        return qSach.thongKeSachTheoTinhTrang();
    }

    public boolean kiemTraTonTaiMaSach(String maSach) {
        Sach sach = new Sach();
        sach.setMaSach_20151094(maSach);
        return qSach.kiemTraTonTaiMaSach(sach);
    }

    public Sach timSachTheoMa(String maSach) {
        return qSach.timKiemSachTheoMa(maSach);
    }
}
