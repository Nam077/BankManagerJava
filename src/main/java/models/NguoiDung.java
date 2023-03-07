package models;

import interfaces.NguoiDungInterface;
import lombok.*;

import java.util.Scanner;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NguoiDung implements NguoiDungInterface, Comparable<NguoiDung> {
    private String id;
    private String tenNguoiDung;
    private String namSinh;
    private String idNganHang;
    private String soTaiKhoan;
    private Double soDuTaiKhoan;
    private String diaChi;
    private boolean khachHangThanThien;

    boolean isKhanhHangThanThien() {
        this.khachHangThanThien = this.soDuTaiKhoan > 100000 ? true : false;
        return this.khachHangThanThien;
    }

    @Override
    public void napTien(double tienNap) {
        this.soDuTaiKhoan += tienNap;
        System.out.println("Người dùng " + this.tenNguoiDung + " nạp tiền " + tienNap + " vào tài khoản " + this.soDuTaiKhoan);
    }

    @Override
    public boolean chuyenTien(double tienChuyen, String idNguoiDung, String idNganHang) {
        if (this.soDuTaiKhoan < tienChuyen) {
            System.out.println("Số dư tài khoản của người dùng " + this.tenNguoiDung + " không đủ để chuyển tiền");
            return false;
        }
        if (isKhanhHangThanThien()) {
            this.soDuTaiKhoan -= tienChuyen;
            System.out.println("Người dùng " + this.tenNguoiDung + " chuyển tiền " + tienChuyen + " vào tài khoản " + this.soDuTaiKhoan);
        } else {
            this.soDuTaiKhoan -= tienChuyen + 2000;
            System.out.println("Người dùng " + this.tenNguoiDung + " chuyển tiền " + tienChuyen + " vào tài khoản " + this.soDuTaiKhoan);
        }
        return true;
    }

    @Override
    public boolean rutTien(double tienRut) {
        if (this.soDuTaiKhoan < tienRut) {
            System.out.println("Số dư tài khoản của người dùng " + this.tenNguoiDung + " không đủ để rút tiền");
            return false;
        }
        this.soDuTaiKhoan -= tienRut;
        System.out.println("Người dùng " + this.tenNguoiDung + " rút tiền " + tienRut + " vào tài khoản " + this.soDuTaiKhoan);
        return true;
    }

    @Override
    public double kiemTraSoDu() {
        return this.soDuTaiKhoan;
    }

    @Override
    public int compareTo(NguoiDung o) {

        if (this.soDuTaiKhoan > o.soDuTaiKhoan) {
            return 1;
        } else if (this.soDuTaiKhoan < o.soDuTaiKhoan) {
            return -1;
        } else {
            return 0;
        }
    }

    void nhap() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập id người dùng: ");
        this.id = scanner.nextLine();
        System.out.print("Nhập tên người dùng: ");
        this.tenNguoiDung = scanner.nextLine();
        System.out.print("Nhập năm sinh: ");
        this.namSinh = scanner.nextLine();
        System.out.print("Nhập id ngân hàng: ");
        this.idNganHang = scanner.nextLine();
        System.out.print("Nhập số tài khoản: ");
        this.soTaiKhoan = scanner.nextLine();
        System.out.print("Nhập số dư tài khoản: ");
        this.soDuTaiKhoan = scanner.nextDouble();
        System.out.print("Nhập địa chỉ: ");
        scanner.nextLine();
        this.diaChi = scanner.nextLine();

    }

    void nhapThongTin() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập id người dùng: ");
        this.id = scanner.nextLine();
        System.out.print("Nhập tên người dùng: ");
        this.tenNguoiDung = scanner.nextLine();
        System.out.print("Nhập năm sinh: ");
        this.namSinh = scanner.nextLine();
        System.out.print("Nhập số tài khoản: ");
        this.soTaiKhoan = scanner.nextLine();
        System.out.print("Nhập số dư tài khoản: ");
        this.soDuTaiKhoan = scanner.nextDouble();
        System.out.print("Nhập địa chỉ: ");
        scanner.nextLine();
        this.diaChi = scanner.nextLine();

    }

    public int showMenu() {
        System.out.println("1. Nạp tiền");
        System.out.println("2. Chuyển tiền");
        System.out.println("3. Rút tiền");
        System.out.println("4. Kiểm tra số dư tài khoản");
        System.out.println("5. Thoát");
        System.out.print("Nhập lựa chọn: ");
        Scanner scanner = new Scanner(System.in);
        int luaChon = scanner.nextInt();
        return luaChon;
    }

}
