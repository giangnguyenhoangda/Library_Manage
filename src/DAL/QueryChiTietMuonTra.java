/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.ChiTietMuonTra;
import DTO.MuonTra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QueryChiTietMuonTra {

    Connection con;

    public QueryChiTietMuonTra() {
        con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
    }

    public ArrayList<ChiTietMuonTra> SelectFromChiTietMuonTra() {
        ArrayList<ChiTietMuonTra> ds = new ArrayList<>();
        try {
            String sql = "Select * from ChiTietMuonTra";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietMuonTra chitiet = new ChiTietMuonTra();
                chitiet.setMaMuonTra(rs.getString(1));
                chitiet.setMaSach(rs.getString(2));
                java.sql.Date date = rs.getDate(3);
                if (date == null) {
                    chitiet.setNgayTra(null);
                } else {
                    chitiet.setNgayTra(new Date(date.getTime()));
                }
                chitiet.setTienPhat(rs.getInt(4));
                ds.add(chitiet);
            }
            return ds;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<ChiTietMuonTra> timKiemChiTietMuonTra(ChiTietMuonTra chiTiet) {

        ArrayList<ChiTietMuonTra> ds = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietMuonTra "
                + "where MaMuonTra_20151094 like ? AND MaSach_20151094 like ? "
                + "AND (NgayTra_20151094 like ? OR NgayTra_20151094 IS NULL) AND TienPhat_20151094 like ?";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + chiTiet.getMaMuonTra() + "%");
            ps.setString(2, "%" + chiTiet.getMaSach() + "%");
            if (chiTiet.getNgayTra() == null) {
                ps.setString(3, "%%");
            } else {
                ps.setString(3, "%" + sdfSQL.format(chiTiet.getNgayTra()) + "%");
            }
            if (chiTiet.getTienPhat() == null) {
                ps.setString(4, "%%");
            } else {
                ps.setString(4, "%" + chiTiet.getTienPhat() + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietMuonTra ct = new ChiTietMuonTra();
                ct.setMaMuonTra(rs.getString(1));
                ct.setMaSach(rs.getString(2));
                Date ngayTraSQL = rs.getDate("NgayTra_20151094");
                if (ngayTraSQL == null) {
                    ct.setNgayTra(null);
                } else {
                    ct.setNgayTra(new Date(ngayTraSQL.getTime()));
                }
                ct.setTienPhat(rs.getInt("TienPhat_20151094"));
                ds.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public static void main(String[] args) {
        ChiTietMuonTra ct = new ChiTietMuonTra();
        ct.setMaSach("ms000001");
        ct.setMaMuonTra("ms");
        QueryChiTietMuonTra q = new QueryChiTietMuonTra();
        ArrayList<ChiTietMuonTra> c = q.timKiemChiTietMuonTra(ct);
        for (ChiTietMuonTra s : c) {
            System.out.println(s.getMaSach());
        }
    }

    public int insertIntoChiTietMuonTra(ChiTietMuonTra ct) {
        if (ct.getNgayTra() == null) {
            String sql = "Insert Into ChiTietMuonTra values(?,?,NULL,?) ";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, ct.getMaMuonTra());
                ps.setString(2, ct.getMaSach());
                ps.setInt(3, ct.getTienPhat());
                int kq = ps.executeUpdate();
                if (kq > 0) {
                    return kq;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String sql = "Insert Into ChiTietMuonTra values(?,?,?,?) ";
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, ct.getMaMuonTra());
                ps.setString(2, ct.getMaSach());
                ps.setDate(3, new java.sql.Date(ct.getNgayTra().getTime()));
                ps.setInt(4, ct.getTienPhat());
                int kq = ps.executeUpdate();
                if (kq > 0) {
                    return kq;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int suaChiTietMuonTra(ChiTietMuonTra ct) {
        if (ct.getNgayTra() == null) {
            try {
                String sql = "Update ChiTietMuonTra set NgayTra_20151094=NULL,TienPhat_20151094=? Where MaMuonTra_20151094=? AND MaSach_20151094=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, ct.getTienPhat());
                ps.setString(2, ct.getMaMuonTra());
                ps.setString(3, ct.getMaSach());
                int kq = ps.executeUpdate();
                if (kq > 0) {
                    return kq;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                String sql = "Update ChiTietMuonTra set NgayTra_20151094=?,TienPhat_20151094=? Where MaMuonTra_20151094=? AND MaSach_20151094=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setDate(1, new java.sql.Date(ct.getNgayTra().getTime()));
                ps.setInt(2, ct.getTienPhat());
                ps.setString(3, ct.getMaMuonTra());
                ps.setString(4, ct.getMaSach());
                int kq = ps.executeUpdate();
                if (kq > 0) {
                    return kq;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int xoaChiTietMuonTra(ChiTietMuonTra ct) {
        try {
            String sql = "delete from ChiTietMuonTra where MaMuonTra_20151094=? AND MaSach_20151094=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ct.getMaMuonTra());
            ps.setString(2, ct.getMaSach());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public HashMap<String, Integer> thongKeChiTietMuonTraTheoMaMuonTra() {
        HashMap<String, Integer> ds = new HashMap<>();
        try {
            String sql = "Select MaMuonTra_20151094,COUNT(*) SoLuong_20151094 from ChiTietMuonTra GROUP By MaMuonTra_20151094";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMuonTra = rs.getString(1);
                Integer soLuong = rs.getInt(2);
                ds.put(maMuonTra, soLuong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<String, Integer> thongKeChiTietMuonTraTheoMaSach() {
        HashMap<String, Integer> ds = new HashMap<>();
        try {
            String sql = "Select MaSach_20151094,COUNT(*) SoLuong_20151094 from ChiTietMuonTra GROUP By MaSach_20151094";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maSach = rs.getString(1);
                Integer soLuong = rs.getInt(2);
                ds.put(maSach, soLuong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<Date, Integer> thongKeChiTietMuonTraTheoNgayTra() {
        HashMap<Date, Integer> ds = new HashMap<>();
        try {
            String sql = "Select NgayTra_20151094,COUNT(*) SoLuong_20151094 from ChiTietMuonTra GROUP By NgayTra_20151094";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                java.sql.Date ngayTraSQL = rs.getDate(1);
                Date ngayTra = null;
                if (ngayTraSQL != null) {
                    ngayTra = new Date(rs.getDate(1).getTime());
                }
                Integer soLuong = rs.getInt(2);
                ds.put(ngayTra, soLuong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<Integer, Integer> thongKeChiTietMuonTraTheoTienPhat() {
        HashMap<Integer, Integer> ds = new HashMap<>();
        try {
            String sql = "Select TienPhat_20151094,COUNT(*) SoLuong_20151094 from ChiTietMuonTra GROUP By TienPhat_20151094";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer tienPhat = rs.getInt(1);
                Integer soLuong = rs.getInt(2);
                ds.put(tienPhat, soLuong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<ChiTietMuonTra> timChiTietMuonTraTheoMuonTra(MuonTra mt) {
        ArrayList<ChiTietMuonTra> ds = new ArrayList<>();
        try {
            String sql = "Select * from ChiTietMuonTra where MaMuonTra_20151094=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mt.getMaMuonTra());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietMuonTra ct = new ChiTietMuonTra();
                ct.setMaMuonTra(mt.getMaMuonTra());
                ct.setMaSach(rs.getString("MaSach_20151094"));
                java.sql.Date ngayTraSql = rs.getDate("NgayTra_20151094");
                if (ngayTraSql != null) {
                    ct.setNgayTra(new Date(rs.getDate("NgayTra_20151094").getTime()));
                } else {
                    ct.setNgayTra(null);
                }
                ct.setTienPhat(rs.getInt("TienPhat_20151094"));
                ds.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
