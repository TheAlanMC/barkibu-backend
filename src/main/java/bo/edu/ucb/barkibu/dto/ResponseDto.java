package bo.edu.ucb.barkibu.dto;
//TODO: MAKE ERROR CODES DICTIOANRY
//CODE=0 -> OK
public class ResponseDto<T>{
    private T result;
    private String statusCode;
    private String errorDetail;

    public ResponseDto() {
    }

    public ResponseDto(T result, String statusCode, String errorDetail) {
        this.result = result;
        this.statusCode = statusCode;
        this.errorDetail = errorDetail;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "result=" + result +
                ", statusCode='" + statusCode + '\'' +
                ", errorDetail='" + errorDetail + '\'' +
                '}';
    }
}
