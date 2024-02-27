import java.util.HashMap;
import java.util.Scanner;

public class Kegiatan1 {
    static class Library {
        private HashMap<String, String> adminCredentials;
        private HashMap<String, String> mahasiswaCredentials;

        public Library() {
            adminCredentials = new HashMap<>();
            mahasiswaCredentials = new HashMap<>();

            adminCredentials.put("admin", "admin123");
            mahasiswaCredentials.put("202310370311186", "Nizar");
        }

        public void adminLogin(String username, String password) {
            if (adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password)) {
                System.out.println("Admin login berhasil");
            } else {
                System.out.println("Username atau password admin salah");
            }
        }

        public void mahasiswaLogin(String nim) {
            if (nim.length() == 15 && mahasiswaCredentials.containsKey(nim)) {
                String nama = mahasiswaCredentials.get(nim);
                System.out.println("Mahasiswa " + nama + " dengan NIM " + nim + " berhasil login");
            } else {
                System.out.println("NIM tidak valid atau tidak terdaftar");
            }
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Pilihan Login:");
            System.out.println("1. Admin");
            System.out.println("2. Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan username admin: ");
                    String adminUsername = scanner.nextLine();
                    System.out.print("Masukkan password admin: ");
                    String adminPassword = scanner.nextLine();
                    library.adminLogin(adminUsername, adminPassword);
                    break;
                case 2:
                    System.out.print("Masukkan NIM mahasiswa: ");
                    String nimMahasiswa = scanner.nextLine();
                    library.mahasiswaLogin(nimMahasiswa);
                    break;
                case 3:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        } while (choice != 3);

        scanner.close();
    }
}
