package brioal.panel;

import brioal.base.RecordItem;
import brioal.frame.Frame_Main;
import brioal.interfaces.UiCommon;
import brioal.uioverride.RocoderCell;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * Created by brioal on 15-10-9.
 * ������ʾ��ʷ��¼
 * ����ӭҳ�����
 * ����list����
 */

public class Panel_Record extends JPanel implements UiCommon {
    private JList<RecordItem> jlist; // list���
    private List<RecordItem> lists; //������ݵ�lis
    private ListModel<RecordItem> listModel;
    private  JScrollPane scrollPane ;
    private JFrame frame ;


    public Panel_Record(List<RecordItem> lists, JFrame frame) {
        this.lists = lists;
        this.frame = frame;
        initComponent();
        setComponent();
        addComponent();
    }

    @Override
    public void initComponent() {

        listModel = new AbstractListModel<RecordItem>() {
            @Override
            public int getSize() {
                return lists.size();
            }

            @Override
            public RecordItem getElementAt(int index) {
                return lists.get(index);
            }
        };
        jlist = new JList(listModel);
    }



    @Override
    public void setComponent() {
        setSize(300, 600);
        setBackground(Color.WHITE);
        jlist.setSize(300, 600);
        scrollPane = new JScrollPane(jlist);
        scrollPane.setSize(300,600);
        jlist.setCellRenderer(new RocoderCell());
        jlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                new Frame_Main(new File(lists.get(jlist.getSelectedIndex()).getFilepath()));
                frame.dispose();
            }
        });
    }

    @Override
    public void addComponent() {
        add(scrollPane);
    }

}
