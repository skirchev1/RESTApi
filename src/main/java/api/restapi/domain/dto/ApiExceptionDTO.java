package api.restapi.domain.dto;

import java.util.Date;

public class ApiExceptionDTO {
    private String msg;

    private Date date;

    public ApiExceptionDTO(String msg) {
        this.msg = msg;
        this.date = new Date();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
