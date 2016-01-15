package brioal.panel;

import brioal.interfaces.UiCommon;
import brioal.uioverride.BlankButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by brioal on 15-10-11.
 * 用于显示主界面上方的快捷工具栏
 * 被Panel_Main调用
 */

public class Panel_ToolBar extends JPanel implements UiCommon {
    JButton
            btn1,
            btn2,
            btn3,
            btn4,
            btn5,
            btn6,
            btn7,
            btn8,
            btn9,
            btn10,
            btn11,
            btn12,
            btn13,
            btn14;
    private ActionListener listener;

    public Panel_ToolBar(ActionListener listener) {
        this.listener = listener;
        setLayout(null);
        initComponent();
        setComponent();
        addComponent();
    }

    @Override
    public void initComponent() {
        btn1 = new BlankButton(new ImageIcon("drawable/new.png"), "");
        btn2 = new BlankButton(new ImageIcon("drawable/open.png"), "");
        btn3 = new BlankButton(new ImageIcon("drawable/save.png"), "");
        btn4 = new BlankButton(new ImageIcon("drawable/saveas.png"), "");
        btn5 = new BlankButton(new ImageIcon("drawable/close.png"), "");
        btn6 = new BlankButton(new ImageIcon("drawable/sum.png"), "");
        btn7 = new BlankButton(new ImageIcon("drawable/average.png"), "");
        btn8 = new BlankButton(new ImageIcon("drawable/max.png"), "");
        btn9 = new BlankButton(new ImageIcon("drawable/min.png"), "");
        btn10 = new BlankButton(new ImageIcon("drawable/addrow.png"), "");
        btn11 = new BlankButton(new ImageIcon("drawable/deleterow.png"), "");
        btn12 = new BlankButton(new ImageIcon("drawable/copy.png"), "");
        btn13 = new BlankButton(new ImageIcon("drawable/cut.png"), "");
        btn14 = new BlankButton(new ImageIcon("drawable/paste.png"), "");

        btn1.setActionCommand("新建");
		btn1.setToolTipText("新建");
		 
        btn2.setActionCommand("打开");
        btn2.setToolTipText("打开");
		btn3.setActionCommand("保存");
		btn3.setToolTipText("保存");
		
        btn4.setActionCommand("另存为");
        btn4.setToolTipText("另存为");
		
		btn5.setActionCommand("关闭");
        btn5.setToolTipText("关闭");
		
		btn6.setActionCommand("求和");
		btn6.setToolTipText("求和");
		
        btn7.setActionCommand("求平均");
        btn7.setToolTipText("求平均");
		
		
		btn8.setActionCommand("求最大值");
        btn8.setToolTipText("求最大值");
		
		
		btn9.setActionCommand("求最小值");
		btn9.setToolTipText("求最小值");
		
        btn10.setActionCommand("添加行");
		btn10.setToolTipText("添加行");
		
        btn11.setActionCommand("删除行");
        btn11.setToolTipText("删除行");
		
		btn12.setActionCommand("复制");
        btn12.setToolTipText("复制");
		
		btn13.setActionCommand("剪切");
        btn13.setToolTipText("剪切");
		
		btn14.setActionCommand("粘贴");
		btn14.setToolTipText("粘贴");
    }

    @Override
    public void setComponent() {
        setBackground(Color.WHITE);
        btn1.addActionListener(listener);
        btn2.addActionListener(listener);
        btn3.addActionListener(listener);
        btn4.addActionListener(listener);
        btn5.addActionListener(listener);
        btn6.addActionListener(listener);
        btn7.addActionListener(listener);
        btn8.addActionListener(listener);
        btn9.addActionListener(listener);
        btn10.addActionListener(listener);
        btn11.addActionListener(listener);
        btn12.addActionListener(listener);
        btn13.addActionListener(listener);
        btn14.addActionListener(listener);
    }

    @Override
    public void addComponent() {
        btn1.setSize(35, 35);
        btn1.setLocation(3, 3);

        btn2.setSize(35, 35);
        btn2.setLocation(45, 3);

        btn3.setSize(35, 35);
        btn3.setLocation(87, 3);

        btn4.setSize(35, 35);
        btn4.setLocation(129, 3);

        btn5.setSize(35, 35);
        btn5.setLocation(171, 3);

        btn6.setSize(35, 35);
        btn6.setLocation(216, 3);

        btn7.setSize(35, 35);
        btn7.setLocation(258, 3);

        btn8.setSize(35, 35);
        btn8.setLocation(310, 3);

        btn9.setSize(35, 35);
        btn9.setLocation(352, 3);

        btn10.setSize(35, 35);
        btn10.setLocation(394, 3);

        btn11.setSize(35, 35);
        btn11.setLocation(436, 3);

        btn12.setSize(35, 35);
        btn12.setLocation(478, 3);

        btn13.setSize(35, 35);
        btn13.setLocation(520, 3);

        btn14.setSize(35, 35);
        btn14.setLocation(562, 3);

        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        add(btn6);
        add(btn7);
        add(btn8);
        add(btn9);
        add(btn10);
        add(btn11);
        add(btn12);
        add(btn13);
        add(btn14);
    }
}
