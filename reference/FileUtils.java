/*
 * Copyright(C) 2012 D-CIRCLE, INC. All rights reserved.
 *
 * (1)このソフトウェアは、ディサークル株式会社に帰属する機密情報 であり開示を固く禁じます。
 * (2)この情報を使用するには、ディサークル株式会社とのライセンス 契約が必要となります。
 *
 * This software is the confidential and proprietary information of
 * D-CIRCLE, INC. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license
 * agreement you entered into with D-CIRCLE.
 */

package net.poweregg.exkeihi.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;

/**
 * @author : phapnv
 * @PG_ID :
 * @createDate : Jul 26, 2012
 */
public class FileUtils implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The Unix separator character.
     */
    private static final char UNIX_SEPARATOR = '/';

    /**
     * The Windows separator character.
     */
    private static final char WINDOWS_SEPARATOR = '\\';
    /**
     *
     * @param dataBuff
     * @param fileName
     * @return
     * author phapnv
     */
    public static boolean writeFile(StringBuffer dataBuff, String fileName){
        try {
            File csvFile = new File(fileName);
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(csvFile, false), ConvertUtils.getEncoding());           // CSVファイルの作�??
            writer.write(dataBuff.toString());
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean copyFile(File source, String destinationPath){
        File destinationFile = new File(destinationPath);
         return copyFile(source, destinationFile);
    }

    /**
     *
     * @param source
     * @param destination
     * @return
     * author phapnv
     */
    public static boolean copyFile(File source, File destination){
        try{
            java.io.FileInputStream sourceFile = new java.io.FileInputStream(source);
            try{
                    java.io.FileOutputStream destinationFile = null;
                    try{
                        destinationFile = new FileOutputStream(destination);
                        byte buffer[] = new byte[512 * 1024];
                        int nbLecture;

                        while ((nbLecture = sourceFile.read(buffer)) != -1){
                                destinationFile.write(buffer, 0, nbLecture);
                        }
                    } finally {
                            destinationFile.close();
                    }
            } finally {
                    sourceFile.close();
            }
        } catch (IOException e){
                e.printStackTrace();
                return false;
        }
        return true;
    }


     /**
      *
      * @param source
      * @param destination
      * @return
      * author phapnv
      */
    public static boolean moveFile(File source,File destination) {
        if(!destination.exists()){
            boolean result = source.renameTo(destination);
            if( !result ) {
                result = true;
                result &= copyFile(source,destination);
                if(result){ result &= source.delete();}

            } return(result);
        } else {
                return(false);
        }
    }

    public static boolean deleteFile(String fileName){
        try{
            File fileDeleted = new File(fileName);
            if(fileDeleted.exists()){
               fileDeleted.delete();
            }
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean deleteFile(File file){
        try{
            if(null== file){
                return false;
            }
            if(file.exists()){
                file.delete();
            }
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param source
     * @param destinationPath
     * @return
     * author phapnv
     */
    public static boolean moveFile(File source, String destinationPath){
        File destinationFile = new File(destinationPath);
         return moveFile(source, destinationFile);
    }

    /**
     *
     * @param filename
     * @return
     * author phapnv
     */
    public static String getExtension(String filename) {
        String ext = null;
        String s = filename;
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    // hoctdy add start 2012.09.12
    public static String getName(String filename) {
        if (filename == null) {
            return null;
        }
        int index = indexOfLastSeparator(filename);
        return filename.substring(index + 1);
    }

    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        }
        int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
        int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    public static boolean writeFile(StringBuffer dataBuff, String fileName,String encoding){
        try {
            File csvFile = new File(fileName);
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(csvFile, false), encoding);           // CSVファイルの作�??
            writer.write(dataBuff.toString());
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    // hoctdy add start 2012.09.12

    /**
     * Close I/O stream
     */
    public static void closeStream(Closeable stream) {
    	if(null != stream) {
    		try {
    			stream.close();
    		} catch (IOException ioe) {
    			//無視にする
    		}
    	}
    }
}
