public class Tables {

    static String getColumn1(ReorderBuffer reorderBuffer) {
        String text = "<tr>";
        for (int i = 0; i < reorderBuffer.getColumnSize(); i++) {
            text += "<th>" +
                    reorderBuffer.getColumn(i) +
                    "</th>";
        }
        text += "</tr>";
        return text;
    }

    static String getRow1(ReorderBuffer reorderBuffer) {
        String text = "";
        for (int i = 0; i < reorderBuffer.getColumnSize(); i++) {
            text += "<tr>" +
                    "<td>" +
                    reorderBuffer.getEntry(i) +
                    "</td>" +
                    "<td>" +
                    reorderBuffer.getBusy(i) +
                    "</td>" +
                    "<td>" +
                    reorderBuffer.getInstruction(i) +
                    "</td>" +
                    "<td>" +
                    reorderBuffer.getState(i) +
                    "</td>" +
                    "<td>" +
                    reorderBuffer.getDestination(i) +
                    "</td>" +
                    "<td>" +
                    reorderBuffer.getValue(i) +
                    "</td>" +
                    "</tr>";
        }
        return text;
    }

    static String getColumn2(ReservationStations reservationStations) {
        String text = "<tr>";
        ;
        for (int i = 0; i < reservationStations.getColumnSize(); i++) {
            text += "<th>" +
                    reservationStations.getColumn(i) +
                    "</th>";
        }
        text += "</tr>";
        return text;
    }

    static String getRow2(ReservationStations reservationStations) {
        String text = "";
        for (int i = 0; i < 7; i++) {
            text += "<tr>" +
                    "<td>" +
                    reservationStations.getName(i) +
                    "</td>" +
                    "<td>" +
                    reservationStations.getBusy(i) +
                    "</td>" +
                    "<td>" +
                    reservationStations.getOp(i) +
                    "</td>" +
                    "<td>" +
                    reservationStations.getVj(i) +
                    "</td>" +
                    "<td>" +
                    reservationStations.getVk(i) +
                    "</td>" +
                    "<td>" +
                    reservationStations.getQj(i) +
                    "</td>" +
                    "<td>" +
                    reservationStations.getQk(i) +
                    "</td>" +
                    "<td>" +
                    reservationStations.getDest(i) +
                    "</td>" +
                    "<td>" +
                    reservationStations.getA(i) +
                    "</td>" +
                    "</tr>";
        }
        return text;
    }

    static String getColumn3(FPRegisterStatus registerStatus) {
        String text = "<tr>";
        for (int i = 0; i < registerStatus.getLineSize(); i++) {
            text += "<th>" +
                    registerStatus.getLine1(i) +
                    "</th>";
        }
        text += "</tr>";
        return text;
    }

    static String getRow3(FPRegisterStatus registerStatus) {
        String text = "<tr>";
        for (int i = 0; i < registerStatus.getLineSize(); i++) {
            text += "<td>" +
                    registerStatus.getLine2(i) +
                    "</td>";
        }
        text += "</tr>" +
                "<tr>";
        for (int i = 0; i < registerStatus.getLineSize(); i++) {
            text += "<td>" +
                    registerStatus.getLine3(i) +
                    "</td>";
        }
        return text += "</tr>";
    }

    static String getTables(ReorderBuffer reorderBuffer, ReservationStations reservationStations,
            FPRegisterStatus registerStatus) {
        String column1 = getColumn1(reorderBuffer);
        String row1 = getRow1(reorderBuffer);
        String column2 = getColumn2(reservationStations);
        String row2 = getRow2(reservationStations);
        String column3 = getColumn3(registerStatus);
        String row3 = getRow3(registerStatus);
        String text = "<html>" +
                "<table border='1'>" +
                "<tr>" +
                "<th colsplan='4'>" +
                reorderBuffer.getTitle() +
                "</th>" +
                "</tr>" +
                column1 +
                row1 +
                "</table>" +
                "<br>" +
                "<table border='1'>" +
                "<tr>" +
                "<th colsplan='4'>" +
                reservationStations.getTitle() +
                "</th>" +
                "</tr>" +
                column2 +
                row2 +
                "</table>" +
                "<br>" +
                "<table border='1'>" +
                "<tr>" +
                "<th colsplan='4'>" +
                registerStatus.getTitle() +
                "</th>" +
                "</tr>" +
                column3 +
                row3 +
                "</table>" +
                "</html>";
        return text;
    }
}
