package ink.reactor.api.plugin.service.economy;

/**
 * Represents the response of an economic transaction.
 * Contains information about the operation result, the updated balance, and potential error messages.
 */
public record EconomyResponse(
    double amount,
    double balance,
    ResponseType type,
    String errorMessage
) {

    public enum ResponseType {
        SUCCESS,
        FAILURE,
        NOT_IMPLEMENTED;
    }

    public boolean transactionSuccess() {
        return type == ResponseType.SUCCESS;
    }
}
