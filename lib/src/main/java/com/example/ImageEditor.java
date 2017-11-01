package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter  ;
import java.util.Scanner;

public class ImageEditor {
    public static void main(String[] args){
        Scanner sc = null;
        try {
            sc = new Scanner(new File(args[0])).useDelimiter("((#[^\\n]*\\n)|(\\s+))+");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String header = sc.next();
        int width = sc.nextInt();
        int height = sc.nextInt();
        int max = sc.nextInt();
        Image original = new Image(width, height, sc);
        Pixel[][] result;
        String transformation = args[2];
        if(transformation.equals("invert"))
        {
            result = original.invert();
        }
        else if(transformation.equals("emboss"))
        {
            result = original.emboss();
        }
        else if(transformation.equals("grayscale"))
        {
            result = original.greyScale();
        }
        else if(transformation.equals("motionblur"))
        {
            int blur = Integer.parseInt(args[3]);
            result = original.motionBlur(blur);
        }
        else
        {
            result = original.getImage();
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(args[1]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.println(header);
        pw.println(width + " " + height);
        pw.println(max);
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                pw.println(result[i][j].getRed());
                pw.println(result[i][j].getGreen());
                pw.println(result[i][j].getBlue());
            }
        }
        pw.close();
        sc.close();
    }

}
