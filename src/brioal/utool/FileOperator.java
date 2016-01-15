package brioal.utool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by brioal on 15-10-12.
 * �ļ���̬������
 *�����ļ���������Դ���ļ���
 * ��ȡ�ļ������ļ���,��������Դ
 */

public class FileOperator {
    //����list��·��,��������
    public static void Save(List<Vector> contents, File file) {
        try {
            Vector content;
            FileWriter fileWritter = new FileWriter(file);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

            for (int i = 0; i < contents.size(); i++) {
                content = contents.get(i);
                for (int j = 0; j < contents.get(i).size(); j++) {
                    String str = "";
                    str += content.get(j) + "<";
                    bufferWritter.write(str);
                }
                bufferWritter.newLine();
            }
            bufferWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //    ����·��,��ȡlist
    public static ArrayList<Vector> Read(File file) {
        ArrayList<Vector> contents = new ArrayList<Vector>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Vector content = null;
            String str = "";

            while ((str = bufferedReader.readLine()) != null) {
                String[] arr = str.split("<");
                content = new Vector();
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].equals("false")) {
                        boolean b = new Boolean(false);
                        content.add(b);
                    } else {

                        content.add(arr[i]);
                    }
                }
                contents.add(content);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contents;
    }
}
