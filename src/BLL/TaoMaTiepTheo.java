/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.QueryDocGia;
import DAL.QueryMuonTra;
import DAL.QueryNhanVien;
import DAL.QuerySach;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class TaoMaTiepTheo {

    QuerySach qSach;
    QueryDocGia qDocGia;
    QueryNhanVien qNhanVien;
    QueryMuonTra qMuonTra;

    public TaoMaTiepTheo() {
        qSach = new QuerySach();
        qDocGia = new QueryDocGia();
        qNhanVien = new QueryNhanVien();
        qMuonTra = new QueryMuonTra();
    }

    public String taoChuoiTuSoDaCho(int n) {
        int sotieptheo = 0;
        if (n >= 0 && n < 9) {
            sotieptheo = n + 1;
            return "000" + sotieptheo;
        } else if (n >= 9 && n < 99) {
            sotieptheo = n + 1;
            return "00" + sotieptheo;
        } else if (n >= 99 && n < 999) {
            sotieptheo = n + 1;
            return "0" + sotieptheo;
        } else if (n >= 999 && n < 9999) {
            sotieptheo = n + 1;
            return "" + sotieptheo;
        }
        return "";
    }

    public String layMaSachTiepTheo() {
        int soThuTuCuoiCuaBangSach = qSach.laySoThuTuCuoiCuaBangSach();
        return "MS" + taoChuoiTuSoDaCho(soThuTuCuoiCuaBangSach);
    }

    public String layMaNhanVienTiepTheo() {
        int soThuTuCuoi = qNhanVien.laySoThuTuCuoiCuaBangNhanVien();
        return "NV" + taoChuoiTuSoDaCho(soThuTuCuoi);
    }

    public String layMaDocGiaTiepTheo() {
        int soThuTuCuoi = qDocGia.laySoThuTuCuoiCuaBangDocGia();
        return "DG" + taoChuoiTuSoDaCho(soThuTuCuoi);
    }

    public String layMaMuonTraTiepTheo() {
        int soThuTuCuoi = qMuonTra.laySoThuTuCuoiCuaBangMuonTra();
        return "MT" + taoChuoiTuSoDaCho(soThuTuCuoi);
    }
}
