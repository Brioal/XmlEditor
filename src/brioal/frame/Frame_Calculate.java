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
 * ���м���Ĵ���
 * �������ڿɼ�ʱһֱ��ǰ,��������С����ʱ����ʧ
 * ����JTableScope��,JTable����ѡ������ݵķ�Χ
 * ���ݴ����stateֵ�ж��ǽ��к��ֲ���
 * ��������ѡ��ť�Զ���䵽JTable�ı����
 */
public class Frame_Calculate extends JFrame implements UiCommon, ActionListener {
    public static int GET_AVERAGE = 100; // ��ƽ��
    public static int GET_SUM = 101; // ���
    public static int GET_MAX = 102; // �����ֵ
    public static int GET_MIN = 103; // ����Сֵ
    private List<Vector> contents; // ����Դ
    private Panel_JTable table; // ÿһ�е�����
    private int mx = 0, my = 0, jfx = 0, jfy = 0;
    private int start_Row, start_Column, end_Row, end_Column; // �洢ѡ��ķ�Χ
    private JLabel result; // ��ʾ���
    private JLabel scope_label; // ��ʾѡ��ķ�Χ
    private JLabel title; //��ʾ����
    private JLabel showMessage; // ��ʾ��ʾ��Ϣ
    private BlankButton close; // �رհ�ť
    private BlankButton calculate; // ��������ť
    public int result_data = 0; // �洢������
    private int model = 0; // Ҫ���еļ��������


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
        //���ݲ�ͬ�ļ����������ò�ͬ����Ϣ
        if (model == Frame_Calculate.GET_SUM) {
            title = new JLabel("���");
        }
        if (model == Frame_Calculate.GET_AVERAGE) {
            title = new JLabel("��ƽ����");
        }
        if (model == Frame_Calculate.GET_MAX) {
            title = new JLabel("�����ֵ");
        }
        if (model == Frame_Calculate.GET_MIN) {
            title = new JLabel("����Сֵ");
        }
        scope_label = new JLabel("ѡ��ķ�Χ:A1:A1");
        calculate = new BlankButton(null, "�������");
        result = new JLabel("");
        close = new BlankButton(null, "�ر�");
        if (model == Frame_Calculate.GET_SUM) {
            showMessage = new JLabel("�ڱ���ϻ���ѡ��Ҫ������͵�����");
        }
        if (model == Frame_Calculate.GET_AVERAGE) {
            showMessage = new JLabel("�ڱ���ϻ���ѡ��Ҫ������ƽ����������");
        }
        if (model == Frame_Calculate.GET_MAX) {
            showMessage = new JLabel("�ڱ���ϻ���ѡ��Ҫ���������ֵ������");
        }
        if (model == Frame_Calculate.GET_MIN) {
            showMessage = new JLabel("�ڱ���ϻ���ѡ��Ҫ��������Сֵ������");
        }
    }

    @Override
    public void setComponent() {
        //���� Jlabel
        title.setSize(100, 30);
        title.setFont(new Font("����", Font.PLAIN, 15));
        title.setLocation(25, 10);
        //tip JLabel
        showMessage.setSize(300, 35);
        showMessage.setFont(new Font("����", Font.PLAIN, 15));
        showMessage.setLocation(10, 40);
        //��Χ JLabel
        scope_label.setSize(300, 35);
        scope_label.setFont(new Font("����", Font.PLAIN, 18));
        scope_label.setLocation(40, 70);
        //��� JTextFiled
        result.setSize(180, 35);
        scope_label.setFont(new Font("����", Font.PLAIN, 18));
        result.setLocation(10, 110);
        //���㰴ť JButton
        calculate.setSize(100, 30);
        calculate.setLocation(40, 150);
        calculate.addActionListener(this);
        //�رհ�ť JButton
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
        setAlwaysOnTop(true); //���ô���������ǰ
        //��������϶��¼�
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

    //��ʼ����ֵ��Χ
    public void setData(JTableScope scope) {
        result_data = 0 ;
        this.start_Row = scope.getStart_Row();
        this.start_Column = scope.getStart_Clom();
        this.end_Row = scope.getEnd_Row();
        this.end_Column = scope.getEnd_Clom();
        scope_label.setText("ѡ��ķ�Χ:" + (char) (start_Column + 65) + "" + (start_Row+1) + ":" + (char) (end_Column + 65) + "" + (end_Row+1));
        int sum = calculate();
        result.setText("�����:" + sum);
    }

    //���������
    public void fillData(int row, int col) {
        JTableModel model = (JTableModel) table.getTable().getModel();
        model.setValueAt(result_data, row, col);
        this.dispose();
    }

    //����
    public int calculate() {
        // ��� , ��ƽ��
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
        //�����Сֵ��ȡ
        if (model == Frame_Calculate.GET_MAX || model == Frame_Calculate.GET_MIN) {
            int temp = Integer.valueOf(contents.get(start_Row).get(start_Column).toString().trim());
            for (int i = start_Row + 1; i <= end_Row; i++) {
                for (int j = start_Column; j <= end_Column; j++) {
                    int s = Integer.valueOf(contents.get(i).get(j).toString().trim());
                    if (model == Frame_Calculate.GET_MAX) {
                        //��ȡ���ֵ
                        result_data = s > temp ? s : temp;
                    } else {
                        //��ȡ��Сֵ
                        result_data = s < temp ? s : temp;
                    }
                }
            }
        }
        return result_data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("�������")) {
            //ͨ���Է�Χ����ֵ�жϾ�����������λ��
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
        if (e.getActionCommand().equals("�ر�")) {
            this.dispose();
        }
    }
}
