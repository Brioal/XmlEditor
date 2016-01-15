package brioal.adapter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by Brioal on 2015/12/29.
 * 默认的表格单元格渲染器
 * 提供改变字体 , 背景色, 前景色的方法
 * MyJTable中设置为static,使得每次操作的cell为同一个
 */
public class MyJtableCellRender extends DefaultTableCellRenderer {
    private Font font; // 字体
    private Color backColor; // 背景色
    private Color frontColor; // 字体颜色
    private int styleModel;  // 格式方式

    public MyJtableCellRender() {
        //设置默认值
        font = new Font("宋体", Font.PLAIN, 18);
        backColor = Color.WHITE;
        frontColor = Color.BLACK;
        styleModel = DefaultTableCellRenderer.CENTER;
    }

    public void setFont(Font font, int row, int col) {
        this.font = font;
    }

    //对齐当时
    public void setStyle(int styleModel) {
        this.styleModel = styleModel;
    }

    //背景色
    public void setBackColor(Color backColor) {
        this.backColor = backColor;
    }

    //前景色
    public void setFrontColor(Color frontColor) {
        this.frontColor = frontColor;
    }


    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        setFont(font); // 设置字体
        setToolTipText("当前内容为" + value.toString()); // 提示信息
        setForeground(frontColor); // 前景色
        setBackground(backColor); // 背景色
        setHorizontalAlignment(styleModel);//设置默认居中

        return super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
    }
}
