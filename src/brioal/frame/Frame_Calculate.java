package brioal.frame;

import brioal.adapter.JTableModel;
import brioal.base.JTableScope;
import brioal.interfaces.UiCommon;
import brioal.panel.Panel_JTable;
import brioal.uioverride.BlankButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

/**
 * Created by Brioal on 2015/12/25.
 * 进行计算的窗口
 * 当主窗口可见时一直在前,主窗口最小化的时候消失
 * 传入JTableScope类,JTable内所选择的数据的范围
 * 根据传入的state值判断是进行何种操作
 * 计算结果点选按钮自动填充到JTable的表格内
 */
public class Frame_Calculate extends JFrame implements UiCommon, ActionListener {
    public static int GET_AVERAGE = 100; // 求平均
    public static int GET_SUM = 101; // 求和
    public static int GET_MAX = 102; // 求最大值
    public static int GET_MIN = 103; // 求最小值
    private List<Vector> contents; // 数据源
    private Panel_JTable table; // 每一行的数据
    private int mx = 0, my = 0, jfx = 0, jfy = 0;
    private int start_Row, start_Column, end_Row, end_Column; // 存储选择的范围
    private JLabel result; // 显示结果
    private JLabel scope_label; // 显示选择的范围
    private JLabel title; //显示标题
    private JLabel showMessage; // 显示提示信息
    private BlankButton close; // 关闭按钮
    private BlankButton calculate; // 计算结果按钮
    public int result_data = 0; // 存储计算结果
    private int model = 0; // 要进行的计算的类型


    public Frame_Calculate(Panel_JTable table, int model, JTableScope scope) {
        this.model = model;
        this.contents = table.getContents();
        this.table = table;
        initComponent();
        setComponent();
        addComponent();
        setData(scope);
    }


    @Override
    public void initComponent() {
        //根据不同的计算类型设置不同的信息
        if (model == Frame_Calculate.GET_SUM) {
            title = new JLabel("求和");
        }
        if (model == Frame_Calculate.GET_AVERAGE) {
            title = new JLabel("求平均数");
        }
        if (model == Frame_Calculate.GET_MAX) {
            title = new JLabel("求最大值");
        }
        if (model == Frame_Calculate.GET_MIN) {
            title = new JLabel("求最小值");
        }
        scope_label = new JLabel("选择的范围:A1:A1");
        calculate = new BlankButton(null, "填充数据");
        result = new JLabel("");
        close = new BlankButton(null, "关闭");
        if (model == Frame_Calculate.GET_SUM) {
            showMessage = new JLabel("在表格上滑动选择要进行求和的数据");
        }
        if (model == Frame_Calculate.GET_AVERAGE) {
            showMessage = new JLabel("在表格上滑动选择要进行求平均数的数据");
        }
        if (model == Frame_Calculate.GET_MAX) {
            showMessage = new JLabel("在表格上滑动选择要进行求最大值的数据");
        }
        if (model == Frame_Calculate.GET_MIN) {
            showMessage = new JLabel("在表格上滑动选择要进行求最小值的数据");
        }
    }

    @Override
    public void setComponent() {
        //标题 Jlabel
        title.setSize(100, 30);
        title.setFont(new Font("宋体", Font.PLAIN, 15));
        title.setLocation(25, 10);
        //tip JLabel
        showMessage.setSize(300, 35);
        showMessage.setFont(new Font("宋体", Font.PLAIN, 15));
        showMessage.setLocation(10, 40);
        //范围 JLabel
        scope_label.setSize(300, 35);
        scope_label.setFont(new Font("宋体", Font.PLAIN, 18));
        scope_label.setLocation(40, 70);
        //结果 JTextFiled
        result.setSize(180, 35);
        scope_label.setFont(new Font("宋体", Font.PLAIN, 18));
        result.setLocation(10, 110);
        //计算按钮 JButton
        calculate.setSize(100, 30);
        calculate.setLocation(40, 150);
        calculate.addActionListener(this);
        //关闭按钮 JButton
        close.setSize(100, 30);
        close.setLocation(200, 150);
        close.addActionListener(this);
    }

    @Override
    public void addComponent() {
        add(title);
        add(showMessage);
        add(scope_label);
        add(result);
        add(calculate);
        add(close);

        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setAlwaysOnTop(true); //设置窗口总是在前
        //设置鼠标拖动事件
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - mx + jfx, e.getYOnScreen() - my + jfy);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mx = e.getXOnScreen();
                my = e.getYOnScreen();
                jfx = getX();
                jfy = getY();
            }
        });
    }

    //初始化数值范围
    public void setData(JTableScope scope) {
        result_data = 0 ;
        this.start_Row = scope.getStart_Row();
        this.start_Column = scope.getStart_Clom();
        this.end_Row = scope.getEnd_Row();
        this.end_Column = scope.getEnd_Clom();
        scope_label.setText("选择的范围:" + (char) (start_Column + 65) + "" + (start_Row+1) + ":" + (char) (end_Column + 65) + "" + (end_Row+1));
        int sum = calculate();
        result.setText("结果是:" + sum);
    }

    //填充表格数据
    public void fillData(int row, int col) {
        JTableModel model = (JTableModel) table.getTable().getModel();
        model.setValueAt(result_data, row, col);
        this.dispose();
    }

    //计算
    public int calculate() {
        // 求和 , 求平均
        if (model == Frame_Calculate.GET_SUM || model == Frame_Calculate.GET_AVERAGE) {
            int count = 0;
            for (int i = start_Row; i <= end_Row; i++) {
                for (int j = start_Column; j <= end_Column; j++) {
                    count++;
                    int s = Integer.valueOf(contents.get(i).get(j).toString().trim());
                    System.out.println(":"+s+":");
                    result_data += s;
                }
            }
            if (model == Frame_Calculate.GET_AVERAGE) {
                System.out.println(count);
                if (count != 0) {
                    result_data = result_data / count;
                }
            }
        }
        //最大最小值获取
        if (model == Frame_Calculate.GET_MAX || model == Frame_Calculate.GET_MIN) {
            int temp = Integer.valueOf(contents.get(start_Row).get(start_Column).toString().trim());
            for (int i = start_Row + 1; i <= end_Row; i++) {
                for (int j = start_Column; j <= end_Column; j++) {
                    int s = Integer.valueOf(contents.get(i).get(j).toString().trim());
                    if (model == Frame_Calculate.GET_MAX) {
                        //获取最大值
                        result_data = s > temp ? s : temp;
                    } else {
                        //获取最小值
                        result_data = s < temp ? s : temp;
                    }
                }
            }
        }
        return result_data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("填充数据")) {
            //通过对范围的数值判断决定数据填充的位置
            int row, column;

            if (end_Row == start_Row) {
                row = end_Row;
            } else {
                row = end_Row + 1;
            }
            if (end_Column == start_Column) {
                column = end_Column;
            } else {
                column = end_Column + 1;
            }
            fillData(row, column);

        }
        if (e.getActionCommand().equals("关闭")) {
            this.dispose();
        }
    }
}
