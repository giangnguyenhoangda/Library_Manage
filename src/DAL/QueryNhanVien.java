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
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QueryNhanVien {

    Connection con;
    QueryTaiKhoan qTaiKhoan;

    public NhanVien timNhanVien(NhanVien nv) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public QueryNhanVien() {
        con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
        qTaiKhoan = new QueryTaiKhoan();
    }

    public NhanVien timKiemNhanVienQuaTaiKhoan(TaiKhoan taiKhoan) {
        String maNhanVien = qTaiKhoan.timKiemMaNhanVienThongQuaTaiKhoan(taiKhoan);
        NhanVien nv = timKiemNhanVienThongQuaMaNhanVien(maNhanVien);
        return nv;
    }

    public NhanVien timKiemNhanVienThongQuaMaNhanVien(String maNhanVien) {
        NhanVien nv = new NhanVien();
        String sql = "Select * From NhanVien where MaNhanVien_20151094=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nv.setMaNhanVien(maNhanVien);
                nv.setTenNhanVien(rs.getString("TenNhanVien_20151094"));
                nv.setGioiTinh(rs.getString(3));
                nv.setPhone(rs.getString(4));
                nv.setLuong(rs.getInt(5));
                nv.setDiaChi(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }

    public ArrayList<NhanVien> timKiemNhanVien(NhanVien nhanVien) {

        ArrayList<NhanVien> dsTimKiem = new ArrayList<NhanVien>();
        String sql = "SELECT * FROM NhanVien "
                + "where MaNhanVien_20151094 like ? AND TenNhanVien_20151094 like ? "
                + "AND GioiTinh_20151094 like ? AND Phone_20151094 like ? AND Luong_20151094 like ? "
                + "AND DiaChi_20151094 like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nhanVien.getMaNhanVien() + "%");
            ps.setString(2, "%" + nhanVien.getTenNhanVien() + "%");
            ps.setString(3, "%" + nhanVien.getGioiTinh() + "%");
            ps.setString(4, "%" + nhanVien.getPhone() + "%");
            if (nhanVien.getLuong() == null) {
                ps.setString(5, "%%");
            } else {
                ps.setString(5, "%" + nhanVien.getLuong() + "%");
            }
            ps.setString(6, "%" + nhanVien.getDiaChi() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String maNhanVien = rs.getString("MaNhanVien_20151094").trim();
                String tenNhanVien = rs.getString("TenNhanVien_20151094").trim();
                String gioiTinh = rs.getString("GioiTinh_20151094").trim();
                String phone = rs.getString("Phone_20151094").trim();
                int luong = rs.getInt("Luong_20151094");
                String diaChi = rs.getString("DiaChi_20151094").trim();

                NhanVien nhanVienTimThay = new NhanVien();
                nhanVienTimThay.setMaNhanVien(maNhanVien);
                nhanVienTimThay.setTenNhanVien(tenNhanVien);
                nhanVienTimThay.setGioiTinh(gioiTinh);
                nhanVienTimThay.setPhone(phone);
                nhanVienTimThay.setLuong(luong);
                nhanVienTimThay.setDiaChi(diaChi);
                dsTimKiem.add(nhanVienTimThay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsTimKiem;
    }

    public ArrayList<NhanVien> SelectFromNhanVien() {
        ArrayList<NhanVien> ds = new ArrayList<>();
        try {
            String sql = "Select * from NhanVien";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNhanVien = rs.getString("MaNhanVien_20151094").trim();
                String tenNhanVien = rs.getString("TenNhanVien_20151094");
                String gioiTinh = rs.getString("GioiTinh_20151094").trim();
                String phone = rs.getString("Phone_20151094").trim();
                int luong = rs.getInt("Luong_20151094");
                String diaChi = rs.getString("DiaChi_20151094");

                NhanVien nhanVienTimThay = new NhanVien();
                nhanVienTimThay.setMaNhanVien(maNhanVien);
                nhanVienTimThay.setTenNhanVien(tenNhanVien);
                nhanVienTimThay.setGioiTinh(gioiTinh);
                nhanVienTimThay.setPhone(phone);
                nhanVienTimThay.setLuong(luong);
                nhanVienTimThay.setDiaChi(diaChi);
                ds.add(nhanVienTimThay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public int insertIntoNhanVien(NhanVien nv) {
        String sql = "Insert Into NhanVien values(?,?,?,?,?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getTenNhanVien());
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getPhone());
            ps.setInt(5, nv.getLuong());
            ps.setString(6, nv.getDiaChi());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                QueryTaiKhoan qTaiKhoan = new QueryTaiKhoan();
                int kqThemTaiKhoan = qTaiKhoan.themTaiKhoan(nv);
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int suaNhanVien(NhanVien nv) {
        String sql = "Update NhanVien set TenNhanVien_20151094=?,GioiTinh_20151094=?,Phone_20151094=?,Luong_20151094=?,DiaChi_20151094=? where MaNhanVien_20151094=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getTenNhanVien());
            ps.setString(2, nv.getGioiTinh());
            ps.setString(3, nv.getPhone());
            ps.setInt(4, nv.getLuong());
            ps.setString(5, nv.getDiaChi());
            ps.setString(6, nv.getMaNhanVien());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return kq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteNhanVien(NhanVien nv) {
        QueryTaiKhoan qTaiKhoan = new QueryTaiKhoan();
        TaiKhoan tk = new TaiKhoan();
        tk.setTenTaiKhoan(nv.getMaNhanVien());
        int kq = qTaiKhoan.xoaTaiKhoan(tk);
        if (kq > 0) {
            String sql = "delete from NhanVien where MaNhanVien_20151094=?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, nv.getMaNhanVien());
                int kqXoaNV = ps.executeUpdate();
                if (kqXoaNV > 0) {
                    return kqXoaNV;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public HashMap<String, Integer> thongKeNhanVienTheoTenNhanVien() {
        HashMap<String, Integer> ds = new HashMap<>();
        String sql = "SELECT TenNhanVien_20151094, COUNT(*) SoLuong_20151094 FROM NhanVien GROUP BY TenNhanVien_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenNhanVien = rs.getString("TenNhanVien_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                ds.put(tenNhanVien, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<String, Integer> thongKeNhanVienTheoGioiTinh() {
        HashMap<String, Integer> ds = new HashMap<>();
        String sql = "SELECT GioiTinh_20151094, COUNT(*) SoLuong_20151094 FROM NhanVien GROUP BY GioiTinh_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String gioiTinh = rs.getString("GioiTinh_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                ds.put(gioiTinh, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<String, Integer> thongKeNhanVienTheoLuong() {
        HashMap<String, Integer> ds = new HashMap<>();
        String sql = "SELECT Luong_20151094, COUNT(*) SoLuong_20151094 FROM NhanVien GROUP BY Luong_20151094";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String luong = rs.getString("Luong_20151094");
                int soLuong = rs.getInt("SoLuong_20151094");
                ds.put(luong, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HashMap<String, Integer> thongKeNhanVienTheoDiaChi() {
        HashMap<String, Integer> ds = new HashMap<>();
        String sql = "SELECT DiaChi_20151094, COUNT(*) SoLuong_20151094 FROM NhanVien GROUP BY DiaChi_20151094";
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

    public int laySoThuTuCuoiCuaBangNhanVien() {
        int stt = 0;
        String maNhanVien = "";
        try {
            String sql = "Select MaNhanVien_20151094 from NhanVien";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maNhanVien = rs.getString(1).trim();
            }
            if (!maNhanVien.equals("")) {
                maNhanVien = maNhanVien.substring(2);
                stt = Integer.parseInt(maNhanVien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stt;
    }

    public boolean kiemTraTonTaiMaNhanVien(String maNhanVien) {
        String sql = "Select MaNhanVien_20151094 from NhanVien where MaNhanVien_20151094=?";
        String maDocGiaTimDuoc = "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maDocGiaTimDuoc = rs.getString("MaNhanVien_20151094").trim();
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
