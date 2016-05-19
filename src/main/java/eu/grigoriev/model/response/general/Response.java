package eu.grigoriev.model.response.general;

public abstract class Response {
    private String status;

    protected Response(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
