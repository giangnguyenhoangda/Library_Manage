/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLLDocGia;
import BLL.BLLMuonTra;
import BLL.BLLNhanVien;
import BLL.KiemTraDuLieuNhapThoaMan;
import DAL.PrintMuonTra;
import DAL.PrintSach;
import DTO.DocGia;
import DTO.MuonTra;
import DTO.NhanVien;
import DTO.Sach;
import static GUI.QuanLyMuonTra.mtChon;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
public class XemThongTin extends javax.swing.JFrame {

    /**
     * Creates new form XemThongTin
     */
    BLL.BLLSach bllSach;
    DefaultTableModel dtmTimKiemSach;
    PrintSach printSach;
    DefaultTableModel dtmMau;
    SimpleDateFormat sdf, sdf1;
    BLLMuonTra bllMuonTra;
    DefaultTableModel dtmTimKiem, dtmToanBoMuonTra;
    static MuonTra mtChon;
    static DocGia dgChon;
    static NhanVien nvChon;
    BLLNhanVien bllNhanVien;
    BLLDocGia bllDocGia;
    JButton btnLastMuonTra, btnLastSach;

    public XemThongTin() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
        }
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        bllNhanVien = new BLLNhanVien();
        bllDocGia = new BLLDocGia();
        bllSach = new BLL.BLLSach();
        printSach = new PrintSach();
        bllMuonTra = new BLLMuonTra();
        dtmMau = new DefaultTableModel();
        dtmMau.addColumn("Mã Sách");
        dtmMau.addColumn("Tên Sách");
        dtmMau.addColumn("Tác Giả");
        dtmMau.addColumn("Nhà Xuất Bản");
        dtmMau.addColumn("Thể Loại");
        dtmMau.addColumn("Tình Trạng");
        DefaultComboBoxModel<String> itemComboxTimKiemTinhTrang = new DefaultComboBoxModel<String>();
        itemComboxTimKiemTinhTrang.addElement("Còn sách");
        itemComboxTimKiemTinhTrang.addElement("Đã cho mượn");
        itemComboxTimKiemTinhTrang.addElement("Không tồn tại");
        cbxTimKiemTinhTrang.setModel(itemComboxTimKiemTinhTrang);
        cbxTimKiemTinhTrang.setSelectedIndex(-1);
        tblTimKiemKetQua.setModel(dtmMau);
        hienThiToanBoSachLenBangDanhSach();
        autoDoiMauChu(jLabel1, Color.BLACK, Color.RED);
        jLabel7.setForeground(Color.BLUE);
        autoDoiMauChu(jLabel7, Color.BLUE, Color.GREEN);
        sdf1 = new SimpleDateFormat("hh:mm:ss aa");
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        Color mauChu = jLabel3.getForeground();
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                jLabel3.setText(sdf1.format(cal.getTime()));
                if (jLabel3.getForeground() == mauChu) {
                    jLabel3.setForeground(Color.BLACK);
                } else {
                    jLabel3.setForeground(mauChu);
                }
            }
        });
        t.start();
        Calendar cal = Calendar.getInstance();
        jLabel3.setText(sdf1.format(cal.getTime()));
        hienThiToanBoMuonTra();
        hienThiMaDocGiaLenCombobox();
        hienThiMaDocGiaLenComBoxTimKiemMaDocGia();
        hienThiMaNhanVienLenComBoxTimKiemMaNhanVien();
        jScrollPane3.setOpaque(false);
        jScrollPane3.getViewport().setOpaque(false);
        scTimKiemKetQua.setOpaque(false);
        scTimKiemKetQua.getViewport().setOpaque(false);
    }

    private void hienThiMaDocGiaLenCombobox() {
        ArrayList<DocGia> list = bllDocGia.layDanhSachDocGia();
        DefaultComboBoxModel<String> cbxModelCapNhatMaDocGia = new DefaultComboBoxModel<String>();
        if (list.size() > 0) {
            for (DocGia s : list) {
                cbxModelCapNhatMaDocGia.addElement(s.getMaDocGia());
            }
        }
        cbxTimKiemMaDocGia.setModel(cbxModelCapNhatMaDocGia);
    }

    private void hienThiMaDocGiaLenComBoxTimKiemMaDocGia() {
        ArrayList<DocGia> ds = bllDocGia.layDanhSachDocGia();
        DefaultComboBoxModel cbxModel = new DefaultComboBoxModel<Object>();
        DefaultComboBoxModel<String> cbx = new DefaultComboBoxModel<>();
        for (DocGia dg : ds) {
            cbxModel.addElement(dg);
            cbx.addElement(dg.getTenDocGia());
        }
        cbxTimKiemTenDocGia.setModel(cbx);
        cbxTimKiemTenDocGia.setSelectedIndex(-1);
        cbxTimKiemMaDocGia.setModel(cbxModel);
        cbxTimKiemMaDocGia.setSelectedIndex(-1);
    }

    private void hienThiMaNhanVienLenComBoxTimKiemMaNhanVien() {
        ArrayList<NhanVien> ds = bllNhanVien.layToanBoNhanVien();
        DefaultComboBoxModel cbxModel = new DefaultComboBoxModel<Object>();
        DefaultComboBoxModel<String> cbx = new DefaultComboBoxModel<>();
        for (NhanVien nv : ds) {
            cbxModel.addElement(nv);
            cbx.addElement(nv.getTenNhanVien());
        }
        cbxTimKiemTenNhanVien.setModel(cbx);
        cbxTimKiemTenNhanVien.setSelectedIndex(-1);
        cbxTimKiemMaNhanVien.setModel(cbxModel);
        cbxTimKiemMaNhanVien.setSelectedIndex(-1);
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
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã Sách");
        dtm.addColumn("Tên Sách");
        dtm.addColumn("Tác Giả");
        dtm.addColumn("Nhà Xuất Bản");
        dtm.addColumn("Thể Loại");
        dtm.addColumn("Tình Trạng");
        for (Sach s : ds) {
            Vector<String> v = new Vector<>();
            v.add(s.getMaSach_20151094());
            v.add(s.getTenSach_20151094());
            v.add(s.getTacGia_20151094());
            v.add(s.getNhaXuatBan_20151094());
            v.add(s.getTheLoai_20151094());
            v.add(s.getTinhTrang_20151094());
            dtm.addRow(v);
        }
        tblTimKiemKetQua.setModel(dtm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTimKiemMaSach = new javax.swing.JTextField();
        txtTimKiemTenSach = new javax.swing.JTextField();
        txtTimKiemTacGia = new javax.swing.JTextField();
        txtTimKiemNhaXuatBan = new javax.swing.JTextField();
        txtTimKiemTheLoai = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cbxTimKiemTinhTrang = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTimKiemKetQua = new javax.swing.JTable();
        btnTimKiem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnTimKiemInRaFile = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        pnTimKiemTimKiem = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtTimKiemMaMuonTra = new javax.swing.JTextField();
        txtTimKiemTienCoc = new javax.swing.JTextField();
        cbxTimKiemMaNhanVien = new javax.swing.JComboBox<>();
        cbxTimKiemMaDocGia = new javax.swing.JComboBox<>();
        cbxTimKiemTenDocGia = new javax.swing.JComboBox<>();
        cbxTimKiemTenNhanVien = new javax.swing.JComboBox<>();
        jDateChooserNgayMuon = new com.toedter.calendar.JDateChooser();
        jDateChooserNgayHenTra = new com.toedter.calendar.JDateChooser();
        pnTimKiemKetQua = new javax.swing.JPanel();
        scTimKiemKetQua = new javax.swing.JScrollPane();
        tblTimKiemKetQua1 = new javax.swing.JTable();
        btnTimKiemTimKiem = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Owl-Book-64.png"))); // NOI18N
        jLabel4.setText("QUẢN LÝ SÁCH");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("@Author Nguyễn Hoàng Giang");

        jLabel8.setText("MSSV: 20151094");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 153));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("jLabel10");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(26, 26, 26))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1250, 700));

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHÀO MỪNG BẠN ĐÃ ĐẾN VỚI THƯ VIỆN");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Bạn có thể tìm kiếm sách và xem thông tin mượn sách tại đây !");

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setBackground(new java.awt.Color(51, 204, 255));

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));

        jPanel6.setBackground(new java.awt.Color(51, 204, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Owl-Book-64.png"))); // NOI18N
        jLabel5.setText("TÌM KIẾM SÁCH");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5)
        );

        jPanel10.setBackground(new java.awt.Color(51, 204, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Sách:"));

        jLabel6.setText("Mã Sách:");

        jLabel13.setText("Tên Sách:");

        jLabel14.setText("Tác Giả:");

        jLabel15.setText("Nhà Xuất Bản:");

        jLabel16.setText("Thể Loại:");

        jLabel17.setText("Tình Trạng:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiemMaSach)
                    .addComponent(txtTimKiemTenSach)
                    .addComponent(txtTimKiemTacGia)
                    .addComponent(txtTimKiemNhaXuatBan)
                    .addComponent(txtTimKiemTheLoai)
                    .addComponent(cbxTimKiemTinhTrang, 0, 159, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTimKiemMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtTimKiemTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtTimKiemTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15))
                    .addComponent(txtTimKiemNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(txtTimKiemTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbxTimKiemTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(51, 204, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Kết Quả:"));
        jPanel11.setLayout(new java.awt.BorderLayout());

        tblTimKiemKetQua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblTimKiemKetQuaMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblTimKiemKetQua);

        jPanel11.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        btnTimKiem.setBackground(new java.awt.Color(51, 204, 255));
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Search-16.png"))); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 204, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Broom-16.png"))); // NOI18N
        jButton2.setText("Làm Sạch Ô Nhập Liệu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 204, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-select-all.png"))); // NOI18N
        jButton3.setText("Xem Toàn Bộ Sách");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnTimKiemInRaFile, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTimKiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem)
                        .addGap(14, 14, 14)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiemInRaFile)
                        .addContainerGap())
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Tìm Kiếm Sách", jPanel3);

        jPanel4.setBackground(new java.awt.Color(51, 204, 255));

        jPanel7.setBackground(new java.awt.Color(51, 204, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Books-64.png"))); // NOI18N
        jLabel12.setText("TÌM KIẾM THÔNG TIN MƯỢN SÁCH");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
        );

        pnTimKiemTimKiem.setBackground(new java.awt.Color(51, 204, 255));
        pnTimKiemTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Mượn Trả:"));

        jLabel18.setText("Mã Mượn Trả:");

        jLabel19.setText("Mã Độc Giả:");

        jLabel20.setText("Tên Độc Giả:");

        jLabel21.setText("Mã Nhân Viên:");

        jLabel22.setText("Tên Nhân Viên:");

        jLabel23.setText("Ngày Mượn:");

        jLabel24.setText("Ngày Hẹn Trả:");

        jLabel25.setText("Tiền Cọc:");

        cbxTimKiemMaNhanVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTimKiemMaNhanVienItemStateChanged(evt);
            }
        });

        cbxTimKiemMaDocGia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTimKiemMaDocGiaItemStateChanged(evt);
            }
        });

        cbxTimKiemTenDocGia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTimKiemTenDocGiaItemStateChanged(evt);
            }
        });

        cbxTimKiemTenNhanVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTimKiemTenNhanVienItemStateChanged(evt);
            }
        });

        jDateChooserNgayMuon.setDateFormatString("dd/MM/yyyy");

        jDateChooserNgayHenTra.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout pnTimKiemTimKiemLayout = new javax.swing.GroupLayout(pnTimKiemTimKiem);
        pnTimKiemTimKiem.setLayout(pnTimKiemTimKiemLayout);
        pnTimKiemTimKiemLayout.setHorizontalGroup(
            pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTimKiemTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiemMaMuonTra)
                    .addComponent(txtTimKiemTienCoc)
                    .addComponent(cbxTimKiemMaNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxTimKiemMaDocGia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxTimKiemTenDocGia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxTimKiemTenNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooserNgayMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jDateChooserNgayHenTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnTimKiemTimKiemLayout.setVerticalGroup(
            pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTimKiemTimKiemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(txtTimKiemMaMuonTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbxTimKiemMaDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cbxTimKiemTenDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cbxTimKiemMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbxTimKiemTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jDateChooserNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jDateChooserNgayHenTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtTimKiemTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        pnTimKiemKetQua.setBackground(new java.awt.Color(51, 204, 255));
        pnTimKiemKetQua.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Mượn Trả:"));
        pnTimKiemKetQua.setLayout(new java.awt.BorderLayout());

        tblTimKiemKetQua1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTimKiemKetQua1MousePressed(evt);
            }
        });
        scTimKiemKetQua.setViewportView(tblTimKiemKetQua1);

        pnTimKiemKetQua.add(scTimKiemKetQua, java.awt.BorderLayout.CENTER);

        btnTimKiemTimKiem.setBackground(new java.awt.Color(51, 204, 255));
        btnTimKiemTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Search-16.png"))); // NOI18N
        btnTimKiemTimKiem.setText("Tìm Kiếm");
        btnTimKiemTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTimKiemActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 204, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Broom-16.png"))); // NOI18N
        jButton1.setText("Làm Sạch Ô Nhập Dữ Liệu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(51, 204, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-select-all.png"))); // NOI18N
        jButton4.setText("Xem Toàn Bộ Mượn Trả");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(51, 204, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-List-16.png"))); // NOI18N
        jButton5.setText("Xem Chi Tiết Mượn Trả");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(51, 204, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16a.png"))); // NOI18N
        jButton6.setText("In Ra File");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnTimKiemTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTimKiemTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnTimKiemKetQua, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(pnTimKiemTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiemTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnTimKiemKetQua, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Tìm Kiếm Thông Tin Mượn Sách", jPanel4);

        jPanel2.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("@Author Nguyễn Hoàng Giang");

        jLabel11.setText("MSSV: 20151094");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 102, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(261, 261, 261)
                        .addComponent(jLabel11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(129, 129, 129)
                        .addComponent(jLabel9)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel11)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String maSach = txtTimKiemMaSach.getText();
        String tenSach = txtTimKiemTenSach.getText();
        String tacGia = txtTimKiemTacGia.getText();
        String nhaXuatBan = txtTimKiemNhaXuatBan.getText();
        String theLoai = txtTimKiemTheLoai.getText();
        if (maSach.equals("") && tenSach.equals("") && tacGia.equals("") && nhaXuatBan.equals("") && theLoai.equals("") && cbxTimKiemTinhTrang.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
            return;
        } else {
            if (cbxTimKiemTinhTrang.getSelectedIndex() == -1) {
                String tinhTrang = "";
                ArrayList<Sach> ds = bllSach.timKiemSach("%" + maSach + "%", "%" + tenSach + "%", "%" + tacGia + "%", "%" + nhaXuatBan + "%", "%" + theLoai + "%", "%" + tinhTrang + "%");
                dtmTimKiemSach = addDuLieuChotblTimKiem(ds);

                tblTimKiemKetQua.setModel(dtmTimKiemSach);
            } else {
                String tinhTrang = cbxTimKiemTinhTrang.getSelectedItem().toString();
                ArrayList<Sach> ds = bllSach.timKiemSach("%" + maSach + "%", "%" + tenSach + "%", "%" + tacGia + "%", "%" + nhaXuatBan + "%", "%" + theLoai + "%", "%" + tinhTrang + "%");
                dtmTimKiemSach = addDuLieuChotblTimKiem(ds);
                tblTimKiemKetQua.setModel(dtmTimKiemSach);
            }
            btnLastSach = btnTimKiem;
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private DefaultTableModel addDuLieuChotblTimKiem(ArrayList<Sach> ds) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã Sách");
        dtm.addColumn("Tên Sách");
        dtm.addColumn("Tác Giả");
        dtm.addColumn("Nhà Xuất Bản");
        dtm.addColumn("Thể Loại");
        dtm.addColumn("Tình Trạng");
        for (Sach s : ds) {
            Vector<String> v = new Vector<>();
            v.add(s.getMaSach_20151094());
            v.add(s.getTenSach_20151094());
            v.add(s.getTacGia_20151094());
            v.add(s.getNhaXuatBan_20151094());
            v.add(s.getTheLoai_20151094());
            v.add(s.getTinhTrang_20151094());
            dtm.addRow(v);
        }
        return dtm;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        txtTimKiemMaSach.setText("");
        txtTimKiemTenSach.setText("");
        txtTimKiemTacGia.setText("");
        txtTimKiemNhaXuatBan.setText("");
        txtTimKiemTheLoai.setText("");
        cbxTimKiemTinhTrang.setSelectedIndex(-1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        hienThiToanBoSachLenBangDanhSach();
        btnLastSach = jButton3;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnTimKiemInRaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemInRaFileActionPerformed
        // TODO add your handling code here:
        if (btnLastSach == btnTimKiem || btnLastSach == jButton3) {
            JOptionPane.showMessageDialog(null, "<html><p>Chuẩn bị in thông tin.</p>"
                    + "<p>Vui lòng chờ trong giây lát</p></html>");
            if (btnLastSach == btnTimKiem) {
                String maSach = txtTimKiemMaSach.getText();
                String tenSach = txtTimKiemTenSach.getText();
                String tacGia = txtTimKiemTacGia.getText();
                String nhaXuatBan = txtTimKiemNhaXuatBan.getText();
                String theLoai = txtTimKiemTheLoai.getText();
                String tinhTrang = "";
                if (cbxTimKiemTinhTrang.getSelectedIndex() != -1) {
                    tinhTrang = cbxTimKiemTinhTrang.getSelectedItem() + "";
                }
                String regex = "\\s*";
                if (maSach.matches(regex) && tenSach.matches(regex) && tacGia.matches(regex) && nhaXuatBan.matches(regex) && theLoai.matches(regex) && tinhTrang.matches(regex)) {
                    JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu để tìm kiếm.");
                    return;
                }
                printSach.printKetQuaTimKiemSach("%" + maSach + "%", "%" + tenSach + "%", "%" + tacGia + "%", "%" + nhaXuatBan + "%", "%" + theLoai + "%", "%" + tinhTrang + "%");
            } else {
                printSach.printToanBoSach();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn tìm kiếm trước");
        }
    }//GEN-LAST:event_btnTimKiemInRaFileActionPerformed

    private void cbxTimKiemMaNhanVienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTimKiemMaNhanVienItemStateChanged
        if (cbxTimKiemMaNhanVien.getSelectedIndex() != -1) {
            NhanVien nv = (NhanVien) cbxTimKiemMaNhanVien.getSelectedItem();
            cbxTimKiemTenNhanVien.setSelectedIndex(cbxTimKiemMaNhanVien.getSelectedIndex());
        }
    }//GEN-LAST:event_cbxTimKiemMaNhanVienItemStateChanged

    private void cbxTimKiemMaDocGiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTimKiemMaDocGiaItemStateChanged
        // TODO add your handling code here:
        if (cbxTimKiemMaDocGia.getSelectedIndex() != -1) {
            cbxTimKiemTenDocGia.setSelectedIndex(cbxTimKiemMaDocGia.getSelectedIndex());
        }
    }//GEN-LAST:event_cbxTimKiemMaDocGiaItemStateChanged

    private void cbxTimKiemTenDocGiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTimKiemTenDocGiaItemStateChanged
        // TODO add your handling code here:
        if (cbxTimKiemTenDocGia.getSelectedIndex() != -1 && (cbxTimKiemTenDocGia.getSelectedIndex() != cbxTimKiemMaDocGia.getSelectedIndex())) {
            cbxTimKiemMaDocGia.setSelectedIndex(cbxTimKiemTenDocGia.getSelectedIndex());
        }
    }//GEN-LAST:event_cbxTimKiemTenDocGiaItemStateChanged

    private void cbxTimKiemTenNhanVienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTimKiemTenNhanVienItemStateChanged
        // TODO add your handling code here:
        if (cbxTimKiemTenNhanVien.getSelectedIndex() != -1 && (cbxTimKiemTenNhanVien.getSelectedIndex() != cbxTimKiemMaNhanVien.getSelectedIndex())) {
            cbxTimKiemMaNhanVien.setSelectedIndex(cbxTimKiemTenNhanVien.getSelectedIndex());
        }
    }//GEN-LAST:event_cbxTimKiemTenNhanVienItemStateChanged

    private void btnTimKiemTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemTimKiemActionPerformed
        // TODO add your handling code here:
        try {
            String maMuonTra = txtTimKiemMaMuonTra.getText();
            String maDocGia = "";
            if (cbxTimKiemMaDocGia.getSelectedIndex() != -1) {
                maDocGia = cbxTimKiemMaDocGia.getSelectedItem().toString();
            }
            String maNhanVien = "";
            if (cbxTimKiemMaNhanVien.getSelectedIndex() != -1) {
                maNhanVien = cbxTimKiemMaNhanVien.getSelectedItem().toString();
            }
            Date ngayMuon = jDateChooserNgayMuon.getDate();
            String strNgayMuon = "";
            if (ngayMuon != null) {
                strNgayMuon = sdf.format(ngayMuon);
            }
            Date ngayHenTra = jDateChooserNgayHenTra.getDate();
            String strNgayHenTra = "";
            if (ngayHenTra != null) {
                strNgayHenTra = sdf.format(ngayHenTra);
            }
            String tienCoc = txtTimKiemTienCoc.getText();
            if (kiemTraDuLieuNhap()) {
                dtmTimKiem = hienThiDuLieuTuArrayListLenBan(bllMuonTra.timKiemMuonTra(maMuonTra, maDocGia, maNhanVien, strNgayMuon, strNgayHenTra, tienCoc));
                tblTimKiemKetQua1.setModel(dtmTimKiem);
                btnLastMuonTra = btnTimKiemTimKiem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }//GEN-LAST:event_btnTimKiemTimKiemActionPerformed

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

    private boolean kiemTraDuLieuNhap() {
        String maDocGia = "";
        if (cbxTimKiemMaDocGia.getSelectedIndex() != -1) {
            maDocGia = cbxTimKiemMaDocGia.getSelectedItem().toString();
        }
        String maNhanVien = "";
        if (cbxTimKiemMaNhanVien.getSelectedIndex() != -1) {
            maNhanVien = cbxTimKiemMaNhanVien.getSelectedItem().toString();
        }
        Date ngayMuon = jDateChooserNgayMuon.getDate();
        String strNgayMuon = "";
        if (ngayMuon != null) {
            strNgayMuon = sdf.format(ngayMuon);
        }
        Date ngayHenTra = jDateChooserNgayHenTra.getDate();
        String strNgayHenTra = "";
        if (ngayHenTra != null) {
            strNgayHenTra = sdf.format(ngayHenTra);
        }
        String kiemTra = KiemTraDuLieuNhapThoaMan.kiemTraDuLieuNhapTimKiemMuonTra(txtTimKiemMaMuonTra.getText(), maDocGia, maNhanVien, strNgayMuon, strNgayHenTra, txtTimKiemTienCoc.getText());
        if (kiemTra.equals("Chưa nhập dữ liệu")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
            return false;
        }
        if (kiemTra.equals("Nhập sai định dạng ngày")) {
            JOptionPane.showMessageDialog(null, "Nhập sai định dạng ngày");
            return false;
        }
        if (kiemTra.equals("Nhập sai định dạng ngày của ngày hẹn trả")) {
            JOptionPane.showMessageDialog(null, "Nhập sai định dạng ngày của ngày hẹn trả");
            return false;
        }
        if (kiemTra.equals("Nhập sai định dạng tiền cọc")) {
            JOptionPane.showMessageDialog(null, "Nhập sai định dạng tiền cọc");
            return false;
        }
        return true;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cbxTimKiemMaDocGia.setSelectedIndex(-1);
        txtTimKiemMaMuonTra.setText("");
        cbxTimKiemMaNhanVien.setSelectedIndex(-1);
        jDateChooserNgayMuon.setDate(null);
        jDateChooserNgayHenTra.setDate(null);
        cbxTimKiemMaDocGia.setSelectedIndex(-1);
        cbxTimKiemMaNhanVien.setSelectedIndex(-1);
        txtTimKiemTienCoc.setText("");
        cbxTimKiemTenDocGia.setSelectedIndex(-1);
        cbxTimKiemTenNhanVien.setSelectedIndex(-1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        hienThiToanBoMuonTra();
        btnLastMuonTra = jButton4;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void hienThiToanBoMuonTra() {
        try {
            dtmToanBoMuonTra = hienThiDuLieuTuArrayListLenBan(bllMuonTra.layToanBoMuonTra());
            tblTimKiemKetQua1.setModel(dtmToanBoMuonTra);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int row = tblTimKiemKetQua1.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Hãy chọn một mượn trả trên bảng danh sách");
        } else {
            XemChiTietMuonSachChoKhach ui = new XemChiTietMuonSachChoKhach();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tblTimKiemKetQua1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimKiemKetQua1MousePressed
        // TODO add your handling code here:
        DocGia dg = new DocGia();
        MuonTra mt = new MuonTra();
        int row = tblTimKiemKetQua1.getSelectedRow();
        txtTimKiemMaMuonTra.setText((tblTimKiemKetQua1.getValueAt(row, 0) + "").trim());
        String maDocGia = (tblTimKiemKetQua1.getValueAt(row, 1) + "");
        ArrayList<DocGia> dsDocGia = bllDocGia.layDanhSachDocGia();
        for (int i = 0; i < dsDocGia.size(); i++) {
            if (maDocGia.equalsIgnoreCase(dsDocGia.get(i).getMaDocGia())) {
                cbxTimKiemMaDocGia.setSelectedIndex(i);
                cbxTimKiemTenDocGia.setSelectedIndex(i);
                break;
            }
        }
        String maNhanVien = (tblTimKiemKetQua1.getValueAt(row, 2) + "");
        ArrayList<NhanVien> dsNhanVien = bllNhanVien.layToanBoNhanVien();
        for (int i = 0; i < dsNhanVien.size(); i++) {
            if (maNhanVien.equalsIgnoreCase(dsNhanVien.get(i).getMaNhanVien())) {
                cbxTimKiemMaNhanVien.setSelectedIndex(i);
                cbxTimKiemTenNhanVien.setSelectedIndex(i);
                break;
            }
        }
        String ngayMuon = (tblTimKiemKetQua1.getValueAt(row, 3) + "").trim();
        String ngayHenTra = (tblTimKiemKetQua1.getValueAt(row, 4) + "").trim();
        try {
            jDateChooserNgayMuon.setDate(sdf.parse(ngayMuon));
            jDateChooserNgayHenTra.setDate(sdf.parse(ngayHenTra));
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtTimKiemTienCoc.setText((tblTimKiemKetQua1.getValueAt(row, 5) + "").trim());
        mt.setMaMuonTra((tblTimKiemKetQua1.getValueAt(row, 0) + "").trim());
        mt.setMaDocGia(tblTimKiemKetQua1.getValueAt(row, 1) + "");
        mt.setMaNhanVien(tblTimKiemKetQua1.getValueAt(row, 2) + "");
        mt.setNgayMuon(jDateChooserNgayMuon.getDate());
        mt.setNgayHenTra(jDateChooserNgayHenTra.getDate());
        mt.setTienCoc(Integer.parseInt(tblTimKiemKetQua1.getValueAt(row, 5) + ""));
        ArrayList<DocGia> ds = bllDocGia.timKiemDocGia(tblTimKiemKetQua1.getValueAt(row, 1) + "", "", "", "", "", "");
        dg = ds.get(0);
        System.out.println(mt.getMaMuonTra() + " " + mt.getMaDocGia() + " " + mt.getMaNhanVien() + " " + mt.getNgayMuon() + " " + mt.getNgayHenTra() + " " + mt.getTienCoc());
        System.out.println(dg.getMaDocGia().trim() + " " + dg.getTenDocGia().trim() + " " + dg.getGioiTinh().trim() + " " + dg.getPhone().trim() + " " + dg.getEmail().trim() + " " + dg.getDiaChi().trim());
        mtChon = mt;
        dgChon = dg;
        NhanVien nv = new NhanVien();
        ArrayList<NhanVien> dsnv = bllNhanVien.timKiemNhanVien(maNhanVien, "", "", "", "", "");
        nvChon = dsnv.get(0);
    }//GEN-LAST:event_tblTimKiemKetQua1MousePressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
        if (btnLastMuonTra == btnTimKiemTimKiem || btnLastMuonTra == jButton4) {
            JOptionPane.showMessageDialog(null, "<html><p>Chuẩn bị in thông tin.</p>"
                    + "<p>Vui lòng đợi trong giây lát</p></html>");
            if (btnLastMuonTra == btnTimKiemTimKiem) {
                String maMuonTra = txtTimKiemMaMuonTra.getText();
                String maDocGia = "";
                if(cbxTimKiemMaDocGia.getSelectedIndex()!=-1){
                    maDocGia = cbxTimKiemMaDocGia.getSelectedItem()+"";
                }
                
                String maNhanVien = "";
                if(cbxTimKiemMaNhanVien.getSelectedIndex()!=-1){
                    maNhanVien = cbxTimKiemMaNhanVien.getSelectedItem()+"";
                }
                Date ngayMuon = jDateChooserNgayMuon.getDate();
                String strNgayMuon = "";
                if (ngayMuon != null) {
                    strNgayMuon = sdfSQL.format(ngayMuon);
                }
                Date ngayHenTra = jDateChooserNgayHenTra.getDate();
                String strNgayHenTra = "";
                if (ngayHenTra != null) {
                    strNgayHenTra = sdfSQL.format(ngayHenTra);
                }
                String tienCoc = txtTimKiemTienCoc.getText();
                String regex = "\\s*";
                if (maMuonTra.matches(regex) && maDocGia.matches(regex) && maNhanVien.matches(regex) && strNgayHenTra.matches(regex) && tienCoc.matches(regex) && strNgayMuon.matches(regex)) {
                    JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu tìm kiếm");
                    return;
                }
                PrintMuonTra.printKetQuaTimKiem("%" + maMuonTra + "%", "%" + maDocGia + "%", "%" + maNhanVien + "%", "%" + strNgayMuon + "%", "%" + tienCoc + "%", "%" + strNgayHenTra + "%");
            } else {
                PrintMuonTra.printDanhSachMuonTra();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy tìm kiếm trước");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tblTimKiemKetQuaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimKiemKetQuaMouseReleased
        // TODO add your handling code here:
        int row = tblTimKiemKetQua.getSelectedRow();
        String maSach = tblTimKiemKetQua.getValueAt(row, 0)+"";
        String tenSach =tblTimKiemKetQua.getValueAt(row, 1)+"";
        String tacGia=tblTimKiemKetQua.getValueAt(row, 2)+"";
        String nhaXuatBan=tblTimKiemKetQua.getValueAt(row, 3)+"";
        String theLoai=tblTimKiemKetQua.getValueAt(row, 4)+"";
        String tinhTrang=tblTimKiemKetQua.getValueAt(row, 5)+"";
        txtTimKiemMaSach.setText(maSach);
        txtTimKiemTenSach.setText(tenSach);
        txtTimKiemTacGia.setText(tacGia);
        txtTimKiemNhaXuatBan.setText(nhaXuatBan);
        txtTimKiemTheLoai.setText(theLoai);
        if(tinhTrang.equalsIgnoreCase("Còn sách")){
            cbxTimKiemTinhTrang.setSelectedIndex(0);
        }
        else if(tinhTrang.equalsIgnoreCase("Đã cho mượn")){
            cbxTimKiemTinhTrang.setSelectedIndex(1);
        }
        else if(tinhTrang.equalsIgnoreCase("Không còn")){
            cbxTimKiemTinhTrang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_tblTimKiemKetQuaMouseReleased

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
            java.util.logging.Logger.getLogger(XemThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XemThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XemThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XemThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XemThongTin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemInRaFile;
    private javax.swing.JButton btnTimKiemTimKiem;
    private javax.swing.JComboBox<String> cbxTimKiemMaDocGia;
    private javax.swing.JComboBox<String> cbxTimKiemMaNhanVien;
    private javax.swing.JComboBox<String> cbxTimKiemTenDocGia;
    private javax.swing.JComboBox<String> cbxTimKiemTenNhanVien;
    private javax.swing.JComboBox<String> cbxTimKiemTinhTrang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooserNgayHenTra;
    private com.toedter.calendar.JDateChooser jDateChooserNgayMuon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnTimKiemKetQua;
    private javax.swing.JPanel pnTimKiemTimKiem;
    private javax.swing.JScrollPane scTimKiemKetQua;
    private javax.swing.JTable tblTimKiemKetQua;
    private javax.swing.JTable tblTimKiemKetQua1;
    private javax.swing.JTextField txtTimKiemMaMuonTra;
    private javax.swing.JTextField txtTimKiemMaSach;
    private javax.swing.JTextField txtTimKiemNhaXuatBan;
    private javax.swing.JTextField txtTimKiemTacGia;
    private javax.swing.JTextField txtTimKiemTenSach;
    private javax.swing.JTextField txtTimKiemTheLoai;
    private javax.swing.JTextField txtTimKiemTienCoc;
    // End of variables declaration//GEN-END:variables
}
