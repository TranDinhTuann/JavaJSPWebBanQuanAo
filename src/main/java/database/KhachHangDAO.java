package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.KhachHang;

public class KhachHangDAO implements DAOInterface<KhachHang> {
	public ArrayList<KhachHang> data = new ArrayList<>();
	
	@Override
	public ArrayList<KhachHang> selectAll() {
		ArrayList<KhachHang> ketqua = new ArrayList<KhachHang>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM khachhang";
			PreparedStatement st = con.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String maKhachHang = rs.getString("makhachhang");
				String tenDangNhap = rs.getString("tendangnhap");
				String matKhau = rs.getString("matkhau");
				String hoVaten = rs.getString("hovaten");
				String gioiTinh = rs.getString("gioitinh");
				String diaChi = rs.getString("diachi");
				String diaChiNhanHang = rs.getString("diachinhanhang");
				String diaChiMuaHang = rs.getString("diachimuahang");
				Date ngaySinh = rs.getDate("ngaysinh");
				String soDienThoai = rs.getString("sodienthoai");
				String email = rs.getString("email");
				boolean dangKyNhanBanTinEmail = rs.getBoolean("dangkynhanbantinemail");
				
				KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaten, gioiTinh, diaChi, diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBanTinEmail);
				ketqua.add(kh);
			}
			JDBCUtil.closeConnection(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public KhachHang selectById(KhachHang t) {
		KhachHang ketqua = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			String sql = "SELECT * FROM khachhang WHERE makhachhang=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaKhachHang());
			
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String maKhachHang = rs.getString("makhachhang");
				String tenDangNhap = rs.getString("tendangnhap");
				String matKhau = rs.getString("matkhau");
				String hoVaten = rs.getString("hovaten");
				String gioiTinh = rs.getString("gioitinh");
				String diaChi = rs.getString("diachi");
				String diaChiNhanHang = rs.getString("diachinhanhang");
				String diaChiMuaHang = rs.getString("diachimuahang");
				Date ngaySinh = rs.getDate("ngaysinh");
				String soDienThoai = rs.getString("sodienthoai");
				String email = rs.getString("email");
				boolean dangKyNhanBanTinEmail = rs.getBoolean("dangkynhanbantinemail");
				
				ketqua = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaten, gioiTinh, diaChi, diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBanTinEmail);
				break;
			}
			JDBCUtil.closeConnection(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}
	
	public KhachHang selectByUsernameAndPassword(KhachHang t) {
		KhachHang ketqua = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			String sql = "SELECT * FROM khachhang WHERE tendangnhap=? and matkhau=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTenDangNhap());
			st.setString(2, t.getMatKhau());
			
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String maKhachHang = rs.getString("makhachhang");
				String tenDangNhap = rs.getString("tendangnhap");
				String matKhau = rs.getString("matkhau");
				String hoVaten = rs.getString("hovaten");
				String gioiTinh = rs.getString("gioitinh");
				String diaChi = rs.getString("diachi");
				String diaChiNhanHang = rs.getString("diachinhanhang");
				String diaChiMuaHang = rs.getString("diachimuahang");
				Date ngaySinh = rs.getDate("ngaysinh");
				String soDienThoai = rs.getString("sodienthoai");
				String email = rs.getString("email");
				boolean dangKyNhanBanTinEmail = rs.getBoolean("dangkynhanbantinemail");
				
				ketqua = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaten, gioiTinh, diaChi, diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBanTinEmail);
				break;
			}
			JDBCUtil.closeConnection(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int insert(KhachHang t) {
		int ketqua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			
			String sql = "INSERT INTO khachhang(maKhachHang, tenDangNhap, matKhau, hoVaten, gioiTinh, diaChi, diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBanTinEmail)" + 
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaKhachHang());
			st.setString(2, t.getTenDangNhap());
			st.setString(3, t.getMatKhau());
			st.setString(4, t.getHoVaTen());
			st.setString(5, t.getGioiTinh());
			st.setString(6, t.getDiaChi());
			st.setString(7, t.getDiaChiNhanHang());
			st.setString(8, t.getDiaChiMuaHang());
			st.setDate(9, t.getNgaySinh());
			st.setString(10, t.getSoDienThoai());
			st.setString(11, t.getEmail());
			st.setBoolean(12, t.isDangKyNhanBanTinEmail());
			// b3: chạy câu lệnh sql
			ketqua = st.executeUpdate();
//			b4 : duyệt và lấy giá trị từng cột
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketqua + " dòng bị thay đổi");
//			b5: đóng
			JDBCUtil.closeConnection(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int insertAll(ArrayList<KhachHang> arr) {
		int count=0;
		for (KhachHang KhachHang : arr) {
			count += this.insert(KhachHang);
		}
		return count;
	}

	@Override
	public int delete(KhachHang t) {
		int ketqua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			
			String sql = "DELETE from khachhang " + 
			"WHERE makhachhang=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaKhachHang());
			
			// b3: chạy câu lệnh sql
			ketqua = st.executeUpdate();
			
//			b4 : duyệt và lấy giá trị từng cột
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketqua + " dòng bị thay đổi");
//			b5: đóng
			JDBCUtil.closeConnection(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int deleteAll(ArrayList<KhachHang> arr) {
		int count = 0;
		for (KhachHang KhachHang : arr) {
			count += this.insert(KhachHang);
		}
		return count;
	}

	@Override
	public int update(KhachHang t) {
		int ketqua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			
			String sql = "UPDATE khachhang "+
			"SET "+
			"tendangnhap=?"+
			"matkhau=?"+
			"hovaten=?"+
			"gioitinh=?"+
			"diachi=?"+
			"diachinhanhang=?"+
			"diachimuahang=?"+
			"ngaysinh=?"+
			"sodienthoai=?"+
			"email=?"+
			"dangkynhanbantinemail=?"+
			"WHERE makhachhang=?";
			
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTenDangNhap());
			st.setString(2, t.getMatKhau());
			st.setString(3, t.getHoVaTen());
			st.setString(4, t.getGioiTinh());
			st.setString(5, t.getDiaChi());
			st.setString(6, t.getDiaChiNhanHang());
			st.setString(7, t.getDiaChiMuaHang());
			st.setDate(8, t.getNgaySinh());
			st.setString(9, t.getSoDienThoai());
			st.setString(10, t.getEmail());
			st.setBoolean(11, t.isDangKyNhanBanTinEmail());
			st.setString(12, t.getMaKhachHang());
			
			// b3: chạy câu lệnh sql
			ketqua = st.executeUpdate();
			
//			b4 : duyệt và lấy giá trị từng cột
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketqua + " dòng bị thay đổi");
//			b5: đóng
			JDBCUtil.closeConnection(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}
	
	public boolean kiemTraTenDangNhap(String tenDangNhap) {
		boolean ketQua = false;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM khachhang WHERE tenDangNhap=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, tenDangNhap);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				ketQua = true;
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public boolean changePassword(KhachHang t) {
		int ketqua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			
			String sql = "UPDATE khachhang "+
			"SET "+
			"matkhau=?"+
			"WHERE makhachhang=?";
			
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMatKhau());
			st.setString(2, t.getMaKhachHang());
			
			// b3: chạy câu lệnh sql
			ketqua = st.executeUpdate();
			
//			b4 : duyệt và lấy giá trị từng cột
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketqua + " dòng bị thay đổi");
//			b5: đóng
			JDBCUtil.closeConnection(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// không hiểu lắm dùng boolean là phải thế này
		// true là ít nhất lớn hơn 0 còn false khi nó trả về không
		// hiểu đơn giản bên trên ketqua = 0 là false h >0 là true
		return ketqua>0;
	}
	
	public int updateInfo(KhachHang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE khachhang " + " SET " + " hovaten=?" + ", gioitinh=?"
					+ ", diachi=?" + ", diachinhanhang=?" + ", diachimuahang=?" + ", ngaysinh=?" + ", sodienthoai=?"
					+ ", email=?" + ", dangkinhanbangtin=?" + " WHERE makhachhang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getHoVaTen());
			st.setString(2, t.getGioiTinh());
			st.setString(3, t.getDiaChi());
			st.setString(4, t.getDiaChiNhanHang());
			st.setString(5, t.getDiaChiMuaHang());
			st.setDate(6, t.getNgaySinh());
			st.setString(7, t.getSoDienThoai());
			st.setString(8, t.getEmail());
			st.setBoolean(9, t.isDangKyNhanBanTinEmail());
			st.setString(10, t.getMaKhachHang());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public static void main(String[] args) {
		KhachHangDAO khd = new KhachHangDAO();
		
//		ArrayList<KhachHang> kh = khd.selectAll();
//		for (KhachHang khachHang : kh) {
//			System.out.println(khachHang.toString());
//		}
		
//		KhachHang id = new KhachHang("KH01", null, null, null, null, null, null, null, null, null, null, false);
//		KhachHang kh = khd.selectById(id);
//		System.out.println(kh);
		
//		KhachHang khnew = new KhachHang("KH03", "TuanTran", "tuantd", "Trần Đình Tuấn", "Nam", "Hà Nội", "Hà Nội", "Hà Nội", new Date(2002-1900, 12 ,12 ), "0981634164", "tuan@gmail.com", false);
//		khd.insert(khnew);
//		System.out.println(khnew);
		
		// lỗi
//		KhachHang khold = new KhachHang("KH01", null, null, null, null, null, null, null, null, null, null, false);
//		khd.delete(khold);
//		System.out.println(khold);
		
		// lỗi
//		KhachHang kh_update = khd.selectById( new KhachHang("KH03", "TuanTran", "tuantd", "Trần Đình Tuấn", "Nam", "Hà Nội", "Hà Nội", "Hà Nội", new Date(2002-1900, 12 ,12 ), "0981634164", "tuan@gmail.com", false));
//		kh_update.setDiaChi("Vinhomes");
//		khd.update(kh_update);
//		System.out.println(kh_update);
	}
}
