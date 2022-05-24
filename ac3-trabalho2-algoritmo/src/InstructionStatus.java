import java.util.ArrayList;

public class InstructionStatus {

    String title = "Instruction Status";
    String[] column = { "Instruction", "Issue", "Execute", "Write Result" };
    ArrayList<String> instruction = new ArrayList<String>();
    ArrayList<String> issue = new ArrayList<String>();
    ArrayList<String> execute = new ArrayList<String>();
    ArrayList<String> writeResult = new ArrayList<String>();

    void setInstructions(ArrayList<String> input) {
        for (int i = 0; i < input.size(); i++) {
            instruction.add(i, input.get(i));
        }
        for (int i = 0; i < input.size(); i++) {
            issue.add(i, " ");
            execute.add(i, " ");
            writeResult.add(i, " ");
        }
    }

    String getTitle() {
        return this.title;
    }

    String getColumn(int i) {
        return this.column[i];
    }

    String getInstruction(int index) {
        return this.instruction.get(index);
    }

    int getInstructionSize() {
        return this.instruction.size();
    }

    void setIssue(int index) {
        this.issue.set(index, "x");
    }

    String getIssue(int index) {
        return this.issue.get(index);
    }

    void setExecute(int index) {
        this.execute.set(index, "x");
    }

    String getExecute(int index) {
        return this.execute.get(index);
    }

    void setWriteResult(int index) {
        this.writeResult.set(index, "x");
    }

    String getWriteResult(int index) {
        return this.writeResult.get(index);
    }

}
