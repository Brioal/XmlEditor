package brioal.uioverride;

import brioal.interfaces.UiCommon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by brioal on 15-10-10.
 * �˵���
 * ��������ΪActionListener���¼�����
 */

public class MyMenuBar extends JMenuBar implements UiCommon {
    final JMenu []  menus = new JMenu[]{
            new JMenu("�ļ�"),
            new JMenu("�༭"),
            new JMenu("��ʽ"),
            new JMenu("����"),
            new JMenu("����")
    };
    JMenu style = new JMenu("���뷽ʽ");
    JMenuItem styles [] = new JMenuItem[]{
       new JMenuItem("����"),
       new JMenuItem("����"),
       new JMenuItem("����"),
    } ;

    final JMenuItem [] [] menuItems = new JMenuItem[][]{
            { // �ļ�
                    new JMenuItem("�½�"),
                    new JMenuItem("��"),
                    new JMenuItem("����"),
                    new JMenuItem("���Ϊ"),
                    new JMenuItem("�ر�"),
                    new JMenuItem("�˳�"),
            },
            { // �༭
                    new JMenuItem("ѡ��ȫ��"),
                    new JMenuItem("ȡ��ѡ��"),
                    new JMenuItem("����"),
                    new JMenuItem("����"),
                    new JMenuItem("ճ��"),
                    new JMenuItem("�����"),
                    new JMenuItem("ɾ����"),
            },
            { // ��ʽ
                    new JMenuItem("ǰ��ɫ"),
                    new JMenuItem("����ɫ"),
                    new JMenuItem("ѡ�б���"),
                    style

            },
            {  //����
                    new JMenuItem("���"),
                    new JMenuItem("��ƽ��"),
                    new JMenuItem("�����ֵ"),
                    new JMenuItem("����Сֵ"),
            },
            { // ����
                    new JMenuItem("�ܽ�"),
                    new JMenuItem("��������"),

            }
    };
    private ActionListener listener ;

    public MyMenuBar(ActionListener listener) {
        this.listener = listener;
        initComponent();
        setComponent();
        addComponent();
    }

    @Override
    public void initComponent() {
        for (int i = 0; i < styles.length; i++) {
            styles[i].addActionListener(listener);
            style.add(styles[i]);
        }

        for (int i = 0; i < menus.length; i++) {
            for (int j = 0; j < menuItems[i].length; j++) {
                menuItems[i][j].setFont(new Font("����", Font.PLAIN, 15));
                menus[i].setFont(new Font("����", Font.PLAIN, 15));
                menus[i].add(menuItems[i][j]);
                menuItems[i][j].addActionListener(listener);
            }
            add(menus[i]);
        }
    }

    @Override
    public void setComponent() {

    }

    @Override
    public void addComponent() {

    }
}
