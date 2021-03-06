import java.util.ArrayList;

public class ReorderBuffer {

    String title = "Reorder Buffer";
    String[] column = { "Entry", "Busy", "Instruction", "State", "Destination", "Value" };
    ArrayList<String> entry = new ArrayList<String>();
    ArrayList<String> busy = new ArrayList<String>();
    ArrayList<String> instruction = new ArrayList<String>();
    ArrayList<String> state = new ArrayList<String>();
    ArrayList<String> destination = new ArrayList<String>();
    ArrayList<String> value = new ArrayList<String>();
    ArrayList<String> reorderList = new ArrayList<String>();
    ArrayList<Boolean> jump = new ArrayList<>();

    String getTitle() {
        return title;
    }

    String getColumn(int i) {
        return column[i];
    }

    int getColumnSize() {
        return column.length;
    }

    String getEntry(int index) {
        return entry.get(index);
    }

    int getEntrySize() {
        return entry.size();
    }

    void setBusy(int index, String ent) {
        busy.set(index, ent);
    }

    String getBusy(int index) {
        return busy.get(index);
    }

    int getNotBusy() {
        for (int i = 0; i < busy.size(); i++) {
            if (busy.get(i) == "No")
                return i;
        }
        return -1;
    }

    void setInstructions(int index, String input) {
        instruction.set(index, input);
    }

    String getInstruction(int index) {
        return instruction.get(index);
    }

    void setState(int index, String ent) {
        this.state.set(index, ent);
    }

    String getState(int index) {
        return state.get(index);
    }

    void setDestination(int index, String ent) {
        destination.set(index, ent);
    }

    String getDestination(int index) {
        return destination.get(index);
    }

    void setValue(int index, String ent) {
        value.set(index, ent);
    }

    String getValue(int index) {
        return value.get(index);
    }

    void setReorderList(String ent) {
        reorderList.add(ent);
    }

    void removeFromList(String s) {
        for (int i = 0; i < reorderList.size(); i++) {
            if (reorderList.get(i) == s) {
                reorderList.remove(i);
            }
        }
    }

    String getReorderList(int index) {
        return reorderList.get(index);
    }

    void deleteRow() {
        for (int i = 0; i < instruction.size() - 1; i++) {
            busy.set(i, busy.get(i + 1));
            instruction.set(i, instruction.get(i + 1));
            state.set(i, state.get(i + 1));
            destination.set(i, destination.get(i + 1));
            value.set(i, value.get(i + 1));
            jump.set(i, jump.get(i + 1));
        }
        instruction.set(5, "");
        busy.set(5, "No");
        state.set(5, "");
        destination.set(5, "");
        value.set(5, "");
        jump.set(5, false);
    }

    public Boolean getJump(int index) {
        return jump.get(index);
    }

    public void setJump(int index, Boolean ent) {
        jump.set(index, ent);
        System.out.println("Previs??o do jump: " + jump.get(index));
    }

    ReorderBuffer() {
        for (int i = 0; i < 6; i++) {
            instruction.add(i, "");
        }
        for (int i = 0; i < 6; i++) {
            String str = i + 1 + "";
            entry.add(i, str);
            busy.add(i, "No");
            state.add(i, "");
            destination.add(i, "");
            value.add(i, "");
            jump.add(i, false);
        }
    }

};