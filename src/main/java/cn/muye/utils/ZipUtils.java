package cn.muye.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.zip.*;

/**
 * Created with IntelliJ IDEA.
 * Project Name : tinker-agent
 * User: Jelynn
 * Date: 2017/4/10
 * Time: 13:55
 * Describe:
 * Version:1.0
 */
public class ZipUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtils.class);

    /**
     * 递归压缩文件夹
     *
     * @param srcRootDir 压缩文件夹根目录的子路径
     * @param file       当前递归压缩的文件或目录对象
     * @param zos        压缩文件存储对象
     * @throws Exception
     */
    private static void zip(String srcRootDir, File file, ZipOutputStream zos) {
        if (file == null) {
            return;
        }
        try {
            //如果是文件，则直接压缩该文件
            if (file.isFile()) {
                int count, bufferLen = 1024 * 2;
                byte data[] = new byte[bufferLen];

                //获取文件相对于压缩文件夹根目录的子路径
                String subPath = file.getAbsolutePath();
                int index = subPath.indexOf(srcRootDir);
                if (index != -1) {
                    subPath = subPath.substring(srcRootDir.length() + File.separator.length());
                }
                ZipEntry entry = new ZipEntry(subPath);
                zos.putNextEntry(entry);
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                while ((count = bis.read(data, 0, bufferLen)) != -1) {
                    zos.write(data, 0, count);
                }
                bis.close();
                zos.closeEntry();
            }
            //如果是目录，则压缩整个目录
            else {
                //压缩目录中的文件或子目录
                File[] childFileList = file.listFiles();
                for (int n = 0; n < childFileList.length; n++) {
                    childFileList[n].getAbsolutePath().indexOf(file.getAbsolutePath());
                    zip(srcRootDir, childFileList[n], zos);
                }
            }
            zos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件压缩出错", e);
        }
    }

    /**
     * 对文件或文件目录进行压缩
     *
     * @param savePath    要压缩的源文件路径。如果压缩一个文件，则为该文件的全路径；如果压缩一个目录，则为该目录的顶层目录路径
     * @param zipPath     压缩文件保存的路径。注意：zipPath不能是srcPath路径下的子文件夹
     * @param zipFileName 压缩文件名
     * @throws Exception
     */
    public static boolean fileToZip(String zipPath, String savePath, String zipFileName) throws Exception {
        if (StringUtil.isNullOrEmpty(savePath) || StringUtil.isNullOrEmpty(zipPath) || StringUtil.isNullOrEmpty(zipFileName)) {
            LOGGER.error("参数不能为空");
            return false;
        }
        CheckedOutputStream cos = null;
        ZipOutputStream zos = null;
        try {
            final File srcFile = new File(zipPath);
            String dir = srcFile.getParentFile().getAbsolutePath();
            //判断压缩文件保存的路径是否为源文件路径的子文件夹，如果是，则抛出异常（防止无限递归压缩的发生）
            if (srcFile.isDirectory() && savePath.indexOf(zipPath) != -1) {
                LOGGER.error("savePath must not be the child directory of zipPath");
                return false;
            }

            //判断压缩文件保存的路径是否存在，如果不存在，则创建目录
            File saveDir = new File(savePath);
            if (!saveDir.exists() || !saveDir.isDirectory()) {
                saveDir.mkdirs();
            }

            //创建压缩文件保存的文件对象
            String saveFilePath = savePath + File.separator + zipFileName;
            File saveFile = new File(saveFilePath);
            if (saveFile.exists()) {
                //删除已存在的目标文件
                saveFile.delete();
            }

            cos = new CheckedOutputStream(new FileOutputStream(saveFile), new CRC32());
            zos = new ZipOutputStream(cos);

            //如果只是压缩一个文件，则需要截取该文件的父目录
            String srcRootDir = zipPath;
            if (srcFile.isFile()) {
                int index = zipPath.lastIndexOf(File.separator);
                if (index != -1) {
                    srcRootDir = zipPath.substring(0, index);
                }
            }
            //调用递归压缩方法进行目录或文件压缩
            zip(srcRootDir, srcFile, zos);
            return true;
        } catch (Exception e) {
            LOGGER.error("压缩文件出错", e);
        } finally {
            if (null != zos) {
                zos.close();
            }
        }
        return false;
    }

//    public static void main(String[] args) {
////        String sourceFilePath = "E:\\test_zip";
////        String zipFilePath = "E:\\test_zip";
////        String fileName = "12700153file";
////        boolean flag = fileToZip(sourceFilePath, zipFilePath, fileName);
////        FileUtils.deleteDir(new File(sourceFilePath), "zip");
////        if (flag) {
////            System.out.println("文件打包成功!");
////        } else {
////            System.out.println("文件打包失败!");
////        }
//        File file=new File("E:\\partner_upload_files\\aaa");
//        //deleteFileByWinCom(file);
//        deleteFile(file);
//    }

    /**
     * 通过递归调用删除一个文件夹及下面的所有文件
     * @param file
     */
    public static void deleteFile(File file){
        if(file.isFile() && file.exists() && !file.getName().endsWith(".zip")){//表示该文件不是文件夹
            LOGGER.info(file.delete()? "删除成功" : "删除失败");
        }else{
            //首先得到当前的路径
            String[] childFilePaths = file.list();
            for(String childFilePath : childFilePaths){
                File childFile=new File(file.getAbsolutePath()+"\\"+childFilePath);
                deleteFile(childFile);
            }
            LOGGER.info(file.delete()? "删除成功" : "删除失败");
        }
    }
}
