package com.youbao.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class FileUtils {
	public static final String PREFIX = "stream2file";
    public static final String SUFFIX = ".tmp";
    public static InputStream file2Stream(File file) throws Exception{
    	InputStream fileInputStream = new FileInputStream(file);
 	   return fileInputStream;
    }
	public static void stream2file (InputStream in, File tempFile, Stram2FileCallback callback) throws IOException {
        FileOutputStream out = new FileOutputStream(tempFile);
        BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(in));
			String data =br.readLine();
			while (data != null) {
				data = callback.lineMapTo(data);
//				String[] colums = data.split(",");
//				if(FileSystemService.LEN_COLUM_DATA == colums.length) {
//					data = colums[0] + "," + colums[1] + "," + colums[2] + "," + colums[3] + "," + "scuiot" + "," + colums[5] + "," +
//							colums[6] + "," + colums[7] + "," + colums[8] + "," + colums[9] + "," + colums[10] + "," + colums[11] + "," + 
//							colums[12] + "," + colums[13] + "," + colums[14] + "," + colums[15] + "," + colums[16];
//				}
				out.write(data.getBytes());
				data =br.readLine();
			}
		} finally {
			br.close();
			out.close();
		}
    }
}
