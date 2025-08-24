package com.kaedesbingo;


import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;


class Icons
{
    static final BufferedImage BINGO_24 = load("/kaedesbingo/bingo_24.png"); // TODO: put an icon at this path


    private static BufferedImage load(String path)
    {
        try { return ImageIO.read(Objects.requireNonNull(Icons.class.getResourceAsStream(path))); }
        catch (Exception e) { return new BufferedImage(24,24,BufferedImage.TYPE_INT_ARGB); }
    }
}