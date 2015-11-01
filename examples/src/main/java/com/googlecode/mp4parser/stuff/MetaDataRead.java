package com.googlecode.mp4parser.stuff;

import com.coremedia.iso.IsoFile;
import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.boxes.apple.AppleNameBox;
import com.googlecode.mp4parser.util.Path;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Change metadata and make sure chunkoffsets are corrected.
 */
public class MetaDataRead {


    public static void main(String[] args) throws IOException {
        MetaDataRead cmd = new MetaDataRead();
        String xml = cmd.read("C:\\content\\Mobile_H264.mp4");
        System.err.println(xml);
    }

    public String read(String videoFilePath) throws IOException {

        File videoFile = new File(videoFilePath);
        if (!videoFile.exists()) {
            throw new FileNotFoundException("File " + videoFilePath + " not exists");
        }

        if (!videoFile.canRead()) {
            throw new IllegalStateException("No read permissions to file " + videoFilePath);
        }
        IsoFile isoFile = new IsoFile(new FileDataSourceImpl(videoFilePath));

        AppleNameBox nam = Path.getPath(isoFile, "/moov[0]/udta[0]/meta[0]/ilst/©nam");
        String xml = nam.getValue();
        isoFile.close();
        return xml;
    }
}
