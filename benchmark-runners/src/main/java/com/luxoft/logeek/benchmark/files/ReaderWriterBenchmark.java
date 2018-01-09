package com.luxoft.logeek.benchmark.files;

import com.luxoft.logeek.file.FileReaderWriter;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.openjdk.jmh.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Fork(10)
@State(Scope.Benchmark)
@Warmup(batchSize = 10, iterations = 10)
@Measurement(batchSize = 10, iterations = 100)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ReaderWriterBenchmark {

    private List<File> files = new ArrayList<>();
    private File temp;
//    private SXSSFWorkbook workbook;
    private ByteArrayOutputStream baos;

    @Setup(Level.Invocation)
    public void setup() throws IOException {
        temp = Files.createTempFile("temp", ".xslx").toFile();

//        workbook = new SXSSFWorkbook();
//        IntStream.range(0, 9).forEach(i -> workbook.createSheet());

        baos = new ByteArrayOutputStream();
    }

    @TearDown
    public void tearDown() {
        files.forEach(File::delete);
        files.clear();
    }

//    @Benchmark
//    public File measureDoInEffectively() throws IOException {
//        return FileReaderWriter.doInEffectively(temp, workbook);
//    }

//    @Benchmark
//    public File measureDoEffectively() throws IOException {
//        return FileReaderWriter.doEffectively(temp, baos, workbook);
//    }
}
