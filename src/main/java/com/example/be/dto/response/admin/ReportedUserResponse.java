package com.example.be.dto.response.admin;

import java.util.Date;

public class ReportedUserResponse {
    private String userName;
    private int userId;
    private String reportUserName;
    private int annunciatorId;
    private String reportContent;
    private Date day;

    public ReportedUserResponse() {
    }

    public ReportedUserResponse(String userName, int userId, String reportUserName, int annunciatorId,
            String reportContent, Date day) {
        this.userName = userName;
        this.userId = userId;
        this.reportUserName = reportUserName;
        this.annunciatorId = annunciatorId;
        this.reportContent = reportContent;
        this.day = day;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReportUserName() {
        return reportUserName;
    }

    public void setReportUserName(String reportUserName) {
        this.reportUserName = reportUserName;
    }

    public int getAnnunciatorId() {
        return annunciatorId;
    }

    public void setAnnunciatorId(int annunciatorId) {
        this.annunciatorId = annunciatorId;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

}
