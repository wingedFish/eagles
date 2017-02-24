package com.wingedfish.eagles.metadata.domain.monitor;

/**
 * Created by wingedfish on 2017/2/22.
 */
public class ScheduleJobInfo {
    private long monitorId;
    private long systemId;
    private String monitorKey;
    private String descKey;
    private String cronExpression;
    private int status;

    public long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(long monitorId) {
        this.monitorId = monitorId;
    }

    public long getSystemId() {
        return systemId;
    }

    public void setSystemId(long systemId) {
        this.systemId = systemId;
    }

    public String getMonitorKey() {
        return monitorKey;
    }

    public void setMonitorKey(String monitorKey) {
        this.monitorKey = monitorKey;
    }

    public String getDescKey() {
        return descKey;
    }

    public void setDescKey(String descKey) {
        this.descKey = descKey;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
