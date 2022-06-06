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
        for (int i = 0; i < reorderBuffer.getEntrySize(); i++) {
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
                    "#" + reservationStations.getDest(i) +
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

    static String getColumn4(InstructionStatus instructionStatus) {
        String text = "<tr>";
        for (int i = 0; i < instructionStatus.getColumnSize(); i++) {
            text += "<th>" +
                    instructionStatus.getColumn(i) +
                    "</th>";
        }
        text += "</tr>";
        return text;
    }

    static String getRow4(InstructionStatus instructionStatus) {
        String text = "";
        for (int i = 0; i < instructionStatus.getStatusSize(); i++) {
            text += "<tr>" +
                    "<td>" +
                    instructionStatus.getInstruction(i) +
                    "</td>" +
                    "<td>" +
                    instructionStatus.getStatus(i) +
                    "</td>" +
                    "</tr>";
        }
        return text;
    }

    static String getTables(InstructionStatus instructionStatus, ReorderBuffer reorderBuffer,
            ReservationStations reservationStations,
            FPRegisterStatus registerStatus) {
        String column1 = getColumn1(reorderBuffer);
        String row1 = getRow1(reorderBuffer);
        String column2 = getColumn2(reservationStations);
        String row2 = getRow2(reservationStations);
        String column3 = getColumn3(registerStatus);
        String row3 = getRow3(registerStatus);
        String column4 = getColumn4(instructionStatus);
        String row4 = getRow4(instructionStatus);

        String text = "<html>" +
        // "<head>" +
        // "<title>Bootstrap Example</title>" +
        // "<meta charset='utf-8>'" +
        // "<meta name='viewport' content='width=device-width, initial-scale=1'>" +
        // "<link rel='stylesheet'
        // href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>"
        // +
        // "<script>" +
        // src='https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>"
        // +
        // "<script>" +
        // src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>"
        // +
        // "</head>" +

                "<style>" +
                "div {" +
                "font-size:70%" +
                "}" +
                // "#d1 {" +
                // "background-color: blue;" +
                // "display: inline;" +
                // "}" +
                // "#d2 {" +
                // "background-color: red;" +
                // "display: inline;" +
                // "}" +
                // "#d3 {" +
                // "background-color: green;" +
                // "display: inline;" +
                // "}" +
                // "#d4 {" +
                // "background-color: yellow;" +
                // "display: inline;" +
                // "}" +

                "</style>" +
                "<div id='d1' class = 'col-1'>" +
                "<table border='1' style = 'font-size:70%'>" +
                "<tr>" +
                "<th colsplan='4'>" +
                "InstructionStatus.getTitle()" +
                "</th>" +
                "</tr>" +
                column4 +
                row4 +
                "</table>" +
                "</div>" +
                "<div id='d2' class = 'col-11'  face='Times'>" +
                "<table border='1'>" +
                "<tr>" +
                "<th colsplan='4'>" +
                reorderBuffer.getTitle() +
                "</th>" +
                "</tr>" +
                column1 +
                row1 +
                "</table>" +
                "</div>" +
                "<div id='d3' class = 'col-6'>" +
                "<table border='1'>" +
                "<tr>" +
                "<th colsplan='4'>" +
                reservationStations.getTitle() +
                "</th>" +
                "</tr>" +
                column2 +
                row2 +
                "</table>" +
                "</div>" +
                "<div id='d4' class = 'col-6'>" +
                "<table border='1'>" +
                "<tr>" +
                "<th colsplan='4'>" +
                registerStatus.getTitle() +
                "</th>" +
                "</tr>" +
                column3 +
                row3 +
                "</table>" +
                "</div>" +
                "</html>";
        return text;
    }
}
