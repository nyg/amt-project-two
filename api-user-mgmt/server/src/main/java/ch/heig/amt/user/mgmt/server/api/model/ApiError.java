package ch.heig.amt.user.mgmt.server.api.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;

import javax.annotation.Generated;

@Getter
@JsonTypeName("error")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")
public class ApiError {

    public static final ApiError INTERNAL = new ApiError("Internal error");
    public static final ApiError AUTHENTICATION = new ApiError("Authentication failed");
    public static final ApiError INACTIVE = new ApiError("Inactive account");
    public static final ApiError NOT_FOUND = new ApiError("Path not found");
    public static final ApiError USER_NOT_FOUND = new ApiError("User not found");

    private String message;

    public ApiError(String message) {
        this.message = message;
    }
}
