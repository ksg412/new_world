package com.sg.source.common.util;

import com.sg.source.common.annotation.Excel;
import com.sg.source.common.vo.BaseVO;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;


public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);


    /**
     * 데이터목록을 엑셀파일로 변환한다.
     *
     * @param dataList - 데이터목록
     * @param voClass  - 대상 Vo 객체
     * @return 엑셀파일(.xlsx)
     * @throws IOException
     */
    public static File toExcelFile(List<?> dataList, Class<?> voClass) throws ExcelException {
        return toExcelFile(dataList, voClass, "");
    }

    /**
     * 데이터목록을 엑셀파일로 변환한다.
     *
     * @param dataList - 데이터목록
     * @param voClass  - 대상 Vo 객체
     * @param group    - 대상 그룹
     * @return 엑셀파일(.xlsx)
     * @throws IOException
     */
    public static File toExcelFile(List<?> dataList, Class<?> voClass, String group) throws ExcelException {
        return toExcelFile(dataList, voClass, group, null);
    }

    public static File toExcelFile(List<List<T>> list, String[] sheetNames, Map headers) throws ExcelException, IOException {
        return toExcelFile(list, sheetNames, headers, null);
    }

    public static File toExcelFile(List<List<T>> list, String[] sheetNames, Map headers, CellStyleDecorator cellStyleDecorator) throws ExcelException, IOException {
        File xlsxFile = null;
        FileOutputStream out = null;
        try {
            xlsxFile = new File((getTempPath() + RandomStringUtils.randomAlphanumeric(10) + ".xlsx").replaceAll("&", ""));
            xlsxFile.getParentFile().mkdirs();

            out = new FileOutputStream(xlsxFile);
            SXSSFWorkbook workbook = new SXSSFWorkbook(100);

            for (int i = 0; i < list.size(); i++) {
                List dataList = list.get(i);
                Class voClass = dataList.get(0).getClass();

                GetList4Excel getListGetter = new GetList4Excel() {
                    @Override
                    public List<?> getList(int pageNo) {
                        if (1 == pageNo)
                            return dataList;
                        return Collections.EMPTY_LIST;
                    }

                    @Override
                    public int getTotalCount() {
                        return dataList.size();
                    }

                    @Override
                    public void progress(int done) {
                        //N/A
                        logger.info("progress !!");
                    }

                    @Override
                    public void setResultCount(int count) {
                        //N/A
                        logger.info("setResultCount !!");
                    }

                    @Override
                    public Class<?> getVoClass() {
                        return voClass;
                    }
                };
                if (sheetNames != null && sheetNames[i] != null) {
                    createWorkbook(getListGetter, "", headers, workbook, null, sheetNames[i], cellStyleDecorator);
                }
            }


            workbook.write(out);
            workbook.dispose();
            out.close();
        } catch (Exception e) {
            logger.error("error {}", e);
            throw new ExcelException("엑셀생성중 오류 발생", e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                logger.error("error {}", e);
            }
        }

        return xlsxFile;
    }

    /**
     * 데이터목록을 엑셀파일로 변환한다.
     *
     * @param list       - 시트별리스트를 담은 리스트
     * @param sheetNames - 시트명 배열
     * @return 엑셀파일(.xlsx)
     * @throws IOException
     */
    public static File toExcelFile(List<List<T>> list, String[] sheetNames) throws ExcelException, IOException {
        File xlsxFile = null;
        FileOutputStream out = null;
        try {
            String plUploadTempPath = getTempPath();


            xlsxFile = new File((plUploadTempPath + RandomStringUtils.randomAlphanumeric(10) + ".xlsx").replaceAll("&", ""));
            xlsxFile.getParentFile().mkdirs();

            out = new FileOutputStream(xlsxFile);
            SXSSFWorkbook workbook = new SXSSFWorkbook(100);

            for (int i = 0; i < list.size(); i++) {
                List dataList = list.get(i);
                Class voClass = dataList.get(0).getClass();

                GetList4Excel getListGetter = new GetList4Excel() {
                    @Override
                    public List<?> getList(int pageNo) {
                        if (1 == pageNo)
                            return dataList;
                        return Collections.EMPTY_LIST;
                    }

                    @Override
                    public int getTotalCount() {
                        return dataList.size();
                    }

                    @Override
                    public void progress(int done) {
                        //N/A
                        logger.info("progress !!");
                    }

                    @Override
                    public void setResultCount(int count) {
                        //N/A
                        logger.info("setResultCount !!");
                    }

                    @Override
                    public Class<?> getVoClass() {
                        return voClass;
                    }
                };
                if (sheetNames != null && sheetNames[i] != null) {
                    createWorkbook(getListGetter, "", (String[]) null, workbook, null, sheetNames[i]);
                }
            }


            workbook.write(out);
            workbook.dispose();
            out.close();
        } catch (Exception e) {
            logger.error("error {}", e);
            throw new ExcelException("엑셀생성중 오류 발생", e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                logger.error("error {}", e);
            }
        }

        return xlsxFile;
    }

    private static String getTempPath() {
        /*ComFileService comFileService = (ComFileService) CmmnUtil.getBean(ComFileService.class);
        if(null==comFileService){
            logger.warn("공통 파일 서비스 없음, 임시파일 저장 경로 못찾음.");
            return "/tmp/";
        }
        return comFileService.getPlUploadTempPath();*/

        return null;
    }

    /**
     * 데이터목록을 엑셀파일로 변환한다.
     *
     * @param dataList - 데이터목록
     * @param voClass  - 대상 Vo 객체
     * @param group    - 대상 그룹
     * @param headers  - 헤더목록
     * @return 엑셀파일(.xlsx)
     * @throws IOException
     */
    public static File toExcelFile(List<?> dataList, Class<?> voClass, String group, String[] headers) throws ExcelException {
        GetList4Excel getListGetter = new GetList4Excel() {
            @Override
            public List<?> getList(int pageNo) {
                if (1 == pageNo)
                    return dataList;
                return Collections.EMPTY_LIST;
            }

            @Override
            public int getTotalCount() {
                return dataList.size();
            }

            @Override
            public void progress(int done) {
                //N/A
                logger.info("progress !!");
            }

            @Override
            public void setResultCount(int count) {
                //N/A
                logger.info("setResultCount !!");
            }

            @Override
            public Class<?> getVoClass() {
                return voClass;
            }
        };
        return toExcelFile(getListGetter, group, headers);
    }

    public static File toExcelFile(GetList4Excel gl4e) throws ExcelException {
        return toExcelFile(gl4e, null, null);
    }

    /**
     * 데이터목록을 엑셀파일로 변환한다.
     *
     * @param gl4e    데이터를 뽑아올 서비스객체
     * @param group   - 대상 그룹
     * @param headers - 헤더목록
     * @return 엑셀파일(.xlsx)
     * @throws IOException
     */
    public static File toExcelFile(
            GetList4Excel gl4e
            , String group, String[] headers) throws ExcelException {
        File xlsxFile = null;
        FileOutputStream out = null;
        try {
            xlsxFile = new File((getTempPath() + RandomStringUtils.randomAlphanumeric(10) + ".xlsx").replaceAll("&", ""));
            xlsxFile.getParentFile().mkdirs();

            out = new FileOutputStream(xlsxFile);
            SXSSFWorkbook workbook = new SXSSFWorkbook(100);

            int count = createWorkbook(gl4e, group, headers, workbook, out, null);

            workbook.write(out);
            workbook.dispose();
            out.close();
            gl4e.setResultCount(count);
        } catch (Exception e) {
            logger.error("error {}", e);
            throw new ExcelException("엑셀생성중 오류 발생", e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                logger.error("error {}", e);
            }
        }

        return xlsxFile;
    }

    private static int createWorkbook(GetList4Excel gl4e, String group, Map headers, SXSSFWorkbook workbook, FileOutputStream out, String sheetName) throws ExcelException, IOException {
        return createWorkbook(gl4e, group, headers, workbook, out, sheetName, null);
    }

    private static int createWorkbook(GetList4Excel gl4e, String group, Map headers, SXSSFWorkbook workbook, FileOutputStream out, String sheetName, CellStyleDecorator cellStyleDecorator) throws ExcelException, IOException {
        Sheet sheet;
        if (sheetName != null) {
            sheet = workbook.createSheet(sheetName);
        } else {
            sheet = workbook.createSheet();
        }

        int rownum = 0;
        int cellnum = 0;

        CreationHelper creationHelper = workbook.getCreationHelper();
        CellStyle dateStyle = workbook.createCellStyle();

        List<Field> visibleFieldList = new ArrayList<>();

        Row row = sheet.createRow(rownum++);
//        TableList listAnnotation;
//        boolean isVisible;

        Map<String, String> dateStyles = new HashMap<>();

        int count = 0;
        int pageNo = 1;
        Collection dataList = gl4e.getList(pageNo);
        Object vo;
        int idx = 0;
        Set headerSet = null;

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

        List<Field> fieldList = null;
/*
        if (null != gl4e.getVoClass()) {
            fieldList = annotaionFieldList(gl4e.getVoClass(), TableList.class, true);
        }
        if (null != fieldList && !fieldList.isEmpty()) {
            for (Field f : fieldList) {
                listAnnotation = f.getAnnotation(TableList.class);
                isVisible = listAnnotation.visible();

                if (isVisible) {
                    // group이 들어온 경우 Vo @gov.mfds.cmmn.annotation.ui.TableList 에 group과 동일한 필드와 group 설정이 없는 필드만 보여준다.
                    String[] groups = listAnnotation.group();
                    if (StringUtils.isNotEmpty(group)
                            && ArrayUtils.isNotEmpty(groups)
                            && !ArrayUtils.contains(groups, group)) {
                        isVisible = false;
                    }
                }

                if (isVisible) {
                    String header;
                    if (headers != null) {
                        header = (String) headers.get(CmmnUtil.getMessages().getText(f));
                    } else {
                        header = listAnnotation.caption();
                        if (StringUtils.isEmpty(header)) {
                            header = CmmnUtil.getMessages().getText(f);
                        }
                    }
                    Cell cell = row.createCell(cellnum++);
                    cell.setCellValue(header);

                    if(cellStyleDecorator != null) {
                        cellStyleDecorator.setHeaderStyle(workbook, cell);
                    }

                    visibleFieldList.add(f);

                    // Date 타입인 경우 날짜 형식을 지정한다.
                    if (Date.class.isAssignableFrom(f.getType())) {
                        String format = listAnnotation.format();
                        if (StringUtils.isEmpty(format)) {
                            format = "yyyy-mm-dd";
                        }
                        dateStyles.put(f.getName(), format);
                    }
                }
            }
        } else {
*/
        if (!dataList.isEmpty()) {
            Object next = dataList.iterator().next();
            if (next instanceof Map) {
                headerSet = ((Map) next).keySet();
                for (Object key : headerSet) {
                    Cell cell = row.createCell(cellnum++);
                    String header;
                    if (headers != null) {
                        header = (String) headers.get(String.valueOf(key));
                    } else {
                        header = String.valueOf(key);
                    }
                    cell.setCellValue(header);

                    if (cellStyleDecorator != null) {
                        cellStyleDecorator.setHeaderStyle(workbook, cell);
                    }
                }
            }
        }
//        }


        float progress = 0l;
        while (null != dataList && !dataList.isEmpty()) {
            count += dataList.size();
            progress = 100f * count / gl4e.getTotalCount();
            logger.info(String.format("getList 4 excel\t\t pageNo:%,d\t\t%,d / %,d\t(%.2f %%)", pageNo, count, gl4e.getTotalCount(), progress));
            Iterator itr = dataList.iterator();
            if (itr != null) {
                while (itr.hasNext()) {
                    vo = itr.next();
                    row = sheet.createRow(rownum++);
                    cellnum = 0;
                    if (null != fieldList && !fieldList.isEmpty()) {
                        for (Field field : visibleFieldList) {
                            Cell cell = row.createCell(cellnum++);
                            field.setAccessible(true);
                            Object value = null;
                            try {
                                value = field.get(vo);
                            } catch (IllegalAccessException e) {
                                logger.error("{}", e);
                            }

                            if (value != null) {
                                setCellValue(cell, field.getType(), value);
                                if (Date.class.isAssignableFrom(field.getType())) {
                                    dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat(MapUtils.getString(dateStyles, field.getName())));
                                    cell.setCellStyle(dateStyle);
                                }
                            }
                        }
                    } else {
                        CellStyle cellStyle = workbook.createCellStyle();
                        if ((rownum + 1) % 2 == 0) {
                            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        }
                        if (vo instanceof Map) {
                            for (Object key : headerSet) {
                                Cell cell = row.createCell(cellnum++);
                                Object o = ((Map) vo).get(key);

                                if (o != null) {
                                    setCellValue(cell, o.getClass(), o, cellStyle, workbook.createDataFormat());
                                }

                                /*if (null != o) {
                                    cell.setCellValue(String.valueOf(o));
                                }*/
                            }
                        }
                    }
                }
            }

            pageNo++;
            //set Auto resize
//            sheet.trackAllColumnsForAutoSizing();
            for (int i = 0; i < headerSet.size(); i++) {
                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i, (sheet.getColumnWidth(i) + 1024));
            }
            dataList = gl4e.getList(pageNo);
            gl4e.progress(count);
            if (out != null) {
                out.flush();
            }
        }
        return count;
    }

    private static int createWorkbook(GetList4Excel gl4e, String group, String[] headers, SXSSFWorkbook workbook, FileOutputStream out, String sheetName) throws ExcelException, IOException {
        Sheet sheet;
        if (sheetName != null) {
            sheet = workbook.createSheet(sheetName);
        } else {
            sheet = workbook.createSheet();
        }

        int rownum = 0;
        int cellnum = 0;

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

        CreationHelper creationHelper = workbook.getCreationHelper();
        CellStyle dateStyle = workbook.createCellStyle();

        List<Field> visibleFieldList = new ArrayList<>();

        Row row = sheet.createRow(rownum++);
        Excel listAnnotation;
        boolean isVisible;

        Map<String, String> dateStyles = new HashMap<>();

        int count = 0;
        int pageNo = 1;
        Collection dataList = gl4e.getList(pageNo);
        Object vo;
        int idx = 0;
        Set headerSet = null;

        List<Field> fieldList = null;
        if (null != gl4e.getVoClass()) {
            fieldList = CommonUtil.annotaionFieldList(gl4e.getVoClass(), Excel.class, true);
        }
        if (null != fieldList && !fieldList.isEmpty()) {
            for (Field f : fieldList) {
                listAnnotation = f.getAnnotation(Excel.class);

                    String header;
                    if (headers != null) {
                        header = headers[idx++];
                    } else {
                        header = listAnnotation.value();
                        if (StringUtils.isEmpty(header)) {
                            header = CommonUtil.getMessages().getText(f);
                        }
                    }
                    Cell cell = row.createCell(cellnum++);
                    cell.setCellValue(header);

                    cell.setCellStyle(headerStyle);
                    visibleFieldList.add(f);

/*
                    // Date 타입인 경우 날짜 형식을 지정한다.
                    if (Date.class.isAssignableFrom(f.getType())) {
                        String format = listAnnotation.format();
                        if (StringUtils.isEmpty(format)) {
                            format = "yyyy-mm-dd";
                        }
                        dateStyles.put(f.getName(), format);
                    }
*/
                }

        } else {
            if (!dataList.isEmpty()) {
                Object next = dataList.iterator().next();
                if (next instanceof Map) {
                    headerSet = ((Map) next).keySet();
                    for (Object key : headerSet) {
                        Cell cell = row.createCell(cellnum++);
                        String header;
                        if (headers != null) {
                            header = headers[idx++];
                        } else {
                            header = String.valueOf(key);
                        }
                        cell.setCellValue(header);
                    }
                }
            }
       }

        float progress = 0l;
        while (null != dataList && !dataList.isEmpty()) {
            count += dataList.size();
            progress = 100f * count / gl4e.getTotalCount();
            logger.info(String.format("getList 4 excel\t\t pageNo:%,d\t\t%,d / %,d\t(%.2f %%)", pageNo, count, gl4e.getTotalCount(), progress));
            Iterator itr = dataList.iterator();
            if (itr != null) {
                while (itr.hasNext()) {
                    vo = itr.next();
                    row = sheet.createRow(rownum++);
                    cellnum = 0;
                    if (null != fieldList && !fieldList.isEmpty()) {
                        for (Field field : visibleFieldList) {
                            Cell cell = row.createCell(cellnum++);
                            field.setAccessible(true);
                            Object value = null;
                            try {
                                value = field.get(vo);
                            } catch (IllegalAccessException e) {
                                logger.error("{}", e);
                            }

                            if (value != null) {
                                setCellValue(cell, field.getType(), value);
                                if (Date.class.isAssignableFrom(field.getType())) {
                                    dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat(MapUtils.getString(dateStyles, field.getName())));
                                    cell.setCellStyle(dateStyle);
                                }
                            }
                        }
                    } else {
                        if (vo instanceof Map) {
                            for (Object key : headerSet) {
                                Cell cell = row.createCell(cellnum++);
                                Object o = ((Map) vo).get(key);
                                if (null != o) {
                                    cell.setCellValue(String.valueOf(o));
                                }
                            }
                        }
                    }
                }
            }
            pageNo++;
            dataList = gl4e.getList(pageNo);
            gl4e.progress(count);
            if (out != null) {
                out.flush();
            }
        }
        return count;
    }

    //통화 적용
    private static void setCellValue(Cell cell, Class<?> type, Object value, CellStyle cellStyle, DataFormat format) {
        if (!isNumeric(type)) {
            if (Date.class.isAssignableFrom(type)) {
                cell.setCellValue((Date) value);
            } else if (Boolean.class.equals(type) || Boolean.TYPE.equals(type)) {
                cell.setCellValue((Boolean) value);
//            } else if (CmmnCodeVo.class.equals(type)) {
//                cell.setCellValue(value.toString());
            } else {
                cell.setCellValue((String) value);
            }
            cell.setCellStyle(cellStyle);
        } else {
            try {
                Double dValue = Double.valueOf(Double.parseDouble(value.toString()));
                cell.setCellValue(dValue.doubleValue());

                cellStyle.setDataFormat(format.getFormat("#,##0"));
                cell.setCellStyle(cellStyle);
            } catch (NumberFormatException e) {
                logger.warn("NumberFormatException parsing a numeric value : " + value);
                cell.setCellValue((String) value);
            }
        }
    }

    private static void setCellValue(Cell cell, Class<?> type, Object value) {
        if (!isNumeric(type)) {
            if (Date.class.isAssignableFrom(type)) {
                cell.setCellValue((Date) value);
            } else if (Boolean.class.equals(type) || Boolean.TYPE.equals(type)) {
                cell.setCellValue((Boolean) value);
//            } else if (CmmnCodeVo.class.equals(type)) {
//                cell.setCellValue(value.toString());
            } else {
                cell.setCellValue((String) value);
            }
        } else {
            try {
                Double dValue = Double.valueOf(Double.parseDouble(value.toString()));
                cell.setCellValue(dValue.doubleValue());
            } catch (NumberFormatException e) {
                logger.warn("NumberFormatException parsing a numeric value : " + value);
                cell.setCellValue((String) value);
            }
        }
    }

    /**
     * 클래스 타입이 숫자형인지 확인한다.
     *
     * @param type 클래스 타입
     * @return 숫자형 여부
     */
    private static boolean isNumeric(Class<?> type) {
        boolean isNumeric = false;
        if (Integer.class.equals(type) || Integer.TYPE.equals(type)
                || Long.class.equals(type) || Long.TYPE.equals(type)
                || Short.class.equals(type) || Short.TYPE.equals(type)
                || Double.class.equals(type) || Double.TYPE.equals(type)
                || Float.class.equals(type) || Float.TYPE.equals(type)
                || BigDecimal.class.equals(type)) {
            isNumeric = true;
        }
        return isNumeric;
    }

    public static boolean fromExcelFile(SetListFromExcel slfe) throws ExcelException {
        Integer headerRowIndex = slfe.getHeaderRowIndex();
        if (null == headerRowIndex) {
            headerRowIndex = 0;
        }
        Integer dataRowIndex = slfe.getDataRowIndex();
        if (null == dataRowIndex) {
            dataRowIndex = headerRowIndex + 1;
        }
        Class clz = slfe.getVoType();
        String group = slfe.getGroup();
        Integer listBufferSize = slfe.getListBufferSize();
        if (null == listBufferSize) {
            listBufferSize = 1000;
        }

        Map<String, Field> headerMap = new HashMap<>();
        Map<Integer, String> headerCellMap = new HashMap<>();

        Excel listAnnotation;
        boolean isVisible;
        if (null!=clz && BaseVO.class.isAssignableFrom(clz)) {
            List<Field> fieldList = null;
            fieldList = CommonUtil.annotaionFieldList(clz, Excel.class, true);
            for (Field f : fieldList) {
                listAnnotation = f.getAnnotation(Excel.class);
                isVisible=true;
/*
                isVisible = listAnnotation.visible();

                if (isVisible) {
                    // group이 들어온 경우 Vo @gov.mfds.cmmn.annotation.ui.TableList 에 group과 동일한 필드와 group 설정이 없는 필드만 보여준다.
                    String[] groups = listAnnotation.group();
                    if (StringUtils.isNotEmpty(group)
                            && ArrayUtils.isNotEmpty(groups)
                            && !ArrayUtils.contains(groups, group)) {
                        isVisible = false;
                    }
                }
*/

                if (isVisible) {
                    String header = listAnnotation.value();
                    if (StringUtils.isEmpty(header)) {
                        header = CommonUtil.getMessages().getText(f);
                    }
                    headerMap.put(header, f);
                }
            }
        }


        List voListBuffer = new ArrayList();

        int totalCnt = 0;
        FileInputStream is = null;
        try {
            is = new FileInputStream(slfe.getExcelFile());
            Workbook workbook = new SXSSFWorkbook();//StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);
            Integer sheetIndex = slfe.getSheetIndex();
            if (null == sheetIndex) {
                sheetIndex = slfe.getSheetIndex(workbook);
            }
            if (null == sheetIndex) {
                sheetIndex = 0;
            }
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            logger.debug(String.format("sheetname : %s, lastrownum:%,d", sheet.getSheetName(), sheet.getLastRowNum()));
            slfe.setTotalCount(sheet.getLastRowNum() - dataRowIndex + 1);
            for (Row r : sheet) {
                if (headerRowIndex > r.getRowNum()) {
                    continue;
                } else if (headerRowIndex == r.getRowNum()) {
                    for (Cell cell : r) {
                        String stringCellValue = cell.getStringCellValue();
                        headerCellMap.put(cell.getColumnIndex(), stringCellValue);
                    }
                    slfe.setAddionalHeader(headerCellMap);//추가헤더 설정
                } else if (dataRowIndex <= r.getRowNum()) {

                    if (headerMap.isEmpty()) {//mapType
                        HashMap<Object, Object> m1 = new HashMap<>();

                        for (Cell cell : r) {
                            int columnIndex = cell.getColumnIndex();
                            String s = headerCellMap.get(columnIndex);
                            if (null != s) {
                                m1.put(s, cell.getStringCellValue());
                            }
                        }

                        voListBuffer.add(m1);
                    } else {

                        Object o1 = clz.newInstance();

                        for (Cell cell : r) {
                            int columnIndex = cell.getColumnIndex();
                            String s = headerCellMap.get(columnIndex);
                            if (null != s) {
                                Field field = headerMap.get(s);
                                if (null != field) {
                                    field.setAccessible(true);
                                    field.set(o1, cell.getStringCellValue());
                                }
                            }
                        }
                        //validation
                        boolean valid = slfe.validation(o1);
                        if (valid) {
                            voListBuffer.add(o1);
                        } else {
                            throw new ExcelUtil.ExcelException("검증실패");
                        }
                    }

                    totalCnt++;
                }

                slfe.progress(totalCnt);
                if (listBufferSize == voListBuffer.size()) {//로딩된 vo 목록 처리
                    slfe.treatList(voListBuffer);
                    voListBuffer.clear();
                }
            }
            slfe.treatList(voListBuffer);//마지막 남은것
            slfe.setResultCount(totalCnt);
            slfe.commit();
            return true;
        } catch (Exception e) {
            slfe.rollback(e);
            throw new ExcelException("엑셀처리중 오류 발생[롤백호출완료]", e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error("error {}", e);
            }
            slfe.setExtra(headerCellMap,headerMap);
        }
    }

    public static List<LinkedResultMap> toList(InputStream is) throws EncryptedDocumentException, InvalidFormatException, IOException {
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer, String> headers = new HashMap<Integer, String>();
        List<LinkedResultMap> datas = new ArrayList<LinkedResultMap>();
        int firstRow = sheet.getFirstRowNum();
        for (Row row : sheet) {
            LinkedResultMap data = new LinkedResultMap();
            for (Cell cell : row) {
                if (row.getRowNum() == firstRow) { // header
                    String label = cell.getStringCellValue();
                    if (label == null) {
                        label = "Column" + cell.getColumnIndex();
                    }
                    headers.put(cell.getColumnIndex(), label);
                } else if (row.getRowNum() > firstRow) {          // body
                    Object value = "";
                    if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                        value = cell.getNumericCellValue();
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = HSSFDateUtil.getJavaDate((double) value);
                            String format = cell.getCellStyle().getDataFormatString();
                            value = new CellDateFormatter(format).format(date);
                        }
                    } else if (cell.getCellTypeEnum() == CellType.STRING) {
                        value = cell.getStringCellValue();
                    } else if (cell.getCellTypeEnum() ==CellType.BOOLEAN) {
                        value = cell.getBooleanCellValue();
                    } else if (cell.getCellTypeEnum() == CellType.ERROR) {
                        value = cell.getErrorCellValue();
                    }
                    data.put(String.valueOf(headers.get(cell.getColumnIndex())), value);
                }
            }
            if (row.getRowNum() == firstRow) { // header
                continue;
            }
            datas.add(data);
        }
        return datas;
    }

    public interface SetListFromExcel {
        void treatList(List list) throws ExcelException;

        void rollback(Exception e) throws ExcelException;

        void commit() throws ExcelException;

        default void setTotalCount(int count) {
        }

        default void setResultCount(int count) {
        }

        default void progress(int done) {
        }

        default Boolean validation(Object o1) {
            return false;
        }

        default Integer getHeaderRowIndex() {
            return 0;
        }

        default Class getVoType() {
            return null;
        }

        default String getGroup() {
            return null;
        }

        default Integer getListBufferSize() {
            return 1000;
        }

        File getExcelFile();

        default Integer getDataRowIndex() {
            return getHeaderRowIndex() + 1;
        }

        default Integer getSheetIndex() {
            return null;
        }

        default Integer getSheetIndex(Workbook workbook) {
            return null;
        }

        default void setAddionalHeader(Map<Integer, String> headerCellMap) {
        }

        default void setExtra(Map<Integer, String> headerCellMap, Map<String, Field> headerMap){
        };
    }

    public interface GetList4Excel {
        Collection getList(int pageNo) throws ExcelException;

        int getTotalCount();

        void progress(int done);

        void setResultCount(int count);

        default Class<?> getVoClass(){return null;};
    }

    public interface CellStyleDecorator {
        void setHeaderStyle(SXSSFWorkbook workbook, Cell cell);
    }

    public static class ExcelException extends Exception {
        public ExcelException(String msg, Exception e) {
            super(msg, e);
        }

        public ExcelException(String msg) {
            super(msg);
        }
    }

    public static Cell getOrCreateCell(Sheet sheet, int rowIdx, int colIdx) {
        Row row = sheet.getRow(rowIdx);
        if (row == null) {
            row = sheet.createRow(rowIdx);
        }
        Cell cell = row.getCell(colIdx);
        if (cell == null) {
            cell = row.createCell(colIdx);
        }
        return cell;
    }

    public static void addComment(Workbook workbook, Sheet sheet, int rowIdx, int colIdx, String author, String commentText, CellStyle errorStyle) {
        CreationHelper factory = workbook.getCreationHelper();
        Cell cell = getOrCreateCell(sheet, rowIdx, colIdx);

        logger.info(String.format("%s,%s : %s       -  %s",rowIdx,colIdx,ExcelUtil.toCellAddr(rowIdx,colIdx),commentText));
        Comment comment = cell.getCellComment();
        if (null == comment) {
            ClientAnchor anchor = factory.createClientAnchor();
            anchor.setCol1(cell.getColumnIndex() + 1);
            anchor.setCol2(cell.getColumnIndex() + 3);
            anchor.setRow1(rowIdx + 1);
            anchor.setRow2(rowIdx + 5);
            Drawing<?> drawing = sheet.createDrawingPatriarch();
            comment = drawing.createCellComment(anchor);
            comment.setAuthor(author);
            comment.setString(factory.createRichTextString(commentText));
            cell.setCellComment(comment);
        } else {
            RichTextString string = comment.getString();
            String s = string.getString() + commentText;
            comment.setString(factory.createRichTextString(s));
        }
        cell.setCellStyle(errorStyle);
    }
    public static String toCellAddr(int row, int col){
        String s = CellReference.convertNumToColString(col);
        return String.format("%s%s",s,row+1);
    }
}