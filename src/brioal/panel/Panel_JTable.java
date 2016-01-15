package brioal.panel;

import brioal.adapter.JTableModel;
import brioal.interfaces.UiCommon;
import brioal.uioverride.MyJTable;
import brioal.uioverride.RowHeadJTable;
import brioal.utool.FileOperator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by brioal on 15-10-11.
 * 表格面板，封装表格，包含一些数据操作
 * 被Panel_Main调用
 * 传入xml文件
 */

public class Panel_JTable extends JPanel implements UiCommon {

    private int width, height;
    private java.util.List<Vector> contents;
    private Vector content;
    JTable table;
    JTableModel model;


    public Panel_JTable(Dimension dimension, File file) {
        if (file == null) { // 新建
            contents = new ArrayList<>();
            for (int i = 0; i < 25; i++) {
                content = new Vector();
                for (int j = 0; j <= 18; j++) {
                    content.add(" ");
                }
                contents.add(content);
            }
        } else { //打开
            contents = FileOperator.Read(file);
        }
        this.width = (int) dimension.getWidth();
        this.height = (int) dimension.getHeight();
        initComponent();
        setComponent();
        addComponent();


    }

    public JTable getTable() {
        return table;
    }

    public java.util.List<Vector> getContents() {
        return contents;
    }

    public String copy(int row, int column) {

        return model.getValueAt(row, column).toString();
    }

    public String cut(int row, int column) {
        String result = model.getValueAt(row, column).toString();
        model.setValueAt("", row, column);
        return result;

    }

    public void paste(String string, int row, int column) {

        model.setValueAt(string, row, column);
    }

    //保存
    public void save(File newFile) {
        FileOperator.Save(contents, newFile);
    }

    public void addRow(int index) {
        model.addRow(index);
    }


    public void removeRow(int index) {
        model.removeRow(index);
    }


    @Override
    public void initComponent() {
        model = new JTableModel(contents);
        table = new MyJTable(model);
    }

    @Override
    public void setComponent() {
    }

    @Override
    public void addComponent() {
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setRowHeaderView(new RowHeadJTable(table, 40)); // 添加行表头
        scrollPane.setPreferredSize(new Dimension(width - 5, height - 100));
        add(scrollPane);
    }

}
