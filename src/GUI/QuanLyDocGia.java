/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLLDocGia;
import BLL.KiemTraDuLieuNhapThoaMan;
import DAL.ImportFileExcel;
import DAL.PrintDocGia;
import DTO.DocGia;
import static GUI.QuanLyDocGia.dgDangKy;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class QuanLyDocGia extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyDocGia
     */
    DefaultTableModel dtmMau,

    /**
     * Creates new form QuanLyDocGia
     */
    dtmTimKiem, 

    /**
     * Creates new form QuanLyDocGia
     */
    dtmCapNhat, 

    /**
     * Creates new form QuanLyDocGia
     */
    dtmToanBoDocGia;
    JButton btn, btnLast;
    BLLDocGia bll;
    PrintDocGia inDocGia;
    ImportFileExcel importFile;
    static DocGia dgDangKy;
    public QuanLyDocGia() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
        }
        initComponents();
        bll = new BLLDocGia();
        inDocGia = new PrintDocGia();
        importFile = new ImportFileExcel();
        dgDangKy = new DocGia();

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dtmMau = new DefaultTableModel();
        dtmMau.addColumn("Mã Độc Giả");
        dtmMau.addColumn("Tên Độc Giả");
        dtmMau.addColumn("Giới Tính");
        dtmMau.addColumn("Phone");
        dtmMau.addColumn("Email");
        dtmMau.addColumn("Địa Chỉ");
        tblTimKiemKetQua.setModel(dtmMau);
        hienThiDanhSachDocGia();
        resetDuLieuNhap();
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
        txtTimKiemMaDocGia.setText("");
        txtTimKiemTenDocGia.setText("");
        rdNam.setSelected(false);
        rdNu.setSelected(false);
        txtTimKiemPhone.setText("");
        txtTimKiemEmail.setText("");
        txtTimKiemDiaChi.setText("");
    }

    private void hienThiDanhSachDocGia() {
        try {
            dtmToanBoDocGia = hienThiDanhSach(bll.layDanhSachDocGia());
            tblTimKiemKetQua.setModel(dtmToanBoDocGia);
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
        txtTimKiemMaDocGia = new javax.swing.JTextField();
        txtTimKiemTenDocGia = new javax.swing.JTextField();
        txtTimKiemPhone = new javax.swing.JTextField();
        txtTimKiemEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTimKiemDiaChi = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        btnTimKiem = new javax.swing.JButton();
        btnThemDocGia = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnSuaDocGia = new javax.swing.JButton();
        btnXoaDocGia = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnTimKiemInRaFIle = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTimKiemKetQua = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1250, 659));

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/23-64.png"))); // NOI18N
        jLabel1.setText("QUẢN LÝ ĐỘC GIẢ");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("@Author Nguyễn Hoàng Giang");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("MSSV: 20151094");

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 153, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("jLabel10");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));

        jPanel10.setBackground(new java.awt.Color(51, 204, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Độc Giả:"));

        jLabel2.setText("Mã Độc Giả:");

        jLabel3.setText("Tên Độc Giả:");

        jLabel4.setText("Giới Tính:");

        jLabel5.setText("Phone:");

        jLabel6.setText("Email:");

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
                    .addComponent(txtTimKiemTenDocGia)
                    .addComponent(txtTimKiemPhone)
                    .addComponent(txtTimKiemEmail)
                    .addComponent(txtTimKiemDiaChi)
                    .addComponent(txtTimKiemMaDocGia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(rdNam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdNu)
                        .addGap(0, 136, Short.MAX_VALUE)))
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
                            .addComponent(txtTimKiemMaDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTimKiemTenDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(txtTimKiemEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        btnThemDocGia.setBackground(new java.awt.Color(51, 204, 255));
        btnThemDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Add-16.png"))); // NOI18N
        btnThemDocGia.setText("Thêm Độc Giả");
        btnThemDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDocGiaActionPerformed(evt);
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

        btnSuaDocGia.setBackground(new java.awt.Color(51, 204, 255));
        btnSuaDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Edit-16a.png"))); // NOI18N
        btnSuaDocGia.setText("Sửa Độc Giả");
        btnSuaDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDocGiaActionPerformed(evt);
            }
        });

        btnXoaDocGia.setBackground(new java.awt.Color(51, 204, 255));
        btnXoaDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Delete-16a.png"))); // NOI18N
        btnXoaDocGia.setText("Xóa Độc Giả");
        btnXoaDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDocGiaActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 204, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-select-all.png"))); // NOI18N
        jButton3.setText("Xem Toàn Bộ Độc Giả");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 204, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Broom-16.png"))); // NOI18N
        jButton2.setText("Làm Sạch Dữ Liệu Nhập");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnTimKiemInRaFIle.setBackground(new java.awt.Color(51, 204, 255));
        btnTimKiemInRaFIle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-Print-16a.png"))); // NOI18N
        btnTimKiemInRaFIle.setText("In Ra File");
        btnTimKiemInRaFIle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemInRaFIleActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(51, 204, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sign-up-16.png"))); // NOI18N
        jButton4.setText("Thêm Độc Giả Này Vào Mượn Sách");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(51, 204, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Kết Quả:"));
        jPanel11.setLayout(new java.awt.BorderLayout());

        tblTimKiemKetQua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTimKiemKetQuaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTimKiemKetQuaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblTimKiemKetQuaMouseReleased(evt);
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
                        .addGap(52, 52, 52)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTimKiemInRaFIle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSuaDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoaDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemDocGia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSuaDocGia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaDocGia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiemInRaFIle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        String maDocGia = txtTimKiemMaDocGia.getText();
        String tenDocGia = txtTimKiemTenDocGia.getText();
        String gioiTinh = rdNam.isSelected() == true ? "Nam" : rdNu.isSelected() == true ? "Nữ" : "";
        String phone = txtTimKiemPhone.getText();
        String email = txtTimKiemEmail.getText();
        String diaChi = txtTimKiemDiaChi.getText();
        if (maDocGia.equals("") && tenDocGia.equals("") && gioiTinh.equals("") && phone.equals("") && email.equals("") && diaChi.equals("")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
            return;
        } else if (!phone.matches("\\d+|\\s*")) {
            JOptionPane.showMessageDialog(null, "Nhập sai số điện thoại");
            return;
        } else {
            dtmTimKiem = hienThiDanhSach(bll.timKiemDocGia(maDocGia, tenDocGia, gioiTinh, phone, email, diaChi));
            tblTimKiemKetQua.setModel(dtmTimKiem);
            btnLast = btnTimKiem;
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnThemDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDocGiaActionPerformed
        // TODO add your handling code here:
        //cách 1:
        //        int stt = DataBaseQuery.laySoThuTuCuoiCuaBangDocGia(con) + 1;
        //        String maDocGia = "DG" + stt;
        //        String tenDocGia = txtCapNhatTenDocGia.getText();
        //        String gioiTinh = rdCapNhatNam.isSelected() == true ? "Nam" : rdCapNhatNu.isSelected() == true ? "Nữ" : "";
        //        String phone = txtCapNhatPhone.getText();
        //        String email = txtCapNhatEmail.getText();
        //        String diaChi = txtCapNhatDiaChi.getText();
        //        if (tenDocGia.equals("") || gioiTinh.equals("") || phone.equals("") || email.equals("") || diaChi.equals("")) {
            //            JOptionPane.showMessageDialog(null, "Chưa nhập đầy đủ dữ liệu");
            //            return;
            //        }
        //        if (gioiTinh.equals("")) {
            //            JOptionPane.showMessageDialog(null, "Chưa chọn giới tính");
            //            return;
            //        } else {
            //            DataBaseQuery.insertIntoDocGia(con, maDocGia, tenDocGia, gioiTinh, phone, email, diaChi);
            //            hienThiDanhSachDocGia();
            //            resetCapNhat();
            //        }
        //cách 2
        String maDocGia = txtTimKiemMaDocGia.getText();
        String tenDocGia = txtTimKiemTenDocGia.getText();
        String gioiTinh = rdNam.isSelected() == true ? "Nam" : rdNam.isSelected() == true ? "Nữ" : "";
        String phone = txtTimKiemPhone.getText();
        String email = txtTimKiemEmail.getText();
        String diaChi = txtTimKiemDiaChi.getText();
        String kiemTra = KiemTraDuLieuNhapThoaMan.kiemTraDuLieuNhapDocGia(maDocGia, tenDocGia, gioiTinh, phone, email, diaChi);
        if (kiemTra.equals("Chưa nhập dữ liệu")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
            return;
        }
        if (kiemTra.equals("Chưa nhập mã độc giả")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã độc giả");
            return;
        }
        if (kiemTra.equals("Chưa nhập tên độc giả")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên độc giả");
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
        if (kiemTra.equals(phone + " không phải số điện thoại")) {
            JOptionPane.showMessageDialog(null, phone + " không phải số điện thoại");
            return;
        }
        if (kiemTra.equals("Chưa nhập email")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập email");
            return;
        }
        if (kiemTra.equals(email + " không phải là email.")) {
            JOptionPane.showMessageDialog(null, email + " không phải là email.");
            return;
        }
        if (kiemTra.equals("Chưa nhập địa chỉ")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập địa chỉ");
            return;
        }
        if (kiemTra.equals("Dữ liệu thỏa mãn")) {
            int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm không ?", "Hỏi Thêm", JOptionPane.YES_NO_OPTION);
            if (kt == JOptionPane.YES_OPTION) {
                int kq = bll.themDocGia(maDocGia, tenDocGia, gioiTinh, phone, email, diaChi);
                if (kq > 0) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    hienThiDanhSachDocGia();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại");
                }
                resetDuLieuNhap();
            }
        }
    }//GEN-LAST:event_btnThemDocGiaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            int kq = importFile.doDanhSachDocGiaTuFileExcel(chooser.getSelectedFile().getAbsolutePath());
            if (kq > 0) {
                JOptionPane.showMessageDialog(null, "Thêm thành công " + kq + " độc giả");
                hienThiDanhSachDocGia();
                resetDuLieuNhap();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSuaDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDocGiaActionPerformed
        // TODO add your handling code here:
        //Cách 1
        //        int row = tblCapNhatKetQua.getSelectedRow();
        //        if (row < 0) {
            //            JOptionPane.showMessageDialog(null, "Chưa chọn độc giả");
            //            return;
            //        }
        //        String maDocGia = txtCapNhatMaDocGia.getText().trim();
        //        String tenDocGia = txtCapNhatTenDocGia.getText().trim();
        //        String gioiTinh = rdCapNhatNam.isSelected() == true ? "Nam" : rdCapNhatNu.isSelected() == true ? "Nữ" : "";
        //        String phone = txtCapNhatPhone.getText().trim();
        //        String email = txtCapNhatEmail.getText().trim();
        //        String diaChi = txtCapNhatDiaChi.getText().trim();
        //        if (tenDocGia.equals("") || gioiTinh.equals("") || phone.equals("") || email.equals("") || diaChi.equals("")) {
            //            JOptionPane.showMessageDialog(null, "Chưa nhập đầy đủ dữ liệu");
            //            return;
            //        }
        //        DataBaseQuery.suaDocGia(con, maDocGia, tenDocGia, gioiTinh, phone, email, diaChi);
        //        hienThiDanhSachDocGia();
        //        resetCapNhat();
        //Cách 2
        int row = tblTimKiemKetQua.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chưa chọn độc giả");
            return;
        }
        String maDocGia = txtTimKiemMaDocGia.getText().trim();
        String tenDocGia = txtTimKiemTenDocGia.getText().trim();
        String gioiTinh = rdNam.isSelected() == true ? "Nam" : rdNu.isSelected() == true ? "Nữ" : "";
        String phone = txtTimKiemPhone.getText().trim();
        String email = txtTimKiemEmail.getText().trim();
        String diaChi = txtTimKiemDiaChi.getText().trim();
        String kiemTra = KiemTraDuLieuNhapThoaMan.kiemTraDuLieuNhapDocGia(maDocGia, tenDocGia, gioiTinh, phone, email, diaChi);
        if (kiemTra.equals("Chưa nhập dữ liệu")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu");
            return;
        }
        if (kiemTra.equals("Chưa nhập mã độc giả")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập mã độc giả");
            return;
        }
        if (kiemTra.equals("Chưa nhập tên độc giả")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên độc giả");
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
        if (kiemTra.equals(phone + " không phải số điện thoại")) {
            JOptionPane.showMessageDialog(null, phone + " không phải số điện thoại");
            return;
        }
        if (kiemTra.equals("Chưa nhập email")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập email");
            return;
        }
        if (kiemTra.equals(email + " không phải là email.")) {
            JOptionPane.showMessageDialog(null, email + " không phải là email.");
            return;
        }
        if (kiemTra.equals("Chưa nhập địa chỉ")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập địa chỉ");
            return;
        }
        if (kiemTra.equals("Dữ liệu thỏa mãn")) {
            int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa không ?", "Hỏi Sửa", JOptionPane.YES_NO_OPTION);
            if (kt == JOptionPane.YES_OPTION) {
                int kq = bll.suaDocGia(maDocGia, tenDocGia, gioiTinh, phone, email, diaChi);
                if (kq > 0) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                }
                hienThiDanhSachDocGia();
                resetDuLieuNhap();
            }
        }
    }//GEN-LAST:event_btnSuaDocGiaActionPerformed

    private void btnXoaDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDocGiaActionPerformed
        // TODO add your handling code here:
        //Cash 1
        //        int row = tblCapNhatKetQua.getSelectedRow();
        //        if (row < 0) {
            //            JOptionPane.showMessageDialog(null, "Chưa chọn độc giả");
            //            return;
            //        } else {
            //            int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không ?", "Hỏi Xóa", JOptionPane.YES_NO_OPTION);
            //            if (kt == JOptionPane.YES_OPTION) {
                //                String maDocGia = txtCapNhatMaDocGia.getText();
                //                DataBaseQuery.deleteDocGia(con, maDocGia);
                //                hienThiDanhSachDocGia();
                //                tblCapNhatKetQua.setModel(dtmToanBoDocGia);
                //                resetCapNhat();
                //            }
            //        }

        int[] row = tblTimKiemKetQua.getSelectedRows();
        if (row.length > 0) {
            int kt = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không ?", "Hỏi Xóa", JOptionPane.YES_NO_OPTION);
            if (kt == JOptionPane.YES_OPTION) {
                int soLuong = 0;
                for (int x : row) {
                    String maDocGia = tblTimKiemKetQua.getValueAt(x, 0) + "";
                    maDocGia = maDocGia.trim();
                    soLuong += bll.xoaDocGia(maDocGia, "", "", "", "", "");
                }
                if (soLuong > 0) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công " + soLuong + " độc giả");
                    hienThiDanhSachDocGia();
                    resetDuLieuNhap();
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thấy bại");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn độc giả");
            return;
        }
    }//GEN-LAST:event_btnXoaDocGiaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        hienThiDanhSachDocGia();
        btnLast = jButton3;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        resetDuLieuNhap();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnTimKiemInRaFIleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemInRaFIleActionPerformed
        // TODO add your handling code here:
        if (btnLast == btnTimKiem || btnLast == jButton3) {
            JOptionPane.showMessageDialog(null, "<html><p>Đang chuẩn bị in kết quả.</p>"
                + "<p>Vui lòng chờ trong giây lát </p></html>");
            if (btnLast == btnTimKiem) {
                String maDocGia = txtTimKiemMaDocGia.getText();
                String tenDocGia = txtTimKiemTenDocGia.getText();
                String gioiTinh = rdNam.isSelected() == true ? "Nam" : rdNu.isSelected() == true ? "Nữ" : "";
                String phone = txtTimKiemPhone.getText();
                String email = txtTimKiemEmail.getText();
                String diaChi = txtTimKiemDiaChi.getText();
                String regex = "\\s*";
                if (maDocGia.matches(regex) && tenDocGia.matches(regex) && gioiTinh.matches(regex) && phone.matches(regex) && email.matches(regex) && diaChi.matches(regex)) {
                    JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu để tìm kiếm");
                    return;
                }
                inDocGia.printKetQuaTimKiemDocGia("%" + maDocGia + "%", "%" + tenDocGia + "%", "%" + gioiTinh + "%", "%" + phone + "%", "%" + email + "%", "%" + diaChi + "%");
            } else {
                inDocGia.printToanBoDocGia();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy tìm kiếm trước");
        }
    }//GEN-LAST:event_btnTimKiemInRaFIleActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int row = tblTimKiemKetQua.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Hãy chọn một độc giả để thêm vào thông tin mượn sách");
        } else {
            dgDangKy.setMaDocGia(tblTimKiemKetQua.getValueAt(row, 0) + "");
            dgDangKy.setTenDocGia(tblTimKiemKetQua.getValueAt(row, 1) + "");
            dgDangKy.setGioiTinh(tblTimKiemKetQua.getValueAt(row, 2) + "");
            dgDangKy.setPhone(tblTimKiemKetQua.getValueAt(row, 3) + "");
            dgDangKy.setEmail(tblTimKiemKetQua.getValueAt(row, 4) + "");
            dgDangKy.setDiaChi(tblTimKiemKetQua.getValueAt(row, 5) + "");
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm độc giả này vào mượn sách", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "<html><p>Thêm thành công. Sau khi cửa sổ này đóng.</p>"
                    + "<p>Vui lòng nhấn nút \"Lấy thông tin độc giả vừa chọn\" để cập nhật thông tin.</p></html>", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblTimKiemKetQuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimKiemKetQuaMouseClicked
        // TODO add your handling code here:
        //        int row = tblTimKiemKetQua.getSelectedRow();
        //        dgDangKy.setMaDocGia(tblTimKiemKetQua.getValueAt(row, 0) + "");
        //        dgDangKy.setTenDocGia(tblTimKiemKetQua.getValueAt(row, 1) + "");
        //        dgDangKy.setGioiTinh(tblTimKiemKetQua.getValueAt(row, 2) + "");
        //        dgDangKy.setPhone(tblTimKiemKetQua.getValueAt(row, 3) + "");
        //        dgDangKy.setEmail(tblTimKiemKetQua.getValueAt(row, 4) + "");
        //        dgDangKy.setDiaChi(tblTimKiemKetQua.getValueAt(row, 5) + "");
        //        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm độc giả này vào mượn sách", "Xác nhận", JOptionPane.YES_NO_OPTION);
        //        if (xacNhan == JOptionPane.YES_OPTION) {
            //            this.dispose();
            //        }
    }//GEN-LAST:event_tblTimKiemKetQuaMouseClicked

    private void tblTimKiemKetQuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimKiemKetQuaMousePressed
        // TODO add your handling code here:
        resetDuLieuNhap();
        int row = tblTimKiemKetQua.getSelectedRow();
        txtTimKiemMaDocGia.setText((tblTimKiemKetQua.getValueAt(row, 0) + "").trim());
        txtTimKiemTenDocGia.setText((tblTimKiemKetQua.getValueAt(row, 1) + "").trim());
        String gioiTinh = (tblTimKiemKetQua.getValueAt(row, 2) + "").trim();
        if (gioiTinh.equals("Nam")) {
            rdNam.setSelected(true);
            rdNu.setSelected(false);
        } else {
            rdNu.setSelected(true);
            rdNam.setSelected(false);
        }
        txtTimKiemPhone.setText((tblTimKiemKetQua.getValueAt(row, 3) + "").trim());
        txtTimKiemEmail.setText((tblTimKiemKetQua.getValueAt(row, 4) + "").trim());
        txtTimKiemDiaChi.setText((tblTimKiemKetQua.getValueAt(row, 5) + "").trim());
    }//GEN-LAST:event_tblTimKiemKetQuaMousePressed

    private void tblTimKiemKetQuaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimKiemKetQuaMouseReleased
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(QuanLyDocGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyDocGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyDocGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyDocGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyDocGia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaDocGia;
    private javax.swing.JButton btnThemDocGia;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemInRaFIle;
    private javax.swing.JButton btnXoaDocGia;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JTextField txtTimKiemEmail;
    private javax.swing.JTextField txtTimKiemMaDocGia;
    private javax.swing.JTextField txtTimKiemPhone;
    private javax.swing.JTextField txtTimKiemTenDocGia;
    // End of variables declaration//GEN-END:variables
}
