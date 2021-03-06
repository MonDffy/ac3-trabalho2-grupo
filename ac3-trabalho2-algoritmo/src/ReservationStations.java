public class ReservationStations {

    String title = "Reservation Stations";
    String[] column = { "Name", "Busy", "Op", "Vj", "Vk", "Qj", "Qk", "Dest", "A" };
    String name[] = new String[8];
    String busy[] = new String[8];
    String op[] = new String[8];
    String vj[] = new String[8];
    String vk[] = new String[8];
    String qj[] = new String[8];
    String qk[] = new String[8];
    String dest[] = new String[8];
    String a[] = new String[8];
    int[] count = new int[8];

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

    int getNotBusy() {
        for (int i = 0; i < busy.length; i++) {
            if (busy[i] == "No")
                return i;
        }
        return -1;
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
        qk[index] = ent;
    }

    String getQk(int index) {
        return qk[index];
    }

    void setDest(int index, String ent) {
        dest[index] = ent;
    }

    String getDest(int index) {
        return dest[index];
    }

    void setA(int index, String ent) {
        a[index] = ent;
    }

    String getA(int index) {
        return a[index];
    }

    void setCount(int index, int ent) {
        count[index] = ent;
    }

    int getCount(int index) {
        return count[index];
    }

    void deleteRow() {
        for (int i = 0; i < name.length; i++) {
            busy[i] = "No";
            op[i] = "";
            vj[i] = "";
            vk[i] = "";
            qj[i] = "";
            qk[i] = "";
            dest[i] = "";
            a[i] = "";
        }
    }

    ReservationStations() {

        name[0] = "Load1";
        name[1] = "Load2";
        name[2] = "Add1";
        name[3] = "Add2";
        name[4] = "Add3";
        name[5] = "Mult1";
        name[6] = "Mult2";
        name[7] = "BEQ";
        for (int i = 0; i < 8; i++) {
            busy[i] = "No";
            op[i] = "";
            vj[i] = "";
            vk[i] = "";
            qj[i] = "";
            qk[i] = "";
            dest[i] = "";
            a[i] = "";
        }

    }

}
