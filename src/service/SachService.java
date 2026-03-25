package service;

import model.Sach;
import utils.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SachService {

    // 1. Lấy tất cả
    public List<Sach> getAll() {
        List<Sach> list = new ArrayList<>();
        String sql = "SELECT * FROM Sach";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapToSach(rs));
            }

        } catch (Exception e) {
            System.out.println("Lỗi getAll: " + e.getMessage());
        }
        return list;
    }

    // 2. Thêm
    public boolean insert(Sach s) {
        String sql = "INSERT INTO Sach (maS, tenTieuDe, tap, moTa, maMH, maLop, maBoSach, soTrang, kichThuoc, hinhThuc, soLuong, gia, namXuatBan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            setData(ps, s);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi insert: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 3. Sửa
    public boolean update(Sach s) {
        String sql = "UPDATE Sach SET tenTieuDe=?, tap=?, moTa=?, maMH=?, maLop=?, maBoSach=?, soTrang=?, kichThuoc=?, hinhThuc=?, soLuong=?, gia=?, namXuatBan=? WHERE maS=?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // set giống insert nhưng khác vị trí maS
            ps.setString(1, s.getTenTieuDe());
            ps.setInt(2, s.getTap());
            ps.setString(3, s.getMoTa());
            ps.setInt(4, s.getMaMH());
            ps.setInt(5, s.getMaLop());
            ps.setInt(6, s.getMaBoSach());
            ps.setInt(7, s.getSoTrang());
            ps.setString(8, s.getKichThuoc());
            ps.setString(9, s.getHinhThuc());
            ps.setInt(10, s.getSoLuong());
            ps.setBigDecimal(11, s.getGia());
            ps.setInt(12, s.getNamXuatBan());
            ps.setString(13, s.getMaS());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi update: " + e.getMessage());
            return false;
        }
    }

    // 4. Xóa
    public boolean delete(String maS) {
        String sql = "DELETE FROM Sach WHERE maS=?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maS);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("Không tìm thấy sách để xóa");
            }
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi delete: " + e.getMessage());
            return false;
        }
    }

    // 5. Tìm theo ID
    public Sach findById(String maS) {
        String sql = "SELECT * FROM Sach WHERE maS=?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maS);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapToSach(rs);
                }
            }

        } catch (Exception e) {
            System.out.println("Lỗi findById: " + e.getMessage());
        }
        return null;
    }

    // 6. Tìm theo tên
    public List<Sach> findByName(String ten) {
        List<Sach> list = new ArrayList<>();
        String sql = "SELECT * FROM Sach WHERE tenTieuDe LIKE ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + ten + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapToSach(rs));
                }
            }

        } catch (Exception e) {
            System.out.println("Lỗi findByName: " + e.getMessage());
        }
        return list;
    }

    // Mapping
    private Sach mapToSach(ResultSet rs) throws SQLException {
        return new Sach(
                rs.getString("maS"),
                rs.getString("tenTieuDe"),
                rs.getInt("tap"),
                rs.getString("moTa"),
                rs.getInt("maMH"),
                rs.getInt("maLop"),
                rs.getInt("maBoSach"),
                rs.getInt("soTrang"),
                rs.getString("kichThuoc"),
                rs.getString("hinhThuc"),
                rs.getInt("soLuong"),
                rs.getBigDecimal("gia"),
                rs.getInt("namXuatBan")
        );
    }

    // Set data dùng chung
    private void setData(PreparedStatement ps, Sach s) throws SQLException {
        ps.setString(1, s.getMaS());
        ps.setString(2, s.getTenTieuDe());
        ps.setInt(3, s.getTap());
        ps.setString(4, s.getMoTa());
        ps.setInt(5, s.getMaMH());
        ps.setInt(6, s.getMaLop());
        ps.setInt(7, s.getMaBoSach());
        ps.setInt(8, s.getSoTrang());
        ps.setString(9, s.getKichThuoc());
        ps.setString(10, s.getHinhThuc());
        ps.setInt(11, s.getSoLuong());
        ps.setBigDecimal(12, s.getGia());
        ps.setInt(13, s.getNamXuatBan());
    }
}