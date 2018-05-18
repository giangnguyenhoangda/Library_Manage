/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class TaiKhoan {

    private String tenTaiKhoan;
    private String matKhau;
    private String maNhanVien;

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }
    
    public void setTenTaiKhoan(String tentaiKhoan){
        this.tenTaiKhoan=tentaiKhoan;
    }
}
