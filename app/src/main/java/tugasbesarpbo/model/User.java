
package tugasbesarpbo.model;

public class User {

    private long userId;
    private String fullName;
    private String username;
    private String password;
    private Role role;
    private String phone;
    private boolean active;

    public User(long userId, String fullName, String username,
                String password, Role role, String phone,
                boolean active) {
        this.userId = userId;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.active = active;
    }

    public long getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return active;
    }
}
