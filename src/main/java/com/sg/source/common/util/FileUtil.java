package com.sg.source.common.util;

import com.sg.source.common.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private static final int BUFFER_SIZE = 100 * 1024;

    /**
     * 디렉토리 생성
     *
     * @param path 생성 디렉토리 경로
     * @return boolean
     */
    public static boolean makeDir(String path) {
        boolean rst = false;

        File f = new File(path);
        if (!f.exists()) {
            return f.mkdir();
        }
        return rst;
    }

    /**
     * 디렉토리 생성
     *
     * @param path 생성 디렉토리 경로(상위 디렉토리포함)
     * @return boolean
     */
    public static boolean makeDirs(String path) {
        boolean rst = false;

        File f = new File(path);
        if (!f.exists()) {
            return f.mkdirs();
        }
        return rst;
    }

    /**
     * 파일 복사
     *
     * @param sourceFile 소스 파일 및 경로
     * @param target     대상 파일 및 경로
     * @return boolean
     */
    public static boolean fileCopy(String sourceFile, String target) {
        boolean yn = false;

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel in = null;
        FileChannel out = null;

        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(target);
            in = fis.getChannel();
            out = fos.getChannel();
            in.transferTo(0, (int) in.size(), out);
            yn = true;
            LOGGER.debug("파일이 복사되었습니다. :" + sourceFile + " >>>>> " + target);

        } catch (FileNotFoundException e1) {
            LOGGER.debug("파일 복사중 에러발생");

        } catch (IOException e2) {
            LOGGER.debug("파일 복사중 에러발생");

        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ioe) {
                LOGGER.debug("close error 무시");
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioe) {
                LOGGER.debug("close error 무시");
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ioe) {
                LOGGER.debug("close error 무시");
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ioe) {
                LOGGER.debug("close error 무시");
            }
        }

        return yn;
    }

    /**
     * 파일 복사
     *
     * @param sourcePath 소스경로
     * @param sourceFile 소스파일
     * @param targetPath 대상경로
     * @param targetFile 대상파일
     * @return
     */
    public static boolean fileCopy(String sourcePath, String sourceFile, String targetPath, String targetFile) {
        boolean yn = false;

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel in = null;
        FileChannel out = null;

        try {
            fis = new FileInputStream(new File(sourcePath, sourceFile));
            fos = new FileOutputStream(new File(targetPath, targetFile));
            in = fis.getChannel();
            out = fos.getChannel();
            in.transferTo(0, (int) in.size(), out);
            yn = true;
            LOGGER.debug("파일이 복사되었습니다. :" + sourceFile + " >>>>> " + targetFile);

        } catch (FileNotFoundException e1) {
            LOGGER.debug("파일 복사중 에러발생");

        } catch (IOException e2) {
            LOGGER.debug("파일 복사중 에러발생");

        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ioe) {
                LOGGER.debug("close error 무시");
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioe) {
                LOGGER.debug("close error 무시");
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ioe) {
                LOGGER.debug("close error 무시");
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ioe) {
                LOGGER.debug("close error 무시");
            }
        }

        return yn;
    }

    /**
     * 파일 복사(덮어쓰기)
     *
     * @param srcPath    소스 파일 패스
     * @param srcFile    소스 파일
     * @param targetPath 대상 파일 패스
     * @param targetFile 대상 파일
     * @return 결과
     */
    public static boolean fileCopyOverwrite(String srcPath, String srcFile, String targetPath, String targetFile) {
        boolean yn = false;

        try {
            Path src = Paths.get(srcPath, srcFile);
            Path target = Paths.get(targetPath, targetFile);

            if (Files.exists(src)) {
                Files.copy(src, target, StandardCopyOption.REPLACE_EXISTING);
                yn = true;
            }
        } catch (IOException e) {
            LOGGER.debug("파일 복사중 에러발생");
        }
        return yn;
    }

    /**
     * 파일 삭제
     *
     * @param filePath 삭제 파일 경로 및 파일명
     * @return boolean
     */
    public static boolean fileRemove(String filePath) {
        boolean rst = false;

        try {
            File f = new File(filePath);

            LOGGER.debug("파일을 삭제합니다. :" + filePath);
            f.delete();

            rst = true;
        } catch (SecurityException e) {
            LOGGER.debug("파일 삭제중 오류가 발생하였습니다.");
        }

        return rst;
    }

    /**
     * 파일 이름 변경
     *
     * @param beforePath 변경전 파일 및 경로
     * @param afterPath  변경후 파일 및 경로
     * @return
     */
    public static boolean fileRename(String beforePath, String afterPath) {
        boolean rst = false;

        File f = new File(beforePath);
        rst = f.renameTo(new File(afterPath));
        LOGGER.debug("파일이 이름을 변경 하였습니다. :" + beforePath + " >>>>> " + afterPath);

        return rst;
    }

    /**
     * 디렉토리 삭제 (하위 디렉토리 및 파일 삭제)
     *
     * @param dirPath 디렉토리 경로
     * @return boolean
     */
    public static boolean dirRemove(String dirPath) {
        boolean rst = false;

        Path path = Paths.get(dirPath);
        try {
            if (Files.exists(path)) {
                Files.walkFileTree(path, new FileVisitor<Path>() {

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        } catch (IOException e) {
            LOGGER.debug("디렉토리 삭제 중 에러발생");
        }
        rst = true;

        return rst;
    }

    /**
     * 디렉토리인지 확인한다.
     *
     * @param dir
     * @return boolean true:디렉토리임, false:디렉토리아님
     * @throws Exception
     */
    public static boolean isDirectory(String dir) throws Exception {
        File file = new File(dir);
        return file.isDirectory();
    }

    /**
     * 파일명의 확장자를 가져온다
     *
     * @param FileName
     * @return String 확장자
     */
    public static String getFileExt(String FileName) {
        int idx = FileName.lastIndexOf(".");
        String Ext = FileName.substring(idx + 1).toLowerCase();
        return Ext;
    }

    /**
     * 파일정보를 파싱하여 저장하고 파일정보를 리턴한다.
     *
     * @param file
     * @param storePath       저장경로
     * @param storeFileNm
     * @return FileVO 파일 정보
     * @throws Exception
     */
    public static FileVO fileUpload(MultipartFile file, String storePath, String storeFileNm) throws Exception {

        // 반환할 파일VO리스트

        FileVO fileVO = null;
        String filePath = "";

        // 업로드될 파일이 있다면
        if (!file.isEmpty()) {


            String orginFileName = file.getOriginalFilename();

            // 원 파일명이 없는 경우 건너띔 (첨부가 되지 않은 input file type)

            // 파일 확장자
            String fileExt = getFileExt(orginFileName);
            storeFileNm = storeFileNm + "." + fileExt;
            // 파일 사이즈
            long _size = file.getSize();
            filePath = storePath + File.separator + storeFileNm;


            Files.createDirectories(Paths.get(storePath));

            //System.out.println("filePath:"+filePath);
            file.transferTo(new File(filePath));

            // 파일VO
            fileVO = new FileVO();
            fileVO.setFileExtsn(fileExt);
            fileVO.setStorePath(storePath);
            fileVO.setFileSize(_size);
            fileVO.setFileNm(orginFileName);
            fileVO.setStoreFileNm(storeFileNm);

        }

        return fileVO;

    }

    public static void dbxFileCopy(String sourcePath, String tempFile, String targetPath, String fileName) throws Exception {
        Path dbxPath = Paths.get(sourcePath, tempFile);
        if (!Files.exists(dbxPath)) {
            LOGGER.error("file not exit!" + dbxPath.toString());
            throw new Exception("임시폴더에 파일이 존재하지 않습니다.");
        }
        Files.createDirectories(Paths.get(targetPath));

        String targetFileName = fileName;
        if (StringUtils.isEmpty(targetFileName)) {
            targetFileName = dbxPath.toFile().getName();
        }
        Path target = Paths.get(targetPath, targetFileName);

        //파일 존재 유무
        File targetPath1 = new File(targetPath + File.separator + fileName);
        if (!targetPath1.exists()) {
            Files.copy(dbxPath, target);
        }
    }

    public static void dbxFileMove(String sourcePath, String tempFile, String targetPath, String fileName) throws Exception {
        Path dbxPath = Paths.get(sourcePath, tempFile);
        if (!Files.exists(dbxPath)) {
            LOGGER.error("file not exit!" + dbxPath.toString());
            throw new Exception("임시폴더에 파일이 존재하지 않습니다.");
        }
        Files.createDirectories(Paths.get(targetPath));

        String targetFileName = fileName;
        if (StringUtils.isEmpty(targetFileName)) {
            targetFileName = dbxPath.toFile().getName();
        }
        Path target = Paths.get(targetPath, targetFileName);

        //파일 존재 유무
        File targetPath1 = new File(targetPath + File.separator + fileName);
        if (!targetPath1.exists()) {
            Files.move(dbxPath, target);
        }
    }

    //압축파일에 파일작성
    public static void zip(String parent, File file, ZipArchiveOutputStream zos, String zipPath) throws IOException {

        FileInputStream fis = null;
        BufferedInputStream bis = null;

        //buffer size
        int size = 1024;
        byte[] buf = new byte[size];

        if (!file.exists()) {
            System.out.println(file.getName() + " : 파일없음");
        }

        //해당 폴더안에 다른 폴더가 재귀호출
        if (file.isDirectory()) {
            String dirName = file.getPath().replace(zipPath, "");
            String parentName = dirName.substring(1) + File.separator;
            dirName = dirName.substring(1, dirName.length() - file.getName().length());
            ZipArchiveEntry entry = new ZipArchiveEntry(dirName + file.getName() + File.separator);
            zos.putArchiveEntry(entry);
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                zip(parentName, new File(file.getPath() + File.separator + files[i]), zos, zipPath);
            }

        } else {
            //encoding 설정
            zos.setEncoding("UTF-8");

            //buffer에 해당파일의 stream을 입력한다.
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis, size);


            //zip에 넣을 다음 entry 를 가져온다.
            ZipArchiveEntry entry = new ZipArchiveEntry(parent + file.getName());
            zos.putArchiveEntry(entry);


            //준비된 버퍼에서 집출력스트림으로 write 한다.
            int len;
            while ((len = bis.read(buf, 0, size)) != -1) {
                zos.write(buf, 0, len);
            }

            bis.close();
            fis.close();
            zos.closeArchiveEntry();
        }
    }

    //zip파일 생성 메서드
    public static void zipDirectory(String dir, String zipfile) throws IOException, IllegalArgumentException {
        //디렉토리 존재 유무 체크 및 해당 파일 리스트를 가져오기 위하여 객체 생성
        File d = new File(dir);

        //디렉토리 존재 유무 체크
        if (!d.isDirectory())
            throw new IllegalArgumentException("Not a directory:  " + dir);

        //해당 경로의 파일을 배열로 가져옴
        String[] entries = d.list();

        //파일 복사를 위한 버퍼
        byte[] buffer = new byte[4096];
        int bytesRead;

        //zip파일을 생성하기 위한 객체 생성
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));

        //해당경로의 파일들을 루프
        for (int i = 0; i < entries.length; i++) {
            File f = new File(d, entries[i]);

            if (f.isDirectory())
                continue;// Ignore directory

            //스트림으로 파일을 읽음
            FileInputStream in = new FileInputStream(f); // Stream to read file

            //zip파일을 만들기 위하여 out객체에 write하여 zip파일 생성
            ZipEntry entry = new ZipEntry(f.getPath()); // Make a ZipEntry

            System.out.println("압축 대상 파일 : " + entry);

            out.putNextEntry(entry); // Store entry
            while ((bytesRead = in.read(buffer)) != -1)
                out.write(buffer, 0, bytesRead);
            in.close();
        }
        out.close();
    }

    /*public static void filDown(HttpServletRequest request,
                               HttpServletResponse response, String filePath, String realFilNm,
                               String viewFileNm) throws IOException {

        File file = new File(filePath + File.separator + realFilNm);
        LOGGER.debug("path:{},exists:{}, isFile:{}, ", file.getAbsolutePath(),file.exists(),file.isFile());
//        System.out.println("root = " + filePath + realFilNm);
        try {
            if (file.exists() && file.isFile()) {
                response.setContentType("application/octet-stream; charset=utf-8");
                response.setContentLength((int) file.length());
                String browser = getBrowser(request);
                String disposition = getDisposition(viewFileNm, browser);
                response.setHeader("Content-Disposition", disposition);
                response.setHeader("Content-Transfer-Encoding", "binary");
                OutputStream out = response.getOutputStream();
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                FileCopyUtils.copy(fis, out);
                if (fis != null)
                    fis.close();
                out.flush();
                out.close();

                try {
                    String n = CmmnUtil.getMessages().getText("Globals.dwldLog.catch", "N");
                    if ("Y".equals(n)) {
                        CustomLoggerService bean = (CustomLoggerService) CmmnUtil.getBean(CustomLoggerService.class);
                        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
                        DwldLogVO dwldLogVO = new DwldLogVO();
                        dwldLogVO.setCours(file.getAbsolutePath());
                        dwldLogVO.setFileNm(realFilNm);
                        dwldLogVO.setIpAdres(loginVO.getIp());
                        dwldLogVO.setUsrId(loginVO.getUserId());
                        dwldLogVO.setReqUrl(request.getHeader("referer"));
                        String sysCode = CmmnUtil.getMessages().getText("Globals.SysCode");
                        dwldLogVO.setSysCode(sysCode);
                        bean.addDwldLog(dwldLogVO);
                    }
                }catch (Exception e){}

            } else {
                LOGGER.warn("파일경로 없음 : {}", file.getAbsolutePath());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    public static String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1)
            return "MSIE";
        else if (header.indexOf("Chrome") > -1)
            return "Chrome";
        else if (header.indexOf("Opera") > -1)
            return "Opera";
        else if (header.indexOf("Firefox") > -1)
        	return "Firefox";
        return "Chrome";
    }

    public static String getDisposition(String filename, String browser)
            throws UnsupportedEncodingException {
        String dispositionPrefix = "attachment;filename=";
        String encodedFilename = null;
        if (browser.equals("MSIE")) {
            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll(
                    "\\+", "%20");
        } else if (browser.equals("Firefox") || browser.equals("Opera")) {
            encodedFilename = "\""
                    + new String(filename.getBytes(StandardCharsets.UTF_8), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < filename.length(); i++) {
                char c = filename.charAt(i);
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
                } else {
                    sb.append(c);
                }
            }
            encodedFilename = sb.toString();
            return String.format("%s\"%s\"",dispositionPrefix , encodedFilename);//chrome ',' issue fix
        }
        return dispositionPrefix + encodedFilename;
    }

    public static void appendFile(InputStream in, File destFile) {
        InputStream input = null;
        OutputStream output = null;
        try {
            if (destFile.exists()) {
                output = new BufferedOutputStream(new FileOutputStream(destFile, true), BUFFER_SIZE);
            } else {
                output = new BufferedOutputStream(new FileOutputStream(destFile), BUFFER_SIZE);
            }
            input = new BufferedInputStream(in, BUFFER_SIZE);

            int len = 0;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((len = in.read(buffer)) > 0) {
                output.write(buffer, 0, len);
            }
        } catch (IOException ie) {
            LOGGER.error(ie.getMessage());
        } finally {
            ResourceCloseHelper.close(input, output);
        }
    }


    /*public static void fileDownload(HttpServletRequest request, HttpServletResponse response, File file, String fileName) throws Exception{
        String mimetype = "application/x-msdownload";
        response.setContentType(mimetype);
        setFileDisposition(request, response, fileName);

        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            in = new BufferedInputStream(new FileInputStream(file));
            out = new BufferedOutputStream(response.getOutputStream());

            FileCopyUtils.copy(in, out);
            out.flush();
        } catch (IOException ex) {
            // 다음 Exception 무시 처리
            // Connection reset by peer: socket write error
            EgovBasicLogger.ignore("IO Exception", ex);
        } finally {
            EgovResourceCloseHelper.close(in, out);
        }
    }

    private static void setFileDisposition(HttpServletRequest request, HttpServletResponse response, String fileNm) throws Exception {
        String browser = getBrowser(request);

        String dispositionPrefix = "attachment; filename=";
        String encodedFilename = null;

        if (browser.equals("MSIE")) {
            encodedFilename = URLEncoder.encode(fileNm, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
            encodedFilename = URLEncoder.encode(fileNm, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Firefox")) {
            encodedFilename = "\"" + new String(fileNm.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Opera")) {
            encodedFilename = "\"" + new String(fileNm.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < fileNm.length(); i++) {
                char c = fileNm.charAt(i);
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
                } else {
                    sb.append(c);
                }
            }
            encodedFilename = sb.toString();
        } else {
            throw new IOException("Not supported browser");
        }
        response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

        if ("Opera".equals(browser)) {
            response.setContentType("application/octet-stream;charset=UTF-8");
        }
    }

    private static String getBrowser(HttpServletRequest request) throws Exception {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
            return "Trident";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }*/

    /**
     * 디렉토리를 생성한다
     *
     * @param path
     * @throws IOException
     */
    public void mkdir(String path) throws IOException {
        File file = new File(path);
        if (!file.isDirectory()) {
            boolean _flag = file.mkdir();
            if (!_flag) throw new IOException("Directory creation Failed");
        }
    }
}
