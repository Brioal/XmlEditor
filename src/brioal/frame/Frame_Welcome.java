package brioal.frame;

import brioal.base.RecordItem;
import brioal.interfaces.UiCommon;
import brioal.panel.Panel_Logo;
import brioal.panel.Panel_Operator;
import brioal.panel.Panel_Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by brioal on 15-10-9.
 * ???????,?????????????,????????,logo
 */

public class Frame_Welcome extends JFrame implements UiCommon {
    private Panel_Logo panel_logo; //???logo
    private Panel_Record panel_record; // ?????????
    private Panel_Operator panel_operator; //?????????????
    private ActionListener listener; // ?????????
    private java.util.List<RecordItem> lists; // JList??????
    private FileDialog fileDialog;


    public Frame_Welcome() {
        super("Welcome to Mini Excel!");
        setIconImage(new ImageIcon("drawable/icon.png").getImage());
        initComponent();
        setComponent();
        addComponent();
        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void initComponent() {
        //?????????????
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("新建")) {
                    new Frame_Main(null);
                    Frame_Welcome.this.dispose();
                }
                if (e.getActionCommand().equals("打开")) {
                    fileDialog = new FileDialog(Frame_Welcome.this);
                    fileDialog.setMode(FileDialog.LOAD);
                    fileDialog.setVisible(true);
                    if (fileDialog.getFile() != null) {
                        File file = new File(fileDialog.getFile());
                        new Frame_Main(file);
                        addRecord(new RecordItem(file.getName(),file.getAbsolutePath()));
                    }
                    Frame_Welcome.this.dispose();
                }
                if (e.getActionCommand().equals("关于")) {
                    new Frame_Summary();
                }
            }
        };
        panel_logo = new Panel_Logo();
        lists = initRecoder();//??????????
        panel_record = new Panel_Record(lists, this); // ?????????????
        panel_operator = new Panel_Operator(this, listener); // ???????????????
    }

    @Override
    public void setComponent() {
        //????????????
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        getContentPane().setLayout(null);
//        ????panel??С
        panel_logo.setSize(498, 378);
        panel_operator.setSize(498, 198);
        panel_record.setSize(300, 600);
//        ????panelλ??
        panel_logo.setLocation(302, 0);
        panel_record.setLocation(0, -10);
        panel_operator.setLocation(302, 380);

    }

    @Override
    public void addComponent() {
        add(panel_logo);
        add(panel_record);
        add(panel_operator);
    }

    //???????????????
    public java.util.List<RecordItem> initRecoder() {
        java.util.List<RecordItem> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("data/history.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String record = "";
            RecordItem item = null;
            while (null != (record = reader.readLine())) {
                String records[] = record.split("<");
                item = new RecordItem(records[0], records[1]);
                System.out.println(item);
                list.add(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    //??????????????
    public void addRecord(RecordItem item) {
        try {
            FileWriter fileWriter = new FileWriter("data/history.txt", true); // true??????????
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (lists.size() == 0) {
                bufferedWriter.write(item.toString());
                bufferedWriter.flush();
            } else {
                for (int i = 0; i < lists.size(); i++) {
                    if (!item.equals(lists.get(i))) {
                        bufferedWriter.newLine();
                        bufferedWriter.write(item.toString());
                        bufferedWriter.flush();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
