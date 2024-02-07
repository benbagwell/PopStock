package com.nashss.se.popstock.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.popstock.activity.requests.UpdateWarehouseRequest;
import com.nashss.se.popstock.activity.results.UpdateWarehouseResult;

public class UpdateWarehouseLambda extends LambdaActivityRunner<UpdateWarehouseRequest, UpdateWarehouseResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateWarehouseRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateWarehouseRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdateWarehouseRequest unauthenticatedUpdateRequest = input.fromPath(path -> UpdateWarehouseRequest.builder()
                        .withWarehouseId(path.get("warehouseId"))
                        .build());
                UpdateWarehouseRequest unauthenticatedRequest = input.fromBody(UpdateWarehouseRequest.class);
                return input.fromUserClaims(claims ->
                        UpdateWarehouseRequest.builder()
                                .withUserId(claims.get("email"))
                                .withWarehouseId(unauthenticatedUpdateRequest.getWarehouseId())
                                .withName(unauthenticatedRequest.getName())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateWarehouseActivity().handleRequest(request)
        );
    }
}
