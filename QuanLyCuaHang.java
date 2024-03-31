package quanLycuahang;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class QuanLyCuaHang {

    static class SanPham {
        private String maSanPham;
        private String tenSanPham;
        private String loai;
        private int soLuong;
        private double gia;

        public SanPham(String maSanPham, String tenSanPham, String loai, int soLuong, double gia) {
            this.maSanPham = maSanPham;
            this.tenSanPham = tenSanPham;
            this.loai = loai;
            this.soLuong = soLuong;
            this.gia = gia;
        }

        public String getMaSanPham() {
            return maSanPham;
        }

        public String getTenSanPham() {
            return tenSanPham;
        }

        public String getLoai() {
            return loai;
        }

        public int getSoLuong() {
            return soLuong;
        }

        public double getGia() {
            return gia;
        }
    }

    static class CuaHang {
        private ArrayList<SanPham> danhSachSanPham;

        public CuaHang() {
            danhSachSanPham = new ArrayList<>();
        }

        public void nhapSanPham() {
            Scanner scanner = new Scanner(System.in);
            char tiepTucNhap;
            do {
            	System.out.println("====================");
                System.out.print("Nhập mã sản phẩm: ");
                String maSanPham = scanner.nextLine();
                System.out.print("Nhập tên sản phẩm: ");
                String tenSanPham = scanner.nextLine();
                System.out.print("Nhập loại sản phẩm: ");
                String loai = scanner.nextLine();
                System.out.print("Nhập số lượng: ");
                int soLuong = scanner.nextInt();
                System.out.print("Nhập giá sản phẩm: ");
                double gia = scanner.nextDouble();
                scanner.nextLine(); 

                SanPham sp = new SanPham(maSanPham, tenSanPham, loai, soLuong, gia);
                danhSachSanPham.add(sp);

                System.out.print("Bạn muốn nhập sản phẩm khác không? (C/K): ");
                tiepTucNhap = scanner.nextLine().charAt(0);
            } while (Character.toUpperCase(tiepTucNhap) == 'C');

            System.out.println("Đã thêm sản phẩm vào cửa hàng!");
        }


        public void hienThiSanPham() {
            System.out.println("===== DANH SÁCH SẢN PHẨM TRONG CỬA HÀNG =====");
            for (SanPham sp : danhSachSanPham) {
                System.out.println("Mã sản phẩm: " + sp.getMaSanPham());
                System.out.println("Tên sản phẩm: " + sp.getTenSanPham());
                System.out.println("Loại: " + sp.getLoai());
                System.out.println("Số lượng: " + sp.getSoLuong());
                System.out.println("Giá: " + sp.getGia());
                System.out.println("===========================================");
            }
        }

        public void timKiemSanPham() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập thông tin tìm kiếm (mã sản phẩm hoặc tên sản phẩm): ");
            String timKiem = scanner.nextLine();
            boolean found = false;
            for (SanPham sp : danhSachSanPham) {
                if (sp.getMaSanPham().equalsIgnoreCase(timKiem) || sp.getTenSanPham().toLowerCase().contains(timKiem.toLowerCase())) {
                    System.out.println("===== KẾT QUẢ TÌM KIẾM =====");
                    System.out.println("Mã sản phẩm: " + sp.getMaSanPham());
                    System.out.println("Tên sản phẩm: " + sp.getTenSanPham());
                    System.out.println("Loại: " + sp.getLoai());
                    System.out.println("Số lượng: " + sp.getSoLuong());
                    System.out.println("Giá: " + sp.getGia());
                    System.out.println("=============================");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Không tìm thấy sản phẩm!");
            }
        }

        public void xoaSanPham() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập mã sản phẩm cần xóa: ");
            String maSanPham = scanner.nextLine();

            SanPham sanPhamCanXoa = null;
            for (SanPham sp : danhSachSanPham) {
                if (sp.getMaSanPham().equals(maSanPham)) {
                    sanPhamCanXoa = sp;
                    break;
                }
            }

            if (sanPhamCanXoa == null) {
                System.out.println("Không tìm thấy sản phẩm trong cửa hàng!");
                return;
            }

            danhSachSanPham.remove(sanPhamCanXoa);

            System.out.println("Đã xóa sản phẩm thành công!");
        }

        public void sapXepTheoGia() {
            Collections.sort(danhSachSanPham, new Comparator<SanPham>() {
                @Override
                public int compare(SanPham sp1, SanPham sp2) {
                    return Double.compare(sp1.getGia(), sp2.getGia());
                }
            });

            System.out.println("===== DANH SÁCH SẢN PHẨM THEO GIÁ TĂNG DẦN =====");
            for (SanPham sp : danhSachSanPham) {
                System.out.println("Mã sản phẩm: " + sp.getMaSanPham());
                System.out.println("Tên sản phẩm: " + sp.getTenSanPham());
                System.out.println("Loại: " + sp.getLoai());
                System.out.println("Số lượng: " + sp.getSoLuong());
                System.out.println("Giá: " + sp.getGia());
                System.out.println("===========================================");
            }
        }

        public void xuatSanPhamRaFile() {
            try {
                FileWriter writer = new FileWriter("danh_sach_san_pham.txt");
                for (SanPham sp : danhSachSanPham) {
                    writer.write("Mã sản phẩm: " + sp.getMaSanPham() + "\n");
                    writer.write("Tên sản phẩm: " + sp.getTenSanPham() + "\n");
                    writer.write("Loại: " + sp.getLoai() + "\n");
                    writer.write("Số lượng: " + sp.getSoLuong() + "\n");
                    writer.write("Giá: " + sp.getGia() + "\n");
                    writer.write("===========================================" + "\n");
                }
                writer.close();
                System.out.println("Đã xuất danh sách sản phẩm ra file thành công!");
            } catch (IOException e) {
                System.out.println("Đã xảy ra lỗi khi xuất file: " + e.getMessage());
            }
        }

        public void docSanPhamTuFile() {
            try {
                File file = new File("danh_sach_san_pham.txt");
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String maSanPham = scanner.nextLine().replace("Mã sản phẩm: ", "");
                    String tenSanPham = scanner.nextLine().replace("Tên sản phẩm: ", "");
                    String loai = scanner.nextLine().replace("Loại: ", "");
                    int soLuong = Integer.parseInt(scanner.nextLine().replace("Số lượng: ", ""));
                    double gia = Double.parseDouble(scanner.nextLine().replace("Giá: ", ""));
                    scanner.nextLine(); // Đọc dòng trống

                    SanPham sp = new SanPham(maSanPham, tenSanPham, loai, soLuong, gia);
                    danhSachSanPham.add(sp);
                }
                System.out.println("Đã đọc danh sách sản phẩm từ file thành công!");
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Không tìm thấy file danh_sach_san_pham.txt!");
            }
        }
    }

    public static void main(String[] args) {
        CuaHang cuaHang = new CuaHang();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== MENU QUẢN LÝ CỬA HÀNG =====");
            System.out.println("1. Nhập sản phẩm mới");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Tìm kiếm sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Sắp xếp sản phẩm theo giá");
            System.out.println("6. Xuất danh sách sản phẩm ra file");
            System.out.println("7. Đọc danh sách sản phẩm từ file");
            System.out.println("0. Thoát chương trình");
            System.out.print("Vui lòng chọn chức năng: ");
            int chon = scanner.nextInt();
            scanner.nextLine();

            switch (chon) {
                case 1:
                    cuaHang.nhapSanPham();
                    break;
                case 2:
                    cuaHang.hienThiSanPham();
                    break;
                case 3:
                    cuaHang.timKiemSanPham();
                    break;
                case 4:
                    cuaHang.xoaSanPham();
                    break;
                case 5:
                    cuaHang.sapXepTheoGia();
                    break;
                case 6:
                    cuaHang.xuatSanPhamRaFile();
                    break;
                case 7:
                    cuaHang.docSanPhamTuFile();
                    break;
                case 0:
                    System.out.println("Chương trình kết thúc!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }

            System.out.println();
        }
    }
}
