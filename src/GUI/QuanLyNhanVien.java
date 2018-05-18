/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLLNhanVien;
import BLL.KiemTraDuLieuNhapThoaMan;
import BLL.TaoMaTiepTheo;
import DAL.ImportFileExcel;
import DAL.PrintNhanVien;
import DTO.NhanVien;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class QuanLyNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyNhanVien
     */
    DefaultTableModel dtmDanhSachNhanVien;
    DefaultTableModel dtmDanhSachTimKiem;
    DefaultTableModel dtmMau;
    DefaultTableModel dtmDanhSachCapNhat;
    Connection con;
    JButton btn, btnLast;
    BLLNhanVien bll;
    TaoMaTiepTheo taoMa;
    KiemTraDuLieuNhapThoaMan kiemTraduLieuNhap;
    PrintNhanVien inNhanVien;
    ImportFileExcel importFile;

    public QuanLyNhanVien() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
        }
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        importFile = new ImportFileExcel();
        bll = new BLLNhanVien();
        taoMa = new TaoMaTiepTheo();
        kiemTraduLieuNhap = new KiemTraDuLieuNhapThoaMan();
        inNhanVien = new PrintNhanVien();
        dtmMau = new DefaultTableModel();
        dtmMau.addColumn("Mã Nhân Viên");
        dtmMau.addColumn("Tên Nhân Viên");
        dtmMau.addColumn("Giới Tính");
        dtmMau.addColumn("Phone");
        dtmMau.addColumn("Lương");
        dtmMau.addColumn("Địa Chỉ");
        tblTimKiemKetQua.setModel(dtmMau);
        tblTimKiemKetQua.setModel(dtmMau);
        resetDuLieuNhap();
        hienThiToanBoNhanVienLenBang();
        autoDoiMauChu(jLabel1, Color.BLACK, Color.RED);
        jLabel8.setForeground(Color.BLUE);
        autoDoiMauChu(jLabel8, Color.BLUE, Color.GREEN);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
        Color mauChu = jLabel10.getForeground();
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                jLabel10.setText(sdf.format(cal.getTime()));
                if (jLabel10.getForeground() == mauChu) {
                    jLabel10.setForeground(Color.BLACK);
                } else {
                    jLabel10.setForeground(mauChu);
                }
            }
        });
        t.start();
        Calendar cal = Calendar.getInstance();
        jLabel10.setText(sdf.format(cal.getTime()));
        jScrollPane3.setOpaque(false);
        jScrollPane3.getViewport().setOpaque(false);
        if(!Login.txtTenDangNhap.getText().equalsIgnoreCase("admin")){
            hanCheChucNang();
        }
    }
    
    private void hanCheChucNang(){
        btnThemNhanVien.setEnabled(false);
        jButton1.setEnabled(false);
        btnSuaNhanVien.setEnabled(false);
        btnXoaNhanVien.setEnabled(false);
    }

    private void autoDoiMauChu(JLabel lbl, Color col1, Color col2) {
        Timer t = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lbl.getForeground() == col1) {
                    lbl.setForeground(col2);
                } else {
                    lbl.setForeground(col1);
                }
            }
        });
        t.start();
    }

    private void resetDuLieuNhap() {
        txtTimKiemMaNhanVien.setText("");
        txtTimKiemTenNhanVien.setText("");
        if (rdNam.isSelected() == true) {
            rdNam.setSelected(false);
        } else {
            rdNu.setSelected(false);
        }
        txtTimKiemPhone.setText("");
        txtTimKiemLuong.setText("");
        txtTimKiemDiaChi.setText("");
    }

    private void hienThiToanBoNhanVienLenBang() {
        dtmDanhSachNhanVien = hienThi(bll.layToanBoNhanVien());
        tblTimKiemKetQua.setModel(dtmDanhSachNhanVien);
    }

    private DefaultTableModel hienThi(ArrayList<NhanVien> ds) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã Nhân Viên");
        dtm.addColumn("Tên Nhân Viên");
        dtm.addColumn("Giới Tính");
        dtm.addColumn("Phone");
        dtm.addColumn("Lương");
        dtm.addColumn("Địa Chỉ");
        for (NhanVien nv : ds) {
            Vector<String> v = new Vector<>();
            v.add(nv.getMaNhanVien());
            v.add(nv.getTenNhanVien());
            v.add(nv.getGioiTinh());
            v.add(nv.getPhone());
            v.add(nv.getLuong() + "");
            v.add(nv.getDiaChi());
            dtm.addRow(v);
        }
        return dtm;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTimKiemMaNhanVien = new javax.swing.JTextField();
        txtTimKiemTenNhanVien = new javax.swing.JTextField();
        txtTimKiemPhone = new javax.swing.JTextField();
        txtTimKiemLuong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTimKiemDiaChi = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        btnTimKiem = new javax.swing.JButton();
        btnThemNhanVien = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnSuaNhanVien = new javax.swing.JButton();
        btnXoaNhanVien = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnResetCapNhat = new javax.swing.JButton();
        btnTimKiemInRaFile = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTimKiemKetQua = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1250, 642));

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/if_Manager_131484.png"))); // NOI18N
        jLabel1.setText("Quản Lý Nhân Viên");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("@Author Nguyễn Hoàng Giang");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("MSSV: 20151094");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 204));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("jLabel10");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));

        jPanel10.setBackground(new java.awt.Color(51, 204, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Nhân Viên:"));

        jLabel2.setText("Mã Nhân Viên:");

        jLabel3.setText("Tên Nhân Viên:");

        jLabel4.setText("Giới Tính:");

        jLabel5.setText("Phone:");

        jLabel6.setText("Lương:");

        jLabel7.setText("Địa Chỉ:");

        rdNam.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup1.add(rdNam);
        rdNam.setText("Nam");

        rdNu.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiemTenNhanVien)
                    .addComponent(txtTimKiemPhone)
                    .addComponent(txtTimKiemLuong)
                    .addComponent(txtTimKiemDiaChi)
                    .addComponent(txtTimKiemMaNhanVien, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(rdNam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdNu)
                        .addGap(0, 93, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTimKiemMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTimKiemTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rdNam)
                            .addComponent(rdNu))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addComponent(txtTimKiemPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(txtTimKiemLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTimKiemDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTimKiem.setBackground(new java.awt.Color(51, 204, 255));
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Search-16.png"))); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnThemNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        btnThemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Add Administrator-16.png"))); // NOI18N
        btnThemNhanVien.setText("Thêm Nhân Viên");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 204, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Microsoft Excel-16.png"))); // NOI18N
        jButton1.setText("Thêm Từ File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnSuaNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        btnSuaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Edit Account-16.png"))); // NOI18N
        btnSuaNhanVien.setText("Sửa Nhân Viên");
        btnSuaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhanVienActionPerformed(evt);
            }
        });

        btnXoaNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        btnXoaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Delete-16.png"))); // NOI18N
        btnXoaNhanVien.setText("Xóa Nhân Viên");
        btnXoaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhanVienActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 204, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-select-all.png"))); // NOI18N
        jButton2.setText("Xem Toàn Bộ Nhân Viên");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnResetCapNhat.setBackground(new java.awt.Color(51, 204, 255));
        btnResetCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Broom-16.png"))); // NOI18N
        btnResetCapNhat.setText("Reset Ô Nhập Dữ Liệu");
        btnResetCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetCapNhatActionPerformed(evt);
            }
        });

        btnTimKiemInRaFile.setBackground(new java.awt.Color(51, 204, 255));
        btnTimKiemInRaFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16a.png"))); // NOI18N
        btnTimKiemInRaFile.setText("In Ra File");
        btnTimKiemInRaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemInRaFileActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(51, 204, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Nhân Viên:"));
        jPanel11.setLayout(new java.awt.BorderLayout());

        tblTimKiemKetQua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTimKiemKetQuaMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblTimKiemKetQua);

        jPanel11.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTimKiemInRaFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSuaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnResetCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSuaNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetCapNhat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiemInRaFile)
                .addGap(0, 66, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String maNhanVien = txtTimKiemMaNhanVien.getText();
        String tenNhanVien = txtTimKiemTenNhanVien.getText();
        String gioiTinh = rdNam.isSelected() == true ? "Nam" : rdNu.isSelected() == true ? "Nữ" : "";
        String phone = txtTimKiemPhone.getText();
        String luong = txtTimKiemLuong.getText();
        String diaChi = txtTimKiemDiaChi.getText();
        if (maNhanVien.equals("") && tenNhanVien.equals("") && gioiTinh.equals("") && phone.equals("") && luong.equals("") && diaChi.equals("")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
            return;
        }
        if (!phone.matches("\\d+|\\s*")) {
            JOptionPane.showMessageDialog(null, "Nhập sai số điện thoại");
            return;
        } else if (luong.equals("")) {
            dtmDanhSachTimKiem = hienThi(bll.timKiemNhanVien(maNhanVien, tenNhanVien, gioiTinh, phone, luong, diaChi));
            tblTimKiemKetQua.setModel(dtmDanhSachTimKiem);
            btnLast = btnTimKiem;
            return;
        } else {
            Integer luongInt = TryParseInt(luong);
            if (luongInt == null) {
                JOptionPane.showMessageDialog(null, "Nhập sai lương");
                return;
            } else {
                dtmDanhSachTimKiem = hienThi(bll.timKiemNhanVien(maNhanVien, tenNhanVien, gioiTinh, phone, luong, diaChi));
                tblTimKiemKetQua.setModel(dtmDanhSachTimKiem);
                btnLast = btnTimKiem;
                System.out.println("Tìm kiếm");
            }
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    public Integer TryParseInt(String someText) {
        try {
            return Integer.parseInt(someText);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        // TODO add your handling code here:
        //        int stt = DataBaseQuery.laySoThuTuCuoiCuaBangNhanVien(con) + 1;
        //        String maNhanVien = "NV" + stt;
        //        String tenNhanVien = txtCapNhatTenNhanVien.getText();
        //        String gioiTinh = rdCapNhatNam.isSelected() == true ? "Nam" : rdCapNhatNu.isSelected() == true ? "Nữ" : "";
        //        String phone = txtCapNhatPhone.getText();
        //        Integer luong = TryParseInt(txtCapNhatLuong.getText());
        //        String diaChi = txtCapNhatDiaChi.getText();
        //        if (gioiTinh.equals("") && tenNhanVien.equals("") && phone.equals("") && luong==null && diaChi.equals("")) {
        //            JOptionPane.showMessageDialog(null, "Chưa điền đầy đủ");
        //            return;
        //        }
        //        if (gioiTinh.equals("")) {
        //            JOptionPane.showMessageDialog(null, "Chưa chọn giới tính");
        //            return;
        //        }
        //        if (luong == null) {
        //            JOptionPane.showMessageDialog(null, "Nhập sai lương");
        //            return;
        //        }
        //        if(phone.equals("")||diaChi.equals("")){
        //            JOptionPane.showMessageDialog(null, "Chưa điền đầy đủ");
        //            return;
        //        }
        //        DataBaseQuery.insertIntoNhanVien(con, maNhanVien, tenNhanVien, gioiTinh, phone, luong, diaChi);
        //        hienThiToanBoNhanVienLenBang();
        //        resetCapNhat();
        //btnTimKiem.doClick();
        String maNhanVien = txtTimKiemMaNhanVien.getText();
        String tenNhanVien = txtTimKiemTenNhanVien.getText();
        String gioiTinh = rdNam.isSelected() == true ? "Nam" : rdNu.isSelected() == true ? "Nữ" : "";
        String phone = txtTimKiemPhone.getText();
        String luong = txtTimKiemLuong.getText();
        String diaChi = txtTimKiemDiaChi.getText();
        String kiemTra = KiemTraDuLieuNhapThoaMan.kiemTraDuLieuNhapThemNhanVien(maNhanVien, tenNhanVien, gioiTinh, phone, luong, diaChi);
        if (kiemTra.equals("Chưa nhập dữ liệu")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
            return;
        }
        if (kiemTra.equals("Chưa nhập mã nhân viên")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã nhân viên");
            return;
        }
        if (kiemTra.equals("Chưa nhập tên nhân viên")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên nhân viên");
            return;
        }
        if (kiemTra.equals("Chưa chọn giới tính")) {
            JOptionPane.showMessageDialog(null, "Chưa chọn giới tính");
            return;
        }
        if (kiemTra.equals("Chưa nhập số điện thoại")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập số điện thoại");
            return;
        }
        if (kiemTra.equals("Nhập sai số điện thoại")) {
            JOptionPane.showMessageDialog(null, "Nhập sai số điện thoại");
            return;
        }
        if (kiemTra.equals("Chưa nhập lương")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập lương");
            return;
        }
        if (kiemTra.equals("Nhập sai lương")) {
            JOptionPane.showMessageDialog(null, "Nhập sai lương");
            return;
        }
        if (kiemTra.equals("Chưa nhập địa chỉ")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập địa chỉ");
            return;
        }
        if (kiemTra.equals("Dữ liệu thỏa mãn")) {
            int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (kt != JOptionPane.YES_OPTION) {
                return;
            }
            int luongNhanVien = Integer.parseInt(luong);
            int kq = bll.themNhanVien(maNhanVien, tenNhanVien, gioiTinh, phone, Integer.parseInt(luong), diaChi);
            if (kq > 0) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                hienThiToanBoNhanVienLenBang();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
            resetDuLieuNhap();
        }
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            int kq = importFile.doDanhSachNhanVienTuFileExcel(chooser.getSelectedFile().getAbsolutePath());
            if (kq > 0) {
                JOptionPane.showMessageDialog(null, "Thêm thành công " + kq + " nhân viên");
                hienThiToanBoNhanVienLenBang();
                resetDuLieuNhap();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSuaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhanVienActionPerformed
        // TODO add your handling code here:
        //Cách 1
        //        int row = tblCapNhatKetQua.getSelectedRow();
        //        if (row < 0) {
        //            JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên");
        //            return;
        //        }
        //        String maNhanVien = tblCapNhatKetQua.getValueAt(row, 0) + "";
        //        String tenNhanVien = txtCapNhatTenNhanVien.getText().trim();
        //        String gioiTinh = rdCapNhatNam.isSelected() == true ? "Nam" : rdCapNhatNu.isSelected() == true ? "Nữ" : "null";
        //        String luong = txtCapNhatLuong.getText();
        //        String phone = txtCapNhatPhone.getText().trim();
        //        String diaChi = txtCapNhatDiaChi.getText().trim();
        //
        //        if (tenNhanVien.equals("") && phone.equals("") && luong.equals("") && diaChi.equals("")) {
        //            JOptionPane.showMessageDialog(null, "Chưa nhập đủ dữ liệu");
        //            return;
        //        }
        //        if (gioiTinh.equals("")) {
        //            JOptionPane.showMessageDialog(null, "Chưa chọn giới tính");
        //            return;
        //        }
        //        if (luong.equals("")) {
        //            JOptionPane.showMessageDialog(null, "Chưa nhập lương");
        //            return;
        //        } else {
        //            Integer luongInt = TryParseInt(luong);
        //            if (luongInt == null) {
        //                JOptionPane.showMessageDialog(null, "Nhập sai lương");
        //                return;
        //            } else {
        //                DataBaseQuery.suaNhanVien(con, maNhanVien, tenNhanVien, gioiTinh, phone, luongInt, diaChi);
        //                hienThiToanBoNhanVienLenBang();
        //                resetCapNhat();
        //                btnTimKiem.doClick();
        //            }
        //        }
        //Cách 2
        int row = tblTimKiemKetQua.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên");
            return;
        }
        String maNhanVien = tblTimKiemKetQua.getValueAt(row, 0) + "";
        String tenNhanVien = txtTimKiemTenNhanVien.getText().trim();
        String gioiTinh = rdNam.isSelected() == true ? "Nam" : rdNu.isSelected() == true ? "Nữ" : "null";
        String luong = txtTimKiemLuong.getText();
        String phone = txtTimKiemPhone.getText().trim();
        String diaChi = txtTimKiemDiaChi.getText().trim();
        String kiemTra = KiemTraDuLieuNhapThoaMan.kiemTraDuLieuNhapThemNhanVien(maNhanVien, tenNhanVien, gioiTinh, phone, luong, diaChi);
        if (kiemTra.equals("Chưa nhập dữ liệu")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
            return;
        }
        if (kiemTra.equals("Chưa nhập mã nhân viên")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã nhân viên");
            return;
        }
        if (kiemTra.equals("Chưa nhập tên nhân viên")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên nhân viên");
            return;
        }
        if (kiemTra.equals("Chưa chọn giới tính")) {
            JOptionPane.showMessageDialog(null, "Chưa chọn giới tính");
            return;
        }
        if (kiemTra.equals("Chưa nhập số điện thoại")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập số điện thoại");
            return;
        }
        if (kiemTra.equals("Nhập sai số điện thoại")) {
            JOptionPane.showMessageDialog(null, "Nhập sai số điện thoại");
            return;
        }
        if (kiemTra.equals("Chưa nhập lương")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập lương");
            return;
        }
        if (kiemTra.equals("Nhập sai lương")) {
            JOptionPane.showMessageDialog(null, "Nhập sai lương");
            return;
        }
        if (kiemTra.equals("Chưa nhập địa chỉ")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập địa chỉ");
            return;
        }
        if (kiemTra.equals("Dữ liệu thỏa mãn")) {
            int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa không ?", "Hỏi Sửa", JOptionPane.YES_NO_OPTION);
            if (kt == JOptionPane.YES_OPTION) {
                int luongNhanVien = Integer.parseInt(luong);
                int kq = bll.suaNhanVien(maNhanVien, tenNhanVien, gioiTinh, phone, row, diaChi);
                if (kq > 0) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Sưa thất bại");
                }
                resetDuLieuNhap();
                hienThiToanBoNhanVienLenBang();
            }
        }
    }//GEN-LAST:event_btnSuaNhanVienActionPerformed

    private void btnXoaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanVienActionPerformed
        // TODO add your handling code here:
        //Chỉ xóa được 1
        //        int row = tblCapNhatKetQua.getSelectedRow();
        //        if (row < 0) {
        //            JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên");
        //            return;
        //        }
        //        int kt = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa ?","Xác nhận",JOptionPane.YES_NO_OPTION);
        //        if(kt!=JOptionPane.YES_OPTION){
        //            return;
        //        }
        //        String maNhanVien = txtCapNhatMaNhanVien.getText();
        //        DataBaseQuery.deleteNhanVien(con, maNhanVien);
        //        resetCapNhat();
        //        hienThiToanBoNhanVienLenBang();
        //        btnTimKiem.doClick();
        //Xóa được nhiều
        int[] row = tblTimKiemKetQua.getSelectedRows();
        if (row.length > 0) {
            int kt = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (kt != JOptionPane.YES_OPTION) {
                return;
            }
            int soLuong = 0;
            for (int r : row) {
                String maNhanVien = tblTimKiemKetQua.getValueAt(r, 0) + "";
                soLuong += bll.xoaNhanVien(maNhanVien.trim(), "", "", "", "", "");
            }
            if (soLuong > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công " + soLuong + " nhân viên");
                resetDuLieuNhap();
                hienThiToanBoNhanVienLenBang();
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên");
            return;
        }
    }//GEN-LAST:event_btnXoaNhanVienActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        hienThiToanBoNhanVienLenBang();
        btnLast = jButton2;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnResetCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetCapNhatActionPerformed
        // TODO add your handling code here:
        resetDuLieuNhap();
    }//GEN-LAST:event_btnResetCapNhatActionPerformed

    private void btnTimKiemInRaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemInRaFileActionPerformed
        // TODO add your handling code here:
        if (btnLast == btnTimKiem || btnLast == jButton2) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in kết quả.</p>"
                    + "<p>Vui lòng đợi trong ít phút.</p></html>");
            if (btnLast == btnTimKiem) {
                String maNhanVien = txtTimKiemMaNhanVien.getText();
                String tenNhanVien = txtTimKiemTenNhanVien.getText();
                String gioiTinh = rdNam.isSelected() == true ? "Nam" : rdNu.isSelected() == true ? "Nữ" : "";
                String phone = txtTimKiemPhone.getText();
                String luong = txtTimKiemLuong.getText();
                String diaChi = txtTimKiemDiaChi.getText();
                String regex = "\\s*";
                if (maNhanVien.matches(regex) && tenNhanVien.matches(regex) && gioiTinh.matches(regex) && phone.matches(regex) && luong.matches(regex) && diaChi.matches(regex)) {
                    JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu tìm kiếm");
                    return;
                }
                inNhanVien.printKetQuaTimKiemNhanVien("%" + maNhanVien + "%", "%" + tenNhanVien + "%", "%" + gioiTinh + "%", "%" + phone + "%", "%" + luong + "%", "%" + diaChi + "%");
            } else {
                inNhanVien.printToanBoNhanVien();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy tìm kiếm trước");
        }
    }//GEN-LAST:event_btnTimKiemInRaFileActionPerformed

    private void tblTimKiemKetQuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimKiemKetQuaMousePressed
        // TODO add your handling code here:
        int row = tblTimKiemKetQua.getSelectedRow();
        txtTimKiemMaNhanVien.setText((tblTimKiemKetQua.getValueAt(row, 0) + "").trim());
        txtTimKiemTenNhanVien.setText(tblTimKiemKetQua.getValueAt(row, 1) + "");
        String gioiTinh = tblTimKiemKetQua.getValueAt(row, 2) + "";
        if (gioiTinh.equals("Nam")) {
            rdNam.setSelected(true);
            rdNu.setSelected(false);
        } else {
            rdNu.setSelected(true);
            rdNam.setSelected(false);
        }
        txtTimKiemPhone.setText((tblTimKiemKetQua.getValueAt(row, 3) + "").trim());
        txtTimKiemLuong.setText(tblTimKiemKetQua.getValueAt(row, 4) + "");
        txtTimKiemDiaChi.setText(tblTimKiemKetQua.getValueAt(row, 5) + "");
    }//GEN-LAST:event_tblTimKiemKetQuaMousePressed

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
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResetCapNhat;
    private javax.swing.JButton btnSuaNhanVien;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemInRaFile;
    private javax.swing.JButton btnXoaNhanVien;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tblTimKiemKetQua;
    private javax.swing.JTextField txtTimKiemDiaChi;
    private javax.swing.JTextField txtTimKiemLuong;
    private javax.swing.JTextField txtTimKiemMaNhanVien;
    private javax.swing.JTextField txtTimKiemPhone;
    private javax.swing.JTextField txtTimKiemTenNhanVien;
    // End of variables declaration//GEN-END:variables
}
