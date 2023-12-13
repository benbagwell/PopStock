package com.nashss.se.popstock.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.popstock.activity.requests.GetItemsRequest;
import com.nashss.se.popstock.activity.results.GetItemsResult;

public class GetItemsLambda extends LambdaActivityRunner<GetItemsRequest, GetItemsResult> implements RequestHandler<AuthenticatedLambdaRequest<GetItemsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetItemsRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    GetItemsRequest unauthenticatedRequest = input.fromPath(path -> GetItemsRequest.builder()
                            .withWarehouseId(path.get("warehouseId"))
                            .build());

                    return input.fromUserClaims(claims ->
                            GetItemsRequest.builder()
                                    .withWarehouseId(unauthenticatedRequest.getWarehouseId())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideGetItemsActivity().handleRequest(request)
        );
    }
}
