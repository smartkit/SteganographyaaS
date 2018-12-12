package info.smartkit.saas.steganographyaas.service;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

public interface StegoExposeService {
    //
    double rsa_analysis(BufferedImage image, int color, boolean overlap);
    //
    double sa_analysis(BufferedImage image, int color );
    //
    double rso_analysis(BufferedImage image, int color, boolean overlap);
}
