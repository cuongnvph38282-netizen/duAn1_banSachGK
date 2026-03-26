
package model;

import java.math.BigDecimal;


public class Sach {
    private String maS;
    private String tenTieuDe;
    private int tap;
    private String moTa;
    private int maMH;
    private int maLop;
    private int maBoSach;
    private int soTrang;
    private String kichThuoc;
    private String hinhThuc;
    private int soLuong;
    private BigDecimal gia;
    private int namXuatBan;

    // Thêm 3 field tên để hiển thị
    private String tenMonHoc;
    private String tenLop;
    private String tenBoSach;

    public Sach() {
    }

    public Sach(String maS, String tenTieuDe, int tap, String moTa,
            int maMH, int maLop, int maBoSach,
            int soTrang, String kichThuoc, String hinhThuc,
            int soLuong, BigDecimal gia, int namXuatBan) {
                this.maS        = maS;
                this.tenTieuDe  = tenTieuDe;
                this.tap        = tap;
                this.moTa       = moTa;
                this.maMH       = maMH;
                this.maLop      = maLop;
                this.maBoSach   = maBoSach;
                this.soTrang    = soTrang;
                this.kichThuoc  = kichThuoc;
                this.hinhThuc   = hinhThuc;
                this.soLuong    = soLuong;
                this.gia        = gia;
                this.namXuatBan = namXuatBan;
            }

    public String getMaS() {
        return maS;
    }

    public void setMaS(String maS) {
        this.maS = maS;
    }

    public String getTenTieuDe() {
        return tenTieuDe;
    }

    public void setTenTieuDe(String tenTieuDe) {
        this.tenTieuDe = tenTieuDe;
    }

    public int getTap() {
        return tap;
    }

    public void setTap(int tap) {
        this.tap = tap;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getMaMH() {
        return maMH;
    }

    public void setMaMH(int maMH) {
        this.maMH = maMH;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public int getMaBoSach() {
        return maBoSach;
    }

    public void setMaBoSach(int maBoSach) {
        this.maBoSach = maBoSach;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getTenBoSach() {
        return tenBoSach;
    }

    public void setTenBoSach(String tenBoSach) {
        this.tenBoSach = tenBoSach;
    }

    @Override
    public String toString() {
        return "Sach{" + "maS=" + maS + ", tenTieuDe=" + tenTieuDe + ", tap=" + tap + ", moTa=" + moTa + ", maMH=" + maMH + ", maLop=" + maLop + ", maBoSach=" + maBoSach + ", soTrang=" + soTrang + ", kichThuoc=" + kichThuoc + ", hinhThuc=" + hinhThuc + ", soLuong=" + soLuong + ", gia=" + gia + ", namXuatBan=" + namXuatBan + ", tenMonHoc=" + tenMonHoc + ", tenLop=" + tenLop + ", tenBoSach=" + tenBoSach + '}';
    }
    
}