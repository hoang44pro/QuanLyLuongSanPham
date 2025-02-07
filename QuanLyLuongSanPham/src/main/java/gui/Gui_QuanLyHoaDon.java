package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import gui_package.ChucNang;
import gui_package.HintTextFieldUI;
import model.HoaDonBanHang;
import model.HoaDonNhapHang;
import model.KhachHang;
import model.TaiKhoan;
import services.QuanLyHoaDonService;

public class Gui_QuanLyHoaDon extends JPanel implements MouseListener {
	private JTextField txtTimKiem;
	private JTable tblHoaDon;
	private JTable tblChiTiet;
	private JLabel lblNgay;
	private JButton btnThemHoaDon;
	private JLabel lblGio;
	private JLabel lblMaKhachHang;
	private JLabel lblSdt;
	private JLabel lblTenKhachHang;
	private JLabel lblDiaChi;
	private JLabel lblMaNhanVien;
	private JComboBox cboTimKiem;
	private QuanLyHoaDonService quanLyHoaDonService = new QuanLyHoaDonService();
	private DefaultTableModel modelHoaDon;
	private DefaultTableModel modelChiTiet;
	private JDateChooser txtNgayLap;
	private JComboBox cboLoaiHoaDon;
	private JTextField txtMaKh;
	private JTextField txtSdt;
	private JTextField txtTen;
	private JTextField txtDiaChi;
	private JTextField txtMaNv;
	private JTextField txtTenNv;
	protected JPanel pnlHead;
	private TaiKhoan taiKhoan;
	private List<?> listTable;
	private JButton btnLamMoi;;

	/**
	 * Create the panel.
	 */
	public Gui_QuanLyHoaDon() {

		setBounds(new Rectangle(0, 0, 1600, 1046));

		setBackground(new Color(242, 129, 25));
		setSize(1600, 1046);
		setLayout(null);
		pnlHead = new JPanel();
		pnlHead.setBackground(new Color(242, 129, 25));
		pnlHead.setBounds(0, 0, 1600, 92);
		add(pnlHead);
		pnlHead.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("QUẢN LÝ HÓA ĐƠN");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel_2.setBounds(491, 0, 531, 92);
		pnlHead.add(lblNewLabel_2);

		lblNgay = new JLabel("New label");
		lblNgay.setForeground(new Color(255, 255, 255));
		lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNgay.setBounds(0, 16, 233, 33);
		pnlHead.add(lblNgay);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(194, 93, 0));
		panel_1.setPreferredSize(new Dimension(1600, 72));
		panel_1.setBounds(0, 92, 1600, 72);
		add(panel_1);

		btnThemHoaDon = new JButton("Thêm hóa đơn");
		btnThemHoaDon.setBounds(388, 13, 204, 41);
		panel_1.setLayout(null);

