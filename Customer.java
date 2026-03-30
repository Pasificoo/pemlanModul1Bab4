class Customer {
    private String nama;
    private String noPelanggan;
    private double saldo;
    private String pin;
    private int cobaLogin;
    private boolean diblokir;

    public Customer(String nama, String noPelanggan, double saldo, String pin) {
        this.nama = nama;
        this.noPelanggan = noPelanggan;
        this.saldo = saldo;
        this.pin = pin;
        this.cobaLogin = 0;
        this.diblokir = false;
    }

    public boolean login(String inputPin) {
        if (diblokir) {
            System.out.println("Akun diblokir!");
            return false;
        }

        if (this.pin.equals(inputPin)) {
            cobaLogin = 0;
            return true;
        } else {
            cobaLogin++;
            System.out.println("PIN salah!");

            if (cobaLogin >= 3) {
                diblokir = true;
                System.out.println("Akun diblokir karena 3x salah.");
            }
            return false;
        }
    }

    public void topUp(double jumlah) {
        if (!diblokir) {
            saldo += jumlah;
            System.out.println("Top up berhasil. Saldo: " + saldo);
        }
    }

    public void pembelian(double jumlah) {
        if (diblokir) {
            System.out.println("Akun diblokir!");
            return;
        }

        double cashback = hitungCashback(jumlah);
        double total = jumlah - cashback;

        if ((saldo - total) < 10000) {
            System.out.println("Transaksi gagal! Saldo minimal tidak terpenuhi.");
        } else {
            saldo -= total;
            saldo += cashback;
            System.out.println("Transaksi berhasil!");
            System.out.println("Cashback: " + cashback);
            System.out.println("Saldo sekarang: " + saldo);
        }
    }

    private double hitungCashback(double jumlah) {
        String jenis = noPelanggan.substring(0, 2);

        if (jenis.equals("38")) { // Silver
            if (jumlah > 1000000) return jumlah * 0.05;
        } else if (jenis.equals("56")) { // Gold
            if (jumlah > 1000000) return jumlah * 0.07;
            else return jumlah * 0.02;
        } else if (jenis.equals("74")) { // Platinum
            if (jumlah > 1000000) return jumlah * 0.10;
            else return jumlah * 0.05;
        }

        return 0;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNama() {
        return nama;
    }
}