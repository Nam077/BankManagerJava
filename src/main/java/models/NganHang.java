package models;


import interfaces.NganHangInterface;
import lombok.*;

import java.util.ArrayList;
import java.util.Scanner;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NganHang implements NganHangInterface {
    public String id;
    public String tenNganHang;
    public ArrayList<NguoiDung> nguoiDungs;

    @Override
    public void themNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDungs.add(nguoiDung);
    }

    public NganHang(String id, String tenNganHang) {
        this.id = id;
        this.tenNganHang = tenNganHang;
    }

    @Override
    public double getTongTien() {
        double tongTien = 0.0;
        for (NguoiDung nguoiDung : this.nguoiDungs) {
            tongTien += nguoiDung.getSoDuTaiKhoan();
        }
        return tongTien;
    }

    void nhap() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id: ");
        this.id = scanner.nextLine();
        System.out.println("Nhập tên ngân hàng: ");
        this.tenNganHang = scanner.nextLine();
        this.nguoiDungs = new ArrayList<>();
    }

    public NguoiDung findNguoiDung(String idNguoiDung) {
        for (NguoiDung nguoiDung : this.nguoiDungs) {
            if (nguoiDung.getId().equals(idNguoiDung)) {
                return nguoiDung;
            }
        }
        return null;
    }

    public void editNguoiDung(NguoiDung nguoiDung) {
        int index = this.nguoiDungs.indexOf(nguoiDung);
        this.nguoiDungs.set(index, nguoiDung);
    }
}
