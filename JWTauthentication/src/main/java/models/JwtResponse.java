package models;

public class JwtResponse {
    private String jwtToken;
    private String username;

    // Private constructor to enforce the use of the builder
    private JwtResponse(String jwtToken, String username) {
        this.jwtToken = jwtToken;
        this.username = username;
    }

    // Getters for the fields
    public String getJwtToken() {
        return jwtToken;
    }

    public String getUsername() {
        return username;
    }

    // Static Builder class
    public static class Builder {
        private String jwtToken;
        private String username;

        public Builder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this; // Return the builder for method chaining
        }

        public Builder username(String username) {
            this.username = username;
            return this; // Return the builder for method chaining
        }

        public JwtResponse build() {
            return new JwtResponse(jwtToken, username);
        }
    }

    // Static factory method to initiate the builder
    public static Builder builder() {
        return new Builder();
    }
}
