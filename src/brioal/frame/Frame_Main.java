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
 * 文件操作的主界面
 * 通过传入File来打开一个表格
 */

public class Frame_Main extends JFrame implements UiCommon {
    private JMenuBar menuBar; // 菜单栏
    private ActionListener listener; // 事件监听器
    private File file; // 要打开的文件
    private FileDialog open; // 文件操作窗口
    private Panel_ToolBar toolBar; // 菜单按钮
    private Panel_JTable panel_jTable; // 表格
    private Dimension di; // 屏幕宽度
    private JTable jTable; // JTable对象
    private Frame_Calculate frame_calculate; // 求和窗体
    private MouseListener jTableLitener; // 求和窗口的监听事件
    private MouseMotionListener jTableMotionListener;
    private String temp = null; // 用于复制 ,剪切, 粘贴操作的中间值
    private JTableScope scope; // 范围
    private JPopupMenu menu; // 右键弹出菜单
    private JMenuItem items[] = new JMenuItem[]{ // 右键菜单项
            new JMenuItem("设置背景颜色"),
            new JMenuItem("设置字体颜色"),
            new JMenuItem("添加行"),
            new JMenuItem("删除行"),
            new JMenuItem("复制"),
            new JMenuItem("剪切"),
            new JMenuItem("粘贴"),
    };

    public Frame_Main(File file) {
        //设置图标
        setIconImage(new ImageIcon("drawable/icon.png").getImage());
        this.file = file;
        if (file != null) {
            setTitle(file.getAbsolutePath()); // 标题显示为当前打开文件的完整路径
        }
        //实现全屏
        Toolkit tk = Toolkit.getDefaultToolkit();
        di = tk.getScreenSize();
        setSize(di);
        scope = new JTableScope();
        //对组件的操作
        initComponent();
        setComponent();
        addComponent();
        setVisible(true);
    }


