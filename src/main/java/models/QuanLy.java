package models;

import java.util.ArrayList;
import java.util.Scanner;

public class QuanLy {
    private ArrayList<NganHang> nganHangs;
    private static Scanner scanner = new Scanner(System.in);

    public QuanLy() {
        nganHangs = new ArrayList<>();
    }

    public static int showMainMenu() {
        System.out.println("\n---- Main Menu ----");
        System.out.println("1. Tạo ngân hàng");
        System.out.println("2. Tạo người dùng");
        System.out.println("3. Hiện thị thông tin ngân hàng");
        System.out.println("4. Hiện thị thông tin người dùng theo ngân hàng");
        System.out.println("5. Hiển thị tổng số tiền ngân hàng");
        System.out.println("6. Thực hiện giao dịch");
        System.out.println("7. Sắp xếp danh sách người dùng theo số dư");
        System.out.println("8. Hiển thị tất cả ngân hàng");
        System.out.println("0. Thoát");
        System.out.print("\nVui lòng nhập lựa chọn của bạn: ");
        return scanner.nextInt();
    }

    public void addNganHang(NganHang nganHang) {
        nganHangs.add(nganHang);
    }

    public ArrayList<NguoiDung> getNguoiDungs() {
        ArrayList<NguoiDung> nguoiDungs = new ArrayList<>();
        for (NganHang nganHang : nganHangs) {
            nganHang.getNguoiDungs().forEach(nguoiDung -> {
                nguoiDungs.add(nguoiDung);
            });
        }
        return nguoiDungs;
    }

    public NganHang findNganHang(String maNganHang) {
        for (NganHang nganHang : nganHangs) {
            if (nganHang.getId().equals(maNganHang)) {
                return nganHang;
            }
        }
        return null;
    }

    public void nhapNguoiDung() throws Exception {
        System.out.println("\n---- Tạo người dùng ----");
        System.out.print("Nhập id ngân hàng: ");
        scanner.nextLine();
        String maNganHang = scanner.nextLine();
        NganHang nganHang = findNganHang(maNganHang);
        if (nganHang == null) {
            System.out.println("Không tìm thấy ngân hàng");
            return;
        }
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.nhapThongTin();
        nguoiDung.setIdNganHang(maNganHang);
        int index = nganHangs.indexOf(nganHang);
        nganHang.nguoiDungs.add(nguoiDung);
        nganHang.nguoiDungs.set(index, nguoiDung);
    }

    public void hienThiThongTinNganHang() {
        System.out.println("\n---- Hiển thị thông tin ngân hàng ----");
        for (NganHang nganHang : nganHangs) {
            System.out.println(nganHang);
        }
    }

    public void hienThiThongTinNguoiDungTheoNganHang() {
        System.out.print("Nhập id ngân hàng: ");
        scanner.nextLine();
        String maNganHang = scanner.nextLine();
        NganHang nganHang = findNganHang(maNganHang);
        if (nganHang == null) {
            System.out.println("Không tìm thấy ngân hàng");
            return;
        }
        for (NguoiDung nguoiDung : nganHang.nguoiDungs) {
            System.out.println(nguoiDung);
        }
    }

    public void hienThiTongSoTienNganHang() {
        System.out.print("Nhập id ngân hàng: ");
        scanner.nextLine();
        String maNganHang = scanner.nextLine();
        NganHang nganHang = findNganHang(maNganHang);
        if (nganHang == null) {
            System.out.println("Không tìm thấy ngân hàng");
            return;
        }
        System.out.println(nganHang.getTongTien());
    }

    public void editNganHang(NganHang nganHang) {
        int index = nganHangs.indexOf(nganHang);
        nganHangs.set(index, nganHang);
    }

    public void sapXepDanhSachNguoiDungTheoSoDu() {
        System.out.println("\n---- Sắp xếp người dùng theo số dư ----");
        System.out.print("Nhập id ngân hàng: ");
        scanner.nextLine();
        String maNganHang = scanner.nextLine();
        NganHang nganHang = findNganHang(maNganHang);
        if (nganHang == null) {
            System.out.println("Không tìm thấy ngân hàng");
            return;
        }

        ArrayList<NguoiDung> nguoiDungs = nganHang.nguoiDungs;
        nguoiDungs.sort((o1, o2) -> o1.compareTo(o2));
        for (NguoiDung nguoiDung : nguoiDungs) {
            System.out.println(nguoiDung);
        }
    }

