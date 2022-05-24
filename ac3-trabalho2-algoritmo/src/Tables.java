public class Tables {

    static String getColumn1(InstructionStatus instructionStatus) {
        String text = "<tr>";
        ;
        for (int i = 0; i < 4; i++) {
            text += "<th>" +
                    instructionStatus.getColumn(i) +
                    "</th>";
        }
        text += "</tr>";
        return text;
    }

    static String getRow1(InstructionStatus instructionStatus) {
        String text = "";
        for (int i = 0; i < instructionStatus.getInstructionSize(); i++) {
            text += "<tr>" +
                    "<td>" +
                    instructionStatus.getInstruction(i) +
                    "</td>" +
                    "<td>" +
                    instructionStatus.getIssue(i) +
                    "</td>" +
                    "<td>" +
                    instructionStatus.getExecute(i) +
                    "</td>" +
                    "<td>" +
                    instructionStatus.getWriteResult(i) +
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
                    reservationStations.getA(i) +
                    "</td>" +
                    "</tr>";
        }
        return text;
    }

    static String getColumn3(RegisterStatus registerStatus) {
        String text = "<tr>";
        for (int i = 0; i < registerStatus.getLineSize(); i++) {
            text += "<th>" +
                    registerStatus.getLine1(i) +
                    "</th>";
        }
        text += "</tr>";
        return text;
    }

    static String getRow3(RegisterStatus registerStatus) {
        String text = "<tr>";
        for (int i = 0; i < registerStatus.getLineSize(); i++) {
            text += "<td>" +
                    registerStatus.getLine2(i) +
                    "</td>";
        }
        text += "</tr>";
        return text;
    }

    static String getTables(InstructionStatus instructionStatus, ReservationStations reservationStations,
            RegisterStatus registerStatus) {
        String column1 = getColumn1(instructionStatus);
        String row1 = getRow1(instructionStatus);
        String column2 = getColumn2(reservationStations);
        String row2 = getRow2(reservationStations);
        String column3 = getColumn3(registerStatus);
        String row3 = getRow3(registerStatus);
        String text = "<html>" +
                "<table border='1'>" +
                "<tr>" +
                "<th colsplan='4'>" +
                instructionStatus.getTitle() +
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
