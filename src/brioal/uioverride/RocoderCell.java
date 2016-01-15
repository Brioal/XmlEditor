package brioal.uioverride;

import brioal.base.RecordItem;

import javax.swing.*;
import java.awt.*;

/**
 * Created by brioal on 15-10-10.
 * 自定义jlist内item 的显示内容,传入recoditem元素
 * 被list.setCellRenderer使用
 */

public class RocoderCell extends JPanel implements ListCellRenderer {
    RecordItem item ;
    String filename ;
    String filepath;
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        item = (RecordItem) value;
        filename = item.getFilename();
        filepath = item.getFilepath();
        return this;
    }

    public void paintComponent(Graphics g)
    {

        Graphics2D g2d = (Graphics2D) g;// 转化为2d
        // 抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(25, 25, 25, 60));
        g2d.setStroke(new BasicStroke(3));
        g2d.fillRoundRect(3, 8, getWidth() - 3, getHeight() - 3, 10, 10);
        g.setColor(Color.BLACK);
        g.setFont(new Font("宋体", Font.PLAIN, 20));
        g.drawString(filename, 25, 25);   //在制定位置绘制文本
        setFont(new Font("宋体", 0, 18));
        g.drawString(filepath, 5, 60);   //在制定位置绘制文本
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(300,100);   //Cell的尺寸
    }
}
