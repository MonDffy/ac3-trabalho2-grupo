public class ReservationStations {

    String title = "Reservation Stations";
    String[] column = { "Name", "Busy", "Op", "Vj", "Vk", "Qj", "Qk", "A" };
    String name[] = new String[7];
    String busy[] = new String[7];
    String op[] = new String[7];
    String vj[] = new String[7];
    String vk[] = new String[7];
    String qj[] = new String[7];
    String qk[] = new String[7];
    String a[] = new String[7];

    String getTitle() {
        return title;
    }

    String getColumn(int index) {
        return column[index];
    }

    int getColumnSize() {
        return column.length;
    }

    String getName(int index) {
        return name[index];
    }

    void setBusy(int index, String ent) {
        busy[index] = ent;
    }

    String getBusy(int index) {
        return busy[index];
    }

    void setOp(int index, String ent) {
        op[index] = ent;
    }

    String getOp(int index) {
        return op[index];
    }

    void setVj(int index, String ent) {
        vj[index] = ent;
    }

    String getVj(int index) {
        return vj[index];
    }

    void setVk(int index, String ent) {
        vk[index] = ent;
    }

    String getVk(int index) {
        return vk[index];
    }

    void setQj(int index, String ent) {
        qj[index] = ent;
    }

    String getQj(int index) {
        return qj[index];
    }

    void setQk(int index, String ent) {
        qj[index] = ent;
    }

    String getQk(int index) {
        return qk[index];
    }

    void setA(int index, String ent) {
        a[index] = ent;
    }

    String getA(int index) {
        return a[index];
    }

    ReservationStations() {

        name[0] = "Load1";
        name[1] = "Load2";
        name[2] = "Add1";
        name[3] = "Add2";
        name[4] = "Add3";
        name[5] = "Mult1";
        name[6] = "Mult2";
        for (int i = 0; i < 7; i++) {
            busy[i] = "No";
            op[i] = " ";
            vj[i] = " ";
            vk[i] = " ";
            qj[i] = " ";
            qk[i] = " ";
            a[i] = " ";
        }

    }

}
