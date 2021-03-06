package com.luxoft.logeek.file;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;

public class FileReaderWriter {

    public static File doInEffectively(File file, SXSSFWorkbook workbook) throws IOException {
        try (FileOutputStream stream = new FileOutputStream(file)) {
            workbook.write(stream);
            return file;
        }
    }

    public static File doEffectively(File file, ByteArrayOutputStream baos, SXSSFWorkbook workbook) throws IOException {
        workbook.write(baos);
        FileUtils.writeByteArrayToFile(file, baos.toByteArray());

        return file;
    }
}
