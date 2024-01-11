package database;

import java.util.ArrayList;

import model.ChiTietDonHang;
import model.DonHang;

public class DonHangDAO implements DAOInterface<DonHang> {
	public ArrayList<DonHang> data = new ArrayList<>();
	
	@Override
	public ArrayList<DonHang> selectAll() {
		return this.data;
	}

	@Override
	public DonHang selectById(DonHang t) {
		for (DonHang DonHang : data) {
			if(DonHang.equals(t)) {
				return DonHang;
			}
		}
		// khong co giong thi return null
		return null;
	}

	@Override
	public int insert(DonHang t) {
		if(this.selectById(t) == null) {
			this.data.add(t);
			return 1;
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<DonHang> arr) {
		int count=0;
		for (DonHang DonHang : arr) {
			count += this.insert(DonHang);
		}
		return count;
	}

	@Override
	public int delete(DonHang t) {
		if(this.selectById(t) != null) {
			ChiTietDonHangDAO ctdh = new ChiTietDonHangDAO();
			ctdh.deleteAll(t);
			this.data.remove(t);
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<DonHang> arr) {
		int count = 0;
		for (DonHang DonHang : arr) {
			count += this.insert(DonHang);
		}
		return count;
	}

	@Override
	public int update(DonHang t) {
		if(this.selectById(t) != null) {
			this.data.remove(t);
			this.data.add(t);
			return 1;
		}
		return 0;
	}
	
	
}
