package info.smartkit.saas.steganographyaas.utils;

public enum EnumTest {

    One("One"),
    Two("Two");

    private String str;

    EnumTest(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}