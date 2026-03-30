public class MainCustomer {
    public static void main(String[] args) {

        Customer c1 = new Customer("Ryan", "5612345678", 2000000, "1234");

        if (c1.login("1234")) {

            c1.topUp(500000);

            c1.pembelian(1200000);
        }

        // TEST BLOKIR
        c1.login("0000");
        c1.login("0000");
        c1.login("0000");
        c1.login("1234");
    }
}