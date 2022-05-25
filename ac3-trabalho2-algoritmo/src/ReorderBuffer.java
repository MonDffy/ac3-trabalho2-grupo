import java.util.ArrayList;

public class ReorderBuffer {

    String title = "Reorder Buffer";
    String[] column = { "Entry", "Busy", "Instruction", "State", "Destination", "Value" };
    ArrayList<String> entry = new ArrayList<String>();
    ArrayList<String> busy = new ArrayList<String>();
    ArrayList<String> instruction = new ArrayList<String>();
    ArrayList<String> state = new ArrayList<String>();
    ArrayList<String> destination = new ArrayList<String>();
    ArrayList<String> values = new ArrayList<String>();

    String getTitle() {
        return this.title;
    }

    String getColumn(int i) {
        return this.column[i];
    }

    int getColumnSize() {
        return this.column.length;
    }

    String getEntry(int index) {
        return this.entry.get(index);
    }

    void setBusy(int index, String ent) {
        busy.set(index, ent);
    }

    String getBusy(int index) {
        return busy.get(index);
    }

    void setInstructions(ArrayList<String> input) {
        for (int i = 0; i < input.size(); i++) {
            instruction.add(i, input.get(i));
        }
        for (int i = 0; i < input.size(); i++) {
            String str = "" + i;
            entry.add(i, str);
            busy.add(i, "No");
            state.add(i, " ");
            destination.add(i, " ");
            values.add(i, " ");
        }
    }

    String getInstruction(int index) {
        return this.instruction.get(index);
    }

    void setState(int index, String ent) {
        state.set(index, ent);
    }

    String getState(int index) {
        return this.state.get(index);
    }

    void setDestination(ArrayList<String[]> instructions) {
        for (int i = 0; i < destination.size(); i++) {
            destination.set(i, instructions.get(i)[1]);
        }
    }

    String getDestination(int index) {
        return this.destination.get(index);
    }

    void setValue(int index, String ent) {
        values.set(index, ent);
    }

    String getValue(int index) {
        return this.values.get(index);
    }

}
