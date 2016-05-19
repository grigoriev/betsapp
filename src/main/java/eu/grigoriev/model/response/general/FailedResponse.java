package eu.grigoriev.model.response.general;

public class FailedResponse extends Response {
    public FailedResponse() {
        super(ResponseStatus.FAILED);
    }

    private FailedResponse(String status) {
        super(status);
    }
}
