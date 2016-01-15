package brioal.uioverride;

import javax.swing.*;
import java.awt.*;

/**
 * Created by brioal on 15-10-10.
 * 白色背景的按钮
 * 单独传入文字 , 图标,或者两者
 */

public class BlankButton extends JButton {
    private ImageIcon icon;
    private String text;

    public BlankButton(ImageIcon icon, String text) {
        this.icon = icon;
        this.text = text;
        setActionCommand(text);
        setOpaque(true);
        setBorder(null);  //设置边框
        setFocusPainted(false); // 设置无焦点
        setMargin(new Insets(0, 0, 0, 0));//设置边距
        setContentAreaFilled(false);//不绘制按钮区域
        setBorderPainted(false);//不绘制边框
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;// 转化为2d
        // 抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(25, 25, 25, 60));
        g2d.setStroke(new BasicStroke(3));
        g2d.fillRoundRect(10, 3, getWidth() - 10, getHeight() - 3, 20, 20);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("宋体", Font.PLAIN, 20));
        if (text == "") {
            g.drawImage(icon.getImage(), 10, 3, 25, 25, this);
        } else if (icon == null) {
            g.drawString(text, 15, 25);
        } else {
            g.drawString(text, getWidth() / 2 - 70, 25);
            g.drawImage(icon.getImage(), 50, 5, 35, 35, this);
        }
    }
}




