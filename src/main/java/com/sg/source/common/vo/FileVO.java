package com.sg.source.common.vo;

import java.util.List;

public class FileVO {

    private String atchmnflGroupId;

	/*첨부파일 일련번호*/
	private Integer atchmnflSn;
	
	/* 저장경로 */
	private String storePath;
	
	/* 파일명 */
	private String fileNm;
	
	/* 파일확장자 */
	private String fileExtsn;
	
	/* 저장파일명 */
	private String storeFileNm;

	/* 파일타입 */
	private String fileTy;
	
	/* 파일사이즈 */
	private long fileSize;
	
	/* 임시저장경로 */
	private String tempStorePath;
	
	/* 임시저장파일명 */
	private String tempFileNm;
	
	/* 파일 경로 */
	private String filePath;
	
	/* 파일 상세 */
	private String fileDetail;
	
	/* 파일 순번 */
	private Integer fileOrdr;
	
	/* 첨부파일목록 */
	private List<FileVO> atchmnflList;

	/* 삭제여부 */
    private String deleteAt;

	public String getAtchmnflGroupId() {
		return atchmnflGroupId;
	}

	public void setAtchmnflGroupId(String atchmnflGroupId) {
		this.atchmnflGroupId = atchmnflGroupId;
	}

	public Integer getAtchmnflSn() {
		return atchmnflSn;
	}

	public void setAtchmnflSn(Integer atchmnflSn) {
		this.atchmnflSn = atchmnflSn;
	}

	public String getStorePath() {
		return storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getFileExtsn() {
		return fileExtsn;
	}

	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}

	public String getStoreFileNm() {
		return storeFileNm;
	}

	public void setStoreFileNm(String storeFileNm) {
		this.storeFileNm = storeFileNm;
	}

	public String getFileTy() {
		return fileTy;
	}

	public void setFileTy(String fileTy) {
		this.fileTy = fileTy;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getTempStorePath() {
		return tempStorePath;
	}

	public void setTempStorePath(String tempStorePath) {
		this.tempStorePath = tempStorePath;
	}

	public String getTempFileNm() {
		return tempFileNm;
	}

	public void setTempFileNm(String tempFileNm) {
		this.tempFileNm = tempFileNm;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileDetail() {
		return fileDetail;
	}

	public void setFileDetail(String fileDetail) {
		this.fileDetail = fileDetail;
	}

	public Integer getFileOrdr() {
		return fileOrdr;
	}

	public void setFileOrdr(Integer fileOrdr) {
		this.fileOrdr = fileOrdr;
	}

	public List<FileVO> getAtchmnflList() {
		return atchmnflList;
	}

	public void setAtchmnflList(List<FileVO> atchmnflList) {
		this.atchmnflList = atchmnflList;
	}

	public String getDeleteAt() {
		return deleteAt;
	}

	public void setDeleteAt(String deleteAt) {
		this.deleteAt = deleteAt;
	}
}
