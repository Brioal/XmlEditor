package brioal.base;

/**
 * Created by Brioal on 2015/12/27.
 * 进行计算的时候传入到计算界面的点选范围base类
 * 包含开始行列值,结束行列值
 */
public class JTableScope {
    private int start_Row ;
    private int start_Clom ;
    private int end_Row ;
    private int end_Clom;

    public JTableScope() {
        this.start_Row = 0;
        this.start_Clom = 0;
        this.end_Row = 0;
        this.end_Clom = 0;

    }
    public JTableScope(int start_Row, int start_Clom, int end_Row, int end_Clom) {
        this.start_Row = start_Row;
        this.start_Clom = start_Clom;
        this.end_Row = end_Row;
        this.end_Clom = end_Clom;
    }

    public void setData(int start_Row, int start_Clom, int end_Row, int end_Clom) {
        this.start_Row = start_Row;
        this.start_Clom = start_Clom;
        this.end_Row = end_Row;
        this.end_Clom = end_Clom;
    }

    public int getStart_Row() {
        return start_Row;
    }

    public int getStart_Clom() {
        return start_Clom;
    }

    public int getEnd_Row() {
        return end_Row;
    }

    public int getEnd_Clom() {
        return end_Clom;
    }

    @Override
    public String toString() {
        return start_Row+":"+start_Clom+":"+end_Row+":"+end_Clom;
    }
}