    public void thucHienGiaoDich() {
        System.out.println("\n---- Thực hiện giao dịch ----");
        System.out.print("Nhập id người dùng: ");
        scanner.nextLine();
        String idNguoiDung = scanner.nextLine();
        NguoiDung nguoiDung = findNguoiDung(idNguoiDung);
        if (nguoiDung == null) {
            System.out.println("Không tìm thấy người dùng");
            return;
        }
        int choice = nguoiDung.showMenu();
        switch (choice) {
            case 1:
                System.out.print("Nhập số tiền muốn nạp: ");
                int soTienNap = scanner.nextInt();
                nguoiDung.napTien(soTienNap);
                break;
            case 2:
                System.out.print("Nhập số tiền muốn chuyển: ");
                double soTienChuyen = scanner.nextDouble();
                System.out.print("Nhập id ngân hàng: ");
                String maNganHang = scanner.nextLine();
                NganHang nganHang = findNganHang(maNganHang);
                while (nganHang == null) {
                    System.out.println("Không tìm thấy ngân hàng");
                    System.out.print("Nhập id ngân hàng: ");
                    maNganHang = scanner.nextLine();
                    nganHang = findNganHang(maNganHang);
                }
                NguoiDung nguoiDung1 = nganHang.findNguoiDung(idNguoiDung);
                while (nguoiDung1 == null) {
                    System.out.println("Không tìm thấy người dùng");
                    System.out.print("Nhập id người dùng: ");
                    idNguoiDung = scanner.nextLine();
                    nguoiDung1 = findNguoiDung(idNguoiDung);
                }
                nguoiDung.chuyenTien(soTienChuyen, idNguoiDung, maNganHang);
                nguoiDung1.napTien(soTienChuyen);
                NganHang NganHangChuyen = this.findNganHang(nguoiDung.getIdNganHang());
                NganHang NganHangNap = this.findNganHang(nguoiDung1.getIdNganHang());
                NganHangChuyen.editNguoiDung(nguoiDung);
                NganHangNap.editNguoiDung(nguoiDung1);
                this.editNganHang(NganHangChuyen);
                this.editNganHang(NganHangNap);
                break;
            case 3:
                System.out.print("Nhập số tiền muốn rút: ");
                double soTienRut = scanner.nextDouble();
                nguoiDung.rutTien(soTienRut);
                NganHang NganHangRut = this.findNganHang(nguoiDung.getIdNganHang());
                NganHangRut.editNguoiDung(nguoiDung);
                this.editNganHang(NganHangRut);
                break;
            case 4:
        }

    }

    private NguoiDung findNguoiDung(String idNguoiDung) {
        for (NguoiDung nguoiDung : this.getNguoiDungs()) {
            if (nguoiDung.getId().equals(idNguoiDung)) {
                return nguoiDung;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        QuanLy quanLy = new QuanLy();
        int choice = -1;
        while (choice != 0) {
            choice = quanLy.showMainMenu();
            switch (choice) {
                case 1:
                    NganHang nganHang = new NganHang();
                    nganHang.nhap();
                    quanLy.addNganHang(nganHang);
                    break;
                case 2:
                    quanLy.nhapNguoiDung();
                    break;
                case 3:
                    quanLy.hienThiThongTinNganHang();
                    break;
                case 4:
                    quanLy.hienThiThongTinNguoiDungTheoNganHang();
                    break;
                case 5:
                    quanLy.hienThiTongSoTienNganHang();
                    break;
                case 6:
                    quanLy.thucHienGiaoDich();

                    break;

                case 7:
                    quanLy.sapXepDanhSachNguoiDungTheoSoDu();
                    break;
                case 8:
                    quanLy.hienThiDanhSachNganHang();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        }

    }

    private void hienThiDanhSachNganHang() {
        for (NganHang nganHang : this.nganHangs) {
            System.out.println(nganHang);
        }
    }

}
