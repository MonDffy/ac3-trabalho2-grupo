import java.util.ArrayList;

public class FPRegisterStatus {

    String title = "FP Register Status";
    ArrayList<String> line1 = new ArrayList<String>();
    ArrayList<String> line2 = new ArrayList<String>();
    ArrayList<String> line3 = new ArrayList<String>();

    String getTitle() {
        return title;
    }

    String getLine1(int index) {
        return line1.get(index);
    }

    void setLine2(int index, String ent) {
        line2.set(index + 1, ent);
    }

    String getLine2(int index) {
        return line2.get(index);
    }

    void setLine3(int index, String ent) {
        line3.set(index + 1, ent);
    }

    String getLine3(int index) {
        return line3.get(index);
    }

    int getLineSize() {
        return line1.size();
    }

    FPRegisterStatus() {
        line1.add("Field");
        line2.add("Reorder #");
        line3.add("Busy");
        for (int i = 0; i < 30; i++) {
            line1.add("F" + i);
            line2.add("");
            line3.add("No");
        }

    }

}
