/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class ChiTietMuonTra {

    private String maMuonTra;
    private String maSach;
    private Date ngayTra;
    private Integer tienPhat;

    public String getMaMuonTra() {
        return maMuonTra;
    }

    public void setMaMuonTra(String maMuonTra) {
        this.maMuonTra = maMuonTra;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public Integer getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(Integer tienPhat) {
        this.tienPhat = tienPhat;
    }

    @Override
    public boolean equals(Object obj) {
        ChiTietMuonTra ct = (ChiTietMuonTra) obj;
        if (this.maMuonTra.equals(ct.getMaMuonTra()) && this.maSach.equals(ct.getMaSach())) {
            return true;
        }
        return false;
    }

}
