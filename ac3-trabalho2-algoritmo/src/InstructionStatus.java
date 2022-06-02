// Não vai ser usado

import java.util.ArrayList;

public class InstructionStatus {

    static String title = "Instruction Status";
    String[] column = { "Instruction", "Status" };
    ArrayList<String> instruction = new ArrayList<String>();
    ArrayList<String> status = new ArrayList<String>();
    int inst;

    void setInstructions(ArrayList<String> input) {
        for (int i = 0; i < input.size(); i++) {
            instruction.add(i, input.get(i));
            status.add(i, " ");
        }
        inst = -1;
    }

    static String getTitle() {
        return title;
    }

    String getColumn(int i) {
        return this.column[i];
    }

    int getColumnSize() {
        return this.column.length;
    }

    String getInstruction(int index) {
        return instruction.get(index);
    }

    String getNextInstruction() {
        nextInst();
        return instruction.get(inst);
    }

    void nextInst() {
        inst++;
    }


    int getInstructionSize() {
        return this.instruction.size();
    }
    

    void setStatus(int index, String ent) {
        this.status.set(index, ent);
    }

    String getStatus(int index) {
        return this.status.get(index);
    }

    int getStatusSize() {
        return this.status.size();
    }

}
