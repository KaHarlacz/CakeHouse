package kharlacz.springapp.user.authentication;

import lombok.*;

import java.util.Base64;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class BasicAuthString {
    private String base64Str;
    
    private static final int USERNAME = 0;
    private static final int PASSWORD = 1;
    
    public String getUsername() {
        return getDecodedPart(USERNAME);
    }
    
    public String getPassword() {
        return getDecodedPart(PASSWORD);
    }
    
    private String getDecodedPart(int partIdx) {
        final var decodedBytes = Base64.getDecoder().decode(base64Str);
        final var decodedParts = new String(decodedBytes).split(":");
        return decodedParts[partIdx];
    }
}
