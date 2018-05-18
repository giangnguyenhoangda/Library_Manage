/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DocGia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QueryDocGia {

    Connection con;

    public QueryDocGia() {
        con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
    }

    public ArrayList<DocGia> SelectFromDocGia() {
        ArrayList<DocGia> ds = new ArrayList<>();
        try {
            String sql = "Select * from DocGia";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maDocGia = rs.getString("MaDocGia_20151094").trim();
                String tenDocGia = rs.getString("TenDocGia_20151094");
                String gioiTinh = rs.getString("GioiTinh_20151094").trim();
                String phone = rs.getString("Phone_20151094").trim();
                String email = rs.getString("Email_20151094");
                String diaChi = rs.getString("DiaChi_20151094");
                DocGia dg = new DocGia();
                dg.setMaDocGia(maDocGia);
                dg.setTenDocGia(tenDocGia);
                dg.setGioiTinh(gioiTinh);
                dg.setPhone(phone);
                dg.setEmail(email);
                dg.setDiaChi(diaChi);
                ds.add(dg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public int insertIntoDocGia(DocGia dg) {
        int kq = 0;
        String sql = "Insert Into DocGia values(?,?,?,?,?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dg.getMaDocGia());
            ps.setString(2, dg.getTenDocGia());
            ps.setString(3, dg.getGioiTinh());
            ps.setString(4, dg.getPhone());
            ps.setString(5, dg.getEmail());
            ps.setString(6, dg.getDiaChi());
            kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int suaDocGia(DocGia dg) {
        String sql = "Update DocGia set TenDocGia_20151094=?,GioiTinh_20151094=?,Phone_20151094=?,Email_20151094=?,DiaChi_20151094=? where MaDocGia_20151094=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dg.getTenDocGia());
            ps.setString(2, dg.getGioiTinh());
            ps.setString(3, dg.getPhone());
            ps.setString(4, dg.getEmail());
            ps.setString(5, dg.getDiaChi());
            ps.setString(6, dg.getMaDocGia());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteDocGia(DocGia dg) {
        String sql = "delete from DocGia where MaDocGia_20151094=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dg.getMaDocGia());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<DocGia> timKiemDocGia(DocGia dg) {

        ArrayList<DocGia> ds = new ArrayList<>();
        String sql = "SELECT * FROM DocGia "
                + "where MaDocGia_20151094 like ? AND TenDocGia_20151094 like ? "
                + "AND GioiTinh_20151094 like ? AND Phone_20151094 like ? AND Email_20151094 like ? AND DiaChi_20151094 like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + dg.getMaDocGia() + "%");
            ps.setString(2, "%" + dg.getTenDocGia() + "%");
            ps.setString(3, "%" + dg.getGioiTinh() + "%");
            ps.setString(4, "%" + dg.getPhone() + "%");
            ps.setString(5, "%" + dg.getEmail() + "%");
            ps.setString(6, "%" + dg.getDiaChi() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maDocGia = rs.getString("MaDocGia_20151094").trim();
                String tenDocGia = rs.getString("TenDocGia_20151094");
                String gioiTinh = rs.getString("GioiTinh_20151094").trim();
                String phone = rs.getString("Phone_20151094").trim();
                String email = rs.getString("Email_20151094");
                String diaChi = rs.getString("DiaChi_20151094");
                DocGia dgTimThay = new DocGia();
                dgTimThay.setMaDocGia(maDocGia);
                dgTimThay.setTenDocGia(tenDocGia);
                dgTimThay.setGioiTinh(gioiTinh);
                dgTimThay.setPhone(phone);
                dgTimThay.setEmail(email);
                dgTimThay.setDiaChi(diaChi);
                ds.add(dgTimThay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<String, Integer> thongKeDocGiaTheoTenDocGia() {
        HashMap<String, Integer> ds = new HashMap<>();
        String sql = "SELECT TenDocGia_20151094, COUNT(*) SoLuong_20151094 FROM DocGia GROUP BY TenDocGia_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tneDocGia = rs.getString("TenDocGia_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                ds.put(tneDocGia, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<String, Integer> thongKeDocGiaTheoGioiTinh() {
        HashMap<String, Integer> ds = new HashMap<>();
        String sql = "SELECT GioiTinh_20151094, COUNT(*) SoLuong_20151094 FROM DocGia GROUP BY GioiTinh_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String gioiTinh = rs.getString("GioiTinh_20151094").trim();
                int soLuong = rs.getInt("SoLuong_20151094");
                ds.put(gioiTinh, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<String, Integer> thongKeDocGiaTheoDiaChi() {
        HashMap<String, Integer> ds = new HashMap<>();
        String sql = "SELECT DiaChi_20151094, COUNT(*) SoLuong_20151094 FROM DocGia GROUP BY DiaChi_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String diaChi = rs.getString("DiaChi_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                ds.put(diaChi, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public int laySoThuTuCuoiCuaBangDocGia() {
        int stt = 0;
        String maDocGia = "";
        try {
            String sql = "Select MaDocGia_20151094 from DocGia";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maDocGia = rs.getString(1).trim();
            }
            if (!maDocGia.equals("")) {
                maDocGia = maDocGia.substring(2);
                stt = Integer.parseInt(maDocGia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stt;
    }

    public boolean kiemTraTonTaiMaDocGia(String maDocGia) {
        String sql = "Select MaDocGia_20151094 from DocGia where MaDocGia_20151094=?";
        String maDocGiaTimDuoc = "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maDocGia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maDocGiaTimDuoc = rs.getString("MaDocGia_20151094").trim();
            }
            if (maDocGiaTimDuoc.equals("")) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
