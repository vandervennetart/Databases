package data;

import logica.Emoticon;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Data {
    public static ArrayList<Emoticon> leesData(){
        ArrayList<Emoticon> emoticons = new ArrayList<>();

        try {
            File test = new File("./src/main/resources/emoticoncodes.txt");

            Scanner sc = new Scanner(test );
            while (sc.hasNextLine()){
                String[] info = sc.nextLine().split(" ");
                emoticons.add(new Emoticon(info[0], info[info.length-1]));
                emoticons.getLast().setAfbeelding(new ImageIcon("src/main/resources/emoticons/"+ emoticons.getLast().getNaam() +".png"));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return emoticons;
    }
}
