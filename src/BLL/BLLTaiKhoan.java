/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.QueryTaiKhoan;
import DTO.TaiKhoan;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class BLLTaiKhoan {
    QueryTaiKhoan qTaiKhoan;
    public BLLTaiKhoan(){
        qTaiKhoan=new QueryTaiKhoan();
    }
    public boolean kiemTraTonTaiTaiKhoan(String tenDangNhap,String matKhauCu){
        TaiKhoan tk = new TaiKhoan();
        tk.setTenTaiKhoan(tenDangNhap);
        tk.setMatKhau(matKhauCu);
        return qTaiKhoan.kiemTraTonTaiTaiKhoan(tk);
    }
    public int thayDoiTaiKhoan(String tenDangNhap,String matKhauMoi){
        TaiKhoan tk = new TaiKhoan();
        tk.setTenTaiKhoan(tenDangNhap);
        tk.setMatKhau(matKhauMoi);
        return qTaiKhoan.suaTaiKhoan(tk);
    }
}
