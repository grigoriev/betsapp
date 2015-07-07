package eu.grigoriev.model.response;

public class AjaxResponse {
    private String status;

    public AjaxResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
