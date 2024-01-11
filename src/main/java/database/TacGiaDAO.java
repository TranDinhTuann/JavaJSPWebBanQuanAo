package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.TacGia;

public class TacGiaDAO implements DAOInterface<TacGia> {

	@Override
	public ArrayList<TacGia> selectAll() {
		ArrayList<TacGia> ketqua = new ArrayList<TacGia>();
		try {
			//buoc 1: tao ket noi csdl
			Connection con = JDBCUtil.getConnection();
					
			// buoc2: tao doi tuong statement
			String sql = "SELECT * FROM tacgia";
			// tạo đối tượng này từ kết nối cơ sở dữ liệu con và sql
			PreparedStatement st = con.prepareStatement(sql);
			
			// buoc3: thuc thi cau lenh sql
			System.out.println(sql);	
			// thực thi truy vấn và trả về 1 đối tượng resultset 
			ResultSet rs = st.executeQuery();
			
			// buoc4: duyệt và lấy giá trị từng cột
			while(rs.next()) {
				String maTacGia = rs.getString("matacgia");
				String haVaTen = rs.getString("hovaten");
				Date ngaySinh = rs.getDate("ngaysinh");
				String tieuSu = rs.getString("tieusu");
				// 1 đối tượng
				TacGia tg = new TacGia(maTacGia, haVaTen, ngaySinh, tieuSu);
				ketqua.add(tg);
			}
			
			// buoc 5: dong 
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public TacGia selectById(TacGia t) {
		TacGia ketqua = null;
		try {
			//buoc 1: tao ket noi csdl
			Connection con = JDBCUtil.getConnection();
					
			// buoc2: tao doi tuong statement
			String sql = "SELECT * FROM tacgia WHERE matacgia=?";
			PreparedStatement st = con.prepareStatement(sql); // tạo đối tượng này từ kết nối cơ sở dữ liệu con và sql
			st.setString(1, t.getMaTacGia()); // thiết lập giá trị của tham số thứ nhất trong truy vấn SQL bằng giá trị được truyền từ phương thức t.getMaTacGia(). 
			
			// buoc3: thuc thi cau lenh sql
			System.out.println(sql);	
			// thực thi truy vấn và trả về 1 đối tượng resultset 
			ResultSet rs = st.executeQuery();
			
			// buoc4: duyệt và lấy giá trị từng cột
			while(rs.next()) {
				String maTacGia = rs.getString("matacgia");
				String haVaTen = rs.getString("hovaten");
				Date ngaySinh = rs.getDate("ngaysinh");
				String tieuSu = rs.getString("tieusu");
				// 1 đối tượng
				ketqua = new TacGia(maTacGia, haVaTen, ngaySinh, tieuSu);
				break;
			}
			
			// buoc 5: dong 
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int insert(TacGia t) {
		int ketqua = 0;
		try {
			//buoc 1: tao ket noi csdl
			Connection con = JDBCUtil.getConnection();
					
			// buoc2: tao doi tuong statement
			String sql = "INSERT INTO tacgia (matacgia, hovaten, ngaysinh, tieusu)" + 
						"VALUES (?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql); // tạo đối tượng này từ kết nối cơ sở dữ liệu con và sql
			
			st.setString(1, t.getMaTacGia()); // thiết lập giá trị của tham số thứ nhất trong truy vấn SQL bằng giá trị được truyền từ phương thức t.getMaTacGia(). 
			st.setString(2, t.getHoVaTen());
			st.setDate(3, t.getNgaySinh());
			st.setString(4, t.getTieuSu());
			
			// buoc3: thuc thi cau lenh sql
			System.out.println(sql);
			ketqua = st.executeUpdate();
			
			// buoc4: duyệt và lấy giá trị từng cột
			System.out.println("Bạn đã thực thi: "+sql);
			System.out.println("Có "+ketqua+" dòng bị thay đổi");
			
			// buoc 5: dong 
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int insertAll(ArrayList<TacGia> arr) {
		int count = 0;
		for (TacGia tacGia : arr) {
			count += this.insert(tacGia);
		}
		return count;
	}

	@Override
	public int delete(TacGia t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from tacgia "+
					 " WHERE matacgia=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTacGia());
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public int deleteAll(ArrayList<TacGia> arr) {
		int count = 0;
		for (TacGia tacGia : arr) {
			count += this.delete(tacGia);
		}
		return count;
	}

	@Override
	public int update(TacGia t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE tacgia "+
					 " SET " +
					 " hovaten=?"+
					 ", ngaysinh=?"+
					 ", tieusu=?"+
					 " WHERE matacgia=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getHoVaTen());
			st.setDate(2, t.getNgaySinh());
			st.setString(3, t.getTieuSu());
			st.setString(4, t.getMaTacGia());
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
	
	public static void main(String[] args) {
		TacGiaDAO tgd = new TacGiaDAO();
		
		ArrayList<TacGia> tg = tgd.selectAll();
		for (TacGia tacGia : tg) {
			System.out.println(tacGia.toString());
		}
//		
//		TacGia id = new TacGia("TG01", "", null, "");
//		TacGia tg1 = tgd.selectById(id);
//		System.out.println(tg1);
		
//		TacGia tg_new = new TacGia("TG04", "Nguyễn Nhật Diệu An", new Date(2019-1900, 06, 9), "xinh gái");
//		tgd.insert(tg_new);
//		System.out.println(tg_new);
		
//		TacGia tg_new = new TacGia("TG04", "Nguyễn Nhật Diệu An1", new Date(2019-1900, 06, 9), "xinh gái");
//		tgd.delete(tg_new);
//		System.out.println(tg_new);
		
//		TacGia tg_new = tgd.selectById(new TacGia("TG01", "", null, ""));
//		tg_new.setTieuSu("da thay doi");
//		tgd.update(tg_new);
//		System.out.println(tg_new);
	}
}

