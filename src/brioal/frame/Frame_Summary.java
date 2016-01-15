package brioal.frame;

import brioal.interfaces.UiCommon;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Brioal on 2015/12/30.
 * ��ʾ�ܽᴰ�� , ��Frame_Main��ʹ���ֲ��¼�����
 */
public class Frame_Summary extends JFrame implements UiCommon {
    private JPanel panel;
    private JScrollPane scrollPane;

    public Frame_Summary() {
        super("�ܽ�");
        initComponent();
        setComponent();
        addComponent();
        setSize(1366, 4496);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Frame_Summary();
    }

    @Override
    public void initComponent() {
        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                g.drawImage(new ImageIcon("drawable/sumary.png").getImage(), 0, 0, 1366, 2648, null);

            }
        };

        panel.setPreferredSize(new Dimension(1366, 2648));
        scrollPane = new JScrollPane(panel);
        setContentPane(scrollPane);
    }

    @Override
    public void setComponent() {

    }

    @Override
    public void addComponent() {

    }
}
