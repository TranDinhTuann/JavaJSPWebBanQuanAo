package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.KhachHangDAO;
import model.KhachHang;
import util.MaHoa;

/**
 * Servlet implementation class KhachHang
 */
@WebServlet("/khach-hang")
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KhachHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String hanhDong = request.getParameter("hanhDong");
		if(hanhDong.equals("dang-nhap")) {
			dangNhap(request, response);
		}else if(hanhDong.equals("dang-xuat")) {
			dangXuat(request, response); 
		}else if(hanhDong.equals("dang-ky")) {
			dangKy(request, response); 
		}else if(hanhDong.equals("doi-mat-khau")) {
			doiMatKhau(request, response); 
		}else if(hanhDong.equals("thay-doi-thong-tin")) {
			thayDoiThongTin(request, response); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void dangNhap(HttpServletRequest request, HttpServletResponse response) {
		try {
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
			
			matKhau = MaHoa.toSHA1(matKhau);
			
			KhachHang kh = new KhachHang();
			kh.setTenDangNhap(tenDangNhap);
			kh.setMatKhau(matKhau);
			
			KhachHangDAO khd = new KhachHangDAO();
			KhachHang khachHang = khd.selectByUsernameAndPassword(kh);
			String url="";
			if(khachHang!=null) {
//				tạo phiên của người dùng
				HttpSession session = request.getSession();
				session.setAttribute("khachHang", khachHang);
				url = "/index.jsp";
			}else {
				request.setAttribute("baoLoi", "Tên đăng nhập hoặc mật khẩu không đúng");
				url = "/khachhang/dangnhap.jsp";
			}
			RequestDispatcher rq = getServletContext().getRequestDispatcher(url);
			rq.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void dangXuat(HttpServletRequest request, HttpServletResponse response) {
		try {
			//		Hủy phiên làm việc
					HttpSession session = request.getSession();
					session.invalidate();
					
			//		quay lại trang chủ
			//		chuyển đến đúng link
					String url = request.getScheme() + "://" + request.getServerName() + ":" + 
							request.getServerPort() + request.getContextPath();
					response.sendRedirect(url+"/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void dangKy(HttpServletRequest request, HttpServletResponse response) {
		try {
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
			String matKhauNhapLai = request.getParameter("matKhauNhapLai");
			String hoVaTen = request.getParameter("hoVaTen");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String diaChiKhachHang = request.getParameter("diaChiKhachHang");
			String diaChiMuaHang = request.getParameter("diaChiMuaHang");
			String diaChiNhanHang = request.getParameter("diaChiNhanHang");
			String dienThoai = request.getParameter("dienThoai");
			String email = request.getParameter("email");
			String dongYNhanMail = request.getParameter("dongYNhanMail");
			
			request.setAttribute("tenDangNhap", tenDangNhap);		
			request.setAttribute("hoVaTen", hoVaTen);
			request.setAttribute("gioiTinh", gioiTinh);
			request.setAttribute("ngaySinh", ngaySinh);
			request.setAttribute("diaChiKhachHang", diaChiKhachHang);
			request.setAttribute("diaChiMuaHang", diaChiMuaHang);
			request.setAttribute("diaChiNhanHang", diaChiNhanHang);
			request.setAttribute("dienThoai", dienThoai);
			request.setAttribute("email", email);
			request.setAttribute("dongYNhanMail", dongYNhanMail);
			
			String url = "";
			
			String baoLoi = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();

			if(khachHangDAO.kiemTraTenDangNhap(tenDangNhap)) {
				baoLoi +="Tên đăng nhập đã tồn tại, vui lòng chọn tên đăng nhập khác.<br/>";
			}
			
			if(!matKhau.equals(matKhauNhapLai)) {
				baoLoi +="Mẫu khẩu không khớp.<br/>";
			}else {
				matKhau = MaHoa.toSHA1(matKhau);
			}
			
			request.setAttribute("baoLoi", baoLoi);
			
			
			if(baoLoi.length()>0) {
				url = "/khachhang/dangky.jsp";
			}else {
				Random rd = new Random();
				String maKhachHang = System.currentTimeMillis() + rd.nextInt(1000) +"";
				KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChiKhachHang, diaChiNhanHang, diaChiMuaHang, Date.valueOf(ngaySinh), dienThoai, email, dongYNhanMail!=null);
				khachHangDAO.insert(kh);
				url = "/khachhang/thanhcong.jsp";
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void doiMatKhau(HttpServletRequest request, HttpServletResponse response) {
		try {
			String matKhauHienTai = request.getParameter("matKhauHienTai");
			String matKhauMoi = request.getParameter("matKhauMoi");
			String matKhauMoiNhapLai = request.getParameter("matKhauMoiNhapLai");
			
			String matKhauHienTai_Sha1 = MaHoa.toSHA1(matKhauHienTai);
			String baoLoi="";
			String url="/khachhang/doimatkhau.jsp";
			
			//kiểm tra mật khẩu cũ có giống hay không
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("khachHang");
			KhachHang khachHang = null;
			if(obj!=null) {
				khachHang = (KhachHang)obj;
			}
			if(khachHang==null) {
				baoLoi = "Bạn chưa đăng nhập vào hệ thống!";
			}else {
				// Nếu khách hàng đã đăng nhập thành công thì kiểm tra mật khẩu họ nhập vào có giống trong database hay không
				if(!matKhauHienTai_Sha1.equals(khachHang.getMatKhau())) {
					baoLoi = "Mật khẩu hiện tại không chính xác!";
				}else {
					// nếu giống thì tiếp tục kiểm tra mật khẩu mới có trùng khi nhập lại hay không
					if(!matKhauMoi.equals(matKhauMoiNhapLai)) {
						baoLoi = "Mật khẩu nhập lại không khớp!";
					}else {
						// 2 mật khẩu ban đầu chưa được mã hóa và kiểm tra nó giống nhau hay không
						// trùng thì mã hóa nó và set vào khách hàng mật khẩu đó
						String matKhauMoi_Sha1 = MaHoa.toSHA1(matKhauMoi);
						// nhập mật khẩu vào nhưng nó chưa lưu vào database
						khachHang.setMatKhau(matKhauMoi_Sha1);
						// gọi ra để thay đổi dữ liệu trong database
						KhachHangDAO khd = new KhachHangDAO();
						if(khd.changePassword(khachHang)) {
							baoLoi = "Mật khẩu đã thay đổi thành công!";
						}else {
							baoLoi = "Thay đổi mật khẩu không thành công!";
						}
					}
				}
			}
			
			request.setAttribute("baoLoi", 	baoLoi);
			
			RequestDispatcher rq = getServletContext().getRequestDispatcher(url);
			rq.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void thayDoiThongTin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String hoVaTen = request.getParameter("hoVaTen");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String diaChiKhachHang = request.getParameter("diaChiKhachHang");
			String diaChiMuaHang = request.getParameter("diaChiMuaHang");
			String diaChiNhanHang = request.getParameter("diaChiNhanHang");
			String dienThoai = request.getParameter("dienThoai");
			String email = request.getParameter("email");
			String dongYNhanMail = request.getParameter("dongYNhanMail");
			request.setAttribute("hoVaTen", hoVaTen);
			request.setAttribute("gioiTinh", gioiTinh);
			request.setAttribute("ngaySinh", ngaySinh);
			request.setAttribute("diaChiKhachHang", diaChiKhachHang);
			request.setAttribute("diaChiMuaHang", diaChiMuaHang);
			request.setAttribute("diaChiNhanHang", diaChiNhanHang);
			request.setAttribute("dienThoai", dienThoai);
			request.setAttribute("dongYNhanMail", dongYNhanMail);
			
			String url = "";
			
			String baoLoi = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();

			request.setAttribute("baoLoi", baoLoi);
			
			
			if(baoLoi.length()>0) {
				url = "/khachhang/dangky.jsp";
			}else {
				Object obj = request.getSession().getAttribute("khachHang");
				KhachHang khachHang = null;
				if (obj!=null)
					khachHang = (KhachHang)obj;		
					if(khachHang!=null){	
						String maKhachHang = khachHang.getMaKhachHang();
						KhachHang kh = new KhachHang(maKhachHang, "", "", hoVaTen, gioiTinh, diaChiKhachHang, diaChiNhanHang, diaChiMuaHang, Date.valueOf(ngaySinh), dienThoai, email, dongYNhanMail!=null);
						khachHangDAO.updateInfo(kh);
						KhachHang kh2 = khachHangDAO.selectById(kh);
						request.getSession().setAttribute("khachHang", kh2);
						url = "/khachhang/thanhcong.jsp";
					}
				
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
