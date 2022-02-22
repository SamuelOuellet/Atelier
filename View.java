import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class View {
    JFrame frame;
    JList<Integer> listInt;
    JScrollPane scr;
    Integer[] tabInt = {1,2,3,4,5,6,7,8,9};
    JTextField txfMin, txfMax, txfMoyenne;
    JCheckBox chbMulti;
    JButton btnGo, btnSelection;

    final int WIDTH_TXF = 270;
    final int HEIGHT_TXF = 30;

    public View(){
        frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(new Dimension(600,270));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listInt = new JList<>(tabInt);
        listInt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listInt.addListSelectionListener(e -> listSelectionChange(e));
        listInt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
                    System.out.println(listInt.getSelectedValue());
            }
        });

        scr = new JScrollPane(listInt);

        txfMin = new JTextField("txfMin");
        txfMax = new JTextField("txfMax");
        txfMoyenne = new JTextField("txfMoyenne");

        btnGo = new JButton("GO");
        btnGo.addActionListener(e -> btnGoAction());
        
        btnSelection = new JButton("Sélectionner tout");
        btnSelection.addActionListener(e -> btnSelectionAction());

        chbMulti = new JCheckBox("Multisélection");
        chbMulti.addActionListener(e -> chbMultiAction());

        scr.setBounds(0,0,300,300);
        txfMin.setBounds(310,5,WIDTH_TXF,HEIGHT_TXF);
        txfMax.setBounds(310,40,WIDTH_TXF,HEIGHT_TXF);
        txfMoyenne.setBounds(310,80,WIDTH_TXF,HEIGHT_TXF);
        btnGo.setBounds(320,120,60,30);
        chbMulti.setBounds(320,155,150,30);
        btnSelection.setBounds(320,190,200,30);

        frame.add(scr);
        frame.add(txfMin);
        frame.add(txfMax);
        frame.add(txfMoyenne);
        frame.add(btnGo);
        frame.add(chbMulti);
        frame.add(btnSelection);

        frame.setVisible(true);

    }

    private void btnGoAction() {
        int[] tab = listInt.getSelectedIndices();
        int longeur = tab.length;
        int somme = 0;
        double moyenne = 0.0;

        for (int i=0; i<tab.length; i++){
            System.out.println(tabInt[tab[i]]);
            somme += tabInt[tab[i]];
        }
        System.out.println(somme);
        if (longeur != 0)
            moyenne = (double)somme / (double)longeur;

        //max
        int imax;
        int max = 0;

        for (int i = 0; i < tab.length-1; i++){

            imax = i;

            for (int j = i+1; j < tab.length; j++)
            if (tab[j] > tab[imax])
            imax = j;

            if (imax != i){
                max = imax;
            }
        }
        //min
        int imin;
        int min = 0;

        for (int i = 0; i <tab.length-1; i++){

            imin = i;

            for (int j = i+1; j <tab.length; j++)
            if (tab[j] < tab[imin])
            imin = j;

            if (imin != i){
                min = imin;
            }

        }

        txfMoyenne.setText(String.valueOf(moyenne));
        txfMax.setText(String.valueOf(max));
        txfMin.setText(String.valueOf(min));

    }

    private void listSelectionChange(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            System.out.println(listInt.getSelectedValue());
    }

    private void btnSelectionAction() {
        if (chbMulti.isSelected())
            listInt.setSelectionInterval(0,8);
    }

    private void chbMultiAction() {
        if (chbMulti.isSelected())
            listInt.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        else
            listInt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public static void main(String[] args) {
        View v = new View();
    }
}
