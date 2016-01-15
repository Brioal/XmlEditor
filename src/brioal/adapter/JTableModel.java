package brioal.adapter;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

/**
 * Created by brioal on 15-10-12.
 * 填充数据显示到JTable ui
 * 传入List用作数据源
 * 传入JTable用于数据改动时自适应宽度
 */

public class JTableModel extends AbstractTableModel {
    private List<Vector> contens;  //存储所有数据
    private Vector content;  //存储每一行的数

    public JTableModel(List<Vector> contents) {
        this.contens = contents;
    }

    //    添加行
    public void addRow(int index) {
        content = new Vector();
        for (int i = 0; i < getColumnCount(); i++) {
            content.add("");
        }
        contens.add(index, content);
        fireTableRowsInserted(index, index);
    }

    // 删除行
    public void removeRow(int rowindex) {
        contens.remove(rowindex);
        fireTableRowsDeleted(rowindex, rowindex);
    }

    //删除列
    public void removeCol(int colindex) {
        getContent(0).remove(colindex);
    }

    //获取每一行的数据
    public Vector getContent(int rowindex) {
        return contens.get(rowindex);
    }

    //获取行数
    @Override
    public int getRowCount() {
        return contens.size();
    }

    //获取列数
    @Override
    public int getColumnCount() {
        return contens.get(0).size();
    }


    //获取每一个单元格的数据
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getContent(rowIndex).get(columnIndex);
    }

    //  设置是否可以编辑
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    /**
     * 数值改变之后调用
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Vector content = contens.get(rowIndex);
        content.set(columnIndex, aValue); // 更新数据源
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void updateRow(int row, int column) {
        fireTableCellUpdated(row, column);
    }
}
