package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import gui_package.CircleBtn;
import gui_package.RoundedPanel;
import model.TaiKhoan;
import services.DangNhapService;

public class Gui_DangNhap extends JFrame {

	private JPanel contentPane;
	private int xClicked;
	private int yClicked;
	private JLabel lblLogo;
	private JPanel panelThongTinDN;
	protected JButton btnDangNhap;
	private JButton btnThoat;
	private JLabel lblTaiKhoan;
	private JTextField txtTaiKhoan;
	private JTextField txtMatKhau;
	private JLabel lblMatKhau;
	private JLabel lbTieuDe;
	private CircleBtn btnXoa;
	private JPanel panelNgang;
	private JLabel lblHD;
	private JLabel lblDB;
	private JLabel lblTK;
	private JLabel lblLuong;
	private DangNhapService dangNhapService = new DangNhapService();
	private Gui_Chinh chinh;
	private JLabel lblQuenMK;
	private static Gui_DangNhap dangNhap=new Gui_DangNhap() ;

	/**
	 * Create the frame.
	 */
	public Gui_DangNhap() {
		chinh = new Gui_Chinh();
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xClicked = e.getX();
				yClicked = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - xClicked, y - yClicked);
			}
		});

		setUndecorated(true);
		setBounds(100, 100, 1200, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 129, 25));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/dt2.png"))));
		lblLogo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(440, 79, 320, 224);
		contentPane.add(lblLogo);

		panelThongTinDN = new RoundedPanel();
		panelThongTinDN.setBackground(new Color(248, 198, 153));
		panelThongTinDN.setBounds(38, 336, 1124, 434);
		contentPane.add(panelThongTinDN);
		panelThongTinDN.setLayout(null);

		lblTaiKhoan = new JLabel("Tài Khoản:");
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTaiKhoan.setForeground(Color.BLACK);
		lblTaiKhoan.setIconTextGap(-20);
		lblTaiKhoan.setBounds(107, 133, 109, 50);
		panelThongTinDN.add(lblTaiKhoan);

		txtTaiKhoan = new JTextField("vanchinh");
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTaiKhoan.setBounds(226, 133, 791, 50);
		panelThongTinDN.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);

		txtMatKhau = new JPasswordField("admin");
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(226, 208, 791, 50);
		panelThongTinDN.add(txtMatKhau);

		lblMatKhau = new JLabel("Mật Khẩu:");
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblMatKhau.setForeground(Color.BLACK);
		lblMatKhau.setIconTextGap(-20);
		lblMatKhau.setBounds(107, 208, 109, 50);
		panelThongTinDN.add(lblMatKhau);

		btnDangNhap = new CircleBtn("Đăng Nhập");
		btnDangNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDangNhap.setBounds(149, 334, 150, 60);
		panelThongTinDN.add(btnDangNhap);

		btnDangNhap.setFont(new Font("Calibri", Font.BOLD, 20));
		btnDangNhap.setBackground(new Color(233, 180, 46));
		btnDangNhap.setBorder(new LineBorder(new Color(0, 0, 0)));

		btnXoa = new CircleBtn("Xóa");
		btnXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnXoa.setBounds(486, 334, 150, 60);
		panelThongTinDN.add(btnXoa);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTaiKhoan.setText("");
				txtMatKhau.setText("");
			}
		});
		btnXoa.setBackground(new Color(233, 180, 46));
		btnXoa.setFont(new Font("Calibri", Font.BOLD, 20));

		btnThoat = new CircleBtn("Thoát");
		btnThoat.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnThoat.setBounds(825, 334, 150, 60);
		panelThongTinDN.add(btnThoat);
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnThoat.setFont(new Font("Calibri", Font.BOLD, 20));
		btnThoat.setBorder(new LineBorder(new Color(1, 242, 233)));
		btnThoat.setBackground(new Color(233, 180, 46));


		lblQuenMK = new JLabel("quên mật khẩu ?");
		lblQuenMK.setHorizontalTextPosition(SwingConstants.CENTER);
		lblQuenMK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblQuenMK.setIconTextGap(-20);
		lblQuenMK.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuenMK.setForeground(Color.GRAY);
		lblQuenMK.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblQuenMK.setBounds(866, 269, 151, 30);
		panelThongTinDN.add(lblQuenMK);
		
		JLabel lblNewLabel = new JLabel("Đăng Nhập");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(450, 44, 224, 50);
		panelThongTinDN.add(lblNewLabel);

		lbTieuDe = new JLabel("QUẢN LÝ LƯƠNG CỬA HÀNG ĐIỆN THOẠI 17");
		lbTieuDe.setVerticalAlignment(SwingConstants.BOTTOM);
		lbTieuDe.setFont(new Font("Calibri", Font.BOLD, 48));
		lbTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lbTieuDe.setForeground(Color.WHITE);
		lbTieuDe.setBounds(0, 0, 1200, 75);
		contentPane.add(lbTieuDe);

		panelNgang = new JPanel();
		panelNgang.setBounds(0, 75, 1200, 230);
		panelNgang.setBackground(new Color(194, 93, 0));
		contentPane.add(panelNgang);
		panelNgang.setLayout(null);

		lblHD = new JLabel("");
		lblHD.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/l.png"))));
		lblHD.setBounds(41, 43, 148, 154);
		panelNgang.add(lblHD);
		lblHD.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHD.setHorizontalAlignment(SwingConstants.CENTER);

		lblTK = new JLabel("");
		lblTK.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/tk.png"))));
		lblTK.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTK.setHorizontalAlignment(SwingConstants.CENTER);
		lblTK.setBounds(237, 43, 148, 154);
		panelNgang.add(lblTK);

		lblDB = new JLabel("");
		lblDB.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/database.png"))));
		lblDB.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDB.setHorizontalAlignment(SwingConstants.CENTER);
		lblDB.setBounds(812, 43, 148, 154);
		panelNgang.add(lblDB);

		lblLuong = new JLabel("");
		lblLuong.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/sp.png"))));
		lblLuong.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblLuong.setBounds(1012, 43, 148, 154);
		panelNgang.add(lblLuong);

		btnDangNhap.addActionListener(e -> {
			String ten = txtTaiKhoan.getText().trim().equals("vanchinh") ? "NV00000000" : txtTaiKhoan.getText().trim();
			String mk = txtMatKhau.getText().trim();
			TaiKhoan taiKhoan = dangNhapService.geTaiKhoan(ten);
			if (!(KiemTraRongText(txtTaiKhoan))) {
				if (taiKhoan != null )
					if(taiKhoan.getNhanVien().gettrangThaiLamViec()) {
						if (mk.equals(taiKhoan.getMatKhau())) {
							chinh.setTaiKhoan(taiKhoan);
							chinh.setVisible(true);
							this.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác");
						}
					}
					else {
						JOptionPane.showMessageDialog(this, "Nhân viên này đã nghỉ việc");
					}
				else {
					JOptionPane.showMessageDialog(this, "Sai tên tài khoản");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Tên tài khoản không được bỏ trống");
			}
		});

	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	public static Gui_DangNhap getInstance() {
		if (dangNhap == null) {
			return new Gui_DangNhap();
		}
		return dangNhap;
	}

	public boolean KiemTraRongText(JTextField txt) {
		return txt.getText().equals("") ? true : false;
	}
}
