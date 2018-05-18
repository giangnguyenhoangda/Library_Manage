/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.QueryNhanVien;
import DAL.QueryTaiKhoan;
import DTO.NhanVien;
import DTO.TaiKhoan;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLNhanVien {

    QueryNhanVien qNhanVien;
    QueryTaiKhoan qTaiKhoan;

    public BLLNhanVien() {
        qNhanVien = new QueryNhanVien();
        qTaiKhoan = new QueryTaiKhoan();
    }

    public ArrayList<NhanVien> timKiemNhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, String phone, String luong, String diaChi) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien(maNhanVien);
        nhanVien.setTenNhanVien(tenNhanVien);
        nhanVien.setGioiTinh(gioiTinh);
        nhanVien.setPhone(phone);
        if (luong.matches("\\s*")) {
            nhanVien.setLuong(null);
        } else {
            nhanVien.setLuong(Integer.parseInt(luong));
        }
        nhanVien.setDiaChi(diaChi);
        return qNhanVien.timKiemNhanVien(nhanVien);
    }

    public ArrayList<NhanVien> layToanBoNhanVien() {
        return qNhanVien.SelectFromNhanVien();
    }

    public int themNhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, String phone, int luong, String diaChi) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien(maNhanVien);
        nhanVien.setTenNhanVien(tenNhanVien);
        nhanVien.setGioiTinh(gioiTinh);
        nhanVien.setPhone(phone);
        nhanVien.setLuong(luong);
        nhanVien.setDiaChi(diaChi);
        return qNhanVien.insertIntoNhanVien(nhanVien);
    }

    public int suaNhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, String phone, int luong, String diaChi) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien(maNhanVien);
        nhanVien.setTenNhanVien(tenNhanVien);
        nhanVien.setGioiTinh(gioiTinh);
        nhanVien.setPhone(phone);
        nhanVien.setLuong(luong);
        nhanVien.setDiaChi(diaChi);
        return qNhanVien.suaNhanVien(nhanVien);
    }

    public int xoaNhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, String phone, String luong, String diaChi) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien(maNhanVien);
        nhanVien.setTenNhanVien(tenNhanVien);
        nhanVien.setGioiTinh(gioiTinh);
        nhanVien.setPhone(phone);
        nhanVien.setLuong(null);
        nhanVien.setDiaChi(diaChi);
        return qNhanVien.deleteNhanVien(nhanVien);
    }

    public HashMap<String, Integer> thongKeTheoTen() {
        return qNhanVien.thongKeNhanVienTheoTenNhanVien();
    }

    public HashMap<String, Integer> thongKeTheoGioiTinh() {
        return qNhanVien.thongKeNhanVienTheoGioiTinh();
    }

    public HashMap<String, Integer> thongKeTheoLuong() {
        return qNhanVien.thongKeNhanVienTheoLuong();
    }

    public HashMap<String, Integer> thongKeTheoDiaChi() {
        return qNhanVien.thongKeNhanVienTheoDiaChi();
    }

    public NhanVien timKiemThongTinNhanVienTheoMa(String maNhanVien) {
        return qNhanVien.timKiemNhanVienThongQuaMaNhanVien(maNhanVien);
    }

    public NhanVien timKiemThongTinNhanVienQuaTaiKhoan(String tenDangNhap) {
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenTaiKhoan(tenDangNhap);
        return qNhanVien.timKiemNhanVienQuaTaiKhoan(taiKhoan);
    }

    public boolean kiemTraTontaiMaNhanVien(String maNhanVien) {
        return qNhanVien.kiemTraTonTaiMaNhanVien(maNhanVien);
    }
}
