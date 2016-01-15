package brioal.uioverride;

import brioal.interfaces.UiCommon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by brioal on 15-10-10.
 * 菜单栏
 * 传入数据为ActionListener绑定事件监听
 */

public class MyMenuBar extends JMenuBar implements UiCommon {
    final JMenu []  menus = new JMenu[]{
            new JMenu("文件"),
            new JMenu("编辑"),
            new JMenu("格式"),
            new JMenu("数据"),
            new JMenu("关于")
    };
    JMenu style = new JMenu("对齐方式");
    JMenuItem styles [] = new JMenuItem[]{
       new JMenuItem("居中"),
       new JMenuItem("居左"),
       new JMenuItem("居右"),
    } ;

    final JMenuItem [] [] menuItems = new JMenuItem[][]{
            { // 文件
                    new JMenuItem("新建"),
                    new JMenuItem("打开"),
                    new JMenuItem("保存"),
                    new JMenuItem("另存为"),
                    new JMenuItem("关闭"),
                    new JMenuItem("退出"),
            },
            { // 编辑
                    new JMenuItem("选择全部"),
                    new JMenuItem("取消选择"),
                    new JMenuItem("复制"),
                    new JMenuItem("剪切"),
                    new JMenuItem("粘贴"),
                    new JMenuItem("添加行"),
                    new JMenuItem("删除行"),
            },
            { // 格式
                    new JMenuItem("前景色"),
                    new JMenuItem("背景色"),
                    new JMenuItem("选中背景"),
                    style

            },
            {  //数据
                    new JMenuItem("求和"),
                    new JMenuItem("求平均"),
                    new JMenuItem("求最大值"),
                    new JMenuItem("求最小值"),
            },
            { // 关于
                    new JMenuItem("总结"),
                    new JMenuItem("关于作者"),

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
                menuItems[i][j].setFont(new Font("宋体", Font.PLAIN, 15));
                menus[i].setFont(new Font("宋体", Font.PLAIN, 15));
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
