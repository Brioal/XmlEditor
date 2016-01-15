package brioal.adapter;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

/**
 * Created by brioal on 15-10-12.
 * ���������ʾ��JTable ui
 * ����List��������Դ
 * ����JTable�������ݸĶ�ʱ����Ӧ���
 */

public class JTableModel extends AbstractTableModel {
    private List<Vector> contens;  //�洢��������
    private Vector content;  //�洢ÿһ�е���

    public JTableModel(List<Vector> contents) {
        this.contens = contents;
    }

    //    �����
    public void addRow(int index) {
        content = new Vector();
        for (int i = 0; i < getColumnCount(); i++) {
            content.add("");
        }
        contens.add(index, content);
        fireTableRowsInserted(index, index);
    }

    // ɾ����
    public void removeRow(int rowindex) {
        contens.remove(rowindex);
        fireTableRowsDeleted(rowindex, rowindex);
    }

    //ɾ����
    public void removeCol(int colindex) {
        getContent(0).remove(colindex);
    }

    //��ȡÿһ�е�����
    public Vector getContent(int rowindex) {
        return contens.get(rowindex);
    }

    //��ȡ����
    @Override
    public int getRowCount() {
        return contens.size();
    }

    //��ȡ����
    @Override
    public int getColumnCount() {
        return contens.get(0).size();
    }


    //��ȡÿһ����Ԫ�������
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getContent(rowIndex).get(columnIndex);
    }

    //  �����Ƿ���Ա༭
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    /**
     * ��ֵ�ı�֮�����
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Vector content = contens.get(rowIndex);
        content.set(columnIndex, aValue); // ��������Դ
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void updateRow(int row, int column) {
        fireTableCellUpdated(row, column);
    }
}
