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
import BLL.KiemTraDuLieuNhapThoaMan;
import BLL.TaoMaTiepTheo;
import DAL.PrintMuonTra;
import DTO.DocGia;
import DTO.MuonTra;
import DTO.NhanVien;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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
public class QuanLyMuonTra extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyMuonTra
     */
    DefaultTableModel dtmToanBoMuonTra;
    Connection con;
    DefaultTableModel dtmMau, dtmTimKiem, dtmCapNhat;
    DefaultTableModel dtmTimKiemChiTietMuonTra, dtmToanBoChiTietMuonTra, dtmCapNhatChiTietMuonTra;
    DefaultTableModel dtmMauChiTietMuonTra;
    JButton btnMuonTra, btnChiTietMuonTra;
    BLLMuonTra bllMuonTra;
    BLLChiTietMuonTra bllChiTiet;
    BLLSach bllSach;
    BLLNhanVien bllNhanVien;
    BLLDocGia bllDocGia;
    TaoMaTiepTheo taoMa;
    PrintMuonTra inMuonTra;
    KiemTraDuLieuNhapThoaMan kiemTraDuLieu;
    SimpleDateFormat sdf;
    static MuonTra mtChon;
    static DocGia dgChon;
    static NhanVien nvChon;
    JButton btnLast;
    SimpleDateFormat sdfSQL;

    public QuanLyMuonTra() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
        }
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        bllMuonTra = new BLLMuonTra();
        taoMa = new TaoMaTiepTheo();
        inMuonTra = new PrintMuonTra();
        kiemTraDuLieu = new KiemTraDuLieuNhapThoaMan();
        bllChiTiet = new BLLChiTietMuonTra();
        bllSach = new BLLSach();
        bllNhanVien = new BLLNhanVien();
        bllDocGia = new BLLDocGia();

        dtmMau = new DefaultTableModel();
        dtmMau.addColumn("Mã Mượn Trả");
        dtmMau.addColumn("Mã Độc Giả");
        dtmMau.addColumn("Mã Nhân Viên");
        dtmMau.addColumn("Ngày Mượn");
        dtmMau.addColumn("Ngày Hẹn Trả");
        dtmMau.addColumn("Tiền Cọc");
        tblTimKiemKetQua.setModel(dtmMau);
        hienThiToanBoMuonTra();
        hienThiMaDocGiaLenCombobox();
        hienThiMaDocGiaLenComBoxTimKiemMaDocGia();
        hienThiMaNhanVienLenComBoxTimKiemMaNhanVien();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
        autoDoiMauChu(jLabel1, Color.BLACK, Color.RED);
        jLabel10.setForeground(Color.BLUE);
        autoDoiMauChu(jLabel10, Color.BLUE, Color.GREEN);
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss aa");
        Color mauChu = jLabel12.getForeground();
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                jLabel12.setText(sdf1.format(cal.getTime()));
                if (jLabel12.getForeground() == mauChu) {
                    jLabel12.setForeground(Color.BLACK);
                } else {
                    jLabel12.setForeground(mauChu);
                }
            }
        });
        t.start();
        Calendar cal = Calendar.getInstance();
        jLabel12.setText(sdf1.format(cal.getTime()));
        scTimKiemKetQua.setOpaque(false);
        scTimKiemKetQua.getViewport().setOpaque(false);
        scTimKiemKetQua.setOpaque(false);
        scTimKiemKetQua.getViewport().setOpaque(false);
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

    private void hienThiToanBoMuonTra() {
        try {
            dtmToanBoMuonTra = hienThiDuLieuTuArrayListLenBan(bllMuonTra.layToanBoMuonTra());
            tblTimKiemKetQua.setModel(dtmToanBoMuonTra);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pnTimKiemTimKiem = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiemMaMuonTra = new javax.swing.JTextField();
        txtTimKiemTienCoc = new javax.swing.JTextField();
        cbxTimKiemMaNhanVien = new javax.swing.JComboBox<>();
        cbxTimKiemMaDocGia = new javax.swing.JComboBox<>();
        cbxTimKiemTenDocGia = new javax.swing.JComboBox<>();
        cbxTimKiemTenNhanVien = new javax.swing.JComboBox<>();
        jDateChooserNgayMuon = new com.toedter.calendar.JDateChooser();
        jDateChooserNgayHenTra = new com.toedter.calendar.JDateChooser();
        btnTimKiemTimKiem = new javax.swing.JButton();
        btnCapNhatThemMuonTra = new javax.swing.JButton();
        btnCapNhatSuaMuonTra = new javax.swing.JButton();
        btnCapNhatXoaMuonTra = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnTimKiemInRaFile = new javax.swing.JButton();
        pnTimKiemKetQua = new javax.swing.JPanel();
        scTimKiemKetQua = new javax.swing.JScrollPane();
        tblTimKiemKetQua = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1250, 700));

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/open-book1.png"))); // NOI18N
        jLabel1.setText("QUẢN LÝ MƯỢN TRẢ");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("@Author Nguyễn Hoàng Giang");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("MSSV: 20151094");

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 102, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("jLabel12");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));

        pnTimKiemTimKiem.setBackground(new java.awt.Color(51, 204, 255));
        pnTimKiemTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Mượn Trả:"));

        jLabel2.setText("Mã Mượn Trả:");

        jLabel3.setText("Mã Độc Giả:");

        jLabel4.setText("Tên Độc Giả:");

        jLabel5.setText("Mã Nhân Viên:");

        jLabel6.setText("Tên Nhân Viên:");

        jLabel7.setText("Ngày Mượn:");

        jLabel8.setText("Ngày Hẹn Trả:");

        jLabel9.setText("Tiền Cọc:");

        txtTimKiemTienCoc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        cbxTimKiemMaNhanVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTimKiemMaNhanVienItemStateChanged(evt);
            }
        });

        cbxTimKiemMaDocGia.setBackground(new java.awt.Color(51, 204, 255));
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
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
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
                    .addComponent(jLabel2)
                    .addComponent(txtTimKiemMaMuonTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxTimKiemMaDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxTimKiemTenDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbxTimKiemMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxTimKiemTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jDateChooserNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jDateChooserNgayHenTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnTimKiemTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTimKiemTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        btnTimKiemTimKiem.setBackground(new java.awt.Color(51, 204, 255));
        btnTimKiemTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Search-16.png"))); // NOI18N
        btnTimKiemTimKiem.setText("Tìm Kiếm");
        btnTimKiemTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTimKiemActionPerformed(evt);
            }
        });

        btnCapNhatThemMuonTra.setBackground(new java.awt.Color(51, 204, 255));
        btnCapNhatThemMuonTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Add-16.png"))); // NOI18N
        btnCapNhatThemMuonTra.setText("Thêm Mượn Trả");
        btnCapNhatThemMuonTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatThemMuonTraActionPerformed(evt);
            }
        });

        btnCapNhatSuaMuonTra.setBackground(new java.awt.Color(51, 204, 255));
        btnCapNhatSuaMuonTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Edit-16a.png"))); // NOI18N
        btnCapNhatSuaMuonTra.setText("Sửa Mượn Trả");
        btnCapNhatSuaMuonTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSuaMuonTraActionPerformed(evt);
            }
        });

        btnCapNhatXoaMuonTra.setBackground(new java.awt.Color(51, 204, 255));
        btnCapNhatXoaMuonTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Delete-16a.png"))); // NOI18N
        btnCapNhatXoaMuonTra.setText("Xóa Mượn Trả");
        btnCapNhatXoaMuonTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatXoaMuonTraActionPerformed(evt);
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

        jButton2.setBackground(new java.awt.Color(51, 204, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-select-all.png"))); // NOI18N
        jButton2.setText("Xem Toàn Bộ Mượn Trả");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 204, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/open-book.png"))); // NOI18N
        jButton3.setText("Xem Chi Tiết Mượn Trả");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(51, 204, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/credit-card.png"))); // NOI18N
        jButton4.setText("Trả Sách");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
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

        pnTimKiemKetQua.setBackground(new java.awt.Color(51, 204, 255));
        pnTimKiemKetQua.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Mượn Trả:"));
        pnTimKiemKetQua.setLayout(new java.awt.BorderLayout());

        scTimKiemKetQua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                scTimKiemKetQuaMousePressed(evt);
            }
        });

        tblTimKiemKetQua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTimKiemKetQuaMousePressed(evt);
            }
        });
        scTimKiemKetQua.setViewportView(tblTimKiemKetQua);

        pnTimKiemKetQua.add(scTimKiemKetQua, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnTimKiemTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTimKiemTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCapNhatThemMuonTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCapNhatSuaMuonTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCapNhatXoaMuonTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTimKiemInRaFile, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnTimKiemKetQua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(pnTimKiemTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiemTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCapNhatThemMuonTra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCapNhatSuaMuonTra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCapNhatXoaMuonTra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiemInRaFile)
                .addGap(0, 21, Short.MAX_VALUE))
            .addComponent(pnTimKiemKetQua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                System.out.println("Mã dg:" + maDocGia);
            }
            String maNhanVien = "";
            if (cbxTimKiemMaNhanVien.getSelectedIndex() != -1) {
                maNhanVien = cbxTimKiemMaNhanVien.getSelectedItem().toString();
                System.out.println("Mã nhân viên:" + maNhanVien);
            }
            Date ngayMuon = jDateChooserNgayMuon.getDate();
            String strNgayMuon = "";
            if (ngayMuon != null) {
                strNgayMuon = sdf.format(ngayMuon);
            }
            System.out.println(strNgayMuon);
            Date ngayHenTra = jDateChooserNgayHenTra.getDate();
            String strNgayHenTra = "";
            if (ngayHenTra != null) {
                strNgayHenTra = sdf.format(ngayHenTra);
            }
            System.out.println(strNgayHenTra);
            String tienCoc = txtTimKiemTienCoc.getText();
            System.out.println(tienCoc);
            if (kiemTraDuLieuNhap()) {
                dtmTimKiem = hienThiDuLieuTuArrayListLenBan(bllMuonTra.timKiemMuonTra(maMuonTra, maDocGia, maNhanVien, strNgayMuon, strNgayHenTra, tienCoc));
                tblTimKiemKetQua.setModel(dtmTimKiem);
                btnLast = btnTimKiemTimKiem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }//GEN-LAST:event_btnTimKiemTimKiemActionPerformed

    private void btnCapNhatThemMuonTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatThemMuonTraActionPerformed
        // TODO add your handling code here:
        String maMuonTra = txtTimKiemMaMuonTra.getText();
        String maDocGia = cbxTimKiemMaDocGia.getSelectedIndex() != -1 ? cbxTimKiemMaDocGia.getSelectedItem() + "" : "";
        String maNhanVien = cbxTimKiemMaNhanVien.getSelectedIndex() != -1 ? cbxTimKiemMaNhanVien.getSelectedItem() + "" : "";
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
        String kiemTra = KiemTraDuLieuNhapThoaMan.kiemTraDuLieuNhapCapNhatMuonTra(maMuonTra, maDocGia, maNhanVien, strNgayMuon, strNgayHenTra, tienCoc);
        if (kiemTra.equals("Chưa nhập dữ liệu")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
            return;
        }
        if (kiemTra.equals("Chưa nhập mã mượn trả")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã mượn trả");
            return;
        }
        if (kiemTra.equals("Chưa nhập mã độc giả")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã độc giả");
            return;
        }
        if (kiemTra.equals("Chưa nhập mã nhân viên")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã nhân viên");
            return;
        }
        if (kiemTra.equals("Chưa nhập ngày mượn")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập ngày mượn");
            return;
        }
        if (kiemTra.equals("Nhập sai định dạng ngày của ngày mượn. (Định dạng ngày: dd/MM/yyyy)")) {
            JOptionPane.showMessageDialog(null, "Nhập sai định dạng ngày của ngày mượn. (Định dạng ngày: dd/MM/yyyy)");
            return;
        }
        if (kiemTra.equals("Chưa nhập ngày hẹn trả")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập ngày hẹn trả");
            return;
        }
        if (kiemTra.equals("Nhập sai định dạng ngày của ngày hẹn trả.(Định dạng ngày: dd/MM/yyyy)")) {
            JOptionPane.showMessageDialog(null, "Nhập sai định dạng ngày của ngày hẹn trả.(Định dạng ngày: dd/MM/yyyy)");
            return;
        }
        if (kiemTra.equals("Chưa nhập tiền đặt cọc")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập tiền đặt cọc");
            return;
        }
        if (kiemTra.equals("Nhập sai tiền đặt cọc. (Tiền đặt cọc phải là số)")) {
            JOptionPane.showMessageDialog(null, "Nhập sai tiền đặt cọc. (Tiền đặt cọc phải là số)");
            return;
        }
        if (kiemTra.equals("Dữ liệu thỏa mãn")) {
            if (!bllDocGia.kiemTraiTonTaiMaDocGia(maDocGia)) {
                JOptionPane.showMessageDialog(null, "Không tồn tại mã độc giả này");
                return;
            } else if (!bllNhanVien.kiemTraTontaiMaNhanVien(maNhanVien)) {
                JOptionPane.showMessageDialog(null, "Không tồn tại mã nhân viên này");
                return;
            } else {
                int ktthem = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm", "Hỏi", JOptionPane.YES_NO_OPTION);
                if (ktthem != JOptionPane.YES_OPTION) {
                    return;
                }
                try {
                    int kq = bllMuonTra.themMuonTra(maMuonTra, maDocGia, maNhanVien, sdf.parse(strNgayMuon), sdf.parse(strNgayHenTra), Integer.parseInt(tienCoc));
                    if (kq > 0) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công");
                        jButton1.doClick();

                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thất bại");
                    }
                    hienThiToanBoMuonTra();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnCapNhatThemMuonTraActionPerformed

    private void btnCapNhatSuaMuonTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSuaMuonTraActionPerformed
        // TODO add your handling code here:
        int row = tblTimKiemKetQua.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng");
            return;
        }
        int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa không ?", "Hỏi Sửa", JOptionPane.YES_NO_OPTION);
        if (kt == JOptionPane.YES_OPTION) {
            String maMuonTra = txtTimKiemMaMuonTra.getText().trim();
            String maDocGia = cbxTimKiemMaDocGia.getSelectedIndex() != -1 ? cbxTimKiemMaDocGia.getSelectedItem() + "" : "";
            String maNhanVien = cbxTimKiemMaNhanVien.getSelectedIndex() != -1 ? cbxTimKiemMaNhanVien.getSelectedItem() + "" : "";
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
            String kiemTra = KiemTraDuLieuNhapThoaMan.kiemTraDuLieuNhapSua(maDocGia, maNhanVien, strNgayMuon, strNgayHenTra, tienCoc);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyy");
            if (kiemTra.equals("Chưa nhập dữ liệu")) {
                JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
                return;
            }
            if (kiemTra.equals("Chưa nhập mã độc giả")) {
                JOptionPane.showMessageDialog(null, "Chưa nhập mã độc giả");
                return;
            }
            if (kiemTra.equals("Chưa nhập mã nhân viên")) {
                JOptionPane.showMessageDialog(null, "Chưa nhập mã nhân viên");
                return;
            }
            if (kiemTra.equals("Chưa nhập ngày mượn")) {
                JOptionPane.showMessageDialog(null, "Chưa nhập ngày mượn");
                return;
            }
            if (kiemTra.equals("Nhập sai định dạng ngày của ngày mượn. (Định dạng ngày: dd/MM/yyyy)")) {
                JOptionPane.showMessageDialog(null, "Nhập sai định dạng ngày của ngày mượn. (Định dạng ngày: dd/MM/yyyy)");
                return;
            }
            if (kiemTra.equals("Chưa nhập ngày hẹn trả")) {
                JOptionPane.showMessageDialog(null, "Chưa nhập ngày hẹn trả");
                return;
            }
            if (kiemTra.equals("Nhập sai định dạng ngày của ngày hẹn trả.(Định dạng ngày: dd/MM/yyyy)")) {
                JOptionPane.showMessageDialog(null, "Nhập sai định dạng ngày của ngày hẹn trả.(Định dạng ngày: dd/MM/yyyy)");
                return;
            }
            if (kiemTra.equals("Chưa nhập tiền đặt cọc")) {
                JOptionPane.showMessageDialog(null, "Chưa nhập tiền đặt cọc");
                return;
            }
            if (kiemTra.equals("Nhập sai tiền đặt cọc. (Tiền đặt cọc phải là số)")) {
                JOptionPane.showMessageDialog(null, "Nhập sai tiền đặt cọc. (Tiền đặt cọc phải là số)");
                return;
            }
            if (kiemTra.equals("Dữ liệu thỏa mãn")) {
                try {
                    int soLuong = bllMuonTra.suaMuonTra(maMuonTra, maDocGia, maNhanVien, sdf.parse(strNgayMuon), sdf.parse(strNgayHenTra), Integer.parseInt(tienCoc));
                    if (soLuong > 0) {
                        JOptionPane.showMessageDialog(null, "Sửa thành công");
                        hienThiToanBoMuonTra();
                        tblTimKiemKetQua.setModel(dtmToanBoMuonTra);
                        jButton1.doClick();
                    } else {
                        JOptionPane.showMessageDialog(null, "Sửa thất bại");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }//GEN-LAST:event_btnCapNhatSuaMuonTraActionPerformed

    private void btnCapNhatXoaMuonTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatXoaMuonTraActionPerformed
        // TODO add your handling code here:
        int[] row = tblTimKiemKetQua.getSelectedRows();
        if (row.length <= 0) {
            JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng");
            return;
        }
        int kq = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa ?", "Hỏi Xóa", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (kq == JOptionPane.YES_OPTION) {
            int soLuong = 0;
            for (int x : row) {
                String maMuonTra = tblTimKiemKetQua.getValueAt(x, 0) + "";
                soLuong += bllMuonTra.xoaMuonTra(maMuonTra.trim(), "", "", null, null, 0);
            }
            if (soLuong > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công " + soLuong + " mượn trả");
                jButton1.doClick();
            } else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại");
            }
            hienThiToanBoMuonTra();
            tblTimKiemKetQua.setModel(dtmToanBoMuonTra);
        }
        return;
    }//GEN-LAST:event_btnCapNhatXoaMuonTraActionPerformed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        hienThiToanBoMuonTra();
        btnLast = jButton2;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row = tblTimKiemKetQua.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Hãy chọn một mượn trả trên bảng danh sách");
        } else {
            XemChiTietMuonSach ui = new XemChiTietMuonSach();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int row = tblTimKiemKetQua.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Hãy Chọn một mượn trả từ bảng");
        } else {
            TraSach ui = new TraSach();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnTimKiemInRaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemInRaFileActionPerformed
        // TODO add your handling code here:
        if (btnLast == btnTimKiemTimKiem || btnLast == jButton2) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in kết quả.</p>"
                    + "<p>Vui lòng chờ trong giây lát.</p></html>");
            if (btnLast == btnTimKiemTimKiem) {
                String maMuonTra = txtTimKiemMaMuonTra.getText().trim();
                String maDocGia = "";
                if (cbxTimKiemMaDocGia.getSelectedIndex() != -1) {
                    DocGia dg = (DocGia) cbxTimKiemMaDocGia.getSelectedItem();
                    maDocGia = dg.getMaDocGia();
                    System.out.println(maDocGia);
                }
                String maNhanVien = "";
                if (cbxTimKiemMaNhanVien.getSelectedIndex() != -1) {
                    NhanVien nv = (NhanVien) cbxTimKiemMaNhanVien.getSelectedItem();
                    maNhanVien = nv.getMaNhanVien();
                    System.out.println(nv.getMaNhanVien());
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
    }//GEN-LAST:event_btnTimKiemInRaFileActionPerformed

    private void tblTimKiemKetQuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimKiemKetQuaMousePressed
        // TODO add your handling code here:
        DocGia dg = new DocGia();
        MuonTra mt = new MuonTra();
        int row = tblTimKiemKetQua.getSelectedRow();
        txtTimKiemMaMuonTra.setText((tblTimKiemKetQua.getValueAt(row, 0) + "").trim());
        String maDocGia = (tblTimKiemKetQua.getValueAt(row, 1) + "");
        ArrayList<DocGia> dsDocGia = bllDocGia.layDanhSachDocGia();
        for (int i = 0; i < dsDocGia.size(); i++) {
            if (maDocGia.equalsIgnoreCase(dsDocGia.get(i).getMaDocGia())) {
                cbxTimKiemMaDocGia.setSelectedIndex(i);
                cbxTimKiemTenDocGia.setSelectedIndex(i);
                break;
            }
        }
        String maNhanVien = (tblTimKiemKetQua.getValueAt(row, 2) + "");
        ArrayList<NhanVien> dsNhanVien = bllNhanVien.layToanBoNhanVien();
        for (int i = 0; i < dsNhanVien.size(); i++) {
            if (maNhanVien.equalsIgnoreCase(dsNhanVien.get(i).getMaNhanVien())) {
                cbxTimKiemMaNhanVien.setSelectedIndex(i);
                cbxTimKiemTenNhanVien.setSelectedIndex(i);
                break;
            }
        }
        String ngayMuon = (tblTimKiemKetQua.getValueAt(row, 3) + "").trim();
        String ngayHenTra = (tblTimKiemKetQua.getValueAt(row, 4) + "").trim();
        try {
            jDateChooserNgayMuon.setDate(sdf.parse(ngayMuon));
            jDateChooserNgayHenTra.setDate(sdf.parse(ngayHenTra));
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtTimKiemTienCoc.setText((tblTimKiemKetQua.getValueAt(row, 5) + "").trim());
        mt.setMaMuonTra((tblTimKiemKetQua.getValueAt(row, 0) + "").trim());
        mt.setMaDocGia(tblTimKiemKetQua.getValueAt(row, 1) + "");
        mt.setMaNhanVien(tblTimKiemKetQua.getValueAt(row, 2) + "");
        mt.setNgayMuon(jDateChooserNgayMuon.getDate());
        mt.setNgayHenTra(jDateChooserNgayHenTra.getDate());
        mt.setTienCoc(Integer.parseInt(tblTimKiemKetQua.getValueAt(row, 5) + ""));
        ArrayList<DocGia> ds = bllDocGia.timKiemDocGia(tblTimKiemKetQua.getValueAt(row, 1) + "", "", "", "", "", "");
        dg = ds.get(0);
        System.out.println(mt.getMaMuonTra() + " " + mt.getMaDocGia() + " " + mt.getMaNhanVien() + " " + mt.getNgayMuon() + " " + mt.getNgayHenTra() + " " + mt.getTienCoc());
        System.out.println(dg.getMaDocGia().trim() + " " + dg.getTenDocGia().trim() + " " + dg.getGioiTinh().trim() + " " + dg.getPhone().trim() + " " + dg.getEmail().trim() + " " + dg.getDiaChi().trim());
        mtChon = mt;
        dgChon = dg;
        NhanVien nv = new NhanVien();
        ArrayList<NhanVien> dsnv = bllNhanVien.timKiemNhanVien(maNhanVien, "", "", "", "", "");
        nvChon = dsnv.get(0);
    }//GEN-LAST:event_tblTimKiemKetQuaMousePressed

    private void scTimKiemKetQuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scTimKiemKetQuaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_scTimKiemKetQuaMousePressed

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
            java.util.logging.Logger.getLogger(QuanLyMuonTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyMuonTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyMuonTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyMuonTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyMuonTra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatSuaMuonTra;
    private javax.swing.JButton btnCapNhatThemMuonTra;
    private javax.swing.JButton btnCapNhatXoaMuonTra;
    private javax.swing.JButton btnTimKiemInRaFile;
    private javax.swing.JButton btnTimKiemTimKiem;
    private javax.swing.JComboBox<String> cbxTimKiemMaDocGia;
    private javax.swing.JComboBox<String> cbxTimKiemMaNhanVien;
    private javax.swing.JComboBox<String> cbxTimKiemTenDocGia;
    private javax.swing.JComboBox<String> cbxTimKiemTenNhanVien;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooserNgayHenTra;
    private com.toedter.calendar.JDateChooser jDateChooserNgayMuon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pnTimKiemKetQua;
    private javax.swing.JPanel pnTimKiemTimKiem;
    private javax.swing.JScrollPane scTimKiemKetQua;
    private javax.swing.JTable tblTimKiemKetQua;
    private javax.swing.JTextField txtTimKiemMaMuonTra;
    private javax.swing.JTextField txtTimKiemTienCoc;
    // End of variables declaration//GEN-END:variables
}
