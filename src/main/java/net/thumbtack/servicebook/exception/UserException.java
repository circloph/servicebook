package net.thumbtack.servicebook.exception;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import net.thumbtack.servicebook.error.Error;
import net.thumbtack.servicebook.error.ErrorCode;

public class UserException extends RuntimeException implements GraphQLError {

    private Error error;

    public UserException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        error = new Error(errorCode.toString(), errorCode.getField(), errorCode.getMessage());
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("field", error.getField());
    }
}
