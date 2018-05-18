/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.QueryDocGia;
import DTO.DocGia;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLDocGia {

    QueryDocGia qDocGia;

    public BLLDocGia() {
        qDocGia = new QueryDocGia();
    }

    public ArrayList<DocGia> layDanhSachDocGia() {
        return qDocGia.SelectFromDocGia();
    }

    public ArrayList<DocGia> timKiemDocGia(String maDocGia, String tenDocGia, String gioiTinh, String phone, String email, String diaChi) {
        DocGia dg = new DocGia();
        dg.setMaDocGia(maDocGia);
        dg.setTenDocGia(tenDocGia);
        dg.setGioiTinh(gioiTinh);
        dg.setPhone(phone);
        dg.setEmail(email);
        dg.setDiaChi(diaChi);
        return qDocGia.timKiemDocGia(dg);
    }

    public int themDocGia(String maDocGia, String tenDocGia, String gioiTinh, String phone, String email, String diaChi) {
        DocGia dg = new DocGia();
        dg.setMaDocGia(maDocGia);
        dg.setTenDocGia(tenDocGia);
        dg.setGioiTinh(gioiTinh);
        dg.setPhone(phone);
        dg.setEmail(email);
        dg.setDiaChi(diaChi);
        return qDocGia.insertIntoDocGia(dg);
    }

    public int suaDocGia(String maDocGia, String tenDocGia, String gioiTinh, String phone, String email, String diaChi) {
        DocGia dg = new DocGia();
        dg.setMaDocGia(maDocGia);
        dg.setTenDocGia(tenDocGia);
        dg.setGioiTinh(gioiTinh);
        dg.setPhone(phone);
        dg.setEmail(email);
        dg.setDiaChi(diaChi);
        return qDocGia.suaDocGia(dg);
    }

    public int xoaDocGia(String maDocGia, String tenDocGia, String gioiTinh, String phone, String email, String diaChi) {
        DocGia dg = new DocGia();
        dg.setMaDocGia(maDocGia);
        dg.setTenDocGia(tenDocGia);
        dg.setGioiTinh(gioiTinh);
        dg.setPhone(phone);
        dg.setEmail(email);
        dg.setDiaChi(diaChi);
        return qDocGia.deleteDocGia(dg);
    }

    public HashMap<String, Integer> thongKeTheoTen() {
        return qDocGia.thongKeDocGiaTheoTenDocGia();
    }

    public HashMap<String, Integer> thongKeTheoGioiTinh() {
        return qDocGia.thongKeDocGiaTheoGioiTinh();
    }

    public HashMap<String, Integer> thongKeTheoDiaChi() {
        return qDocGia.thongKeDocGiaTheoDiaChi();
    }
    
    public boolean kiemTraiTonTaiMaDocGia(String maDocGia){
        return qDocGia.kiemTraTonTaiMaDocGia(maDocGia);
    }

}
