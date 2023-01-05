package com.zebra.connectivitydemo;

import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.printer.PrinterStatus;
import com.zebra.sdk.printer.ZebraPrinter;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.ZebraPrinterLinkOs;

public class WristbandPrinterStatus {


    public enum ePrinterStatus {
        Idle,
        OutOfPaper,
        Paused,
        HeadOpen
    }


    public static ePrinterStatus checkPrinterStatus(ZebraPrinter printer) {

        ePrinterStatus m_printerStatus = ePrinterStatus.Idle;


        try {
            ZebraPrinterLinkOs xlinkOsPrinter = ZebraPrinterFactory.createLinkOsPrinter(printer);

            PrinterStatus printerStatus = (xlinkOsPrinter != null) ? xlinkOsPrinter.getCurrentStatus() : printer.getCurrentStatus();

            if (printerStatus.isReadyToPrint) {
                m_printerStatus = ePrinterStatus.Idle;
            } else if (printerStatus.isHeadOpen) {
                m_printerStatus = ePrinterStatus.HeadOpen;
            } else if (printerStatus.isPaused) {
                m_printerStatus = ePrinterStatus.Paused;
            } else if (printerStatus.isPaperOut) {
                m_printerStatus = ePrinterStatus.OutOfPaper;
            }
        } catch (ConnectionException e) {
            //setStatus(e.getMessage(), Color.RED);
        }
        return m_printerStatus;
    }


}
