/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class KiemTraDuLieuNhapThoaMan {

    public static String kiemTraDuLieuNhapCapNhatMuonTra(String maMuonTra, String maDocGia, String maNhanVien, String ngayMuon, String ngayHenTra, String tienCoc) {
        String regex = "\\s*";
        String regexNgay = "\\d{1,2}/\\d{1,2}/\\d\\d\\d\\d";
        String regexTien = "\\d+";
        if (maMuonTra.matches(regex) && maDocGia.matches(regex) && maNhanVien.matches(regex) && ngayMuon.matches(regex) && ngayHenTra.matches(regex) && tienCoc.matches(regex)) {
            return "Chưa nhập dữ liệu";
        }
        if (maMuonTra.matches(regex)) {
            return "Chưa nhập mã mượn trả";
        } else if (maDocGia.matches(regex)) {
            return "Chưa nhập mã độc giả";
        } else if (maNhanVien.matches(regex)) {
            return "Chưa nhập mã nhân viên";
        } else if (ngayMuon.matches(regex)) {
            return "Chưa nhập ngày mượn";
        } else if (!ngayMuon.matches(regexNgay)) {
            return "Nhập sai định dạng ngày của ngày mượn. (Định dạng ngày: dd/MM/yyyy)";
        } else if (ngayHenTra.matches(regex)) {
            return "Chưa nhập ngày hẹn trả";
        } else if (!ngayHenTra.matches(regexNgay)) {
            return "Nhập sai định dạng ngày của ngày hẹn trả.(Định dạng ngày: dd/MM/yyyy)";
        } else if (tienCoc.matches(regex)) {
            return "Chưa nhập tiền đặt cọc";
        } else if (!tienCoc.matches(regexTien)) {
            return "Nhập sai tiền đặt cọc. (Tiền đặt cọc phải là số)";
        }
        return "Dữ liệu thỏa mãn";
    }

    public static String kiemTraDuLieuNhapTimKiemMuonTra(String maMuonTra, String maDocGia, String maNhanVien, String ngayMuon, String ngayHenTra, String tienCoc) {
        String regex = "\\s*";
        String regexNgay = "\\d{1,2}/\\d{1,2}/\\d\\d\\d\\d";
        String regexTien = "\\d+";
        if (maMuonTra.matches(regex) && maDocGia.matches(regex) && maNhanVien.matches(regex) && ngayMuon.matches(regex) && ngayHenTra.matches(regex) && tienCoc.matches(regex)) {
            return "Chưa nhập dữ liệu";
        } else if (!ngayMuon.matches(regex) || !ngayHenTra.matches(regex) || !tienCoc.matches(regex)) {
            if (!ngayMuon.matches(regexNgay) && !ngayMuon.matches(regex)) {
                return "Nhập sai định dạng ngày";
            } else if (!ngayHenTra.matches(regexNgay) && !ngayHenTra.matches(regex)) {
                return "Nhập sai định dạng ngày của ngày hẹn trả";
            } else if (!tienCoc.matches(regexTien) && !tienCoc.matches(regex)) {
                return "Nhập sai định dạng tiền cọc";
            }
        }
        return "Dữ liệu thỏa mãn";
    }

    public static String kiemTraDuLieuNhapSua(String maDocGia, String maNhanVien, String ngayMuon, String ngayHenTra, String tienCoc) {
        String regex = "\\s*";
        String regexNgay = "\\d{1,2}/\\d{1,2}/\\d\\d\\d\\d";
        String regexTien = "\\d+";
        if (maDocGia.matches(regex) && maNhanVien.matches(regex) && ngayMuon.matches(regex) && ngayHenTra.matches(regex) && tienCoc.matches(regex)) {
            return "Chưa nhập dữ liệu";
        }
        if (maDocGia.matches(regex)) {
            return "Chưa nhập mã độc giả";
        } else if (maNhanVien.matches(regex)) {
            return "Chưa nhập mã nhân viên";
        } else if (ngayMuon.matches(regex)) {
            return "Chưa nhập ngày mượn";
        } else if (!ngayMuon.matches(regexNgay)) {
            return "Nhập sai định dạng ngày của ngày mượn. (Định dạng ngày: dd/MM/yyyy)";
        } else if (ngayHenTra.matches(regex)) {
            return "Chưa nhập ngày hẹn trả";
        } else if (!ngayHenTra.matches(regexNgay)) {
            return "Nhập sai định dạng ngày của ngày hẹn trả.(Định dạng ngày: dd/MM/yyyy)";
        } else if (tienCoc.matches(regex)) {
            return "Chưa nhập tiền đặt cọc";
        } else if (!tienCoc.matches(regexTien)) {
            return "Nhập sai tiền đặt cọc. (Tiền đặt cọc phải là số)";
        }
        return "Dữ liệu thỏa mãn";
    }

    public static String kiemTraDuLieuNhapThemNhanVien(String maNhanVien, String tenNhanVien, String gioiTinh, String phone, String luong, String diaChi) {

        String regex = "\\s*";
        String regexLuong = "\\d+";
        String regexPhone = "\\d{10,11}";
        if (maNhanVien.matches(regex) && tenNhanVien.matches(regex) && gioiTinh.matches(regex) && phone.matches(regex) && luong.matches(regex) && diaChi.matches(regex)) {
            return "Chưa nhập dữ liệu";
        } else if (maNhanVien.matches(regex)) {
            return "Chưa nhập mã nhân viên";
        } else if (tenNhanVien.matches(regex)) {
            return "Chưa nhập tên nhân viên";
        } else if (gioiTinh.matches(regex)) {
            return "Chưa chọn giới tính";
        } else if (phone.matches(regex)) {
            return "Chưa nhập số điện thoại";
        } else if (!phone.matches(regexPhone)) {
            return "Nhập sai số điện thoại";
        } else if (luong.matches(regex)) {
            return "Chưa nhập lương";
        } else if (!luong.matches(regexLuong)) {
            return "Nhập sai lương";
        } else if (diaChi.matches(regex)) {
            return "Chưa nhập địa chỉ";
        }
        return "Dữ liệu thỏa mãn";
    }

    public static String kiemTraDuLieuNhapDocGia(String maDocGia, String tenDocGia, String gioiTinh, String phone, String email, String diaChi) {

        String regex = "\\s*";
        String regexEmail = "(\\w+)(.*)@(.+)(\\.)(.+)";
        String regexPhone = "\\d{10,11}";
        if (maDocGia.matches(regex) && tenDocGia.matches(regex) & gioiTinh.matches(regex) && phone.matches(regex) && email.matches(regex) && diaChi.matches(regex)) {
            return "Chưa nhập dữ liệu";
        } else if (maDocGia.matches(regex)) {
            return "Chưa nhập mã độc giả";
        } else if (tenDocGia.matches(regex)) {
            return "Chưa nhập tên độc giả";
        } else if (gioiTinh.matches(regex)) {
            return "Chưa chọn giới tính";
        } else if (phone.matches(regex)) {
            return "Chưa nhập số điện thoại";
        } else if (!phone.matches(regexPhone)) {
            return phone + " không phải số điện thoại";
        } else if (email.matches(regex)) {
            return "Chưa nhập email";
        } else if (!email.matches(regexEmail)) {
            return email + " không phải là email.";
        } else if (diaChi.matches(regex)) {
            return "Chưa nhập địa chỉ";
        }
        return "Dữ liệu thỏa mãn";
    }

    public static String kiemTraDuLieuChiTietMuonTraNhapTimKiem(String maMuonTra, String maSach, String ngayTra, String tienPhat) {
        String regex = "\\s*";
        String regexNgay = "\\d{1,2}/\\d{1,2}/\\d\\d\\d\\d";
        String regexSo = "\\d+";
        if (maMuonTra.matches(regex) && maSach.matches(regex) && ngayTra.matches(regex) && tienPhat.matches(regex)) {
            return "Chưa nhập dữ liệu";
        } else if (!ngayTra.matches(regexNgay) && !ngayTra.matches(regex)) {
            return "Nhập sai định dạng ngày của ngày trả. Định dạng ngày (dd/MM/yyyy).";
        } else if (!tienPhat.matches(regexSo) && !tienPhat.matches(regex)) {
            return "Nhập sai giá trị tiền phạt.";
        }
        return "Dữ liệu thỏa mãn";
    }

    public static String kiemTraChiTietMuonTraCapNhat(String maMuonTra, String maSach, String ngayTra, String tienPhat) {
        String regex = "\\s*";
        String regexNgay = "(\\d{1,2}/\\d{1,2}/\\d\\d\\d\\d)|NULL";
        String regexSo = "\\d+";
        if (maMuonTra.matches(regex) && maSach.matches(regex) && ngayTra.matches(regex) && tienPhat.matches(regex)) {
            return "Chưa nhập dữ liệu";
        } else if (maMuonTra.matches(regex)) {
            return "Chưa chọn mã mượn trả";
        } else if (maSach.matches(regex)) {
            return "Chưa chọn mã sách";
        } else if (ngayTra.matches(regex)) {
            return "Chưa nhập ngày trả";
        } else if (!ngayTra.matches(regexNgay)) {
            return "Nhập sai ngày trả";
        } else if (tienPhat.matches(regex)) {
            return "Chưa nhập tiền phạt";
        } else if (!tienPhat.matches(regexSo)) {
            return "Nhập sai tiền phạt";
        }
        return "Dữ liệu thỏa mãn";
    }

    public static String kiemTraDuLieuNhapCapNhatSach(String maSach, String tenSach, String tacGia, String nhaXuatBan, String theLoai, String tinhTrang) {
        String regex = "\\s*";
        if (maSach.matches(regex) && tenSach.matches(regex) && tacGia.matches(regex) && nhaXuatBan.matches(regex) && theLoai.matches(regex)) {
            return "Chưa nhập dữ liệu";
        } else if (maSach.matches(regex)) {
            return "Chưa nhập mã sách";
        } else if (tenSach.matches(regex)) {
            return "Chưa nhập tên sách";
        } else if (tacGia.matches(regex)) {
            return "Chưa nhập tác giả";
        } else if (nhaXuatBan.matches(regex)) {
            return "Chưa nhập nhà xuất bản";
        } else if (theLoai.matches(regex)) {
            return "Chưa nhập thể loại";

        } else if (tinhTrang.matches(regex)) {
            return "Chưa chọn tình trạng sách";
        }
        return "Dữ liệu thỏa mãn";
    }
}
