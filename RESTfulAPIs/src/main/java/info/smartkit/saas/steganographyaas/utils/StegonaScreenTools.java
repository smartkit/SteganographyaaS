package info.smartkit.saas.steganographyaas.utils;

//@see: https://github.com/DominicBreuker/stego-toolkit
public enum StegonaScreenTools {
    FILE("file"),//Check out what kind of file you have
    EXIFTOOL("exiftool"),
    BINWALK("binwalk"),
    STRINGS("strings"),
    FOREMOST("foremost"),
    PNGCHECK("pngcheck"),
    IDENTIFY("identify"),
    FFMPEG("ffmpeg");

    private String str;

    StegonaScreenTools(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
