package com.nashss.se.popstock.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.popstock.activity.requests.CreateTransactionRequest;
import com.nashss.se.popstock.activity.results.CreateTransactionResult;

public class CreateTransactionLambda
        extends LambdaActivityRunner<CreateTransactionRequest, CreateTransactionResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateTransactionRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateTransactionRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateTransactionRequest unauthenticatedRequest = input.fromBody(CreateTransactionRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateTransactionRequest.builder()
                                    .withWarehouseId(unauthenticatedRequest.getWarehouseId())
                                    .withItemId(unauthenticatedRequest.getItemId())
                                    .withCount(unauthenticatedRequest.getCount())
                                    .withTransactionDate(unauthenticatedRequest.getTransactionDate())
                                    .withPartnerId(unauthenticatedRequest.getPartnerId())
                                    .withTransactionType(unauthenticatedRequest.getTransactionType())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateTransactionActivity().handleRequest(request)
        );
    }
}
