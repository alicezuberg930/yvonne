package server.rem.enums;

public enum JWTAlgorithm {
    HS256("HmacSHA256"),
    HS384("HmacSHA384"),
    HS512("HmacSHA512");

    private final String hmacAlgorithm;

    JWTAlgorithm(String hmacAlgorithm) {
        this.hmacAlgorithm = hmacAlgorithm;
    }

    public String getHmacAlgorithm() {
        return hmacAlgorithm;
    }
}

