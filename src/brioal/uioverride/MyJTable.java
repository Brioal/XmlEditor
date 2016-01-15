package brioal.uioverride;

import brioal.adapter.MyJtableCellRender;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by brioal on 15-10-12.
 * 对默认的JTable进行一些自定义
 * 设置默认字体
 * 设置表头
 * 设置默认Cellrenderer
 */

public class MyJTable extends JTable {
    public static MyJtableCellRender cellRender ;

    public MyJTable(TableModel dm) {
        super(dm);
		setFont(new Font("宋体",Font.PLAIN,15));
        setColumnSelectionAllowed(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        setRowHeight(25);
        cellRender = new MyJtableCellRender();
    }


    @Override
    public String getColumnName(int column) {
        if (column > 1) {
            return (char)(column+64)+"";
        }
        else {
            return "";
        }
    }

    @Override
    public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {

        return cellRender;
    }

    public static MyJtableCellRender getCellRender() {
        return cellRender;
    }
}
