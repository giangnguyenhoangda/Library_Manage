/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLLChiTietMuonTra;
import BLL.BLLDocGia;
import BLL.BLLMuonTra;
import BLL.BLLNhanVien;
import BLL.BLLSach;
import DAL.PrintChiTietMuonTra;
import DAL.PrintDocGia;
import DAL.PrintMuonTra;
import DAL.PrintNhanVien;
import DAL.PrintSach;
import DTO.ChiTietMuonTra;
import DTO.DocGia;
import DTO.MuonTra;
import DTO.NhanVien;
import DTO.Sach;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class ThongKe extends javax.swing.JFrame {

    /**
     * Creates new form ThongKe
     */
    JButton btnChiTietMuonTra,
            /**
             * Creates new form ThongKe
             */
            btnMuonTra;
    DefaultTableModel dtmDanhSachNhanVien;
    BLLChiTietMuonTra bllChiTiet;
    DefaultTableModel dtmToanBoChiTietMuonTra, dtmToanBoDocGia;
    BLLMuonTra bllMuonTra;
    DefaultTableModel dtmToanBoMuonTra;
    PrintDocGia inDocGia;
    JButton btnThongkeDocGia, btnThongKeNhanVien;
    BLLDocGia bllDocGia;
    BLLNhanVien bllNhanVien;
    PrintNhanVien inNhanVien;
    DefaultTableModel dtmThongKeKetQua;
    DefaultTableModel dtmToanBoSach;
    JButton btn;
    BLL.BLLSach bllSach;
    PrintSach printSach;

    public ThongKe() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
        }
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        bllChiTiet = new BLLChiTietMuonTra();
        bllMuonTra = new BLLMuonTra();
        inDocGia = new PrintDocGia();
        bllDocGia = new BLLDocGia();
        inNhanVien = new PrintNhanVien();
        bllNhanVien = new BLLNhanVien();
        bllSach = new BLLSach();
        printSach = new PrintSach();
        hienThiToanBoChiTietMuonTra();
        hienThiToanBoMuonTra();
        hienThiDanhSachDocGia();
        hienThiToanBoNhanVienLenBang();
        hienThiToanBoSachLenBangDanhSach();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
        Color mauChu = lblGio.getForeground();
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                lblGio.setText(sdf.format(cal.getTime()));
                if (lblGio.getForeground() == mauChu) {
                    lblGio.setForeground(Color.BLACK);
                } else {
                    lblGio.setForeground(mauChu);
                }
            }
        });
        t.start();
        Calendar cal = Calendar.getInstance();
        jLabel5.setText(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
        lblGio.setText(sdf.format(cal.getTime()));
        autoDoiMauChu(jLabel1, Color.BLACK, Color.WHITE);
        Color mauCu = jLabel3.getForeground();
        autoDoiMauChu(jLabel3, mauCu, Color.orange);
        Color mauCu1 = jLabel4.getForeground();
        autoDoiMauChu(jLabel4, mauCu1, mauCu);
        setOpaqueJScrollPan(jScrollPane1, false);
        setOpaqueJScrollPan(jScrollPane2, false);
        setOpaqueJScrollPan(jScrollPane3, false);
        setOpaqueJScrollPan(jScrollPane4, false);
        setOpaqueJScrollPan(jScrollPane5, false);
        setOpaqueJScrollPan(jScrollPane6, false);
        setOpaqueJScrollPan(jScrollPane7, false);
        setOpaqueJScrollPan(jScrollPane8, false);
        setOpaqueJScrollPan(jScrollPane9, false);
        setOpaqueJScrollPan(scMuonTraThongKeDanhSach, false);
    }

    private void setOpaqueJScrollPan(JScrollPane sc, boolean b) {
        sc.setOpaque(b);
        sc.getViewport().setOpaque(b);
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

    private void hienThiToanBoSachLenBangDanhSach() {
        ArrayList<Sach> ds = bllSach.layToanBoSach();
        dtmToanBoSach = new DefaultTableModel();
        dtmToanBoSach.addColumn("Mã Sách");
        dtmToanBoSach.addColumn("Tên Sách");
        dtmToanBoSach.addColumn("Tác Giả");
        dtmToanBoSach.addColumn("Nhà Xuất Bản");
        dtmToanBoSach.addColumn("Thể Loại");
        dtmToanBoSach.addColumn("Tình Trạng");
        for (Sach s : ds) {
            Vector<String> v = new Vector<>();
            v.add(s.getMaSach_20151094());
            v.add(s.getTenSach_20151094());
            v.add(s.getTacGia_20151094());
            v.add(s.getNhaXuatBan_20151094());
            v.add(s.getTheLoai_20151094());
            v.add(s.getTinhTrang_20151094());
            dtmToanBoSach.addRow(v);
        }
        tblToanBoSach.setModel(dtmToanBoSach);
    }

    private void hienThiToanBoNhanVienLenBang() {
        dtmDanhSachNhanVien = hienThi(bllNhanVien.layToanBoNhanVien());
        tblThongKeDanhSachNhanVien.setModel(dtmDanhSachNhanVien);
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

    private void hienThiDanhSachDocGia() {
        try {
            dtmToanBoDocGia = hienThiDanhSach(bllDocGia.layDanhSachDocGia());
            tblDanhSachDocGia.setModel(dtmToanBoDocGia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel hienThiDanhSach(ArrayList<DocGia> ds) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        dtm.addColumn("Mã Độc Giả");
        dtm.addColumn("Tên Độc Giả");
        dtm.addColumn("Giới Tính");
        dtm.addColumn("Phone");
        dtm.addColumn("Email");
        dtm.addColumn("Địa Chỉ");
        for (DocGia dg : ds) {
            Vector<String> v = new Vector<>();
            v.add(dg.getMaDocGia());
            v.add(dg.getTenDocGia());
            v.add(dg.getGioiTinh());
            v.add(dg.getPhone());
            v.add(dg.getEmail());
            v.add(dg.getDiaChi());
            dtm.addRow(v);
        }
        return dtm;
    }

    private void hienThiToanBoChiTietMuonTra() {
        dtmToanBoChiTietMuonTra = hienThiChiTietMuonTra(bllChiTiet.layToanBoChiTietMuonTra());
        tblChiTietMuonTraThongKeChiTiet.setModel(dtmToanBoChiTietMuonTra);
    }

    private DefaultTableModel hienThiChiTietMuonTra(ArrayList<ChiTietMuonTra> ds) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã Mượn Trả");
        dtm.addColumn("Mã Sách");
        dtm.addColumn("Ngày Trả");
        dtm.addColumn("Tiền Phạt");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (ChiTietMuonTra m : ds) {
            Vector<String> v = new Vector<>();
            v.add(m.getMaMuonTra());
            v.add(m.getMaSach());
            Date date = m.getNgayTra();
            if (date == null) {
                v.add("");
            } else {
                v.add(sdf.format(m.getNgayTra()));
            }
            v.add(m.getTienPhat() + "");
            dtm.addRow(v);
        }
        return dtm;
    }

    private void hienThiToanBoMuonTra() {
        try {
            dtmToanBoMuonTra = hienThiDuLieuTuArrayListLenBan(bllMuonTra.layToanBoMuonTra());
            tblMuonTraThongKeDanhSach.setModel(dtmToanBoMuonTra);
            hienThiToanBoChiTietMuonTra();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel hienThiDuLieuTuArrayListLenBan(ArrayList<MuonTra> dl) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã Mượn Trả");
        dtm.addColumn("Mã Độc Giả");
        dtm.addColumn("Mã Nhân Viên");
        dtm.addColumn("Ngày Mượn");
        dtm.addColumn("Ngày Hẹn Trả");
        dtm.addColumn("Tiền Cọc");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (MuonTra m : dl) {
            Vector<String> v = new Vector<>();
            v.add(m.getMaMuonTra());
            v.add(m.getMaDocGia());
            v.add(m.getMaNhanVien());
            v.add(sdf.format(m.getNgayMuon()));
            v.add(sdf.format(m.getNgayHenTra()));
            v.add(m.getTienCoc() + "");
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblGio = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblToanBoSach = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        btnToanBoSachInRaFile = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblThongKeKetQua = new javax.swing.JTable();
        jPanel31 = new javax.swing.JPanel();
        btnThongKeTheoTenSach = new javax.swing.JButton();
        btnThongKeTheoTacGia = new javax.swing.JButton();
        btnThongKeTheoNhaXuatBan = new javax.swing.JButton();
        btnThongKeTheoTheLoai = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnThongKeInRaFile1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblThongKeDanhSachNhanVien = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        btnThongKeToanBoInRaFIle = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        btnThongKeTheoTenNhanVien = new javax.swing.JButton();
        btnThongKeTheoGioiTinh1 = new javax.swing.JButton();
        btnThongKeTheoLuong = new javax.swing.JButton();
        btnThongKeTheoDiaChi1 = new javax.swing.JButton();
        btnThongKeInRaFile = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanhSachDocGia = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        btnDanhSachDocGiaInRaFilr = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThongKeDocGia = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        btnThongKeTheoTenDocGia = new javax.swing.JButton();
        btnThongKeTheoGioiTinh = new javax.swing.JButton();
        btnThongKeTheoDiaChi = new javax.swing.JButton();
        btnThongKeInRaFIle = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        tbdMuonTraThongKe = new javax.swing.JTabbedPane();
        pnMuonTraThongKeToanBo = new javax.swing.JPanel();
        pnMuonTraDanhSachMuonTra = new javax.swing.JPanel();
        scMuonTraThongKeDanhSach = new javax.swing.JScrollPane();
        tblMuonTraThongKeDanhSach = new javax.swing.JTable();
        pnMuonTraThongKeToanBoChucNang = new javax.swing.JPanel();
        btnMuonTraThongKeToanBoInRaFile = new javax.swing.JButton();
        pnMuonTraThongKeTheoThuocTinh = new javax.swing.JPanel();
        pnMuonTraThongKeTheoThuocTinhKetQua = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKeMuonTra = new javax.swing.JTable();
        pnMuonTraThongKeTheoThuocTinhChucNang = new javax.swing.JPanel();
        btnMuonTraThongKeTheoMaDocGia = new javax.swing.JButton();
        btnMuonTraThongKeTheoMaNhanVien = new javax.swing.JButton();
        btnMuonTraThongKeTheoNgayMuon = new javax.swing.JButton();
        btnMuonTraThongKeTheoNgayHenTra = new javax.swing.JButton();
        btnMuonTraThongKeMuonTraTrongNgay = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnMuonTraThongKeInRaFile = new javax.swing.JButton();
        lblLogoMuonTraThongKe = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooserNgay = new com.toedter.calendar.JDateChooser();
        jPanel25 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblChiTietMuonTraThongKeChiTiet = new javax.swing.JTable();
        jPanel28 = new javax.swing.JPanel();
        btnChiTietMuonTraThongKeToanBoInRaFile = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel32 = new javax.swing.JPanel();
        btnChiTietMuonTraThongKeTheoMaSach = new javax.swing.JButton();
        btnChiTietMuonTraThongKeTheoNgayTra = new javax.swing.JButton();
        btnChiTietMuonTraThongKeTheoTienPhat = new javax.swing.JButton();
        btnThongKeTheoMaMuonTra = new javax.swing.JButton();
        btnChiTietMuonTraThongKeThuocTinhInRaFile = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1250, 730));

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/rsz_xqhvwugeksohjulylbjc.png"))); // NOI18N
        jLabel1.setText("BÁO CÁO - THỐNG KÊ");

        lblGio.setFont(new java.awt.Font("Tahoma", 3, 20)); // NOI18N
        lblGio.setForeground(new java.awt.Color(255, 102, 0));
        lblGio.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("@Author Nguyễn Hoàng Giang");

        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("MSSV: 20151094");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("jLabel5");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblGio, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(67, 67, 67)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGio, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));

        jTabbedPane1.setBackground(new java.awt.Color(51, 204, 255));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(51, 204, 255));

        jPanel20.setBackground(new java.awt.Color(51, 204, 255));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jPanel21.setBackground(new java.awt.Color(51, 204, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Sách:"));
        jPanel21.setLayout(new java.awt.BorderLayout());

        jScrollPane8.setOpaque(false);
        jScrollPane8.setViewportView(tblToanBoSach);

        jPanel21.add(jScrollPane8, java.awt.BorderLayout.CENTER);

        jPanel22.setBackground(new java.awt.Color(51, 204, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng:"));

        btnToanBoSachInRaFile.setBackground(new java.awt.Color(51, 204, 255));
        btnToanBoSachInRaFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16.png"))); // NOI18N
        btnToanBoSachInRaFile.setText("In Ra File");
        btnToanBoSachInRaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToanBoSachInRaFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnToanBoSachInRaFile)
                .addContainerGap(796, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnToanBoSachInRaFile)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.add(jPanel22, java.awt.BorderLayout.PAGE_END);

        jPanel20.add(jPanel21, java.awt.BorderLayout.CENTER);

        jTabbedPane3.addTab("Toàn Bộ Sách", jPanel20);

        jPanel23.setBackground(new java.awt.Color(51, 204, 255));

        jPanel24.setBackground(new java.awt.Color(51, 204, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Kết Quả:"));
        jPanel24.setLayout(new java.awt.BorderLayout());

        jScrollPane9.setOpaque(false);
        jScrollPane9.setViewportView(tblThongKeKetQua);

        jPanel24.add(jScrollPane9, java.awt.BorderLayout.CENTER);

        jPanel31.setBackground(new java.awt.Color(51, 204, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng:"));

        btnThongKeTheoTenSach.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeTheoTenSach.setText("Thống Kê Theo Tên Sách");
        btnThongKeTheoTenSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoTenSachActionPerformed(evt);
            }
        });

        btnThongKeTheoTacGia.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeTheoTacGia.setText("Thống Kê Theo Tác Giả");
        btnThongKeTheoTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoTacGiaActionPerformed(evt);
            }
        });

        btnThongKeTheoNhaXuatBan.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeTheoNhaXuatBan.setText("Thống Kê Theo Nhà Xuất Bản");
        btnThongKeTheoNhaXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoNhaXuatBanActionPerformed(evt);
            }
        });

        btnThongKeTheoTheLoai.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeTheoTheLoai.setText("Thống Kê Theo Thể Loại");
        btnThongKeTheoTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoTheLoaiActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 204, 255));
        jButton3.setText("Thống Kê Theo Tình Trạng Sách");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnThongKeInRaFile1.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeInRaFile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16.png"))); // NOI18N
        btnThongKeInRaFile1.setText("In Ra File");
        btnThongKeInRaFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeInRaFile1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeTheoTheLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeTheoNhaXuatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeTheoTacGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeTheoTenSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeInRaFile1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(btnThongKeTheoTenSach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKeTheoTacGia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThongKeTheoNhaXuatBan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKeTheoTheLoai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKeInRaFile1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1237916771593128258jean_victor_balin_graphics_rounded.svg.med.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75))
        );

        jTabbedPane3.addTab("Thống Kê Theo Thuộc Tính", jPanel23);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 989, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane3))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane3))
        );

        jTabbedPane1.addTab("Sách", new javax.swing.ImageIcon(getClass().getResource("/image/rsz_google_play_books_icon.png")), jPanel4); // NOI18N

        jPanel5.setBackground(new java.awt.Color(51, 204, 255));

        jPanel18.setBackground(new java.awt.Color(51, 204, 255));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jPanel14.setBackground(new java.awt.Color(51, 204, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Nhân Viên:"));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jScrollPane5.setViewportView(tblThongKeDanhSachNhanVien);

        jPanel14.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel15.setBackground(new java.awt.Color(51, 204, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng:"));

        btnThongKeToanBoInRaFIle.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeToanBoInRaFIle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16.png"))); // NOI18N
        btnThongKeToanBoInRaFIle.setText("In Ra File");
        btnThongKeToanBoInRaFIle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeToanBoInRaFIleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnThongKeToanBoInRaFIle)
                .addContainerGap(796, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThongKeToanBoInRaFIle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel15, java.awt.BorderLayout.PAGE_END);

        jPanel18.add(jPanel14, java.awt.BorderLayout.CENTER);

        jTabbedPane4.addTab("Toàn Bộ Nhân Viên", jPanel18);

        jPanel19.setBackground(new java.awt.Color(51, 204, 255));

        jPanel16.setBackground(new java.awt.Color(51, 204, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Kết Quả:"));
        jPanel16.setLayout(new java.awt.BorderLayout());

        jScrollPane7.setViewportView(tblThongKe);

        jPanel16.add(jScrollPane7, java.awt.BorderLayout.CENTER);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1237916771593128258jean_victor_balin_graphics_rounded.svg.med.png"))); // NOI18N
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel17.setBackground(new java.awt.Color(51, 204, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng:"));

        btnThongKeTheoTenNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeTheoTenNhanVien.setText("Thống Kê Theo Tên Nhân Viên");
        btnThongKeTheoTenNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoTenNhanVienActionPerformed(evt);
            }
        });

        btnThongKeTheoGioiTinh1.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeTheoGioiTinh1.setText("Thống Kê Theo Giới Tính");
        btnThongKeTheoGioiTinh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoGioiTinh1ActionPerformed(evt);
            }
        });

        btnThongKeTheoLuong.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeTheoLuong.setText("Thống Kê Theo Lương");
        btnThongKeTheoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoLuongActionPerformed(evt);
            }
        });

        btnThongKeTheoDiaChi1.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeTheoDiaChi1.setText("Thống Kê Theo Địa Chỉ");
        btnThongKeTheoDiaChi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoDiaChi1ActionPerformed(evt);
            }
        });

        btnThongKeInRaFile.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeInRaFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16a.png"))); // NOI18N
        btnThongKeInRaFile.setText("In Ra File");
        btnThongKeInRaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeInRaFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThongKeTheoTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeTheoGioiTinh1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeTheoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeTheoDiaChi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeInRaFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(btnThongKeTheoTenNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThongKeTheoGioiTinh1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKeTheoLuong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKeTheoDiaChi1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKeInRaFile)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(27, 122, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        jTabbedPane4.addTab("Theo Thuộc Tính", jPanel19);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 989, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane4))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane4))
        );

        jTabbedPane1.addTab("Nhân Viên", new javax.swing.ImageIcon(getClass().getResource("/image/rsz_flat-faces-icons-circle-6.png")), jPanel5); // NOI18N

        jPanel6.setBackground(new java.awt.Color(51, 204, 255));

        jPanel8.setBackground(new java.awt.Color(51, 204, 255));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(51, 204, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Độc Giả:"));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setViewportView(tblDanhSachDocGia);

        jPanel9.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel10.setBackground(new java.awt.Color(51, 204, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng:"));

        btnDanhSachDocGiaInRaFilr.setBackground(new java.awt.Color(51, 204, 255));
        btnDanhSachDocGiaInRaFilr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16a.png"))); // NOI18N
        btnDanhSachDocGiaInRaFilr.setText("In Ra File");
        btnDanhSachDocGiaInRaFilr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachDocGiaInRaFilrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnDanhSachDocGiaInRaFilr)
                .addContainerGap(809, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDanhSachDocGiaInRaFilr)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        jPanel8.add(jPanel9, java.awt.BorderLayout.CENTER);

        jTabbedPane2.addTab("Toàn Bộ Độc Giả", jPanel8);

        jPanel11.setBackground(new java.awt.Color(51, 204, 255));

        jPanel12.setBackground(new java.awt.Color(51, 204, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Kết Quả:"));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jScrollPane4.setViewportView(tblThongKeDocGia);

        jPanel12.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel13.setBackground(new java.awt.Color(51, 204, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng:"));

        btnThongKeTheoTenDocGia.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeTheoTenDocGia.setText("Thống Kê Theo Tên Độc Giả");
        btnThongKeTheoTenDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoTenDocGiaActionPerformed(evt);
            }
        });

        btnThongKeTheoGioiTinh.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeTheoGioiTinh.setText("Thống Kê Theo Giới Tính");
        btnThongKeTheoGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoGioiTinhActionPerformed(evt);
            }
        });

        btnThongKeTheoDiaChi.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeTheoDiaChi.setText("Thống Kê Theo Địa Chỉ");
        btnThongKeTheoDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoDiaChiActionPerformed(evt);
            }
        });

        btnThongKeInRaFIle.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeInRaFIle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16a.png"))); // NOI18N
        btnThongKeInRaFIle.setText("In Ra File");
        btnThongKeInRaFIle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeInRaFIleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThongKeTheoTenDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeTheoGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeTheoDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeInRaFIle, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(btnThongKeTheoTenDocGia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThongKeTheoGioiTinh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKeTheoDiaChi)
                .addGap(4, 4, 4)
                .addComponent(btnThongKeInRaFIle)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1237916771593128258jean_victor_balin_graphics_rounded.svg.med.png"))); // NOI18N
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 37, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Thống Kê Theo Thuộc Tính", jPanel11);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1002, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane2))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane2))
        );

        jTabbedPane1.addTab("Độc Giả", new javax.swing.ImageIcon(getClass().getResource("/image/icons8-User-96.png")), jPanel6); // NOI18N

        jPanel7.setBackground(new java.awt.Color(51, 204, 255));

        pnMuonTraThongKeToanBo.setBackground(new java.awt.Color(51, 204, 255));

        pnMuonTraDanhSachMuonTra.setBackground(new java.awt.Color(51, 204, 255));
        pnMuonTraDanhSachMuonTra.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Mượn Trả:"));
        pnMuonTraDanhSachMuonTra.setLayout(new java.awt.BorderLayout());

        scMuonTraThongKeDanhSach.setViewportView(tblMuonTraThongKeDanhSach);

        pnMuonTraDanhSachMuonTra.add(scMuonTraThongKeDanhSach, java.awt.BorderLayout.CENTER);

        pnMuonTraThongKeToanBoChucNang.setBackground(new java.awt.Color(51, 204, 255));
        pnMuonTraThongKeToanBoChucNang.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng:"));

        btnMuonTraThongKeToanBoInRaFile.setBackground(new java.awt.Color(51, 204, 255));
        btnMuonTraThongKeToanBoInRaFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16a.png"))); // NOI18N
        btnMuonTraThongKeToanBoInRaFile.setText("In Ra File");
        btnMuonTraThongKeToanBoInRaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuonTraThongKeToanBoInRaFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMuonTraThongKeToanBoChucNangLayout = new javax.swing.GroupLayout(pnMuonTraThongKeToanBoChucNang);
        pnMuonTraThongKeToanBoChucNang.setLayout(pnMuonTraThongKeToanBoChucNangLayout);
        pnMuonTraThongKeToanBoChucNangLayout.setHorizontalGroup(
            pnMuonTraThongKeToanBoChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMuonTraThongKeToanBoChucNangLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(btnMuonTraThongKeToanBoInRaFile)
                .addContainerGap(796, Short.MAX_VALUE))
        );
        pnMuonTraThongKeToanBoChucNangLayout.setVerticalGroup(
            pnMuonTraThongKeToanBoChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMuonTraThongKeToanBoChucNangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMuonTraThongKeToanBoInRaFile)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnMuonTraThongKeToanBoLayout = new javax.swing.GroupLayout(pnMuonTraThongKeToanBo);
        pnMuonTraThongKeToanBo.setLayout(pnMuonTraThongKeToanBoLayout);
        pnMuonTraThongKeToanBoLayout.setHorizontalGroup(
            pnMuonTraThongKeToanBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMuonTraDanhSachMuonTra, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
            .addComponent(pnMuonTraThongKeToanBoChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnMuonTraThongKeToanBoLayout.setVerticalGroup(
            pnMuonTraThongKeToanBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMuonTraThongKeToanBoLayout.createSequentialGroup()
                .addComponent(pnMuonTraDanhSachMuonTra, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnMuonTraThongKeToanBoChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, 66, Short.MAX_VALUE))
        );

        tbdMuonTraThongKe.addTab("Thống Kê Toàn Bộ Mượn Trả", pnMuonTraThongKeToanBo);

        pnMuonTraThongKeTheoThuocTinh.setBackground(new java.awt.Color(51, 204, 255));

        pnMuonTraThongKeTheoThuocTinhKetQua.setBackground(new java.awt.Color(51, 204, 255));
        pnMuonTraThongKeTheoThuocTinhKetQua.setBorder(javax.swing.BorderFactory.createTitledBorder("Kết Quả:"));
        pnMuonTraThongKeTheoThuocTinhKetQua.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(tblThongKeMuonTra);

        pnMuonTraThongKeTheoThuocTinhKetQua.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnMuonTraThongKeTheoThuocTinhChucNang.setBackground(new java.awt.Color(51, 204, 255));
        pnMuonTraThongKeTheoThuocTinhChucNang.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng:"));

        btnMuonTraThongKeTheoMaDocGia.setBackground(new java.awt.Color(51, 204, 255));
        btnMuonTraThongKeTheoMaDocGia.setText("Thống Kê Theo Mã Độc Giả");
        btnMuonTraThongKeTheoMaDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuonTraThongKeTheoMaDocGiaActionPerformed(evt);
            }
        });

        btnMuonTraThongKeTheoMaNhanVien.setBackground(new java.awt.Color(51, 204, 255));
        btnMuonTraThongKeTheoMaNhanVien.setText("Thống Kê Theo Mã Nhân Viên");
        btnMuonTraThongKeTheoMaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuonTraThongKeTheoMaNhanVienActionPerformed(evt);
            }
        });

        btnMuonTraThongKeTheoNgayMuon.setBackground(new java.awt.Color(51, 204, 255));
        btnMuonTraThongKeTheoNgayMuon.setText("Thống Kê Theo Ngày Mượn");
        btnMuonTraThongKeTheoNgayMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuonTraThongKeTheoNgayMuonActionPerformed(evt);
            }
        });

        btnMuonTraThongKeTheoNgayHenTra.setBackground(new java.awt.Color(51, 204, 255));
        btnMuonTraThongKeTheoNgayHenTra.setText("Thống Kê Theo Ngày Hẹn Trả");
        btnMuonTraThongKeTheoNgayHenTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuonTraThongKeTheoNgayHenTraActionPerformed(evt);
            }
        });

        btnMuonTraThongKeMuonTraTrongNgay.setBackground(new java.awt.Color(51, 204, 255));
        btnMuonTraThongKeMuonTraTrongNgay.setText("Số Lượng Sách Mượn Ngày");
        btnMuonTraThongKeMuonTraTrongNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuonTraThongKeMuonTraTrongNgayActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(51, 204, 255));
        jButton5.setText("Thống Kê Theo Tiền Cọc");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 204, 255));
        jButton1.setText("Sách Mượn Ngày");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 204, 255));
        jButton2.setText("Độc Giả Mượn Sách Ngày");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(51, 204, 255));
        jButton4.setText("Sách Trả Ngày");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(51, 204, 255));
        jButton6.setText("Độc Giả Trả Sách Ngày");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(51, 204, 255));
        jButton7.setText("Số Lượng Mượn Trả Ngày");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnMuonTraThongKeInRaFile.setBackground(new java.awt.Color(51, 204, 255));
        btnMuonTraThongKeInRaFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16a.png"))); // NOI18N
        btnMuonTraThongKeInRaFile.setText("In Ra File");
        btnMuonTraThongKeInRaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuonTraThongKeInRaFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMuonTraThongKeTheoThuocTinhChucNangLayout = new javax.swing.GroupLayout(pnMuonTraThongKeTheoThuocTinhChucNang);
        pnMuonTraThongKeTheoThuocTinhChucNang.setLayout(pnMuonTraThongKeTheoThuocTinhChucNangLayout);
        pnMuonTraThongKeTheoThuocTinhChucNangLayout.setHorizontalGroup(
            pnMuonTraThongKeTheoThuocTinhChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMuonTraThongKeTheoThuocTinhChucNangLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnMuonTraThongKeTheoThuocTinhChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMuonTraThongKeMuonTraTrongNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMuonTraThongKeTheoMaDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMuonTraThongKeTheoMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMuonTraThongKeTheoNgayMuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMuonTraThongKeTheoNgayHenTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnMuonTraThongKeTheoThuocTinhChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMuonTraThongKeInRaFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnMuonTraThongKeTheoThuocTinhChucNangLayout.setVerticalGroup(
            pnMuonTraThongKeTheoThuocTinhChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMuonTraThongKeTheoThuocTinhChucNangLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(pnMuonTraThongKeTheoThuocTinhChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMuonTraThongKeTheoMaDocGia)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnMuonTraThongKeTheoThuocTinhChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMuonTraThongKeTheoMaNhanVien)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnMuonTraThongKeTheoThuocTinhChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMuonTraThongKeTheoNgayMuon)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnMuonTraThongKeTheoThuocTinhChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMuonTraThongKeTheoNgayHenTra)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnMuonTraThongKeTheoThuocTinhChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMuonTraThongKeMuonTraTrongNgay)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnMuonTraThongKeTheoThuocTinhChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(btnMuonTraThongKeInRaFile))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblLogoMuonTraThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogoMuonTraThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1237916771593128258jean_victor_balin_graphics_rounded.svg.med.png"))); // NOI18N
        lblLogoMuonTraThongKe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel6.setText("Ngày:");

        jDateChooserNgay.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout pnMuonTraThongKeTheoThuocTinhLayout = new javax.swing.GroupLayout(pnMuonTraThongKeTheoThuocTinh);
        pnMuonTraThongKeTheoThuocTinh.setLayout(pnMuonTraThongKeTheoThuocTinhLayout);
        pnMuonTraThongKeTheoThuocTinhLayout.setHorizontalGroup(
            pnMuonTraThongKeTheoThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMuonTraThongKeTheoThuocTinhLayout.createSequentialGroup()
                .addGroup(pnMuonTraThongKeTheoThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnMuonTraThongKeTheoThuocTinhLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnMuonTraThongKeTheoThuocTinhLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(pnMuonTraThongKeTheoThuocTinhChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnMuonTraThongKeTheoThuocTinhKetQua, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLogoMuonTraThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnMuonTraThongKeTheoThuocTinhLayout.setVerticalGroup(
            pnMuonTraThongKeTheoThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMuonTraThongKeTheoThuocTinhLayout.createSequentialGroup()
                .addGroup(pnMuonTraThongKeTheoThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnMuonTraThongKeTheoThuocTinhLayout.createSequentialGroup()
                        .addContainerGap(121, Short.MAX_VALUE)
                        .addComponent(lblLogoMuonTraThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(pnMuonTraThongKeTheoThuocTinhLayout.createSequentialGroup()
                        .addComponent(pnMuonTraThongKeTheoThuocTinhKetQua, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(pnMuonTraThongKeTheoThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooserNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(pnMuonTraThongKeTheoThuocTinhChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );

        tbdMuonTraThongKe.addTab("Thống Kê Theo Thuộc Tính", pnMuonTraThongKeTheoThuocTinh);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 989, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tbdMuonTraThongKe))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tbdMuonTraThongKe))
        );

        jTabbedPane1.addTab("Mượn Trả", new javax.swing.ImageIcon(getClass().getResource("/image/notebook.png")), jPanel7); // NOI18N

        jPanel25.setBackground(new java.awt.Color(51, 204, 255));

        jPanel26.setBackground(new java.awt.Color(51, 204, 255));

        jPanel27.setBackground(new java.awt.Color(51, 204, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Chi Tiết Mượn Trả:"));
        jPanel27.setLayout(new java.awt.BorderLayout());

        jScrollPane6.setViewportView(tblChiTietMuonTraThongKeChiTiet);

        jPanel27.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        jPanel28.setBackground(new java.awt.Color(51, 204, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng:"));

        btnChiTietMuonTraThongKeToanBoInRaFile.setBackground(new java.awt.Color(51, 204, 255));
        btnChiTietMuonTraThongKeToanBoInRaFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16a.png"))); // NOI18N
        btnChiTietMuonTraThongKeToanBoInRaFile.setText("In Ra File");
        btnChiTietMuonTraThongKeToanBoInRaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietMuonTraThongKeToanBoInRaFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(btnChiTietMuonTraThongKeToanBoInRaFile)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnChiTietMuonTraThongKeToanBoInRaFile)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane5.addTab("Thống Kê Toàn Bộ Chi Tiết Mượn Trả", jPanel26);

        jPanel29.setBackground(new java.awt.Color(51, 204, 255));

        jPanel30.setBackground(new java.awt.Color(51, 204, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder("Kết Quả:"));
        jPanel30.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setViewportView(jTable2);

        jPanel30.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel32.setBackground(new java.awt.Color(51, 204, 255));
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng:"));

        btnChiTietMuonTraThongKeTheoMaSach.setBackground(new java.awt.Color(51, 204, 255));
        btnChiTietMuonTraThongKeTheoMaSach.setText("Thống Kê Theo Mã Sách");
        btnChiTietMuonTraThongKeTheoMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietMuonTraThongKeTheoMaSachActionPerformed(evt);
            }
        });

        btnChiTietMuonTraThongKeTheoNgayTra.setBackground(new java.awt.Color(51, 204, 255));
        btnChiTietMuonTraThongKeTheoNgayTra.setText("Thống Kê Theo Ngày Trả");
        btnChiTietMuonTraThongKeTheoNgayTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietMuonTraThongKeTheoNgayTraActionPerformed(evt);
            }
        });

        btnChiTietMuonTraThongKeTheoTienPhat.setBackground(new java.awt.Color(51, 204, 255));
        btnChiTietMuonTraThongKeTheoTienPhat.setText("Thống Kê Theo Tiền Phạt");
        btnChiTietMuonTraThongKeTheoTienPhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietMuonTraThongKeTheoTienPhatActionPerformed(evt);
            }
        });

        btnThongKeTheoMaMuonTra.setBackground(new java.awt.Color(51, 204, 255));
        btnThongKeTheoMaMuonTra.setText("Thống Kê Theo Mã Mượn Trả");
        btnThongKeTheoMaMuonTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeTheoMaMuonTraActionPerformed(evt);
            }
        });

        btnChiTietMuonTraThongKeThuocTinhInRaFile.setBackground(new java.awt.Color(51, 204, 255));
        btnChiTietMuonTraThongKeThuocTinhInRaFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16a.png"))); // NOI18N
        btnChiTietMuonTraThongKeThuocTinhInRaFile.setText("In Ra File");
        btnChiTietMuonTraThongKeThuocTinhInRaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietMuonTraThongKeThuocTinhInRaFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThongKeTheoMaMuonTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChiTietMuonTraThongKeTheoMaSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChiTietMuonTraThongKeTheoNgayTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChiTietMuonTraThongKeTheoTienPhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChiTietMuonTraThongKeThuocTinhInRaFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(btnThongKeTheoMaMuonTra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChiTietMuonTraThongKeTheoMaSach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChiTietMuonTraThongKeTheoNgayTra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChiTietMuonTraThongKeTheoTienPhat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChiTietMuonTraThongKeThuocTinhInRaFile)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1237916771593128258jean_victor_balin_graphics_rounded.svg.med.png"))); // NOI18N
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        jTabbedPane5.addTab("Thống Kê Theo Thuộc Tính", jPanel29);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 989, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.Alignment.TRAILING))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane5))
        );

        jTabbedPane1.addTab("Chi Tiết Mượn Trả", new javax.swing.ImageIcon(getClass().getResource("/image/icons8-details.png")), jPanel25); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, Short.MAX_VALUE))
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

    private void btnToanBoSachInRaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToanBoSachInRaFileActionPerformed
        // TODO add your handling code here:
        //        JOptionPane.showMessageDialog(null, "Chức năng đang cập nhật");
        //        JFileChooser chooser = new JFileChooser();
        //        if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
        //            File file = chooser.getSelectedFile();
        //            JOptionPane.showMessageDialog(null, file.getAbsoluteFile());
        //        }
        JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                + "<p>Vui lòng chờ trong giây lát</p></html>");
        printSach.printToanBoSach();
    }//GEN-LAST:event_btnToanBoSachInRaFileActionPerformed

    private void btnThongKeTheoTenSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoTenSachActionPerformed
        // TODO add your handling code here:
        HashMap<String, Integer> thongke = bllSach.ketQuaThongKeSachTheoTenSach();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Tên Sách");
        dtm.addColumn("Số Lượng(sách)");
        int stt = 1;
        for (Map.Entry<String, Integer> m : thongke.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtm.addRow(v);
            stt++;
        }
        tblThongKeKetQua.setModel(dtm);
        btn = btnThongKeTheoTenSach;
    }//GEN-LAST:event_btnThongKeTheoTenSachActionPerformed

    private void btnThongKeTheoTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoTacGiaActionPerformed
        // TODO add your handling code here:
        HashMap<String, Integer> thongke = bllSach.ketQuaThongKeSachTheoTacGia();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Tác Giả");
        dtm.addColumn("Số Lượng(sách)");
        int stt = 1;
        for (Map.Entry<String, Integer> m : thongke.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtm.addRow(v);
            stt++;
        }
        tblThongKeKetQua.setModel(dtm);
        btn = btnThongKeTheoTacGia;
    }//GEN-LAST:event_btnThongKeTheoTacGiaActionPerformed

    private void btnThongKeTheoNhaXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoNhaXuatBanActionPerformed
        // TODO add your handling code here:
        HashMap<String, Integer> thongke = bllSach.ketQuaThongKeSachTheoNhaXuatBan();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Nhà Xuất Bản");
        dtm.addColumn("Số Lượng(sách)");
        int stt = 1;
        for (Map.Entry<String, Integer> m : thongke.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtm.addRow(v);
            stt++;
        }
        tblThongKeKetQua.setModel(dtm);
        btn = btnThongKeTheoNhaXuatBan;
    }//GEN-LAST:event_btnThongKeTheoNhaXuatBanActionPerformed

    private void btnThongKeTheoTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoTheLoaiActionPerformed
        // TODO add your handling code here:
        HashMap<String, Integer> thongke = bllSach.ketQuaThongKeSachTheoTheLoai();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Thể Loại");
        dtm.addColumn("Số Lượng(sách)");
        int stt = 1;
        for (Map.Entry<String, Integer> m : thongke.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtm.addRow(v);
            stt++;
        }
        tblThongKeKetQua.setModel(dtm);
        btn = btnThongKeTheoTheLoai;
    }//GEN-LAST:event_btnThongKeTheoTheLoaiActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        HashMap<String, Integer> thongke = bllSach.ketQuaThongKeSachTheoTinhTrang();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Tình Trạng");
        dtm.addColumn("Số Lượng(sách)");
        int stt = 1;
        for (Map.Entry<String, Integer> m : thongke.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtm.addRow(v);
            stt++;
        }
        tblThongKeKetQua.setModel(dtm);
        btn = jButton3;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnThongKeInRaFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeInRaFile1ActionPerformed
        // TODO add your handling code here:
        if (btn == btnThongKeTheoTenSach) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            printSach.printThongKeSachTheoTenSach();
        } else if (btn == btnThongKeTheoTacGia) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            printSach.printThongKeSachTheoTacGia();
        } else if (btn == btnThongKeTheoNhaXuatBan) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            printSach.printThongKeSachTheoNhaXuatBan();
        } else if (btn == btnThongKeTheoTheLoai) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            printSach.printThongKeSachTheoTheLoai();
        } else if (btn == jButton3) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            printSach.printThongKeSachTheoTinhTrang();
        } else if (btn == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng bấm nút thống kê trước");
            return;
        }
    }//GEN-LAST:event_btnThongKeInRaFile1ActionPerformed

    private void btnThongKeToanBoInRaFIleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeToanBoInRaFIleActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                + "<p>Vui lòng chờ trong giây lát</p></html>");
        inNhanVien.printToanBoNhanVien();
    }//GEN-LAST:event_btnThongKeToanBoInRaFIleActionPerformed

    private void btnThongKeTheoTenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoTenNhanVienActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Tên Nhân Viên");
        dtmThongKe.addColumn("Số Lượng(nhân viên)");
        HashMap<String, Integer> ds = bllNhanVien.thongKeTheoTen();
        int stt = 1;
        for (Map.Entry<String, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        tblThongKe.setModel(dtmThongKe);
        btnThongKeNhanVien = btnThongKeTheoTenNhanVien;
    }//GEN-LAST:event_btnThongKeTheoTenNhanVienActionPerformed

    private void btnThongKeTheoGioiTinh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoGioiTinh1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Giới Tính");
        dtmThongKe.addColumn("Số Lượng(nhân viên)");
        HashMap<String, Integer> ds = bllNhanVien.thongKeTheoGioiTinh();
        int stt = 1;
        for (Map.Entry<String, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        tblThongKe.setModel(dtmThongKe);
        btnThongKeNhanVien = btnThongKeTheoGioiTinh;
    }//GEN-LAST:event_btnThongKeTheoGioiTinh1ActionPerformed

    private void btnThongKeTheoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoLuongActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Lương");
        dtmThongKe.addColumn("Số Lượng(nhân viên)");
        HashMap<String, Integer> ds = bllNhanVien.thongKeTheoLuong();
        int stt = 1;
        for (Map.Entry<String, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        tblThongKe.setModel(dtmThongKe);
        btnThongKeNhanVien = btnThongKeTheoLuong;
    }//GEN-LAST:event_btnThongKeTheoLuongActionPerformed

    private void btnThongKeTheoDiaChi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoDiaChi1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Địa Chỉ");
        dtmThongKe.addColumn("Số Lượng(nhân viên)");
        HashMap<String, Integer> ds = bllNhanVien.thongKeTheoDiaChi();
        int stt = 1;
        for (Map.Entry<String, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        tblThongKe.setModel(dtmThongKe);
        btnThongKeNhanVien = btnThongKeTheoDiaChi;
    }//GEN-LAST:event_btnThongKeTheoDiaChi1ActionPerformed

    private void btnThongKeInRaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeInRaFileActionPerformed
        // TODO add your handling code here:
        if (btnThongKeNhanVien == btnThongKeTheoTenNhanVien) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            inNhanVien.printThongKeTheoTen();
        } else if (btnThongKeNhanVien == btnThongKeTheoGioiTinh) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            inNhanVien.printThongKeTheoGioiTinh();
        } else if (btnThongKeNhanVien == btnThongKeTheoLuong) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            inNhanVien.printThongKeTheoLuong();
        } else if (btnThongKeNhanVien == btnThongKeTheoDiaChi) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            inNhanVien.printThongKeTheoDiaChi();
        } else if (btnThongKeNhanVien == null) {
            JOptionPane.showMessageDialog(null, "Hãy chọn 1 nút thống kê trước");
            return;
        }
    }//GEN-LAST:event_btnThongKeInRaFileActionPerformed

    private void btnDanhSachDocGiaInRaFilrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachDocGiaInRaFilrActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                + "<p>Vui lòng chờ trong giây lát</p></html>");
        inDocGia.printToanBoDocGia();
    }//GEN-LAST:event_btnDanhSachDocGiaInRaFilrActionPerformed

    private void btnThongKeTheoTenDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoTenDocGiaActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Tên Độc Giả");
        dtmThongKe.addColumn("Số Lượng(độc giả)");
        int stt = 1;
        HashMap<String, Integer> ds = bllDocGia.thongKeTheoTen();
        for (Map.Entry<String, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        tblThongKeDocGia.setModel(dtmThongKe);
        btnThongkeDocGia = btnThongKeTheoTenDocGia;
    }//GEN-LAST:event_btnThongKeTheoTenDocGiaActionPerformed

    private void btnThongKeTheoGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoGioiTinhActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Giới Tính");
        dtmThongKe.addColumn("Số Lượng(độc giả)");
        int stt = 1;
        HashMap<String, Integer> ds = bllDocGia.thongKeTheoGioiTinh();
        for (Map.Entry<String, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        tblThongKeDocGia.setModel(dtmThongKe);
        btnThongkeDocGia = btnThongKeTheoGioiTinh;
    }//GEN-LAST:event_btnThongKeTheoGioiTinhActionPerformed

    private void btnThongKeTheoDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoDiaChiActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Địa Chỉ");
        dtmThongKe.addColumn("Số Lượng(độc giả)");
        int stt = 1;
        HashMap<String, Integer> ds = bllDocGia.thongKeTheoDiaChi();
        for (Map.Entry<String, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        tblThongKeDocGia.setModel(dtmThongKe);
        btnThongkeDocGia = btnThongKeTheoDiaChi;
    }//GEN-LAST:event_btnThongKeTheoDiaChiActionPerformed

    private void btnThongKeInRaFIleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeInRaFIleActionPerformed
        // TODO add your handling code here:
        if (btnThongkeDocGia == btnThongKeTheoTenDocGia) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            inDocGia.printThongKeTheoTen();
        } else if (btnThongkeDocGia == btnThongKeTheoGioiTinh) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            inDocGia.printThongKeTheoGioiTinh();
        } else if (btnThongkeDocGia == btnThongKeTheoDiaChi) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            inDocGia.printThongKeTheoDiaChi();
        } else if (btnThongkeDocGia == null) {
            JOptionPane.showMessageDialog(null, "Hãy chọn 1 nút thống kê trước");
            return;
        }
    }//GEN-LAST:event_btnThongKeInRaFIleActionPerformed

    private void btnMuonTraThongKeToanBoInRaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuonTraThongKeToanBoInRaFileActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                + "<p>Vui lòng chờ trong giây lát</p></html>");
        PrintMuonTra.printDanhSachMuonTra();
    }//GEN-LAST:event_btnMuonTraThongKeToanBoInRaFileActionPerformed

    private void btnMuonTraThongKeTheoMaDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuonTraThongKeTheoMaDocGiaActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Mã Độc Giả");
        dtmThongKe.addColumn("Số Lượng(mượn trả)");
        int stt = 1;
        HashMap<String, Integer> ds = bllMuonTra.thongKeTheoMaDocGia();
        for (Map.Entry<String, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        tblThongKeMuonTra.setModel(dtmThongKe);
        btnMuonTra = btnMuonTraThongKeTheoMaDocGia;
    }//GEN-LAST:event_btnMuonTraThongKeTheoMaDocGiaActionPerformed

    private void btnMuonTraThongKeTheoMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuonTraThongKeTheoMaNhanVienActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Mã Nhân Viên");
        dtmThongKe.addColumn("Số Lượng(mượn trả)");
        int stt = 1;
        HashMap<String, Integer> ds = bllMuonTra.thongKeTheoMaNhanVien();
        for (Map.Entry<String, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        tblThongKeMuonTra.setModel(dtmThongKe);
        btnMuonTra = btnMuonTraThongKeTheoMaNhanVien;
    }//GEN-LAST:event_btnMuonTraThongKeTheoMaNhanVienActionPerformed

    private void btnMuonTraThongKeTheoNgayMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuonTraThongKeTheoNgayMuonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Ngày Mượn");
        dtmThongKe.addColumn("Số Lượng(mượn trả)");
        int stt = 1;
        HashMap<Date, Integer> ds = bllMuonTra.thongKeTheoNgayMuon();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Map.Entry<Date, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(sdf.format(m.getKey()));
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        tblThongKeMuonTra.setModel(dtmThongKe);
        btnMuonTra = btnMuonTraThongKeTheoNgayMuon;
    }//GEN-LAST:event_btnMuonTraThongKeTheoNgayMuonActionPerformed

    private void btnMuonTraThongKeTheoNgayHenTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuonTraThongKeTheoNgayHenTraActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Ngày Hẹn Trả");
        dtmThongKe.addColumn("Số Lượng(mượn trả)");
        int stt = 1;
        HashMap<Date, Integer> ds = bllMuonTra.thongKeTheoNgayHenTra();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Map.Entry<Date, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(sdf.format(m.getKey()));
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        tblThongKeMuonTra.setModel(dtmThongKe);
        btnMuonTra = btnMuonTraThongKeTheoNgayHenTra;
    }//GEN-LAST:event_btnMuonTraThongKeTheoNgayHenTraActionPerformed

    private void btnMuonTraThongKeMuonTraTrongNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuonTraThongKeMuonTraTrongNgayActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Ngày");
        dtm.addColumn("Số Lượng (Sách mượn)");
        int soLuong = 0;
        Date ngay = jDateChooserNgay.getDate();
        if (ngay == null) {
            Calendar cal = Calendar.getInstance();
            ngay = cal.getTime();
        }
        soLuong = bllMuonTra.soSachMuonNgay(ngay);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Vector<String> v = new Vector<>();
        v.add(sdf.format(ngay));
        v.add(soLuong + "");
        dtm.addRow(v);
        tblThongKeMuonTra.setModel(dtm);
        btnMuonTra = btnMuonTraThongKeMuonTraTrongNgay;
    }//GEN-LAST:event_btnMuonTraThongKeMuonTraTrongNgayActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Tiền Cọc");
        dtmThongKe.addColumn("Số Lượng(mượn trả)");
        int stt = 1;
        HashMap<Integer, Integer> ds = bllMuonTra.thongKeTheoTienCoc();
        for (Map.Entry<Integer, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey() + "");
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        tblThongKeMuonTra.setModel(dtmThongKe);
        btnMuonTra = jButton5;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnMuonTraThongKeInRaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuonTraThongKeInRaFileActionPerformed
        // TODO add your handling code here:
        Date ngay = jDateChooserNgay.getDate();
        if (ngay == null) {
            ngay = Calendar.getInstance().getTime();
        }
        if (btnMuonTra == null) {
            JOptionPane.showMessageDialog(null, "Hãy chọn một nút thống kê trước");
            return;
        }
        JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                + "<p>Vui lòng đợi trong giây lát</p></html>");
        if (btnMuonTra == btnMuonTraThongKeTheoMaDocGia) {
            PrintMuonTra.printThongKeTheoMaDocGia();
        } else if (btnMuonTra == btnMuonTraThongKeTheoMaNhanVien) {
            PrintMuonTra.printThongKeTheoMaNhanVien();
        } else if (btnMuonTra == btnMuonTraThongKeTheoNgayHenTra) {
            PrintMuonTra.printThongKeTheoNgayHenTra();
        } else if (btnMuonTraThongKeTheoNgayMuon == btnMuonTra) {
            PrintMuonTra.printThongKeTheoNgayMuon();
        } else if (btnMuonTra == jButton5) {
            PrintMuonTra.printThongKeTheoTienCoc();
        } else if (btnMuonTra == jButton1) {
            PrintMuonTra.inSachMuonNgay(ngay);
        } else if (btnMuonTra == jButton2) {
            PrintMuonTra.inDocGiaMuonNgay(ngay);
        } else if (btnMuonTra == jButton4) {
            PrintMuonTra.inDanhSachSachTraNgay(ngay);
        } else if (btnMuonTra == jButton6) {
            PrintMuonTra.inDanhSachDocGiaTraNgay(ngay);
        } else if (btnMuonTra == btnMuonTraThongKeMuonTraTrongNgay) {
            PrintMuonTra.inSoLuongSachMuonNgay(ngay);
        } else if (btnMuonTra == jButton7) {
            PrintMuonTra.inSoLuongMuonTraNgay(ngay);
        }
    }//GEN-LAST:event_btnMuonTraThongKeInRaFileActionPerformed

    private void btnChiTietMuonTraThongKeToanBoInRaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietMuonTraThongKeToanBoInRaFileActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                + "<p>Vui lòng chờ trong giây lát</p></html>");
        PrintChiTietMuonTra.printDanhSachChiTietMuonTra();
    }//GEN-LAST:event_btnChiTietMuonTraThongKeToanBoInRaFileActionPerformed

    private void btnChiTietMuonTraThongKeTheoMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietMuonTraThongKeTheoMaSachActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Mã Sách");
        dtmThongKe.addColumn("Số Lượng(mượn trả)");
        int stt = 1;
        HashMap<String, Integer> ds = bllChiTiet.thongKeTheoMaSach();
        for (Map.Entry<String, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        jTable2.setModel(dtmThongKe);
        btnChiTietMuonTra = btnChiTietMuonTraThongKeTheoMaSach;
    }//GEN-LAST:event_btnChiTietMuonTraThongKeTheoMaSachActionPerformed

    private void btnChiTietMuonTraThongKeTheoNgayTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietMuonTraThongKeTheoNgayTraActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Ngày Trả");
        dtmThongKe.addColumn("Số Lượng(sách)");
        int stt = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyy");
        HashMap<Date, Integer> ds = bllChiTiet.thongKeTheoNgayTra();
        for (Map.Entry<Date, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            Date ngayTra = m.getKey();
            if (ngayTra == null) {
                v.add("");
            } else {
                v.add(sdf.format(m.getKey()));
            }
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        jTable2.setModel(dtmThongKe);
        btnChiTietMuonTra = btnChiTietMuonTraThongKeTheoNgayTra;
    }//GEN-LAST:event_btnChiTietMuonTraThongKeTheoNgayTraActionPerformed

    private void btnChiTietMuonTraThongKeTheoTienPhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietMuonTraThongKeTheoTienPhatActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Tiền Phạt");
        dtmThongKe.addColumn("Số Lượng(sách)");
        int stt = 1;
        HashMap<Integer, Integer> ds = bllChiTiet.thongKeTheoTienPhat();
        for (Map.Entry<Integer, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey() + "");
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        jTable2.setModel(dtmThongKe);
        btnChiTietMuonTra = btnChiTietMuonTraThongKeTheoTienPhat;
    }//GEN-LAST:event_btnChiTietMuonTraThongKeTheoTienPhatActionPerformed

    private void btnThongKeTheoMaMuonTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeTheoMaMuonTraActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtmThongKe = new DefaultTableModel();
        dtmThongKe.addColumn("STT");
        dtmThongKe.addColumn("Mã Mượn Trả");
        dtmThongKe.addColumn("Số Lượng(sách)");
        int stt = 1;
        HashMap<String, Integer> ds = bllChiTiet.thongKeTheoMaMuonTra();
        for (Map.Entry<String, Integer> m : ds.entrySet()) {
            Vector<String> v = new Vector<>();
            v.add(stt + "");
            v.add(m.getKey());
            v.add(m.getValue() + "");
            dtmThongKe.addRow(v);
            stt++;
        }
        jTable2.setModel(dtmThongKe);
        btnChiTietMuonTra = btnThongKeTheoMaMuonTra;
    }//GEN-LAST:event_btnThongKeTheoMaMuonTraActionPerformed

    private void btnChiTietMuonTraThongKeThuocTinhInRaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietMuonTraThongKeThuocTinhInRaFileActionPerformed
        // TODO add your handling code here:
        if (btnChiTietMuonTra == btnThongKeTheoMaMuonTra) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            PrintChiTietMuonTra.printThongKeTheoMaMuonTra();
        } else if (btnChiTietMuonTra == btnChiTietMuonTraThongKeTheoMaSach) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            PrintChiTietMuonTra.printThongKeTheoMaSach();
        } else if (btnChiTietMuonTra == btnChiTietMuonTraThongKeTheoNgayTra) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            PrintChiTietMuonTra.printThongKeTheoNgayTra();
        } else if (btnChiTietMuonTra == btnChiTietMuonTraThongKeTheoTienPhat) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            PrintChiTietMuonTra.printThongKeTheoTienPhat();
        } else if (btnChiTietMuonTra == null) {
            JOptionPane.showMessageDialog(null, "Hãy chọn một loại thống kê trước");
            return;
        }
    }//GEN-LAST:event_btnChiTietMuonTraThongKeThuocTinhInRaFileActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Date ngay = jDateChooserNgay.getDate();
        if (ngay == null) {
            ngay = Calendar.getInstance().getTime();
        }
        ArrayList<Sach> ds = bllMuonTra.sachMuonNgay(ngay);
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã sách");
        dtm.addColumn("Tên sách");
        dtm.addColumn("Tác giả");
        dtm.addColumn("Nhà xuất bản");
        dtm.addColumn("Thể loại");
        for (Sach s : ds) {
            Vector<String> v = new Vector<>();
            v.add(s.getMaSach_20151094());
            v.add(s.getTenSach_20151094());
            v.add(s.getTacGia_20151094());
            v.add(s.getNhaXuatBan_20151094());
            v.add(s.getTheLoai_20151094());
            dtm.addRow(v);
        }
        tblThongKeMuonTra.setModel(dtm);
        btnMuonTra = jButton1;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Date ngay = jDateChooserNgay.getDate();
        if (ngay == null) {
            ngay = Calendar.getInstance().getTime();
        }
        ArrayList<DocGia> ds = bllMuonTra.docGiaMuonNgay(ngay);
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã độc giả");
        dtm.addColumn("Tên độc giả");
        dtm.addColumn("Giới tính");
        dtm.addColumn("Số điện thoại");
        dtm.addColumn("Email");
        dtm.addColumn("Đia chỉ");
        for (DocGia dg : ds) {
            Vector<String> v = new Vector<>();
            v.add(dg.getMaDocGia());
            v.add(dg.getTenDocGia());
            v.add(dg.getGioiTinh());
            v.add(dg.getPhone());
            v.add(dg.getEmail());
            v.add(dg.getDiaChi());
            dtm.addRow(v);
        }
        tblThongKeMuonTra.setModel(dtm);
        btnMuonTra = jButton2;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Date ngay = jDateChooserNgay.getDate();
        if (ngay == null) {
            ngay = Calendar.getInstance().getTime();
        }
        ArrayList<Sach> ds = bllMuonTra.sachTraNgay(ngay);
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã sách");
        dtm.addColumn("Tên sách");
        dtm.addColumn("Tác giả");
        dtm.addColumn("Nhà xuất bản");
        dtm.addColumn("Thể loại");
        for (Sach s : ds) {
            Vector<String> v = new Vector<>();
            v.add(s.getMaSach_20151094());
            v.add(s.getTenSach_20151094());
            v.add(s.getTacGia_20151094());
            v.add(s.getNhaXuatBan_20151094());
            v.add(s.getTheLoai_20151094());
            dtm.addRow(v);
        }
        tblThongKeMuonTra.setModel(dtm);
        btnMuonTra = jButton4;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Date ngay = jDateChooserNgay.getDate();
        if (ngay == null) {
            ngay = Calendar.getInstance().getTime();
        }
        ArrayList<DocGia> ds = bllMuonTra.docGiaTraNgay(ngay);
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã độc giả");
        dtm.addColumn("Tên độc giả");
        dtm.addColumn("Giới tính");
        dtm.addColumn("Số điện thoại");
        dtm.addColumn("Email");
        dtm.addColumn("Đia chỉ");
        for (DocGia dg : ds) {
            Vector<String> v = new Vector<>();
            v.add(dg.getMaDocGia());
            v.add(dg.getTenDocGia());
            v.add(dg.getGioiTinh());
            v.add(dg.getPhone());
            v.add(dg.getEmail());
            v.add(dg.getDiaChi());
            dtm.addRow(v);
        }
        tblThongKeMuonTra.setModel(dtm);
        btnMuonTra = jButton6;
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Ngày");
        dtm.addColumn("Số Lượng (Mượn Trả)");
        int soLuong = 0;
        Date ngay = jDateChooserNgay.getDate();
        if (ngay == null) {
            Calendar cal = Calendar.getInstance();
            ngay = cal.getTime();
        }
        soLuong = bllMuonTra.demMuonTraTrongNgay(ngay);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Vector<String> v = new Vector<>();
        v.add(sdf.format(ngay));
        v.add(soLuong + "");
        dtm.addRow(v);
        tblThongKeMuonTra.setModel(dtm);
        btnMuonTra = jButton7;
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1StateChanged

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
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChiTietMuonTraThongKeTheoMaSach;
    private javax.swing.JButton btnChiTietMuonTraThongKeTheoNgayTra;
    private javax.swing.JButton btnChiTietMuonTraThongKeTheoTienPhat;
    private javax.swing.JButton btnChiTietMuonTraThongKeThuocTinhInRaFile;
    private javax.swing.JButton btnChiTietMuonTraThongKeToanBoInRaFile;
    private javax.swing.JButton btnDanhSachDocGiaInRaFilr;
    private javax.swing.JButton btnMuonTraThongKeInRaFile;
    private javax.swing.JButton btnMuonTraThongKeMuonTraTrongNgay;
    private javax.swing.JButton btnMuonTraThongKeTheoMaDocGia;
    private javax.swing.JButton btnMuonTraThongKeTheoMaNhanVien;
    private javax.swing.JButton btnMuonTraThongKeTheoNgayHenTra;
    private javax.swing.JButton btnMuonTraThongKeTheoNgayMuon;
    private javax.swing.JButton btnMuonTraThongKeToanBoInRaFile;
    private javax.swing.JButton btnThongKeInRaFIle;
    private javax.swing.JButton btnThongKeInRaFile;
    private javax.swing.JButton btnThongKeInRaFile1;
    private javax.swing.JButton btnThongKeTheoDiaChi;
    private javax.swing.JButton btnThongKeTheoDiaChi1;
    private javax.swing.JButton btnThongKeTheoGioiTinh;
    private javax.swing.JButton btnThongKeTheoGioiTinh1;
    private javax.swing.JButton btnThongKeTheoLuong;
    private javax.swing.JButton btnThongKeTheoMaMuonTra;
    private javax.swing.JButton btnThongKeTheoNhaXuatBan;
    private javax.swing.JButton btnThongKeTheoTacGia;
    private javax.swing.JButton btnThongKeTheoTenDocGia;
    private javax.swing.JButton btnThongKeTheoTenNhanVien;
    private javax.swing.JButton btnThongKeTheoTenSach;
    private javax.swing.JButton btnThongKeTheoTheLoai;
    private javax.swing.JButton btnThongKeToanBoInRaFIle;
    private javax.swing.JButton btnToanBoSachInRaFile;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JDateChooser jDateChooserNgay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblGio;
    private javax.swing.JLabel lblLogoMuonTraThongKe;
    private javax.swing.JPanel pnMuonTraDanhSachMuonTra;
    private javax.swing.JPanel pnMuonTraThongKeTheoThuocTinh;
    private javax.swing.JPanel pnMuonTraThongKeTheoThuocTinhChucNang;
    private javax.swing.JPanel pnMuonTraThongKeTheoThuocTinhKetQua;
    private javax.swing.JPanel pnMuonTraThongKeToanBo;
    private javax.swing.JPanel pnMuonTraThongKeToanBoChucNang;
    private javax.swing.JScrollPane scMuonTraThongKeDanhSach;
    private javax.swing.JTabbedPane tbdMuonTraThongKe;
    private javax.swing.JTable tblChiTietMuonTraThongKeChiTiet;
    private javax.swing.JTable tblDanhSachDocGia;
    private javax.swing.JTable tblMuonTraThongKeDanhSach;
    private javax.swing.JTable tblThongKe;
    private javax.swing.JTable tblThongKeDanhSachNhanVien;
    private javax.swing.JTable tblThongKeDocGia;
    private javax.swing.JTable tblThongKeKetQua;
    private javax.swing.JTable tblThongKeMuonTra;
    private javax.swing.JTable tblToanBoSach;
    // End of variables declaration//GEN-END:variables
}
