package brioal.frame;

import brioal.base.JTableScope;
import brioal.interfaces.UiCommon;
import brioal.panel.Panel_JTable;
import brioal.panel.Panel_ToolBar;
import brioal.uioverride.MyJTable;
import brioal.uioverride.MyMenuBar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


/**
 * Created by brioal on 15-10-10.
 * �ļ�������������
 * ͨ������File����һ�����
 */

public class Frame_Main extends JFrame implements UiCommon {
    private JMenuBar menuBar; // �˵���
    private ActionListener listener; // �¼�������
    private File file; // Ҫ�򿪵��ļ�
    private FileDialog open; // �ļ���������
    private Panel_ToolBar toolBar; // �˵���ť
    private Panel_JTable panel_jTable; // ���
    private Dimension di; // ��Ļ���
    private JTable jTable; // JTable����
    private Frame_Calculate frame_calculate; // ��ʹ���
    private MouseListener jTableLitener; // ��ʹ��ڵļ����¼�
    private MouseMotionListener jTableMotionListener;
    private String temp = null; // ���ڸ��� ,����, ճ���������м�ֵ
    private JTableScope scope; // ��Χ
    private JPopupMenu menu; // �Ҽ������˵�
    private JMenuItem items[] = new JMenuItem[]{ // �Ҽ��˵���
            new JMenuItem("���ñ�����ɫ"),
            new JMenuItem("����������ɫ"),
            new JMenuItem("�����"),
            new JMenuItem("ɾ����"),
            new JMenuItem("����"),
            new JMenuItem("����"),
            new JMenuItem("ճ��"),
    };

    public Frame_Main(File file) {
        //����ͼ��
        setIconImage(new ImageIcon("drawable/icon.png").getImage());
        this.file = file;
        if (file != null) {
            setTitle(file.getAbsolutePath()); // ������ʾΪ��ǰ���ļ�������·��
        }
        //ʵ��ȫ��
        Toolkit tk = Toolkit.getDefaultToolkit();
        di = tk.getScreenSize();
        setSize(di);
        scope = new JTableScope();
        //������Ĳ���
        initComponent();
        setComponent();
        addComponent();
        setVisible(true);
    }


