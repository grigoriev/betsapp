package eu.grigoriev.model.response.general;

public class SuccessResponse extends Response {
    public SuccessResponse() {
        super(ResponseStatus.SUCCESS);
    }

    private SuccessResponse(String status) {
        super(status);
    }
}
