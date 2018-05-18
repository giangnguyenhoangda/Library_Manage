/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.BLLDocGia;
import BLL.BLLNhanVien;
import BLL.BLLSach;
import BLL.TaoMaTiepTheo;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class ImportFileExcel {

    QuerySach qSach;
    Connection con;
    TaoMaTiepTheo taoSo;
    BLLSach bllSach;
    QueryNhanVien qNhanVien;
    QueryDocGia qDocGia;
    BLLNhanVien bllNhanVien;
    BLLDocGia bllDocGia;

    public ImportFileExcel() {
        con = MyConnection.getConnection("QuanLyThuVienV2", "sa", "hot10000%");
        qSach = new QuerySach();
        taoSo = new TaoMaTiepTheo();
        bllSach = new BLLSach();
        bllDocGia = new BLLDocGia();
        bllNhanVien = new BLLNhanVien();
        qNhanVien = new QueryNhanVien();
        qDocGia = new QueryDocGia();
    }

    public int docDanhSachSachTuFileExcel(String path) {
        int ketqua = 0;
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            Workbook workBook = new XSSFWorkbook(fis);
            Sheet firtSheet = workBook.getSheetAt(0);
            Iterator<Row> iRow = firtSheet.iterator();
            JOptionPane.showMessageDialog(null, "Chuẩn bị thêm sách từ file. Nhấn OK và đợi trong giây lát");
            int dong = 0;
            while (iRow.hasNext()) {
                Row row = iRow.next();
                dong++;
                Iterator<Cell> iCell = row.iterator();
                String giaTriCaDong = "";
                while (iCell.hasNext()) {
                    Cell cell = iCell.next();
                    giaTriCaDong += cell.getStringCellValue() + ";";
                }
                if (!giaTriCaDong.equals("")) {
                    giaTriCaDong = giaTriCaDong.substring(0, giaTriCaDong.length() - 1);
                    giaTriCaDong = giaTriCaDong.trim();
                    String[] arr = giaTriCaDong.split(";");
                    if (arr.length == 5) {
                        String maSach = taoSo.layMaSachTiepTheo();
                        String tenSach = arr[0];
                        String tacGia = arr[1];
                        String nhaXuatBan = arr[2];
                        String theLoai = arr[3];
                        String tinhTrang = arr[4];
                        if (bllSach.themSach(maSach, tenSach, tacGia, nhaXuatBan, theLoai, tinhTrang) > 0) {
                            ketqua++;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Sách ở dòng " + dong + " không được thêm do thiếu thông tin");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public int doDanhSachNhanVienTuFileExcel(String path) {
        int kq = 0;
        int soLuong = 0;
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            XSSFWorkbook workBook = new XSSFWorkbook(fis);
            Sheet firtSheet = workBook.getSheetAt(0);
            int stt = qNhanVien.laySoThuTuCuoiCuaBangNhanVien();
            Iterator<Row> iRow = firtSheet.iterator();
            int dong = 0;
            JOptionPane.showMessageDialog(null, "Chuẩn bị thêm nhân viên từ file. Nhấn OK và đợi trong giây lát....");
            while (iRow.hasNext()) {
                dong++;
                Row row = iRow.next();
                Iterator<Cell> iCell = row.iterator();
                String giaTriRow = "";
                while (iCell.hasNext()) {
                    Cell cell = iCell.next();
                    if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                        giaTriRow += "0" + new DataFormatter().formatCellValue(cell) + ";";
                    } else if (cell.getCellTypeEnum() == CellType.STRING) {
                        giaTriRow += cell.getStringCellValue() + ";";
                    }
                }
                if (!giaTriRow.equals("")) {
                    giaTriRow = giaTriRow.substring(0, giaTriRow.length() - 1);
                    giaTriRow = giaTriRow.trim();
                    String[] arr = giaTriRow.split(";");
                    if (arr.length == 5) {
                        String maNhanVien = taoSo.layMaNhanVienTiepTheo();
                        String tenNhanVien = arr[0];
                        String gioiTinh = arr[1];
                        String phone = arr[2];
                        String luong = arr[3];
                        String diaChi = arr[4];
                        String regexGioiTinh = "[Nn][Aa][Mm]|Nữ|NỮ|nữ|nỮ";
                        String regexPhone = "\\d{10,11}";
                        if (!gioiTinh.matches(regexGioiTinh) || !phone.matches(regexPhone)) {
                            if (!gioiTinh.matches(regexGioiTinh)) {
                                JOptionPane.showMessageDialog(null, "Nhân viên dòng " + dong + " không được thêm do sai giới tính");
                                continue;
                            }
                            if (!phone.matches(regexPhone)) {
                                JOptionPane.showMessageDialog(null, "Nhân viên dòng " + dong + " không được thêm do số điện thoại không hợp lệ");
                                System.out.println(phone);
                                continue;
                            }
                            if (!luong.matches(".*\\D+.*")) {
                                JOptionPane.showMessageDialog(null, "Nhân viên dòng " + dong + " không được thêm do lương không hợp lệ");
                                continue;
                            }
                        }
                        kq = bllNhanVien.themNhanVien(maNhanVien, tenNhanVien, gioiTinh, phone, Integer.parseInt(luong), diaChi);
                        if (kq > 0) {
                            soLuong += kq;
                            stt++;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nhân viên ở dòng " + dong + " không được thêm do thiếu thông tin.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuong;
    }

    public static void main(String[] args) {
        ImportFileExcel i = new ImportFileExcel();
        i.doDanhSachNhanVienTuFileExcel("E:\\Source Code\\Java\\QuanLyThuVienVer2.0\\DanhSachNhanVien.xlsx");
    }

    public int doDanhSachDocGiaTuFileExcel(String path) {
        int kq = 0;
        int soLuong = 0;
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            XSSFWorkbook workBook = new XSSFWorkbook(fis);
            Sheet firtSheet = workBook.getSheetAt(0);
            int stt = qDocGia.laySoThuTuCuoiCuaBangDocGia();
            Iterator<Row> iRow = firtSheet.iterator();
            int dong = 0;
            JOptionPane.showMessageDialog(null, "Chuẩn bị thêm độc giả từ file. Nhấn OK và đợi trong giây lát.");
            while (iRow.hasNext()) {
                Row row = iRow.next();
                dong++;
                Iterator<Cell> iCell = row.iterator();
                String giaTriRow = "";
                while (iCell.hasNext()) {
                    Cell cell = iCell.next();
                    if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                        giaTriRow += "0" + new DataFormatter().formatCellValue(cell) + ";";
                    } else if (cell.getCellTypeEnum() == CellType.STRING) {
                        giaTriRow += cell.getStringCellValue() + ";";
                    }
                }
                if (!giaTriRow.equals("")) {
                    giaTriRow = giaTriRow.substring(0, giaTriRow.length() - 1);
                    giaTriRow = giaTriRow.trim();
                    String[] arr = giaTriRow.split(";");
                    if (arr.length == 5) {
                        String maDocGia = taoSo.layMaDocGiaTiepTheo();
                        String tenDocGia = arr[0];
                        String gioiTinh = arr[1];
                        String phone = arr[2];
                        String email = arr[3];
                        String diaChi = arr[4];
                        String regexGioiTinh = "[Nn][Aa][Mm]|Nữ|NỮ|nữ|nỮ";
                        String regexPhone = "\\d{10,11}";
                        String regexEmail = "\\w+.*@.+\\..+";
                        if (!gioiTinh.matches(regexGioiTinh) || !phone.matches(regexPhone) || !email.matches(regexEmail)) {
                            if (!gioiTinh.matches(regexGioiTinh)) {
                                JOptionPane.showMessageDialog(null, "Độc giả ở dòng " + dong + " không được thêm do sai giới tính");
                                continue;
                            }
                            if (!phone.matches(regexPhone)) {
                                JOptionPane.showMessageDialog(null, "Độc giả ở dòng " + dong + " không được thêm do sai số điện thoại");
                                continue;
                            }
                            if (!email.matches(regexEmail)) {
                                JOptionPane.showMessageDialog(null, "Độc giả ở dòng " + dong + " không được thêm do email sai định dạng");
                                continue;
                            }
                        }
                        kq = bllDocGia.themDocGia(maDocGia, tenDocGia, gioiTinh, phone, email, diaChi);
                        if (kq > 0) {
                            soLuong += kq;
                            stt++;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Độc giả ở dòng " + dong + " không được thêm do thiếu thông tin");
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuong;
    }
}
