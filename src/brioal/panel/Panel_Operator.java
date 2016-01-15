package brioal.panel;

import brioal.interfaces.UiCommon;
import brioal.uioverride.BlankButton;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by brioal on 15-10-10.
 * 用于显示引导界面的操作和设置界面
 * 被Frame_Welcomed调用
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

        btnNew = new BlankButton(new ImageIcon("drawable/new.png"), "      新  建 ");
        btnOpen = new BlankButton(new ImageIcon("drawable/open.png"), "      打  开 ");
        btnAbout = new BlankButton(new ImageIcon("drawable/auther.png"), "     关于");

    }

    @Override
    public void setComponent() {
        //设置布局
        setLayout(null);
        //设置大小
        btnNew.setSize(300, 40);
        btnNew.setLocation(100, 35);

        btnOpen.setSize(300, 40);
        btnOpen.setLocation(100, 80);

        btnAbout.setSize(300, 40);
        btnAbout.setLocation(100, 125);

        //设置命令
        btnNew.setActionCommand("新建");
        btnOpen.setActionCommand("打开");
        btnAbout.setActionCommand("关于");
        //设置事件
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
