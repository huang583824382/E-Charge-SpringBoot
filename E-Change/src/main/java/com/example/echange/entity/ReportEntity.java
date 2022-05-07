package com.example.echange.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "report", schema = "e-change", catalog = "")
public class ReportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "report_id")
    private int reportId;
    @Basic
    @Column(name = "type")
    private Integer type;
    @Basic
    @Column(name = "target_user_id")
    private Integer targetUserId;
    @Basic
    @Column(name = "target_item_id")
    private Integer targetItemId;
    @Basic
    @Column(name = "whistleblower_id")
    private Integer whistleblowerId;
    @Basic
    @Column(name = "report_time")
    private Timestamp reportTime;
    @Basic
    @Column(name = "state")
    private Integer state;
    @Basic
    @Column(name = "option_id")
    private Integer optionId;

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Integer targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Integer getTargetItemId() {
        return targetItemId;
    }

    public void setTargetItemId(Integer targetItemId) {
        this.targetItemId = targetItemId;
    }

    public Integer getWhistleblowerId() {
        return whistleblowerId;
    }

    public void setWhistleblowerId(Integer whistleblowerId) {
        this.whistleblowerId = whistleblowerId;
    }

    public Timestamp getReportTime() {
        return reportTime;
    }

    public void setReportTime(Timestamp reportTime) {
        this.reportTime = reportTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportEntity that = (ReportEntity) o;

        if (reportId != that.reportId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (targetUserId != null ? !targetUserId.equals(that.targetUserId) : that.targetUserId != null) return false;
        if (targetItemId != null ? !targetItemId.equals(that.targetItemId) : that.targetItemId != null) return false;
        if (whistleblowerId != null ? !whistleblowerId.equals(that.whistleblowerId) : that.whistleblowerId != null)
            return false;
        if (reportTime != null ? !reportTime.equals(that.reportTime) : that.reportTime != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (optionId != null ? !optionId.equals(that.optionId) : that.optionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reportId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (targetUserId != null ? targetUserId.hashCode() : 0);
        result = 31 * result + (targetItemId != null ? targetItemId.hashCode() : 0);
        result = 31 * result + (whistleblowerId != null ? whistleblowerId.hashCode() : 0);
        result = 31 * result + (reportTime != null ? reportTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (optionId != null ? optionId.hashCode() : 0);
        return result;
    }
}
