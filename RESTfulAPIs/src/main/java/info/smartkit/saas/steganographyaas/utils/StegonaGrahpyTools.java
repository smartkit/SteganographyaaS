package info.smartkit.saas.steganographyaas.utils;

//@see: https://github.com/DominicBreuker/stego-toolkit
public enum StegonaGrahpyTools {
    AUDIO_STEGO("audiostego"),//Audio (MP3 / WAV)
    JPHIDE("jphide"),//Image (JPG)
    JPSEEK("jpseek"),//Image (JPG)
    JSTEG("jsteg"),//Image (JPG)
    MP3STEGO("mp3stego"),//Audio (MP3)
    OPENSTEGO("openstego"),//Images (PNG)
    OUTGUESS("outguess"),//Images (JPG)
    SPECTROLOGY("spectrology"),//Audio (WAV)
    STEGANO("stegano"),//Images (PNG)
    STEGHIDE("steghide"),//Images (JPG, BMP) and Audio (WAV, AU)
    CLOACKEDPIXEL("cloackedpixel"),//Images (PNG)
    LSBSTEG("LSBSteg"),//Images (PNG, BMP, ...) in uncompressed formats
    F5("f5");//Images (JPG)


    private String str;

    StegonaGrahpyTools(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
