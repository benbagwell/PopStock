package com.nashss.se.popstock.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.popstock.activity.requests.CreateItemRequest;
import com.nashss.se.popstock.activity.results.CreateItemResult;

public class                 CreateItemLambda
        extends LambdaActivityRunner<CreateItemRequest, CreateItemResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateItemRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateItemRequest> input, Context context) {
        return super.runActivity(
            () -> {
                CreateItemRequest unauthenticatedRequest = input.fromBody(CreateItemRequest.class);
                return input.fromUserClaims(claims ->
                        CreateItemRequest.builder()
                                .withName(unauthenticatedRequest.getName())
                                .withCategory(unauthenticatedRequest.getCategory())
                                .withRegionOfOrigin(unauthenticatedRequest.getRegionOfOrigin())
                                .withRegionalDemand(unauthenticatedRequest.getRegionalDemand())
                                .withSalesForecast(unauthenticatedRequest.getSalesForecast())
                                .withPerPallet(unauthenticatedRequest.getPerPallet())
                                .withWeight(unauthenticatedRequest.getWeight())
                                .withPurchaseCost(unauthenticatedRequest.getPurchaseCost())
                                .withBaseMargin(unauthenticatedRequest.getBaseMargin())
                                .withRateOfReplenishment(unauthenticatedRequest.getRateOfReplenishment())
                                .withSynergy(unauthenticatedRequest.getSynergy())
                                .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateItemActivity().handleRequest(request)
        );
    }
}
