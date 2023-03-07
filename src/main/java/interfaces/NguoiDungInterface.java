package interfaces;

public interface NguoiDungInterface {
    void napTien(double tienNap);

    boolean chuyenTien(double tienChuyen, String idNguoiDung, String idNganHang);

    boolean rutTien(double tienRut);

    double kiemTraSoDu();

}
