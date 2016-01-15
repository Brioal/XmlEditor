package brioal.base;

/**
 * Created by brioal on 15-10-10.
 * ���ڴ洢record_panel��item������base
 */

public class RecordItem {
    private String filename; // �ļ���
    private String filepath; // �ļ�·��


    public RecordItem(String filename, String filepath) {
        this.filename = filename;
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public String getFilepath() {
        return filepath;
    }

    @Override
    public String toString() {
        return filename + "<" + filepath;
    }

    @Override
    public boolean equals(Object obj) {
        RecordItem item = (RecordItem) obj;
        if (filename.equals(item.getFilename()) && filepath.equals(item.getFilepath())) {
            return true;
        }
        return false;
    }
}
