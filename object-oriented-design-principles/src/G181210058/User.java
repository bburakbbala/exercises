package G181210058;

public class User implements IUser{
    private String username;
    private String password;
    private String email;

    private User(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
    }

    @Override
    public void setUsername(String name) {
        username = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUserPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUserPassword() {
        return password;
    }

    public void setUserMail(String email) {
        this.email = email;
    }
    public String getUserMail() {
        return email;
    }

    public static class UserBuilder {
        private String username;
        private String password;
        private String email;

        public UserBuilder(String name, String password) {
            this.username = name;
            this.password = password;
        }

        public UserBuilder username(String name) {
            this.username = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}
