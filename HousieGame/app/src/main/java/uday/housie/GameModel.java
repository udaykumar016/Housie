package uday.housie;

/**
 * Created by Lakki on 09-Jul-16.
 */
public class GameModel {


    public GameModel() {
    }

    String id;
    int no;
    boolean select;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public GameModel(String id, int no, boolean select) {
        this.id = id;
        this.no = no;
        this.select = select;
    }


}
