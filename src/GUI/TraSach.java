/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLLChiTietMuonTra;
import BLL.BLLNhanVien;
import BLL.BLLSach;
import DAL.PrintMuonTra;
import DTO.ChiTietMuonTra;
import DTO.DocGia;
import DTO.MuonTra;
import DTO.NhanVien;
import DTO.Sach;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class TraSach extends javax.swing.JFrame {

    /**
     * Creates new form TraSach
     */
    SimpleDateFormat sdf;
    BLLChiTietMuonTra bllChiTiet;
    BLLSach bllSach;
    int tongTienPhat = 0;
    ArrayList<String> sachTra;
    ArrayList<Sach> dsSachTra;
    BLLNhanVien bLLNhanVien;
    
    public TraSach() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
        }
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        bLLNhanVien = new BLLNhanVien();
        bllChiTiet = new BLLChiTietMuonTra();
        sachTra = new ArrayList<String>();
        bllSach = new BLLSach();
        hienThiDuLieuLenLabel();
        hienThiDuLieuLenBang();
        lblTongTienPhat.setText(tongTienPhat + " VND");
        lblTongTien.setText(tongTienPhat + " VND");
        int tienCoc = QuanLyMuonTra.mtChon.getTienCoc();
        int tienCanTra = tongTienPhat - tienCoc;
        lblTienCanTra.setText(tienCanTra + " VND");
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
    }
    
    private void hienThiDuLieuLenLabel() {
        MuonTra mt = QuanLyMuonTra.mtChon;
        NhanVien nv = QuanLyMuonTra.nvChon;
        DocGia dg = QuanLyMuonTra.dgChon;
        
        if (mt != null) {
            lblMaMuonTra.setText(mt.getMaMuonTra().trim());
            lblNgayMuon.setText(sdf.format(mt.getNgayMuon()));
            lblNgayHenTra.setText(sdf.format(mt.getNgayHenTra()));
            lblTienCoc.setText(mt.getTienCoc() + " VND");
        }
        if (nv != null) {
            lblMaNhanVien.setText(nv.getMaNhanVien().trim());
            lblTenNhanVien.setText(nv.getTenNhanVien());
        }
        if (dg != null) {
            lblMaDocGia.setText(dg.getMaDocGia().trim());
            lblTenDocGia.setText(dg.getTenDocGia());
            lblGioiTinh.setText(dg.getGioiTinh().trim());
            lblSoDienThoai.setText(dg.getPhone().trim());
            lblEmail.setText(dg.getEmail());
            lblDiaChi.setText(dg.getDiaChi());
        }
    }
    
    private void hienThiDuLieuLenBang() {
        ArrayList<ChiTietMuonTra> dsChiTiet = bllChiTiet.timChiTietMuonTra(QuanLyMuonTra.mtChon.getMaMuonTra());
        ArrayList<Sach> dsSach = new ArrayList<>();
        dsSachTra = new ArrayList<>();
        for (ChiTietMuonTra ct : dsChiTiet) {
            Sach sach = bllSach.timSachTheoMa(ct.getMaSach());
            dsSach.add(sach);
            dsSachTra.add(sach);
        }
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã Sách");
        dtm.addColumn("Tên Sách");
        dtm.addColumn("Tác Giả");
        dtm.addColumn("Nhà Xuất Bản");
        dtm.addColumn("Thể Loại");
        dtm.addColumn("Ngày Trả");
        dtm.addColumn("Tiền Phạt");
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < dsSach.size(); i++) {
            Vector<String> v = new Vector<>();
            Sach s = dsSach.get(i);
            v.add(s.getMaSach_20151094());
            v.add(s.getTenSach_20151094());
            v.add(s.getTacGia_20151094());
            v.add(s.getNhaXuatBan_20151094());
            v.add(s.getTheLoai_20151094());
            ChiTietMuonTra ct = dsChiTiet.get(i);
            Date ngayTra = ct.getNgayTra();
            if (ngayTra == null) {
                v.add(sdf.format(cal.getTime()));
                sachTra.add(ct.getMaSach());
                
            } else {
                v.add(sdf.format(ct.getNgayTra()));
            }
            if (ct.getTienPhat() == 0) {
                long tienPhat = tinhTienPhat(QuanLyMuonTra.mtChon.getNgayHenTra());
                v.add(tienPhat + "VND");
                tongTienPhat += tienPhat;
            } else {
                v.add(ct.getTienPhat() + "VND");
                tongTienPhat += ct.getTienPhat();
            }
            dtm.addRow(v);
        }
        jTable1.setModel(dtm);
    }
    
    private long tinhTienPhat(Date ngayHenTra) {
        Calendar cal = Calendar.getInstance();
        Date ngayTra = cal.getTime();
        long lNgayTra = ngayTra.getTime();
        long lNgayHenTra = ngayHenTra.getTime();
        long diff = (lNgayTra > lNgayHenTra) ? (lNgayTra - lNgayHenTra) : 0;
        long soNgay = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        System.out.println("số ngày trả muộn:" + soNgay);
        return soNgay * 1000;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnTraSach = new javax.swing.JButton();
        btnHuyBo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblMaMuonTra = new javax.swing.JLabel();
        lblMaNhanVien = new javax.swing.JLabel();
        lblTenNhanVien = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblMaDocGia = new javax.swing.JLabel();
        lblTenDocGia = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblNgayMuon = new javax.swing.JLabel();
        lblNgayHenTra = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblTongTienPhat = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblTienCoc = new javax.swing.JLabel();
        lblTienCanTra = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));

        jPanel4.setBackground(new java.awt.Color(51, 204, 255));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/credit-card (2).png"))); // NOI18N
        jLabel20.setText("TRẢ SÁCH");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setText("@Author Nguyễn Hoàng Giang");

        jLabel33.setText("MSSV: 20151094");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(499, 499, 499)
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addGap(275, 275, 275)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Mượn Sách:", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sách", "Tên Sách", "Tác Giả", "Nhà Xuất Bản", "Ngày Trả", "Tiền Phạt"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btnTraSach.setBackground(new java.awt.Color(51, 204, 255));
        btnTraSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/credit-card.png"))); // NOI18N
        btnTraSach.setText("Trả Sách");
        btnTraSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraSachActionPerformed(evt);
            }
        });

        btnHuyBo.setBackground(new java.awt.Color(51, 204, 255));
        btnHuyBo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Cancel-16.png"))); // NOI18N
        btnHuyBo.setText("Hủy Bỏ");
        btnHuyBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyBoActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("---------------------------------------------------------------------");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("Mã Mượn Trả:");

        jLabel3.setText("Mã Nhân Viên:");

        jLabel4.setText("Tên Nhân Viên:");

        lblMaMuonTra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaMuonTra.setText("jLabel5");

        lblMaNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaNhanVien.setText("jLabel6");

        lblTenNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenNhanVien.setText("jLabel7");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("---------------------------------------------------------------------");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel9.setText("Mã Độc Giả:");

        jLabel10.setText("Tên Độc Giả:");

        jLabel11.setText("Giới Tính:");

        jLabel12.setText("Số Điện Thoại:");

        jLabel13.setText("Email:");

        jLabel14.setText("Địa Chỉ:");

        lblMaDocGia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaDocGia.setText("jLabel15");

        lblTenDocGia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenDocGia.setText("jLabel16");

        lblGioiTinh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGioiTinh.setText("jLabel17");

        lblSoDienThoai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSoDienThoai.setText("jLabel18");

        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmail.setText("jLabel19");

        lblDiaChi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDiaChi.setText("jLabel21");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("----------------------------------------------------------------------");
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel23.setText("Ngày Mượn:");

        jLabel24.setText("Ngày Hẹn Trả:");

        lblNgayMuon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNgayMuon.setText("jLabel25");

        lblNgayHenTra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNgayHenTra.setText("jLabel26");

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("----------------------------------------------------------------------");
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel28.setText("Tổng Tiền Phạt:");

        jLabel29.setText("Tổng Tiền:");

        jLabel30.setText("Tiền Cọc:");

        jLabel31.setText("Tiền Cần Trả:");

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("----------------------------------------------------------------------");
        jLabel34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblTongTienPhat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTienPhat.setText("jLabel35");

        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setText("jLabel36");

        lblTienCoc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTienCoc.setText("jLabel37");

        lblTienCanTra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTienCanTra.setText("jLabel38");

        jCheckBox1.setBackground(new java.awt.Color(51, 204, 255));
        jCheckBox1.setText("In Hóa Đơn");

        jLabel5.setText("Tiền Mất Sách:");

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButton1.setBackground(new java.awt.Color(0, 204, 255));
        jButton1.setText("Mất Sách");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblMaMuonTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblMaDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTenDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(86, 86, 86)
                                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel14))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel24))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblNgayHenTra, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                            .addComponent(lblNgayMuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel28)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblTongTienPhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel31))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTienCoc, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(lblTienCanTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(205, 205, 205))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnHuyBo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTraSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblMaMuonTra))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblMaNhanVien))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblTenNhanVien))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblMaDocGia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblTenDocGia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lblGioiTinh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(lblSoDienThoai))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lblEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lblDiaChi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(lblNgayMuon))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(lblNgayHenTra))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(lblTongTienPhat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTongTien)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTienCoc)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTienCanTra)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTraSach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuyBo)
                        .addContainerGap(17, Short.MAX_VALUE))))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTraSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraSachActionPerformed
        // TODO add your handling code here:
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn xác nhận muốn trả sách ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            String maMuonTra = lblMaMuonTra.getText();
            Calendar cal = Calendar.getInstance();
            long tienPhat = 0;
            try {
                tienPhat = tinhTienPhat(sdf.parse(lblNgayHenTra.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (String str : sachTra) {
                bllChiTiet.suaChiTietMuonTra(maMuonTra, str, sdf.format(cal.getTime()), tienPhat + "");
                bllSach.suaTinhTrangSach(str, "Còn Sách");
            }
            JOptionPane.showMessageDialog(null, "Trả sách thành công");
            if (jCheckBox1.isSelected() == true) {
                this.dispose();
                try {
                    MuonTra mt = new MuonTra();
                    mt.setMaMuonTra(lblMaMuonTra.getText());
                    mt.setNgayMuon(sdf.parse(lblNgayMuon.getText()));
                    mt.setNgayHenTra(sdf.parse(lblNgayHenTra.getText()));
                    String tienCoc = lblTienCoc.getText();
                    mt.setTienCoc(Integer.parseInt(tienCoc.substring(0, tienCoc.length() - 4)));
                    NhanVien nv = bLLNhanVien.timKiemThongTinNhanVienTheoMa(lblMaNhanVien.getText());
                    DocGia dg = new DocGia();
                    dg.setMaDocGia(lblMaDocGia.getText());
                    dg.setTenDocGia(lblTenDocGia.getText());
                    dg.setGioiTinh(lblGioiTinh.getText());
                    dg.setPhone(lblSoDienThoai.getText());
                    dg.setEmail(lblEmail.getText());
                    dg.setDiaChi(lblDiaChi.getText());
                    PrintMuonTra.printTraSachSach(mt, nv, dg, lblTongTienPhat.getText(), lblTongTien.getText(), lblTienCanTra.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            } else {
                this.dispose();
            }
            
        }
    }//GEN-LAST:event_btnTraSachActionPerformed

    private void btnHuyBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyBoActionPerformed
        // TODO add your handling code here:
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn muốn hủy bỏ trả sách ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Toàn bộ những thao tác vừa rồi sẽ không được ghi nhận.");
            this.dispose();
        }
    }//GEN-LAST:event_btnHuyBoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        Sach sach = new Sach();
        sach.setMaSach_20151094(jTable1.getValueAt(row, 0)+"");
        sach.setTenSach_20151094(jTable1.getValueAt(row, 1)+"");
        sach.setTacGia_20151094(jTable1.getValueAt(row, 2)+"");
        sach.setNhaXuatBan_20151094(jTable1.getValueAt(row, 3)+"");
        sach.setTheLoai_20151094(jTable1.getValueAt(row, 4)+"");
        String tienMatSach = jTextField1.getText();
        String strTienPhat = jTable1.getValueAt(row, 6)+"";
        int tienMatSachINT=0;
        int tienPhatSach=0;
        try{
            strTienPhat=strTienPhat.substring(0, strTienPhat.length()-3);
            tienMatSachINT=Integer.parseInt(tienMatSach);
            tienPhatSach = Integer.parseInt(strTienPhat);
            tongTienPhat-=tienPhatSach;
            tongTienPhat+=tienMatSachINT;
            lblTongTienPhat.setText(tongTienPhat+" VND");
            lblTongTien.setText(tongTienPhat+" VND");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        jTable1.setValueAt(tienMatSachINT+"VND", row, 6);
        Sach s = dsSachTra.get(row);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TraSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TraSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TraSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TraSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TraSach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyBo;
    private javax.swing.JButton btnTraSach;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblMaDocGia;
    private javax.swing.JLabel lblMaMuonTra;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblNgayHenTra;
    private javax.swing.JLabel lblNgayMuon;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTenDocGia;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblTienCanTra;
    private javax.swing.JLabel lblTienCoc;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTienPhat;
    // End of variables declaration//GEN-END:variables
}
