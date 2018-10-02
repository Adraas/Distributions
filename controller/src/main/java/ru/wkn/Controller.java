package ru.wkn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Controller {

    public void listenRequestAndSendResponse(Reader reader, Writer writer) throws IOException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        listenRequestFromBuffer(bufferedReader);
        sendResponseToBuffer(bufferedWriter);
    }

    private void listenRequestFromBuffer(BufferedReader bufferedReader) throws IOException, InterruptedException {
        while (!bufferedReader.ready()) {
            Thread.sleep(1000);
        }
        // some instructions
    }

    private void sendResponseToBuffer(BufferedWriter bufferedWriter) {
        DistributorFacade distributorFacade;
        // some instructions
    }

    private String[] parseBufferToStringArray(BufferedReader bufferedReader) {
        String[] parameters = null;
        // some instructions
        return parameters;
    }
}
