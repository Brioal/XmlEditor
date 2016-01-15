package brioal.panel;

import brioal.interfaces.UiCommon;
import brioal.uioverride.BlankButton;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by brioal on 15-10-10.
 * ������ʾ��������Ĳ��������ý���
 * ��Frame_Welcomed����
 */

public class Panel_Operator extends JPanel implements UiCommon {
    JButton btnNew, btnOpen, btnAbout;
    ActionListener listener;
    JFrame frame;

    public Panel_Operator(JFrame frame, ActionListener listener) {
        this.frame = frame;
        this.listener = listener;
        initComponent();
        setComponent();
        addComponent();
    }

    @Override
    public void initComponent() {

        btnNew = new BlankButton(new ImageIcon("drawable/new.png"), "      ��  �� ");
        btnOpen = new BlankButton(new ImageIcon("drawable/open.png"), "      ��  �� ");
        btnAbout = new BlankButton(new ImageIcon("drawable/auther.png"), "     ����");

    }

    @Override
    public void setComponent() {
        //���ò���
        setLayout(null);
        //���ô�С
        btnNew.setSize(300, 40);
        btnNew.setLocation(100, 35);

        btnOpen.setSize(300, 40);
        btnOpen.setLocation(100, 80);

        btnAbout.setSize(300, 40);
        btnAbout.setLocation(100, 125);

        //��������
        btnNew.setActionCommand("�½�");
        btnOpen.setActionCommand("��");
        btnAbout.setActionCommand("����");
        //�����¼�
        btnNew.addActionListener(listener);
        btnOpen.addActionListener(listener);
        btnAbout.addActionListener(listener);
    }

    @Override
    public void addComponent() {
        add(btnNew);
        add(btnOpen);
        add(btnAbout);
    }


}
