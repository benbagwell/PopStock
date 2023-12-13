package com.nashss.se.popstock.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.popstock.activity.requests.GetWarehouseRequest;
import com.nashss.se.popstock.activity.results.GetWarehouseResult;

public class GetWarehouseLambda
        extends LambdaActivityRunner<GetWarehouseRequest, GetWarehouseResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetWarehouseRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetWarehouseRequest> input, Context context) {
        return super.runActivity(
            () -> {
                GetWarehouseRequest unauthenticatedRequest = input.fromPath(path -> GetWarehouseRequest.builder()
                        .withWarehouseId(path.get("warehouseId"))
                        .build());

                return input.fromUserClaims(claims ->
                        GetWarehouseRequest.builder()
                                .withUserId(claims.get("email"))
                                .withWarehouseId(unauthenticatedRequest.getWarehouseId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetWarehouseActivity().handleRequest(request)
        );
    }
}
