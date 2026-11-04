package tugasbesarpbo.util;

public class PasswordHashGenerator {

    public static void main(String[] args) {

        System.out.println("Admin: "+ PasswordUtil.hashPassword("Admin123!"));
        System.out.println("Cashier: "+ PasswordUtil.hashPassword("Cashier123!"));
        System.out.println("Technician: "+ PasswordUtil.hashPassword("Tech123!"));
    }
}
