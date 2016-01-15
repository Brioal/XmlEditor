package brioal.panel;

import brioal.interfaces.UiCommon;

import javax.swing.*;
import java.awt.*;

/**
 * Created by brioal on 15-10-9.
 * logo面板，仅用于显示logo
 */

public class Panel_Logo extends JPanel implements UiCommon {

    public Panel_Logo() {
        initComponent();
        setComponent();
        addComponent();
    }

    @Override
    public void initComponent() {

    }

    @Override
    public void setComponent() {

    }

    @Override
    public void addComponent() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //标题
        g.setColor(new Color(0,0,0));
        g.setFont(new Font("宋体", Font.PLAIN, 80));
        g.drawString("Mini Excel" , 70,190);

    }

}
