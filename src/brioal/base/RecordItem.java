package brioal.base;

/**
 * Created by brioal on 15-10-10.
 * 用于存储record_panel内item的数据base
 */

public class RecordItem {
    private String filename; // 文件名
    private String filepath; // 文件路径


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
