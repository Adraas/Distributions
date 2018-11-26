package ru.wkn.streams;

public class StreamFactory implements StreamFactoryIF<Stream> {

    @Override
    public Stream createStreamByType(String typeOfStream) {
        return typeOfStream.equals("palm-stream") ? new PalmStream()
                : null;
    }
}
