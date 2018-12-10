package info.smartkit.saas.steganographyaas.utils;

//@see: https://github.com/DominicBreuker/stego-toolkit
public enum StegonaDetectTools {
    STEGO_VERITAS("stegoVeritas"),//Images (JPG, PNG, GIF, TIFF, BMP)
    ZSTEG("zsteg"),//Images (PNG, BMP)
    STEG_DETECT("stegdetect"),//Images (JPG)
    STEG_BREAK("stegbreak");//Images (JPG)

    private String str;

    StegonaDetectTools(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return str;
    }
}
