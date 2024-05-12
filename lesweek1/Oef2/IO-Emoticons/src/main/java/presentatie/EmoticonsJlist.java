package presentatie;

import data.Data;
import logica.Emoticon;
import logica.Mood;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ContainerAdapter;
import java.util.ArrayList;
import java.util.Objects;

public class EmoticonsJlist {


    private JList lijst;
    private JLabel result;
    private JPanel mainPAnel;

    private ArrayList<Emoticon> emoticons;

    public EmoticonsJlist() {
        emoticons = Data.leesData();

        emoticons.sort(null);
        lijst.setListData(emoticons.toArray());


        lijst.setSelectedIndex(0);


        setResult();


        lijst.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setResult();
            }
        });
    }

    private void setResult(){
        for (Emoticon emoticon :emoticons) {
            if (Objects.equals(lijst.getSelectedValue().toString(), emoticon.getNaam())){
                result.setText( emoticon.getCode());
                result.setIcon(emoticon.getAfbeelding() );
            }
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("EmoticonsJlist");
        frame.setContentPane(new EmoticonsJlist().mainPAnel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
