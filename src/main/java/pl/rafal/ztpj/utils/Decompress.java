package pl.rafal.ztpj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Decompress {

    public static void decompress_gzip(String source, String destination) throws IOException {
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination);
        GZIPInputStream gzis = new GZIPInputStream(fis);
        int read;
        while ((read = gzis.read(buffer)) != -1){
            fos.write(buffer, 0, read);
        }
        gzis.close();
        fis.close();
        fos.close();
    }

    public static void decompress_zip(String source, String destination) throws IOException {
        String fileZip = source;
        File destDir = new File(destination);
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
