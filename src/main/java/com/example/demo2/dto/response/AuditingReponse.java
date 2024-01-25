package com.example.demo2.dto.response;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class AuditingReponse {
    public String createBy;
    @Temporal(TemporalType.TIMESTAMP)
    public Date createDate;
    public String lastModifiedBy;
    @Temporal(TemporalType.TIMESTAMP)
    public Date lastmodifiedDate;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastmodifiedDate() {
        return lastmodifiedDate;
    }

    public void setLastmodifiedDate(Date lastmodifiedDate) {
        this.lastmodifiedDate = lastmodifiedDate;
    }

    public AuditingReponse(String createBy, Date createDate, String lastModifiedBy, Date lastmodifiedDate) {
        this.createBy = createBy;
        this.createDate = createDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastmodifiedDate = lastmodifiedDate;
    }

    public AuditingReponse() {
    }
}