    @Override
    public void initComponent() {
        //��ʼ������¼������� ,�����˵���,�Զ���Ĺ����� , �Ҽ��˵�
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select_row = jTable.getSelectedRow();
                int select_column = jTable.getSelectedColumn();
                if (e.getActionCommand().equals("ǰ��ɫ")) {
                    Color selectColor = JColorChooser.showDialog(Frame_Main.this, "ѡ�񱳾���ɫ", null);
                    MyJTable.cellRender.setFrontColor(selectColor);
                    panel_jTable.getTable().updateUI();
                }
                if (e.getActionCommand().equals("����ɫ")) {
                    Color selectColor = JColorChooser.showDialog(Frame_Main.this, "ѡ�񱳾���ɫ", null);
                    MyJTable.cellRender.setBackColor(selectColor);
                    panel_jTable.getTable().updateUI();
                }
                if (e.getActionCommand().equals("ѡ�б���")) {
                    Color selectColor = JColorChooser.showDialog(Frame_Main.this, "ѡ�񱳾���ɫ", null);
                    panel_jTable.getTable().setSelectionBackground(selectColor);
                    panel_jTable.getTable().updateUI();
                }
                if (e.getActionCommand().equals("������ɫ")) { //����ȫ��
                    Color selectColor = JColorChooser.showDialog(Frame_Main.this, "ѡ��������ɫ", null);
                    MyJTable.cellRender.setFrontColor(selectColor);
                    jTable.updateUI();
                }
                if (e.getActionCommand().equals("�����")) {
                    panel_jTable.addRow(jTable.getSelectedRow());
                }
                if (e.getActionCommand().equals("ɾ����")) {
                    panel_jTable.removeRow(jTable.getSelectedRow());
                }
                if (e.getActionCommand().equals("����")) {
                    MyJTable.cellRender.setStyle(DefaultTableCellRenderer.CENTER);
                    jTable.updateUI();
                }
                if (e.getActionCommand().equals("����")) {
                    MyJTable.cellRender.setStyle(DefaultTableCellRenderer.LEFT);
                    jTable.updateUI();
                }
                if (e.getActionCommand().equals("����")) {
                    MyJTable.cellRender.setStyle(DefaultTableCellRenderer.RIGHT);
                    jTable.updateUI();
                }
                if (e.getActionCommand().equals("����������ɫ")) {
                    Color selectColor = JColorChooser.showDialog(Frame_Main.this, "ѡ��������ɫ", null);
                    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                        public Component getTableCellRendererComponent(JTable table,
                                                                       Object value, boolean isSelected, boolean hasFocus,
                                                                       int row, int column) {
                            if (row == select_row) {
                                setForeground(selectColor);
                                setHorizontalAlignment(CENTER);
                            } else {
                                setForeground(Color.BLACK);
                            }
                            return super.getTableCellRendererComponent(table, value,
                                    isSelected, hasFocus, row, column);
                        }
                    };
                    jTable.getColumnModel().getColumn(select_column).setCellRenderer(tcr);
                }
                if (e.getActionCommand().equals("���ñ�����ɫ")) {
                    Color selectColor = JColorChooser.showDialog(Frame_Main.this, "ѡ��������ɫ", null);
                    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                        public Component getTableCellRendererComponent(JTable table,
                                                                       Object value, boolean isSelected, boolean hasFocus,
                                                                       int row, int column) {
                            if (row == select_row && column == select_column) {
                                setBackground(selectColor);
                                setHorizontalAlignment(CENTER);
                            } else {
                                setBackground(Color.WHITE);
                            }

                            return super.getTableCellRendererComponent(table, value,
                                    isSelected, hasFocus, row, column);
                        }
                    };
                    jTable.getColumnModel().getColumn(select_column).setCellRenderer(tcr);
                }
                if (e.getActionCommand().equals("�½�")) {
                    Frame_Main.this.dispose();
                    new Frame_Main(null);
                }
                if (e.getActionCommand().equals("��")) {
                    open = new FileDialog(Frame_Main.this, "���ļ�", FileDialog.LOAD);
                    open.setVisible(true);
                    File file = null;
                    try {
                        file = new File(open.getFile());
                    } catch (Exception e1) {
                        System.out.println("û��ѡ���ļ�");
                    }
                    Frame_Main.this.dispose();
                    new Frame_Main(file);

                }
                if (e.getActionCommand().equals("����")) {
                    if (file == null) {
                        saveAs();
                    } else {
                        panel_jTable.save(file);
                    }
                }
                if (e.getActionCommand().equals("���Ϊ") ) {
                    saveAs();
                }
                if (e.getActionCommand().equals("�ر�") ) {
                    new Frame_Welcome();
                    setVisible(false);
                    Frame_Main.this.dispose();
                }
                if (e.getActionCommand().equals("�˳�") ) {
                    System.exit(0);
                }
                if (e.getActionCommand().equals("���")) {
                    frame_calculate = new Frame_Calculate(panel_jTable, Frame_Calculate.GET_SUM, scope);
                    addMouseListener();
                }
                if (e.getActionCommand().equals("��ƽ��")) {
                    frame_calculate = new Frame_Calculate(panel_jTable, Frame_Calculate.GET_AVERAGE, scope);
                    addMouseListener();

                }
                if (e.getActionCommand().equals("�����ֵ")) {
                    frame_calculate = new Frame_Calculate(panel_jTable, Frame_Calculate.GET_MAX, scope);
                    addMouseListener();

                }
                if (e.getActionCommand().equals("����Сֵ")) {
                    frame_calculate = new Frame_Calculate(panel_jTable, Frame_Calculate.GET_MIN, scope);
                    addMouseListener();

                }
                if (e.getActionCommand().equals("��������")) {
                    panel_jTable.addRow(jTable.getSelectedRow() + 1);
                }
                if (e.getActionCommand().equals("ѡ��ȫ��")) {
                    jTable.selectAll();
                }
                if (e.getActionCommand().equals("ȡ��ѡ��")) {
                    jTable.clearSelection();
                }
                if (e.getActionCommand().equals("����")) {
                    temp = panel_jTable.copy(select_row, select_column);
                }
                if (e.getActionCommand().equals("����")) {
                    System.out.println("����");
                    temp = panel_jTable.cut(select_row, select_column);
                }
                if (e.getActionCommand().equals("ճ��")) {
                    if (null != temp) {
                        panel_jTable.paste(temp, select_row, select_column);
                    }
                }
                if (e.getActionCommand().equals("��������")) {
                    //����������ʾ��Ϣ
                    JOptionPane.showMessageDialog(Frame_Main.this,
                            "13�����һ��-�ƿ�!-320130937631", "ѧ����Ϣ", JOptionPane.INFORMATION_MESSAGE);
                }
                if (e.getActionCommand().equals("�ܽ�")) {
                    new Frame_Summary();
                }
            }
        };
        //����JTable�µ�����¼�������
        jTableLitener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            //����ͷ�֮�����ݴ��뵽�������
                //ǰ���Ǵ򿪼������ʱ�����Ӽ�����
                if (e.getButton() == MouseEvent.BUTTON3) {
                    menu.show(jTable, e.getX(), e.getY());
                } else {
                    int rows[] = panel_jTable.getTable().getSelectedRows();
                    int clos[] = panel_jTable.getTable().getSelectedColumns();
                    if (scope == null) {
                        scope = new JTableScope(rows[0], clos[0], rows[rows.length - 1], clos[clos.length - 1]);
                    } else {
                        scope.setData(rows[0], clos[0], rows[rows.length - 1], clos[clos.length - 1]);
                    }
                    System.out.println(scope);
                    if (frame_calculate != null) {
                        frame_calculate.setData(scope);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

//        ��ʼ���˵���
        menuBar = new MyMenuBar(listener);
        toolBar = new Panel_ToolBar(listener);
        panel_jTable = new Panel_JTable(di, file);
        jTable = panel_jTable.getTable();
        menu = new JPopupMenu("�˵�����");
        //�Ҽ��˵�����¼�������
        for (int i = 0; i < items.length; i++) {
            items[i].addActionListener(listener);
            items[i].setFont(new Font("����", Font.PLAIN, 15));
            menu.add(items[i]);
        }
    }

    public void addMouseListener() {
        jTable.addMouseListener(jTableLitener);
    }

    //���Ϊ����
    public void saveAs() {
        open = new FileDialog(Frame_Main.this, "�����ļ�", FileDialog.SAVE);
        open.setVisible(true);
        if (open.getFile() != null) {
            File newFile = new File(open.getFile() + ".xml");
            panel_jTable.save(newFile);
        }
    }

    @Override
    public void setComponent() {
        setBackground(Color.WHITE);
        setJMenuBar(menuBar);
        jTable.addMouseListener(jTableLitener);
        jTable.addMouseMotionListener(jTableMotionListener);
        //���ô��ڹر��¼�

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null,
                        "�ļ����޸ģ��Ƿ񱣴棿", "�����ļ���", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE, null);
                switch (option) {
                    case JOptionPane.YES_NO_OPTION: {
                        if (file == null) {
                            saveAs();
                        } else {
                            panel_jTable.save(file);
                        }
                        break;
                    }
                    case JOptionPane.NO_OPTION:
                        System.exit(0);

                }
                new Frame_Welcome();
                Frame_Main.this.dispose();
            }
        });
        this.addWindowStateListener(new WindowStateListener() {

            public void windowStateChanged(WindowEvent state) {

                if (state.getNewState() == 1 || state.getNewState() == 7) {
                    System.out.println("������С��");
                    if (frame_calculate != null) {
                        frame_calculate.dispose();
                    }
                } else if (state.getNewState() == 0) {
                    System.out.println("���ڻָ�����ʼ״̬");
                } else if (state.getNewState() == 6) {
                    System.out.println("�������");
                }
            }
        });
        setLayout(null);
        setBackground(Color.WHITE);
        toolBar.setSize(600, 30);
        toolBar.setLocation(0, 0);
        panel_jTable.setSize((int) di.getWidth(), (int) di.getHeight() - 30);
        panel_jTable.setLocation(-5, 30);
    }
    @Override
    public void addComponent() {
        add(toolBar);
        add(panel_jTable);
    }
}
