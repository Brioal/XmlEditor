package brioal.uioverride;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by Brioal on 2015/12/30.
 * 自定义的JTable来给面板JTable添加一个行表头
 */
public class RowHeadJTable extends JTable {
    private JTable jTable ;

    public RowHeadJTable(JTable jTable, int columnWidth) {
        super(new RowHeaderTableModel(jTable.getRowCount()));
        this.jTable = jTable;
        setRowHeight(25);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.getColumnModel().getColumn(0).setPreferredWidth(columnWidth);
        this.setDefaultRenderer(Object.class, new IsRowHeaderRenderer(jTable, this));// ?????????
        this.setPreferredScrollableViewportSize(new Dimension(columnWidth,40));
    }
}

class IsRowHeaderRenderer extends JLabel implements TableCellRenderer, ListSelectionListener {
    JTable jTable;
    JTable tableShow;

    public IsRowHeaderRenderer(JTable jTable, JTable tableShow) {
        this.jTable = jTable;
        this.tableShow = tableShow;
        ListSelectionModel listModel = jTable.getSelectionModel();
        listModel.addListSelectionListener(this);
    }

    public Component getTableCellRendererComponent(JTable table, Object obj,
                                                   boolean isSelected, boolean hasFocus, int row, int col) {
        ((RowHeaderTableModel) table.getModel()).setRowCount(jTable
                .getRowCount());
        JTableHeader header = jTable.getTableHeader();
        this.setOpaque(true);
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setHorizontalAlignment(CENTER);
        setBackground(header.getBackground());
        if (isSelect(row)) {
            setForeground(Color.white);
            setBackground(Color.lightGray);
        } else {
            setForeground(header.getForeground());
        }
        setFont(header.getFont());
        setText(String.valueOf(row + 1));
        return this;
    }

    public void valueChanged(ListSelectionEvent e) {
        this.tableShow.repaint();
    }

    private boolean isSelect(int row) {
        int[] sel = jTable.getSelectedRows();
        for (int i = 0; i < sel.length; i++)
            if (sel[i] == row)
                return true;
        return false;
    }
}

class RowHeaderTableModel extends AbstractTableModel {
    private int rowCount;
    public RowHeaderTableModel(int rowCount) {
        this.rowCount = rowCount;
    }
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
    public int getRowCount() {
        return rowCount;
    }
    public int getColumnCount() {
        return 1;
    }
    public Object getValueAt(int row, int column) {
        return row;
    }
}