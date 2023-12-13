package com.nashss.se.popstock.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.popstock.activity.requests.CreateItemRequest;
import com.nashss.se.popstock.activity.results.CreateItemResult;

public class CreateItemLambda
        extends LambdaActivityRunner<CreateItemRequest, CreateItemResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateItemRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateItemRequest> input, Context context) {
        return super.runActivity(
            () -> {
                CreateItemRequest unauthenticatedRequest = input.fromBody(CreateItemRequest.class);
                return input.fromUserClaims(claims ->
                        CreateItemRequest.builder()
                                .withWarehouseId(unauthenticatedRequest.getWarehouseId())
                                .withName(unauthenticatedRequest.getName())
                                .withCategory(unauthenticatedRequest.getCategory())
                                .withSalesForecast(unauthenticatedRequest.getSalesForecast())
                                .withPerPallet(unauthenticatedRequest.getPerPallet())
                                .withWeight(unauthenticatedRequest.getWeight())
                                .withRateOfReplenishment(unauthenticatedRequest.getRateOfReplenishment())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreateItemActivity().handleRequest(request)
        );
    }
}
