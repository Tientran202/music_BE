package com.example.be.dto.response.admin;

import java.util.Date;

public class HiddenUserResponse {
    private String user_name;
    private int user_id;
    private String reportUserName;
    private int annunciator_id;
    private Date reporting_time;
    private String content;
    private Date hidden_time;

    public HiddenUserResponse() {
    }

    public HiddenUserResponse(String user_name, int user_id, String reportUserName, int annunciator_id,
            Date reporting_time, String content, Date hidden_time) {
        this.user_name = user_name;
        this.user_id = user_id;
        this.reportUserName = reportUserName;
        this.annunciator_id = annunciator_id;
        this.reporting_time = reporting_time;
        this.content = content;
        this.hidden_time = hidden_time;
    }

    public int getAnnunciator_id() {
        return annunciator_id;
    }

    public void setAnnunciator_id(int annunciator_id) {
        this.annunciator_id = annunciator_id;
    }

    public String getReportUserName() {
        return reportUserName;
    }

    public void setReportUserName(String reportUserName) {
        this.reportUserName = reportUserName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getReporting_time() {
        return reporting_time;
    }

    public void setReporting_time(Date reporting_time) {
        this.reporting_time = reporting_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getHidden_time() {
        return hidden_time;
    }

    public void setHidden_time(Date hidden_time) {
        this.hidden_time = hidden_time;
    }

}
