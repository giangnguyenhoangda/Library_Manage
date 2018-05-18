/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.QueryChiTietMuonTra;
import DTO.ChiTietMuonTra;
import DTO.MuonTra;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLChiTietMuonTra {
    
    QueryChiTietMuonTra qChiTietMuonTra;
    
    public BLLChiTietMuonTra() {
        qChiTietMuonTra = new QueryChiTietMuonTra();
    }
    
    public ArrayList<ChiTietMuonTra> layToanBoChiTietMuonTra() {
        return qChiTietMuonTra.SelectFromChiTietMuonTra();
    }
    
    public ArrayList<ChiTietMuonTra> timKiemChiTietMuonTra(String maMuonTra, String maSach, String ngayTra, String tienPhat) {
        ChiTietMuonTra ct = new ChiTietMuonTra();
        ct.setMaMuonTra(maMuonTra);
        ct.setMaSach(maSach);
        if (ngayTra.matches("\\s*")) {
            ct.setNgayTra(null);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                ct.setNgayTra(sdf.parse(ngayTra));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (tienPhat.matches("\\s*")) {
            ct.setTienPhat(null);
        } else {
            ct.setTienPhat(Integer.parseInt(tienPhat));
        }
        return qChiTietMuonTra.timKiemChiTietMuonTra(ct);
    }
    
    public int themChiTietMuonTra(String maMuonTra, String maSach, String ngayTra, String tienPhat) {
        ChiTietMuonTra ct = new ChiTietMuonTra();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (ngayTra.equalsIgnoreCase("null")) {
            ct.setNgayTra(null);
        } else {
            try {
                ct.setNgayTra(sdf.parse(ngayTra));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        ct.setMaMuonTra(maMuonTra);
        ct.setMaSach(maSach);
        ct.setTienPhat(Integer.parseInt(tienPhat));
        return qChiTietMuonTra.insertIntoChiTietMuonTra(ct);
    }

    public int suaChiTietMuonTra(String maMuonTra, String maSach, String ngayTra, String tienPhat) {
        ChiTietMuonTra ct = new ChiTietMuonTra();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (ngayTra.matches("\\s*")) {
            ct.setNgayTra(null);
        } else {
            try {
                ct.setNgayTra(sdf.parse(ngayTra));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        ct.setMaMuonTra(maMuonTra);
        ct.setMaSach(maSach);
        ct.setTienPhat(Integer.parseInt(tienPhat));
        return qChiTietMuonTra.suaChiTietMuonTra(ct);
    }

    public int xoaChiTietMuonTra(String maMuonTra, String maSach) {
        ChiTietMuonTra ct = new ChiTietMuonTra();
        ct.setMaMuonTra(maMuonTra);
        ct.setMaSach(maSach);
        return qChiTietMuonTra.xoaChiTietMuonTra(ct);
    }

    public HashMap<String, Integer> thongKeTheoMaMuonTra() {
        return qChiTietMuonTra.thongKeChiTietMuonTraTheoMaMuonTra();
    }

    public HashMap<String, Integer> thongKeTheoMaSach() {
        return qChiTietMuonTra.thongKeChiTietMuonTraTheoMaSach();
    }

    public HashMap<Date, Integer> thongKeTheoNgayTra() {
        return qChiTietMuonTra.thongKeChiTietMuonTraTheoNgayTra();
    }

    public HashMap<Integer, Integer> thongKeTheoTienPhat() {
        return qChiTietMuonTra.thongKeChiTietMuonTraTheoTienPhat();
    }

    public ArrayList<ChiTietMuonTra> timChiTietMuonTra(String maMuonTra) {
        MuonTra mt = new MuonTra();
        mt.setMaMuonTra(maMuonTra);
        return qChiTietMuonTra.timChiTietMuonTraTheoMuonTra(mt);
    }
}
