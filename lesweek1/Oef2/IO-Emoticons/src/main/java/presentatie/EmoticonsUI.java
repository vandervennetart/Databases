package presentatie;

import data.Data;
import logica.Emoticon;
import logica.Mood;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class EmoticonsUI {
    private JPanel panel1;
    private JComboBox dropDown;
    private JLabel result;
    private JPanel icon;

    private ArrayList<Emoticon> emoticons;


    public EmoticonsUI() {
        dropDown.setModel(new DefaultComboBoxModel(Mood.values()));

        emoticons = Data.leesData();
        setResult();

        dropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setResult();

            }
        });
    }

    private void setResult(){
        for (Emoticon emoticon :emoticons) {
            if (Objects.equals(Objects.requireNonNull(dropDown.getSelectedItem()).toString(), emoticon.getNaam())){
                result.setText( emoticon.getCode());
                result.setIcon(emoticon.getAfbeelding() );
            }
        }
    }
        public static void main(String[] args) {
        JFrame frame = new JFrame("EmoticonsUI");
        frame.setContentPane(new EmoticonsUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true);
    }
}
