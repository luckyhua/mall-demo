package com.luckyhua.demo.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static boolean isExist(String path) {
        return isExist(new File(path));
    }

    public static boolean isExist(File file) {
        return file.exists();
    }

    public static boolean isFolder(String folderPath) {
        return isFolder(new File(folderPath));
    }

    public static boolean isFolder(File folder) {
        return isExist(folder) && folder.isDirectory();
    }

    public static boolean isFile(String filePath) {
        return isFile(new File(filePath));
    }

    public static boolean isFile(File file) {
        return isExist(file) && file.isFile();
    }

    //新建文件夹
    public static boolean createFolder(String folderPath) {
        return createFolder(new File(folderPath));
    }

    //新建文件夹
    public static boolean createFolder(File folder) {
        if (folder.exists()) {
            if (folder.isFile()) {
                return false;
            }
            return true;
        } else {
            return folder.mkdirs();
        }
    }

    //新建文件
    public static boolean createFile(String filePath) {
        return createFile(new File(filePath));
    }

    //新建文件
    public static boolean createFile(File file) {
        try {
            if (file.exists()) {
                if (file.isFile()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //删除文件夹或文件
    public static boolean delete(String path) {
        return delete(new File(path));
    }

    //删除文件夹或文件
    public static boolean delete(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if (subFiles == null) {
                    return false;
                }

                for (File subFile : subFiles) {
                    if (!delete(subFile)) {
                        return false;
                    }
                }
            }

            if (!file.delete()) {
                return false;
            }
        }
        return true;
    }

    //遍历文件夹或文件
    public static List<String> enums(String path, String pattern, boolean isRecursive) {
        List<String> filePathList = new ArrayList<String>();
        for (File file : enums(new File(path), pattern, isRecursive)) {
            filePathList.add(file.getAbsolutePath());
        }
        return filePathList;
    }

    //遍历文件夹或文件
    public static List<File> enums(File file, String pattern, boolean isRecursive) {
        List<File> fileList = new ArrayList<File>();
        File[] subFiles = file.listFiles();
        if (subFiles == null) {
            return fileList;
        }

        for (File subFile : subFiles) {
            if (subFile.isDirectory() && isRecursive) {
                fileList.addAll(enums(subFile, pattern, isRecursive));
            }

            if (subFile.isFile() && subFile.getName().matches(pattern)) {
                fileList.add(subFile);
            }
        }
        return fileList;
    }

    public static boolean copy(String srcPath, String destPath) {
        return copyFile(new File(srcPath), new File(destPath));
    }

    public static boolean copy(File srcFile, File destFile) {
        if (!isExist(srcFile)) {
            return false;
        }

        if (srcFile.isFile()) {
            return copyFile(srcFile, destFile);
        } else {
            if (!createFolder(destFile)) {
                return false;
            }

            File[] subSrcFiles = srcFile.listFiles();
            if (subSrcFiles == null) {
                return false;
            }

            for (File subFile : subSrcFiles) {
                File subDestFile = new File(destFile, subFile.getName());
                if (subFile.isFile()) {
                    if (!copyFile(subFile, subDestFile)){
                        return false;
                    }
                } else {
                    if (!copy(subFile, subDestFile)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static boolean copyFile(File srcFile, File destFile) {
        if (!isFile(srcFile)) {
            return false;
        }

        if (!isFile(destFile) && !createFolder(destFile)) {
            return false;
        }

        try {
            InputStream  inputStream = new FileInputStream(srcFile);
            OutputStream  outputStream = new FileOutputStream(destFile);
            try {
                byte[] bytes = new byte[1024];
                int count;
                while ((count = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, count);
                }
                return true;
            } finally {
                inputStream.close();
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 重命名文件
     */
    public static boolean rename(String srcPath, String destPath){
        return rename(new File(srcPath), new File(destPath));
    }

    /**
     * 重命名文件
     */
    public static boolean rename(File srcFile, File destFile){
        return srcFile.renameTo(destFile);
    }

    public static List<String> loadInLine(String path, String encoding) {
        return loadInLine(new File(path), encoding);
    }
    
    public static List<String> loadInLine(File file, String encoding) {
        try {
            InputStream inputStream = new FileInputStream(file);
            try {
                return loadInLine(inputStream, encoding);
            } finally {
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static List<String> loadInLine(InputStream stream, String encoding) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, encoding));

            try {
                List<String> list = new ArrayList<String>();
                String line;
                while ((line = reader.readLine()) != null) {
                    list.add(line);
                }

                return list;
            } finally {
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String load(String path, String encoding) {
        return load(new File(path), encoding);
    }
    
    public static String load(File file, String encoding) {
        try {
            InputStream inputStream = new FileInputStream(file);
            try {
                return load(inputStream, encoding);
            } finally {
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String load(InputStream stream, String encoding) {
        List<String> lines = loadInLine(stream, encoding);
        if (lines == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            builder.append(line + System.lineSeparator());
        }
        
        if (builder.length() > System.lineSeparator().length()) {
            builder.setLength(builder.length() - System.lineSeparator().length());
        }
        return builder.toString();
    }
    
    /**
     * 加载二进制文件
     */
    public static byte[] loadBinary(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            try {
                return loadBinary(inputStream);
            } finally {
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] loadBinary(InputStream inputStream) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] bytes = new byte[inputStream.available()];
            try {
                bufferedInputStream.read(bytes);
            } finally {
                bufferedInputStream.close();
            }
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将字符串输出到文件
     */
    public static boolean write(String content, String path, String encoding) {
        return write(content, new File(path), encoding);
    }
    
    /**
     * 将字符串输出到文件
     */
    public static boolean write(String content, File file, String encoding) {
        try {
            OutputStream outputStream = new FileOutputStream(file);
            try {
                return write(content, outputStream, encoding);
            } finally {
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 将字符串输出到文件
     */
    public static boolean write(String content, OutputStream outputStream, String encoding) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, encoding));
            try {
                writer.write(content);
            } finally {
                writer.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }

    /**
     * 将字符串输出到文件
     */
    public static boolean writeBinary(byte[] content, String path) {
        return writeBinary(content, new File(path));
    }

    /**
     * 将字符串输出到文件
     */
    public static boolean writeBinary(byte[] content, File file) {
        try {
            OutputStream outputStream = new FileOutputStream(file);
            try {
                return writeBinary(content, outputStream);
            } finally {
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将字符串输出到文件
     */
    public static boolean writeBinary(byte[] content, OutputStream outputStream) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            try {
                bufferedOutputStream.write(content);
            } finally {
                bufferedOutputStream.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeStream(InputStream inputStream, String path) {
        return writeStream(inputStream, new File(path));
    }

    public static boolean writeStream(InputStream inputStream, File file) {
        try {
            OutputStream outputStream = new FileOutputStream(file);
            try {
                return writeStream(inputStream, outputStream);
            } finally {
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeStream(InputStream inputStream, OutputStream outputStream) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            try {
                byte[] bytes = new byte[1024 * 1024];
                int count;
                while ((count = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, count);
                }
            } finally {
                bufferedOutputStream.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean appendLine(String content, File file, String encoding) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file, true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, encoding));
            try {
                writer.write(content);
                writer.newLine();
            } finally {
                writer.close();
                outputStream.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
//        try {
//            FileWriter fileWriter = new FileWriter(file, true);
//            try {
//                fileWriter.write(content);
//            } finally {
//                fileWriter.close();
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
    }

    /**
     * 获取文件的后缀名
     * @return 获取文件名最后一个“.”后的内容
     */
    public static String getExtName(String fileName) {
        int pos = fileName.lastIndexOf('.');
        if(pos == -1) {
            return "";
        } else {
            return fileName.substring(pos + 1);
        }
    }

    /**
     * 获取文件的后缀名
     * @return 获取文件名最后一个“.”后的内容
     */
    public static String getExtName(File file) {
        return getExtName(file.getName());
    }
}
