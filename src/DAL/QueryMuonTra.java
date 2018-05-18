/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DocGia;
import DTO.MuonTra;
import DTO.NhanVien;
import DTO.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QueryMuonTra {

    Connection con;

    public QueryMuonTra() {
        con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
    }

    public ArrayList<MuonTra> timKiemMuonTra(MuonTra mt) {

        ArrayList<MuonTra> ds = new ArrayList<>();
        String sql = "SELECT * FROM MuonTra "
                + "where MaMuonTra_20151094 like ? AND MaDocGia_20151094 like ? "
                + "AND MaNhanVien_20151094 like ? AND NgayMuon_20151094 like ? AND NgayHenTra_20151094 like ? AND TienCoc_20151094 like ?";
        try {
            SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + mt.getMaMuonTra() + "%");
            ps.setString(2, "%" + mt.getMaDocGia() + "%");
            ps.setString(3, "%" + mt.getMaNhanVien() + "%");
            if (mt.getNgayMuon() == null) {
                ps.setString(4, "%%");
            } else {
                ps.setString(4, "%" + sdfSQL.format(mt.getNgayMuon()) + "%");
            }
            if (mt.getNgayHenTra() == null) {
                ps.setString(5, "%%");
            } else {
                ps.setString(5, "%" + sdfSQL.format(mt.getNgayHenTra()) + "%");
            }
            if (mt.getTienCoc() == null) {
                ps.setString(6, "%%");
            } else {
                ps.setString(6, "%" + mt.getTienCoc() + "%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMuonTra = rs.getString("MaMuonTra_20151094");
                String maNhanVien = rs.getString("MaNhanVien_20151094");
                String maDocGia = rs.getString("MaDocGia_20151094");
                java.sql.Date ngayMuonSQL = rs.getDate("NgayMuon_20151094");
                Date ngayMuon = new Date(ngayMuonSQL.getTime());
                java.sql.Date ngayHenTraSQL = rs.getDate("NgayHenTra_20151094");
                Date ngayHenTra = new Date(ngayHenTraSQL.getTime());
                Integer tienCoc = rs.getInt("TienCoc_20151094");
                MuonTra mtTimThay = new MuonTra();
                mtTimThay.setMaMuonTra(maMuonTra);
                mtTimThay.setMaDocGia(maDocGia);
                mtTimThay.setMaNhanVien(maNhanVien);
                mtTimThay.setTienCoc(tienCoc);
                mtTimThay.setNgayMuon(ngayMuon);
                mtTimThay.setNgayHenTra(ngayHenTra);
                ds.add(mtTimThay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public static void main(String[] args) {
        QueryMuonTra q = new QueryMuonTra();
        MuonTra mt = new MuonTra();
        mt.setMaMuonTra("%%");
        mt.setMaDocGia("%%");
        mt.setMaNhanVien("%%");
        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
            mt.setNgayMuon(sdf2.parse("10/11/2017"));
            mt.setNgayHenTra(null);
            mt.setTienCoc(null);
            ArrayList<MuonTra> ds = q.timKiemMuonTra(mt);
            for (MuonTra m : ds) {
                System.out.println(m.getMaMuonTra() + "  " + m.getMaDocGia());
            }
        } catch (Exception e) {

        }

    }

    public ArrayList<MuonTra> SelectFromMuonTra() {
        ArrayList<MuonTra> ds = new ArrayList<>();
        String sql = "Select * from MuonTra";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MuonTra mt = new MuonTra();
                mt.setMaMuonTra(rs.getString("MaMuonTra_20151094"));
                mt.setMaDocGia(rs.getString("MaDocGia_20151094").trim());
                mt.setMaNhanVien(rs.getString("MaNhanVien_20151094").trim());
                java.sql.Date ngayMuonSQL = rs.getDate("NgayMuon_20151094");
                if (ngayMuonSQL == null) {
                    mt.setNgayMuon(null);
                } else {
                    mt.setNgayMuon(new Date(ngayMuonSQL.getTime()));
                }
                java.sql.Date ngayHenTra = rs.getDate("NgayHenTra_20151094");
                if (ngayHenTra == null) {
                    mt.setNgayHenTra(null);
                } else {
                    mt.setNgayHenTra(new Date(ngayHenTra.getTime()));
                }
                mt.setTienCoc(rs.getInt("TienCoc_20151094"));
                ds.add(mt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public int insertIntoMuonTra(MuonTra mt) {
        String sql = "Insert Into MuonTra values(?,?,?,?,?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mt.getMaMuonTra());
            ps.setString(2, mt.getMaDocGia());
            ps.setString(3, mt.getMaNhanVien());
            ps.setDate(4, new java.sql.Date(mt.getNgayMuon().getTime()));
            ps.setInt(5, mt.getTienCoc());
            ps.setDate(6, new java.sql.Date(mt.getNgayHenTra().getTime()));
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int suaMuonTra(MuonTra mt) {
        String sql = "Update MuonTra set MaDocGia_20151094=?,MaNhanVien_20151094=?,NgayMuon_20151094=?,NgayHenTra_20151094=?,TienCoc_20151094=? where MaMuonTra_20151094=?";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mt.getMaDocGia());
            ps.setString(2, mt.getMaNhanVien());
            ps.setDate(3, new java.sql.Date(mt.getNgayMuon().getTime()));
            ps.setDate(4, new java.sql.Date(mt.getNgayHenTra().getTime()));
            ps.setInt(5, mt.getTienCoc());
            ps.setString(6, mt.getMaMuonTra());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteMuonTra(MuonTra mt) {
        try {
            String sqlXoaChiTiet = "delete from ChiTietMuonTra where MaMuonTra_20151094=?";
            PreparedStatement ps1 = con.prepareStatement(sqlXoaChiTiet);
            ps1.setString(1, mt.getMaMuonTra());
            int kq1 = ps1.executeUpdate();
            String sql = "delete from MuonTra where MaMuonTra_20151094=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mt.getMaMuonTra());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public HashMap<String, Integer> thongKeMuonTraTheoMaDocGia() {
        HashMap<String, Integer> ds = new HashMap<>();
        String sql = "SELECT MaDocGia_20151094, COUNT(*) SoLuong_20151094 FROM MuonTra GROUP BY MaDocGia_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maDocGia = rs.getString("MaDocGia_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                ds.put(maDocGia, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<String, Integer> thongKeMuonTraTheoMaNhanVien() {
        HashMap<String, Integer> ds = new HashMap<>();
        String sql = "SELECT MaNhanVien_20151094, COUNT(*) SoLuong_20151094 FROM MuonTra GROUP BY MaNhanVien_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maDocGia = rs.getString("MaNhanVien_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                ds.put(maDocGia, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<Date, Integer> thongKeMuonTraTheoNgayMuon() {
        HashMap<Date, Integer> ds = new HashMap<>();
        String sql = "SELECT NgayMuon_20151094, COUNT(*) SoLuong_20151094 FROM MuonTra GROUP BY NgayMuon_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                java.sql.Date ngayMuonSQL = rs.getDate("NgayMuon_20151094");
                Date ngayMuon = new Date(ngayMuonSQL.getTime());
                int soLuong = rs.getInt("SoLuong_20151094");
                ds.put(ngayMuon, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<Date, Integer> thongKeMuonTraTheoNgayHenTra() {
        HashMap<Date, Integer> ds = new HashMap<>();
        String sql = "SELECT NgayHenTra_20151094, COUNT(*) SoLuong_20151094 FROM MuonTra GROUP BY NgayHenTra_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                java.sql.Date ngayHenTraSQL = rs.getDate("NgayHenTra_20151094");
                Date ngayHenTra = new Date(ngayHenTraSQL.getTime());
                int soLuong = rs.getInt("SoLuong_20151094");
                ds.put(ngayHenTra, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<Integer, Integer> thongKeMuonTraTheoTienCoc() {
        HashMap<Integer, Integer> ds = new HashMap<>();
        String sql = "SELECT TienCoc_20151094, COUNT(*) SoLuong_20151094 FROM MuonTra GROUP BY TienCoc_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int tienCoc = rs.getInt("TienCoc_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                ds.put(tienCoc, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public int laySoThuTuCuoiCuaBangMuonTra() {
        int stt = 0;
        String maMuonTra = "";
        try {
            String sql = "Select MaMuonTra_20151094 from MuonTra";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maMuonTra = rs.getString(1).trim();
            }
            if (!maMuonTra.equals("")) {
                maMuonTra = maMuonTra.substring(2);
                stt = Integer.parseInt(maMuonTra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stt;
    }

    public boolean kiemTraTonTaiMaMuonTra(MuonTra mt) {
        String sql = "Select MaMuonTra_20151094 from MuonTra where MaMuonTra_20151094=?";
        String maDocGiaTimDuoc = "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mt.getMaMuonTra());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maDocGiaTimDuoc = rs.getString("MaMuonTra_20151094").trim();
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

    public int thongKeMuonTraTrongNgay(Date ngay) {
        int soLuong = 0;
        try {
            String sql = "select Count(*) from MuonTra where NgayMuon_20151094=?";
            PreparedStatement ps = con.prepareStatement(sql);
            java.sql.Date ngayMuon = new java.sql.Date(ngay.getTime());
            ps.setDate(1, ngayMuon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuong;
    }

    public ArrayList<Sach> sachMuonNgay(Date ngay) {
        ArrayList<Sach> ds = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "select * from Sach\n"
                    + "where MaSach_20151094 in \n"
                    + "(	select MaSach_20151094 from ChiTietMuonTra,MuonTra\n"
                    + "	where MuonTra.MaMuonTra_20151094=ChiTietMuonTra.MaMuonTra_20151094\n"
                    + "	And NgayMuon_20151094 Like ?\n"
                    + ")";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdf.format(ngay));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setMaSach_20151094(rs.getString(1).trim());
                s.setTenSach_20151094(rs.getString(2));
                s.setTacGia_20151094(rs.getString(3));
                s.setNhaXuatBan_20151094(rs.getString(4));
                s.setTheLoai_20151094(rs.getString(5));
                ds.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DocGia> docGiaMuonNgay(Date ngay) {
        ArrayList<DocGia> ds = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "select * from DocGia\n"
                    + "where MaDocGia_20151094 in \n"
                    + "(	select MaDocGia_20151094 from MuonTra\n"
                    + "	where NgayMuon_20151094 Like ?\n"
                    + ")";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdf.format(ngay));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DocGia dg = new DocGia();
                dg.setMaDocGia(rs.getString(1).trim());
                dg.setTenDocGia(rs.getString(2));
                dg.setGioiTinh(rs.getString(3).trim());
                dg.setPhone(rs.getString(4).trim());
                dg.setEmail(rs.getString(5));
                dg.setDiaChi(rs.getString(6));
                ds.add(dg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<Sach> sachTraNgay(Date ngay) {
        ArrayList<Sach> ds = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "select * from Sach\n"
                    + "where MaSach_20151094 in \n"
                    + "(	select MaSach_20151094 from MuonTra,ChiTietMuonTra\n"
                    + "	where MuonTra.MaMuonTra_20151094=ChiTietMuonTra.MaMuonTra_20151094\n"
                    + "	And NgayTra_20151094 Like ?\n"
                    + ")";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdf.format(ngay));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach s = new Sach();
                s.setMaSach_20151094(rs.getString(1).trim());
                s.setTenSach_20151094(rs.getString(2));
                s.setTacGia_20151094(rs.getString(3));
                s.setNhaXuatBan_20151094(rs.getString(4));
                s.setTheLoai_20151094(rs.getString(5));
                ds.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DocGia> docGiaTraNgay(Date ngay) {
        ArrayList<DocGia> ds = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "select * from DocGia\n"
                    + "where MaDocGia_20151094 in \n"
                    + "(	select MaDocGia_20151094 from MuonTra,ChiTietMuonTra\n"
                    + "	where MuonTra.MaMuonTra_20151094=ChiTietMuonTra.MaMuonTra_20151094\n"
                    + "	And NgayTra_20151094 Like ?\n"
                    + ")";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdf.format(ngay));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DocGia dg = new DocGia();
                dg.setMaDocGia(rs.getString(1).trim());
                dg.setTenDocGia(rs.getString(2));
                dg.setGioiTinh(rs.getString(3).trim());
                dg.setPhone(rs.getString(4).trim());
                dg.setEmail(rs.getString(5));
                dg.setDiaChi(rs.getString(6));
                ds.add(dg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public int soLuongSachMuonNgay(Date ngay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int soLuong = 0;
        try {
            String sql = "select COUNT(*) from MuonTra,ChiTietMuonTra\n"
                    + "where MuonTra.MaMuonTra_20151094=ChiTietMuonTra.MaMuonTra_20151094\n"
                    + "And NgayMuon_20151094 Like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdf.format(ngay));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuong;
    }

}