    @Override
    public void initComponent() {
        //初始化点击事件监听器 ,包括菜单栏,自定义的工具栏 , 右键菜单
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select_row = jTable.getSelectedRow();
                int select_column = jTable.getSelectedColumn();
                if (e.getActionCommand().equals("前景色")) {
                    Color selectColor = JColorChooser.showDialog(Frame_Main.this, "选择背景颜色", null);
                    MyJTable.cellRender.setFrontColor(selectColor);
                    panel_jTable.getTable().updateUI();
                }
                if (e.getActionCommand().equals("背景色")) {
                    Color selectColor = JColorChooser.showDialog(Frame_Main.this, "选择背景颜色", null);
                    MyJTable.cellRender.setBackColor(selectColor);
                    panel_jTable.getTable().updateUI();
                }
                if (e.getActionCommand().equals("选中背景")) {
                    Color selectColor = JColorChooser.showDialog(Frame_Main.this, "选择背景颜色", null);
                    panel_jTable.getTable().setSelectionBackground(selectColor);
                    panel_jTable.getTable().updateUI();
                }
                if (e.getActionCommand().equals("字体颜色")) { //设置全局
                    Color selectColor = JColorChooser.showDialog(Frame_Main.this, "选择字体颜色", null);
                    MyJTable.cellRender.setFrontColor(selectColor);
                    jTable.updateUI();
                }
                if (e.getActionCommand().equals("添加行")) {
                    panel_jTable.addRow(jTable.getSelectedRow());
                }
                if (e.getActionCommand().equals("删除行")) {
                    panel_jTable.removeRow(jTable.getSelectedRow());
                }
                if (e.getActionCommand().equals("居中")) {
                    MyJTable.cellRender.setStyle(DefaultTableCellRenderer.CENTER);
                    jTable.updateUI();
                }
                if (e.getActionCommand().equals("居左")) {
                    MyJTable.cellRender.setStyle(DefaultTableCellRenderer.LEFT);
                    jTable.updateUI();
                }
                if (e.getActionCommand().equals("居右")) {
                    MyJTable.cellRender.setStyle(DefaultTableCellRenderer.RIGHT);
                    jTable.updateUI();
                }
                if (e.getActionCommand().equals("设置字体颜色")) {
                    Color selectColor = JColorChooser.showDialog(Frame_Main.this, "选择字体颜色", null);
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
                if (e.getActionCommand().equals("设置背景颜色")) {
                    Color selectColor = JColorChooser.showDialog(Frame_Main.this, "选择字体颜色", null);
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
                if (e.getActionCommand().equals("新建")) {
                    Frame_Main.this.dispose();
                    new Frame_Main(null);
                }
                if (e.getActionCommand().equals("打开")) {
                    open = new FileDialog(Frame_Main.this, "打开文件", FileDialog.LOAD);
                    open.setVisible(true);
                    File file = null;
                    try {
                        file = new File(open.getFile());
                    } catch (Exception e1) {
                        System.out.println("没有选中文件");
                    }
                    Frame_Main.this.dispose();
                    new Frame_Main(file);

                }
                if (e.getActionCommand().equals("保存")) {
                    if (file == null) {
                        saveAs();
                    } else {
                        panel_jTable.save(file);
                    }
                }
                if (e.getActionCommand().equals("另存为") ) {
                    saveAs();
                }
                if (e.getActionCommand().equals("关闭") ) {
                    new Frame_Welcome();
                    setVisible(false);
                    Frame_Main.this.dispose();
                }
                if (e.getActionCommand().equals("退出") ) {
                    System.exit(0);
                }
                if (e.getActionCommand().equals("求和")) {
                    frame_calculate = new Frame_Calculate(panel_jTable, Frame_Calculate.GET_SUM, scope);
                    addMouseListener();
                }
                if (e.getActionCommand().equals("求平均")) {
                    frame_calculate = new Frame_Calculate(panel_jTable, Frame_Calculate.GET_AVERAGE, scope);
                    addMouseListener();

                }
                if (e.getActionCommand().equals("求最大值")) {
                    frame_calculate = new Frame_Calculate(panel_jTable, Frame_Calculate.GET_MAX, scope);
                    addMouseListener();

                }
                if (e.getActionCommand().equals("求最小值")) {
                    frame_calculate = new Frame_Calculate(panel_jTable, Frame_Calculate.GET_MIN, scope);
                    addMouseListener();

                }
                if (e.getActionCommand().equals("插入新行")) {
                    panel_jTable.addRow(jTable.getSelectedRow() + 1);
                }
                if (e.getActionCommand().equals("选择全部")) {
                    jTable.selectAll();
                }
                if (e.getActionCommand().equals("取消选择")) {
                    jTable.clearSelection();
                }
                if (e.getActionCommand().equals("复制")) {
                    temp = panel_jTable.copy(select_row, select_column);
                }
                if (e.getActionCommand().equals("剪切")) {
                    System.out.println("剪切");
                    temp = panel_jTable.cut(select_row, select_column);
                }
                if (e.getActionCommand().equals("粘贴")) {
                    if (null != temp) {
                        panel_jTable.paste(temp, select_row, select_column);
                    }
                }
                if (e.getActionCommand().equals("关于作者")) {
                    //弹出窗口显示信息
                    JOptionPane.showMessageDialog(Frame_Main.this,
                            "13计算机一班-黄俊!-320130937631", "学生信息", JOptionPane.INFORMATION_MESSAGE);
                }
                if (e.getActionCommand().equals("总结")) {
                    new Frame_Summary();
                }
            }
        };
        //绑定在JTable下的鼠标事件监听器
        jTableLitener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            //鼠标释放之后将数据传入到计算面板
                //前提是打开计算面板时候的添加监听器
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

//        初始化菜单栏
        menuBar = new MyMenuBar(listener);
        toolBar = new Panel_ToolBar(listener);
        panel_jTable = new Panel_JTable(di, file);
        jTable = panel_jTable.getTable();
        menu = new JPopupMenu("菜单测试");
        //右键菜单添加事件监听器
        for (int i = 0; i < items.length; i++) {
            items[i].addActionListener(listener);
            items[i].setFont(new Font("宋体", Font.PLAIN, 15));
            menu.add(items[i]);
        }
    }

    public void addMouseListener() {
        jTable.addMouseListener(jTableLitener);
    }

    //另存为方法
    public void saveAs() {
        open = new FileDialog(Frame_Main.this, "保存文件", FileDialog.SAVE);
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
        //设置窗口关闭事件

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null,
                        "文件已修改，是否保存？", "保存文件？", JOptionPane.YES_NO_OPTION,
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
                    System.out.println("窗口最小化");
                    if (frame_calculate != null) {
                        frame_calculate.dispose();
                    }
                } else if (state.getNewState() == 0) {
                    System.out.println("窗口恢复到初始状态");
                } else if (state.getNewState() == 6) {
                    System.out.println("窗口最大化");
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
