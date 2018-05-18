/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QuerySach {

    Connection con;

    public QuerySach() {
        con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
    }

    public void suaTinhTrangSach(Sach sach) {
        try {
            String sql = "update Sach set TinhTrang_20151094=? where MaSach_20151094=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sach.getTinhTrang_20151094());
            ps.setString(2, sach.getMaSach_20151094());
            int kq = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sach> SelectFromSach() {
        ArrayList<Sach> dsSach = new ArrayList<Sach>();
        try {
            String sql = "Select * from Sach";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach sach = new Sach();
                String maSach = rs.getString("MaSach_20151094");
                String tenSach = rs.getString("TenSach_20151094");
                String tacGia = rs.getString("TacGia_20151094");
                String nhaXuatBan = rs.getString("NhaXuatBan_20151094");
                String theLoai = rs.getString("TheLoai_20151094");
                String tinhTrang = rs.getString("TinhTrang_20151094");
                sach.setMaSach_20151094(maSach);
                sach.setTacGia_20151094(tacGia);
                sach.setTenSach_20151094(tenSach);
                sach.setNhaXuatBan_20151094(nhaXuatBan);
                sach.setTheLoai_20151094(theLoai);
                sach.setTinhTrang_20151094(tinhTrang);
                dsSach.add(sach);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsSach;
    }

    public int insertIntoSach(Sach sach) {
        String sql = "Insert Into Sach values(?,?,?,?,?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sach.getMaSach_20151094());
            ps.setString(2, sach.getTenSach_20151094());
            ps.setString(3, sach.getTacGia_20151094());
            ps.setString(4, sach.getNhaXuatBan_20151094());
            ps.setString(5, sach.getTheLoai_20151094());
            ps.setString(6, sach.getTinhTrang_20151094());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int suaSach(Sach sach) {
        String sql = "Update Sach set TenSach_20151094=?,TacGia_20151094=?,NhaXuatBan_20151094=?,TheLoai_20151094=?,TinhTrang_20151094=? where MaSach_20151094=?";
        int kq = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sach.getTenSach_20151094());
            ps.setString(2, sach.getTacGia_20151094());
            ps.setString(3, sach.getNhaXuatBan_20151094());
            ps.setString(4, sach.getTheLoai_20151094());
            ps.setString(5, sach.getTinhTrang_20151094());
            ps.setString(6, sach.getMaSach_20151094());
            kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int deleteSach(Sach sach) {
        int kq = 0;
        String sql = "delete from Sach where MaSach_20151094=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sach.getMaSach_20151094());
            kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ArrayList<Sach> timKiemSach(Sach sach) {

        ArrayList<Sach> dsSach = new ArrayList<Sach>();
        String sql = "SELECT * FROM Sach "
                + "where MaSach_20151094 like ? AND TenSach_20151094 like ? "
                + "AND TacGia_20151094 like ? AND NhaXuatBan_20151094 like ? AND TheLoai_20151094 like ? AND TinhTrang_20151094 like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sach.getMaSach_20151094());
            ps.setString(2, sach.getTenSach_20151094());
            ps.setString(3, sach.getTacGia_20151094());
            ps.setString(4, sach.getNhaXuatBan_20151094());
            ps.setString(5, sach.getTheLoai_20151094());
            ps.setString(6, sach.getTinhTrang_20151094());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maSach = rs.getString("MaSach_20151094");
                String tenSach = rs.getString("TenSach_20151094");
                String tacGia = rs.getString("TacGia_20151094");
                String nhaXuatBan = rs.getString("NhaXuatBan_20151094");
                String theLoai = rs.getString("TheLoai_20151094");
                String tinhTrang = rs.getString("TinhTrang_20151094");

                Sach sachTimThay = new Sach();
                sachTimThay.setMaSach_20151094(maSach);
                sachTimThay.setTacGia_20151094(tacGia);
                sachTimThay.setTenSach_20151094(tenSach);
                sachTimThay.setNhaXuatBan_20151094(nhaXuatBan);
                sachTimThay.setTheLoai_20151094(theLoai);
                sachTimThay.setTinhTrang_20151094(tinhTrang);
                dsSach.add(sachTimThay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsSach;
    }

    public static void main(String[] args) {
        Sach sa = new Sach();
        sa.setMaSach_20151094("%%");
        sa.setTenSach_20151094("%%");
        sa.setTacGia_20151094("%%");
        sa.setNhaXuatBan_20151094("%%");
        sa.setTheLoai_20151094("%%");
        sa.setTinhTrang_20151094("%%");
        QuerySach q = new QuerySach();
        ArrayList<Sach> ds = q.timKiemSach(sa);
        for (Sach s : ds) {
            System.out.println(s.getMaSach_20151094() + "  " + s.getTenSach_20151094() + "  " + s.getTacGia_20151094() + "  " + s.getNhaXuatBan_20151094());
        }
        Sach s = q.timKiemSachTheoMa("MS000001");
        System.out.println(s.getMaSach_20151094() + " " + s.getTenSach_20151094() + " " + s.getTacGia_20151094() + " " + s.getNhaXuatBan_20151094() + " " + s.getTheLoai_20151094());
    }

    public HashMap<String, Integer> thongKeSachTheoTenSach() {
        HashMap<String, Integer> dsThongKe = new HashMap<String, Integer>();
        String sql = "SELECT TenSach_20151094, COUNT(*) SoLuong_20151094 From Sach GROUP BY TenSach_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenSach = rs.getString("TenSach_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                dsThongKe.put(tenSach, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsThongKe;
    }

    public HashMap<String, Integer> thongKeSachTheoTacGia() {
        HashMap<String, Integer> dsThongKe = new HashMap<String, Integer>();
        String sql = "SELECT TacGia_20151094, COUNT(*) SoLuong_20151094 From Sach GROUP BY TacGia_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenSach = rs.getString("TacGia_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                dsThongKe.put(tenSach, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsThongKe;
    }

    public HashMap<String, Integer> thongKeSachTheoNhaXuatBan() {
        HashMap<String, Integer> dsThongKe = new HashMap<String, Integer>();
        String sql = "SELECT NhaXuatBan_20151094, COUNT(*) SoLuong_20151094 From Sach GROUP BY NhaXuatBan_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenSach = rs.getString("NhaXuatBan_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                dsThongKe.put(tenSach, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsThongKe;
    }

    public HashMap<String, Integer> thongKeSachTheoTheLoai() {
        HashMap<String, Integer> dsThongKe = new HashMap<String, Integer>();
        String sql = "SELECT TheLoai_20151094, COUNT(*) SoLuong_20151094 From Sach GROUP BY TheLoai_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenSach = rs.getString("TheLoai_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                dsThongKe.put(tenSach, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsThongKe;
    }

    public HashMap<String, Integer> thongKeSachTheoTinhTrang() {
        HashMap<String, Integer> dsThongKe = new HashMap<String, Integer>();
        String sql = "SELECT TinhTrang_20151094, COUNT(*) SoLuong_20151094 From Sach GROUP BY TinhTrang_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenSach = rs.getString("TinhTrang_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                dsThongKe.put(tenSach, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsThongKe;
    }

    public int laySoThuTuCuoiCuaBangSach() {
        int stt = 0;
        String maSach = "";
        try {
            String sql = "Select MaSach_20151094 from Sach";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maSach = rs.getString(1).trim();
            }
            if (!maSach.equals("")) {
                maSach = maSach.substring(2);
                stt = Integer.parseInt(maSach);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stt;
    }

    public boolean kiemTraTonTaiMaSach(Sach sach) {
        String sql = "Select MaSach_20151094 from Sach where MaSach_20151094=?";
        String maDocGiaTimDuoc = "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sach.getMaSach_20151094());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maDocGiaTimDuoc = rs.getString("MaSach_20151094").trim();
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

    public Sach timKiemSachTheoMa(String maSach) {
        Sach sach = new Sach();
        String sql = "SELECT * FROM Sach "
                + "where MaSach_20151094=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maSach);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sach.setMaSach_20151094(maSach);
                sach.setTenSach_20151094(rs.getString("TenSach_20151094"));
                sach.setTacGia_20151094(rs.getString("TacGia_20151094"));
                sach.setNhaXuatBan_20151094(rs.getString("NhaXuatBan_20151094"));
                sach.setTheLoai_20151094(rs.getString("TheLoai_20151094"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sach;
    }
}
