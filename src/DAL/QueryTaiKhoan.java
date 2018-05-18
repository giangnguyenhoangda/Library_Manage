/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.NhanVien;
import DTO.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QueryTaiKhoan {

    Connection con;

    public QueryTaiKhoan() {
        con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
    }

    public TaiKhoan timKiemTaiKhoan(String maNhanVien) {
        try {
            String sql = "Select * From UserPass where MaNhanVien_20151094=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String iD = rs.getString("ID_20151094");
                String pass = rs.getString("Pass_20151094");
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setTenTaiKhoan(iD);
                taiKhoan.setMatKhau(pass);
                return taiKhoan;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String timKiemMaNhanVienThongQuaTaiKhoan(TaiKhoan taiKhoan){
        try {
            String sql = "Select * From UserPass where ID_20151094=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoan.getTenTaiKhoan());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("MaNhanVien_20151094");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int themTaiKhoan(NhanVien nv) {
        try {
            String sql = "Insert Into UserPass values(?,1,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getMaNhanVien());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int xoaTaiKhoan(TaiKhoan taiKhoan) {
        try {
            String sql = "Delete From UserPass where ID_20151094=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoan.getTenTaiKhoan());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int suaTaiKhoan(TaiKhoan taiKhoan) {
        try {
            String sql = "Update UserPass set Pass_20151094=? where ID_20151094=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoan.getMatKhau());
            ps.setString(2, taiKhoan.getTenTaiKhoan());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean kiemTraTonTaiTaiKhoan(TaiKhoan taiKhoan) {

        try {
            String sql = "Select * from UserPass where ID_20151094=? AND Pass_20151094=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoan.getTenTaiKhoan());
            ps.setString(2, taiKhoan.getMatKhau());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
