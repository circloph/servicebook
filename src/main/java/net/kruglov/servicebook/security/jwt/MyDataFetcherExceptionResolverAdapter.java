package net.kruglov.servicebook.security.jwt;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import graphql.GraphQLError;
import graphql.GraphqlErrorException;
import graphql.schema.DataFetchingEnvironment;

@Component
public class MyDataFetcherExceptionResolverAdapter extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return GraphqlErrorException.newErrorException().message(ex.getMessage()).cause(ex).build();
    }
}
