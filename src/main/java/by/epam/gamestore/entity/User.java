package by.epam.gamestore.entity;

public class User extends AbstractEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private UserRole role;
    private boolean userStatus;
    private boolean archived;

    public User() {
    }

    public User(long id, String firstName, String lastName, String email, String login, String password, UserRole role, boolean userStatus, boolean archived) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
        this.archived = archived;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role).append('\'');
        sb.append(", userStatus=").append(userStatus).append('\'');
        sb.append(", archived=").append(archived).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class UserBuilder {
        private long id;
        private UserRole role;
        private String login;
        private String password;
        private String firstName;
        private String lastName;
        private boolean status;
        private boolean archived;
        private String email;


        public UserBuilder() {

        }

        public UserBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public UserBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public UserBuilder setRole(UserRole role) {
            this.role = role;
            return this;
        }

        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setArchived(boolean archived) {
            this.archived = archived;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User buildUser() {
            return new User(id, firstName, lastName, email, login, password, role, status, archived);
        }
    }
}