		cboLoaiHoaDon = new JComboBox();
		cboLoaiHoaDon.setBounds(0, 11, 207, 45);
		cboLoaiHoaDon.setModel(new DefaultComboBoxModel(new String[] { "Hóa đơn bán", "Hóa đơn nhập" }));
		cboLoaiHoaDon.setSelectedIndex(0);
		cboLoaiHoaDon.setForeground(Color.WHITE);
		cboLoaiHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 24));
		cboLoaiHoaDon.setBackground(new Color(233, 180, 46));
		panel_1.add(cboLoaiHoaDon);
		btnThemHoaDon.setForeground(Color.WHITE);
		btnThemHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnThemHoaDon.setBackground(new Color(233, 180, 46));
		panel_1.add(btnThemHoaDon);

		txtNgayLap = new JDateChooser((new JCalendar()).getDate());
		txtNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNgayLap.setForeground(Color.WHITE);
		txtNgayLap.setBounds(217, 11, 161, 45);
		txtNgayLap.setDateFormatString("dd/MM/yyyy");
		panel_1.add(txtNgayLap);

		cboTimKiem = new JComboBox();
		
		cboTimKiem.setToolTipText("Tìm kiếm hóa đơn theo các tiêu chí ");
		cboTimKiem.setModel(
				new DefaultComboBoxModel(new String[] { "Mã hóa đơn", "Số điện thoại Khách", "Theo tên khách" }));
		cboTimKiem.setSelectedIndex(0);
		cboTimKiem.setForeground(Color.WHITE);
		cboTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 24));
		cboTimKiem.setBackground(new Color(233, 180, 46));
		cboTimKiem.setBounds(1360, 11, 225, 45);
		panel_1.add(cboTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setUI(new HintTextFieldUI("Tìm kiếm", true));
		txtTimKiem.setToolTipText("Nhập vào thông tin tìm kiếm");
		txtTimKiem.setBounds(1001, 11, 349, 45);
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_1.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLamMoi.setBackground(new Color(233, 180, 46));
		btnLamMoi.setBounds(602, 13, 161, 41);
		panel_1.add(btnLamMoi);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 153, 0));
		scrollPane.setBounds(0, 206, 1583, 426);
		add(scrollPane);

		modelHoaDon = new DefaultTableModel(new String[] { "M\u00E3 h\u00F3a \u0111\u01A1n", "Ng\u00E0y l\u1EADp",
				"Khuy\u1EBFn m\u00E3i", "Thu\u1EBF", "Th\u00E0nh ti\u1EC1n" }, 10) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		tblHoaDon = new JTable(modelHoaDon);
		tblHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblHoaDon.setRowMargin(5);
		tblHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tblHoaDon.setRowHeight(36);
		JTableHeader headerTable = tblHoaDon.getTableHeader();
		headerTable.setFont(new Font("Tahoma", Font.PLAIN, 24));
		headerTable.setBackground(new Color(248, 198, 153));
		scrollPane.setViewportView(tblHoaDon);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		scrollPane_1.setBounds(0, 679, 1150, 362);
		add(scrollPane_1);

		modelChiTiet = new DefaultTableModel(new String[] { "M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m",
				"\u0110\u01A1n gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng", "T\u1ED5ng ti\u1EC1n" }, 0) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tblChiTiet = new JTable();
		tblChiTiet.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tblChiTiet.setRowHeight(36);
		tblChiTiet.setModel(modelChiTiet);
		tblChiTiet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTableHeader headerTable1 = tblChiTiet.getTableHeader();
		headerTable1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		headerTable1.setBackground(new Color(248, 198, 153));
		scrollPane_1.setViewportView(tblChiTiet);

		JLabel lblNewLabel = new JLabel("Bảng hóa đơn");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(0, 170, 161, 20);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Bảng chi tiết hóa đơn");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(0, 648, 268, 20);
		add(lblNewLabel_1);

		lblGio = new JLabel("New label");
		lblGio.setForeground(new Color(255, 255, 255));
		lblGio.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblGio.setBounds(0, 50, 201, 42);
		pnlHead.add(lblGio);

		ChucNang.setGio(lblGio, lblNgay);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(248, 198, 153));
		panel_4.setBounds(1177, 674, 396, 362);
		add(panel_4);

		lblMaKhachHang = new JLabel("Mã Khách: ");
		lblMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMaKhachHang.setBounds(15, 64, 147, 35);
		panel_4.add(lblMaKhachHang);

		lblSdt = new JLabel("Số điện thoại: ");
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSdt.setBounds(15, 110, 155, 35);
		panel_4.add(lblSdt);

		lblDiaChi = new JLabel("Địa chỉ: ");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDiaChi.setBounds(15, 202, 147, 35);
		panel_4.add(lblDiaChi);

		lblMaNhanVien = new JLabel("Mã nhân viên:");
		lblMaNhanVien.setHorizontalTextPosition(SwingConstants.LEFT);
		lblMaNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaNhanVien.setBorder(new LineBorder(new Color(255, 0, 0)));
		lblMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMaNhanVien.setBounds(8, 248, 155, 35);
		panel_4.add(lblMaNhanVien);

		JLabel lblThngTinKhch = new JLabel("Thông tin thêm");
		lblThngTinKhch.setBorder(new LineBorder(new Color(255, 255, 255)));
		lblThngTinKhch.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngTinKhch.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblThngTinKhch.setBounds(0, 0, 396, 51);
		panel_4.add(lblThngTinKhch);

		lblTenKhachHang = new JLabel("Tên: ");
		lblTenKhachHang.setBounds(15, 156, 147, 35);
		panel_4.add(lblTenKhachHang);
		lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		txtMaKh = new JTextField();
		txtMaKh.setEditable(false);
		txtMaKh.setBounds(172, 64, 214, 35);
		panel_4.add(txtMaKh);
		txtMaKh.setColumns(10);
		
		txtSdt = new JTextField();
		txtSdt.setEditable(false);
		txtSdt.setColumns(10);
		txtSdt.setBounds(172, 110, 214, 35);
		panel_4.add(txtSdt);
		
		txtTen = new JTextField();
		txtTen.setEditable(false);
		txtTen.setColumns(10);
		txtTen.setBounds(172, 156, 214, 35);
		panel_4.add(txtTen);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(172, 202, 214, 35);
		panel_4.add(txtDiaChi);
		
		txtMaNv = new JTextField();
		txtMaNv.setBorder(new LineBorder(new Color(255, 0, 0)));
		txtMaNv.setEditable(false);
		txtMaNv.setColumns(10);
		txtMaNv.setBounds(172, 248, 214, 35);
		panel_4.add(txtMaNv);
		
		JLabel lblTnNhnVin = new JLabel("Nhân viên: ");
		lblTnNhnVin.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTnNhnVin.setHorizontalAlignment(SwingConstants.LEFT);
		lblTnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTnNhnVin.setBorder(new LineBorder(new Color(255, 0, 0)));
		lblTnNhnVin.setBounds(8, 294, 154, 35);
		panel_4.add(lblTnNhnVin);
		
		txtTenNv = new JTextField();
		txtTenNv.setBorder(new LineBorder(new Color(255, 0, 0)));
		txtTenNv.setEditable(false);
		txtTenNv.setColumns(10);
		txtTenNv.setBounds(172, 294, 214, 35);
		panel_4.add(txtTenNv);
		ChucNang.setTableAlternateRow();
		tblHoaDon.addMouseListener(this);
		
		btnLamMoi.addActionListener(e->{
			modelHoaDon.getDataVector().removeAllElements();
			tblHoaDon.removeAll();
			modelChiTiet.getDataVector().removeAllElements();
			tblChiTiet.removeAll();
//			Date input = txtNgayLap.getDate();
//			LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Date dateN= new Date();
			LocalDate date = LocalDate.now();
			txtNgayLap.setDate(dateN);
			loadTableHoaDon(date.getDayOfMonth(), date.getMonthValue(), date.getYear());

			ChucNang.addNullDataTable(modelHoaDon);
			ChucNang.addNullDataTable(modelChiTiet);
		});
		cboTimKiem.addActionListener(e -> {
			int temp = cboTimKiem.getSelectedIndex();
			chucNangCboTimKIem();
			if (temp == 2) {
				txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
						chucNangCboTimKIem();

					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						chucNangCboTimKIem();

					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						chucNangCboTimKIem();

					}
				});
			}

		});

		txtNgayLap.addPropertyChangeListener(e -> {
			modelHoaDon.getDataVector().removeAllElements();
			tblHoaDon.removeAll();
			modelChiTiet.getDataVector().removeAllElements();
			tblChiTiet.removeAll();
			Date input = txtNgayLap.getDate();
			LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			loadTableHoaDon(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
			ChucNang.addNullDataTable(modelHoaDon);
			ChucNang.addNullDataTable(modelChiTiet);
		});
		btnThemHoaDon.addActionListener(e -> {
			int i = cboLoaiHoaDon.getSelectedIndex();
			if (i == 0) {
				Gui_ThemHoaDonBan themHoaDonBan = new Gui_ThemHoaDonBan();
				themHoaDonBan.setVisible(getFocusTraversalKeysEnabled());

			} else {
				Gui_ThemHoaDonNhap themHoaDonNhap = new Gui_ThemHoaDonNhap();
				themHoaDonNhap.setTaiKhoan(taiKhoan); 
				themHoaDonNhap.setVisible(getFocusTraversalKeysEnabled());
			}

		});
		cboLoaiHoaDon.addActionListener(e -> {
			modelHoaDon.getDataVector().removeAllElements();
			tblHoaDon.removeAll();
			modelChiTiet.getDataVector().removeAllElements();
			tblChiTiet.removeAll();
			Date input = txtNgayLap.getDate();
			LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			loadTableHoaDon(date.getDayOfMonth(), date.getMonthValue(), date.getYear());

			ChucNang.addNullDataTable(modelHoaDon);
			ChucNang.addNullDataTable(modelChiTiet);
		});
		txtTimKiem.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					chucNangCboTimKIem();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * load hóa đơn mặc định là ngày hiện tại
	 */
	private void loadTableHoaDon(int ngay, int thang, int nam) {
		int temp = cboLoaiHoaDon.getSelectedIndex();
		if (temp == 0) {
			List<?> list = quanLyHoaDonService.getHoaDonTheoNgay(ngay, thang, nam);
			loadTableHoaDon(list);
		} else if (temp == 1) {
			List<?> list1 = quanLyHoaDonService.getHoaDonNhapTheoNgay(ngay, thang, nam);
			this.xoaRongKhachHang();
			loadTableHoaDon(list1);
		}

	}

	private void loadTableHoaDon(List<?> list) {
		listTable=list;
		ChucNang.clearDataTable(modelHoaDon);
		
		for (Object object : list) {
			Object[] o = (Object[]) object;
			NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
			o[4] = format.format(Double.parseDouble(o[4].toString()));
			modelHoaDon.addRow(o);
		}
		ChucNang.addNullDataTable(modelHoaDon);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1600, 1046);
		frame.getContentPane().add(new Gui_QuanLyHoaDon());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		Object ob = e.getSource();
		if (ob.equals(tblHoaDon)) {
			int temp = cboLoaiHoaDon.getSelectedIndex();

			if (temp == 0) {
				int row = tblHoaDon.getSelectedRow();
				if(row==-1 || row>listTable.size()-1) {
					return;
				}
				modelChiTiet.getDataVector().removeAllElements();
				tblChiTiet.removeAll();
				System.out.println(row);
				String maHoaDon = (String) tblHoaDon.getValueAt(row, 0);
				List<?> listChiTiet = quanLyHoaDonService.getChiTietHoaDonBanTheoMaHoaDon(maHoaDon);
				if (listChiTiet.size() != 0) {
					duaDuLieuVaoTable(listChiTiet, modelChiTiet);
					HoaDonBanHang hd = quanLyHoaDonService.getHoaDonBanHang(maHoaDon);
//					KhachHang khachHang = quanLyHoaDonService.getKhachHang(hd.getKhachHang().getMaKhachHang());
					KhachHang khachHang = hd.getKhachHang();
					System.out.println(khachHang);
					txtMaKh.setText(khachHang.getMaKhachHang());
					txtSdt.setText(khachHang.getsDT());
					txtDiaChi.setText(khachHang.getDiaChi());
					txtTen.setText(khachHang.getTenKhachHang());
					txtMaNv.setText(hd.getNhanVien().getMaNhanVien());
					txtTenNv.setText(hd.getNhanVien().getTenNhanVien());
				}
				ChucNang.addNullDataTable(modelChiTiet);
			} else if (temp == 1) {
				modelChiTiet.getDataVector().removeAllElements();
				tblChiTiet.removeAll();
				int row = tblHoaDon.getSelectedRow();
				String maHoaDon = (String) tblHoaDon.getValueAt(row, 0);
				List<?> listChiTiet = quanLyHoaDonService.getChiTietHoaDonNhapTheoMaHoaNhap(maHoaDon);
				if (listChiTiet.size() != 0) {
					duaDuLieuVaoTable(listChiTiet, modelChiTiet);
					HoaDonNhapHang hd = quanLyHoaDonService.getHoaDonNhapHang(maHoaDon);
					this.xoaRongKhachHang();
					txtMaNv.setText(hd.getNhanVien().getMaNhanVien());
					txtTenNv.setText(hd.getNhanVien().getTenNhanVien());
				}
				ChucNang.addNullDataTable(modelChiTiet);
			}
		}

	}

	/**
	 * xóa rỗng tất cả thông tin của khách hàng
	 */
	public void xoaRongKhachHang() {
		txtMaKh.setText("");
		txtSdt.setText("");
		txtDiaChi.setText("");
		txtTen.setText("");
		txtMaNv.setText("");
		txtTenNv.setText("");
	}

	/**
	 * hiện thực chức năng cho comboBox tìm kiếm hóa đơn
	 */
	public void chucNangCboTimKIem() {
		int temp = cboTimKiem.getSelectedIndex();
		int temp1 = cboLoaiHoaDon.getSelectedIndex();
		xoaRongKhachHang();
		ChucNang.clearDataTable(modelHoaDon);
		ChucNang.clearDataTable(modelChiTiet);
		String vanBanTim = txtTimKiem.getText().trim();
		if (!vanBanTim.equals("")) {
			if (temp == 0) {
				Object list = quanLyHoaDonService.timHoaDonTheoMa(temp1, vanBanTim);
				if (list != null)
					loadTableHoaDon(Arrays.asList(list));
			} else if (temp == 1) {
				List<?> listKetQua = quanLyHoaDonService.timHoaDonTheoSdt(vanBanTim);
				loadTableHoaDon(listKetQua);
			} else if (temp == 2) {
				List<?> listKQ = quanLyHoaDonService.timHoaDonTheoTenKH(vanBanTim);
				loadTableHoaDon(listKQ);
				System.out.println("So phan tu table "+ listTable.size());
			}
		}
		ChucNang.addNullDataTable(modelChiTiet);
		ChucNang.addNullDataTable(modelHoaDon);

	}

	/**
	 * chèn danh sách chi tiết hóa đơn vào table chi tiết hóa đơn
	 * 
	 * @param list
	 * @param model
	 */
	public void duaDuLieuVaoTable(List<?> list, DefaultTableModel model) {
		for (Object object : list) {
			Object[] o = (Object[]) object;
			NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
			o[4] = format.format(Double.parseDouble(o[4].toString()));
			o[2] = format.format(Double.parseDouble(o[2].toString()));
			model.addRow(o);
		}

	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan=taiKhoan;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
