package service;

import model.Sach;
import utils.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SachService {

    // 1. Lấy tất cả (kèm tên môn học, lớp, bộ sách)
    public List<Sach> getAll() {
        List<Sach> list = new ArrayList<>();
        String sql = """
                SELECT s.maS, s.tenTieuDe, s.tap, s.moTa,
                       s.maMH, mh.tenMonHoc,
                       s.maLop, l.tenLop,
                       s.maBoSach, bs.tenBoSach,
                       s.soTrang, s.kichThuoc, s.hinhThuc,
                       s.soLuong, s.gia, s.namXuatBan
                FROM Sach s
                JOIN MonHoc mh ON s.maMH     = mh.maMH
                JOIN Lop    l  ON s.maLop    = l.maLop
                JOIN BoSach bs ON s.maBoSach = bs.maBoSach
                """;

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
        String sql = """
                INSERT INTO Sach 
                    (maS, tenTieuDe, tap, moTa, maMH, maLop, maBoSach,
                     soTrang, kichThuoc, hinhThuc, soLuong, gia, namXuatBan)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

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
        String sql = """
                UPDATE Sach 
                SET tenTieuDe=?, tap=?, moTa=?, maMH=?, maLop=?, maBoSach=?,
                    soTrang=?, kichThuoc=?, hinhThuc=?, soLuong=?, gia=?, namXuatBan=?
                WHERE maS=?
                """;

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1,     s.getTenTieuDe());
            ps.setInt(2,        s.getTap());
            ps.setString(3,     s.getMoTa());
            ps.setInt(4,        s.getMaMH());
            ps.setInt(5,        s.getMaLop());
            ps.setInt(6,        s.getMaBoSach());
            ps.setInt(7,        s.getSoTrang());
            ps.setString(8,     s.getKichThuoc());
            ps.setString(9,     s.getHinhThuc());
            ps.setInt(10,       s.getSoLuong());
            ps.setBigDecimal(11,s.getGia());
            ps.setInt(12,       s.getNamXuatBan());
            ps.setString(13,    s.getMaS()); // WHERE maS=?

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

    // 5. Tìm theo mã (kèm tên môn học, lớp, bộ sách)
    public Sach findById(String maS) {
        String sql = """
                SELECT s.maS, s.tenTieuDe, s.tap, s.moTa,
                       s.maMH, mh.tenMonHoc,
                       s.maLop, l.tenLop,
                       s.maBoSach, bs.tenBoSach,
                       s.soTrang, s.kichThuoc, s.hinhThuc,
                       s.soLuong, s.gia, s.namXuatBan
                FROM Sach s
                JOIN MonHoc mh ON s.maMH     = mh.maMH
                JOIN Lop    l  ON s.maLop    = l.maLop
                JOIN BoSach bs ON s.maBoSach = bs.maBoSach
                WHERE s.maS = ?
                """;

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

    // 6. Tìm theo tên (kèm tên môn học, lớp, bộ sách)
    public List<Sach> findByName(String ten) {
        List<Sach> list = new ArrayList<>();
        String sql = """
                SELECT s.maS, s.tenTieuDe, s.tap, s.moTa,
                       s.maMH, mh.tenMonHoc,
                       s.maLop, l.tenLop,
                       s.maBoSach, bs.tenBoSach,
                       s.soTrang, s.kichThuoc, s.hinhThuc,
                       s.soLuong, s.gia, s.namXuatBan
                FROM Sach s
                JOIN MonHoc mh ON s.maMH     = mh.maMH
                JOIN Lop    l  ON s.maLop    = l.maLop
                JOIN BoSach bs ON s.maBoSach = bs.maBoSach
                WHERE s.tenTieuDe LIKE ?
                """;

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

    // ===== PRIVATE HELPERS =====

    // Mapping ResultSet -> Sach (dùng constructor đầy đủ kèm tên)
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

// Mapping khi query có JOIN - dùng cho getAll/findById/findByName
private Sach mapToSachWithNames(ResultSet rs) throws SQLException {
    Sach s = mapToSach(rs); // dùng constructor gốc 13 tham số
    s.setTenMonHoc(rs.getString("tenMonHoc"));
    s.setTenLop(rs.getString("tenLop"));
    s.setTenBoSach(rs.getString("tenBoSach"));
    return s;
}

    // Set data cho insert (chỉ dùng mã số, không cần tên)
    private void setData(PreparedStatement ps, Sach s) throws SQLException {
        ps.setString(1,     s.getMaS());
        ps.setString(2,     s.getTenTieuDe());
        ps.setInt(3,        s.getTap());
        ps.setString(4,     s.getMoTa());
        ps.setInt(5,        s.getMaMH());
        ps.setInt(6,        s.getMaLop());
        ps.setInt(7,        s.getMaBoSach());
        ps.setInt(8,        s.getSoTrang());
        ps.setString(9,     s.getKichThuoc());
        ps.setString(10,    s.getHinhThuc());
        ps.setInt(11,       s.getSoLuong());
        ps.setBigDecimal(12,s.getGia());
        ps.setInt(13,       s.getNamXuatBan());
    }
}