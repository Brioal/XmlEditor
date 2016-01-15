package brioal.adapter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by Brioal on 2015/12/29.
 * Ĭ�ϵı��Ԫ����Ⱦ��
 * �ṩ�ı����� , ����ɫ, ǰ��ɫ�ķ���
 * MyJTable������Ϊstatic,ʹ��ÿ�β�����cellΪͬһ��
 */
public class MyJtableCellRender extends DefaultTableCellRenderer {
    private Font font; // ����
    private Color backColor; // ����ɫ
    private Color frontColor; // ������ɫ
    private int styleModel;  // ��ʽ��ʽ

    public MyJtableCellRender() {
        //����Ĭ��ֵ
        font = new Font("����", Font.PLAIN, 18);
        backColor = Color.WHITE;
        frontColor = Color.BLACK;
        styleModel = DefaultTableCellRenderer.CENTER;
    }

    public void setFont(Font font, int row, int col) {
        this.font = font;
    }

    //���뵱ʱ
    public void setStyle(int styleModel) {
        this.styleModel = styleModel;
    }

    //����ɫ
    public void setBackColor(Color backColor) {
        this.backColor = backColor;
    }

    //ǰ��ɫ
    public void setFrontColor(Color frontColor) {
        this.frontColor = frontColor;
    }


    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        setFont(font); // ��������
        setToolTipText("��ǰ����Ϊ" + value.toString()); // ��ʾ��Ϣ
        setForeground(frontColor); // ǰ��ɫ
        setBackground(backColor); // ����ɫ
        setHorizontalAlignment(styleModel);//����Ĭ�Ͼ���

        return super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
    }
}
