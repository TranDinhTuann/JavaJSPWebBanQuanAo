package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.SanPham;
import model.TacGia;
import model.TheLoai;

public class SanPhamDAO implements DAOInterface<SanPham> {
	public ArrayList<SanPham> data = new ArrayList<>();
	@Override
	public ArrayList<SanPham> selectAll() {
		ArrayList<SanPham> ketqua = new ArrayList<SanPham>();
		try {
			//buoc 1: tao ket noi csdl
			Connection con = JDBCUtil.getConnection();
					
			// buoc2: tao doi tuong statement
			String sql = "SELECT * FROM sanpham";
			// tạo đối tượng này từ kết nối cơ sở dữ liệu con và sql
			PreparedStatement st = con.prepareStatement(sql);
			
			// buoc3: thuc thi cau lenh sql
			System.out.println(sql);	
			// thực thi truy vấn và trả về 1 đối tượng resultset 
			ResultSet rs = st.executeQuery();
			
			// buoc4: duyệt và lấy giá trị từng cột
			while(rs.next()) {
				String maSanPham = rs.getString("masanpham");
				String tenSanPham = rs.getString("tensanpham");
				String maTacGia = rs.getString("matacgia");
				int namXuatBan = rs.getInt("namxuatban");
				double giaNhap = rs.getDouble("gianhap");
				double giaGoc = rs.getDouble("giagoc");
				double giaBan = rs.getDouble("giaban");
				double soLuong = rs.getDouble("soluong");
				String maTheLoai = rs.getString("matheloai");
				String ngonNgu = rs.getString("ngonngu");
				String moTa = rs.getString("mota");
				
				TacGia tacGia = (new TacGiaDAO().selectById(new TacGia(maTacGia, "", null, "")));
				TheLoai theLoai = (new TheLoaiDAO().selectById(new TheLoai(maTheLoai, "")));
				// 1 đối tượng
				SanPham sp = new SanPham(maSanPham, tenSanPham, tacGia, namXuatBan, giaNhap, giaGoc, giaBan, soLuong, theLoai, ngonNgu, moTa);
				ketqua.add(sp);
			}
			
			// buoc 5: dong 
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public SanPham selectById(SanPham t) {
		for (SanPham SanPham : data) {
			if(SanPham.equals(t)) {
				return SanPham;
			}
		}
		// khong co giong thi return null
		return null;
	}

	@Override
	public int insert(SanPham t) {
		if(this.selectById(t) == null) {
			this.data.add(t);
			return 1;
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<SanPham> arr) {
		int count=0;
		for (SanPham SanPham : arr) {
			count += this.insert(SanPham);
		}
		return count;
	}

	@Override
	public int delete(SanPham t) {
		if(this.selectById(t) != null) {
			this.data.remove(t);
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<SanPham> arr) {
		int count = 0;
		for (SanPham SanPham : arr) {
			count += this.insert(SanPham);
		}
		return count;
	}

	@Override
	public int update(SanPham t) {
		if(this.selectById(t) != null) {
			this.data.remove(t);
			this.data.add(t);
			return 1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		SanPhamDAO spd = new SanPhamDAO();
		
		ArrayList<SanPham> sp = spd.selectAll();
		for (SanPham sanPham : sp) {
			System.out.println(sanPham.toString());
		}
	}
}
