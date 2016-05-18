package eu.grigoriev.model.response;

public class SuccessResponse extends AjaxResponse {
    public SuccessResponse() {
        super(AjaxStatus.SUCCESS);
    }

    public SuccessResponse(String status) {
        super(status);
    }
}
