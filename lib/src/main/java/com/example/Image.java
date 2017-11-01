package com.example;

import java.util.Scanner;

public class Image {

    private Pixel[][] image;
    public static final int MAX_COLOR = 255;
    public static final int MIN_COLOR = 0;
    private int height;
    private int width;
    public Image(int w, int h, Scanner sc){
        height = h;
        width = w;
        image = new Pixel[h][w];
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                image[i][j] = new Pixel(sc.nextInt(), sc.nextInt(), sc.nextInt());
            }
        }
    }
    public Pixel[][] getImage()
    {
        return image;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public Pixel[][] invert(){
        int new_red;
        int new_green;
        int new_blue;
        Pixel[][] result = new Pixel[height][width];
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                new_red = MAX_COLOR - image[i][j].getRed();
                new_green = MAX_COLOR - image[i][j].getGreen();
                new_blue = MAX_COLOR - image[i][j].getBlue();
                result[i][j] = new Pixel(new_red, new_green, new_blue);
            }
        }
        return result;
    }
    public Pixel[][] emboss(){
        int color;
        int red_diff;
        int green_diff;
        int blue_diff;
        int max_difference;
        Pixel[][] result = new Pixel[height][width];
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                if(i != 0 && j != 0)
                {
                    red_diff = image[i][j].getRed() - image[i-1][j-1].getRed();
                    green_diff = image[i][j].getGreen() - image[i-1][j-1].getGreen();
                    blue_diff = image[i][j].getBlue() - image[i-1][j-1].getBlue();
//
                    max_difference = red_diff;
                    if(Math.abs(max_difference) < Math.abs(green_diff))
                        max_difference = green_diff;
                    if(Math.abs(max_difference) < Math.abs(blue_diff))
                        max_difference = blue_diff;
                    color = max_difference + 128;
                    if(color > MAX_COLOR)
                        color = MAX_COLOR;
                    else if(color < MIN_COLOR)
                        color = MIN_COLOR;
//                    System.out.println("color: " + color);
                    result[i][j] = new Pixel(color, color, color);
                }
                else {
//                    System.out.println("line 87\n");
                    result[i][j] = new Pixel(128, 128, 128); //sets outside pixels to 128
                }
            }
        }
        return result;
    }
    public Pixel[][] greyScale(){
        int new_red;
        int new_green;
        int new_blue;
        int avg_color;
        Pixel[][] result = new Pixel[height][width];
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                avg_color = (image[i][j].getRed() + image[i][j].getGreen() + image[i][j].getBlue())/3;
                result[i][j] = new Pixel(avg_color, avg_color, avg_color);
            }
        }
        return result;
    }
    public Pixel[][] motionBlur(int blur){
        int new_red;
        int new_green;
        int new_blue;
        Pixel[][] result = new Pixel[height][width];
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
//                new_red = image[i][j].getRed();
//                new_green = image[i][j].getGreen();
//                new_blue = image[i][j].getBlue();
                new_red = 0;
                new_green = 0;
                new_blue = 0;
                int k;
                if(blur == 1)
                {
                    return image;
                }
               for(k = 0; k < blur; k++)
               {
                   if(j+k < width)
                   {
                       new_red = new_red + image[i][j + k].getRed();
                       new_green = new_green + image[i][j + k].getGreen();
                       new_blue = new_blue + image[i][j + k].getBlue();
                   }
                   else
                   {
                       break;
                   }
               }
                new_red = new_red/k;
                new_green = new_green/k;
                new_blue = new_blue/k;
                result[i][j] = new Pixel(new_red, new_green, new_blue);
            }
        }
        return result;
    }
}
